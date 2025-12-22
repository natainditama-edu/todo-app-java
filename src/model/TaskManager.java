package model;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private List<Task> taskList;


    public TaskManager() {
        taskList = new ArrayList<>();
    }


    public void addTask(Task task) {
        taskList.add(task);
    }


    public void updateTask(String taskId, Task updatedTask) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getId().equals(taskId)) {
                taskList.set(i, updatedTask);
                return;
            }
        }
    }


    public void deleteTask(String taskId) {
        taskList.removeIf(task -> task.getId().equals(taskId));
    }


    public Task getTask(String taskId) {
        for (Task task : taskList) {
            if (task.getId().equals(taskId)) {
                return task;
            }
        }
        return null;
    }


    public List<Task> getAllTasks() {
        return taskList;
    }
}
