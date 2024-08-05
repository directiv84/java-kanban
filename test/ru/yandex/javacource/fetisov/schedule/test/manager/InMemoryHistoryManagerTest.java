package ru.yandex.javacource.fetisov.schedule.test.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

    @DisplayName("New history is empty")
    @Test
    void shouldBeEmptyHistoryBeforeAddTasks() {
        assertEquals(0, historyManager.getHistory().size());
    }

    @DisplayName("Add task to history")
    @Test
    void shouldIncreaseHistoryAfterAddTask() {
        int sizeBeforeAdd = historyManager.getHistory().size();
        historyManager.add(task1);
        int sizeAfterAdd = historyManager.getHistory().size();
        assertEquals(++sizeBeforeAdd, sizeAfterAdd);
    }

    @DisplayName("Remove task from history")
    @Test
    public void removeTaskTest() {
        historyManager.add(task1);
        int sizeBeforeRemove = historyManager.getHistory().size();
        historyManager.remove(task1.getId());
        int sizeAfterRemove = historyManager.getHistory().size();
        assertEquals(--sizeBeforeRemove, sizeAfterRemove, "Task didn't remove from history");
    }

    @DisplayName("Add duplicate to history")
    @Test
    public void shouldNotIncreaseIfAddDuplicate() {
        historyManager.add(task1);
        historyManager.add(task1);
        assertEquals(1, historyManager.getHistory().size(), "Tasks with same id duplicated in history");
    }
}