package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private final int historyCapacity = 10;
    private final List<Task> viewsHistory = new ArrayList<>(historyCapacity);

    /**
    * В ТЗ явно не указаны действия с историей при удалении задач,
    * поэтому реализовал с отображенеим null при запросе истории.
    */
    @Override
    public void add(Task task) {
        if (viewsHistory.size() == historyCapacity) {
            viewsHistory.removeFirst();
        }
        viewsHistory.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return viewsHistory;
    }
}
