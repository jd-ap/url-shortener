package com.hexagonal.poc.shorturl.application.control;

import com.hexagonal.poc.shorturl.domain.Shortened;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<T> {

    private final Optional<T> value;
    private final Throwable cause;

    private static final Supplier<Throwable> emptyValue = () -> new EmptyValueResultException("no content");

    public static <T> Result<T> of(CheckedSupplier<Optional<T>> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");

        try {
            return new Result<T>(supplier.get(), null);
        } catch (Throwable throwable) {
            return new Result<>(Optional.empty(), throwable);
        }
    }

    public static <T> Result<T> ofNullable(CheckedSupplier<T> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");

        try {
            return new Result<>(Optional.ofNullable(supplier.get()), null);
        } catch (Throwable throwable) {
            return new Result<>(Optional.empty(), throwable);
        }
    }

    public static Result<Shortened> empty() {
        return new Result<>(Optional.empty(), emptyValue.get());
    }

    public final Result<T> onFailure(Consumer<? super Throwable> action) {
        Objects.requireNonNull(action, "action is null");

        if (this.isFailure())
            action.accept(this.cause);

        return this;
    }


    public T get() throws Throwable {
        if (isFailure())
            throw this.cause;

        return value.orElseThrow(emptyValue);
    }

    public boolean isFailure() {
        return null != this.cause;
    }

    public boolean isSuccess() {
        return null == this.cause;
    }

    @FunctionalInterface
    public interface CheckedSupplier<T> {
        T get() throws Throwable;
    }

}
