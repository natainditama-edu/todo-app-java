# USER MANUAL
## Todo Application - Aplikasi Manajemen Tugas

**Version:** 1.0  
**Last Updated:** Desember 2025  
**Platform:** Windows, macOS, Linux (Java 21+)

---

## 📋 Table of Contents

1. [System Requirements](#system-requirements)
2. [Installation](#installation)
3. [Getting Started](#getting-started)
4. [Features Guide](#features-guide)
5. [Keyboard Shortcuts](#keyboard-shortcuts)
6. [Troubleshooting](#troubleshooting)
7. [FAQ](#faq)

---

## System Requirements

### Minimum Requirements

**Hardware:**
- Processor: Intel Core i3 atau equivalent
- RAM: 2 GB
- Storage: 50 MB free space
- Display: 1024x768 resolution

**Software:**
- Operating System: Windows 10/11, macOS 10.14+, Linux (Ubuntu 18.04+)
- Java Runtime Environment (JRE) 21 atau lebih baru

### Recommended Requirements

**Hardware:**
- Processor: Intel Core i5 atau lebih tinggi
- RAM: 4 GB atau lebih
- Storage: 100 MB free space
- Display: 1920x1080 resolution

**Software:**
- Java Development Kit (JDK) 21
- Modern operating system dengan latest updates

---

## Installation

### Step 1: Check Java Installation

**Windows:**
```bash
java -version
```

**macOS/Linux:**
```bash
java --version
```

**Expected Output:**
```
java version "21.0.x"
Java(TM) SE Runtime Environment
```

**If Java not installed:**
- Download from: https://www.oracle.com/java/technologies/downloads/
- Install Java 21 LTS
- Restart terminal/command prompt

### Step 2: Extract Application

1. Download `todo-app.zip`
2. Extract to desired location
3. Navigate to extracted folder

**Folder Structure:**
```
todo-app/
├── todo-app.jar          # Executable JAR file
├── data/
│   └── tasks.csv         # Sample data (100 tasks)
├── docs/
│   ├── UAS_REPORT.md
│   ├── UAS_PRESENTATION.md
│   └── UML/
└── README.md
```

### Step 3: Run Application

**Windows (Double-click):**
- Double-click `todo-app.jar`

**Windows (Command Line):**
```bash
java -jar todo-app.jar
```

**macOS:**
```bash
java -jar todo-app.jar
```

**Linux:**
```bash
java -jar todo-app.jar
```

**First Launch:**
- Application akan load `data/tasks.csv`
- 100 sample tasks akan ditampilkan
- Theme akan auto-detect dari OS

---

## Getting Started

### First Time Setup

**Step 1: Explore Sample Data**
- Application sudah include 100 sample tasks
- Browse tasks di table
- Try filtering dan searching

**Step 2: Understand Interface**
- **Top Menu Bar**: File, Edit, View, Help
- **Toolbar**: Quick access buttons
- **Filter Panel**: Status, Priority, Category dropdowns
- **Search Field**: Real-time keyword search
- **Main Table**: Task list display
- **Statistics Bar**: Bottom status information

**Step 3: Create Your First Task**
1. Click "Add Task" button
2. Fill in task details
3. Click "Save"
4. Task appears in table

---

## Features Guide

### 1. Task Management

#### Adding a New Task

**Method 1: Toolbar Button**
1. Click "Add Task" button (+ icon)
2. Dialog form opens

**Method 2: Menu**
1. File → New Task
2. Dialog form opens

**Method 3: Keyboard Shortcut**
1. Press `Ctrl+N` (Windows/Linux) or `Cmd+N` (macOS)

**Fill Form:**
- **Title** (Required): 3-100 characters
- **Description** (Optional): Max 500 characters
- **Deadline Date**: Select from calendar
- **Deadline Time**: Set time (default 23:59)
- **Priority**: Low, Medium, High
- **Category**: Choose from 10 options

**Validation:**
- Title cannot be empty
- Title must be 3-100 chars
- Deadline must be valid date
- All fields validated on save

**Save:**
- Click "Save" button
- Task added to table
- Data saved to CSV automatically
- Statistics updated

#### Viewing Task Details

**Method 1: Double-Click**
- Double-click any row in table
- Dialog opens with task details

**Method 2: Select + Edit Button**
- Click row to select
- Click "Edit" button
- Dialog opens

**View Mode:**
- All task information displayed
- Can switch to edit mode
- Can close without changes

#### Editing a Task

**Steps:**
1. Select task in table
2. Click "Edit" button or double-click
3. Modify any field
4. Click "Save"

**What Can Be Edited:**
- Title
- Description
- Deadline (date and time)
- Status (Pending, In Progress, Completed, Cancelled)
- Priority
- Category

**What Cannot Be Edited:**
- Task ID (auto-generated UUID)
- Created At timestamp

**Auto-Save:**
- Changes saved to CSV immediately
- Table refreshes automatically
- Statistics updated

#### Deleting a Task

**Steps:**
1. Select task in table
2. Click "Delete" button (trash icon)
3. Confirmation dialog appears
4. Click "Yes" to confirm

**Confirmation Dialog:**
```
Are you sure you want to delete this task?
Title: [Task Title]
This action cannot be undone.

[Yes] [No]
```

**After Deletion:**
- Task removed from table
- CSV file updated
- Statistics recalculated
- Cannot be undone

### 2. Filtering Tasks

#### Filter by Status

**Available Options:**
- All (show all tasks)
- Pending
- In Progress
- Completed
- Cancelled

**How to Use:**
1. Click "Status" dropdown
2. Select desired status
3. Table filters automatically

**Result:**
- Only tasks with selected status shown
- Other tasks hidden
- Statistics show filtered count

#### Filter by Priority

**Available Options:**
- All
- Low
- Medium
- High

**How to Use:**
1. Click "Priority" dropdown
2. Select desired priority
3. Table filters automatically

**Combine Filters:**
- Can combine with Status filter
- Can combine with Category filter
- Can combine with Search

#### Filter by Category

**Available Categories:**
- All
- Work
- Personal
- Academic
- Health
- Finance
- Development
- Testing
- Deployment
- Project
- Meeting

**How to Use:**
1. Click "Category" dropdown
2. Select desired category
3. Table filters automatically

### 3. Searching Tasks

#### Real-Time Search

**How to Use:**
1. Click search field
2. Type keyword
3. Results update as you type

**Search Scope:**
- Task title
- Task description
- Both fields searched simultaneously

**Search Features:**
- Case-insensitive matching
- Partial matching (contains)
- Real-time updates
- Works with active filters

**Clear Search:**
- Click "X" button in search field
- Or delete text manually
- Table shows all tasks again

**Example Searches:**
- "laporan" → finds all tasks with "laporan" in title/description
- "UAS" → finds all UAS-related tasks
- "meeting" → finds all meeting tasks

### 4. Statistics Dashboard

**Metrics Displayed:**

**Total Tasks:**
- Count of all tasks in system
- Updates after add/edit/delete

**Pending:**
- Tasks with status "Pending"
- Not yet started

**In Progress:**
- Tasks currently being worked on
- Status "In Progress"

**Completed:**
- Finished tasks
- Status "Completed"

**Overdue:**
- Tasks past deadline
- Deadline < current date/time
- Highlighted in red

**Location:**
- Bottom status bar
- Always visible
- Updates automatically

### 5. Theme Management

#### Automatic Theme Detection

**Windows:**
- Reads Windows registry
- Key: `HKEY_CURRENT_USER\Software\Microsoft\Windows\CurrentVersion\Themes\Personalize`
- Value: `AppsUseLightTheme`
- 0 = Dark Mode, 1 = Light Mode

**macOS:**
- Detects system appearance
- Follows System Preferences

**Linux:**
- Detects GTK theme
- Follows desktop environment settings

#### Manual Theme Toggle

**Method 1: Menu**
1. View → Toggle Theme
2. Theme switches instantly

**Method 2: Keyboard Shortcut**
1. Press `Ctrl+T` (Windows/Linux)
2. Or `Cmd+T` (macOS)

**Theme Colors:**

**Light Mode:**
- Background: #ECF0F1
- Surface: #FFFFFF
- Text: #000000
- Primary: #2980B9

**Dark Mode:**
- Background: #1E1E1E
- Surface: #2D2D30
- Text: #E0E0E0
- Primary: #4A9EFF

---

## Keyboard Shortcuts

### Global Shortcuts

| Shortcut | Action |
|----------|--------|
| `Ctrl+N` / `Cmd+N` | New Task |
| `Ctrl+E` / `Cmd+E` | Edit Selected Task |
| `Delete` | Delete Selected Task |
| `Ctrl+F` / `Cmd+F` | Focus Search Field |
| `Ctrl+R` / `Cmd+R` | Refresh Table |
| `Ctrl+T` / `Cmd+T` | Toggle Theme |
| `Ctrl+Q` / `Cmd+Q` | Quit Application |
| `F1` | Help |
| `F5` | Refresh |

### Dialog Shortcuts

| Shortcut | Action |
|----------|--------|
| `Enter` | Save (when in dialog) |
| `Esc` | Cancel/Close Dialog |
| `Tab` | Next Field |
| `Shift+Tab` | Previous Field |

### Table Navigation

| Shortcut | Action |
|----------|--------|
| `↑` / `↓` | Navigate Rows |
| `Home` | First Row |
| `End` | Last Row |
| `Page Up` | Scroll Up |
| `Page Down` | Scroll Down |
| `Enter` | Edit Selected Task |

---

## Troubleshooting

### Application Won't Start

**Problem:** Double-clicking JAR does nothing

**Solutions:**
1. Check Java installation: `java -version`
2. Run from command line: `java -jar todo-app.jar`
3. Check error messages in terminal
4. Reinstall Java 21

**Problem:** "Java not found" error

**Solution:**
1. Install Java 21 from Oracle
2. Add Java to PATH
3. Restart terminal
4. Try again

### Data Issues

**Problem:** Tasks not loading

**Solutions:**
1. Check `data/tasks.csv` exists
2. Verify CSV format is correct
3. Check file permissions
4. Restore from backup

**Problem:** Changes not saving

**Solutions:**
1. Check file write permissions
2. Ensure disk space available
3. Check CSV file not locked
4. Run as administrator (Windows)

### UI Issues

**Problem:** Theme not detected

**Solutions:**
1. Windows: Check registry access
2. Manually toggle theme (Ctrl+T)
3. Restart application
4. Check OS theme settings

**Problem:** UI elements not visible

**Solutions:**
1. Check screen resolution (min 1024x768)
2. Adjust window size
3. Reset to default layout
4. Restart application

### Performance Issues

**Problem:** Slow loading with many tasks

**Solutions:**
1. Reduce number of tasks
2. Archive old tasks
3. Increase Java heap size:
   ```bash
   java -Xmx512m -jar todo-app.jar
   ```
4. Close other applications

**Problem:** Search is slow

**Solutions:**
1. Clear filters first
2. Use more specific keywords
3. Reduce total task count
4. Restart application

---

## FAQ

### General Questions

**Q: Can I use this on multiple computers?**
A: Yes, copy the `data/tasks.csv` file to sync data between computers.

**Q: Is my data secure?**
A: Data stored locally in CSV file. No cloud sync. Keep backups.

**Q: Can multiple users use this?**
A: Currently single-user only. No multi-user support.

**Q: Can I export my tasks?**
A: Yes, tasks stored in `data/tasks.csv` (standard CSV format).

### Data Management

**Q: Where is my data stored?**
A: `data/tasks.csv` in application folder.

**Q: How do I backup my data?**
A: Copy `data/tasks.csv` to safe location.

**Q: How do I restore backup?**
A: Replace `data/tasks.csv` with backup file.

**Q: Can I edit CSV directly?**
A: Yes, but use proper format. Restart app after editing.

### Features

**Q: Can I set recurring tasks?**
A: Not in current version. Planned for future release.

**Q: Can I attach files to tasks?**
A: Not in current version. Planned for future release.

**Q: Can I share tasks with others?**
A: Not in current version. Single-user only.

**Q: Can I print tasks?**
A: Export CSV and print from Excel/Sheets.

### Technical

**Q: What Java version do I need?**
A: Java 21 or newer (LTS recommended).

**Q: Does it work on Mac?**
A: Yes, Java is cross-platform.

**Q: Does it work on Linux?**
A: Yes, tested on Ubuntu 18.04+.

**Q: Can I run from USB drive?**
A: Yes, fully portable. Just copy entire folder.

---

## Support

**For Issues:**
- Check Troubleshooting section
- Review FAQ
- Check GitHub Issues

**For Feature Requests:**
- Submit GitHub Issue
- Describe use case
- Provide examples

**Contact:**
- GitHub: [Repository URL]
- Email: [Support Email]

---

## Version History

**Version 1.0 (December 2025)**
- Initial release
- CRUD operations
- Filtering & searching
- Theme management
- CSV persistence
- 100 sample tasks

---

**End of User Manual**
