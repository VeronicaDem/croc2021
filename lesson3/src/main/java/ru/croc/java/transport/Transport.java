package ru.croc.java.transport;

import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.TransportType;
import ru.croc.java.transport.enums.UseType;

/**
 * Общий класс для транспорта.
 */
public class Transport {
    /**
     * Тип транспорта  {@link TransportType}
     */
    private TransportType transportType;
    /**
     * Разделение по использованию. {@link UseType}
     */
    private UseType useType;
    /**
     * Год выпуска.
     */
    private int year;
    /**
     * Максимальная скорость, которую может развить транспорт.
     */
    private double maxVelocity;
    /**
     * Вместительность (в кг).
     */
    private double capacity;
    /**
     * Стоимость (в руб.).
     */
    private double cost;
    /**
     * Цвет.
     */
    private String color;
    /**
     * Марка.
     */
    private String brand;
    /**
     * Мощность в Вт.
     */
    private double power;
    /**
     * Количество этого транспорта в базе.
     */
    private int quantity;
    /**
     * Состояние транспорта. Исправность
     */
    private boolean isOK;
    /**
     * Тип топлива.
     */
    private Fuel typeFuel;
    /**
     * Состояние транспорта. Сдан в аренду
     */
    private boolean isRented;

    public Transport(TransportType transportType, UseType useType, int year, double maxVelocity, double capacity,
                     double cost, String color, String brand, double power, int quantity, boolean isOK,
                     Fuel typeFuel, boolean isRented) {
        this.transportType = transportType;
        this.useType = useType;
        this.year = year;
        this.maxVelocity = maxVelocity;
        this.capacity = capacity;
        this.cost = cost;
        this.color = color;
        this.brand = brand;
        this.power = power;
        this.quantity = quantity;
        this.isOK = isOK;
        this.typeFuel = typeFuel;
        this.isRented = isRented;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public UseType getUseType() {
        return useType;
    }

    public void setUseType(UseType useType) {
        this.useType = useType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(double maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public Fuel getTypeFuel() {
        return typeFuel;
    }

    public void setTypeFuel(Fuel typeFuel) {
        this.typeFuel = typeFuel;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    /**
     * Сдать в аренду
     * @return состояние - успешно сдан в аренду или нет
     */
    public boolean rentIn() {
        if (!isRented()) setRented(true);
        else return false;
        return true;
    }

    /**
     * Починить транспорт. Замена состояния на исправное.
     */
    public String repair() {
        setOK(true);
        return toString();
    }

    @Override
    public String toString() {
        return "Transport{" +
                "transportType=" + transportType +
                ", useType=" + useType +
                ", year=" + year +
                ", maxVelocity=" + maxVelocity +
                ", capacity=" + capacity +
                ", cost=" + cost +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", power=" + power +
                ", quantity=" + quantity +
                ", isOK=" + isOK +
                ", typeFuel=" + typeFuel +
                ", isRented=" + isRented +
                '}';
    }
}
