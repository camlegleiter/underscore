package com.underscore.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import com.underscore._;

public class Underscore {

	public static void main(String[] args) {
		class Lyric implements Comparable<Lyric> {
			public int line;
			public String words;
			public Lyric(int line, String words) {
				this.line = line; this.words = words;
			}
			@Override
			public int compareTo(Lyric o) {
				return line - o.line;
			}
		}
		
		List<Lyric> lyrics = new ArrayList<Lyric>();
		lyrics.add(new Lyric(1, "I'm a lumberjack and I'm okay"));
		lyrics.add(new Lyric(2, "I sleep all night and I work all day"));
		lyrics.add(new Lyric(3, "He's a lumberjack and he's okay"));
		lyrics.add(new Lyric(4, "He sleeps all night and he works all day"));
		
		String val = _.chain(lyrics).map((line) -> line.words.split(" "))
				.reduce((words, count) -> {
					_.each(words, (word) -> {
						if (!count.containsKey(word)) {
							count.put(word, 1);
						} else {
							count.put(word, count.get(word) + 1);
						}
					});
					return count;
				}, new HashMap<String, Integer>()).toString();
		System.out.println(val);
	}
}
