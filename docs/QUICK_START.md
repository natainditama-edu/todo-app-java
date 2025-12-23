# Quick Start Guide - Divisi 2 Deliverables
## Sasya Praditya Agnesia

---

## 🚀 Quick Setup (5 menit)

### Step 1: Extract Files
Copy semua file ke project structure:

```bash
# Copy sample data
📁 data/tasks.csv → project/data/tasks.csv

# Copy Java files
📁 src/com/todoapp/manager/DataValidator.java
📁 src/com/todoapp/test/FileManagerTest.java

# Copy documentation
📁 docs/TEST_REPORT.md
📁 docs/DIVISI2_README.md
```

### Step 2: Verify Structure
```
todo-app/
├── data/
│   └── tasks.csv ✅
├── src/com/todoapp/
│   ├── manager/
│   │   └── DataValidator.java ✅
│   └── test/
│       └── FileManagerTest.java ✅
└── docs/
    ├── TEST_REPORT.md ✅
    └── DIVISI2_README.md ✅
```

### Step 3: Compile (di IntelliJ IDEA)
1. Open project di IntelliJ
2. Right-click `src` folder → Mark Directory as → Sources Root
3. Build → Build Project (Ctrl+F9)

---

## 🧪 Running Tests

### Option 1: From IntelliJ
```
1. Open FileManagerTest.java
2. Right-click class name
3. Click "Run FileManagerTest.main()"
```

### Option 2: From Terminal
```bash
# Navigate to project root
cd todo-app

# Compile test class
javac -d bin src/com/todoapp/test/FileManagerTest.java

# Run tests
java -cp bin com.todoapp.test.FileManagerTest
```

### Expected Output:
```
================================================
   FILE MANAGER TEST SUITE
   Divisi 2 - Sasya Praditya Agnesia
================================================

TEST 1: Save Valid Data
------------------------
✓ PASS: File created successfully

[... 10 tests ...]

================================================
   ALL TESTS COMPLETED
================================================
```

---

## 💻 Using DataValidator

### Example 1: Validate Before Save
```java
import com.todoapp.manager.DataValidator;
import com.todoapp.model.Task;

// In FileManager.saveToFile()
for (Task task : tasks) {
    if (!DataValidator.isValidTask(task)) {
        String errors = DataValidator.getValidationErrors(task);
        throw new IllegalArgumentException("Invalid task: " + errors);
    }
}
```

### Example 2: Validate User Input
```java
// In GUI TaskDialog
String title = titleField.getText();

if (!DataValidator.isValidTitle(title)) {
    JOptionPane.showMessageDialog(this, 
        "Title must be 3-100 characters",
        "Invalid Input", 
        JOptionPane.ERROR_MESSAGE);
    return;
}
```

### Example 3: Sanitize Data
```java
// Before saving to CSV
String safeTitle = DataValidator.sanitizeForCsv(task.getTitle());
String safeDesc = DataValidator.sanitizeForCsv(task.getDescription());
```

---

## 📊 Using Sample Data

### Load Sample Data
```java
import com.todoapp.manager.FileManager;

FileManager fm = new FileManager();
List<Task> tasks = fm.loadFromFile("data/tasks.csv");

System.out.println("Loaded " + tasks.size() + " tasks");
// Output: Loaded 15 tasks
```

### Sample Data Contents
- **15 tasks** covering various scenarios
- **3 statuses**: TODO, IN_PROGRESS, COMPLETED
- **3 priorities**: LOW, MEDIUM, HIGH
- **8 categories**: Development, Academic, Testing, etc.

### Use Cases
1. **GUI Testing**: Populate table dengan sample data
2. **Filter Testing**: Test berbagai kombinasi filter
3. **Search Testing**: Search by title, category, etc.
4. **CRUD Testing**: Add, edit, delete operations

---

## 🔍 Validation Rules Reference

