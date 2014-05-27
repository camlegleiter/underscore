package com.underscore.chain.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.underscore._;
import com.underscore.chain.CollectionChain;
import com.underscore.chain.MapChain;

public class MapChainImpl<K, V> implements MapChain<K, V> {

	private Map<K, V> map;
	
	public MapChainImpl(final Map<K, V> map) {
		this.map = map;
	}

	@Override
	public long count() {
		return map.size();
	}

	@Override
	public MapChain<K, V> each(BiConsumer<? super K, ? super V> iterator) {
		map = _.each(map, iterator);
		return this;
	}

	@Override
	public String join(String delimiter) {
		return _.join(map, delimiter);
	}

	@Override
	public CollectionChain<K> keys() {
		return new CollectionChainImpl<>(map.keySet());
	}

	@Override
	public <R> CollectionChain<R> map(BiFunction<? super K, ? super V, R> iterator) {
		return new CollectionChainImpl<>(_.map(map, iterator));
	}

	@Override
	public MapChain<K, V> omit(K... keys) {
		map = _.omit(map, keys);
		return this;
	}

	@Override
	public MapChain<K, V> pick(K... keys) {
		map = _.pick(map, keys);
		return this;
	}

	@Override
	public MapChain<K, V> tap(Consumer<Map<K, V>> interceptor) {
		_.tap(map, interceptor);
		return this;
	}
	
	@Override
	public <E> CollectionChain<E> toList(
			BiFunction<? super K, ? super V, ? extends E> mapEntryIterator) {
		final List<E> results = new ArrayList<>();
		_.each(map, (key, value) -> {
			final E result = mapEntryIterator.apply(key, value);
			results.add(result);
		});
		return new CollectionChainImpl<>(results);
	}
	
	@Override
	public Map<K, V> value() {
		return map;
	}

	@Override
	public CollectionChain<V> values() {
		return new CollectionChainImpl<>(map.values());
	}
}
