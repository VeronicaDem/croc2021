import ru.croc.lesson7.dbprovider.DataSourceProvider;
import ru.croc.lesson7.model.Student;
import ru.croc.lesson7.repository.StudentRepository;
import ru.croc.lesson7.service.StudentService;

import java.io.IOException;
import java.time.LocalDate;

public class Application {
    public static void main(String[] args) throws IOException {
        DataSourceProvider dataSourceProvider = new DataSourceProvider();
        StudentRepository studentRepository = new StudentRepository(dataSourceProvider.getDataSource());
        StudentService service = new StudentService(studentRepository);

        Student student = service.createNew(new Student(1, "Ivan Ivanov", 1,
                                                          LocalDate.of(2000, 10, 13),
                                                          false,
                                                        LocalDate.of(2018, 1, 18)));
        service.getAll().forEach(System.out::println);
    }
}
