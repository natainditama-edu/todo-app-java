package ui;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * ThemeManager class to manage application themes (Light and Dark mode).
 * Automatically detects OS theme preference and provides appropriate colors.
 * 
 * @author Kelompok 3 - Todo App
 * @version 1.0
 */
public class ThemeManager {
    private static ThemeManager instance;
    private boolean isDarkMode;
    private Map<String, Color> lightColors;
    private Map<String, Color> darkColors;
    
    /**
     * Private constructor for singleton pattern.
     */
    private ThemeManager() {
        initializeColorPalettes();
        detectOSTheme();
    }
    
    /**
     * Gets the singleton instance of ThemeManager.
     * 
     * @return ThemeManager instance
     */
    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }
    
    /**
     * Initializes color palettes for both light and dark themes.
     */
    private void initializeColorPalettes() {
        // Light mode colors (current design)
        lightColors = new HashMap<>();
        lightColors.put("background", new Color(236, 240, 241));      // Light Gray
        lightColors.put("surface", Color.WHITE);                       // White
        lightColors.put("surfaceAlt", new Color(250, 250, 250));      // Very Light Gray
        lightColors.put("text", Color.BLACK);                          // Black
        lightColors.put("textSecondary", new Color(149, 165, 166));   // Gray
        lightColors.put("header", new Color(52, 73, 94));             // Dark Blue-Gray
        lightColors.put("headerText", Color.WHITE);                    // White
        lightColors.put("grid", new Color(220, 220, 220));            // Light Gray
        lightColors.put("border", new Color(189, 195, 199));          // Gray
        lightColors.put("selection", new Color(52, 152, 219));        // Blue
        
        // Semantic colors (same for both themes but adjusted brightness)
        lightColors.put("primary", new Color(41, 128, 185));          // Blue
        lightColors.put("success", new Color(39, 174, 96));           // Green
        lightColors.put("warning", new Color(243, 156, 18));          // Orange
        lightColors.put("danger", new Color(231, 76, 60));            // Red
        lightColors.put("secondary", new Color(149, 165, 166));       // Gray
        
        // Dark mode colors
        darkColors = new HashMap<>();
        darkColors.put("background", new Color(30, 30, 30));          // Dark Gray
        darkColors.put("surface", new Color(45, 45, 48));             // Darker Gray
        darkColors.put("surfaceAlt", new Color(37, 37, 38));          // Very Dark Gray
        darkColors.put("text", new Color(224, 224, 224));             // Light Gray
        darkColors.put("textSecondary", new Color(176, 176, 176));    // Medium Gray
        darkColors.put("header", new Color(37, 37, 38));              // Very Dark Gray
        darkColors.put("headerText", new Color(224, 224, 224));       // Light Gray
        darkColors.put("grid", new Color(62, 62, 66));                // Dark Gray
        darkColors.put("border", new Color(62, 62, 66));              // Dark Gray
        darkColors.put("selection", new Color(14, 99, 156));          // Darker Blue
        
        // Semantic colors for dark mode (lighter/adjusted)
        darkColors.put("primary", new Color(74, 158, 255));           // Lighter Blue
        darkColors.put("success", new Color(76, 209, 55));            // Lighter Green
        darkColors.put("warning", new Color(255, 193, 7));            // Lighter Orange
        darkColors.put("danger", new Color(244, 67, 54));             // Lighter Red
        darkColors.put("secondary", new Color(158, 158, 158));        // Lighter Gray
    }
    
    /**
     * Detects the OS theme preference.
     * Currently checks Windows theme settings.
     */
    private void detectOSTheme() {
        try {
            // Try to detect Windows dark mode
            // Check if Windows is using dark theme via registry
            String osName = System.getProperty("os.name").toLowerCase();
            
            if (osName.contains("win")) {
                // For Windows, check registry for dark mode
                // This is a simplified check - in production, you might use JNA
                String appsUseLightTheme = getWindowsRegistryValue(
                    "HKEY_CURRENT_USER\\Software\\Microsoft\\Windows\\CurrentVersion\\Themes\\Personalize",
                    "AppsUseLightTheme"
                );
                
                // If registry value is "0", dark mode is enabled
                isDarkMode = "0".equals(appsUseLightTheme);
            } else {
                // Default to light mode for other OS
                isDarkMode = false;
            }
        } catch (Exception e) {
            // Default to light mode if detection fails
            isDarkMode = false;
            System.err.println("Failed to detect OS theme, defaulting to light mode: " + e.getMessage());
        }
    }
    
    /**
     * Gets a Windows registry value.
     * 
     * @param key Registry key path
     * @param valueName Value name
     * @return Registry value or null if not found
     */
    private String getWindowsRegistryValue(String key, String valueName) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                "reg", "query", key, "/v", valueName
            );
            Process process = processBuilder.start();
            
            java.io.BufferedReader reader = new java.io.BufferedReader(
                new java.io.InputStreamReader(process.getInputStream())
            );
            
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(valueName)) {
                    // Extract the value (format: "    ValueName    REG_DWORD    0x0")
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length >= 3) {
                        String value = parts[parts.length - 1];
                        // Convert hex to decimal if needed
                        if (value.startsWith("0x")) {
                            return String.valueOf(Integer.parseInt(value.substring(2), 16));
                        }
                        return value;
                    }
                }
            }
            reader.close();
        } catch (Exception e) {
            // Ignore errors and return null
        }
        return null;
    }
    
    /**
     * Checks if dark mode is currently active.
     * 
     * @return true if dark mode, false if light mode
     */
    public boolean isDarkMode() {
        return isDarkMode;
    }
    
    /**
     * Gets a color for the current theme.
     * 
     * @param colorKey Color key (e.g., "background", "text", "primary")
     * @return Color for the current theme
     */
    public Color getColor(String colorKey) {
        Map<String, Color> currentPalette = isDarkMode ? darkColors : lightColors;
        return currentPalette.getOrDefault(colorKey, Color.GRAY);
    }
    
    /**
     * Manually sets the theme mode (for testing or user preference).
     * 
     * @param darkMode true for dark mode, false for light mode
     */
    public void setDarkMode(boolean darkMode) {
        this.isDarkMode = darkMode;
    }
}
