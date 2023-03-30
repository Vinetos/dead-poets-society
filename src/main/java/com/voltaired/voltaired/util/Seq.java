package com.voltaired.voltaired.util;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.voltaired.voltaired.util.Pair.pair;

public interface Seq<T> extends Stream<T>, Iterable<T> {

    // <editor-fold desc="Initializers">
    @SafeVarargs
    static <T> Seq<T> of(final T... values) {
        return () -> Arrays.stream(values);
    }

    @SafeVarargs
    static <T> Seq<T> seq(final T... values) {
        return Seq.of(values);
    }

    static <T> Seq<T> seq(final Collection<T> values) {return Seq.of(values);}

    static <T> Seq<T> seq(final Stream<T> values) {return Seq.of(values);}

    static <T> Seq<T> seq(final Iterator<T> values) {return Seq.of(values);}

    static <T> Seq<T> of(final Collection<T> values) {
        return values != null ? values::stream : Stream::empty;
    }

    static <T> Seq<T> of(final Stream<T> values) {
        return () -> values;
    }

    static <T> Seq<T> of(final Iterator<T> values) {
        if (values == null) {return Stream::empty;}
        final Iterable<T> iterable = () -> values;
        return () -> StreamSupport.stream(iterable.spliterator(), false);
    }

    // </editor-fold>

    Stream<T> delegate();

    // <editor-fold desc="Overrides">
    @Override
    default Seq<T> filter(final Predicate<? super T> predicate) {
        return () -> delegate().filter(predicate);
    }

    @Override
    default <R> Seq<R> map(final Function<? super T, ? extends R> mapper) {
        return () -> delegate().map(mapper);
    }

    @Override
    default IntStream mapToInt(final ToIntFunction<? super T> mapper) {
        return delegate().mapToInt(mapper);
    }

    @Override
    default LongStream mapToLong(final ToLongFunction<? super T> mapper) {
        return delegate().mapToLong(mapper);
    }

    @Override
    default DoubleStream mapToDouble(final ToDoubleFunction<? super T> mapper) {
        return delegate().mapToDouble(mapper);
    }

    @Override
    default <R> Seq<R> flatMap(final Function<? super T, ? extends Stream<? extends R>> mapper) {
        return () -> delegate().flatMap(mapper);
    }

    @Override
    default IntStream flatMapToInt(final Function<? super T, ? extends IntStream> mapper) {
        return delegate().flatMapToInt(mapper);
    }

    @Override
    default LongStream flatMapToLong(final Function<? super T, ? extends LongStream> mapper) {
        return delegate().flatMapToLong(mapper);
    }

    @Override
    default DoubleStream flatMapToDouble(final Function<? super T, ? extends DoubleStream> mapper) {
        return delegate().flatMapToDouble(mapper);
    }

    @Override
    default Seq<T> distinct() {
        return () -> delegate().distinct();
    }

    @Override
    default Seq<T> sorted() {
        return () -> delegate().sorted();
    }

    @Override
    default Seq<T> sorted(final Comparator<? super T> comparator) {
        return () -> delegate().sorted(comparator);
    }

    @Override
    default Seq<T> peek(final Consumer<? super T> action) {
        return () -> delegate().peek(action);
    }

    @Override
    default Seq<T> limit(final long maxSize) {
        return () -> delegate().limit(maxSize);
    }

    @Override
    default Seq<T> skip(final long n) {
        return () -> delegate().skip(n);
    }

    @Override
    default void forEach(final Consumer<? super T> action) {
        delegate().forEach(action);
    }

    @Override
    default void forEachOrdered(final Consumer<? super T> action) {
        delegate().forEachOrdered(action);
    }

    @Override
    default Object[] toArray() {
        return delegate().toArray();
    }

    @SuppressWarnings("SuspiciousToArrayCall")
    @Override
    default <A> A[] toArray(final IntFunction<A[]> generator) {
        return delegate().toArray(generator);
    }

    @Override
    default T reduce(final T identity, final BinaryOperator<T> accumulator) {
        return delegate().reduce(identity, accumulator);
    }

    @Override
    default Optional<T> reduce(final BinaryOperator<T> accumulator) {
        return delegate().reduce(accumulator);
    }

    @Override
    default <U> U reduce(final U identity,
                         final BiFunction<U, ? super T, U> accumulator,
                         final BinaryOperator<U> combiner) {
        return delegate().reduce(identity, accumulator, combiner);
    }

