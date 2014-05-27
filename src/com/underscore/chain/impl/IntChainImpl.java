package com.underscore.chain.impl;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class IntChainImpl implements ArrayChain<Integer> {

	private Integer[] array;
	
	public IntChainImpl(final int[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Integer[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}
	
	public IntChainImpl(final Integer[] array) {
		this.array = array;
	}

	@Override
	public long count() {
		return array.length;
	}

	@Override
	public ArrayChain<Integer> each(Consumer<Integer> iterator) {
		_.each(array, iterator);
		return this;
	}

	@Override
	public ArrayChain<Integer> filter(Predicate<Integer> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Integer find(Predicate<Integer> predicate) {
		return _.find(array, predicate);
	}

	@Override
	public String join(String delimiter) {
		return _.join(array, delimiter);
	}

	@Override
	public ArrayChain<Integer> map(Function<Integer, Integer> iterator) {
		array = _.map(array, iterator);
		return this;
	}

	@Override
	public Integer max() {
		return _.max(array, _.identity());
	}

	@Override
	public Integer min() {
		return _.min(array, _.identity());
	}

	@Override
	public Integer reduce(BinaryOperator<Integer> iterator, Integer memo) {
		return _.reduce(array, iterator, memo);
	}

	@Override
	public ArrayChain<Integer> shuffle() {
		_.shuffle(array);
		return this;
	}

	@Override
	public ArrayChain<Integer> sort() {
		_.sort(array);
		return this;
	}

	@Override
	public Integer sum() {
		return reduce((a, b) -> a + b, 0);
	}

	@Override
	public ArrayChain<Integer> tap(Consumer<Integer[]> interceptor) {
		_.tap(array, interceptor);
		return this;
	}

	@Override
	public Integer[] value() {
		return array;
	}

	@Override
	public ArrayChain<Integer> unique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Integer> without(Integer... values) {
		// TODO Auto-generated method stub
		return null;
	}
}
