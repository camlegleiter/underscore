package com.underscore.chain.impl;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class DoubleChainImpl implements ArrayChain<Double> {

	private Double[] array;
	
	public DoubleChainImpl(final double[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Double[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}

	public DoubleChainImpl(final Double[] array) {
		this.array = array;
	}

	@Override
	public long count() {
		return array.length;
	}
	
	@Override
	public ArrayChain<Double> each(Consumer<Double> iterator) {
		_.each(array, iterator);
		return this;
	}

	@Override
	public ArrayChain<Double> filter(Predicate<Double> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Double find(Predicate<Double> predicate) {
		return _.find(array, predicate);
	}

	@Override
	public String join(String delimiter) {
		return _.join(array, delimiter);
	}

	@Override
	public ArrayChain<Double> map(Function<Double, Double> iterator) {
		array = _.map(array, iterator);
		return this;
	}

	@Override
	public Double max() {
		return _.max(array, _.identity());
	}

	@Override
	public Double min() {
		return _.min(array, _.identity());
	}

	@Override
	public Double reduce(BinaryOperator<Double> iterator, Double memo) {
		return _.reduce(array, iterator, memo);
	}

	@Override
	public ArrayChain<Double> shuffle() {
		_.shuffle(array);
		return this;
	}

	@Override
	public ArrayChain<Double> sort() {
		_.sort(array);
		return this;
	}

	@Override
	public Double sum() {
		return reduce((a, b) -> a + b, 0d);
	}

	@Override
	public ArrayChain<Double> tap(Consumer<Double[]> interceptor) {
		_.tap(array, interceptor);
		return this;
	}


	@Override
	public ArrayChain<Double> unique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double[] value() {
		return array;
	}

	@Override
	public ArrayChain<Double> without(Double... values) {
		// TODO Auto-generated method stub
		return null;
	}
}
