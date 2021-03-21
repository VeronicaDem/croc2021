package ru.croc.java.transport.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.ContainsType;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.UseType;

/**
 * Тест для самолета.
 */
public class PlaneTest {
    Plane transport = new Plane(
            UseType.SPECIFICAL,
            1990,
            150,
            15.0,
            10000000L,
            "black",
            "BOEING",
            100,
            100,
            true,
            Fuel.LIQUID,
            true,
            100,
            100,
            ContainsType.PASSENGER,
            true,
            true,
            false,
            15,
            10,
            15,
            15,
            12,
            10

    );
    @Test
    /**
     * Проверка работы функции {@link Plane#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Plane{" +
                "scope=" + transport.getScope() +
                ", length=" + transport.getLength() +
                ", square=" + transport.getSquare() +
                ", chasisWidth=" + transport.getChasisWidth() +
                ", kOverload=" + transport.getkOverload() +
                ", maxKOverload=" + transport.getMaxKOverload() +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link Plane#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Plane{" +
                "scope=" + transport.getScope() +
                ", length=" + transport.getLength() +
                ", square=" + transport.getSquare() +
                ", chasisWidth=" + transport.getChasisWidth() +
                ", kOverload=" + transport.getkOverload() +
                ", maxKOverload=" + transport.getMaxKOverload() +
                '}', result);
    }
}
