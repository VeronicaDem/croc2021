package ru.croc.java.transport;

import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.TransportType;
import ru.croc.java.transport.enums.UseType;

/**
 * Транспорт личного пользования.
 */
public class IndividualMobilityTransport extends Transport{
    /**
     * Пробег.
     */
    double probeg;
    /**
     * Максимальное количество пассажиров.
     */
    int countPassengers;

    public IndividualMobilityTransport(TransportType transportType, int year, double maxVelocity, double capacity,
                                       double cost, String color, String brand, double power, int quantity,
                                       boolean isOK, Fuel typeFuel, boolean isRented,
                                       double probeg, int countPassengers) {
        super(transportType, UseType.INDIVIDUAL, year, maxVelocity, capacity, cost, color,
                brand, power, quantity, isOK, typeFuel, isRented);
        this.probeg = probeg;
        this.countPassengers = countPassengers;
    }
    @Override
    public String repair() {
        super.repair();
        return toString();
    }

    @Override
    public String toString() {
        return "IndividualMobilityTransport{" +
                "probeg=" + probeg +
                ", countPassengers=" + countPassengers +
                '}';
    }

    public double getProbeg() {
        return probeg;
    }

    public void setProbeg(double probeg) {
        this.probeg = probeg;
    }

    public int getCountPassengers() {
        return countPassengers;
    }

    public void setCountPassengers(int countPassengers) {
        this.countPassengers = countPassengers;
    }
}
