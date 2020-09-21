package net.devtech.math.bench;

import java.math.BigInteger;

import net.devtech.math.Fraction;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

public class Bench {
	public static final Fraction CACHED = Fraction.of(1, 3);
	public static final Fraction BIG_CACHED = Fraction.of(new BigInteger("234567865432345678654323456"), new BigInteger("8765434567897654324567865432"));

	@Benchmark
	public void control(Blackhole blackhole) {
		blackhole.consume(false);
	}

	@Benchmark
	public void allocateCached(Blackhole blackhole) {
		blackhole.consume(Fraction.of(1, 3));
	}

	@Benchmark
	public void allocate(Blackhole blackhole) {
		// fractions are not simplified until necessary, so we don't need random numbers here
		blackhole.consume(Fraction.of(16543, 654353));
	}

	// division is a constant time operation because fractions are lazily simplified
	@Benchmark
	public void divideBy3_1(Blackhole blackhole) {
		blackhole.consume(CACHED.div(3));
	}

	@Benchmark
	public void divideBy3_2(Blackhole blackhole) {
		for (int i = 0; i < 5; i++) {
			blackhole.consume(CACHED.div(3));
		}
	}

	@Benchmark
	public void divideBy3_3(Blackhole blackhole) {
		// division is a constant time operation because fractions are lazily simplified
		for (int i = 0; i < 25; i++) {
			blackhole.consume(CACHED.div(3));
		}
	}

	@Benchmark
	public void casualOperation(Blackhole blackhole) {
		Fraction fraction = CACHED;
		// take some water from a cauldron
		fraction = fraction.add(Fraction.of(2, 3));
		// take a bucket from a tank
		fraction = fraction.add(Fraction.ONE);
		// split amongst 3 pipes
		fraction = fraction.div(3);
		// split amongst 4 pipes
		fraction = fraction.div(4);
		// take 2 buckets from a tank
		fraction = fraction.add(Fraction.TWO);
	}

	@Benchmark
	public void casualOperationForced(Blackhole blackhole) {
		Fraction fraction = CACHED;
		// take some water from a cauldron
		fraction = fraction.add(Fraction.of(2, 3));
		// take a bucket from a tank
		fraction = fraction.add(Fraction.ONE);
		// split amongst 3 pipes
		fraction = fraction.div(3);
		// split amongst 4 pipes
		fraction = fraction.div(4);
		// take 2 buckets from a tank
		fraction = fraction.add(Fraction.TWO);

		// force simplification for benchmarking
		fraction.div(345432345432L);
		fraction.div(345432345432L);
	}
}
