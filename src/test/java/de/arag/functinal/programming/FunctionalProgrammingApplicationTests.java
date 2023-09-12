package de.arag.functinal.programming;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

}
