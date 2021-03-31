import ru.croc.task.menu.Menu;
import ru.croc.task.task.Tasks;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String [] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Menu.Greeting();
        File f = new File("db.txt");
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        Tasks tasks = new Tasks();
        try {
            if (f.length() == 0) {
                f.createNewFile();
            }
            else {
                fileInputStream = new FileInputStream("db.txt");
                objectInputStream = new ObjectInputStream(fileInputStream);
                tasks = (Tasks) objectInputStream.readObject();
            }
        } catch(IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
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
                        Menu.deleteTask(tasks);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                }
                    break;
                case 2:
                    Menu.showTasks(tasks);
                    break;
                case 3:
                    try {
                        Menu.changeTask(tasks);
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        Menu.addTask(tasks);
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
        objectOutputStream.writeObject(tasks);

    }
}
