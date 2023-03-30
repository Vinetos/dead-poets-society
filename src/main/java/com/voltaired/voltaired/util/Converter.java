package com.voltaired.voltaired.util;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.voltaired.voltaired.util.Optionals.opt;

/**
 * Interface for converters.
 *
 * @param <FROM_T>
 *         From type.
 * @param <TO_T>
 *         To type.
 */
@FunctionalInterface
public interface Converter<FROM_T, TO_T> {

    /**
     * Convert if input is not null, return null otherwise.
     *
     * @param input
     *         The input to convert.
     *
     * @return The converted value or null.
     */
    default TO_T convert(final FROM_T input) {
        if (input == null) {
            return null;
        }
        return convertNotNull(input);
    }

    /**
     * Convert given value, wrapped in an optional.
     *
     * @param input
     *         The input to convert.
     *
     * @return The converted value or null.
     */
    default Optional<TO_T> convertOpt(final FROM_T input) {
        return opt(input).map(this::convertNotNull);
    }

    /**
     * Convert the given non-null value. Implementor is not expected to check for nullity.
     *
     * @param input
     *         The value to convert, must be non-null.
     *
     * @return The converted value, may not be null.
     */
    @NotNull TO_T convertNotNull(@NotNull final FROM_T input);

    /**
     * Convert the objects contained in the given list.
     *
     * @param inputList
     *         The list of object to convert.
     *
     * @return A list of converted objects.
     */
    default List<TO_T> convertList(final List<FROM_T> inputList) {
        return inputList.stream().map(this::convert).toList();
    }

    /**
     * Convert the objects contained in the given set.
     *
     * @param inputSet
     *         The set of object to convert.
     *
     * @return A set of converted objects.
     */
    default Set<TO_T> convertSet(final Set<FROM_T> inputSet) {
        return inputSet.stream().map(this::convert).collect(Collectors.toSet());
    }
}

