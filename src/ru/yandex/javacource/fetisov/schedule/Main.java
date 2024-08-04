package ru.yandex.javacource.fetisov.schedule;

import ru.yandex.javacource.fetisov.schedule.manager.*;
import ru.yandex.javacource.fetisov.schedule.task.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        //внутреннее тестирование
        int cnt = 55;
        TaskManager manager = Managers.getDefault();

        System.out.println("-".repeat(cnt));
        //Создайте две задачи, эпик с тремя подзадачами и эпик без подзадач.
        int t1 = manager.addNewTask(new Task("Задача 1", "Описание задачи 1", Status.NEW));
        int t2 = manager.addNewTask(new Task("Задача 2", "Описание задачи 2", Status.NEW));
        int ep1 = manager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1", Status.NEW));
        int ep2 = manager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2", Status.NEW));
        int sbt1 = manager.addNewSubtask(new Subtask("Подзадача 1", "Описание подзадачи 1", Status.NEW, ep1));
        int sbt2 = manager.addNewSubtask(new Subtask("Подзадача 2", "Описание подзадачи 2", Status.DONE, ep1));
        int sbt3 = manager.addNewSubtask(new Subtask("Подзадача 3", "Описание подзадачи 3", Status.IN_PROGRESS, ep1));

        //Запросите созданные задачи несколько раз в разном порядке.
        //После каждого запроса выведите историю и убедитесь, что в ней нет повторов
        System.out.println("Получили таску с id" + t1);
        manager.getTask(t1);
        printHistory(manager);
        //
        System.out.println("Получили эпик с id" + ep2);
        manager.getEpic(ep2);
        printHistory(manager);
        //
        System.out.println("Получили сабтаску с id" + sbt1);
        manager.getSubtask(sbt1);
        printHistory(manager);
        //
        System.out.println("Получили сабтаску с id" + sbt2);
        manager.getSubtask(sbt2);
        printHistory(manager);
        //
        System.out.println("Получили сабтаску с id" + sbt3);
        manager.getSubtask(sbt3);
        printHistory(manager);
        //
        System.out.println("Получили сабтаску с id" + sbt1);
        manager.getSubtask(sbt1);
        printHistory(manager);
        //
        System.out.println("Получили сабтаску с id" + sbt2);
        manager.getSubtask(sbt2);
        printHistory(manager);
        //
        System.out.println("Получили эпик с id" + ep2);
        manager.getEpic(ep2);
        printHistory(manager);
        //
        System.out.println("Получили таску с id" + t1);
        manager.getTask(t1);
        printHistory(manager);
        //
        System.out.println("Получили таску с id" + t2);
        manager.getTask(t2);
        printHistory(manager);
        //
        System.out.println("Получили эпик с id" + ep1);
        manager.getEpic(ep1);
        printHistory(manager);
        //Удалите задачу, которая есть в истории, и проверьте, что при печати она не будет выводиться
        System.out.println("Удалили задачу с id" + t2 + "\n");
        manager.removeTask(t2);
        printHistory(manager);
        //Удалите эпик с тремя подзадачами и убедитесь, что из истории удалился как сам эпик, так и все его подзадачи.
        List<Subtask> ids = manager.getSubtasksByIdEpic(ep1);
        System.out.print("Удалили эпик с id" + ep1 + ", содержащий сабтаски с id ");
        for (int i = 0; i < ids.size(); i++) {
            if (i < ids.size() - 1) {
                System.out.print(ids.get(i).getId() + ",");
            } else {
                System.out.println(ids.get(i).getId() + "\n");
            }
        }
        manager.removeEpic(ep1);
        printHistory(manager);
    }

    private static void printHistory(TaskManager manager) {
        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
