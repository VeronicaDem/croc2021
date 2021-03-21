package ru.croc.java.transport.models;

import ru.croc.java.transport.FlyingTransport;
import ru.croc.java.transport.enums.ContainsType;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.UseType;

/**
 * Самолет.
 */
public class Plane extends FlyingTransport {
    /**
     * Размах.
     */
    double scope;
    /**
     * Общая длина.
     */
    double length;
    /**
     * Площадь крыла.
     */
    double square;
    /**
     * Ширина шасси.
     */
    double chasisWidth;
    /**
     * Коэффициент перегрузки.
     */
    double kOverload;
    /**
     * Нагрузка.
     */
    final double maxKOverload;

    public Plane(UseType useType, int year, double maxVelocity, double capacity, double cost, String color,
                 String brand, double power, int quantity, boolean isOK, Fuel typeFuel, boolean isRented,
                 double longitude, double duration, ContainsType containsType, boolean withAutomatic,
                 boolean highManeuver, boolean onlyOnePilot, double scope, double length, double square,
                 double chasisWidth, double kOverload, double maxKOverload) {
        super(useType, year, maxVelocity, capacity, cost, color, brand, power, quantity, isOK, typeFuel,
                isRented, longitude, duration, containsType, withAutomatic, highManeuver, onlyOnePilot);
        this.scope = scope;
        this.length = length;
        this.square = square;
        this.chasisWidth = chasisWidth;
        this.kOverload = kOverload;
        this.maxKOverload = maxKOverload;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "scope=" + scope +
                ", length=" + length +
                ", square=" + square +
                ", chasisWidth=" + chasisWidth +
                ", kOverload=" + kOverload +
                ", maxKOverload=" + maxKOverload +
                '}';
    }

    public double getScope() {
        return scope;
    }

    public void setScope(double scope) {
        this.scope = scope;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getChasisWidth() {
        return chasisWidth;
    }

    public void setChasisWidth(double chasisWidth) {
        this.chasisWidth = chasisWidth;
    }

    public double getkOverload() {
        return kOverload;
    }

    public void setkOverload(double kOverload) {
        this.kOverload = kOverload;
    }

    public double getMaxKOverload() {
        return maxKOverload;
    }
}
