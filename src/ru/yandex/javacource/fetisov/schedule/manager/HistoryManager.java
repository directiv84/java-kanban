package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.Task;
import java.util.List;

public interface HistoryManager {
    <T extends Task> void add(T task);

    List<Task> getHistory();
}