    @Override
    default <R> R collect(final Supplier<R> supplier,
                          final BiConsumer<R, ? super T> accumulator,
                          final BiConsumer<R, R> combiner) {
        return delegate().collect(supplier, accumulator, combiner);
    }

    @Override
    default <R, A> R collect(final Collector<? super T, A, R> collector) {
        return delegate().collect(collector);
    }

    @Override
    default Optional<T> min(final Comparator<? super T> comparator) {
        return delegate().min(comparator);
    }

    @Override
    default Optional<T> max(final Comparator<? super T> comparator) {
        return delegate().max(comparator);
    }

    @Override
    default long count() {
        return delegate().count();
    }

    @Override
    default boolean anyMatch(final Predicate<? super T> predicate) {
        return delegate().anyMatch(predicate);
    }

    @Override
    default boolean allMatch(final Predicate<? super T> predicate) {
        return delegate().allMatch(predicate);
    }

    @Override
    default boolean noneMatch(final Predicate<? super T> predicate) {
        return delegate().noneMatch(predicate);
    }

    @Override
    default Optional<T> findFirst() {
        return delegate().findFirst();
    }

    @Override
    default Optional<T> findAny() {
        return delegate().findAny();
    }

    @Override
    default Iterator<T> iterator() {
        return delegate().iterator();
    }

    @Override
    default Spliterator<T> spliterator() {
        return delegate().spliterator();
    }

    @Override
    default boolean isParallel() {
        return delegate().isParallel();
    }

    @Override
    default Stream<T> sequential() {
        return delegate().sequential();
    }

    @Override
    default Stream<T> parallel() {
        return delegate().parallel();
    }

    @Override
    default Stream<T> unordered() {
        return delegate().unordered();
    }

    @Override
    default Stream<T> onClose(final Runnable closeHandler) {
        return delegate().onClose(closeHandler);
    }

    @Override
    default void close() {
        delegate().close();
    }

    // </editor-fold>

    /**
     * Find the first element matching the given predicate.
     *
     * @param predicate
     *         The predicate to match.
     *
     * @return The first found item, as an optional.
     */
    default Optional<T> first(final Predicate<T> predicate) {
        return delegate().filter(predicate).findFirst();
    }

    /**
     * Find any element matching the given predicate.
     *
     * @param predicate
     *         The predicate to match.
     *
     * @return Any found item, as an optional.
     */
    default Optional<T> any(final Predicate<T> predicate) {
        return delegate().filter(predicate).findAny();
    }

    /**
     * Creates a map out of the stream. In case of key duplicates, the latest element in the original stream will
     * overwrite the one(s) in place.
     *
     * @param keyMapper
     *         mapping function to extract map keys.
     * @param <KEY_TYPE>
     *         the expected type of key.
     *
     * @return the created map.
     */
    default <KEY_TYPE> Map<KEY_TYPE, T>
    toMap(final Function<T, KEY_TYPE> keyMapper) {
        return delegate().collect(Collectors.toMap(keyMapper, it -> it, (oldKey, newKey) -> newKey));
    }

    /**
     * Creates a map out of the stream. In case of key duplicates, the latest element in the original stream will
     * overwrite the one(s) in place.
     *
     * @param map
     *         the map to fill/update.
     * @param keyMapper
     *         mapping function to extract map keys.
     * @param valueMapper
     *         mapping function to extract map values.
     * @param <KEY_TYPE>
     *         the expected type of key.
     * @param <VALUE_TYPE>
     *         the expected type of value.
     * @param <MAP_TYPE>
     *         the complete return type.
     *
     * @return the created map.
     */
    default <KEY_TYPE, VALUE_TYPE, MAP_TYPE extends Map<KEY_TYPE, VALUE_TYPE>>
    MAP_TYPE toMap(final MAP_TYPE map,
                   final Function<T, KEY_TYPE> keyMapper,
                   final Function<T, VALUE_TYPE> valueMapper) {
        delegate().forEach(it -> map.put(keyMapper.apply(it), valueMapper.apply(it)));
        return map;
    }

