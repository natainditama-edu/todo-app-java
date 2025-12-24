import manager.TaskManager;
import ui.MainFrame;

import javax.swing.*;

/**
 * Main class - Entry point for the Todo Application.
 * Initializes the application and displays the main window.
 * 
 * @author Kelompok 3 - Todo App
 * @version 1.0
 */
public class Main {
    // Data file path - using current working directory
    private static final String DATA_FILE = System.getProperty("user.dir") + "/data/tasks.csv";
    
    /**
     * Main method - Application entry point.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
            // Continue with default look and feel
        }
        
        // Run GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Initialize TaskManager
                System.out.println("=== Todo App Starting ===");
                System.out.println("Data file: " + DATA_FILE);
                
                TaskManager taskManager = new TaskManager(DATA_FILE);
                
                // Create and show main window
                MainFrame mainFrame = new MainFrame(taskManager);
                mainFrame.setVisible(true);
                
                System.out.println("Application started successfully!");
                System.out.println("=========================");
                
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
                
                JOptionPane.showMessageDialog(
                    null,
                    "Error starting application:\n" + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE
                );
                
                System.exit(1);
            }
        });
    }
}