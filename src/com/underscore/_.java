package com.underscore;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import com.underscore.chain.ArrayChain;
import com.underscore.chain.CollectionChain;
import com.underscore.chain.MapChain;
import com.underscore.chain.impl.BooleanChainImpl;
import com.underscore.chain.impl.ByteChainImpl;
import com.underscore.chain.impl.CharChainImpl;
import com.underscore.chain.impl.CollectionChainImpl;
import com.underscore.chain.impl.DoubleChainImpl;
import com.underscore.chain.impl.FloatChainImpl;
import com.underscore.chain.impl.IntChainImpl;
import com.underscore.chain.impl.LongChainImpl;
import com.underscore.chain.impl.MapChainImpl;
import com.underscore.chain.impl.ShortChainImpl;

public final class _ {
	
	private _() { }
	
	/*
	 * Collections
	 */

	/**
	 * <p>
	 * Iterates over a list of elements, yielding each in turn to an iterator
	 * function. Each invocation of the iterator is called with the value in the
	 * list as the single argument. Delegates to the
	 * {@link List#forEach(Consumer)} method, and returns the original list for
	 * chaining.
	 * </p>
	 * 
	 * <p>
	 * <em>Note: an each loop cannot be broken out of--use
	 * {@link _#find(Collection, Predicate)} instead.</em>
	 * </p>
	 * 
	 * @param list
	 *            the list to iterate over
	 * @param iterator
	 *            a {@link java.util.function.Consumer Consumer} object to use
	 *            for iterating
	 * @return the original list, or {@code null} if array is null
	 */
	public static <T> Collection<T> each(final Collection<T> list, final Consumer<T> iterator) {
		if (list == null) return list;
		list.forEach(iterator);
		return list;
	}
	
	/**
	 * <p>
	 * Iterates over an array of elements, yielding each in turn to an iterator
	 * function. Each invocation of the iterator is called with the value in the
	 * list as the single argument. Delegates to the
	 * {@link List#forEach(Consumer)} method, and returns the original list for
	 * chaining.
	 * </p>
	 * 
	 * <p>
	 * <em>Note: an each loop cannot be broken out of--use 
	 * {@link _#find(T[], Predicate)} instead.</em>
	 * </p>
	 * 
	 * @param array
	 *            the array to iterate over
	 * @param iterator
	 *            a {@link java.util.function.Consumer Consumer} object to use
	 *            for iterating
	 * @return the original list, or {@code null} if array is null
	 */
	public static <T> T[] each(final T[] array, final Consumer<T> iterator) {
		if (array == null) return array;
		for (T value : array) {
			iterator.accept(value);
		}
		return array;
	}

	
	/**
	 * <p>
	 * Iterates over a map of key-value pairs, yielding each in turn to an iterator
	 * function. Each invocation of the iterator is called with {@code (key, value)} as the arguments. Delegates to the
	 * {@link Map#forEach(BiConsumer)} method, and returns the original map for
	 * chaining.
	 * </p>
	 * 
	 * <p>
	 * <em>Note: an each loop cannot be broken out of.</em>
	 * </p>
	 * 
	 * @param map
	 *            the map to iterate over
	 * @param iterator
	 *            a {@link java.util.function.Consumer Consumer} object to use
	 *            for iterating
	 * @return the original map, or {@code null} if map is null
	 */
	public static <K, V> Map<K, V> each(Map<K, V> map, BiConsumer<? super K, ? super V> iterator) {
		if (map == null) return map;
		map.forEach(iterator);
		return map;
	}

	/**
	 * 
	 * @param list
	 * @param iterator
	 * @return
	 */
	public static <T, R> Collection<R> map(final Collection<T> list, Function<? super T, ? extends R> iterator) {
		final List<R> results = new LinkedList<R>();
		if (list == null) return results;

		each(list, (value) -> results.add(iterator.apply(value)));
		return results;
	}
	
