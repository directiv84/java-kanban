package ru.yandex.javacource.fetisov.schedule.test.manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.yandex.javacource.fetisov.schedule.manager.Managers;

public class ManagersTest {

    @Test
    void shouldReturnInitializedInMemoryTaskManager() {
        assertNotNull(Managers.getDefault().getAllTasks());
    }

    @Test
    void shouldReturnInitializedInMemoryHistoryManager() {
        assertNotNull(Managers.getDefaultHistory().getHistory());
    }
}
