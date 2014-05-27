package com.underscore.chain.impl;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class BooleanChainImpl implements ArrayChain<Boolean> {

	private Boolean[] array;
	
	public BooleanChainImpl(final boolean[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Boolean[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}
	
	public BooleanChainImpl(final Boolean[] array) {
		this.array = array;
	}
	
	@Override
	public long count() {
		return array.length;
	}

	@Override
	public ArrayChain<Boolean> each(Consumer<Boolean> iterator) {
		_.each(array, iterator);
		return this;
	}

	@Override
	public ArrayChain<Boolean> filter(Predicate<Boolean> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Boolean find(Predicate<Boolean> predicate) {
		return _.find(array, predicate);
	}

	@Override
	public String join(String delimiter) {
		return _.join(array, delimiter);
	}

	@Override
	public ArrayChain<Boolean> map(Function<Boolean, Boolean> iterator) {
		return new BooleanChainImpl(_.map(array, iterator));
	}

	@Override
	public Boolean max() {
		return _.max(array);
	}

	@Override
	public Boolean min() {
		return _.min(array);
	}

	@Override
	public Boolean reduce(BinaryOperator<Boolean> iterator, Boolean memo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Boolean> sort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Boolean> shuffle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sum() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Boolean> tap(Consumer<Boolean[]> interceptor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Boolean> unique() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean[] value() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayChain<Boolean> without(Boolean... values) {
		// TODO Auto-generated method stub
		return null;
	}

}
