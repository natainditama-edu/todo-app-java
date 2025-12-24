package manager;

import model.Task;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * FileManager class for handling data persistence operations.
 * Manages saving and loading tasks to/from CSV file.
 * 
 * @author Kelompok 3 - Todo App
 * @version 1.0
 */
public class FileManager {
    private final String fileName;
    private static final String CSV_HEADER = "id,title,description,deadline,status,priority,category,createdAt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Constructor to initialize FileManager with specified file name.
     * Creates the data directory if it doesn't exist.
     * 
     * @param fileName Name of the CSV file to manage
     */
    public FileManager(String fileName) {
        this.fileName = fileName;
        ensureDataDirectoryExists();
    }
    
    /**
     * Ensures that the data directory exists, creates it if necessary.
     */
    private void ensureDataDirectoryExists() {
        try {
            Path filePath = Paths.get(fileName);
            Path parentDir = filePath.getParent();
            
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Created data directory: " + parentDir);
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not create data directory: " + e.getMessage());
        }
    }
    
    /**
     * Saves the list of tasks to CSV file.
     * Overwrites existing file content.
     * 
     * @param tasks List of tasks to save
     * @throws IOException if file cannot be written
     */
    public void saveToFile(List<Task> tasks) throws IOException {
        if (tasks == null) {
            throw new IllegalArgumentException("Task list cannot be null");
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            // Write CSV header
            writer.write(CSV_HEADER);
            writer.newLine();
            
            // Write each task
            for (Task task : tasks) {
                writer.write(task.toCsvString());
                writer.newLine();
            }
            
            System.out.println("Successfully saved " + tasks.size() + " tasks to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Loads tasks from CSV file.
     * Creates an empty file with header if file doesn't exist.
     * 
     * @return List of tasks loaded from file
     * @throws IOException if file cannot be read
     */
    public List<Task> loadFromFile() throws IOException {
        List<Task> tasks = new ArrayList<>();
        
        // Check if file exists
        if (!Files.exists(Paths.get(fileName))) {
            System.out.println("File not found. Creating new file: " + fileName);
            createEmptyFile();
            return tasks;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;
            
            // Read and skip header
            line = reader.readLine();
            lineNumber++;
            
            if (line == null) {
                System.out.println("File is empty. No tasks loaded.");
                return tasks;
            }
            
            // Read each task line
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                
                // Skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    Task task = parseTaskFromCsv(line);
                    tasks.add(task);
                } catch (Exception e) {
                    System.err.println("Error parsing line " + lineNumber + ": " + e.getMessage());
                    System.err.println("Skipping invalid line: " + line);
                }
            }
            
            System.out.println("Successfully loaded " + tasks.size() + " tasks from " + fileName);
        } catch (IOException e) {
            System.err.println("Error loading tasks from file: " + e.getMessage());
            throw e;
        }
        
        return tasks;
    }
    
    /**
     * Parses a CSV line into a Task object.
     * Handles quoted fields containing commas.
     * 
     * @param csvLine CSV line to parse
     * @return Task object
     * @throws IllegalArgumentException if line format is invalid
     */
    private Task parseTaskFromCsv(String csvLine) {
        List<String> fields = parseCsvLine(csvLine);
        
        if (fields.size() != 8) {
            throw new IllegalArgumentException(
                "Invalid CSV format. Expected 8 fields, got " + fields.size()
            );
        }
        
        try {
            String id = fields.get(0).trim();
            String title = fields.get(1).trim();
            String description = fields.get(2).trim();
            
            // Parse deadline - handle both date-only and datetime formats
            String deadlineStr = fields.get(3).trim();
            LocalDateTime deadline;
            if (deadlineStr.contains(" ")) {
                // Full datetime format
                deadline = LocalDateTime.parse(deadlineStr, FORMATTER);
            } else {
                // Date-only format - set time to end of day
                deadline = java.time.LocalDate.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        .atTime(23, 59, 59);
            }
            
            String status = fields.get(4).trim();
            String priority = fields.get(5).trim();
            String category = fields.get(6).trim();
            LocalDateTime createdAt = LocalDateTime.parse(fields.get(7).trim(), FORMATTER);
            
            return new Task(id, title, description, deadline, status, priority, category, createdAt);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error creating task: " + e.getMessage());
        }
    }
    
    /**
     * Parses a CSV line handling quoted fields.
     * 
     * @param line CSV line to parse
     * @return List of field values
     */
    private List<String> parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder currentField = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                // Check for escaped quote
                if (inQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    currentField.append('"');
                    i++; // Skip next quote
                } else {
                    inQuotes = !inQuotes;
                }
            } else if (c == ',' && !inQuotes) {
                fields.add(currentField.toString());
                currentField = new StringBuilder();
            } else {
                currentField.append(c);
            }
        }
        
        // Add last field
        fields.add(currentField.toString());
        
        return fields;
    }
    
    /**
     * Creates an empty CSV file with header.
     * 
     * @throws IOException if file cannot be created
     */
    private void createEmptyFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(CSV_HEADER);
            writer.newLine();
        }
    }
    
    /**
     * Checks if the data file exists.
     * 
     * @return true if file exists, false otherwise
     */
    public boolean fileExists() {
        return Files.exists(Paths.get(fileName));
    }
    
    /**
     * Gets the file name being managed.
     * 
     * @return File name
     */
    public String getFileName() {
        return fileName;
    }
    
    /**
     * Backs up the current data file.
     * Creates a backup with timestamp in the filename.
     * 
     * @throws IOException if backup fails
     */
    public void backupFile() throws IOException {
        if (!fileExists()) {
            System.out.println("No file to backup.");
            return;
        }
        
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String backupFileName = fileName.replace(".csv", "_backup_" + timestamp + ".csv");
        
        Files.copy(Paths.get(fileName), Paths.get(backupFileName), StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Backup created: " + backupFileName);
    }
}
