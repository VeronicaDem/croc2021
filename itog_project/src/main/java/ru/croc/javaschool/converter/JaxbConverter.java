package ru.croc.javaschool.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class JaxbConverter {
    public JaxbConverter() {
    }

    /**
     * Вытаскивает Объект из текстового файла, содержащего XML
     */
    public <T> T fromFileToObject(String filePath, Class<T> type) throws IOException {
        String xml = Files.lines(Paths.get(filePath)).reduce("", String::concat);
        return fromXml(xml,
                type);


    }
    /**
     * Запись xml в файл
     */
    public void writeXmlToFile(String xml) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("statistics.xml"), "utf-8"))) {
            writer.write(xml);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /**
     * Сериализует объект в xml.
     *
     * @param data объект
     * @return xml
     */
    public String toXml(Object data) throws JsonProcessingException {
        return createXmlMapper().writeValueAsString(data);
    }

    /**
     * Десериализация из xml.
     *
     * @param xml  xml
     * @param type тип объекта
     * @param <T>  тип
     * @return объект
     */
    public <T> T fromXml(String xml, Class<T> type) throws IOException {
        return createXmlMapper().readValue(xml, type);
    }

    /**
     * Создаём настроенный mapper JAXB.
     *
     * @return mapper
     */
    private XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JaxbAnnotationModule()); // модуль jaxb
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // форматирование вывода
        mapper.setDefaultUseWrapper(false);
        return mapper;
    }
}

