package ru.croc.lesson7.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.lesson7.dbprovider.DataSourceProvider;
import ru.croc.lesson7.model.Student;
import ru.croc.lesson7.service.StudentService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryTest {
    DataSourceProvider dataSourceProvider = new DataSourceProvider();
    StudentRepository studentRepository = new StudentRepository(dataSourceProvider.getDataSource());
    StudentService service = new StudentService(studentRepository);
    public StudentRepositoryTest() throws IOException {
    }

    /**
     * Тест удаления студента {@link StudentRepository#deleteStudentById(int)}
     */
    @Test
    public void deleteTest() {
        studentRepository.deleteStudentById(1);
        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        Assertions.assertTrue(studentRepository.deleteStudentById(1));
    }
    /**
     * Тест добавления студента {@link StudentRepository#createNew(Student)}
     */
    @Test
    public void createTest() {
        studentRepository.deleteStudentById(1);
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
     * Тест для просмотра студента {@link StudentRepository#showInfoById(int)}
     */
    @Test
    public void showOneTest() {
        studentRepository.deleteStudentById(1);
        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        Student showed = studentRepository.showInfoById(student.getId());
        Assertions.assertEquals(student.getId(), showed.getId());
        Assertions.assertEquals(student.getFio(), showed.getFio());
        Assertions.assertEquals(student.getGroup(), showed.getGroup());
        Assertions.assertEquals(student.getDateOfBirth(), showed.getDateOfBirth());
        Assertions.assertEquals(student.isExtracted(), showed.isExtracted());
        Assertions.assertEquals(student.getDateOfStart(), showed.getDateOfStart());
        studentRepository.deleteStudentById(1);
        showed = studentRepository.showInfoById(1);
        Assertions.assertNull(showed);
    }
    /**
     * Тест для {@link StudentRepository#findAll()}
     * (фантазии не хватило сделать лучше тест. Подскажите, как лучше проверить такие функции)
     */
    public void showAllTest() {
        studentRepository.deleteStudentById(1);
        studentRepository.deleteStudentById(2);
        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        Student student2 = service.createNew(new Student(2, "Ilya Petrov", 1,
                LocalDate.of(2000, 10, 13),
                false,
                LocalDate.of(2018, 1, 18)));
        List<Student> students = studentRepository.findAll();
        int count = 0;
        for(Student st : students) {
            if(st.getId() == 1 || st.getId() == 2) count++;
        }
        Assertions.assertEquals(2, count);
    }
    /**
     * Тест для {@link StudentRepository#changeStudent(Student)}
     */
    @Test
    public void changeTest() throws Exception {
        studentRepository.deleteStudentById(1);
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
        studentRepository.deleteStudentById(1);
        Assertions.assertThrows(Exception.class, ()-> studentRepository.changeStudent(new Student(1,
                "Ivan Ivanovich", 1, LocalDate.now(), true, LocalDate.now())));
    }
}
