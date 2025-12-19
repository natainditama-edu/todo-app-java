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
        
        initComponents();
        layoutComponents();
        
        if (editingTask != null) {
            populateFields();
        }
        
        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }
    
    // Color scheme (matching MainFrame)
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color SECONDARY_COLOR = new Color(149, 165, 166);
    
    /**
     * Initializes all UI components.
     */
    private void initComponents() {
        // Title field
        txtTitle = new JTextField(30);
        txtTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtTitle.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        
        // Description area
        txtDescription = new JTextArea(4, 30);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        txtDescription.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDescription.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        
        // Priority combo box with custom renderer for padding
        String[] priorities = {Task.PRIORITY_LOW, Task.PRIORITY_MEDIUM, Task.PRIORITY_HIGH};
        cmbPriority = new JComboBox<>(priorities);
        cmbPriority.setSelectedItem(Task.PRIORITY_MEDIUM);
        cmbPriority.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cmbPriority.setRenderer(createComboBoxRenderer());
        
        // Category field
        txtCategory = new JTextField(20);
        txtCategory.setText("General");
        txtCategory.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCategory.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
            BorderFactory.createEmptyBorder(5, 8, 5, 8)
        ));
        
        // Deadline date spinner with month/year buttons
        SpinnerDateModel dateModel = new SpinnerDateModel();
        spnDeadlineDate = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnDeadlineDate, "dd/MM/yyyy");
        spnDeadlineDate.setEditor(dateEditor);
        spnDeadlineDate.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        // Add padding to date spinner editor
        JComponent dateEditorComponent = dateEditor.getTextField();
        dateEditorComponent.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        
        // Deadline time spinner with padding
        SpinnerDateModel timeModel = new SpinnerDateModel();
        spnDeadlineTime = new JSpinner(timeModel);
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(spnDeadlineTime, "HH:mm");
        spnDeadlineTime.setEditor(timeEditor);
        spnDeadlineTime.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        // Add padding to time spinner editor
        JComponent timeEditorComponent = timeEditor.getTextField();
        timeEditorComponent.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
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
                return this;
            }
        };
    }
    
    /**
     * Lays out all components in the dialog.
     */
    private void layoutComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Title: *"), gbc);
        
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
        formPanel.add(new JLabel("Description:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        JScrollPane scrollDesc = new JScrollPane(txtDescription);
        formPanel.add(scrollDesc, gbc);
        
        // Priority
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(new JLabel("Priority:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(cmbPriority, gbc);
        
        // Category
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Category:"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtCategory, gbc);
        
        // Deadline date
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Deadline Date: *"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(spnDeadlineDate, gbc);
        
        // Deadline time
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Deadline Time: *"), gbc);
        
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(spnDeadlineTime, gbc);
        
        mainPanel.add(formPanel, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        JButton btnSave = createStyledButton(
            editingTask == null ? "Add Task" : "Save Changes", 
            PRIMARY_COLOR
        );
        btnSave.addActionListener(e -> onSave());
        
        JButton btnCancel = createStyledButton("Cancel", SECONDARY_COLOR);
        btnCancel.addActionListener(e -> onCancel());
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Required fields note
        JLabel lblRequired = new JLabel("* Required fields");
        lblRequired.setFont(new Font(lblRequired.getFont().getName(), Font.ITALIC, 10));
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
