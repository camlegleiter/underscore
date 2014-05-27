package com.underscore.chain;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ArrayChain<E> {
	public long count();
	
	public ArrayChain<E> each(final Consumer<E> iterator);
	
	public ArrayChain<E> filter(final Predicate<E> predicate);
	
	public E find(final Predicate<E> predicate);
	
	public String join(final String delimiter);
	
	public ArrayChain<E> map(final Function<E, E> iterator);
	
	public E max();
	
	public E min();
	
	public E reduce(final BinaryOperator<E> iterator, E memo);
	
	public ArrayChain<E> sort();
	
	public ArrayChain<E> shuffle();
	
	public E sum();
	
	public ArrayChain<E> tap(final Consumer<E[]> interceptor);
	
	public ArrayChain<E> unique();
	
	public E[] value();
	
	public ArrayChain<E> without(final E... values);
}
