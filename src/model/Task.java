package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task model class representing a todo task.
 * Contains all task properties and business logic.
 * 
 * @author Kelompok 3 - Todo App
 * @version 1.0
 */
public class Task {
    // Status constants
    public static final String STATUS_PENDING = "Pending";
    public static final String STATUS_IN_PROGRESS = "In Progress";
    public static final String STATUS_COMPLETED = "Completed";
    public static final String STATUS_CANCELLED = "Cancelled";
    
    // Priority constants
    public static final String PRIORITY_LOW = "Low";
    public static final String PRIORITY_MEDIUM = "Medium";
    public static final String PRIORITY_HIGH = "High";
    
    // Task properties
    private String id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String status;
    private String priority;
    private String category;
    private LocalDateTime createdAt;
    
    /**
     * Constructor for creating a new task.
     * 
     * @param title Task title
     * @param description Task description
     * @param deadline Task deadline
     * @param priority Task priority
     * @param category Task category
     */
    public Task(String title, String description, LocalDateTime deadline,
                String priority, String category) {
        this.id = java.util.UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.category = category;
        this.status = STATUS_PENDING;
        this.createdAt = LocalDateTime.now();
    }
    
    /**
     * Constructor for loading existing task from file.
     * 
     * @param id Task ID
     * @param title Task title
     * @param description Task description
     * @param deadline Task deadline
     * @param status Task status
     * @param priority Task priority
     * @param category Task category
     * @param createdAt Creation timestamp
     */
    public Task(String id, String title, String description, LocalDateTime deadline,
                String status, String priority, String category, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.status = status;
        this.priority = priority;
        this.category = category;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDateTime getDeadline() {
        return deadline;
    }
    
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getPriority() {
        return priority;
    }
    
    public void setPriority(String priority) {
        this.priority = priority;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    /**
     * Checks if task is overdue.
     * 
     * @return true if overdue, false otherwise
     */
    public boolean isOverdue() {
        return deadline.isBefore(LocalDateTime.now())
                && !status.equalsIgnoreCase(STATUS_COMPLETED);
    }
    
    /**
     * Converts task to CSV string format.
     * 
     * @return CSV string representation
     */
    public String toCsvString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%s,%s,\"%s\",%s,%s,%s,%s,%s",
                id,
                escapeForCsv(title),
                escapeForCsv(description),
                deadline.format(formatter),
                status,
                priority,
                category,
                createdAt.format(formatter));
    }
    
    /**
     * Escapes special characters for CSV format.
     * 
     * @param value String to escape
     * @return Escaped string
     */
    private String escapeForCsv(String value) {
        if (value == null) {
            return "";
        }
        // Replace quotes with double quotes
        return value.replace("\"", "\"\"");
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
