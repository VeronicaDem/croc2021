import ru.croc.task.models.menu.Menu;
import ru.croc.task.service.TasksService;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String [] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Menu.Greeting();
        File f = new File("db.txt");
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        TasksService tasksService = new TasksService();
        try {
            if (f.length() == 0) {
                f.createNewFile();
            }
            else {
                fileInputStream = new FileInputStream("db.txt");
                objectInputStream = new ObjectInputStream(fileInputStream);
                tasksService = (TasksService) objectInputStream.readObject();
            }
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Нажмите 1 для старта");
        int start = scanner.nextInt();
        while (start == 1) {
            System.out.println("1 - Удалить задачу по индексу\n" +
                               "2 - Просмотреть задачи\n" +
                               "3 - Редактировать задачу\n" +
                               "4 - Добавить задачу" );
            int choice = scanner.nextInt();
            switch(choice) {
                case 1:
                    try {
                        Menu.deleteTask(tasksService);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                }
                    break;
                case 2:
                    Menu.showTasks(tasksService);
                    break;
                case 3:
                    try {
                        Menu.changeTask(tasksService);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        Menu.addTask(tasksService);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Введите номер от 1 до 4");

            }

            System.out.println("Нажмите 1 для продолжения");
            start = scanner.nextInt();
        }
        FileOutputStream fileOutput;
        fileOutput = new FileOutputStream("db.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput);
        objectOutputStream.writeObject(tasksService);

    }
}
