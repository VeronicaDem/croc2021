package ru.croc.java.transport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.TransportType;
import ru.croc.java.transport.enums.UseType;

/**
 * Тест для транспорта.
 */
public class TransportTest {
    Transport transport = new Transport(
            TransportType.AERO,
            UseType.INDIVIDUAL,
            2014,
            200,
            150,
            1000000L,
            "red",
            "BMW",
            150,
            15,
            true,
            Fuel.ELECTRICITY,
            true
    );
    @Test
    /**
     * Проверка работы функции {@link Transport#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        transport.repair();
        Assertions.assertTrue(transport.isOK());

    }
    @Test
    /**
     * Проверка работы функции {@link Transport#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        transport.repair();
        Assertions.assertTrue(transport.isOK());
    }
    @Test
    /**
     * Проверка работы функции {@link Transport#rentIn()} при уже сданном в аренду транспорте
     */
    public void testTransportRented() {
        transport.setRented(true);
        Assertions.assertFalse(transport.rentIn());
    }
    @Test
    /**
     * Проверка работы функции {@link Transport#rentIn()}, если транспорт не сдан в аренду
     */
    public void testTransportNotRented() {
        transport.setRented(false);
        Assertions.assertTrue(transport.rentIn());
    }
}
