package ru.yandex.javacource.fetisov.schedule.test.task;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.fetisov.schedule.task.Epic;
import ru.yandex.javacource.fetisov.schedule.task.Status;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicTest {

    Epic epic;

    @BeforeEach
    void BeforeEach() {
        epic = new Epic("Эпик 1", "Описание эпика 1", Status.NEW);
    }

    @DisplayName("Increase the numbers of epic's subtasks")
    @Test
    void shouldIncreaseNumberOfEpicSubtasksAfterAddSubtask() {
        int sizeBeforeAdd = epic.getSubtasksIds().size();
        epic.addSubtaskId(3);
        int sizeAfterAdd = epic.getSubtasksIds().size();
        assertEquals(++sizeBeforeAdd, sizeAfterAdd, "Array of epic's subtasks didn't increase after add new subtask");
    }

    @DisplayName("Decrease the numbers of epic's subtasks")
    @Test
    void shouldDecreaseNumberOfEpicSubtasksAfterRemoveSubtask() {
        epic.addSubtaskId(3);
        int sizeBeforeRemove = epic.getSubtasksIds().size();
        epic.removeSubtaskId(3);
        int sizeAfterRemove = epic.getSubtasksIds().size();
        assertEquals(--sizeBeforeRemove, sizeAfterRemove, "Array of epic's subtasks didn't decrease after add new subtask");
    }
}