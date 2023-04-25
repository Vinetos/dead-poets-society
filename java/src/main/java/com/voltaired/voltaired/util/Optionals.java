package com.voltaired.voltaired.util;

import java.util.Optional;

/**
 * Utilities for optionals.
 */
public enum Optionals {
    ;

    /**
     * Shorthand for creating optionals.
     *
     * @param nullableValue
     *         The value to wrap.
     * @param <T>
     *         The expected type of value.
     *
     * @return A nullable optional of the given value.
     */
    public static <T> Optional<T> opt(final T nullableValue) {
        return Optional.ofNullable(nullableValue);
    }
}
