# TODO APPLICATION
## Aplikasi Manajemen Tugas & Produktivitas

**Pemrograman Berorientasi Objek (PBO)**  
**Kelompok: [Nomor Kelompok]**  
**Semester 3 - 2025**

---

## 📋 SLIDE 1: LATAR BELAKANG

### Tujuan Proyek

Mengembangkan aplikasi desktop untuk mengelola tugas harian pengguna dengan menerapkan prinsip-prinsip Object-Oriented Programming (OOP).

### Fitur Utama

✅ **Manajemen Tugas (CRUD)**
- Tambah, ubah, hapus tugas
- Data: judul, deskripsi, deadline, status, prioritas, kategori

✅ **Kategori & Prioritas**
- Set prioritas: Low, Medium, High
- Filter tugas berdasarkan status, prioritas, kategori

✅ **Reminder & Tracking**
- Deteksi tugas overdue
- Statistik real-time (total, pending, completed, overdue)

✅ **Penyimpanan Data**
- Simpan ke CSV file
- Auto-save setelah setiap operasi
- Load otomatis saat aplikasi dibuka

---

## 👥 SLIDE 2: PEMBAGIAN TUGAS

### Tim Pengembang

| Nama | NIM | Tugas |
|------|-----|-------|
| [Nama 1] | [NIM 1] | Model Layer (Task.java) + Data Validation |
| [Nama 2] | [NIM 2] | Business Logic (TaskManager, FileManager) |
| [Nama 3] | [NIM 3] | UI Layer (MainFrame, TaskDialog) |
| [Nama 4] | [NIM 4] | Theme Management + Testing |

### Pembagian Detail

**[Nama 1]:**
- Implementasi class Task
- DataValidator untuk validasi input
- Unit testing untuk model layer

**[Nama 2]:**
- TaskManager (CRUD operations)
- FileManager (CSV persistence)
- Business logic & filtering

**[Nama 3]:**
- MainFrame (tampilan utama)
- TaskDialog (form input)
- Event handlers & UI components

**[Nama 4]:**
- ThemeManager (dark/light mode)
- Integration testing
- Dokumentasi & laporan

---

## 🏗️ SLIDE 3: DESAIN UML - CLASS DIAGRAM

### Structure Overview

```
┌─────────────────────────────────────────┐
│              <<Main>>                   │
│              Main.java                  │
│  + main(String[] args): void            │
└─────────────────────────────────────────┘
                    │
                    │ creates
                    ↓
┌─────────────────────────────────────────┐
│           TaskManager                   │
├─────────────────────────────────────────┤
│ - tasks: List<Task>                     │
│ - fileManager: FileManager              │
├─────────────────────────────────────────┤
│ + addTask(Task): void                   │
│ + updateTask(String, Task): boolean     │
│ + deleteTask(String): boolean           │
│ + getAllTasks(): List<Task>             │
│ + filterByStatus(String): List<Task>    │
│ + searchTasks(String): List<Task>       │
│ + getStatistics(): String               │
└─────────────────────────────────────────┘
        │                    │
        │ uses               │ has-a
        ↓                    ↓
┌──────────────┐    ┌──────────────────┐
│     Task     │    │  FileManager     │
├──────────────┤    ├──────────────────┤
│ - id: String │    │ - fileName: Str  │
│ - title: Str │    ├──────────────────┤
│ - desc: Str  │    │ + saveToFile()   │
│ - deadline   │    │ + loadFromFile() │
│ - status     │    │ + parseTask()    │
│ - priority   │    └──────────────────┘
│ - category   │
│ - createdAt  │
├──────────────┤
│ + isOverdue()│
│ + toCsvStr() │
└──────────────┘
        ↑
        │ validates
        │
┌──────────────────┐
│  DataValidator   │
├──────────────────┤
│ + isValidTitle() │
│ + isValidStatus()│
│ + isValidPrior() │
└──────────────────┘
```

---

## 🎨 SLIDE 4: DESAIN UML - UI LAYER

### Presentation Layer

```
┌─────────────────────────────────────────┐
│            MainFrame                    │
│         extends JFrame                  │
├─────────────────────────────────────────┤
│ - taskManager: TaskManager              │
│ - themeManager: ThemeManager            │
│ - table: JTable                         │
│ - filterComboBox: JComboBox             │
├─────────────────────────────────────────┤
│ + initComponents(): void                │
│ + layoutComponents(): void              │
│ + refreshTable(): void                  │
│ + applyFilters(): void                  │
└─────────────────────────────────────────┘
        │                    │
        │ uses               │ uses
        ↓                    ↓
┌──────────────┐    ┌──────────────────┐
│ TaskDialog   │    │  ThemeManager    │
├──────────────┤    ├──────────────────┤
│ - task: Task │    │ - isDarkMode     │
│ - isEditMode │    │ - lightColors    │
├──────────────┤    │ - darkColors     │
│ + showDialog()│   ├──────────────────┤
│ + saveTask() │    │ + getInstance()  │
│ + validate() │    │ + getColor()     │
└──────────────┘    │ + detectTheme()  │
                    └──────────────────┘
```

