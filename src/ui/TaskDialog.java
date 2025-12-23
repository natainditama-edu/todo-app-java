package ui;

import model.Task;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * TaskDialog class for adding and editing tasks.
 * Provides a dialog form with input validation.
 * 
 * @author Kelompok 3 - Todo App
 * @version 1.0
 */
public class TaskDialog extends JDialog {
    // Input components
    private JTextField txtTitle;
    private JTextArea txtDescription;
    private JComboBox<String> cmbPriority;
    private JTextField txtCategory;
    private JSpinner spnDeadlineDate;
    private JSpinner spnDeadlineTime;
    
    // Result
    private Task resultTask;
    private boolean confirmed;
    
    // Mode
    private final Task editingTask;
    
    // Theme manager
    private final ThemeManager themeManager;
    
    /**
     * Constructor for TaskDialog.
     * 
     * @param parent Parent frame
     * @param task Task to edit (null for add mode)
     */
    public TaskDialog(JFrame parent, Task task) {
        super(parent, task == null ? "Add New Task" : "Edit Task", true);
        this.editingTask = task;
        this.confirmed = false;
        this.themeManager = ThemeManager.getInstance();
        
        // Set dialog background
        getContentPane().setBackground(themeManager.getColor("background"));
        
        initComponents();
        layoutComponents();
        
        if (editingTask != null) {
            populateFields();
        }
        
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }
    
