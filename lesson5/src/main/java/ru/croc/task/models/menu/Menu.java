package ru.croc.task.models.menu;

import ru.croc.task.models.enums.Status;
import ru.croc.task.service.TasksService;

import java.util.Scanner;


/**
 * Меню.
 */
public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    /**
     * Приветствие
     */
    public static void Greeting() {
        System.out.println("Приветствую. Комания 'Рога и копыта'");
    }

    /**
     * Добавление задачи
     * @param tasksService задачи
     * @throws Exception Введите число от 1 до 3 включительно
     */
    public static void addTask(TasksService tasksService) throws Exception{
        System.out.println("Введите наименование:");
        String name = scanner.nextLine();
        System.out.println("Введите описание:");
        String description = scanner.nextLine();
        System.out.println("Введите исполнителя:");
        String executor = scanner.nextLine();
        System.out.println("Введите статус:");
        System.out.println("1-Сделано\n2-Выполняется сейчас\n3-Просрочено");
        int statusNumber = scanner.nextInt();
        Status status;
        status = choiceStatus(statusNumber);
        tasksService.addTask(name, description, executor, status);
    }

    /**
     * Изменение задачи
     * @param tasksService задачи
     * @throws Exception Введите число от 1 до 5; Нет такой задачи
     */
    public static void changeTask(TasksService tasksService) throws Exception{
        showTasks(tasksService);
        System.out.println("Введите код задачи:");
        int id = scanner.nextInt();
        System.out.println("По какому полю будете менять:");
        System.out.println("1 - по наименованию, \n" +
                           "2 - no описанию, \n" +
                           "3 - по исполнителю, \n" +
                           "4 - по статусу, \n" +
                           "5 - поменять все \n");
        int choice = scanner.nextInt();
        String name, description, executor;
        Status status;
        int statusNumber;
        switch(choice) {
            case 1:
                System.out.println("Введите имя:");
                name = scanner.nextLine();
                tasksService.changeTaskName(id, name);
                break;
            case 2:
                System.out.println("Введите описание:");
                description = scanner.nextLine();
                tasksService.changeTaskDescription(id, description);
                break;
            case 3:
                System.out.println("Введите исполнителя:");
                executor = scanner.nextLine();
                tasksService.changeTaskExecutor(id, executor);
                break;
            case 4:
                System.out.println("Введите статус:");
                System.out.println("1 - Сделано\n2-Выполняется сейчас\n3-Просрочено");
                statusNumber = scanner.nextInt();
                status = choiceStatus(statusNumber);
                tasksService.changeTaskStatus(id, status);
                break;
            case 5:
                System.out.println("Введите имя:");
                name = scanner.nextLine();


                System.out.println("Введите описание:");
                description = scanner.nextLine();


                System.out.println("Введите исполнителя:");
                executor = scanner.nextLine();


                System.out.println("Введите статус:");
                System.out.println("1 - Сделано\n2-Выполняется сейчас\n3-Просрочено");
                statusNumber = scanner.nextInt();
                status = choiceStatus(statusNumber);

                tasksService.changeTask(id, name, description, executor, status);
                break;
            default:
                throw new Exception("Введите число от 1 до 5 включительно");

        }

    }

    /**
     * Удаление задачи
     * @param tasksService задачи
     * @throws Exception Нет такой задачи
     */
    public static void deleteTask(TasksService tasksService) throws Exception{
        showTasks(tasksService);
        System.out.println("Введите код задачи");
        int id = scanner.nextInt();
        tasksService.deleteTaskById(id);
    }

    /**
     * Определение статуса
     * @param statusNumber номер статуса от 1 до 3 включительно
     * @return статус
     * @throws Exception Введите чисто от 1 до 3 включительно
     */
    private static Status choiceStatus(int statusNumber) throws Exception {
        Status status;
        switch(statusNumber) {
            case 1:
                status = Status.DONE;
                break;
            case 2:
                status = Status.PROCESSING;
                break;
            case 3:
                status = Status.EXPIRED;
                break;
            default:
                throw new Exception("Введите число от 1 до 3 включительно");
        }
        return status;
    }

    /**
     * Выводит задачи
     * @param tasksService задачи
     */
    public static void showTasks(TasksService tasksService) {
        System.out.println("Вывожу задачи:");
        System.out.println(tasksService.toString());
    }


}
