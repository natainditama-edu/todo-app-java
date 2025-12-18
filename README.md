# Todo App - Java Task Management Application

A comprehensive Java-based Todo Application with GUI implementing Object-Oriented Programming (OOP) principles, task management, filtering, search, and file persistence.

## 📋 Features

- ✅ **CRUD Operations**: Create, Read, Update, and Delete tasks
- 🔍 **Advanced Search**: Search tasks by keywords in title or description
- 🎯 **Smart Filtering**: Filter by status, priority, and category
- 📊 **Statistics Dashboard**: Real-time task statistics and overview
- 💾 **Data Persistence**: Automatic save/load with CSV file format
- ⏰ **Deadline Tracking**: Automatic overdue detection
- 🎨 **User-Friendly GUI**: Clean Swing-based interface
- 📝 **Task Details**: View comprehensive task information
- 🔄 **Status Management**: Quick status change functionality

## 🏗️ Architecture

### Package Structure
```
src/
├── Main.java                 # Application entry point
├── model/
│   └── Task.java            # Task model with encapsulation
├── manager/
│   ├── TaskManager.java     # Business logic & CRUD operations
│   └── FileManager.java     # Data persistence (CSV)
└── ui/
    ├── MainFrame.java       # Main application window
    └── TaskDialog.java      # Task input/edit dialog
```

### OOP Principles Implemented

1. **Encapsulation**
   - All class attributes are private
   - Controlled access through getters/setters with validation
   - Data hiding and protection

2. **Abstraction**
   - Clear separation of concerns (Model, Manager, UI)
   - Public interfaces hide implementation details
   - FileManager abstracts file operations

3. **Composition**
   - TaskManager uses FileManager
   - MainFrame uses TaskManager
   - Clear dependency management

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Any Java IDE (IntelliJ IDEA, Eclipse, NetBeans) or command line

### Installation & Running

#### Option 1: Using IDE (IntelliJ IDEA)
1. Open the project in IntelliJ IDEA
2. Wait for indexing to complete
3. Right-click on `Main.java`
4. Select "Run 'Main.main()'"

#### Option 2: Command Line
```bash
# Navigate to project directory
cd todo-app-java

# Compile all Java files
javac -d out/production/todo-app-java -sourcepath src src/Main.java src/model/*.java src/manager/*.java src/ui/*.java

# Run the application
cd out/production/todo-app-java
java Main
```

## 📖 User Guide

### Main Window

The main window displays:
- **Task Table**: Shows all tasks with ID, title, status, priority, category, deadline, and creation date
- **Search Bar**: Real-time search across task titles and descriptions
- **Filter Panel**: Dropdown filters for status, priority, and category
- **Action Buttons**: Add, Edit, Delete, Change Status, View Details
- **Statistics Bar**: Shows task counts by status and overdue tasks

### Adding a Task

1. Click **"Add Task"** button
2. Fill in the form:
   - **Title** (required): Task name
   - **Description**: Detailed description
   - **Priority**: Low, Medium, or High
   - **Category**: Custom category (e.g., Study, Work, Personal)
   - **Deadline Date**: Target completion date
   - **Deadline Time**: Target completion time
3. Click **"Add Task"** to save or **"Cancel"** to discard

### Editing a Task

1. Select a task from the table
2. Click **"Edit Task"** button (or double-click the task)
3. Modify the fields as needed
4. Click **"Save Changes"** to update

### Deleting a Task

1. Select a task from the table
2. Click **"Delete Task"** button
3. Confirm the deletion

### Filtering Tasks

Use the dropdown filters to view specific tasks:
- **Status Filter**: All Status, Pending, In Progress, Completed, Cancelled
- **Priority Filter**: All Priority, Low, Medium, High
- **Category Filter**: All Categories, or specific categories

### Searching Tasks

1. Type keywords in the search box
2. Press Enter or click **"Search"**
3. Click **"Clear"** to reset search