	public static <T, R> R[] map(final T[] array, final Function<? super T, ? extends R> iterator) {
		if (array == null || array.length == 0) {
			return (R[]) new Object[0];
		}
		final R[] results = (R[]) new Object[array.length];
		for (int i = 0; i < array.length; ++i) {
			results[i] = iterator.apply(array[i]);
		}
		return results;
	}

	public static <K, V, R> Collection<R> map(Map<K, V> map, final BiFunction<? super K, ? super V, R> iterator) {
		final List<R> results = new LinkedList<R>();
		if (map == null) return results;
		
		each(map, (k, v) -> results.add(iterator.apply(k, v)));
		return results;
	}

	/*
	 *
	 */


	public static <T> T reduce(final Collection<T> collection, final BinaryOperator<T> iterator, T memo) {
		if (collection == null) return memo;

		T result = memo;
		for (T value : collection) {
			result = iterator.apply(value, result);
		}

		return result;
	}
	
	public static <T, U> U reduce(final Collection<T> collection, final BiFunction<? super T, U, U> iterator, U memo) {
		if (collection == null) return memo;
		
		U result = memo;
		for (T value : collection) {
			result = iterator.apply(value, result);
		}
		
		return result;
	}
	
	public static <T> T reduce(final T[] array, final BinaryOperator<T> iterator, T memo) {
		if (array == null) return memo;
		
		T result = memo;
		for (T value : array) {
			result = iterator.apply(value, result);
		}
		
		return result;
	}
	
	public static <T, U> U reduce(final T[] array, final BiFunction<? super T, U, U> iterator, U memo) {
		if (array == null) return memo;
		
		U result = memo;
		for (T value : array) {
			result = iterator.apply(value, result);
		}
		
		return result;
	} 


	/*
	 *
	 */


//	public static <T> T reduceRight(final Collection<T> list, final BinaryOperator<T> iterator, T memo) {
//		return memo;
//	}
	
	public static <T> T reduceRight(final T[] array, final BinaryOperator<T> iterator, T memo) {
		if (array == null) return memo;
		
		for (int i = array.length; i >= 0; --i) {
			memo = iterator.apply(array[i], memo);
		}
		
		return memo;
	}

	/*
	 *
	 */


	public static <T> T find(final Collection<T> collection, final Predicate<T> predicate) {
		for (T value : collection) {
			if (predicate.test(value)) {
				return value;
			}
		}
		return null;
	}
	
	public static <T> T find(final T[] array, final Predicate<T> predicate) {
		for (T value : array) {
			if (predicate.test(value)) {
				return value;
			}
		}
		return null;
	}

	/*
	 *
	 */


	public static <T> Collection<T> filter(Collection<T> collection, final Predicate<T> predicate) {
		final List<T> results = new LinkedList<>();
		if (collection == null) return results;
		each(collection, (value) -> {
			if (predicate.test(value)) {
				results.add(value);
			}
		});
		return results;
	}
	
	public static <T> T[] filter(final T[] array, final Predicate<T> predicate) {
		if (array == null) return newArray(array, 0);
		final List<T> results = new LinkedList<>();
		each(array, (val) -> {
			if (predicate.test(val)) {
				results.add(val);
			}
		});
		return results.toArray(newArray(array, results.size()));
	}

	/*
	 * 
	 */


	public static <T> Collection<T> reject(final Collection<T> list, final Predicate<T> predicate) {
		return filter(list, (value) -> {
			return !predicate.test(value);
		});
	}
	
	public static <T> T[] reject(final T[] array, final Predicate<T> predicate) {
		return filter(array, (value) -> {
			return !predicate.test(value);
		});
	}

	/*
	 * 
	 */


	public static <T> boolean every(final Collection<T> list, final Predicate<T> predicate) {
		final Predicate<T> p = (predicate == null) ? predicate : (t) -> true;
		boolean result = true;
		if (list == null) {
			return result;
		}
		for (T value : list) {
			if (!(result = result && predicate.test(value))) break;
		}
		return result;
	}
	
	public static <T> boolean every(final T[] array, final Predicate<T> predicate) {
		final Predicate<T> p = (predicate == null) ? predicate : (t) -> true;
		boolean result = true;
		if (array == null) {
			return result;
		}
		for (T value : array) {
			if (!(result = result && predicate.test(value))) break;
		}
		return result;
	}

