package de.arag.functinal.programming.solid.principle.liskow;

public class Car extends EngineVehicle {
    @Override
    public Integer numberOfWheels() {
        return super.numberOfWheels() + 2;
    }
}
