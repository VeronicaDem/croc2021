package ru.croc.task.task;

import ru.croc.task.enums.Status;
import ru.croc.task.task.Task;

import java.io.Serializable;
import java.util.*;

/**
 * Задачи.
 */
public class Tasks implements Serializable {
    /**
     * Стандартно для использования ObjectInput/OutputStream.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Список задач.
     */
    private List<Task> tasks;
    /**
     * Отображение кода задачи в индекс списка. (для удобства)
     */
    private Map<Integer, Integer> idToIndex;

    public Tasks() {
        tasks = new ArrayList<>();
        idToIndex = new HashMap<>();
    }
    /**
     * Есть ли задача с такой id.
     * @param id
     */
    public boolean has(int id) {
        return idToIndex.get(id) != null;
    }
    /**
     * Добавление задачи
     * @param name наименование
     * @param description описание
     * @param executor исполнитель
     * @param status статус
     * @return добавленная задача
     */
    public Task addTask(String name, String description, String executor, Status status) {
        Random random = new Random();
        int id;
        do {
            id = random.nextInt();
        }while(idToIndex.get(id) != null || id <= 0);
        Task task = new Task(name,description,executor, status, id);
        tasks.add(task);
        idToIndex.put(id, tasks.size() - 1);
        return task;
    }

    /**
     * Редактирование задачи
     * @param id код редактируемой задачи
     * @param name наименование
     * @param description описание
     * @param executor исполнитель
     * @param status статус
     * @return отредактированная задача
     * @throws Exception нет такой задачи
     */
    public Task changeTask(int id, String name, String description, String executor, Status status) throws Exception{
        changeTaskExecutor(id, executor);
        changeTaskDescription(id, description);
        changeTaskName(id, name);
        Task task = changeTaskStatus(id, status);
        return task;
    }
    /**
     *  Редактирование по полю наименование
     *  @param id код редактируемой задачи
     *  @param name наименование
     *  @return отредактированная задача
      * @throws Exception нет такой задачи
     */
    public Task changeTaskName(int id, String name) throws Exception{
        if(idToIndex.get(id) == null) throw new Exception("Нет такой задачи");
        Task task = tasks.get(idToIndex.get(id));
        task.setName(name);
        return task;
    }

    /**
     * Редактирование по полю описание
     * @param id код редактируемой задачи
     * @param description описание
     * @return отредактированная задача
     * @throws Exception нет такой задачи
     */
    public Task changeTaskDescription(int id, String description) throws Exception{
        if(idToIndex.get(id) == null) throw new Exception("Нет такой задачи");
        Task task = tasks.get(idToIndex.get(id));
        task.setDescription(description);
        return task;
    }

    /**
     * Редактирование задачи по полю исполнитель
     * @param id код задачи
     * @param executor исполнитель
     * @return отредактированная задача
     * @throws Exception нет такой задачи
     */
    public Task changeTaskExecutor(int id, String executor) throws Exception{
        if(idToIndex.get(id) == null) throw new Exception("Нет такой задачи");
        Task task = tasks.get(idToIndex.get(id));
        task.setExecutor(executor);
        return task;
    }

    /**
     * Редактирование задачи по полю статус
     * @param id код задачи
     * @param status статус
     * @return отредактированная задача
     * @throws Exception нет такой задачи
     */
    public Task changeTaskStatus(int id, Status status) throws Exception{
        if(idToIndex.get(id) == null) throw new Exception("Нет такой задачи");
        Task task = tasks.get(idToIndex.get(id));
        task.setStatus(status);
        return task;
    }
    /**
     * Удаление задачи по коду
     * @param id код задачи
     * @return с успехом ли выполнилось удаление
     */
    public boolean deleteTaskById(int id) {
        if(idToIndex.get(id) == null) return false;
        Task task = tasks.get(idToIndex.get(id));
        tasks.remove(task);
        return true;
    }

    @Override
    public String toString() {
        String result = "Задачи:\n";
        for (Task task : tasks) {
            result += task.toString();
            result += "\n";
        }
        if (tasks.isEmpty()) {
            result = "Задач пока нет";
        }
        return result;
    }
}
