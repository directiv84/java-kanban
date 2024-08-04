package ru.yandex.javacource.fetisov.schedule.manager;

import ru.yandex.javacource.fetisov.schedule.task.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private Node head;
    private Node tail;
    private final Map<Integer, Node> history = new HashMap<>();

    static class Node {
        public Task task;
        public Node next;
        public Node prev;

        public Node(Node prev, Task task, Node next) {
            this.task = task;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkLast(Task task) {

        final Node oldTail = tail;
        final Node newTail = new Node(oldTail, task, null);
        tail = newTail;

        if (oldTail == null) {
            head = newTail;
        } else {
            oldTail.next = newTail;
        }

    }

    private void removeNode(Node node) {
        if (node != null) {
            final Node next = node.next;
            final Node prev = node.prev;

            if (head == node && tail == node) {
                head = null;
                tail = null;
            } else if (head == node) {
                head = next;
                head.prev = null;
            } else if (tail == node) {
                tail = prev;
                tail.next = null;
            } else {
                prev.next = next;
                next.prev = prev;
            }
        }
    }

    private List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node node = head;
        while (node != null) {
            tasks.add(node.task);
            node = node.next;
        }
        return tasks;
    }

    @Override
    public void add(Task task) {
        if (task != null) {
            remove(task.getId());
            linkLast(task);
            history.put(task.getId(), tail);
        }
    }

    @Override
    public void remove(int id) {
        final Node node = history.remove(id);
        if (node == null) {
            return;
        }
        removeNode(node);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

}
