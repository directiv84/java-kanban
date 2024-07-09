package ru.yandex.javacource.fetisov.schedule.task;

import java.util.Objects;

public class Task {
    protected int id = 0;
    protected String name;
    protected String description;
    protected Status status;

    public Task(String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return getId() == task.getId() &&
                Objects.equals(getName(), task.getName()) &&
                Objects.equals(getDescription(), task.getDescription()) &&
                getStatus() == task.getStatus();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 7 * result + Objects.hashCode(getName());
        result = 17 * result + Objects.hashCode(getDescription());
        result = 31 * result + Objects.hashCode(getStatus());
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                "}\n";
    }
}