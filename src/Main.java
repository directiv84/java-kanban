import TaskTracker.*;

public class Main {
    public static void main(String[] args) {
        //внутреннее тестирование
        int cnt = 55;
        TaskManager manager = new TaskManager();

        System.out.println("-".repeat(cnt));
        //Добавляем исходные данные
        int t1 = manager.addNewTask(new Task("Задача 1", "Описание задачи 1", Status.NEW));
        //assert t1 ==1 : "Вернулся некоректный id: ожидался 1, вернулся - " + t1;
        int t2 = manager.addNewTask(new Task("Задача 2", "Описание задачи 2", Status.NEW));
        int ep1 = manager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1", Status.NEW));
        int ep2 = manager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2", Status.NEW));
        int sbt1 = manager.addNewSubtask(new Subtask("Подзадача 1", "Описание подзадачи 1", Status.NEW, ep1));
        int sbt2 = manager.addNewSubtask(new Subtask("Подзадача 2", "Описание подзадачи 2", Status.DONE, ep1));
        int sbt3 = manager.addNewSubtask(new Subtask("Подзадача 3", "Описание подзадачи 3", Status.IN_PROGRESS, ep2));
        //Печатаем все списки
        System.out.println("Список задач:\n" + manager.getAllTasks());
        System.out.println("Список эпиков:\n" + manager.getAllEpics());
        System.out.println("Список подзадач:\n" + manager.getAllSubtasks());
        //Получаем, изменяем и обновляем подзадачу
        System.out.println("-".repeat(cnt) + "\n" + "В подзадаче с id = " + sbt1 + " изменили статус на DONE \n" + "-".repeat(cnt));
        Subtask subtask = manager.getSubtask(sbt1);
        subtask.setDescription("New description fot subtask id = " + sbt1);
        subtask.setStatus(Status.DONE);
        manager.updSubtask(subtask);
        //Проверяем, что данные изменились и в подзадаче, и в эпике (статус)
        System.out.println("Эпик с id = " + ep1 + ":\n" + manager.getEpic(ep1));
        System.out.println("Подзадача с id = " + sbt1 + ":\n" + manager.getSubtask(sbt1));
        //Удаляем подзадачу и снова проверяем измнения в в подзадачах и в эпиках
        System.out.println("-".repeat(cnt) + "\n" + "Удалили подзадачу с id = " + sbt3 + " \n" + "-".repeat(cnt));
        manager.delSubtask(sbt3);
        System.out.println("Эпик с id = " + ep2 + ":\n" + manager.getEpic(ep2));
        System.out.println("Подзадача с id = " + sbt3 + ":\n" + manager.getSubtask(sbt3));
        System.out.println("-".repeat(cnt));
    }
}
