package ru.croc.java.transport.models;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ru.croc.java.transport.enums.ContainsType;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.UseType;

/**
 * Тест для вертолета.
 */
public class HelicopterTest {
    Helicopter transport = new Helicopter(
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
            190,
            true,
            10

    );
    @Test
    /**
     * Проверка работы функции {@link Helicopter#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Helicopter{" +
                "angle=" + transport.getAngle() +
                ", reactiveEngine=" + transport.isReactiveEngine() +
                ", maxVibration=" + transport.getMaxVibration() +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link Helicopter#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Helicopter{" +
                "angle=" + transport.getAngle() +
                ", reactiveEngine=" + transport.isReactiveEngine() +
                ", maxVibration=" + transport.getMaxVibration() +
                '}', result);
    }
}
