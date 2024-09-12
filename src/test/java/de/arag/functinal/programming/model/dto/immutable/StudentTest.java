package de.arag.functinal.programming.model.dto.immutable;

import de.arag.functinal.programming.model.dto.AddressRequest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class StudentTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentTest.class);


    @Test
    void should_return_student_without_changing_fields() {
        //Given
        var name = "Saliou Conde";
        List<String> contacts = List.of("1234", "1254");
        AddressRequest address = new AddressRequest();
        address.setCity("Burgdorf");
        address.setZip("34000");
        address.setState("Bern");
        address.setStreet("Oberburgstrasse 37");
        Student student = new Student(name, 38, address, contacts);

        /*
         * Change the fields:
         * AddressRequest#street
         * Student#contacts
         */
        student.getAddress().setStreet("Oberburgstrasse 11A");
        student.getContacts().add("9999");
        LOGGER.info(student.toString());

        //When
        String actual = student.getName();

        //Then
        assertThat(actual).isEqualTo(name);
        assertThat(student.getAddress().getStreet()).isEqualTo("Oberburgstrasse 37");
        assertThat(student.getContacts()).isEqualTo(contacts);
    }

}