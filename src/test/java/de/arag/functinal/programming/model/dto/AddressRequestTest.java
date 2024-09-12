package de.arag.functinal.programming.model.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AddressRequestTest {
    private AddressRequest addressRequest1;
    private AddressRequest addressRequest2;
    private AddressRequest addressRequest3;

    @BeforeEach
    void setUp() {
        addressRequest1 = AddressRequest.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        addressRequest2 = AddressRequest.builder()
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .build();

        addressRequest3 = AddressRequest.builder()
                .street("street1")
                .city("city")
                .state("state")
                .zip("zip")
                .build();
    }

    @Test
    void test_equals_with_one_object() {
        //Given
        boolean expected = true;

        //When
        boolean actual = addressRequest1.equals(addressRequest1);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_equals_with_two_same_objects() {
        //Given
        boolean expected = true;

        //When
        boolean actual = addressRequest1.equals(addressRequest2);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_equals_with_two_different_objects() {
        //Given
        boolean expected = false;

        //When
        boolean actual = addressRequest1.equals(addressRequest3);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_equals_with_null_object() {
        //Given
        boolean expected = false;

        AddressRequest addressRequest2 = null;

        //When
        boolean actual = addressRequest1.equals(addressRequest2);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_hashcode_with_one_object() {
        //When
        int expected = addressRequest1.hashCode();
        int actual = addressRequest1.hashCode();

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_hashcode_with_two_same_objects() {
        //When
        int expected = addressRequest1.hashCode();
        int actual = addressRequest2.hashCode();

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void test_hashcode_with_two_different_objects() {
        //When
        int expected = addressRequest1.hashCode();
        int actual = addressRequest3.hashCode();

        //Then
        assertThat(actual).isNotEqualTo(expected);
    }

}