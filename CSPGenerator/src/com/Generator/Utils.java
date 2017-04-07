package com.Generator;

import java.util.List;
import java.util.Random;

public class Utils {
	
	private static Random r = new Random();

	public static Object getRandom(List<Object> items) {
		int index = r.nextInt(items.size());
		return items.get(index);
	}
	
}