	/*
	 *
	 */


	public static <T> boolean any(final Collection<T> list, final Predicate<T> predicate) {
		final Predicate<T> p = (predicate == null) ? predicate : (t) -> t != null ? true : false;
		boolean result = false;
		if (list == null) return result;
		for (T value : list) {
			if (result || (result = predicate.test(value))) break;
		}
		return result;
	}
	
	public static <T> boolean any(final T[] array, final Predicate<T> predicate) {
		final Predicate<T> p = (predicate == null) ? predicate : t -> t != null ? true : false;
		boolean result = false;
		if (array == null) return result;
		for (T value : array) {
			if (result || (result = predicate.test(value))) break;
		}
		return result;
	}

	/*
	 *
	 */


	public static <T> boolean contains(final Collection<T> collection, final T target) {
		if (collection == null) {
			return false;
		}
		return any(collection, (value) -> {
			return target == null ? value == null : target.equals(value);
		});
	}

	public static <T> boolean contains(final T[] array, final T target) {
		if (array == null) {
			return false;
		}
		return any(array, (value) -> {
			return target == null ? value == null : target.equals(value);
		});
	}
	
	
	/*
	 * 
	 */
	
	
//	public static <T> Collection<T> pluck(final Collection<T> list, final Function<T, T> method) {
//		return map(list, null);
//	}
	
	/*
	 * 
	 */
	
	
//	public static <T> Collection<T> where(final Collection<T> list /*, attrs */) {
//		
//	}
	
	/*
	 * 
	 */
	
	
//	public static <T> Collection<T> findWhere(final Collection<T> list /*, attrs */) {
//		
//	}
	
	/*
	 * 
	 */
	
	public static <T extends Comparable<? super T>> T max(final Collection<T> collection) {
		return max(collection, _.identity());
	}
	
