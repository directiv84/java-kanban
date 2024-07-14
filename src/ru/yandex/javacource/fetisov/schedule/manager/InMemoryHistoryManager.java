package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> viewsHistory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            int historyCapacity = 10;
            if (viewsHistory.size() == historyCapacity) {
                viewsHistory.removeFirst();
            }
            Task taskHistory = task.newInstance(task);
            viewsHistory.add(taskHistory);
        }
    }

    @Override
    public List<Task> getHistory() {
        return viewsHistory;
    }
}