    /**
     * Creates a map out of the stream. In case of key duplicates, the latest element in the original stream will
     * overwrite the one(s) in place.
     *
     * @param keyMapper
     *         mapping function to extract map keys.
     * @param valueMapper
     *         mapping function to extract map values.
     * @param <KEY_TYPE>
     *         the expected type of key.
     * @param <VALUE_TYPE>
     *         the expected type of value.
     *
     * @return the created map.
     */
    default <KEY_TYPE, VALUE_TYPE>
    Map<KEY_TYPE, VALUE_TYPE> toMap(final Function<T, KEY_TYPE> keyMapper,
                                    final Function<T, VALUE_TYPE> valueMapper) {
        return delegate().collect(Collectors.toMap(keyMapper, valueMapper, (oldKey, newKey) -> newKey));
    }

    /**
     * Converts the stream to a list.
     *
     * @return the created list.
     */
    default List<T> toList() {
        return delegate().collect(Collectors.toList());
    }

    /**
     * Dump the content of the stream to the given list.
     *
     * @param list
     *         the list to dump values to.
     * @param <LIST>
     *         the exact type of list.
     *
     * @return the updated list.
     */
    default <LIST extends List<T>> LIST toList(final LIST list) {
        delegate().forEach(list::add);
        return list;
    }

    /**
     * Convert the stream to a set.
     *
     * @return the built set.
     */
    default Set<T> toSet() {
        return delegate().collect(Collectors.toSet());
    }

    /**
     * Dump the content of the stream to the given set.
     *
     * @param set
     *         the set to update
     * @param <SET>
     *         the set type.
     *
     * @return the updated set.
     */
    default <SET extends Set<T>> SET toSet(final SET set) {
        delegate().forEach(set::add);
        return set;
    }

    /**
     * Creates a stream of pairs of the content of the stream and values produces by a supplier.
     *
     * @param supplier
     *         the value supplier.
     * @param <ASSOCIATED_TYPE>
     *         the type of associated values.
     *
     * @return the built stream.
     */
    default <ASSOCIATED_TYPE>
    Seq<Pair<T, ASSOCIATED_TYPE>> associate(final Supplier<ASSOCIATED_TYPE> supplier) {
        return () -> delegate().map(it -> pair(it, supplier.get()));
    }

    /**
     * Creates a stream of pairs of the content of the stream and values produces by a function applied to the value.
     *
     * @param function
     *         the value generation function.
     * @param <ASSOCIATED_TYPE>
     *         the type of associated values.
     *
     * @return the built stream.
     */
    default <ASSOCIATED_TYPE>
    Seq<Pair<T, ASSOCIATED_TYPE>> associate(final Function<T, ASSOCIATED_TYPE> function) {
        return () -> delegate().map(it -> pair(it, function.apply(it)));
    }

    /**
     * Creates a stream of pairs of the content of the stream and values produces by an other stream. Once any of the
     * two streams is close, the produced stream is complete, regardless of potential values remaining in the other
     * stream.
     *
     * @param supplier
     *         the value supplier.
     * @param <ASSOCIATED_TYPE>
     *         the type of associated values.
     *
     * @return the built stream.
     */
    default <ASSOCIATED_TYPE>
    Seq<Pair<T, ASSOCIATED_TYPE>> associate(final Stream<ASSOCIATED_TYPE> supplier) {
        // FIXME: 08/09/2020 stream implement
        final List<T> elements = toList();
        final List<ASSOCIATED_TYPE> associated = Seq.of(supplier).toList();
        final int size = Math.min(elements.size(), associated.size());

        return Seq.of(elements).limit(size).associate(new IteratorSupplier<>(associated.iterator()));
    }

    /**
     * Associate each element with its index in the sequence, starting at the given value.
     *
     * @param startAt
     *         The start index.
     *
     * @return A Seq of pair, index to the left, element to the right.
     */
    default Seq<Pair<Integer, T>> indexed(final int startAt) {
        final AtomicInteger counter = new AtomicInteger(startAt);
        return this.map(element -> pair(counter.getAndIncrement(), element));
    }

    /**
     * Associate each element to its index in the sequence, starting at 0.
     *
     * @return A Seq of pair, index to the left, element to the right.
     */
    default Seq<Pair<Integer, T>> indexed() {
        return indexed(0);
    }

    /**
     * Prints the element of the stream on the standard output.
     *
     * @return this.
     */
    default Seq<T> print() {
        return () -> delegate().peek(it -> System.out.println(it.toString()));
    }

    /**
     * Cast the elements of the stream to the given class, or replace them by null if casting is not possible.
     *
     * @param castTo
     *         The cast destination class.
     * @param <CLASS_T>
     *         Cast type.
     *
     * @return The casted sequence.
     */
    default <CLASS_T> Seq<CLASS_T> castOrNull(final Class<CLASS_T> castTo) {
        return map(element -> {
            if (element == null) {
                return null;
            }
            return castTo.isAssignableFrom(element.getClass())
                   ? castTo.cast(element)
                   : null;
        });
    }