    /**
     * Initializes all UI components.
     */
    private void initComponents() {
        // Title field
        txtTitle = new JTextField(30);
        txtTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTitle.setBackground(themeManager.getColor("surface"));
        txtTitle.setForeground(themeManager.getColor("text"));
        txtTitle.setCaretColor(themeManager.getColor("text"));
        txtTitle.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(themeManager.getColor("border"), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        
        // Description area
        txtDescription = new JTextArea(4, 30);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDescription.setBackground(themeManager.getColor("surface"));
        txtDescription.setForeground(themeManager.getColor("text"));
        txtDescription.setCaretColor(themeManager.getColor("text"));
        txtDescription.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        
        // Priority combo box with custom renderer for padding
        String[] priorities = {Task.PRIORITY_LOW, Task.PRIORITY_MEDIUM, Task.PRIORITY_HIGH};
        cmbPriority = new JComboBox<>(priorities);
        cmbPriority.setSelectedItem(Task.PRIORITY_MEDIUM);
        cmbPriority.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        cmbPriority.setBackground(themeManager.getColor("surface"));
        cmbPriority.setForeground(themeManager.getColor("text"));
        cmbPriority.setRenderer(createComboBoxRenderer());
        cmbPriority.setBorder(null); // Remove focus border
        fixComboBoxButton(cmbPriority);
        
        // Category field
        txtCategory = new JTextField(30);
        txtCategory.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCategory.setBackground(themeManager.getColor("surface"));
        txtCategory.setForeground(themeManager.getColor("text"));
        txtCategory.setCaretColor(themeManager.getColor("text"));
        txtCategory.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(themeManager.getColor("border"), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        
        // Deadline date spinner with month/year buttons
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spnDeadlineDate = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnDeadlineDate, "dd/MM/yyyy");
        spnDeadlineDate.setEditor(dateEditor);
        spnDeadlineDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        spnDeadlineDate.setBorder(BorderFactory.createLineBorder(themeManager.getColor("border"), 1));
        
        // Add padding and theme colors to date spinner editor
        JComponent dateEditorComponent = dateEditor.getTextField();
        dateEditorComponent.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        dateEditorComponent.setBackground(themeManager.getColor("surface"));
        dateEditorComponent.setForeground(themeManager.getColor("text"));
        
        // Deadline time spinner with padding
        SpinnerDateModel timeModel = new SpinnerDateModel();
        spnDeadlineTime = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnDeadlineTime, "HH:mm");
        spnDeadlineTime.setEditor(timeEditor);
        spnDeadlineTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        spnDeadlineTime.setBorder(BorderFactory.createLineBorder(themeManager.getColor("border"), 1));
        
        // Add padding and theme colors to time spinner editor
        JComponent timeEditorComponent = timeEditor.getTextField();
        timeEditorComponent.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        timeEditorComponent.setBackground(themeManager.getColor("surface"));
        timeEditorComponent.setForeground(themeManager.getColor("text"));
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
     * Lays out all components in the dialog.
     */
    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(themeManager.getColor("background"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(themeManager.getColor("background"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Helper method to create themed labels
        java.util.function.Function<String, JLabel> createLabel = text -> {
            JLabel label = new JLabel(text);
            label.setForeground(themeManager.getColor("text"));
            label.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            return label;
        };
        
        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(createLabel.apply("Title: *"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        formPanel.add(txtTitle, gbc);
        
        // Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(createLabel.apply("Description:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollDesc = new JScrollPane(txtDescription);
        scrollDesc.setBorder(BorderFactory.createLineBorder(themeManager.getColor("border"), 1));
        scrollDesc.getViewport().setBackground(themeManager.getColor("surface"));
        formPanel.add(scrollDesc, gbc);
        
        // Priority
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(createLabel.apply("Priority:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(cmbPriority, gbc);
        
        // Category
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(createLabel.apply("Category:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtCategory, gbc);
        
        // Deadline date
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(createLabel.apply("Deadline Date: *"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(spnDeadlineDate, gbc);
        
        // Deadline time
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(createLabel.apply("Deadline Time: *"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(spnDeadlineTime, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonPanel.setBackground(themeManager.getColor("background"));
        
        JButton btnSave = createStyledButton(
            editingTask == null ? "Add Task" : "Save Changes", 
            themeManager.getColor("primary")
        );
        btnSave.addActionListener(e -> onSave());
        
        JButton btnCancel = createStyledButton("Cancel", themeManager.getColor("secondary"));
        btnCancel.addActionListener(e -> onCancel());
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Required fields note
        JLabel lblRequired = new JLabel("* Required fields");
        lblRequired.setFont(new Font("Segoe UI", Font.ITALIC, 10));
        lblRequired.setForeground(themeManager.getColor("textSecondary"));
        mainPanel.add(lblRequired, BorderLayout.NORTH);
        
        setContentPane(mainPanel);
    }
    
    /**
     * Populates fields with existing task data (edit mode).
     */
    private void populateFields() {
        txtTitle.setText(editingTask.getTitle());
        txtDescription.setText(editingTask.getDescription());
        cmbPriority.setSelectedItem(editingTask.getPriority());
        txtCategory.setText(editingTask.getCategory());
        
        // Set deadline date and time
        LocalDateTime deadline = editingTask.getDeadline();
        java.util.Date deadlineDate = java.sql.Timestamp.valueOf(deadline);
        spnDeadlineDate.setValue(deadlineDate);
        spnDeadlineTime.setValue(deadlineDate);
    }
    
    /**
     * Handles save button click.
     */
    private void onSave() {
        if (validateInput()) {
            try {
                String title = txtTitle.getText().trim();
                String description = txtDescription.getText().trim();
                String priority = (String) cmbPriority.getSelectedItem();
                String category = txtCategory.getText().trim();
                LocalDateTime deadline = getDeadlineFromSpinners();
                
                if (editingTask == null) {
                    // Create new task
                    resultTask = new Task(title, description, deadline, priority, category);
                } else {
                    // Update existing task
                    resultTask = new Task(
                        editingTask.getId(),
                        title,
                        description,
                        deadline,
                        editingTask.getStatus(),
                        priority,
                        category,
                        editingTask.getCreatedAt()
                    );
                }
                
                confirmed = true;
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                    this,
                    "Error creating task: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
    
    /**
     * Handles cancel button click.
     */
    private void onCancel() {
        confirmed = false;
        dispose();
    }
    
    /**
     * Validates user input.
     * 
     * @return true if input is valid, false otherwise
     */
    private boolean validateInput() {
        // Validate title
        if (txtTitle.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Title is required!",
                "Validation Error",
                JOptionPane.WARNING_MESSAGE
            );
            txtTitle.requestFocus();
            return false;
        }
        
        // Validate deadline is in the future (for new tasks)
        if (editingTask == null) {
            LocalDateTime deadline = getDeadlineFromSpinners();
            if (deadline.isBefore(LocalDateTime.now())) {
                int choice = JOptionPane.showConfirmDialog(
                    this,
                    "Deadline is in the past. Continue anyway?",
                    "Warning",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
                );
                
                if (choice != JOptionPane.YES_OPTION) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    /**
     * Gets LocalDateTime from date and time spinners.
     * 
     * @return Combined LocalDateTime
     */
    private LocalDateTime getDeadlineFromSpinners() {
        java.util.Date date = (java.util.Date) spnDeadlineDate.getValue();
        java.util.Date time = (java.util.Date) spnDeadlineTime.getValue();
        
        java.util.Calendar calDate = java.util.Calendar.getInstance();
        calDate.setTime(date);
        
        java.util.Calendar calTime = java.util.Calendar.getInstance();
        calTime.setTime(time);
        
        return LocalDateTime.of(
            calDate.get(java.util.Calendar.YEAR),
            calDate.get(java.util.Calendar.MONTH) + 1,
            calDate.get(java.util.Calendar.DAY_OF_MONTH),
            calTime.get(java.util.Calendar.HOUR_OF_DAY),
            calTime.get(java.util.Calendar.MINUTE)
        );
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
     * Shows the dialog and returns the result task.
     * 
     * @return Task object if confirmed, null if cancelled
     */
    public Task showDialog() {
        setVisible(true);
        return confirmed ? resultTask : null;
    }
}
