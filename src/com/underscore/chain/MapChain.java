package com.underscore.chain;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public interface MapChain<K, V> {
	public long count();
	
	public MapChain<K, V> each(final BiConsumer<? super K, ? super V> iterator);
	
	public String join(final String delimiter);
	
	public CollectionChain<K> keys();
	
	public <R> CollectionChain<R> map(final BiFunction<? super K, ? super V, R> iterator);
	
	public MapChain<K, V> omit(final K... keys);
	
	public MapChain<K, V> pick(final K... keys);
	
	public MapChain<K, V> tap(final Consumer<Map<K, V>> interceptor);
	
	public <E> CollectionChain<E> toList(final BiFunction<? super K, ? super V, ? extends E> mapEntryIterator);
	
	public Map<K, V> value();
	
	public CollectionChain<V> values();
}