    /**
     * Cast the elements of the stream to the given class, or skip them if casting is not possible.
     *
     * @param castTo
     *         The cast destination class.
     * @param <CLASS_T>
     *         Cast type.
     *
     * @return The casted sequence.
     */
    default <CLASS_T> Seq<CLASS_T> castOrSkip(final Class<CLASS_T> castTo) {
        return this.filter(element -> castTo.isAssignableFrom(element.getClass()))
                   .map(element -> castTo.isAssignableFrom(element.getClass())
                                   ? castTo.cast(element)
                                   : null);
    }

    /**
     * Remove all null objects from the stream.
     *
     * @return The streams minus all null elements.
     */
    default Seq<T> filterNulls() {
        return filter(Objects::nonNull);
    }

    /**
     * Find the first non-null object.
     *
     * @return The first non-null object of the stream.
     */
    default Optional<T> firstNotNull() {
        return filter(Objects::nonNull).findFirst();
    }

    /**
     * Adds the content of the given stream to the current stream and returns it as a new one.
     *
     * @param stream
     *         the stream to add.
     *
     * @return a new stream containing the current one then the given one.
     */
    default Seq<T> plus(final Stream<T> stream) {
        final List<T> list = toList();
        final var updated = Seq.of(stream).toList(list);
        return Seq.of(updated);
    }

    /**
     * Adds the content of the given stream to the current stream and returns it as a new one.
     *
     * @param value
     *         the value to add.
     *
     * @return a new stream containing the current one then the given one.
     */
    default Seq<T> plus(T value) {
        final List<T> list = toList();
        final var updated = Seq.of(value).toList(list);
        return Seq.of(updated);
    }

    /**
     * Build a string by joining the string representation of all contained values, interspersed with the given string
     * <code>delimiter</code>.
     *
     * @param delimiter
     *         the delimiter string.
     *
     * @return the built {@link String}.
     */
    default String join(final String delimiter) {
        return delegate().map(Objects::toString).collect(Collectors.joining(delimiter));
    }

    /**
     * Simple accessor for int summary.
     *
     * @param mappingFunction
     *         Function from element type to int.
     *
     * @return An int summary.
     */
    default IntSummaryStatistics intSummary(@NotNull ToIntFunction<T> mappingFunction) {
        return collect(Collectors.summarizingInt(mappingFunction));
    }

    /**
     * Build a string by joining the string representation of all contained values.
     *
     * @return the built {@link String}.
     */
    default String join() {
        return join("");
    }

    /**
     * Builds a pair of streams by partitioning the current one using the given pivot function.
     *
     * @param pivot
     *         the function to segregate the values of the given stream.
     * @param <KEY_TYPE>
     *         type of partition key.
     *
     * @return the pair of created streams.
     */
    default <KEY_TYPE>
    Seq<Pair<KEY_TYPE, Seq<T>>>
    partition(final Function<T, KEY_TYPE> pivot) {
        final Map<KEY_TYPE, List<T>> partitions = new HashMap<>();

        final Consumer<T> updater = value -> {
            final KEY_TYPE key = pivot.apply(value);
            if (!partitions.containsKey(key)) {
                partitions.put(key, new ArrayList<>());
            }
            partitions.get(key).add(value);
        };

        delegate().forEach(updater);

        return Seq.of(partitions.entrySet())
                  .map(it -> pair(it.getKey(), Seq.of(it.getValue())));
    }

    default <KEY_T> Seq<Pair<KEY_T, Integer>> countBy(final @NotNull Function<T, KEY_T> keyExtractor) {
        final var result = new HashMap<KEY_T, Integer>();
        delegate().forEach(element -> {
            final var key = keyExtractor.apply(element);
            result.putIfAbsent(key, 0);
            result.put(key, result.get(key) + 1);
        });
        return seq(result.entrySet()).map(entry -> pair(entry.getKey(), entry.getValue()));
    }

    @SuppressWarnings("WeakerAccess")
    class IteratorSupplier<T> implements Supplier<T> {
        private final Iterator<T> iterator;

        public IteratorSupplier(final Iterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public T get() {
            return iterator.hasNext() ? iterator.next() : null;
        }
    }
}

