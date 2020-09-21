package net.devtech.math.test.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class InstanceOf<C> extends BaseMatcher<C> {
	private final Class<?> type;

	public InstanceOf(Class<?> type) {this.type = type;}

	@Override
	public boolean matches(Object item) {
		return this.type.isInstance(item);
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("instanceof " + this.type);
	}
}
