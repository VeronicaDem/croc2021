package ru.croc.java.transport.models;

import ru.croc.java.transport.FlyingTransport;
import ru.croc.java.transport.enums.ContainsType;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.UseType;

/**
 * Вертолет.
 */
public class Helicopter extends FlyingTransport {
    /**
     * Угол остановки лопастей.
     */
    double angle;
    /**
     * Есть реактивный двигатель или нет
     */
    boolean reactiveEngine;
    /**
     * Максимальная вибрация в Гц.
     * Несущий винт создает вибрацию, угрожающую разрушением вертолета
     * Введем максимальную вибрацию...
     */
    double maxVibration;

    public Helicopter(UseType useType, int year, double maxVelocity, double capacity, double cost, String color,
                      String brand, double power, int quantity, boolean isOK, Fuel typeFuel, boolean isRented,
                      double longitude, double duration, ContainsType containsType, boolean withAutomatic,
                      boolean onlyOnePilot, double angle, boolean reactiveEngine, double maxVibration) {
        super(useType, year, maxVelocity, capacity, cost, color,
                brand, power, quantity, isOK, typeFuel, isRented,
                longitude, duration, containsType, withAutomatic, true, onlyOnePilot);
        this.angle = angle;
        this.reactiveEngine = reactiveEngine;
        this.maxVibration = maxVibration;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isReactiveEngine() {
        return reactiveEngine;
    }

    public void setReactiveEngine(boolean reactiveEngine) {
        this.reactiveEngine = reactiveEngine;
    }

    public double getMaxVibration() {
        return maxVibration;
    }

    public void setMaxVibration(double maxVibration) {
        this.maxVibration = maxVibration;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                "angle=" + angle +
                ", reactiveEngine=" + reactiveEngine +
                ", maxVibration=" + maxVibration +
                '}';
    }
}
