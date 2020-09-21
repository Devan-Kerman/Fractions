package net.devtech.math.test.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class IsPositive extends BaseMatcher<Integer> {
	@Override
	public boolean matches(Object item) {
		return (int)item > 0;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("positive number");
	}
}
