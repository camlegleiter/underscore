package com.underscore.chain.impl;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class ShortChainImpl implements ArrayChain<Short> {

	private Short[] array;
	
	public ShortChainImpl(final short[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Short[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}
	
	public ShortChainImpl(final Short[] array) {
		this.array = array;
	}

	@Override
	public long count() {
		return array.length;
	}

	@Override
	public ArrayChain<Short> each(Consumer<Short> iterator) {
		_.each(array, iterator);
		return this;
	}

	@Override
	public ArrayChain<Short> filter(Predicate<Short> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Short find(Predicate<Short> predicate) {
		return _.find(array, predicate);
	}

	@Override
	public String join(String delimiter) {
		return _.join(array, delimiter);
	}

	@Override
	public ArrayChain<Short> map(Function<Short, Short> iterator) {
		array = _.map(array, iterator);
		return this;
	}

	@Override
	public Short max() {
		return _.max(array, _.identity());
	}

	@Override
	public Short min() {
		return _.min(array, _.identity());
	}

	@Override
	public Short reduce(BinaryOperator<Short> iterator, Short memo) {
		return _.reduce(array, iterator, memo);
	}

	@Override
	public ArrayChain<Short> shuffle() {
		_.shuffle(array);
		return this;
	}

	@Override
	public ArrayChain<Short> sort() {
		_.sort(array);
		return this;
	}

	@Override
	public Short sum() {
		return reduce((a, b) -> (short) (a + b), (short) 0);
	}

	@Override
	public ArrayChain<Short> tap(Consumer<Short[]> interceptor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Short[] value() {
		return array;
	}

	@Override
	public ArrayChain<Short> unique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Short> without(Short... values) {
		// TODO Auto-generated method stub
		return null;
	}
}
