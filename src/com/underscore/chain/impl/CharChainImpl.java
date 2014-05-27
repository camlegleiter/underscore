package com.underscore.chain.impl;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.underscore._;
import com.underscore.chain.ArrayChain;

public class CharChainImpl implements ArrayChain<Character> {

	private Character[] array;
	
	public CharChainImpl(final char[] array) {
		Objects.requireNonNull(array);
		
		final int length = array.length;
		this.array = new Character[length];
		
		for (int i = 0; i < length; ++i) {
			this.array[i] = array[i];
		}
	}
	
	public CharChainImpl(final Character[] array) {
		this.array = array;
	}
	
	@Override
	public long count() {
		return array.length;
	}
	
	@Override
	public ArrayChain<Character> each(Consumer<Character> iterator) {
		_.each(array, iterator);
		return this;
	}
	
	@Override
	public ArrayChain<Character> filter(Predicate<Character> predicate) {
		array = _.filter(array, predicate);
		return this;
	}

	@Override
	public Character find(Predicate<Character> predicate) {
		return _.find(array, predicate);
	}
	
	@Override
	public String join(String delimiter) {
		return _.join(array, delimiter);
	}
	
	@Override
	public ArrayChain<Character> map(Function<Character, Character> iterator) {
		array = _.map(array, iterator);
		return this;
	}
	
	@Override
	public Character max() {
		return _.max(array);
	}

	@Override
	public Character min() {
		return _.max(array);
	}

	@Override
	public Character reduce(BinaryOperator<Character> iterator, Character memo) {
		return _.reduce(array, iterator, memo);
	}

	@Override
	public ArrayChain<Character> shuffle() {
		_.shuffle(array);
		return this;
	}

	@Override
	public ArrayChain<Character> sort() {
		_.sort(array);
		return this;
	}
	
	@Override
	public Character sum() {
		return reduce((a, b) -> (char) (a + b), (char) 0);
	}
	
	@Override
	public ArrayChain<Character> tap(Consumer<Character[]> interceptor) {
		_.tap(array, interceptor);
		return this;
	}
	
	@Override
	public ArrayChain<Character> unique() {
		array = _.unique(array);
		return this;
	}

	@Override
	public Character[] value() {
		return array;
	}

	@Override
	public ArrayChain<Character> without(Character... values) {
		array = _.without(array, values);
		return this;
	}
}
