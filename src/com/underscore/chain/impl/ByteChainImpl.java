package com.underscore.chain.impl;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class ByteChainImpl implements ArrayChain<Byte> {

	private Byte[] array;
	
	public ByteChainImpl(final byte[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Byte[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}
	
	public ByteChainImpl(final Byte[] array) {
		this.array = array;
	}
	
	@Override
	public long count() {
		return array.length;
	}
	
	@Override
	public ArrayChain<Byte> each(final Consumer<Byte> iterator) {
		_.each(array, iterator);
		return this;
	}
	
	@Override
	public ArrayChain<Byte> filter(final Predicate<Byte> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Byte find(final Predicate<Byte> predicate) {
		return _.find(array, predicate);
	}

	@Override
	public String join(final String delimiter) {
		return _.join(array, delimiter);
	}

	@Override
	public ArrayChain<Byte> map(final Function<Byte, Byte> iterator) {
		array = _.map(array, iterator);
		return this;
	}
	
	@Override
	public Byte max() {
		return _.max(array);
	}

	@Override
	public Byte min() {
		return _.min(array);
	}

	@Override
	public Byte reduce(final BinaryOperator<Byte> iterator, Byte memo) {
		return _.reduce(array, iterator, memo);
	}
	
	@Override
	public ArrayChain<Byte> shuffle() {
		_.shuffle(array);
		return this;
	}
	
	@Override
	public ArrayChain<Byte> sort() {
		_.sort(array);
		return this;
	}

	@Override
	public Byte sum() {
		return reduce((a, b) -> (byte) (a + b), (byte) 0);
	}

	@Override
	public ArrayChain<Byte> tap(Consumer<Byte[]> interceptor) {
		_.tap(array, interceptor);
		return this;
	}

	@Override
	public String toString() {
		return Arrays.toString(array);
	}

	@Override
	public ArrayChain<Byte> unique() {
		array = _.unique(array);
		return this;
	}
	
	@Override
	public Byte[] value() {
		return array;
	}

	@Override
	public ArrayChain<Byte> without(Byte... values) {
		array = _.without(array, values);
		return this;
	}
}
