package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TaskManager {
    private int idNewTask;
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    public TaskManager() {
        idNewTask = 0;
    }

    public int addNewTask(Task task) {
        int id = getNewId();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public int addNewEpic(Epic epic) {
        int id = getNewId();
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    public Integer addNewSubtask(Subtask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        int id = getNewId();
        subtask.setId(id);
        subtasks.put(id, subtask);
        epic.addSubtaskId(subtask.getId());
        updateEpicStatus(epic);
        return id;
    }

    public void updateTask(Task task) {
        int id = task.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    public void updateEpic(Epic epic) {
        Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return;
        }
        savedEpic.setName(epic.getName());
        savedEpic.setDescription(epic.getDescription());
    }

    public void updateSubtask(Subtask subtask) {
        int id = subtask.getId();
        int epicId = subtask.getEpicId();
        Subtask savedSubtask = subtasks.get(id);
        if (savedSubtask == null) {
            return;
        }
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        subtasks.put(id, subtask);
        updateEpicStatus(epics.get(epicId));
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public void removeAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void removeAllSubtasks() {
        subtasks.clear();
        for (Epic ep : epics.values()) {
            ep.cleanSubtasksEpic();
            updateEpicStatus(ep);
        }
    }

    public void removeTask(int idTask) {
        tasks.remove(idTask);
    }

    public void removeSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtaskId(id);
        updateEpicStatus(epic);
    }

    public void removeEpic(int idTask) {
        if (epics.containsKey(idTask)) {
            ArrayList<Integer> subtasksEpic = epics.get(idTask).getSubtasks();

            for (int i = 0; i < subtasksEpic.size(); i++) {
                subtasks.remove(i);
            }
            epics.remove(idTask);
        }
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Subtask getSubtask(int id) {
        return subtasks.get(id);
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public ArrayList<Subtask> getSubtasksByIdEpic(int idEpic) {
        Epic epic = epics.get(idEpic);
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Integer num : epic.getSubtasks()) subtasksList.add(subtasks.get(num));
        return subtasksList;
    }

    private int getNewId() {
        return ++idNewTask;
    }

    private void updateEpicStatus(Epic epic) {
        ArrayList<Integer> epicSubtasks = epic.getSubtasks();
        HashSet<Status> subtaskStatuses = new HashSet<>();

        for (int id : epicSubtasks) {
            subtaskStatuses.add(subtasks.get(id).getStatus());
        }

        if ((subtaskStatuses.isEmpty()) || (((subtaskStatuses.size() == 1)) && (subtaskStatuses.contains(Status.NEW)))) {
            epic.setStatus(Status.NEW);
        } else if (((subtaskStatuses.size() == 1)) && (subtaskStatuses.contains(Status.DONE))) {
            epic.setStatus(Status.DONE);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}