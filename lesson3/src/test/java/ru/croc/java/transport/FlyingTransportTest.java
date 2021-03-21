package ru.croc.java.transport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.ContainsType;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.UseType;

/**
 * Тест для общего класса летательного транспорта.
 */
public class FlyingTransportTest {
    FlyingTransport transport = new FlyingTransport(
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
            true
    );
    @Test
    /**
     * Проверка работы функции {@link FlyingTransport#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("FlyingTransport{" +
                "longitude=" + transport.getLongitude() +
                ", duration=" + transport.getDuration() +
                ", containsType=" + transport.getContainsType() +
                ", withAutomatic=" + transport.isWithAutomatic() +
                ", highManeuver=" + transport.isHighManeuver() +
                ", onlyOnePilot=" + transport.isOnlyOnePilot() +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link FlyingTransport#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("FlyingTransport{" +
                "longitude=" + transport.getLongitude() +
                ", duration=" + transport.getDuration() +
                ", containsType=" + transport.getContainsType() +
                ", withAutomatic=" + transport.isWithAutomatic() +
                ", highManeuver=" + transport.isHighManeuver() +
                ", onlyOnePilot=" + transport.isOnlyOnePilot() +
                '}', result);
    }
}
