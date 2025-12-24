package ui;

import manager.TaskManager;
import model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import javax.swing.RowSorter;
import javax.swing.SortOrder;

/**
 * MainFrame class for the main application window.
 * Provides the main UI with task table, filters, search, and action buttons.
 * Enhanced with sortable columns, colors, and improved spacing.
 * 
 * @author Kelompok 3 - Todo App
 * @version 1.0
 */
public class MainFrame extends JFrame {
    private final TaskManager taskManager;
    private final ThemeManager themeManager;
    
    // UI Components
    private JTable taskTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> tableSorter;
    private JTextField txtSearch;
    private JComboBox<String> cmbStatusFilter;
    private JComboBox<String> cmbPriorityFilter;
    private JComboBox<String> cmbCategoryFilter;
    private JLabel lblStatistics;
    private JLabel lblEmptyState;
    
    // Table columns
    private static final String[] COLUMN_NAMES = {
        "ID", "Title", "Status", "Priority", "Category", "Deadline", "Created"
    };
    
    private static final DateTimeFormatter DATE_FORMATTER = 
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    // Theme colors (dynamically loaded from ThemeManager)
    private Color PRIMARY_COLOR;
    private Color SUCCESS_COLOR;
    private Color WARNING_COLOR;
    private Color DANGER_COLOR;
    private Color SECONDARY_COLOR;
    private Color BACKGROUND_COLOR;
    private Color HEADER_COLOR;
    private Color ROW_EVEN_COLOR;
    private Color ROW_ODD_COLOR;
    
