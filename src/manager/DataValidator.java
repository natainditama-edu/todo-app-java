package manager;

import model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class DataValidator {

    // Valid values untuk status, priority, dan category
    private static final List<String> VALID_STATUSES = Arrays.asList("TODO", "IN_PROGRESS", "COMPLETED");
    private static final List<String> VALID_PRIORITIES = Arrays.asList("LOW", "MEDIUM", "HIGH");
    private static final List<String> VALID_CATEGORIES = Arrays.asList(
            "Personal", "Academic", "Development", "Testing", "Project",
            "Health", "Finance", "Deployment", "Work", "Other"
    );

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static boolean isValidTask(Task task) {
        if (task == null) {
            return false;
        }

        return isValidId(task.getId()) &&
                isValidTitle(task.getTitle()) &&
                isValidDescription(task.getDescription()) &&
                isValidStatus(task.getStatus()) &&
                isValidPriority(task.getPriority()) &&
                isValidCategory(task.getCategory());
    }

    public static boolean isValidId(String id) {
        return id != null && !id.trim().isEmpty();
    }

    public static boolean isValidTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            return false;
        }

        int length = title.trim().length();
        return length >= 3 && length <= 100;
    }

    public static boolean isValidDescription(String description) {
        if (description == null) {
            return false;
        }

        return description.length() <= 500;
    }

    public static boolean isValidDeadline(String deadline) {
        if (deadline == null || deadline.trim().isEmpty()) {
            return true; // Deadline is optional
        }

        try {
            // Try date format first
            LocalDate.parse(deadline, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            try {
                // Try datetime format
                LocalDate.parse(deadline.split(" ")[0], DATE_FORMATTER);
                return true;
            } catch (DateTimeParseException ex) {
                return false;
            }
        }
    }

    public static boolean isValidStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            return false;
        }

        return VALID_STATUSES.contains(status.toUpperCase());
    }

    public static boolean isValidPriority(String priority) {
        if (priority == null || priority.trim().isEmpty()) {
            return false;
        }

        return VALID_PRIORITIES.contains(priority.toUpperCase());
    }

    public static boolean isValidCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return false;
        }

        return VALID_CATEGORIES.contains(category);
    }


    public static boolean isValidCsvLine(String csvLine) {
        if (csvLine == null || csvLine.trim().isEmpty()) {
            return false;
        }

        String[] parts = csvLine.split(",");
        return parts.length == 8; // id, title, description, deadline, status, priority, category, createdAt
    }

    public static boolean isValidCsvHeader(String header) {
        if (header == null) {
            return false;
        }

        String expectedHeader = "id,title,description,deadline,status,priority,category,createdAt";
        return header.trim().equals(expectedHeader);
    }

    public static String sanitizeForCsv(String input) {
        if (input == null) {
            return "";
        }

        // Replace comma dengan semicolon
        String sanitized = input.replace(",", ";");

        // Handle newlines
        sanitized = sanitized.replace("\n", " ");
        sanitized = sanitized.replace("\r", " ");

        // Trim extra spaces
        sanitized = sanitized.trim();

        return sanitized;
    }

    public static String getValidationErrors(Task task) {
        if (task == null) {
            return "Task is null";
        }

        StringBuilder errors = new StringBuilder();

        if (!isValidId(task.getId())) {
            errors.append("- ID must be positive\n");
        }

        if (!isValidTitle(task.getTitle())) {
            errors.append("- Title must be 3-100 characters\n");
        }

        if (!isValidDescription(task.getDescription())) {
            errors.append("- Description must be max 500 characters\n");
        }

        if (!isValidStatus(task.getStatus())) {
            errors.append("- Status must be TODO, IN_PROGRESS, or COMPLETED\n");
        }

        if (!isValidPriority(task.getPriority())) {
            errors.append("- Priority must be LOW, MEDIUM, or HIGH\n");
        }

        if (!isValidCategory(task.getCategory())) {
            errors.append("- Category must be valid\n");
        }

        return errors.length() == 0 ? null : errors.toString();
    }

    public static List<String> getValidStatuses() {
        return VALID_STATUSES;
    }
    public static List<String> getValidPriorities() {
        return VALID_PRIORITIES;
    }
    public static List<String> getValidCategories() {
        return VALID_CATEGORIES;
    }
}
