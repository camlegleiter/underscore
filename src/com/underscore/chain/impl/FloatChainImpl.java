package com.underscore.chain.impl;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class FloatChainImpl implements ArrayChain<Float> {

	private Float[] array;
	
	public FloatChainImpl(final float[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Float[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}
	
	public FloatChainImpl(final Float[] array) {
		this.array = array;
	}
	
	@Override
	public long count() {
		return array.length;
	}

	@Override
	public ArrayChain<Float> each(Consumer<Float> iterator) {
		_.each(array, iterator);
		return this;
	}

	@Override
	public ArrayChain<Float> filter(Predicate<Float> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Float find(Predicate<Float> predicate) {
		return _.find(array, predicate);
	}

	@Override
	public String join(String delimiter) {
		return _.join(array, delimiter);
	}

	@Override
	public ArrayChain<Float> map(Function<Float, Float> iterator) {
		array = _.map(array, iterator);
		return this;
	}

	@Override
	public Float max() {
		return _.max(array, _.identity());
	}

	@Override
	public Float min() {
		return _.min(array, _.identity());
	}

	@Override
	public Float reduce(BinaryOperator<Float> iterator, Float memo) {
		return _.reduce(array, iterator, memo);
	}

	@Override
	public ArrayChain<Float> shuffle() {
		_.shuffle(array);
		return this;
	}

	@Override
	public ArrayChain<Float> sort() {
		_.sort(array);
		return this;
	}

	@Override
	public Float sum() {
		return reduce((a, b) -> a + b, 0f);
	}

	@Override
	public ArrayChain<Float> tap(Consumer<Float[]> interceptor) {
		_.tap(array, interceptor);
		return this;
	}

	@Override
	public Float[] value() {
		return array;
	}

	@Override
	public ArrayChain<Float> unique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Float> without(Float... values) {
		// TODO Auto-generated method stub
		return null;
	}
}
