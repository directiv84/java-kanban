package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>();
    public static final int MAX_SIZE = 10;

    @Override
    public void add(Task task) {
        if (task != null) {
            if (history.size() == MAX_SIZE) {
                history.removeFirst();
            }
            history.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}
