package de.arag.functinal.programming.solid.principle.liskow;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    @Test
    void numberOfWheels() {
        //Give
        List<Vehicle> vehicles = List.of(new Car(), new MotorCycle(), new MotorCycle());

        //When
        vehicles.forEach(Vehicle::numberOfWheels);

        //Then
        assertEquals(3, vehicles.size());
    }

    @Test
    void hasEngine() {
        //Give
        List<Vehicle> vehicles = List.of(new Car(), new MotorCycle(), new Bicycle());

        //When
        vehicles.stream().map(el -> el.numberOfWheels().toString()).toList().forEach(System.out::println);

        //Then
        assertEquals(3, vehicles.size());
    }
}