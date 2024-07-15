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
        //Добавляем исходные данные
        int t1 = manager.addNewTask(new Task("Задача 1", "Описание задачи 1", Status.NEW));
        Task tp = manager.getTask(t1);
        tp.setName("Задача 1 нью");
        manager.updateTask(tp);
        manager.getTask(t1);
        int t2 = manager.addNewTask(new Task("Задача 2", "Описание задачи 2", Status.NEW));
        int ep1 = manager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1", Status.NEW));
        int ep2 = manager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2", Status.NEW));
        int sbt1 = manager.addNewSubtask(new Subtask("Подзадача 1", "Описание подзадачи 1", Status.NEW, ep1));
        int sbt2 = manager.addNewSubtask(new Subtask("Подзадача 2", "Описание подзадачи 2", Status.DONE, ep1));
        int sbt3 = manager.addNewSubtask(new Subtask("Подзадача 3", "Описание подзадачи 3", Status.IN_PROGRESS, ep2));
        //Печатаем все списки
        printAllTasks(manager);
        //Получаем, изменяем и обновляем подзадачу
        System.out.println("-".repeat(cnt) + "\n" + "В подзадаче с id = " + sbt1 + " изменили статус на DONE \n" + "-".repeat(cnt));
        Subtask subtask = manager.getSubtask(sbt1);
        subtask.setDescription("New description fot subtask id = " + sbt1);
        subtask.setStatus(Status.DONE);
        manager.updateSubtask(subtask);
        manager.getSubtask(sbt1);
        //Получаем данные по измененным эпику и подзадаче для истории
        Epic epic1 =  manager.getEpic(ep1);
        Subtask subtask1 = manager.getSubtask(sbt1);
        //Удаляем подзадачу и снова получаем данные по измененным эпику и подзадаче
        System.out.println("-".repeat(cnt) + "\n" + "Удалили подзадачу с id = " + sbt3 + " \n" + "-".repeat(cnt));
        Epic epic02 =  manager.getEpic(ep2);
        epic02.setName("New name epic 2");
        manager.updateEpic(epic02);
        Subtask subtask02 = manager.getSubtask(sbt3);
        manager.removeSubtask(sbt3);
        Epic epic2 =  manager.getEpic(ep2);
        printAllTasks(manager);
        System.out.println("-".repeat(cnt));
    }

    private static void printAllTasks(TaskManager manager) {
        System.out.println("Задачи:");
        for (Task task : manager.getAllTasks()) {
            System.out.println(task);
        }
        System.out.println("Эпики:");
        for (Task epic : manager.getAllEpics()) {
            System.out.println(epic);
        }
        System.out.println("Подзадачи:");
        for (Task subtask : manager.getAllSubtasks()) {
            System.out.println(subtask);
        }

        System.out.println("История:");
        for (Task task : manager.getHistory()) {
            System.out.println(task);
        }
    }
}
