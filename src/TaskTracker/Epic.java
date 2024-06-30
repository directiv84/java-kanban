package TaskTracker;

import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Integer> epicSubtasks;

    public Epic(String name, String description, Status status) {
        super(name, description, status);
        epicSubtasks = new ArrayList<>();
    }

    protected ArrayList<Integer> getSubtasks() {
        return epicSubtasks;
    }

    protected void addSubtask(int id) {
        epicSubtasks.add(id);
    }

    protected void delSubtask(Integer id) {
        if (epicSubtasks.contains(id)) {
            epicSubtasks.remove(id);
        }
    }

    public void cleanSubtasksEpic() {
        this.epicSubtasks = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", epicSubtasks=" + epicSubtasks +
                "}\n";
    }
}