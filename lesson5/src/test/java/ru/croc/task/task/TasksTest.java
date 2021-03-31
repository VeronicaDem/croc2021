package ru.croc.task.task;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.task.enums.Status;

public class TasksTest {
    @Test
    /**
     * Тестирование добавления задачи - функции {@link Tasks#addTask(String, String, String, Status)}
     */
    public void testAddTask() {
        Tasks tasks = new Tasks();
        Task task = tasks.addTask("Название задачи",
                        "Описание задачи",
                        "Исполнитель",
                        Status.DONE);
        Assertions.assertTrue(tasks.has(task.getId()));
    }
    @Test
    /**
     * Тестирование редактирования - функции {@link Tasks#changeTask(int, String, String, String, Status)}
     */
    public void testChangeTask() throws Exception{
        Tasks tasks = new Tasks();
        Task task = tasks.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasks.changeTask(task.getId(), "Еще одна задача", "XXX", "Аркадий",
                    Status.PROCESSING);
        Assertions.assertEquals("Еще одна задача",task.getName());
        Assertions.assertEquals("XXX", task.getDescription());
        Assertions.assertEquals("Аркадий", task.getExecutor());
        Assertions.assertEquals(Status.PROCESSING, task.getStatus());
        Assertions.assertThrows(Exception.class,()-> tasks.changeTask(-1,
                "Еще одна задача",
                "XXX",
                "Аркадий",
                Status.PROCESSING));
    }
    @Test
    /**
     * Тестирование частичного редактирования задачи - функции {@link Tasks#changeTaskStatus(int, Status)}
     */
    public void testChangeTaskStatus() throws Exception{
        Tasks tasks = new Tasks();
        Task task = tasks.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasks.changeTaskStatus(task.getId(), Status.PROCESSING);
        Assertions.assertEquals(Status.PROCESSING, task.getStatus());
        Assertions.assertEquals("Название задачи", task.getName());
        Assertions.assertEquals("Описание задачи", task.getDescription());
        Assertions.assertEquals("Исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasks.changeTaskStatus(-1, Status.EXPIRED));

    }
    @Test
    /**
     * Тестирование частичного редактирования задачи - функции {@link Tasks#changeTaskName(int, String)}}
     */
    public void testChangeTaskName() throws Exception{
        Tasks tasks = new Tasks();
        Task task = tasks.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasks.changeTaskName(task.getId(), "Другое название задачи");
        Assertions.assertEquals(Status.DONE, task.getStatus());
        Assertions.assertEquals("Другое название задачи", task.getName());
        Assertions.assertEquals("Описание задачи", task.getDescription());
        Assertions.assertEquals("Исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasks.changeTaskName(-1, "Название"));

    }
    @Test
    /**
     * Тестирование частичного редактирование задачи - функции {@link Tasks#changeTaskExecutor(int, String)}
     */
    public void testChangeTaskExecutor() throws Exception {
        Tasks tasks = new Tasks();
        Task task = tasks.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasks.changeTaskExecutor(task.getId(), "Другой исполнитель");
        Assertions.assertEquals(Status.DONE, task.getStatus());
        Assertions.assertEquals("Название задачи", task.getName());
        Assertions.assertEquals("Описание задачи", task.getDescription());
        Assertions.assertEquals("Другой исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasks.changeTaskExecutor(-1, "Другой исполнитель"));
    }
    @Test
    /**
     * Тестирование частичного редактирование задачи - функции {@link Tasks#changeTaskDescription(int, String)}
     */
    public void testChangeTaskDescription() throws Exception {
        Tasks tasks = new Tasks();
        Task task = tasks.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasks.changeTaskDescription(task.getId(), "Другое описание");
        Assertions.assertEquals(Status.DONE, task.getStatus());
        Assertions.assertEquals("Название задачи", task.getName());
        Assertions.assertEquals("Другое описание", task.getDescription());
        Assertions.assertEquals("Исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasks.changeTaskDescription(-1, "Описание"));
    }
    @Test
    /**
     * Тестирование функции удаления - {@link Tasks#deleteTaskById(int)}
     */
    public void testDeleteById() {
        Tasks tasks = new Tasks();
        Task task = tasks.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        Assertions.assertTrue(tasks.deleteTaskById(task.getId()));
        Assertions.assertTrue(tasks.has(task.getId()));
        Assertions.assertFalse(tasks.deleteTaskById(-1));
    }
}
