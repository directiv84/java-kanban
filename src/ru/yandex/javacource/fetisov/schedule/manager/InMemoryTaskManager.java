package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.*;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private int idNewTask = 0;
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();

    private final HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public int addNewTask(Task task) {
        int id = getNewId();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    @Override
    public int addNewEpic(Epic epic) {
        int id = getNewId();
        epic.setId(id);
        epics.put(id, epic);
        return id;
    }

    @Override
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

    @Override
    public void updateTask(Task task) {
        int id = task.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        final Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return;
        }
        epic.setSubtasksIds(savedEpic.getSubtasksIds());
        epic.setStatus(savedEpic.getStatus());
        epics.put(epic.getId(), epic);
    }

    @Override
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

    @Override
    public void removeAllTasks() {
        for (Integer id : tasks.keySet()) {
            historyManager.remove(id);
        }
        tasks.clear();
    }

    @Override
    public void removeAllEpics() {
        for (Integer id : subtasks.keySet()) {
            historyManager.remove(id);
        }
        subtasks.clear();

        for (Integer id : epics.keySet()) {
            historyManager.remove(id);
        }
        epics.clear();
    }

    @Override
    public void removeAllSubtasks() {
        for (Integer id : subtasks.keySet()) {
            historyManager.remove(id);
        }
        subtasks.clear();

        for (Epic ep : epics.values()) {
            ep.cleanSubtasksEpic();
            updateEpicStatus(ep);
        }
    }

    @Override
    public void removeTask(int idTask) {
        tasks.remove(idTask);
        historyManager.remove(idTask);
    }

    @Override
    public void removeSubtask(int id) {
        Subtask subtask = subtasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtaskId(id);
        historyManager.remove(id);
        updateEpicStatus(epic);
    }

    @Override
    public void removeEpic(int idTask) {
        if (epics.containsKey(idTask)) {
            List<Integer> subtasksEpic = epics.get(idTask).getSubtasksIds();
            for (Integer id : subtasksEpic) {
                subtasks.remove(id);
                historyManager.remove(id);
            }
            epics.remove(idTask);
            historyManager.remove(idTask);

        }
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public Task getTask(int id) {
        Task task = tasks.get(id);
        historyManager.add(task);
        return task;
    }

    @Override
    public Subtask getSubtask(int id) {
        Subtask subtask = subtasks.get(id);
        historyManager.add(subtask);
        return subtask;
    }

    @Override
    public Epic getEpic(int id) {
        Epic epic = epics.get(id);
        historyManager.add(epic);
        return epic;
    }

    @Override
    public ArrayList<Subtask> getSubtasksByIdEpic(int idEpic) {
        Epic epic = epics.get(idEpic);
        ArrayList<Subtask> subtasksList = new ArrayList<>();
        for (Integer num : epic.getSubtasksIds()) {
            subtasksList.add(subtasks.get(num));
        }
        return subtasksList;
    }

    @Override
    public int getNewId() {
        return ++idNewTask;
    }

    @Override
    public void updateEpicStatus(Epic epic) {
        List<Integer> epicSubtasks = epic.getSubtasksIds();
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

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }
}