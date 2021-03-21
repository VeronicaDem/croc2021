package ru.croc.java.transport;

import ru.croc.java.transport.enums.*;

/**
 * Автотранспорт.
 */
public class WithEngineTransport extends Transport{
    /**
     * Грузовой или пассажирский
     */
   ContainsType containsType;
    /**
     * Конкретный тип топлива. Например, А76
     */
    String concreteFuel;
    /**
     * Тип кузова
     */
    Bodywork bodywork;
    /**
     * Левый руль или нет (правый тогда)
     */
    boolean left;

    public WithEngineTransport(TransportType transportType, UseType useType, int year, double maxVelocity,
                               double capacity, double cost, String color, String brand,
                               double power, int quantity, boolean isOK, Fuel typeFuel,
                               boolean isRented, ContainsType containsType, String concreteFuel,
                               Bodywork bodywork, boolean left) {
        super(transportType, useType, year, maxVelocity, capacity, cost, color, brand, power, quantity, isOK, typeFuel,
                isRented);
        this.containsType = containsType;
        this.concreteFuel = concreteFuel;
        this.bodywork = bodywork;
        this.left = left;
    }

    public ContainsType getContainsType() {
        return containsType;
    }

    public void setContainsType(ContainsType containsType) {
        this.containsType = containsType;
    }

    public String getConcreteFuel() {
        return concreteFuel;
    }

    public void setConcreteFuel(String concreteFuel) {
        this.concreteFuel = concreteFuel;
    }

    public Bodywork getBodywork() {
        return bodywork;
    }

    public void setBodywork(Bodywork bodywork) {
        this.bodywork = bodywork;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    @Override
    public String toString() {
        return "WithEngineTransport{" +
                "containsType=" + containsType +
                ", concreteFuel='" + concreteFuel + '\'' +
                ", bodywork=" + bodywork +
                ", left=" + left +
                '}';
    }
}
