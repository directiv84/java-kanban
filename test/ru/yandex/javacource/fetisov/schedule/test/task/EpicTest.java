package ru.yandex.javacource.fetisov.schedule.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.fetisov.schedule.task.Epic;
import ru.yandex.javacource.fetisov.schedule.task.Status;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {

    Epic epic;

    @BeforeEach
    void BeforeEach() {
        epic = new Epic("Эпик 1", "Описание эпика 1", Status.NEW);
    }

    @Test
    void shouldReturn1AfterAddOneSubtaskId() {
        epic.addSubtaskId(3);
        assertEquals(1, epic.getSubtasks().size());
    }

    @Test
    void removeSubtaskId() {
        epic.addSubtaskId(3);
        int cntBeforeRemove = epic.getSubtasks().size();
        epic.removeSubtaskId(3);
        int cntAfterRemove = epic.getSubtasks().size();
        assertEquals(cntBeforeRemove-1, cntAfterRemove);
    }
}