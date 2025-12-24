package manager;

import model.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskManager class for managing tasks.
 * Provides CRUD operations, filtering, searching, and statistics.
 * 
 * @author Kelompok 3 - Todo App
 * @version 1.0
 */
public class TaskManager {
    private List<Task> tasks;
    private FileManager fileManager;
    
    /**
     * Constructor to initialize TaskManager with data file.
     * 
     * @param dataFile Path to CSV data file
     */
    public TaskManager(String dataFile) {
        this.fileManager = new FileManager(dataFile);
        this.tasks = new ArrayList<>();
        loadTasks();
    }
    
    /**
     * Loads tasks from file.
     */
    private void loadTasks() {
        try {
            tasks = fileManager.loadFromFile();
        } catch (Exception e) {
            System.err.println("Error loading tasks: " + e.getMessage());
            tasks = new ArrayList<>();
        }
    }
    
    /**
     * Saves tasks to file.
     */
    private void saveTasks() {
        try {
            fileManager.saveToFile(tasks);
        } catch (Exception e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
    
    /**
     * Adds a new task.
     * 
     * @param task Task to add
     */
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }
    
    /**
     * Updates an existing task.
     * 
     * @param taskId ID of task to update
     * @param updatedTask Updated task object
     * @return true if successful, false otherwise
     */
    public boolean updateTask(String taskId, Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(taskId)) {
                tasks.set(i, updatedTask);
                saveTasks();
                return true;
            }
        }
        return false;
    }
    
    /**
     * Deletes a task.
     * 
     * @param taskId ID of task to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteTask(String taskId) {
        boolean removed = tasks.removeIf(task -> task.getId().equals(taskId));
        if (removed) {
            saveTasks();
        }
        return removed;
    }
    
    /**
     * Gets a task by ID.
     * 
     * @param taskId Task ID
     * @return Task object or null if not found
     */
    public Task getTask(String taskId) {
        return tasks.stream()
                .filter(task -> task.getId().equals(taskId))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Gets all tasks.
     * 
     * @return List of all tasks
     */
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }
    
    /**
     * Filters tasks by status.
     * 
     * @param status Status to filter by
     * @return Filtered list of tasks
     */
    public List<Task> filterByStatus(String status) {
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toList());
    }
    
    /**
     * Filters tasks by priority.
     * 
     * @param priority Priority to filter by
     * @return Filtered list of tasks
     */
    public List<Task> filterByPriority(String priority) {
        return tasks.stream()
                .filter(task -> task.getPriority().equals(priority))
                .collect(Collectors.toList());
    }
    
    /**
     * Filters tasks by category.
     * 
     * @param category Category to filter by
     * @return Filtered list of tasks
     */
    public List<Task> filterByCategory(String category) {
        return tasks.stream()
                .filter(task -> task.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    /**
     * Searches tasks by keyword in title or description.
     * 
     * @param keyword Search keyword
     * @return List of matching tasks
     */
    public List<Task> searchTasks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> 
                    task.getTitle().toLowerCase().contains(lowerKeyword) ||
                    task.getDescription().toLowerCase().contains(lowerKeyword)
                )
                .collect(Collectors.toList());
    }
    
    /**
     * Gets all unique categories.
     * 
     * @return List of unique categories
     */
    public List<String> getAllCategories() {
        return tasks.stream()
                .map(Task::getCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    /**
     * Gets statistics summary.
     * 
     * @return Statistics string
     */
    public String getStatistics() {
        long total = tasks.size();
        long pending = tasks.stream().filter(t -> t.getStatus().equals(Task.STATUS_PENDING)).count();
        long inProgress = tasks.stream().filter(t -> t.getStatus().equals(Task.STATUS_IN_PROGRESS)).count();
        long completed = tasks.stream().filter(t -> t.getStatus().equals(Task.STATUS_COMPLETED)).count();
        long overdue = tasks.stream().filter(Task::isOverdue).count();
        
        return String.format("Total: %d | Pending: %d | In Progress: %d | Completed: %d | Overdue: %d",
                total, pending, inProgress, completed, overdue);
    }
    
    /**
     * Changes task status.
     * 
     * @param taskId Task ID
     * @param newStatus New status
     * @return true if successful, false otherwise
     */
    public boolean changeTaskStatus(String taskId, String newStatus) {
        Task task = getTask(taskId);
        if (task != null) {
            task.setStatus(newStatus);
            saveTasks();
            return true;
        }
        return false;
    }
}
