package com.underscore.chain.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.CollectionChain;
import com.underscore.chain.MapChain;

public class CollectionChainImpl<E> implements CollectionChain<E> {

	private Collection<E> collection;
	
	public CollectionChainImpl(final Collection<E> collection) {
		this.collection = collection;
	}

	@Override
	public long count() {
		return collection.size();
	}
	
	@Override
	public CollectionChain<E> each(Consumer<E> iterator) {
		collection = _.each(collection, iterator);
		return this;
	}

	@Override
	public CollectionChain<E> filter(Predicate<E> predicate) {
		collection = _.filter(collection, predicate);
		return this;
	}

	@Override
	public E find(Predicate<E> predicate) {
		return _.find(collection, predicate);
	}

	@Override
	public String join(String delimiter) {
		return _.join(collection, delimiter);
	}

	@Override
	public <R> CollectionChain<R> map(Function<E, R> iterator) {
		return new CollectionChainImpl<R>(_.map(collection, (element) -> iterator.apply(element)));
	}

	@Override
	public E max(Comparator<? super E> comp) {
		return _.max(collection, comp, _.identity());
	}

	@Override
	public E min(Comparator<? super E> comp) {
		return _.min(collection, comp, _.identity());
	}
	
	@Override
	public MapChain<Boolean, List<E>> partition(Predicate<? super E> predicate) {
		return new MapChainImpl<>(_.partition(collection, predicate));
	}

	@Override
	public <R> R reduce(BiFunction<? super E, R, R> iterator, R memo) {
		return _.reduce(collection, iterator, memo);
	}
	
	@Override
	public E reduce(BinaryOperator<E> iterator, E memo) {
		return _.reduce(collection, iterator, memo);
	}

	@Override
	public CollectionChain<E> shuffle() {
		_.shuffle(new ArrayList<>(collection));
		return this;
	}

	@Override
	public CollectionChain<E> sort(Comparator<? super E> comp) {
		_.sort(new ArrayList<>(collection), comp);
		return this;
	}

	@Override
	public CollectionChain<E> tap(Consumer<Collection<E>> interceptor) {
		_.tap(collection, interceptor);
		return this;
	}

	@Override
	public <K, V> MapChain<K, V> toMap(
			Function<? super E, ? extends K> keyMapIterator,
			Function<? super E, ? extends V> valueMapIterator) {
		final Map<K, V> results = new HashMap<K, V>();
		_.each(collection, (element) -> {
			K key = keyMapIterator.apply(element);
			V value = valueMapIterator.apply(element);
			results.put(key, value);
		});
		return new MapChainImpl<>(results);
	}

	@Override
	public CollectionChain<E> unique() {
		collection = _.unique(collection);
		return this;
	}
	
	@Override
	public Collection<E> value() {
		return collection;
	}

	@Override
	public CollectionChain<E> without(E... values) {
		collection = _.without(collection, values);
		return this;
	}
}
