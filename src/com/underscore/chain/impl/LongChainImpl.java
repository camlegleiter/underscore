package com.underscore.chain.impl;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class LongChainImpl implements ArrayChain<Long> {

	private Long[] array;
	
	public LongChainImpl(final long[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Long[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}
	
	public LongChainImpl(final Long[] array) {
		this.array = array;
	}
	
	@Override
	public long count() {
		return array.length;
	}

	@Override
	public ArrayChain<Long> each(Consumer<Long> iterator) {
		_.each(array, iterator);
		return this;
	}

	@Override
	public ArrayChain<Long> filter(Predicate<Long> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Long find(Predicate<Long> predicate) {
		return _.find(array, predicate);
	}

	@Override
	public String join(String delimiter) {
		return _.join(array, delimiter);
	}

	@Override
	public ArrayChain<Long> map(Function<Long, Long> iterator) {
		array = _.map(array, iterator);
		return this;
	}

	@Override
	public Long max() {
		return _.max(array, _.identity());
	}

	@Override
	public Long min() {
		return _.min(array, _.identity());
	}

	@Override
	public Long reduce(BinaryOperator<Long> iterator, Long memo) {
		return _.reduce(array, iterator, memo);
	}

	@Override
	public ArrayChain<Long> shuffle() {
		_.shuffle(array);
		return this;
	}

	@Override
	public ArrayChain<Long> sort() {
		_.sort(array);
		return this;
	}

	@Override
	public Long sum() {
		return reduce((a, b) -> a + b, 0l);
	}

	@Override
	public ArrayChain<Long> tap(Consumer<Long[]> interceptor) {
		_.tap(array, interceptor);
		return this;
	}

	@Override
	public Long[] value() {
		return array;
	}

	@Override
	public ArrayChain<Long> unique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Long> without(Long... values) {
		// TODO Auto-generated method stub
		return null;
	}
}
