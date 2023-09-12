package de.arag.functinal.programming.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CustomerTest {

    @Test
    void testEquals() {
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        c1.setId(100);
        c1.setFirstname("Saliou");
        c1.setLastname("Conde");

        c2.setId(100);
        c2.setFirstname("Saliou");
        c2.setLastname("Conde");
        Customer c3 = c1;
        c3.setFirstname("Aliou");

        //Set<Customer> customers = Set.of(c1, c2);
       boolean isequal = c1 == c2;
       int hash1 = c1.hashCode();
       int hash2 = c2.hashCode();
       int hash3 = c3.hashCode();


        assertThat(isequal).isFalse();
        assertThat(c1.equals(c2)).isTrue();
        assertThat(hash1).isNotEqualTo(hash2);
        assertThat(c1 == c3).isTrue();
        assertThat(hash1).isEqualTo(hash3);
    }

    @Test
    void testString() {
        //Given
        String input = "This is CloudTech This is CloudTech";

        //When
        String value = input.substring(0, input.length()/2);
        StringBuilder result = new StringBuilder(value.toUpperCase());
        result.append(" ");
        result.append(value.toLowerCase());

        System.out.println(result.toString().trim());

        return;
    }

    @Test
    void testNumberLength() {
        //Given
        int number = 12345;
        int count = 0;

        //When
        while (number  > 0) {

            count ++;
            number = number / 10;
        }
        System.out.println("Length is: "+count);
    }
}