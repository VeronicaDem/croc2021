package ru.croc.lesson7.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.lesson7.dbprovider.DataSourceProvider;
import ru.croc.lesson7.model.Student;
import ru.croc.lesson7.repository.StudentRepository;

import java.io.IOException;
import java.time.LocalDate;

public class ServiceTest {
    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    StudentRepository studentRepository = new StudentRepository(dataSourceProvider.getDataSource());
    StudentService service = new StudentService(studentRepository);

    public ServiceTest() throws IOException {
    }

    /**
     * Проверка для {@link StudentService#createNew(Student)}
     */
    @Test
    public void testCreateNew() {
        service.deleteStudent(1);
        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        Assertions.assertEquals(1, student.getId());
        Assertions.assertEquals("Ivan Ivanov", student.getFio());
        Assertions.assertEquals(1, student.getGroup());
        Assertions.assertEquals(LocalDate.of(2000, 10, 13), student.getDateOfBirth());
        Assertions.assertEquals(false, student.isExtracted());
        Assertions.assertEquals(LocalDate.of(2018, 1, 18), student.getDateOfStart());
    }
    /**
     * Проверка для {@link StudentService#changeStudent(Student)}
     */
    @Test
    public void testUpdate() throws Exception {
        service.deleteStudent(1);
        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        student.setGroup(2);
        student.setDateOfBirth(LocalDate.of(2001, 10, 13));
        student.setDateOfStart(LocalDate.of(2018, 2, 19));
        student.setFio("Ivan Ivanovich");
        student.setExtracted(true);
        student = studentRepository.changeStudent(student);
        Assertions.assertEquals(2, student.getGroup());
        Assertions.assertEquals(LocalDate.of(2001, 10, 13), student.getDateOfBirth());
        Assertions.assertEquals(LocalDate.of(2018, 2, 19), student.getDateOfStart());
        Assertions.assertEquals("Ivan Ivanovich", student.getFio());
        Assertions.assertTrue(student.isExtracted());
    }
    /**
     * Проверка для {@link StudentService#getStudentById(int)}
     */
    @Test
    public void testGetStudent() {
        service.deleteStudent(1);
        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        student = service.getStudentById(student.getId());
        Assertions.assertEquals(1, student.getId());
        Assertions.assertEquals("Ivan Ivanov", student.getFio());
        Assertions.assertEquals(1, student.getGroup());
        Assertions.assertEquals(LocalDate.of(2000, 10, 13), student.getDateOfBirth());
        Assertions.assertEquals(false, student.isExtracted());
        Assertions.assertEquals(LocalDate.of(2018, 1, 18), student.getDateOfStart());
    }
    /**
     * Проверка для {@link StudentService#deleteStudent(int)}
     */
    @Test
    public void testDelete() {
        service.deleteStudent(1);
        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        Assertions.assertTrue(service.deleteStudent(1));
    }
}
