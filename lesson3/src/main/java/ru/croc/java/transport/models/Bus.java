package ru.croc.java.transport.models;

import ru.croc.java.transport.*;
import ru.croc.java.transport.enums.*;

/**
 * Автобус.
 */
public class Bus extends WithEngineTransport {
    /**
     * Количество мест в транспорте.
     */
    int countPassengers;
    /**
     * Максимальный вес в кг.
     */
    double maxWeight;
    /**
     * Количество ярусов.
     */
    int countLevels;

    public Bus(int year, double maxVelocity, double capacity, double cost, String color, String brand, double power,
               int quantity, boolean isOK, boolean isRented, String concreteFuel, Bodywork bodywork, boolean left,
               int countPassengers, double maxWeight, int countLevels) {
        super(TransportType.TERRESTRIAL, UseType.PUBLIC, year, maxVelocity, capacity, cost, color, brand, power,
                quantity, isOK, Fuel.LIQUID, isRented, ContainsType.PASSENGER, concreteFuel, bodywork, left);
        this.countPassengers = countPassengers;
        this.maxWeight = maxWeight;
        this.countLevels = countLevels;
    }

    public int getCountPassengers() {
        return countPassengers;
    }

    public void setCountPassengers(int countPassengers) {
        this.countPassengers = countPassengers;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public int getCountLevels() {
        return countLevels;
    }

    public void setCountLevels(int countLevels) {
        this.countLevels = countLevels;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "countPassengers=" + countPassengers +
                ", maxWeight=" + maxWeight +
                ", countLevels=" + countLevels +
                '}';
    }
}
