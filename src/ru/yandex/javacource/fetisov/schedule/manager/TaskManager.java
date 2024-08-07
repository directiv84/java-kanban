package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.Epic;
import ru.yandex.javacource.fetisov.schedule.task.Subtask;
import ru.yandex.javacource.fetisov.schedule.task.Task;

import java.util.List;

public interface TaskManager {
    int addNewTask(Task task);

    int addNewEpic(Epic epic);

    Integer addNewSubtask(Subtask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    void removeAllTasks();

    void removeAllEpics();

    void removeAllSubtasks();

    void removeTask(int idTask);

    void removeSubtask(int id);

    void removeEpic(int idTask);

    List<Task> getAllTasks();

    List<Subtask> getAllSubtasks();

    List<Epic> getAllEpics();

    Task getTask(int id);

    Subtask getSubtask(int id);

    Epic getEpic(int id);

    List<Subtask> getSubtasksByIdEpic(int idEpic);

    int getNewId();

    void updateEpicStatus(Epic epic);

    List<Task> getHistory();
}
