package ru.yandex.javacource.fetisov.schedule.task;

import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Integer> subtasksIds;

    public Epic(String name, String description, Status status) {
        super(name, description, status);
        subtasksIds = new ArrayList<>();
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasksIds;
    }

    public void addSubtaskId(int id) {
        subtasksIds.add(id);
    }

    public void removeSubtaskId(Integer id) {
        subtasksIds.remove(id);
    }

    public void cleanSubtasksEpic() {
        this.subtasksIds = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", subtasksIds=" + subtasksIds +
                "}\n";
    }
}