---

## 💡 SLIDE 5: IMPLEMENTASI PBO - ENCAPSULATION

### Prinsip 1: Encapsulation

**Definisi:** Menyembunyikan detail internal dan hanya expose interface yang diperlukan.

**Implementasi di Task.java:**
```java
public class Task {
    // Private attributes - data tersembunyi
    private String id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String status;
    private String priority;
    
    // Public methods - akses terkontrol
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
```

**Benefit:**
- Data terlindungi dari akses langsung
- Validasi bisa ditambahkan di setter
- Flexibility untuk mengubah implementasi internal

---

## 🔍 SLIDE 6: IMPLEMENTASI PBO - ABSTRACTION

### Prinsip 2: Abstraction

**Definisi:** Menyembunyikan kompleksitas implementasi, fokus pada "apa yang dilakukan" bukan "bagaimana melakukannya".

**Implementasi di TaskManager.java:**
```java
public List<Task> searchTasks(String keyword) {
    // Kompleksitas filtering disembunyikan
    String lowerKeyword = keyword.toLowerCase();
    return tasks.stream()
        .filter(task -> 
            task.getTitle().toLowerCase().contains(lowerKeyword) ||
            task.getDescription().toLowerCase().contains(lowerKeyword)
        )
        .collect(Collectors.toList());
}
```

**Benefit:**
- User tidak perlu tahu detail implementasi
- Code lebih mudah digunakan
- Perubahan internal tidak affect caller

---

## 📦 SLIDE 7: IMPLEMENTASI PBO - MODULARITY

### Prinsip 3: Modularity

**Definisi:** Memecah sistem menjadi modul-modul independent dengan tanggung jawab spesifik.

**Implementasi:**

**FileManager.java** - Hanya handle File I/O
```java
public class FileManager {
    public void saveToFile(List<Task> tasks) { }
    public List<Task> loadFromFile() { }
    private Task parseTaskFromCsv(String line) { }
}
```

**TaskManager.java** - Hanya handle Business Logic
```java
public class TaskManager {
    public void addTask(Task task) { }
    public List<Task> filterByStatus(String status) { }
    public String getStatistics() { }
}
```

**DataValidator.java** - Hanya handle Validation
```java
public class DataValidator {
    public static boolean isValidTitle(String title) { }
    public static boolean isValidStatus(String status) { }
}
```

**Benefit:**
- Single Responsibility Principle
- Easy to maintain dan test
- Reusable components

---

## 🔗 SLIDE 8: IMPLEMENTASI PBO - COMPOSITION

### Prinsip 4: Composition

**Definisi:** Membangun objek kompleks dengan menggabungkan objek-objek sederhana (has-a relationship).

**Implementasi di MainFrame.java:**
```java
public class MainFrame extends JFrame {
    // Composition - MainFrame "has-a" TaskManager
    private TaskManager taskManager;
    
    // Composition - MainFrame "has-a" ThemeManager
    private ThemeManager themeManager;
    
    public MainFrame(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.themeManager = ThemeManager.getInstance();
    }
}
```

**Benefit:**
- Flexible object relationships
- Easier to modify behavior
- Avoids deep inheritance hierarchies

---

## 🎨 SLIDE 9: DESIGN PATTERN - SINGLETON

### Singleton Pattern

**Purpose:** Memastikan class hanya memiliki satu instance dan menyediakan global access point.

**Implementasi di ThemeManager.java:**
```java
public class ThemeManager {
    private static ThemeManager instance;
    
    // Private constructor - prevent external instantiation
    private ThemeManager() {
        initializeColorPalettes();
        detectOSTheme();
    }
    
    // Global access point
    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }
}
```

**Use Case:** Satu instance ThemeManager untuk seluruh aplikasi, memastikan konsistensi tema.

---

## 🏛️ SLIDE 10: DESIGN PATTERN - MVC

### MVC (Model-View-Controller) Pattern

**Separation of Concerns:**

**Model (Task.java)**
- Representasi data
- Business logic (isOverdue)
- Independent dari UI

**View (MainFrame.java, TaskDialog.java)**
- Tampilan UI
- Display data dari model
- Capture user input

**Controller (TaskManager.java)**
- Handle user actions
- Update model
- Coordinate view updates

**Benefit:**
- Clear separation
- Easy to test
- Flexible untuk UI changes

---

## 🏭 SLIDE 11: DESIGN PATTERN - FACTORY METHOD

### Factory Method Pattern

**Purpose:** Menyediakan interface untuk creating objects dengan strategi berbeda.

