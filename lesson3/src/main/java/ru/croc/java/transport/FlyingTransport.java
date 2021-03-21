package ru.croc.java.transport;

import ru.croc.java.transport.enums.ContainsType;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.TransportType;
import ru.croc.java.transport.enums.UseType;

/**
 * Летательное транспортное средство.
 */
public class FlyingTransport extends Transport{
    /**
     * Дальность полета.
     */
    double longitude;
    /**
     * Продолжительность полета (в часах)
     */
    double duration;
    /**
     * Грузовой или пассажирский.
     */
    ContainsType containsType;
    /**
     * Есть средства автоматики или нет.
     */
    boolean withAutomatic;
    /**
     * Обладает высокой манервенностью.
     */
    boolean highManeuver;
    /**
     * Однопиотный или нет
     */
    boolean onlyOnePilot;

    public FlyingTransport(UseType useType, int year, double maxVelocity, double capacity, double cost, String color,
                           String brand, double power, int quantity, boolean isOK, Fuel typeFuel, boolean isRented,
                           double longitude, double duration, ContainsType containsType, boolean withAutomatic,
                           boolean highManeuver, boolean onlyOnePilot) {
        super(TransportType.AERO, useType, year, maxVelocity, capacity, cost, color,
                brand, power, quantity, isOK, typeFuel, isRented);
        this.longitude = longitude;
        this.duration = duration;
        this.containsType = containsType;
        this.withAutomatic = withAutomatic;
        this.highManeuver = highManeuver;
        this.onlyOnePilot = onlyOnePilot;
    }

    @Override
    public String toString() {
        return "FlyingTransport{" +
                "longitude=" + longitude +
                ", duration=" + duration +
                ", containsType=" + containsType +
                ", withAutomatic=" + withAutomatic +
                ", highManeuver=" + highManeuver +
                ", onlyOnePilot=" + onlyOnePilot +
                '}';
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public ContainsType getContainsType() {
        return containsType;
    }

    public void setContainsType(ContainsType containsType) {
        this.containsType = containsType;
    }

    public boolean isWithAutomatic() {
        return withAutomatic;
    }

    public void setWithAutomatic(boolean withAutomatic) {
        this.withAutomatic = withAutomatic;
    }

    public boolean isHighManeuver() {
        return highManeuver;
    }

    public void setHighManeuver(boolean highManeuver) {
        this.highManeuver = highManeuver;
    }

    public boolean isOnlyOnePilot() {
        return onlyOnePilot;
    }

    public void setOnlyOnePilot(boolean onlyOnePilot) {
        this.onlyOnePilot = onlyOnePilot;
    }
}