### Changing Task Status

1. Select a task
2. Click **"Change Status"**
3. Select new status from the dialog
4. Confirm the change

### Viewing Task Details

1. Select a task
2. Click **"View Details"**
3. See complete task information in a dialog

## 💾 Data Storage

### File Format
Tasks are stored in `data/tasks.csv` with the following format:

```csv
id,title,description,deadline,status,priority,category,createdAt
T-A1B2C3D4,Task Title,Task Description,2025-12-25 10:00:00,Pending,High,Study,2025-12-15 08:00:00
```

### Data Fields
- **id**: Unique task identifier (auto-generated)
- **title**: Task title
- **description**: Task description
- **deadline**: Deadline in format `yyyy-MM-dd HH:mm:ss`
- **status**: Pending, In Progress, Completed, or Cancelled
- **priority**: Low, Medium, or High
- **category**: Custom category
- **createdAt**: Creation timestamp

### Backup
The application automatically creates backups when needed. Backup files are named:
```
tasks_backup_YYYYMMDD_HHmmss.csv
```

## 🎯 Task Status

- **Pending**: Task not yet started
- **In Progress**: Task currently being worked on
- **Completed**: Task finished
- **Cancelled**: Task cancelled/abandoned

## 📊 Priority Levels

- **High**: Urgent and important tasks
- **Medium**: Normal priority tasks
- **Low**: Can be done later

## 🔧 Technical Details

### Technologies Used
- **Language**: Java 8+
- **GUI Framework**: Java Swing
- **Data Format**: CSV (Comma-Separated Values)
- **Date/Time**: Java 8 Time API (LocalDateTime)

### Key Classes

#### Task.java
- Model class representing a task
- Encapsulates all task properties
- Provides validation and business logic
- Includes CSV serialization

#### TaskManager.java
- Manages all task operations
- CRUD operations (Create, Read, Update, Delete)
- Filtering and searching functionality
- Integrates with FileManager for persistence

#### FileManager.java
- Handles file I/O operations
- CSV parsing and generation
- Error handling for file operations
- Automatic file creation

#### MainFrame.java
- Main application window
- JTable for task display
- Filter and search UI
- Event handling for all actions

#### TaskDialog.java
- Dialog for adding/editing tasks
- Input validation
- Date/time pickers
- Supports both add and edit modes

## 🐛 Troubleshooting

### Application won't start
- Ensure JDK 8 or higher is installed
- Check that all `.java` files are compiled
- Verify the `data` directory exists

### Data not saving
- Check file permissions for the `data` directory
- Ensure disk space is available
- Check console for error messages

### Tasks not displaying
- Click the **"Refresh"** button
- Check if filters are applied
- Verify the CSV file format is correct

## 👥 Development Team

**Kelompok 3 - Aplikasi Manajemen Tugas**

- **Divisi 1 (Model & Business Logic)**
  - TRESNA FEBRYAN BERNADINE ICO
  - NI WAYAN SINTYA KUMARA DEWI

- **Divisi 2 (File Management & Data)**
  - NYOMAN BHAYU WIRATANAYA
  - SASYA PRADITYA AGNESIA

- **Divisi 3 (UI & Integration)**
  - NYOMAN CANDRA NATA INDITAMA

- **Divisi 4 (Documentation & Deployment)**
  - NI WAYAN LISTIADEWI (Ketua)
  - VANDA VICADA KARLIE TJOENG
  - STEVEN ABIGAIL HASTING RENNIE

## 📝 License

This project is created for educational purposes as part of PBO (Pemrograman Berorientasi Objek) course.

## 🙏 Acknowledgments

- Course instructors for guidance
- Team members for collaboration
- Java Swing documentation and community

---

**Version**: 1.0  
**Last Updated**: December 2025  
**Course**: Pemrograman Berorientasi Objek (PBO)  
**Institution**: Semester 3 - 2025
