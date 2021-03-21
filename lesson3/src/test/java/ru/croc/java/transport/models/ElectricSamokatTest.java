package ru.croc.java.transport.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Тест для электросамоката.
 */
public class ElectricSamokatTest {
    ElectricSamokat transport = new ElectricSamokat(
            2014,
            200,
            150,
            1000000L,
            "red",
            "BMW",
            150,
            15,
            true,
            true,
            100,
            2,
            100,
            150,
            true,
            false,
            "plastic"
    );
    @Test
    /**
     * Проверка работы функции {@link ElectricSamokat#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("ElectricSamokat{" +
                "energy=" + transport.getEnergy() +
                ", circleSize=" + transport.getCircleSize() +
                ", withFire=" + transport.isWithFire() +
                ", withApp=" + transport.isWithApp() +
                ", material='" + transport.getMaterial() + '\'' +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link ElectricSamokat#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("ElectricSamokat{" +
                "energy=" + transport.getEnergy() +
                ", circleSize=" + transport.getCircleSize() +
                ", withFire=" + transport.isWithFire() +
                ", withApp=" + transport.isWithApp() +
                ", material='" + transport.getMaterial() + '\'' +
                '}', result);
    }
}
