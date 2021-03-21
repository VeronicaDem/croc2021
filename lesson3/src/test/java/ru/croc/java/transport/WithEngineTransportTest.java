package ru.croc.java.transport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.java.transport.enums.*;

/**
 * Тест для автотранспорта.
 */
public class WithEngineTransportTest {
    WithEngineTransport transport = new WithEngineTransport(
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
            true,
            ContainsType.PASSENGER,
            "A39",
            Bodywork.CUPE,
            true
    );
    @Test
    /**
     * Проверка работы функции {@link WithEngineTransport#repair()} при исправленном транспорте
     */
    public void testTransportRepaired() {
        transport.setOK(true);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("WithEngineTransport{" +
                "containsType=" + transport.getContainsType() +
                ", concreteFuel='" + transport.getConcreteFuel() + '\'' +
                ", bodywork=" + transport.getBodywork() +
                ", left=" + transport.isLeft() +
                '}', result);
    }
    @Test
    /**
     * Проверка работы функции {@link WithEngineTransport#repair()} при неисправленном транспорте
     */
    public void testTransportNotRepaired() {
        transport.setOK(false);
        String result = transport.repair();
        Assertions.assertTrue(transport.isOK());
        Assertions.assertEquals("WithEngineTransport{" +
                "containsType=" + transport.getContainsType() +
                ", concreteFuel='" + transport.getConcreteFuel() + '\'' +
                ", bodywork=" + transport.getBodywork() +
                ", left=" + transport.isLeft() +
                '}', result);
    }
}
