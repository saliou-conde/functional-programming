package de.arag.functinal.programming;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.Function;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class FunctionalProgrammingApplicationTests {

	@Test
	void contextLoads() {
		//Given
		String hello = "Hello World!";

		//Then
		assertThat(hello).isEqualTo("Hello World!");
	}

	@Test
	void should_return_uppercase() {
		String apply = uppercase().apply("hello world!");

		assertThat(apply).isEqualTo("HELLO WORLD!");
	}


	private Function<String, String> uppercase() {
		return String::toUpperCase;
	}

}
