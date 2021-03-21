package ru.croc.java.transport.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.Bodywork;

/**
 * Тест для автобуса.
 */
public class BusTest {
    Bus transport = new Bus(
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
            "C2",
            Bodywork.UNIVERSAL,
            true,
            30,
            1000000,
            1
    );
    @Test
    /**
     * Проверка работы функции {@link Bus#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Bus{" +
                "countPassengers=" + transport.getCountPassengers() +
                ", maxWeight=" + transport.getMaxWeight() +
                ", countLevels=" + transport.getCountLevels()+
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link Bus#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Bus{" +
                "countPassengers=" + transport.getCountPassengers() +
                ", maxWeight=" + transport.getMaxWeight() +
                ", countLevels=" + transport.getCountLevels()+
                '}', result);
    }
}
