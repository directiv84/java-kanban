package ru.yandex.javacource.fetisov.schedule.test.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.fetisov.schedule.manager.InMemoryHistoryManager;
import ru.yandex.javacource.fetisov.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.fetisov.schedule.task.Epic;
import ru.yandex.javacource.fetisov.schedule.task.Status;
import ru.yandex.javacource.fetisov.schedule.task.Subtask;
import ru.yandex.javacource.fetisov.schedule.task.Task;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    InMemoryHistoryManager historyManager;
    InMemoryTaskManager taskManager;
    Task task1;
    Task task2;
    Epic epic1;
    Epic epic2;
    Subtask subtask1;
    Subtask subtask2;
    Subtask subtask3;

    @BeforeEach
    void BeforeEach() {
        historyManager = new InMemoryHistoryManager();
        taskManager  = new InMemoryTaskManager();
        task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);
        task2 = new Task("Задача 2", "Описание задачи 2", Status.NEW);
        epic1 = new Epic("Эпик 1", "Описание эпика 1", Status.NEW);
        epic2 = new Epic("Эпик 2", "Описание эпика 2", Status.NEW);
        subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", Status.NEW, 1);
        subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", Status.NEW, 1);
        subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", Status.NEW, 2);
    }

    @Test
    void shouldReturn0IfTasksIsEmpty() {
        assertEquals(0, taskManager.getAllTasks().size());
    }

    @Test
    void shouldReturn1IfAddOneTask() {
        taskManager.addNewTask(task1);
        assertEquals(1, taskManager.getAllTasks().size());
    }
    //shouldReturnNullForDrone
//
    @Test
    void shouldReturn1IfAddOneEpic() {
        taskManager.addNewEpic(epic1);
        assertEquals(1, taskManager.getAllTasks().size());
    }
//
//    @Test
//    void addNewSubtask() {
//    }
//
//    @Test
//    void updateTask() {
//    }
//
//    @Test
//    void updateEpic() {
//    }
//
//    @Test
//    void updateSubtask() {
//    }
//
//    @Test
//    void removeAllTasks() {
//    }
//
//    @Test
//    void removeAllEpics() {
//    }
//
//    @Test
//    void removeAllSubtasks() {
//    }
//
//    @Test
//    void removeTask() {
//    }
//
//    @Test
//    void removeSubtask() {
//    }
//
//    @Test
//    void removeEpic() {
//    }
//
//    @Test
//    void getAllTasks() {
//    }
//
//    @Test
//    void getAllSubtasks() {
//    }
//
//    @Test
//    void getAllEpics() {
//    }
//
//    @Test
//    void getTask() {
//    }
//
//    @Test
//    void getSubtask() {
//    }
//
//    @Test
//    void getEpic() {
//    }
//
//    @Test
//    void getSubtasksByIdEpic() {
//    }
//
//    @Test
//    void getNewId() {
//    }
//
//    @Test
//    void updateEpicStatus() {
//    }
//
//    @Test
//    void getHistory() {
//    }
}