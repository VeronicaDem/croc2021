package ru.croc.java.transport.models;

import ru.croc.java.transport.enums.AgeType;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.IndividualMobilityTransport;
import ru.croc.java.transport.enums.TransportType;

/**
 * Велосипед.
 */
public class Bicycle extends IndividualMobilityTransport {
    /**
     * Тип: детский, взрослый
     */
    AgeType ageType;
    /**
     * С корзинкой
     */
    boolean withBasket;

    public Bicycle(int year, double maxVelocity, double capacity, double cost, String color, String brand, double power,
                   int quantity, boolean isOK, boolean isRented, double probeg, int countPassengers, AgeType ageType,
                   boolean withBasket) {
        super(TransportType.TERRESTRIAL, year, maxVelocity, capacity, cost, color, brand, power, quantity, isOK,
                Fuel.LIQUID, isRented, probeg, countPassengers);
        this.ageType = ageType;
        this.withBasket = withBasket;
    }

    public AgeType getAgeType() {
        return ageType;
    }

    public void setAgeType(AgeType ageType) {
        this.ageType = ageType;
    }

    public boolean isWithBasket() {
        return withBasket;
    }

    public void setWithBasket(boolean withBasket) {
        this.withBasket = withBasket;
    }

    @Override
    public String toString() {
        return "Bicycle{" +
                "ageType=" + ageType +
                ", withBasket=" + withBasket +
                '}';
    }
}
