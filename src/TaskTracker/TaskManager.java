package TaskTracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TaskManager {
    private static int idNewTask;
    private static final HashMap<Integer, Task> tasks = new HashMap<>();
    private static final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private static final HashMap<Integer, Epic> epics = new HashMap<>();

    public TaskManager() {
        idNewTask = 0;
    }

    private static int getNewId() {
        return ++idNewTask;
    }

    private void updEpicStatus(Epic epic) {
        ArrayList<Integer> epicSubtasks = epic.getSubtasks();
        HashSet<Status> subtaskStatuses = new HashSet<>();

        for (int i = 0; i < epicSubtasks.size(); i++) {
            int id = epicSubtasks.get(i);
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

    public void updTask(Task task) {
        int id = task.getId();
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    public void updEpic(Epic epic) {
        int id = epic.getId();
        if (epics.containsKey(id)) {
            epics.put(id, epic);
            updEpicStatus(epic);
        }
    }

    public void updSubtask(Subtask subtask) {
        int id = subtask.getId();
        if (subtasks.containsKey(id)) {
            subtasks.put(id, subtask);
            int epicId = subtask.getEpicId();
            updEpicStatus(epics.get(epicId));
        }
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
        updEpicStatus(epic);
        return id;
    }

    public int addNewSubtask(Subtask subtask) {
        int idSubtask = getNewId();
        subtask.setId(idSubtask);
        int idEpic = subtask.getEpicId();

        if (epics.containsKey(idEpic))  {
            subtasks.put(idSubtask, subtask);
            epics.get(idEpic).addSubtask(idSubtask);
            updEpicStatus(epics.get(idEpic));
        } else {
            return 0;
        }

        return idSubtask;
    }

    public void delAllTasks() {
        tasks.clear();
    }

    public void delAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void delAllSubtasks() {
        subtasks.clear();
        for (Epic ep : epics.values()) {
            ep.cleanSubtasksEpic();
            updEpicStatus(ep);
        }
    }

    public void delTask(int idTask) {
        tasks.remove(idTask);
    }

    public void delSubtask(int id) {
        if (subtasks.containsKey(id)) {
            //deleting subtask from epic
            int epicId = subtasks.get(id).getEpicId();
            Epic epic = epics.get(epicId);
            epic.delSubtask(id);
            //deleting subtask
            subtasks.remove(id);
            updEpicStatus(epic);
        }
    }

    public void delEpic(int idTask) {
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
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        return null;
    }

    public Subtask getSubtask(int id) {
        if (subtasks.containsKey(id)) {
            return subtasks.get(id);
        }
        return null;
    }

    public Epic getEpic(int id) {
        if (epics.containsKey(id)) {
            return epics.get(id);
        }
        return null;
    }

    public ArrayList<Integer> getSubtasksByIdEpic (int idEpic) {
        if (epics.containsKey(idEpic)) {
            return epics.get(idEpic).getSubtasks();
        }
        return null;
    }
}