# UML Class Diagram - Todo Application

## 📋 Overview

Diagram UML Class ini menggambarkan struktur lengkap aplikasi Todo, termasuk semua class, attributes, methods, dan relationships antar class.

## 🎯 Komponen Utama

### 1. **Model Layer** (Domain)
- **Task**: Entity class yang merepresentasikan tugas
  - Attributes: id, title, description, deadline, status, priority, category, createdAt
  - Methods: getters, setters, isOverdue(), toCsvString()
  - Constants: STATUS_*, PRIORITY_*

### 2. **Manager Layer** (Business Logic)
- **TaskManager**: Controller untuk operasi CRUD dan business logic
  - CRUD operations: add, update, delete, get
  - Filtering: by status, priority, category
  - Searching: by keyword
  - Statistics calculation

- **FileManager**: Handle data persistence ke CSV
  - Save/load operations
  - CSV parsing
  - Error handling

- **DataValidator**: Validasi input data
  - Validation methods untuk semua fields
  - Static utility methods
  - Predefined valid values

### 3. **UI Layer** (Presentation)
- **MainFrame**: Main application window
  - Table display
  - Filter & search components
  - Event handlers
  - Statistics panel

- **TaskDialog**: Modal dialog untuk add/edit task
  - Form inputs
  - Validation
  - Date/time pickers

- **ThemeManager**: Singleton untuk theme management
  - Dark/light mode detection
  - Color palettes
  - OS theme integration

## 🔗 Relationships

### Composition (Strong "has-a")
- `TaskManager *-- Task`: TaskManager memiliki dan mengelola collection of Tasks
- `MainFrame --> TaskManager`: MainFrame memiliki instance TaskManager
- `MainFrame --> ThemeManager`: MainFrame menggunakan ThemeManager

### Dependency (Weak "uses")
- `Main ..> TaskManager`: Main creates TaskManager
- `Main ..> MainFrame`: Main creates MainFrame
- `TaskManager ..> DataValidator`: TaskManager validates using DataValidator
- `FileManager ..> Task`: FileManager creates Task objects
- `MainFrame ..> TaskDialog`: MainFrame creates TaskDialog

### Design Patterns
- **Singleton**: ThemeManager (single instance)
- **MVC**: Model (Task), View (MainFrame, TaskDialog), Controller (TaskManager)
- **Factory Method**: Task constructors (different creation strategies)

## 📐 Diagram Details

**Total Classes**: 8
- Model: 1 (Task)
- Manager: 3 (TaskManager, FileManager, DataValidator)
- UI: 3 (MainFrame, TaskDialog, ThemeManager)
- Main: 1 (Main)

**Key Features Shown**:
- ✅ All attributes with visibility modifiers
- ✅ All public methods with parameters and return types
- ✅ Constants and static members
- ✅ Relationships with cardinality
- ✅ Package organization
- ✅ Design pattern notes

## 🛠️ How to Generate Diagram

### Option 1: Online PlantUML Editor
1. Buka https://www.plantuml.com/plantuml/uml/
2. Copy isi file `class_diagram.puml`
3. Paste ke editor
4. Diagram akan ter-generate otomatis
5. Download sebagai PNG/SVG

### Option 2: VS Code Extension
1. Install extension "PlantUML" di VS Code
2. Buka file `class_diagram.puml`
3. Press `Alt+D` untuk preview
4. Right-click → Export → PNG/SVG

### Option 3: Command Line
```bash
# Install PlantUML
# Download plantuml.jar dari https://plantuml.com/download

# Generate diagram
java -jar plantuml.jar class_diagram.puml

# Output: class_diagram.png
```

### Option 4: IntelliJ IDEA
1. Install plugin "PlantUML Integration"
2. Buka file `class_diagram.puml`
3. Diagram preview akan muncul di panel kanan
4. Right-click → Export Diagram → PNG

## 📊 Penjelasan Relationship

### 1. Main → TaskManager (Dependency)
```java
Main creates TaskManager instance
Main.main() → new TaskManager(DATA_FILE)
```

### 2. TaskManager *-- Task (Composition)
```java
TaskManager owns and manages Tasks
private List<Task> tasks;
```

### 3. TaskManager → FileManager (Association)
```java
TaskManager uses FileManager for persistence
private FileManager fileManager;
```

### 4. MainFrame → TaskManager (Association)
```java
MainFrame uses TaskManager for operations
private TaskManager taskManager;
```

### 5. ThemeManager (Singleton)
```java
Single instance pattern
private static ThemeManager instance;
public static ThemeManager getInstance()
```

## 🎨 Color Coding (in diagram)

- **White background**: Regular classes
- **Notes**: Explain design patterns and responsibilities
- **Packages**: Group related classes

## 📝 Notes

**Encapsulation**:
- All attributes are private (-)
- Public getters/setters (+)
- Controlled access to data

**Abstraction**:
- Complex logic hidden in methods
- Simple public interfaces
- Implementation details private

**Modularity**:
- Clear package separation
- Single Responsibility Principle
- Each class has specific purpose

**Composition**:
- TaskManager has-a FileManager
- MainFrame has-a TaskManager
- Strong ownership relationships

## 🔍 Verification Checklist

- [x] All classes included
- [x] All attributes shown with types
- [x] All public methods shown
- [x] Relationships clearly defined
- [x] Cardinality specified
- [x] Design patterns noted
- [x] Package structure visible
- [x] Visibility modifiers correct

## 📚 References

- PlantUML Documentation: https://plantuml.com/class-diagram
- UML Class Diagram Guide: https://www.uml-diagrams.org/class-diagrams-overview.html

---

**Created by**: Kelompok 3 - Divisi 4 (Vanda)  
**Date**: Desember 2025  
**Version**: 1.0