**Implementasi di Task.java:**
```java
// Factory method 1: Untuk task baru
public Task(String title, String description, 
            LocalDateTime deadline, String priority, String category) {
    this.id = UUID.randomUUID().toString();  // Auto-generate
    this.status = STATUS_PENDING;             // Default
    this.createdAt = LocalDateTime.now();     // Auto timestamp
}

// Factory method 2: Untuk loading dari file
public Task(String id, String title, String description,
            LocalDateTime deadline, String status, String priority,
            String category, LocalDateTime createdAt) {
    this.id = id;              // Preserve existing ID
    this.createdAt = createdAt; // Preserve timestamp
    // ... set other fields
}
```

**Benefit:** Flexible object creation sesuai context

---

## 📊 SLIDE 12: DEMO APLIKASI

### Live Demonstration

**1. Tambah Task Baru**
- Klik tombol "Add Task"
- Isi form (title, description, deadline, priority, category)
- Validasi otomatis
- Save ke CSV

**2. Filter & Search**
- Filter by status: Pending, In Progress, Completed
- Filter by priority: Low, Medium, High
- Search by keyword di title/description

**3. Edit Task**
- Double-click task di tabel
- Modify data
- Update otomatis

**4. Delete Task**
- Select task
- Klik "Delete"
- Konfirmasi penghapusan

**5. Theme Switching**
- Automatic detection (Windows dark mode)
- Semua komponen beradaptasi

---

## 📈 SLIDE 13: FITUR TAMBAHAN

### Beyond Requirements

**1. Automatic Theme Detection**
- Deteksi Windows dark mode via registry
- Apply color scheme otomatis
- Semua UI components adapt

**2. Advanced Statistics**
- Total tasks
- Pending, In Progress, Completed count
- Overdue tasks detection
- Real-time updates

**3. Data Validation**
- Comprehensive input validation
- Title: 3-100 characters
- Description: max 500 characters
- Status & Priority: dari predefined list

**4. Multiple Date Format Support**
- yyyy-MM-dd (date only)
- yyyy-MM-dd HH:mm:ss (full datetime)
- Auto-conversion

---

## 🔧 SLIDE 14: TEKNOLOGI & TOOLS

### Technology Stack

**Programming Language:**
- Java 21 (LTS)

**GUI Framework:**
- Java Swing
- Custom UI components

**Data Persistence:**
- CSV File Storage
- Auto-save mechanism

**Development Tools:**
- IntelliJ IDEA
- Git & GitHub
- Maven/Gradle (optional)

**Testing:**
- Manual testing (100 sample data)
- Integration testing

---

## 📊 SLIDE 15: CODE METRICS

### Statistik Project

**Lines of Code:**
| Component | LOC | Percentage |
|-----------|-----|------------|
| MainFrame.java | 1,100 | 44% |
| TaskDialog.java | 490 | 20% |
| FileManager.java | 260 | 10% |
| TaskManager.java | 230 | 9% |
| Task.java | 200 | 8% |
| ThemeManager.java | 185 | 7% |
| DataValidator.java | 190 | 8% |
| Main.java | 60 | 2% |
| **Total** | **~2,715** | **100%** |

**Complexity:**
- Classes: 8
- Methods: ~80
- Design Patterns: 4 (Singleton, MVC, Factory, Strategy)

---

## ✅ SLIDE 16: KESIMPULAN

### Pencapaian

**✅ Requirement Terpenuhi:**
- Manajemen tugas (tambah, ubah, hapus)
- Kategori & prioritas
- Filter tugas
- Penyimpanan data (CSV)

**✅ Implementasi PBO:**
- 4 Prinsip OOP (Encapsulation, Abstraction, Modularity, Composition)
- 4 Design Patterns (Singleton, MVC, Factory Method, Strategy)

**✅ Kualitas Code:**
- Clean code architecture
- Comprehensive validation
- Error handling
- Documentation

**✅ User Experience:**
- Intuitive interface
- Responsive design
- Automatic theme detection

---

## 🚀 SLIDE 17: FUTURE IMPROVEMENTS

### Pengembangan Selanjutnya

**Database Integration:**
- Migrasi ke SQLite/MySQL
- Better query performance

**Advanced Features:**
- Task dependencies
- Recurring tasks
- Email notifications

**Collaboration:**
- Multi-user support
- Cloud synchronization
- Task sharing

**Mobile App:**
- Android/iOS version
- Cross-platform sync

---

## 🙏 SLIDE 18: Q&A

### Terima Kasih!

**Tim Pengembang:**
- [Nama 1] - [NIM 1]
- [Nama 2] - [NIM 2]
- [Nama 3] - [NIM 3]
- [Nama 4] - [NIM 4]

**Repository:**
- GitHub: [Link Repository]
- Documentation: README.md
- Sample Data: 100 tasks

**Pertanyaan?**

---

**Todo Application - Pemrograman Berorientasi Objek**  
**Semester 3 - 2025**