| Field | Rules |
|-------|-------|
| **ID** | Must be positive integer (> 0) |
| **Title** | 3-100 characters, not empty |
| **Description** | Max 500 characters, can be empty |
| **Deadline** | Format: yyyy-MM-dd (optional) |
| **Status** | TODO / IN_PROGRESS / COMPLETED |
| **Priority** | LOW / MEDIUM / HIGH |
| **Category** | Personal, Academic, Development, Testing, Project, Health, Finance, Deployment, Work, Other |

### Quick Validation
```java
// Check specific fields
DataValidator.isValidTitle("My Task");        // true
DataValidator.isValidStatus("IN_PROGRESS");   // true
DataValidator.isValidPriority("HIGH");        // true

// Get available values
List<String> statuses = DataValidator.getValidStatuses();
List<String> priorities = DataValidator.getValidPriorities();
List<String> categories = DataValidator.getValidCategories();
```

---

## 🐛 Common Issues & Solutions

### Issue: File Not Found
```
Error: data/tasks.csv not found
```
**Solution**: Create `data` folder di project root

### Issue: Compile Error
```
Error: package com.todoapp.model does not exist
```
**Solution**: Wait for Divisi 1 to push Task.java, atau create temporary Task class

### Issue: Test Fails
```
✗ FAIL: File not created
```
**Solution**: Check file permissions, ensure `data/` folder exists

---

## 📝 Integration Checklist

### For Bhayu (FileManager)
- [ ] Review DataValidator.java
- [ ] Integrate validation dalam FileManager
- [ ] Use sanitizeForCsv() untuk string fields
- [ ] Add error handling berdasarkan validation
- [ ] Test dengan FileManagerTest

### For Candra (GUI Integration)
- [ ] Load tasks.csv saat aplikasi start
- [ ] Use DataValidator untuk validate user input
- [ ] Show validation errors di GUI
- [ ] Use sample data untuk initial testing
- [ ] Run FileManagerTest untuk verify integration

### For All Team Members
- [ ] Review TEST_REPORT.md
- [ ] Understand sample data structure
- [ ] Know validation rules
- [ ] Ready to integrate

---

## 📞 Need Help?

### Quick References
1. **Full Documentation**: `docs/DIVISI2_README.md`
2. **Test Report**: `docs/TEST_REPORT.md`
3. **Validation Code**: `src/com/todoapp/manager/DataValidator.java`
4. **Test Code**: `src/com/todoapp/test/FileManagerTest.java`

### Common Questions

**Q: Bagaimana cara menambah sample data?**
A: Edit `data/tasks.csv`, tambah baris baru dengan format yang sama

**Q: Bagaimana cara test FileManager sendiri?**
A: Run `FileManagerTest.java` untuk comprehensive testing

**Q: Validasi apa saja yang wajib?**
A: ID, Title, Status, Priority, Category. Deadline dan Description optional.

**Q: Format CSV yang benar?**
A: 8 columns: id,title,description,deadline,status,priority,category,createdAt

---

## ✅ Verification Steps

Sebelum push ke GitHub, verify:

1. [ ] All files ada di location yang benar
2. [ ] FileManagerTest compile tanpa error
3. [ ] DataValidator compile tanpa error
4. [ ] tasks.csv format valid
5. [ ] Documentation complete
6. [ ] Code has JavaDoc comments
7. [ ] No hardcoded paths
8. [ ] Ready untuk integration

---

## 🎯 Summary

**What's Included**:
- ✅ 15 sample tasks (tasks.csv)
- ✅ DataValidator class (comprehensive validation)
- ✅ FileManagerTest class (10 test cases)
- ✅ TEST_REPORT.md (full documentation)
- ✅ DIVISI2_README.md (detailed guide)
- ✅ Quick Start Guide (this file)

**Ready For**:
- ✅ Integration dengan FileManager (Bhayu)
- ✅ Integration dengan GUI (Candra)
- ✅ Testing oleh team
- ✅ Documentation oleh Divisi 4

**Status**: COMPLETE & READY TO PUSH 🚀

---

**Last Updated**: December 19, 2025
**Author**: Sasya Praditya Agnesia
**Division**: 2 - File Management & Data Persistence
