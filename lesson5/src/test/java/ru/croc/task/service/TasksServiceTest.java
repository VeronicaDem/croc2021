package ru.croc.task.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.task.models.enums.Status;
import ru.croc.task.models.task.Task;

public class TasksServiceTest {
    @Test
    /**
     * Тестирование добавления задачи - функции {@link TasksService#addTask(String, String, String, Status)}
     */
    public void testAddTask() {
        TasksService tasksService = new TasksService();
        Task task = tasksService.addTask("Название задачи",
                        "Описание задачи",
                        "Исполнитель",
                        Status.DONE);
        Assertions.assertTrue(tasksService.has(task.getId()));
    }
    @Test
    /**
     * Тестирование редактирования - функции {@link TasksService#changeTask(int, String, String, String, Status)}
     */
    public void testChangeTask() throws Exception{
        TasksService tasksService = new TasksService();
        Task task = tasksService.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasksService.changeTask(task.getId(), "Еще одна задача", "XXX", "Аркадий",
                    Status.PROCESSING);
        Assertions.assertEquals("Еще одна задача",task.getName());
        Assertions.assertEquals("XXX", task.getDescription());
        Assertions.assertEquals("Аркадий", task.getExecutor());
        Assertions.assertEquals(Status.PROCESSING, task.getStatus());
        Assertions.assertThrows(Exception.class,()-> tasksService.changeTask(-1,
                "Еще одна задача",
                "XXX",
                "Аркадий",
                Status.PROCESSING));
    }
    @Test
    /**
     * Тестирование частичного редактирования задачи - функции {@link TasksService#changeTaskStatus(int, Status)}
     */
    public void testChangeTaskStatus() throws Exception{
        TasksService tasksService = new TasksService();
        Task task = tasksService.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasksService.changeTaskStatus(task.getId(), Status.PROCESSING);
        Assertions.assertEquals(Status.PROCESSING, task.getStatus());
        Assertions.assertEquals("Название задачи", task.getName());
        Assertions.assertEquals("Описание задачи", task.getDescription());
        Assertions.assertEquals("Исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasksService.changeTaskStatus(-1, Status.EXPIRED));

    }
    @Test
    /**
     * Тестирование частичного редактирования задачи - функции {@link TasksService#changeTaskName(int, String)}}
     */
    public void testChangeTaskName() throws Exception{
        TasksService tasksService = new TasksService();
        Task task = tasksService.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasksService.changeTaskName(task.getId(), "Другое название задачи");
        Assertions.assertEquals(Status.DONE, task.getStatus());
        Assertions.assertEquals("Другое название задачи", task.getName());
        Assertions.assertEquals("Описание задачи", task.getDescription());
        Assertions.assertEquals("Исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasksService.changeTaskName(-1, "Название"));

    }
    @Test
    /**
     * Тестирование частичного редактирование задачи - функции {@link TasksService#changeTaskExecutor(int, String)}
     */
    public void testChangeTaskExecutor() throws Exception {
        TasksService tasksService = new TasksService();
        Task task = tasksService.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasksService.changeTaskExecutor(task.getId(), "Другой исполнитель");
        Assertions.assertEquals(Status.DONE, task.getStatus());
        Assertions.assertEquals("Название задачи", task.getName());
        Assertions.assertEquals("Описание задачи", task.getDescription());
        Assertions.assertEquals("Другой исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasksService.changeTaskExecutor(-1, "Другой исполнитель"));
    }
    @Test
    /**
     * Тестирование частичного редактирование задачи - функции {@link TasksService#changeTaskDescription(int, String)}
     */
    public void testChangeTaskDescription() throws Exception {
        TasksService tasksService = new TasksService();
        Task task = tasksService.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        tasksService.changeTaskDescription(task.getId(), "Другое описание");
        Assertions.assertEquals(Status.DONE, task.getStatus());
        Assertions.assertEquals("Название задачи", task.getName());
        Assertions.assertEquals("Другое описание", task.getDescription());
        Assertions.assertEquals("Исполнитель", task.getExecutor());
        Assertions.assertThrows(Exception.class, ()-> tasksService.changeTaskDescription(-1, "Описание"));
    }
    @Test
    /**
     * Тестирование функции удаления - {@link TasksService#deleteTaskById(int)}
     */
    public void testDeleteById() {
        TasksService tasksService = new TasksService();
        Task task = tasksService.addTask("Название задачи",
                "Описание задачи",
                "Исполнитель",
                Status.DONE);
        Assertions.assertTrue(tasksService.deleteTaskById(task.getId()));
        Assertions.assertFalse(tasksService.has(task.getId()));
        Assertions.assertFalse(tasksService.deleteTaskById(-1));
    }
}
