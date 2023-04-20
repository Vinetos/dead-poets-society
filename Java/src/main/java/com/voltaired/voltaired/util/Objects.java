package com.voltaired.voltaired.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

/**
 * Utility methods for any object.
 */
public enum Objects {
    ;

    private static final Logger LOGGER = LoggerFactory.getLogger(Objects.class);

    /**
     * Try to cast `in` to CAST_T, wrapped in an optional.
     *
     * @param in
     *         The input value to cast.
     * @param out
     *         The class to cast to.
     * @param <T>
     *         The generic type of the output class.
     *
     * @return An optional wrapping the cast value, or null is casting is invalid.
     */
    public static <T> Optional<T> cast(final Object in, @NotNull final Class<T> out) {
        requireNonNull(out);

        if (null == in) {
            return Optional.empty();
        }

        if (out.isAssignableFrom(in.getClass())) {
            return Optional.of(out.cast(in));
        }

        return Optional.empty();
    }

    /**
     * Try and load a class from the classpath, wrapped as an optional.
     *
     * @param className
     *         The class to load.
     * @param <T>
     *         Type of class.
     *
     * @return An optional of the found class or empty.
     */
    @SuppressWarnings("unchecked") public static <T> Optional<Class<T>> loadClass(final String className) {
        if (className.isBlank()) throw new RuntimeException("Invalid blanc class name");

        try {
            return Optional.of((Class<T>) Class.forName(
                    className,
                    false,
                    Thread.currentThread().getContextClassLoader()
            ));
        } catch (final ClassNotFoundException classNotFoundException) {
            LOGGER.warn("class not found: {}, return empty.", className);
        }
        return Optional.empty();
    }

    /**
     * Utility method that test whether the given value is actually valued (not null). If it is, return it, otherwise,
     * trigger the given non-null supplier to return a backup value.
     *
     * @param nullableObject
     *         The object to test and return if not null.
     * @param backupSupplier
     *         The backup supplier, will be called only if nullableObject is null.
     * @param <T>
     *         The expected type of object.
     *
     * @return nullableObject if not null, otherwise the result of calling the backup supplier.
     */
    public static <T> @NotNull T either(T nullableObject,
                                        @NotNull Supplier<@NotNull T> backupSupplier) {
        return nullableObject != null ? nullableObject : backupSupplier.get();
    }

    /**
     * Utility method that test whether the given value is actually valued (not null). If it is, return it, otherwise,
     * trigger the given non-null supplier to return a backup value.
     *
     * @param nullableObject
     *         The object to test and return if not null.
     * @param backupValue
     *         The backup supplier, will be called only if nullableObject is null.
     * @param <T>
     *         The expected type of object.
     *
     * @return nullableObject if not null, otherwise the result of calling the backup supplier.
     */
    public static <T> @NotNull T either(T nullableObject, @NotNull T backupValue) {
        return nullableObject != null ? nullableObject : backupValue;
    }
}
