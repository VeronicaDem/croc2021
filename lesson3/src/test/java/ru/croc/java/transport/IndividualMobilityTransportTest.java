package ru.croc.java.transport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.Fuel;
import ru.croc.java.transport.enums.TransportType;

/**
 * Тест для транспорта личного использования
 */
public class IndividualMobilityTransportTest {
    IndividualMobilityTransport transport = new IndividualMobilityTransport(
            TransportType.TERRESTRIAL,
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
            true,
            100,
            2
    );
    @Test
    /**
     * Проверка работы функции {@link IndividualMobilityTransport#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("IndividualMobilityTransport{" +
                "probeg=" + transport.getProbeg() +
                ", countPassengers=" + transport.getCountPassengers() +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link IndividualMobilityTransport#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("IndividualMobilityTransport{" +
                "probeg=" + transport.getProbeg() +
                ", countPassengers=" + transport.getCountPassengers() +
                '}', result);
    }
}
