package ru.yandex.javacource.fetisov.schedule.test.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.yandex.javacource.fetisov.schedule.manager.InMemoryHistoryManager;
import ru.yandex.javacource.fetisov.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.fetisov.schedule.task.Status;
import ru.yandex.javacource.fetisov.schedule.task.Task;




public class InMemoryHistoryManagerTest {
    InMemoryHistoryManager historyManager;
    InMemoryTaskManager taskManager;
    Task task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);

    @BeforeEach
    void BeforeEach() {
        historyManager = new InMemoryHistoryManager();
        taskManager  = new InMemoryTaskManager();
    }

    @Test
    void shouldBeEmptyHistoryBeforeAddTasks() {
        assertEquals(0, historyManager.getHistory().size());
    }

    @Test
    void shouldIncreaseHistoryAfterAddTask() {
        int sizeBeforeAdd = historyManager.getHistory().size();
        historyManager.add(task1);
        int sizeAfterAdd = historyManager.getHistory().size();
        assertEquals(++sizeBeforeAdd, sizeAfterAdd);
    }
}