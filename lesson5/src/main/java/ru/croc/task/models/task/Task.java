package ru.croc.task.models.task;

import ru.croc.task.models.enums.Status;

import java.io.Serializable;

/**
 * Задача.
 */
public class Task implements Serializable {
    /**
     * Стандартно для использования ObjectInput/OutputStream.
     */
    private static final long serialVersionUID = 1l;
    /**
     * Код задачи.
     */
    private final int id;
    /**
     * Наименование.
     */
    private String name;
    /**
     * Описание.
     */
    private String description;
    /**
     * Статус
     */
    private Status status;
    /**
     * Исполнитель.
     */
    private String executor;


    public Task(String name, String description, String executor, Status status, int id) {
        this.name = name;
        this.description = description;
        this.executor = executor;
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Задача с кодом: " +
                id +
                ", наименование='" + name + '\'' +
                ", описание='" + description + '\'' +
                ", исполнитель='" + executor + '\'';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
