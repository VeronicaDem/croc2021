package ru.croc.java.transport.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.Bodywork;
import ru.croc.java.transport.enums.ContainsType;
import ru.croc.java.transport.enums.Fuel;

/**
 * Тест для легковой машины.
 */
public class CarTest {
    Car transport = new Car(
            2018,
            150,
            130,
            1000000L,
            "red",
            "BMW",
            200.2,
            100,
            true,
            Fuel.ELECTRICITY,
            true,
            ContainsType.PASSENGER,
            "A20",
            Bodywork.SEDAN,
            true,
            10,
            10.0,
            "A"

    );
    @Test
    /**
     * Проверка работы функции {@link Car#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Car{" +
                "countPlaces=" + transport.getCountPlaces() +
                ", probeg=" + transport.getProbeg() +
                ", classSize='" + transport.getClassSize() + '\'' +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link Car#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("Car{" +
                "countPlaces=" + transport.getCountPlaces() +
                ", probeg=" + transport.getProbeg() +
                ", classSize='" + transport.getClassSize() + '\'' +
                '}', result);
    }
}
