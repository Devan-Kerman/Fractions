package net.devtech.math.test;

import java.math.BigInteger;

import net.devtech.math.BigFraction;
import net.devtech.math.Fraction;
import net.devtech.math.LongFraction;
import net.devtech.math.test.matchers.InstanceOf;
import net.devtech.math.test.matchers.IsPositive;
import org.junit.Assert;
import org.junit.Test;

public class Tests {
	@Test
	public void cache() {
		Fraction fraction = Fraction.of(1, 3);
		Assert.assertEquals("1/3", fraction.toString());
	}

	@Test
	public void equality() {
		Fraction fraction = Fraction.of(64, 128);
		Assert.assertEquals(Fraction.ONE_HALF, fraction);
	}

	@Test
	public void autoExpand() {
		Fraction fraction = Fraction.of(1, Long.MAX_VALUE);
		fraction = fraction.div(2);
		Assert.assertThat(fraction, new InstanceOf<>(BigFraction.class));
		Assert.assertEquals(fraction.toString(), "1/" + BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.valueOf(2)));
		fraction = fraction.mul(2);
		Assert.assertEquals(Fraction.of(1, Long.MAX_VALUE), fraction);
		Assert.assertThat(fraction, new InstanceOf<>(LongFraction.class));
	}

	@Test
	public void zero() {
		Fraction fraction = Fraction.of(0, 100);
		Assert.assertEquals("0/1", fraction.toString());
	}

	@Test
	public void simplification() {
		Fraction fraction = Fraction.of(new BigInteger("10000000000000000000"), new BigInteger("10000000000000000000"));
		Assert.assertEquals("1/1", fraction.toString());
	}

	@Test
	public void comparison() {
		Assert.assertThat(Fraction.ONE_HALF.compareTo(Fraction.ONE_THIRD), new IsPositive());
	}
}