	public static <T extends Comparable<? super T>> T max(final Collection<T> collection, final UnaryOperator<T> iterator) {
		if (collection == null) return null;
		T result = null, lastComputed = null;
		for (T value : collection) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && computed.compareTo(lastComputed) > 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	public static <T> T max(final Collection<T> collection, final Comparator<? super T> comparator) {
		return max(collection, comparator, _.identity());
	}
	
	public static <T> T max(final Collection<T> collection, final Comparator<? super T> comparator, final UnaryOperator<T> iterator) {
		if (collection == null || comparator == null) return null;
		T result = null, lastComputed = null;
		for (T value : collection) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && comparator.compare(computed, lastComputed) > 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	
	public static <T extends Comparable<? super T>> T max(final T[] array) {
		return max(array, _.identity());
	}
	
	public static <T extends Comparable<? super T>> T max(final T[] array, final UnaryOperator<T> iterator) {
		if (array == null) return null;
		T result = null, lastComputed = null;
		for (T value : array) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && computed.compareTo(lastComputed) > 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	public static <T> T max(final T[] array, final Comparator<? super T> comparator) {
		return max(array, comparator, _.identity());
	}
	
	public static <T> T max(final T[] array, final Comparator<? super T> comparator, final UnaryOperator<T> iterator) {
		if (array == null) return null;
		T result = null, lastComputed = null;
		for (T value : array) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && comparator.compare(computed, lastComputed) > 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	/*
	 * 
	 */
	
	public static <T extends Comparable<? super T>> T min(final Collection<T> collection) {
		return min(collection, _.identity());
	}
	
	public static <T extends Comparable<? super T>> T min(final Collection<T> collection, final UnaryOperator<T> iterator) {
		if (collection == null) return null;
		T result = null, lastComputed = null;
		for (T value : collection) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && computed.compareTo(lastComputed) < 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	public static <T> T min(final Collection<T> collection, final Comparator<? super T> comparator) {
		return min(collection, comparator, _.identity());
	}
	
	public static <T> T min(final Collection<T> collection, final Comparator<? super T> comparator, final UnaryOperator<T> iterator) {
		if (collection == null || comparator == null) return null;
		T result = null, lastComputed = null;
		for (T value : collection) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && comparator.compare(computed, lastComputed) < 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	
	public static <T extends Comparable<? super T>> T min(final T[] array) {
		return min(array, _.identity());
	}
	
	public static <T extends Comparable<? super T>> T min(final T[] array, final UnaryOperator<T> iterator) {
		if (array == null) return null;
		T result = null, lastComputed = null;
		for (T value : array) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && computed.compareTo(lastComputed) < 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	public static <T> T min(final T[] array, final Comparator<? super T> comparator) {
		return min(array, comparator, _.identity());
	}
	
	public static <T> T min(final T[] array, final Comparator<? super T> comparator, final UnaryOperator<T> iterator) {
		if (array == null || comparator == null) return null;
		T result = null, lastComputed = null;
		for (T value : array) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed != null && comparator.compare(computed, lastComputed) < 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	/*
	 * 
	 */
	
	
	public static <T> void shuffle(final List<T> list) {
		Collections.shuffle(list);
	}
	
	public static <T> void shuffle(final T[] array) {
		int rand;
		int index = 0;
		final int length = array.length;
		final T[] shuffled = newArray(array, length);
		for (T value : array) {
			rand = random(index++);
			shuffled[index - 1] = shuffled[rand];
			shuffled[rand] = value;
		}
		
		for (int i = 0; i < length; ++i) {
			array[i] = (T) shuffled[i];
		}
	}
	
	public static <T extends Comparable<? super T>> void sort(final List<T> list) {
		Collections.sort(list);
	}
	
	public static <T> void sort(final List<T> list, final Comparator<? super T> comp) {
		Collections.sort(list, comp);
	}
	
	public static void sort(final Object[] array) {
		Arrays.sort(array);
	}
	
	public static <T> void sort(final T[] array, final Comparator<? super T> comp) {
		Arrays.sort(array, comp);
	}
	
	// sample
	// sortBy
	// groupBy
	// indexBy
	// countBy
	// sortedIndex
//	public static <T> int sortedIndex(final Collection<? extends Comparable<? super T>> list, final T target) {
//		if (list == null) return -1;
//		if (list instanceof List) {
//			return Collections.binarySearch((List) list, target);
//		}
//		
//		int low = 0, high = list.size() - 1;
//		while (low < high) {
//			int mid = (low + high) >>> 1;
//			Comparable<? super T> midValue;
//		}
//		return low;
//	}
	
	// toArray
	
	private static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
	
	public static <T> Object[] toArray(final Collection<T> list) {
		if (list == null) {
			return EMPTY_OBJECT_ARRAY;
		}
		return list.toArray();
	}
	
	public static <K, V> Object[] toArray(final Map<K, V> map) {
		if (map == null) {
			return EMPTY_OBJECT_ARRAY;
		}
		return values(map).toArray();
	}
	
	public static int size(final Iterable<?> iterable) {
		if (iterable == null) return 0;
		if (iterable instanceof Collection) {
			return ((Collection<?>) iterable).size();
		}
		
		int i = 0;
		for (Object value : iterable) {
			++i;
		}
		return i;
	}
	
	public static <T> int size(final T[] array) {
		if (array == null) return 0;
		return array.length;
	}
	
	public static int size(final Map<?, ?> map) {
		if (map == null) return 0;
		return map.size();
	}
	
	
	
	/*
	 * Arrays
	 */
	
	static <T> T[] newArray(T[] reference, final int length) {
		Class<?> clazz = reference.getClass().getComponentType();
		
		@SuppressWarnings("unchecked")
		T[] result = (T[]) Array.newInstance(clazz, length);
		return result;
	}
	
	public static Object[] concat(final Object[]... values) {
		final List<Object> results = new ArrayList<>();
		for (Object[] array : values) {
			results.addAll(Arrays.asList(array));
		}
		return results.toArray();
	}
	
	
	public static <T> T first(final T[] array) {
		if (array == null || array.length == 0) return null;
		return array[0];
	}
	
	// initial
	
	public static <T> T last(final T[] array) {
		if (array == null || array.length == 0) return null;
		return array[array.length - 1];
	}
	
	// rest/tail/drop
	
	public static <T> T[] compact(final T[] array) {
		return filter(array, (value) -> {
			return value != null && !isEmpty(value);
		});
	}
	
	// flatten
	
//	public static Collection<Object> flatten(final Collection<?> collection, final boolean shallow) {
//		return flatten(collection, shallow, new ArrayList<Object>());
//	}
//	
//	private static Collection<Object> flatten(final Collection<?> collection, final boolean shallow, final List<Object> output) {
//		if (shallow && _.every(collection, _::isCollection)) {
//			return _.concat(output, collection);
//		}
//		for (Object element : collection) {
//			if (_.isCollection(element)) {
//				if (shallow) {
//					output.addAll((Collection<?>) element);
//				} else {
//					flatten((Collection<?>) element, shallow, output);
//				}
//			} else {
//				output.add(element);
//			}
//		}
//		return output;
//	}
	
	public static <T> Collection<T> without(final Collection<T> collection, final T... values) {
		return _.difference(collection, values);
	}
	
	public static <T> T[] without(final T[] array, final T... values) {
		return _.difference(array, values);
	}
	
	
	public static <T> Map<Boolean, List<T>> partition(final T[] array, final Predicate<? super T> predicate) {
		final Map<Boolean, List<T>> results = new HashMap<>();
		results.put(true, new LinkedList<T>());
		results.put(false, new LinkedList<T>());
		each(array, (value) -> {
			results.get(predicate.test(value)).add(value);
		});
		return results;
	}
	
	public static <T> Map<Boolean, List<T>> partition(final Collection<T> collection, final Predicate<? super T> predicate) {
		final Map<Boolean, List<T>> results = new HashMap<>();
		results.put(true, new LinkedList<T>());
		results.put(false, new LinkedList<T>());
		each(collection, (value) -> {
			results.get(predicate.test(value)).add(value);
		});
		return results;
	}
	
	public static <T> Collection<T> unique(final Collection<T> collection) {
		return new HashSet<>(collection);
	}
	
	public static <T> T[] unique(final T[] array) {
		if (array == null) return array;
		final Set<T> set = new HashSet<T>(array.length);
		for (T value : array) {
			set.add(value);
		}
		
		return set.toArray(newArray(array, set.size()));
	}
	
	// union
	// intersection
	
	public static <T> Collection<T> difference(final Collection<T> collection, final T... rest) {
		return filter(collection, (value) -> !contains(rest, value));
	}
	
	public static <T> T[] difference(final T[] array, final T... rest) {
		return filter(array, (value) -> !contains(rest, value));
	}
	
	// zip
	// object
	

	public static <T> int indexOf(final T[] array, final T target) {
		if (array == null) {
			return -1;
		}
		for (int i = 0; i < array.length; ++i) {
			if (target == null ? array[i] == null : target.equals(array[i])) {
				return i;
			}
		}
		
		return -1;
	}
	
	public static <T> int lastIndexOf(final T[] array, final T target) {
		return lastIndexOf(array, target, array.length);
	}
	
	public static <T> int lastIndexOf(final T[] array, final T target, final int from) {
		if (array == null) {
			return -1;
		}
		
		final int length = array.length;
		int i = (from > length || from < 0) ? length : from;
		while (i-- > -1) {
			if (target == null ? array[i] == null : target.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}
	
	
	public static int[] range(final int stop) {
		return range(0, stop, 1);
	}
	
	public static int[] range(final int start, final int stop) {
		return range(start, stop, 1);
	}
	
	public static int[] range(final int start, final int stop, final int step) {
		final int length = (int) Math.max(Math.ceil((stop - start) / step), 0);
		int i = 0, s = start;
		int[] range = new int[length];
		while (i < length) {
			range[i++] = s;
			s += step;
		}
		return range;
	}
	
	
	/*
	 * Functions
	 */
	
	
//	public static <T, R> Function<T[], R> compose(final Function<T[], R>... functions) {
//		return (arguments) -> {
//
//		};
//	}
	
	
//	public static <T, R> Function<T, R> after(int times, final Function<T, R> function) {
//		
//	}
	
	
	/*
	 * Objects
	 */
	
	public static <K, V> Set<K> keys(final Map<K, V> map) {
		final Set<K> keys = new HashSet<K>(map.size());
		each(map.keySet(), (key) -> {
			keys.add(key);
		});
		return Collections.unmodifiableSet(keys);
	}
	
	public static <K, V> Collection<V> values(final Map<K, V> map) {
		final Collection<V> values = new LinkedList<V>();
		each(map.values(), (value) -> {
			values.add(value);
		});
		return Collections.unmodifiableCollection(values);
	}
	
	public static <K, V> Set<Map.Entry<K, V>> pairs(final Map<K, V> map) {
		final Set<K> keys = keys(map);
		final int length = keys.size();
		
		final Set<Map.Entry<K, V>> pairs = new HashSet<>(length);
		each(keys, (key) -> {
			final Map.Entry<K, V> entry = new AbstractMap.SimpleEntry<K, V>(key, map.get(key));
			pairs.add(entry);
		});
		return pairs;
	}
	
	
	
	public static <K, V> Map<V, K> invert(final Map<K, V> map) {
		final Map<V, K> result = new HashMap<>(map.size());
		each(map.keySet(), (key) -> {
			result.put(map.get(key), key);
		});
		return result;
	}
	
//	public static <K, V> Map<K, V> extend(final Map<K, V> map) {
//		
//	}
	
	public static <T> Collection<T> pick(final Collection<T> list, final T... args) {
		final List<T> copy = new ArrayList<>();
		each(args, (value) -> {
			if (contains(list, value)) {
				copy.add(value);
			}
		});
		return copy;
	}
	
	public static <K, V> Map<K, V> pick(final Map<K, V> map, final K... keys) {
		final Map<K, V> copy = new HashMap<K, V>();
		each(keys, (key) -> {
			if (map.containsKey(key)) {
				copy.put(key, map.get(key));
			}
		});
		return copy;
	}
	
	public static <T> Collection<T> omit(final Collection<T> list, final T... args) {
		final List<T> copy = new ArrayList<>();
		each(args, (value) -> {
			if (!contains(list, value)) {
				copy.add(value);
			}
		});
		return copy;
	}
	
	public static <K, V> Map<K, V> omit(final Map<K, V> map, final K ...keys) {
		final Map<K, V> copy = new HashMap<K, V>();
		each(keys, (key) -> {
			if (!map.containsKey(key)) {
				copy.put(key, map.get(key));
			}
		});
		return copy;
	}
	
	
	public static Object clone(final Object value) throws Exception {
		if (value instanceof Cloneable) {
			if (isArray(value)) {
				final Class<?> componentType = value.getClass().getComponentType();
				if (!componentType.isPrimitive()) {
					return ((Object[]) value).clone();
				} else {
					int length = Array.getLength(value);
					final Object result = Array.newInstance(componentType, length);
					while (length-- > 0) {
						Array.set(result, length, Array.get(value, length));
					}
					return result;
				}
			} else {
				try {
					Method method = value.getClass().getMethod("clone",
							new Class<?>[0]);
					method.invoke(value, new Object[0]);
				} catch (final Exception e) {
					throw new Exception("Cloneable type "
							+ value.getClass().getName()
							+ " has no clone method", e);
				}
			}
		}
		
		return null;
	}
	
	public static <T> T tap(final T value, final Consumer<T> interceptor) {
		interceptor.accept(value);
		return value;
	}
	
	public static boolean isEqual(final Object a, final Object b) {
		if (a == b) return true;
		return a == null ? b == null : a.equals(b);
	}
	
	public static boolean isEmpty(Object o) {
		if (o == null) return true;
		if (o instanceof Iterable) {
			return !((Iterable<?>) o).iterator().hasNext();
		} else if (isArray(o)) {
			if (o.getClass().isPrimitive()) {
				return Array.getLength(o) == 0;
			}
			return ((Object[]) o).length == 0;
		} else if (isCharSequence(o)) {
			return ((CharSequence) o).length() == 0;
		} else if (o instanceof Map) {
			return ((Map<?, ?>) o).isEmpty();
		}
		return true;
	}
	
	public static boolean isCollection(Object o) {
		return o != null && o instanceof Collection;
	}
	
	public static Predicate<Object> isCollection() {
		return (o) -> o != null & o instanceof Collection;
	}
	
	public static boolean isArray(Object o) {
		return o != null && o.getClass().isArray();
	}
	
	public static Predicate<Object> isArray() {
		return (o) -> o != null && o.getClass().isArray();
	}
	
	public static boolean isObject(Object o) {
		return o instanceof Object;
	}
	
	public static Predicate<Object> isObject() {
		return (o) -> o instanceof Object;
	}
	
	public static boolean isCharSequence(Object o) {
		return o instanceof CharSequence;
	}
	
	public static Predicate<Object> isCharSequence() {
		return (o) -> o instanceof CharSequence;
	}
	
	public static boolean isNumber(Object o) {
		return o instanceof Number;
	}
	
	public static Predicate<Object> isNumber() {
		return (o) -> o instanceof Number;
	}
	
	public static boolean isFinite(Object o) {
		return isNumber(o) && Double.isFinite((Double) o);
	}
	
	public static Predicate<Object> isFinite() {
		return (o) -> isNumber(o) && Double.isFinite((Double) o);
	}
	
	public static boolean isNaN(Object o) {
		return isNumber(o) && Double.isNaN((Double) o);
	}
	
	public static Predicate<Object> isNaN() {
		return (o) -> isNumber(o) && Double.isNaN((Double) o);
	}
	
	public static boolean isBoolean(Object o) {
		return o instanceof Boolean;
	}
	
	public static Predicate<Object> isBoolean() {
		return (o) -> o instanceof Boolean;
	}
	
	public static boolean isNull(Object o) {
		return o == null;
	}
	
	public static Predicate<Object> isNull() {
		return (o) -> o == null;
	}
	
	
	public static <K, V> boolean has(final Map<K, V> map, final Object key) {
		if (map == null) return false;
		return map.containsKey(key);
	}
	
	
	public static <T> UnaryOperator<T> identity() {
		return (v) -> v;
	}
	
	public static <T> Supplier<T> constant(final T value) {
		return () -> value;
	}
	
	
	public static <K, V> Predicate<Map<K, V>> matches(final Map<K, V> attrs) {
		return (obj) -> {
			if (obj == null ? attrs == null : obj.equals(attrs)) {
				return true;
			}
			for (K key : attrs.keySet()) {
				if (!attrs.get(key).equals(obj.get(key))) {
					return false;
				}
			}
			return true;
		};
	}
	
	
	public static <R> List<R> times(final int n, final Function<Integer, R> iterator) {
		List<R> accum = new ArrayList<R>(Math.max(0, n));
		for (int i = 0; i < n; ++i) {
			accum.set(i, iterator.apply(i));
		}
		return accum;
	}
	
	public static int random(final int min) {
		return random(min, Integer.MIN_VALUE);
	}
	
	public static int random(int min, int max) {
		if (max < min) {
			max = min;
			min = 0;
		}
		return (int) (min + Math.floor(Math.random() * (max - min + 1)));
	}
	
	
	public static Date now() {
		return Calendar.getInstance().getTime();
	}
	
	private static final Map<String, String> escapeEntityMap;
	static {
		final Map<String, String> map = new HashMap<String, String>();
		map.put("&", "&amp;");
		map.put("<", "&lt;");
		map.put(">", "&gt;");
		map.put("\"", "&quot;");
		map.put("'", "&#x27");
		escapeEntityMap = Collections.unmodifiableMap(map);
	}
	private static final String escapeEntityRegex = "[" + join(keys(escapeEntityMap), "") + "]";
	
	private static final String unescapeEntityRegex = "(" + join(values(escapeEntityMap), "|") + ")";
	
	public static String escape(String string) {
		if (string == null) {
			return EMPTY_STRING;
		}
		return string.replaceAll(escapeEntityRegex, unescapeEntityRegex);
	}
	
	public static String unescape(String string) {
		if (string == null) {
			return EMPTY_STRING;
		}
		return string.replaceAll(unescapeEntityRegex, escapeEntityRegex);
	}
	
	private static final AtomicLong idCount = new AtomicLong();
	
	public static final String uniqueId(String prefix) {
		synchronized (idCount) {
			final long id = idCount.incrementAndGet();
			return (prefix == null) ? Long.toString(id) : prefix + id;
		}
	}
	
	
	
	
	
	
	
	
	
	
	private static final String EMPTY_STRING = "";
	
	public static <T> String join(final Collection<T> collection, final String delimiter) {
		if (collection == null) {
			return EMPTY_STRING;
		}
		
		return join(collection.iterator(), delimiter);
	}
	
	public static <T> String join(final Iterator<T> iterator, final String delimiter) {
		if (iterator == null) {
			return EMPTY_STRING;
		}
		
		if (!iterator.hasNext()) {
			return EMPTY_STRING;
		}
		
		final StringBuilder builder = new StringBuilder();
		for(;;) {
			final T value = iterator.next();
			builder.append(value);
			if (!iterator.hasNext()) {
				return builder.toString();
			}
			builder.append(delimiter);
		}
	}
	
	public static String join(final Map<?, ?> map, final String delimiter) {
		if (map == null || map.size() == 0) {
			return EMPTY_STRING;
		}
		
		final Iterator<?> iterator = map.entrySet().iterator();
		if (!iterator.hasNext()) {
			return EMPTY_STRING;
		}
		
		final StringBuilder builder = new StringBuilder();
		for(;;) {
			final Entry<?, ?> e = (Entry<?, ?>) iterator.next();
			final Object key = e.getKey();
			final Object value = e.getValue();
			builder.append(key == map ? "(map)" : key);
			builder.append('=');
			builder.append(value == map ? "(map)" : value);
			if (!iterator.hasNext()) {
				return builder.toString();
			}
			builder.append(delimiter);
		}
		
	}
	
	public static String join(final Object[] array, final String delimiter) {
		if (array == null || array.length == 0) {
			return EMPTY_STRING;
		}
		
		final StringBuilder builder = new StringBuilder();
		
		final Object first = array[0];
		if (first != null) {
			builder.append(first);
		}

		for (int i = 1; i < array.length; ++i) {
			if (delimiter != null) {
				builder.append(delimiter);
			}
			final Object element = array[i];
			if (element != null) {
				builder.append(element);
			}
		}
		
		return builder.toString();
	}
	
	
	/*
	 * Chaining
	 */
	
	public static ArrayChain<Integer> chain(final int[] array) {
		return new IntChainImpl(array);
	}
	
	public static ArrayChain<Float> chain(final float[] array) {
		return new FloatChainImpl(array);
	}
	
	public static ArrayChain<Double> chain(final double[] array) {
		return new DoubleChainImpl(array);
	}
	
	public static ArrayChain<Long> chain(final long[] array) {
		return new LongChainImpl(array);
	}
	
	public static ArrayChain<Short> chain(final short[] array) {
		return new ShortChainImpl(array);
	}
	
	public static ArrayChain<Boolean> chain(final boolean[] array) {
		return new BooleanChainImpl(array);
	}
	
	public static ArrayChain<Byte> chain(final byte[] array) {
		return new ByteChainImpl(array);
	}
	
	public static ArrayChain<Character> chain(final char[] array) {
		return new CharChainImpl(array);
	}
	
	public static <K, V> MapChain<K, V> chain(final Map<K, V> map) {
		return new MapChainImpl<>(map);
	}
	
	public static <E extends Comparable<? super E>> CollectionChain<E> chain(final Collection<E> collection) {
		return new CollectionChainImpl<>(collection);
	}
}
