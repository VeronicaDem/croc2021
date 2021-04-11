package ru.croc.lesson7.service;

import ru.croc.lesson7.model.Student;
import ru.croc.lesson7.repository.StudentRepository;

import java.util.List;

/**
 * Сервис для студентов.
 */
public class StudentService {
    /**
     * Репозиторий.
     */
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    /**
     * Получение списка студентов
     * @return список студентов
     */
    public List<Student> getAll() {
        return repository.findAll();
    }

    /**
     * Создание студента
     * @param student студент
     * @return созданный студент
     */
    public Student createNew(Student student) {
        Student addedStudent = repository.createNew(student);
        return addedStudent;
    }
}
