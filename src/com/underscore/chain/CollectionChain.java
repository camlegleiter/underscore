package com.underscore.chain;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface CollectionChain<E> {
	public long count();
	
	public CollectionChain<E> each(final Consumer<E> iterator);
	
	public CollectionChain<E> filter(final Predicate<E> predicate);
	
	public E find(final Predicate<E> predicate);
	
	public String join(final String delimiter);
	
	public <R> CollectionChain<R> map(final Function<E, R> iterator);
	
	public E max(Comparator<? super E> comp);
	
	public E min(Comparator<? super E> comp);
	
	public MapChain<Boolean, List<E>> partition(Predicate<? super E> predicate);
	
	public E reduce(final BinaryOperator<E> iterator, E memo);
	
	public <R> R reduce(final BiFunction<? super E, R, R> iterator, R memo);
	
	public CollectionChain<E> sort(Comparator<? super E> comp);
	
	public CollectionChain<E> shuffle();
	
	public CollectionChain<E> tap(final Consumer<Collection<E>> interceptor);
	
	public <K, V> MapChain<K, V> toMap(Function<? super E, ? extends K> keyMapIterator, Function<? super E, ? extends V> valueMapIterator);
	
	public CollectionChain<E> unique();
	
	public Collection<E> value();
	
	public CollectionChain<E> without(final E... values);
}