    /**
     * Constructor to initialize MainFrame.
     * 
     * @param taskManager TaskManager instance
     */
    public MainFrame(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.themeManager = ThemeManager.getInstance();
        
        // Load theme colors
        loadThemeColors();
        
        // Apply theme to UIManager defaults for combo boxes and dialogs
        UIManager.put("ComboBox.background", themeManager.getColor("surface"));
        UIManager.put("ComboBox.foreground", themeManager.getColor("text"));
        UIManager.put("ComboBox.selectionBackground", themeManager.getColor("surface")); // Remove blue selection
        UIManager.put("ComboBox.selectionForeground", themeManager.getColor("text"));
        UIManager.put("ComboBox.buttonBackground", themeManager.getColor("surface"));
        UIManager.put("ComboBox.buttonDarkShadow", themeManager.getColor("border"));
        UIManager.put("ComboBox.buttonShadow", themeManager.getColor("border"));
        UIManager.put("ComboBox.buttonHighlight", themeManager.getColor("surface"));
        UIManager.put("ComboBox.focus", themeManager.getColor("surface")); // Remove blue focus
        UIManager.put("OptionPane.background", themeManager.getColor("background"));
        UIManager.put("Panel.background", themeManager.getColor("background"));
        UIManager.put("OptionPane.messageForeground", themeManager.getColor("text"));
        
        // Apply theme to scrollbars
        UIManager.put("ScrollBar.thumb", themeManager.getColor("border"));
        UIManager.put("ScrollBar.track", themeManager.getColor("surface"));
        UIManager.put("ScrollBar.background", themeManager.getColor("surface"));
        
        initComponents();
        layoutComponents();
        setupEventHandlers();
        refreshTable();
        
        setTitle("Todo App - Task Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    /**
     * Loads colors from ThemeManager based on current theme.
     */
    private void loadThemeColors() {
        PRIMARY_COLOR = themeManager.getColor("primary");
        SUCCESS_COLOR = themeManager.getColor("success");
        WARNING_COLOR = themeManager.getColor("warning");
        DANGER_COLOR = themeManager.getColor("danger");
        SECONDARY_COLOR = themeManager.getColor("secondary");
        BACKGROUND_COLOR = themeManager.getColor("background");
        HEADER_COLOR = themeManager.getColor("header");
        ROW_EVEN_COLOR = themeManager.getColor("surface");
        ROW_ODD_COLOR = themeManager.getColor("surfaceAlt");
    }
    
    /**
     * Initializes all UI components.
     */
    private void initComponents() {
        // Table with custom model that supports cell spanning
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        taskTable = new JTable(tableModel) {
            @Override
            public boolean getScrollableTracksViewportWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }
            
            // Override to handle empty state row spanning
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                // Check if this is an empty state row (ID column is empty)
                Object idValue = getValueAt(row, 0);
                if (idValue == null || idValue.toString().isEmpty()) {
                    // For empty state, only show content in first visible column
                    // Other columns will be handled by custom renderer
                }
                return c;
            }
        };
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskTable.getTableHeader().setReorderingAllowed(false);
        
        // Enable table sorting
        tableSorter = new TableRowSorter<>(tableModel);
        taskTable.setRowSorter(tableSorter);
        
        // Set custom comparators for date columns
        tableSorter.setComparator(5, Comparator.comparing((String s) -> {
            // Remove [OVERDUE] tag if present
            String cleanDate = s.replace(" [OVERDUE]", "");
            try {
                return java.time.LocalDateTime.parse(cleanDate, DATE_FORMATTER);
            } catch (Exception e) {
                return java.time.LocalDateTime.MIN;
            }
        }));
        
        tableSorter.setComparator(6, Comparator.comparing((String s) -> {
            try {
                return java.time.LocalDateTime.parse(s, DATE_FORMATTER);
            } catch (Exception e) {
                return java.time.LocalDateTime.MIN;
            }
        }));
        
        // Table styling
        taskTable.setRowHeight(28);
        taskTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        taskTable.setGridColor(themeManager.getColor("grid"));
        taskTable.setShowGrid(true);
        taskTable.setIntercellSpacing(new Dimension(1, 1));
        taskTable.setBackground(themeManager.getColor("surface"));
        taskTable.setForeground(themeManager.getColor("text"));
        taskTable.setSelectionBackground(themeManager.getColor("selection"));
        taskTable.setSelectionForeground(themeManager.getColor("text"));
        
        // Header styling
        JTableHeader header = taskTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 13));
        header.setBackground(HEADER_COLOR);
        header.setForeground(themeManager.getColor("headerText"));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 35));
        
        // Custom header renderer to ensure white text and show sort indicators
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                setBackground(HEADER_COLOR);
                setForeground(themeManager.getColor("headerText"));
                setFont(new Font("Segoe UI", Font.BOLD, 13));
                setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                // Add sort indicator if column is sorted
                if (tableSorter != null && tableSorter.getSortKeys().size() > 0) {
                    for (RowSorter.SortKey sortKey : tableSorter.getSortKeys()) {
                        if (sortKey.getColumn() == column) {
                            String sortIndicator = sortKey.getSortOrder() == SortOrder.ASCENDING ? " ▲" : " ▼";
                            setText(value.toString() + sortIndicator);
                            break;
                        }
                    }
                }
                
                return this;
            }
        };
        
        for (int i = 0; i < taskTable.getColumnModel().getColumnCount(); i++) {
            taskTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        
        // Custom cell renderer for colors and padding
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                // Add padding
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                setHorizontalAlignment(JLabel.LEFT);
                
                if (!isSelected) {
                    // Alternating row colors
                    if (row % 2 == 0) {
                        c.setBackground(ROW_EVEN_COLOR);
                    } else {
                        c.setBackground(ROW_ODD_COLOR);
                    }
                    
                    // Default text color
                    c.setForeground(themeManager.getColor("text"));
                    
                    // Color coding for Status column (index 2)
                    if (column == 2) {
                        String status = value.toString();
                        setFont(getFont().deriveFont(Font.BOLD));
                        setHorizontalAlignment(JLabel.CENTER);
                        
                        // Use different opacity for light and dark mode
                        int alpha = themeManager.isDarkMode() ? 120 : 180; // Higher opacity in light mode
                        
                        switch (status) {
                            case "Pending":
                                c.setBackground(new Color(SECONDARY_COLOR.getRed(), SECONDARY_COLOR.getGreen(), SECONDARY_COLOR.getBlue(), alpha));
                                c.setForeground(themeManager.isDarkMode() ? themeManager.getColor("text") : Color.WHITE);
                                break;
                            case "In Progress":
                                c.setBackground(new Color(PRIMARY_COLOR.getRed(), PRIMARY_COLOR.getGreen(), PRIMARY_COLOR.getBlue(), alpha));
                                c.setForeground(themeManager.isDarkMode() ? themeManager.getColor("text") : Color.WHITE);
                                break;
                            case "Completed":
                                c.setBackground(new Color(SUCCESS_COLOR.getRed(), SUCCESS_COLOR.getGreen(), SUCCESS_COLOR.getBlue(), alpha));
                                c.setForeground(themeManager.isDarkMode() ? themeManager.getColor("text") : Color.WHITE);
                                break;
                            case "Cancelled":
                                c.setBackground(new Color(DANGER_COLOR.getRed(), DANGER_COLOR.getGreen(), DANGER_COLOR.getBlue(), alpha));
                                c.setForeground(themeManager.isDarkMode() ? themeManager.getColor("text") : Color.WHITE);
                                break;
                        }
                    }
                    // Color coding for Priority column (index 3)
                    else if (column == 3) {
                        String priority = value.toString();
                        setFont(getFont().deriveFont(Font.BOLD));
                        setHorizontalAlignment(JLabel.CENTER);
                        
                        // Use different opacity for light and dark mode
                        int alpha = themeManager.isDarkMode() ? 80 : 180; // Higher opacity in light mode
                        
                        switch (priority) {
                            case "Low":
                                c.setBackground(new Color(SUCCESS_COLOR.getRed(), SUCCESS_COLOR.getGreen(), SUCCESS_COLOR.getBlue(), alpha));
                                c.setForeground(themeManager.isDarkMode() ? themeManager.getColor("text") : Color.WHITE);
                                break;
                            case "Medium":
                                c.setBackground(new Color(WARNING_COLOR.getRed(), WARNING_COLOR.getGreen(), WARNING_COLOR.getBlue(), alpha));
                                c.setForeground(themeManager.isDarkMode() ? themeManager.getColor("text") : Color.WHITE);
                                break;
                            case "High":
                                c.setBackground(new Color(DANGER_COLOR.getRed(), DANGER_COLOR.getGreen(), DANGER_COLOR.getBlue(), alpha));
                                c.setForeground(themeManager.isDarkMode() ? themeManager.getColor("text") : Color.WHITE);
                                break;
                        }
                    }
                    // Highlight overdue tasks in Deadline column (index 5)
                    else if (column == 5 && value != null && value.toString().contains("[OVERDUE]")) {
                        c.setForeground(DANGER_COLOR);
                        setFont(getFont().deriveFont(Font.BOLD));
                    } else {
                        setFont(getFont().deriveFont(Font.PLAIN));
                    }
                }
                
                return c;
            }
        };
        
        // Apply renderer to all columns
        for (int i = 0; i < taskTable.getColumnCount(); i++) {
            taskTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
        
        // Set column widths
        taskTable.getColumnModel().getColumn(0).setPreferredWidth(100);  // ID
        taskTable.getColumnModel().getColumn(1).setPreferredWidth(220); // Title
        taskTable.getColumnModel().getColumn(2).setPreferredWidth(110); // Status
        taskTable.getColumnModel().getColumn(3).setPreferredWidth(90);  // Priority
        taskTable.getColumnModel().getColumn(4).setPreferredWidth(110); // Category
        taskTable.getColumnModel().getColumn(5).setPreferredWidth(150); // Deadline
        taskTable.getColumnModel().getColumn(6).setPreferredWidth(150); // Created
        
        // Search field
        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSearch.setBackground(themeManager.getColor("surface"));
        txtSearch.setForeground(themeManager.getColor("text"));
        txtSearch.setCaretColor(themeManager.getColor("text"));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(themeManager.getColor("border"), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        
        // Filter combo boxes with custom renderer for padding
        cmbStatusFilter = new JComboBox<>(new String[]{
            "All Status", 
            Task.STATUS_PENDING, 
            Task.STATUS_IN_PROGRESS, 
            Task.STATUS_COMPLETED, 
            Task.STATUS_CANCELLED
        });
        cmbStatusFilter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbStatusFilter.setBackground(themeManager.getColor("surface"));
        cmbStatusFilter.setForeground(themeManager.getColor("text"));
        cmbStatusFilter.setRenderer(createComboBoxRenderer());
        cmbStatusFilter.setBorder(null); // Remove focus border
        fixComboBoxButton(cmbStatusFilter);
        
        cmbPriorityFilter = new JComboBox<>(new String[]{
            "All Priority", 
            Task.PRIORITY_LOW, 
            Task.PRIORITY_MEDIUM, 
            Task.PRIORITY_HIGH
        });
        cmbPriorityFilter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbPriorityFilter.setBackground(themeManager.getColor("surface"));
        cmbPriorityFilter.setForeground(themeManager.getColor("text"));
        cmbPriorityFilter.setRenderer(createComboBoxRenderer());
        cmbPriorityFilter.setBorder(null); // Remove focus border
        fixComboBoxButton(cmbPriorityFilter);
        
        cmbCategoryFilter = new JComboBox<>(new String[]{"All Categories"});
        cmbCategoryFilter.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbCategoryFilter.setBackground(themeManager.getColor("surface"));
        cmbCategoryFilter.setForeground(themeManager.getColor("text"));
        cmbCategoryFilter.setRenderer(createComboBoxRenderer());
        cmbCategoryFilter.setBorder(null); // Remove focus border
        fixComboBoxButton(cmbCategoryFilter);
        
        // Statistics label
        lblStatistics = new JLabel();
        lblStatistics.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblStatistics.setForeground(themeManager.getColor("text"));
        
        // Empty state label
        lblEmptyState = new JLabel("");
        lblEmptyState.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblEmptyState.setForeground(themeManager.getColor("textSecondary"));
        lblEmptyState.setHorizontalAlignment(JLabel.RIGHT);
    }
    
    /**
     * Lays out all components in the frame.
     */
    private void layoutComponents() {
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(BACKGROUND_COLOR);
        
        // Top panel - Filters (left) and Search (right)
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(BACKGROUND_COLOR);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        
        // Left side - Filters + Refresh button
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        leftPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel lblFilters = new JLabel("Filters:");
        lblFilters.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblFilters.setForeground(themeManager.getColor("text"));
        leftPanel.add(lblFilters);
        leftPanel.add(cmbStatusFilter);
        leftPanel.add(cmbPriorityFilter);
        leftPanel.add(cmbCategoryFilter);
        
        JButton btnRefresh = createStyledButton("Refresh", PRIMARY_COLOR);
        btnRefresh.addActionListener(e -> refreshTable());
        leftPanel.add(btnRefresh);
        
        topPanel.add(leftPanel, BorderLayout.WEST);
        
        // Right side - Search input + buttons
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        rightPanel.setBackground(BACKGROUND_COLOR);
        
        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblSearch.setForeground(themeManager.getColor("text"));
        rightPanel.add(lblSearch);
        rightPanel.add(txtSearch);
        
        JButton btnSearch = createStyledButton("Search", PRIMARY_COLOR);
        btnSearch.addActionListener(e -> applyFilters());
        rightPanel.add(btnSearch);
        
        JButton btnClearSearch = createStyledButton("Clear", SECONDARY_COLOR);
        btnClearSearch.addActionListener(e -> {
            txtSearch.setText("");
            applyFilters();
        });
        rightPanel.add(btnClearSearch);
        
        topPanel.add(rightPanel, BorderLayout.EAST);
        
        add(topPanel, BorderLayout.NORTH);
        
        // Center panel - Empty state label + Table
        JPanel centerPanel = new JPanel(new BorderLayout(0, 5));
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        
        // Empty state label (above table, right-aligned)
        JPanel emptyStatePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        emptyStatePanel.setBackground(BACKGROUND_COLOR);
        emptyStatePanel.add(lblEmptyState);
        centerPanel.add(emptyStatePanel, BorderLayout.NORTH);
        
        // Table with scroll pane
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(themeManager.getColor("border"), 1));
        scrollPane.getViewport().setBackground(themeManager.getColor("surface"));
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // Bottom panel - Buttons and Statistics
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setBackground(BACKGROUND_COLOR);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 5));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        
        JButton btnAdd = createStyledButton("Add Task", SUCCESS_COLOR);
        btnAdd.addActionListener(e -> showAddDialog());
        buttonPanel.add(btnAdd);
        
        JButton btnEdit = createStyledButton("Edit Task", PRIMARY_COLOR);
        btnEdit.addActionListener(e -> showEditDialog());
        buttonPanel.add(btnEdit);
        
        JButton btnDelete = createStyledButton("Delete Task", DANGER_COLOR);
        btnDelete.addActionListener(e -> showDeleteConfirm());
        buttonPanel.add(btnDelete);
        
        JButton btnChangeStatus = createStyledButton("Change Status", WARNING_COLOR);
        btnChangeStatus.addActionListener(e -> showChangeStatusDialog());
        buttonPanel.add(btnChangeStatus);
        
        JButton btnViewDetails = createStyledButton("View Details", SECONDARY_COLOR);
        btnViewDetails.addActionListener(e -> showTaskDetails());
        buttonPanel.add(btnViewDetails);
        
        bottomPanel.add(buttonPanel, BorderLayout.WEST);
        
        // Statistics panel
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        statsPanel.setBackground(BACKGROUND_COLOR);
        statsPanel.add(lblStatistics);
        bottomPanel.add(statsPanel, BorderLayout.EAST);
        
        add(bottomPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Creates a styled button with the specified text and color.
     * 
     * @param text Button text
     * @param bgColor Background color
     * @return Styled JButton
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    /**
     * Fixes combo box button appearance for dark mode using custom UI.
     * 
     * @param comboBox JComboBox to fix
     */
    private void fixComboBoxButton(JComboBox<?> comboBox) {
        try {
            comboBox.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    // Create invisible button to hide the arrow
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(0, 0));
                    button.setVisible(false);
                    return button;
                }
                
                @Override
                public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                    // Override to prevent blue focus background
                    g.setColor(themeManager.getColor("surface"));
                    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
                }
            });
        } catch (Exception e) {
            // If custom UI fails, ignore
        }
    }
    
    /**
     * Creates a custom renderer for combo boxes with padding.
     * 
     * @return ListCellRenderer for combo boxes
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private ListCellRenderer createComboBoxRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                
                if (!isSelected) {
                    setBackground(themeManager.getColor("surface"));
                    setForeground(themeManager.getColor("text"));
                } else {
                    setBackground(themeManager.getColor("selection"));
                    setForeground(themeManager.getColor("text"));
                }
                
                return this;
            }
        };
    }
    
    /**
     * Sets up event handlers for components.
     */
    private void setupEventHandlers() {
        // Filter change listeners
        cmbStatusFilter.addActionListener(e -> applyFilters());
        cmbPriorityFilter.addActionListener(e -> applyFilters());
        cmbCategoryFilter.addActionListener(e -> applyFilters());
        
        // Search on Enter key
        txtSearch.addActionListener(e -> applyFilters());
        
        // Double-click to edit
        taskTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    showEditDialog();
                }
            }
        });
    }
    
    /**
     * Refreshes the task table with current data.
     */
    public void refreshTable() {
        updateCategoryFilter();
        applyFilters();
    }
    
    /**
     * Updates the category filter with current categories.
     */
    private void updateCategoryFilter() {
        String selectedCategory = (String) cmbCategoryFilter.getSelectedItem();
        cmbCategoryFilter.removeAllItems();
        cmbCategoryFilter.addItem("All Categories");
        
        List<String> categories = taskManager.getAllCategories();
        for (String category : categories) {
            cmbCategoryFilter.addItem(category);
        }
        
        // Restore selection if still valid
        if (selectedCategory != null) {
            for (int i = 0; i < cmbCategoryFilter.getItemCount(); i++) {
                if (cmbCategoryFilter.getItemAt(i).equals(selectedCategory)) {
                    cmbCategoryFilter.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    
    /**
     * Applies current filters and search to the table.
     */
    private void applyFilters() {
        // Clear existing rows
        tableModel.setRowCount(0);
        
        // Get all tasks
        List<Task> tasks = taskManager.getAllTasks();
        
        // Store original count for empty state message
        int originalCount = tasks.size();
        boolean hasFilters = false;
        String filterDescription = "";
        
        // Apply search filter
        String searchKeyword = txtSearch.getText().trim();
        if (!searchKeyword.isEmpty()) {
            tasks = taskManager.searchTasks(searchKeyword);
            hasFilters = true;
            filterDescription = "search: \"" + searchKeyword + "\"";
        }
        
        // Apply status filter
        String statusFilter = (String) cmbStatusFilter.getSelectedItem();
        if (statusFilter != null && !statusFilter.equals("All Status")) {
            tasks = filterByStatus(tasks, statusFilter);
            hasFilters = true;
            if (!filterDescription.isEmpty()) filterDescription += ", ";
            filterDescription += "status: " + statusFilter;
        }
        
        // Apply priority filter
        String priorityFilter = (String) cmbPriorityFilter.getSelectedItem();
        if (priorityFilter != null && !priorityFilter.equals("All Priority")) {
            tasks = filterByPriority(tasks, priorityFilter);
            hasFilters = true;
            if (!filterDescription.isEmpty()) filterDescription += ", ";
            filterDescription += "priority: " + priorityFilter;
        }
        
        // Apply category filter
        String categoryFilter = (String) cmbCategoryFilter.getSelectedItem();
        if (categoryFilter != null && !categoryFilter.equals("All Categories")) {
            tasks = filterByCategory(tasks, categoryFilter);
            hasFilters = true;
            if (!filterDescription.isEmpty()) filterDescription += ", ";
            filterDescription += "category: " + categoryFilter;
        }
        
        // Populate table and update empty state label
        if (tasks.isEmpty()) {
            // Show empty state in label
            String message;
            if (originalCount == 0) {
                message = "No tasks yet. Click 'Add Task' to create your first task!";
            } else if (hasFilters) {
                message = "No tasks found matching " + filterDescription;
            } else {
                message = "No tasks available";
            }
            lblEmptyState.setText(message);
        } else {
            // Clear empty state label
            lblEmptyState.setText("");
            
            for (Task task : tasks) {
                Object[] row = {
                    task.getId(),
                    task.getTitle(),
                    task.getStatus(),
                    task.getPriority(),
                    task.getCategory(),
                    task.getDeadline().format(DATE_FORMATTER) + (task.isOverdue() ? " [OVERDUE]" : ""),
                    task.getCreatedAt().format(DATE_FORMATTER)
                };
                tableModel.addRow(row);
            }
        }
        
        // Update statistics
        updateStatistics();
    }
    
    /**
     * Filters tasks by status.
     */
    private List<Task> filterByStatus(List<Task> tasks, String status) {
        return tasks.stream()
            .filter(t -> t.getStatus().equals(status))
            .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Filters tasks by priority.
     */
    private List<Task> filterByPriority(List<Task> tasks, String priority) {
        return tasks.stream()
            .filter(t -> t.getPriority().equals(priority))
            .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Filters tasks by category.
     */
    private List<Task> filterByCategory(List<Task> tasks, String category) {
        return tasks.stream()
            .filter(t -> t.getCategory().equals(category))
            .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Updates the statistics label.
     */
    private void updateStatistics() {
        lblStatistics.setText(taskManager.getStatistics());
    }
    
    /**
     * Shows dialog to add a new task.
     */
    private void showAddDialog() {
        TaskDialog dialog = new TaskDialog(this, null);
        Task newTask = dialog.showDialog();
        
        if (newTask != null) {
            taskManager.addTask(newTask);
            refreshTable();
            showStyledMessage(
                "Task added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    /**
     * Shows dialog to edit selected task.
     */
    private void showEditDialog() {
        int selectedRow = taskTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showStyledMessage(
                "Please select a task to edit.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        String taskId = (String) tableModel.getValueAt(selectedRow, 0);
        
        // Skip if empty state row
        if (taskId == null || taskId.isEmpty()) {
            return;
        }
        
        Task task = taskManager.getTask(taskId);
        
        if (task != null) {
            TaskDialog dialog = new TaskDialog(this, task);
            Task updatedTask = dialog.showDialog();
            
            if (updatedTask != null) {
                taskManager.updateTask(taskId, updatedTask);
                refreshTable();
                showStyledMessage(
                    "Task updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }
    
    /**
     * Shows confirmation dialog to delete selected task.
     */
    private void showDeleteConfirm() {
        int selectedRow = taskTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showStyledMessage(
                "Please select a task to delete.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        String taskId = (String) tableModel.getValueAt(selectedRow, 0);
        String taskTitle = (String) tableModel.getValueAt(selectedRow, 1);
        
        // Skip if empty state row
        if (taskId == null || taskId.isEmpty()) {
            return;
        }
        
        int choice = showStyledConfirm(
            "Are you sure you want to delete task:\n\"" + taskTitle + "\"?",
            "Confirm Delete"
        );
        
        if (choice == JOptionPane.YES_OPTION) {
            taskManager.deleteTask(taskId);
            refreshTable();
            showStyledMessage(
                "Task deleted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    /**
     * Shows dialog to change task status.
     */
    private void showChangeStatusDialog() {
        int selectedRow = taskTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showStyledMessage(
                "Please select a task to change status.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        String taskId = (String) tableModel.getValueAt(selectedRow, 0);
        
        // Skip if empty state row
        if (taskId == null || taskId.isEmpty()) {
            return;
        }
        
        Task task = taskManager.getTask(taskId);
        
        if (task != null) {
            String[] statuses = {
                Task.STATUS_PENDING,
                Task.STATUS_IN_PROGRESS,
                Task.STATUS_COMPLETED,
                Task.STATUS_CANCELLED
            };
            
            // Create a custom combobox with padding
            JComboBox<String> statusComboBox = new JComboBox<>(statuses);
            statusComboBox.setSelectedItem(task.getStatus());
            statusComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            statusComboBox.setBackground(themeManager.getColor("surface"));
            statusComboBox.setForeground(themeManager.getColor("text"));
            statusComboBox.setRenderer(createComboBoxRenderer());
            statusComboBox.setBorder(null); // Remove focus border
            fixComboBoxButton(statusComboBox);
            
            // Create custom option pane
            JOptionPane pane = new JOptionPane(
                statusComboBox,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                null,
                new Object[]{},
                null
            );
            
            final int[] result = {JOptionPane.CLOSED_OPTION};
            
            // Create styled OK and Cancel buttons
            JButton okButton = createStyledButton("OK", PRIMARY_COLOR);
            okButton.addActionListener(e -> {
                result[0] = JOptionPane.OK_OPTION;
                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(okButton);
                dialog.dispose();
            });
            
            JButton cancelButton = createStyledButton("Cancel", SECONDARY_COLOR);
            cancelButton.addActionListener(e -> {
                result[0] = JOptionPane.CANCEL_OPTION;
                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(cancelButton);
                dialog.dispose();
            });
            
            pane.setOptions(new Object[]{okButton, cancelButton});
            
            JDialog dialog = pane.createDialog(this, "Change Status");
            dialog.setVisible(true);
            
            if (result[0] == JOptionPane.OK_OPTION) {
                String newStatus = (String) statusComboBox.getSelectedItem();
                if (newStatus != null && !newStatus.equals(task.getStatus())) {
                    task.setStatus(newStatus);
                    taskManager.updateTask(taskId, task);
                    refreshTable();
                    showStyledMessage(
                        "Status changed successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        }
    }
    
    /**
     * Shows detailed information about selected task.
     */
    private void showTaskDetails() {
        int selectedRow = taskTable.getSelectedRow();
        
        if (selectedRow == -1) {
            showStyledMessage(
                "Please select a task to view details.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        
        String taskId = (String) tableModel.getValueAt(selectedRow, 0);
        
        // Skip if empty state row
        if (taskId == null || taskId.isEmpty()) {
            return;
        }
        
        Task task = taskManager.getTask(taskId);
        
        if (task != null) {
            StringBuilder details = new StringBuilder();
            details.append("ID: ").append(task.getId()).append("\n");
            details.append("Title: ").append(task.getTitle()).append("\n");
            details.append("Description: ").append(task.getDescription()).append("\n");
            details.append("Status: ").append(task.getStatus()).append("\n");
            details.append("Priority: ").append(task.getPriority()).append("\n");
            details.append("Category: ").append(task.getCategory()).append("\n");
            details.append("Deadline: ").append(task.getDeadline().format(DATE_FORMATTER)).append("\n");
            details.append("Created: ").append(task.getCreatedAt().format(DATE_FORMATTER)).append("\n");
            details.append("Overdue: ").append(task.isOverdue() ? "Yes" : "No");
            
            JTextArea textArea = new JTextArea(details.toString());
            textArea.setEditable(false);
            textArea.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            textArea.setBackground(themeManager.getColor("surface"));
            textArea.setForeground(themeManager.getColor("text"));
            textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setBorder(BorderFactory.createLineBorder(themeManager.getColor("border"), 1));
            scrollPane.getViewport().setBackground(themeManager.getColor("surface"));
            
            showStyledMessage(
                scrollPane,
                "Task Details",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }
    
    /**
     * Helper method to show styled message dialog with custom buttons.
     */
    private void showStyledMessage(Object message, String title, int messageType) {
        JOptionPane pane = new JOptionPane(
            message,
            messageType,
            JOptionPane.DEFAULT_OPTION,
            null,
            new Object[]{},
            null
        );
        
        // Create styled OK button
        JButton okButton = createStyledButton("OK", PRIMARY_COLOR);
        okButton.addActionListener(e -> {
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(okButton);
            dialog.dispose();
        });
        
        pane.setOptions(new Object[]{okButton});
        
        JDialog dialog = pane.createDialog(this, title);
        dialog.setVisible(true);
    }
    
    /**
     * Helper method to show styled confirm dialog with custom buttons.
     */
    private int showStyledConfirm(String message, String title) {
        JOptionPane pane = new JOptionPane(
            message,
            JOptionPane.WARNING_MESSAGE,
            JOptionPane.YES_NO_OPTION,
            null,
            new Object[]{},
            null
        );
        
        final int[] result = {JOptionPane.CLOSED_OPTION};
        
        // Create styled Yes and No buttons
        JButton yesButton = createStyledButton("Yes", DANGER_COLOR);
        yesButton.addActionListener(e -> {
            result[0] = JOptionPane.YES_OPTION;
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(yesButton);
            dialog.dispose();
        });
        
        JButton noButton = createStyledButton("No", SECONDARY_COLOR);
        noButton.addActionListener(e -> {
            result[0] = JOptionPane.NO_OPTION;
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(noButton);
            dialog.dispose();
        });
        
        pane.setOptions(new Object[]{yesButton, noButton});
        
        JDialog dialog = pane.createDialog(this, title);
        dialog.setVisible(true);
        
        return result[0];
    }
    
    /**
     * Helper method to show styled input dialog with custom buttons.
     */
    private Object showStyledInput(String message, String title, Object[] options, Object initialValue) {
        JOptionPane pane = new JOptionPane(
            message,
            JOptionPane.QUESTION_MESSAGE,
            JOptionPane.OK_CANCEL_OPTION,
            null,
            new Object[]{},
            null
        );
        
        final Object[] result = {null};
        
        // Create styled OK and Cancel buttons
        JButton okButton = createStyledButton("OK", PRIMARY_COLOR);
        okButton.addActionListener(e -> {
            result[0] = initialValue;
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(okButton);
            dialog.dispose();
        });
        
        JButton cancelButton = createStyledButton("Cancel", SECONDARY_COLOR);
        cancelButton.addActionListener(e -> {
            result[0] = null;
            JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor(cancelButton);
            dialog.dispose();
        });
        
        pane.setOptions(new Object[]{okButton, cancelButton});
        
        JDialog dialog = pane.createDialog(this, title);
        dialog.setVisible(true);
        
        return result[0];
    }
}
