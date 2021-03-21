package ru.croc.java.transport.models;

import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.IndividualMobilityTransport;
import ru.croc.java.transport.enums.TransportType;

/**
 * Электрический самокат.
 */
public class ElectricSamokat extends IndividualMobilityTransport {
    /**
     * Емкость аккумулятора.
     */
    float energy;
    /**
     * Размер колеса.
     */
    float circleSize;
    /**
     * С подсветкой или нет.
     */
    boolean withFire;
    /**
     * Есть ли приложение для смартфона.
     */
    boolean withApp;
    /**
     * Материал.
     */
    String material;

    public ElectricSamokat(int year, double maxVelocity, double capacity, double cost, String color, String brand, double power, int quantity, boolean isOK, boolean isRented, double probeg, int countPassengers, float energy, float circleSize, boolean withFire, boolean withApp, String material) {
        super(TransportType.TERRESTRIAL, year, maxVelocity, capacity, cost, color, brand, power, quantity, isOK, Fuel.ELECTRICITY, isRented, probeg, countPassengers);
        this.energy = energy;
        this.circleSize = circleSize;
        this.withFire = withFire;
        this.withApp = withApp;
        this.material = material;
    }

    @Override
    public String toString() {
        return "ElectricSamokat{" +
                "energy=" + energy +
                ", circleSize=" + circleSize +
                ", withFire=" + withFire +
                ", withApp=" + withApp +
                ", material='" + material + '\'' +
                '}';
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        this.energy = energy;
    }

    public float getCircleSize() {
        return circleSize;
    }

    public void setCircleSize(float circleSize) {
        this.circleSize = circleSize;
    }

    public boolean isWithFire() {
        return withFire;
    }

    public void setWithFire(boolean withFire) {
        this.withFire = withFire;
    }

    public boolean isWithApp() {
        return withApp;
    }

    public void setWithApp(boolean withApp) {
        this.withApp = withApp;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
