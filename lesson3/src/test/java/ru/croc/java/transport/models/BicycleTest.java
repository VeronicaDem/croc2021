package ru.croc.java.transport.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.AgeType;

/**
 * Тест для велосипеда.
 */
public class BicycleTest {
    Bicycle transport = new Bicycle(
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
            AgeType.KIDS,
            true
    );
    @Test
    /**
     * Проверка работы функции {@link Bicycle#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Bicycle{" +
                "ageType=" + transport.getAgeType() +
                ", withBasket=" + transport.isWithBasket() +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link Bicycle#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Bicycle{" +
                "ageType=" + transport.getAgeType() +
                ", withBasket=" + transport.isWithBasket() +
                '}', result);
    }
}
