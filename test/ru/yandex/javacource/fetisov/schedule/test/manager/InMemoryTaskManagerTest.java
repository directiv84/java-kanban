package ru.yandex.javacource.fetisov.schedule.test.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.javacource.fetisov.schedule.manager.InMemoryHistoryManager;
import ru.yandex.javacource.fetisov.schedule.manager.InMemoryTaskManager;
import ru.yandex.javacource.fetisov.schedule.task.Epic;
import ru.yandex.javacource.fetisov.schedule.task.Status;
import ru.yandex.javacource.fetisov.schedule.task.Subtask;
import ru.yandex.javacource.fetisov.schedule.task.Task;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    InMemoryHistoryManager historyManager;
    InMemoryTaskManager taskManager;
    Task task1 = new Task("Задача 1", "Описание задачи 1", Status.NEW);
    Task task2 = new Task("Задача 2", "Описание задачи 2", Status.NEW);
    Epic epic1 = new Epic("Эпик 1", "Описание эпика 1", Status.NEW);
    Epic epic2 = new Epic("Эпик 2", "Описание эпика 2", Status.NEW);
    Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", Status.NEW, 1);
    Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", Status.NEW, 1);
    Subtask subtask3 = new Subtask("Подзадача 3", "Описание подзадачи 3", Status.NEW, 2);

    @BeforeEach
    void BeforeEach() {
        historyManager = new InMemoryHistoryManager();
        taskManager  = new InMemoryTaskManager();
    }

    /**
     * Не понятны требования для некоторых проверок:
     * 1. "проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;"
     * 2. "проверьте, что объект Subtask нельзя сделать своим же эпиком"
     * 3. "проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;"
     * --
     * 1. В эпик в списке подзадач мы храним не объекты класса Эпик, а их номера
     * 2. Аналогично п.1
     * Единственная возможность в существующей архитектуре получить для реализации сценариев пп.1-2 -
     * получить при обновлении некорректные значения, но о необходимости валидации значений при обновлении нигде не
     * упомяналось.
     * 3. Id нигде в логике не задаются, они только генерятся. Или речь про валидацию данных при обновлени?
     */

    @Test
    void shouldBeEqualsTasksIfTheirIdsEquals() {
        int id = taskManager.addNewTask(task1);
        Task t2 = taskManager.getTask(id);
        assertEquals(task1, t2, "Tasks with equals ids not equals");
    }

    @Test
    void shouldBeEqualsEpicsIfTheirIdsEquals() {
        int id = taskManager.addNewEpic(epic1);
        Epic ep2 = taskManager.getEpic(id);
        assertEquals(epic1, ep2, "Epics with equals ids not equals");
    }

    @Test
    void shouldBeEqualsSubtasksIfTheirIdsEquals() {
        taskManager.addNewEpic(epic1);
        int id = taskManager.addNewSubtask(subtask1);
        Subtask st2 = taskManager.getSubtask(id);
        assertEquals(subtask1, st2, "Subtasks with equals ids not equals");
    }

    @Test
    void shouldAddNewTaskAndGetTaskById() {
        int id = taskManager.addNewTask(task1);
        Task task = taskManager.getTask(id);
        assertNotNull(task, "New task didn't create");
        assertEquals(task1, task, "Task not equals after and before addNewTask");
    }

    @Test
    void shouldAddNewEpicAndGetEpicById() {
        int id = taskManager.addNewEpic(epic1);
        Epic epic = taskManager.getEpic(id);
        assertNotNull(epic, "New epic didn't create");
        assertEquals(epic1, epic, "Epic not equals after and before addNewEpic");
    }

    @Test
    void shouldAddNewSubtaskAndGetSubtaskById() {
        taskManager.addNewEpic(epic1);
        int id = taskManager.addNewSubtask(subtask1);
        Subtask subtask = taskManager.getSubtask(id);
        assertNotNull(subtask, "New subtask didn't create");
        assertEquals(subtask1, subtask, "Subtask not equals after and before addNewSubtask");
    }

    @Test
    void shouldBeSavedValuesOfFieldsAfterAddToManager() {
        int id = taskManager.addNewTask(task1);
        Task task2 = taskManager.getTask(id);
        assertEquals(task1.getName(), task2.getName(), "Names not equals before and after addNewTask");
        assertEquals(task1.getDescription(), task2.getDescription(), "Descriptions not equals before and after addNewTask");
        assertEquals(task1.getStatus(), task2.getStatus(), "Statuses not equals before and after addNewTask");
    }

    @Test
    void shouldBeSavedValuesOfFieldsTaskInHistoryAfterUpdateTask() {
        int id = taskManager.addNewTask(task1);
        taskManager.getTask(id);
        task1.setName("New name task 1");
        assertNotEquals(historyManager.getHistory(), List.of(task1), "History changes with task");
    }

    @Test
    void shouldRemoveTaskById() {
        int id = taskManager.addNewTask(task1);
        Task task = taskManager.getTask(id);
        assertNotNull(task, "New task didn't create");
        taskManager.removeTask(id);
        task = taskManager.getTask(id);
        assertNull(task, "Task didn't remove");
    }

    @Test
    void shouldRemoveEpicById() {
        int id = taskManager.addNewEpic(epic1);
        Epic epic = taskManager.getEpic(id);
        assertNotNull(epic, "New epic didn't create");
        taskManager.removeEpic(id);
        epic = taskManager.getEpic(id);
        assertNull(epic, "Epic didn't remove");
    }

    @Test
    void shouldRemoveSubtaskById() {
        taskManager.addNewEpic(epic1);
        int id = taskManager.addNewSubtask(subtask1);
        Subtask subtask = taskManager.getSubtask(id);
        assertNotNull(subtask, "New subtask didn't create");
        taskManager.removeSubtask(id);
        subtask = taskManager.getSubtask(id);
        assertNull(subtask, "Subtask didn't remove");
    }

    @Test
    void shouldChangeEpicsStatusAfterChangeSubtaskStatus() {
        int id = taskManager.addNewEpic(epic1);
        int idSubtask = taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);
        Status oldStatus = taskManager.getEpic(id).getStatus();
        Subtask savedSubtask = taskManager.getSubtask(idSubtask);
        savedSubtask.setStatus(Status.DONE);
        taskManager.updateSubtask(savedSubtask);
        assertNotEquals(oldStatus, taskManager.getEpic(id).getStatus(), "Epic's status didn't change after change subtask's status");
    }

}