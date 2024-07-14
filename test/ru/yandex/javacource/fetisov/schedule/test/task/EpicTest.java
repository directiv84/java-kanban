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
    void shouldIncreaseNumberOfEpicSubtasksAfterAddSubtask() {
        int sizeBeforeAdd = epic.getSubtasks().size();
        epic.addSubtaskId(3);
        int sizeAfterAdd = epic.getSubtasks().size();
        assertEquals(++sizeBeforeAdd, sizeAfterAdd, "Array of epic's subtasks didn't increase after add new subtask");
    }

    @Test
    void shouldDecreaseNumberOfEpicSubtasksAfterRemoveSubtask() {
        epic.addSubtaskId(3);
        int sizeBeforeRemove = epic.getSubtasks().size();
        epic.removeSubtaskId(3);
        int sizeAfterRemove = epic.getSubtasks().size();
        assertEquals(--sizeBeforeRemove, sizeAfterRemove, "Array of epic's subtasks didn't decrease after add new subtask");
    }
}