package model;
import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private String id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String status;
    private String priority;
    private String category;
    private LocalDateTime createdAt;


    public Task(String title, String description, LocalDateTime deadline,
                String priority, String category) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
        this.category = category;
        this.status = "Pending";
        this.createdAt = LocalDateTime.now();
    }


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


    public boolean isOverdue() {
        return deadline.isBefore(LocalDateTime.now())
                && !status.equalsIgnoreCase("Completed");
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
