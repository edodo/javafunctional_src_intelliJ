package lambdasinaction._01lambda.basic1;

import java.util.*;
import java.util.function.Consumer;

public class FilteringApples {

	public static void main(String... args) {

		List<Apple> inventory =
				Arrays.asList(new Apple(80, "green"),
						new Apple(155, "green"),
						new Apple(120, "red"));

		//1. filter method 호출 - Anonymous Inner class
		filter(inventory, new ApplePredicate<Apple>() {
			@Override
			public boolean test(Apple apple) {
				return apple.getColor().equals("red");
			}
		}).forEach(new Consumer<Apple>() {

			@Override
			public void accept(Apple apple) {
				System.out.println("필터링된 Apple = " + apple);
			}
		});

	}

	public static List<Apple> filter(List<Apple> inventory, ApplePredicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByColor(List<Apple> inventory, String color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	@FunctionalInterface
	interface ApplePredicate<T> {
		public boolean test(T a);
	}

	static class AppleWeightPredicate implements ApplePredicate<Apple> {
		public boolean test(Apple apple) {
			return apple.getWeight() > 150;
		}
	}

	static class AppleColorPredicate implements ApplePredicate<Apple> {
		public boolean test(Apple apple) {
			return "green".equals(apple.getColor());
		}
	}

	static class AppleRedAndHeavyPredicate implements ApplePredicate<Apple> {
		public boolean test(Apple apple) {
			return "red".equals(apple.getColor()) && apple.getWeight() > 150;
		}
	}
}