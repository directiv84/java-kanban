package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.Task;
import java.util.List;

public interface HistoryManager {
    void add(Task task);
    void remove(int id);
    List<Task> getHistory();

}