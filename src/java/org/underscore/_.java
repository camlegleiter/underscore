package java.org.underscore;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public final class _ {
	
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
	 * Iterates over a list of elements, yielding each in turn to an iterator
	 * function. Each invocation of the iterator is called with the value in the
	 * list as the single argument. Delegates to the
	 * {@link List#forEach(Consumer)} method, and returns the original list for
	 * chaining.
	 * </p>
	 * 
	 * <p>
	 * <em>Note: a foEach loop cannot be broken out of--use
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
	public static <T> Collection<T> forEach(final Collection<T> list, final Consumer<T> iterator) {
		return each(list, iterator);
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
	 * Iterates over an array of elements, yielding each in turn to an iterator
	 * function. Each invocation of the iterator is called with the value in the
	 * list as the single argument. Delegates to the
	 * {@link List#forEach(Consumer)} method, and returns the original list for
	 * chaining.
	 * </p>
	 * 
	 * <p>
	 * <em>Note: a forEach loop cannot be broken out of--use 
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
	public static <T> T[] forEach(final T[] array, final Consumer<T> iterator) {
		return each(array, iterator);
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
	public static <K, V> Map<K, V> each(Map<K, V> map, BiConsumer<K, V> iterator) {
		if (map == null) return map;
		map.forEach(iterator);
		return map;
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
	 * <em>Note: a forEach loop cannot be broken out of.</em>
	 * </p>
	 * 
	 * @param map
	 *            the map to iterate over
	 * @param iterator
	 *            a {@link java.util.function.BiConsumer BiConsumer} object to use
	 *            for iterating
	 * @return the original map, or {@code null} if map is null
	 */
	public static <K, V> Map<K, V> forEach(Map<K, V> map, BiConsumer<K, V> iterator) {
		return forEach(map, iterator);
	}
	
	/*
	 *
	 */


	public static <T, R> Collection<R> map(final Collection<T> list, Function<T, R> iterator) {
		final List<R> results = new LinkedList<R>();
		if (list == null) return results;

		each(list, (value) -> results.add(iterator.apply(value)));
		return results;
	}

	public static <T, R> Collection<R> collect(final Collection<T> list, final Function<T, R> iterator) {
		return map(list, iterator);
	}
	
	public static <T, R> R[] map(final T[] array, final Function<T, R> iterator) {
		if (array == null || array.length == 0) {
			return (R[]) new Object[0];
		}
		final R[] results = (R[]) new Object[array.length];
		for (int i = 0; i < array.length; ++i) {
			results[i] = iterator.apply(array[i]);
		}
		return results;
	}
	
	public static <T, R> R[] collect(final T[] array, final Function<T, R> iterator) {
		return map(array, iterator);
	}

	public static <K, V, R> Collection<R> map(Map<K, V> map, final BiFunction<K, V, R> iterator) {
		final List<R> results = new LinkedList<R>();
		if (map == null) return results;
		
		each(map, (k, v) -> results.add(iterator.apply(k, v)));
		return results;
	}

	public static <K, V, R> Collection<R> collect(Map<K, V> map, final BiFunction<K, V, R> iterator) {
		return map(map, iterator);
	}

	/*
	 *
	 */


	public static <T> T reduce(final Collection<T> collection, final BinaryOperator<T> iterator, T memo) {
		if (collection == null) return memo;

		each(collection, (value) -> {
			memo = iterator.apply(value, memo);
		});

		return memo;
	}

	public static <T> T foldl(final Collection<T> collection, final BinaryOperator<T> iterator, T memo) {
		return reduce(collection, iterator, memo);
	}

	public static <T> T inject(final Collection<T> collection, final BinaryOperator<T> iterator, T memo) {
		return reduce(collection, iterator, memo);
	}
	
	public static <T> T reduce(final T[] array, final BinaryOperator<T> iterator, T memo) {
		if (array == null) return memo;
		
		each(array, (value) -> {
			memo = iterator.apply(value, memo);
		});
		
		return memo;
	}
	
	public static <T> T foldl(final T[] array, final BinaryOperator<T> iterator, T memo) {
		return reduce(array, iterator, memo);
	}
	
	public static <T> T inject(final T[] array, final BinaryOperator<T> iterator, T memo) {
		return reduce(array, iterator, memo);
	}

	/*
	 *
	 */


//	public static <T> T reduceRight(final Collection<T> list, final BinaryOperator<T> iterator, T memo) {
//		return memo;
//	}
//
//	public static <T> T foldr(final Collection<T> list, final BinaryOperator<T> iterator, T memo) {
//		return reduceRight(list, iterator, memo);
//	}
	
	public static <T> T reduceRight(final T[] array, final BinaryOperator<T> iterator, T memo) {
		if (array == null) return memo;
		
		for (int i = array.length; i >= 0; --i) {
			memo = iterator.apply(array[i], memo);
		}
		
		return memo;
	}
	
	public static <T> T foldr(final T[] array, final BinaryOperator<T> iterator, T memo) {
		return reduceRight(array, iterator, memo);
	}

	/*
	 *
	 */


	public static <T> T find(final Collection<T> list, final Predicate<T> predicate) {
		final T result;
		any(list, (value) -> {
			if (predicate.test(value)) {
				result = value;
				return true;
			}
			return false;
		});
		return result;
	}

	public static <T> T detect(final Collection<T> list, final Predicate<T> predicate) {
		return find(list, predicate);
	}
	
	public static <T> T find(final T[] array, final Predicate<T> predicate) {
		final T result;
		any(array, (value) -> {
			if (predicate.test(value)) {
				result = value;
				return true;
			}
			return false;
		});
		return result;
	}
	
	public static <T> T detect(final T[] array, final Predicate<T> predicate) {
		return find(array, predicate);
	}

	/*
	 *
	 */


	public static <T> Collection<T> filter(Collection<T> list, final Predicate<T> predicate) {
		final List<T> results = new LinkedList<>();
		if (list == null) return results;
		each(list, (value) -> {
			if (predicate.test(value)) {
				results.add(value);
			}
		});
		return results;
	}
	
	public static <T> Collection<T> select(Collection<T> list, final Predicate<T> predicate) {
		return filter(list, predicate);
	}
	
	public static <T> T[] filter(final T[] array, final Predicate<T> predicate) {
		if (array == null) return (T[]) EMPTY_OBJECT_ARRAY;
		final T[] results = (T[]) new Object[array.length];
		for (int i = 0, j = 0; i < array.length && j < array.length; ++i) {
			if (predicate.test(array[i])) {
				results[j++] = array[i];
			}
		}
		return results;
	}

	public static <T> T[] select(final T[] array, final Predicate<T> predicate) {
		return filter(array, predicate);
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

	public static <T> boolean all(final Collection<T> list, final Predicate<T> predicate) {
		return every(list, predicate);
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

	public static <T> boolean some(final Collection<T> list, final Predicate<T> predicate) {
		return any(list, predicate);
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
	
	public static <T> boolean some(final T[] array, final Predicate<T> predicate) {
		return any(array, predicate);
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

	public static <T> boolean include(Collection<T> list, T target) {
		return contains(list, target);
	}
	
	public static <T> boolean contains(final T[] array, final T target) {
		if (array == null) return false;
		return any(array, (value) -> {
			return target == null ? value == null : target.equals(value);
		});
	}
	
	public static <T> boolean include(final T[] array, final T target) {
		return contains(array, target);
	}
	
	
	/*
	 * 
	 */
	
	
//	public static <T, R> Collection<R> invoke(final Collection<T> list, final Function<T, R> method) {
//		return _.map(list, (value) -> {
//			return (method == null) ? method : method.apply(value);
//		});
//	}
//	
//	public static <T, R> R[] invoke(final T[] array, final Function<T, R> method) {
//		return _.map(array, (value) -> {
//			return (method == null) ? method : method.apply(value);
//		});
//	}
	
	/*
	 * 
	 */
	
	
	public static <T> Collection<T> pluck(final Collection<T> list, final Function<T, T> method) {
		return map(list, null);
	}
	
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
	
	
	public static <T extends Comparable<? super T>> T max(final Collection<T> list, final Function<T, T> iterator) {
		if (list == null) return null;
		T result = null, lastComputed = null;
		for (T value : list) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed.compareTo(lastComputed) > 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	/*
	 * 
	 */
	
	
	public static <T extends Comparable<? super T>> T min(final Collection<T> list, final Function<T, T> iterator) {
		if (list == null) return null;
		T result = null, lastComputed = null;
		for (T value : list) {
			T computed = (iterator == null) ? value : iterator.apply(value);
			if (computed.compareTo(lastComputed) < 0) {
				result = value;
				lastComputed = computed;
			}
		}
		return result;
	}
	
	/*
	 * 
	 */
	
	
	public static <T> Collection<T> shuffle(final Collection<T> list) {
		int rand;
		int index = 0;
		final List<T> shuffled = new ArrayList<T>(list.size());
		for (T value : list) {
			rand = random(index++);
			shuffled.set(index - 1, shuffled.get(rand));
			shuffled.set(rand, value);
		}
		return shuffled;
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
	
	public static int size(final Iterable<?> list) {
		if (list == null) return 0;
		if (list instanceof Collection) {
			return ((Collection<?>) list).size();
		}
		
		int i = 0;
		for (Object value : list) {
			++i;
		}
		return i;
	}
	
	
	
	
	
	/*
	 * Arrays
	 */
	
	
	public static <T> T first(final T[] array) {
		if (array == null || array.length == 0) return null;
		return array[0];
	}
	
	public static <T> T head(final T[] array) {
		return first(array);
	}
	
	public static <T> T take(final T[] array) {
		return first(array);
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
	
	public static <T> Collection<T> without(final Collection<T> collection, final Collection<T> values) {
		collection.removeAll(values);
		
		return null;
	}
	
	
	public static <T> Map<Boolean, List<T>> partition(final T[] array, final Predicate<T> predicate) {
		Map<Boolean, List<T>> results = new HashMap<>();
		results.put(true, new LinkedList<T>());
		results.put(false, new LinkedList<T>());
		each(array, (value) -> {
			results.get(predicate.test(value)).add(value);
		});
		return results;
	}
	
	public static <T> Map<Boolean, List<T>> partition(final Collection<T> collection, final Predicate<T> predicate) {
		Map<Boolean, List<T>> results = new HashMap<>();
		results.put(true, new LinkedList<T>());
		results.put(false, new LinkedList<T>());
		each(collection, (value) -> {
			results.get(predicate.test(value)).add(value);
		});
		return results;
	}
	
	public static <T> T[] uniq(final T[] array) {
		if (array == null) return array;
		final Set<T> set = new HashSet<T>(array.length);
		for (T value : array) {
			set.add(value);
		}
		return (T[]) set.toArray();
	}
	
	public static <T> T[] unique(final T[] array) {
		return uniq(array);
	}
	
	public static <T> Collection<T> uniq(final Collection<T> collection) {
		return new HashSet<>(collection);
	}
	
	public static <T> Collection<T> unique(final Collection<T> collection) {
		return uniq(collection);
	}
	
	
	// union
	// intersection
	
	public static <T> Collection<T> difference(final Collection<T> collection, final Collection<T> rest) {
		return filter(collection, value -> {
			return !contains(rest, value);
		});
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
	
	public static <T> Collection<T> pick(final Collection<T> list, final Collection<T> args) {
		final List<T> copy = new ArrayList<>();
		each(args, (value) -> {
			if (contains(list, value)) {
				copy.add(value);
			}
		});
		return copy;
	}
	
	public static <T> Collection<T> omit(final Collection<T> list, final Collection<T> args) {
		final List<T> copy = new ArrayList<>();
		each(args, (value) -> {
			if (!contains(list, value)) {
				copy.add(value);
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
				}
			} else {
				try {
					Method method = value.getClass().getMethod("clone",
							new Class<?>[0]);
					method.invoke(value, new Object[0]);
				} catch (final NoSuchMethodException e) {
					throw new Exception("Cloneable type "
							+ value.getClass().getName()
							+ " has no clone method", e);
				} catch (final IllegalAccessException e) {
					throw new Exception("Cloneable type "
							+ value.getClass().getName()
							+ " has no clone method", e);
				} catch (final IllegalArgumentException e) {
					throw new Exception("Cloneable type "
							+ value.getClass().getName()
							+ " has no clone method", e);
				} catch (final InvocationTargetException e) {
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
		if (isArray(o)) {
			Iterable<?> it = (Iterable<?>) o;
			return !it.iterator().hasNext();
		} else if (isString(o)) {
			CharSequence s = (CharSequence) o;
			return s.length() == 0;
		} else if (o instanceof Map) {
			Map<?, ?> map = (Map<?, ?>) o;
			return map.isEmpty();
		}
		return true;
	}
	
//	public static boolean isElement(Object o) {
//		
//	}
	
	public static boolean isArray(Object o) {
		return o != null && o.getClass().isArray();
	}
	
	public static boolean isObject(Object o) {
		return o instanceof Object;
	}
	
	public static boolean isString(Object o) {
		return o instanceof CharSequence;
	}
	
	public static boolean isNumber(Object o) {
		return o instanceof Number;
	}
	
	public static boolean isDate(Object o) {
		return o instanceof Date;
	}
	
	public static boolean isRegExp(Object o) {
		if (!(o instanceof CharSequence)) {
			return false;
		}
		
		try {
			Pattern.compile(((CharSequence) o).toString());
		} catch (PatternSyntaxException e) {
			return false;
		}
		return true;
	}
	
	public static boolean isFinite(Object o) {
		return isNumber(o) && Double.isFinite((Double) o);
	}
	
	public static boolean isNaN(Object o) {
		return isNumber(o) && Double.isNaN((Double) o);
	}
	
	public static boolean isBoolean(Object o) {
		return o instanceof Boolean;
	}
	
	public static boolean isNull(Object o) {
		return o == null;
	}
	
	public static boolean isUndefined(Object o) {
		return o == null;
	}
	
	
	public static <K, V> boolean has(final Map<K, V> map, final K key) {
		if (map == null) return false;
		return map.containsKey(key);
	}
	
	
	public static <T> UnaryOperator<T> identity(final T value) {
		return (v) -> v;
	}
	
	public static <T> Supplier<T> constant(final T value) {
		return () -> value;
	}
	
	
	public static <K, V> Predicate<Map<K, V>> matches(Map<K, V> attrs) {
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
	
	public static int random(Integer min) {
		return random(min, null);
	}
	
	public static int random(Integer min, Integer max) {
		if (max == null) {
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
	
	private static final AtomicInteger idCount = new AtomicInteger();
	
	private static final Object mutex = new Object();
	
	public static final String uniqueId(String prefix) {
		synchronized (mutex) {
			final int id = idCount.incrementAndGet();
			return (prefix == null) ? Integer.toString(id) : prefix + id;
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
		
		final T first = iterator.next();
		if (!iterator.hasNext()) {
			return first == null ? EMPTY_STRING : first.toString();
		}
		
		final String del = (delimiter == null) ? EMPTY_STRING : delimiter;
		
		StringBuilder builder = new StringBuilder().append(first == null ? EMPTY_STRING : first);
		while (iterator.hasNext()) {
			if (delimiter != null) {
				builder.append(delimiter);
			}
			final T element = iterator.next();
			if (element != null) {
				builder.append(element.toString());
			}
		}
		
		return builder.toString();
	}
	
	public static <T> String join(final T[] array, final String delimiter) {
		if (array == null || array.length == 0) {
			return EMPTY_STRING;
		}
		
		final String del = (delimiter == null) ? "" : delimiter;
		
		final StringBuilder builder = new StringBuilder().append(array[0]);
		for (int i = 1; i < array.length; ++i) {
			builder.append(del).append(array[i]);
		}
		
		return builder.toString();
	}
}
