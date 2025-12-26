# LAPORAN AKHIR SEMESTER
## IMPLEMENTASI PRINSIP OBJECT-ORIENTED PROGRAMMING DALAM APLIKASI TODO BERBASIS JAVA SWING

---

**Disusun Oleh:**  
[Nama Mahasiswa]  
[NIM]

**Program Studi:** Teknik Informatika  
**Mata Kuliah:** Pemrograman Berorientasi Objek (PBO)  
**Semester:** 3  
**Tahun Akademik:** 2025

---

## DAFTAR ISI

**HALAMAN JUDUL** .................................................. i  
**DAFTAR ISI** ...................................................... ii  
**DAFTAR GAMBAR** .................................................. iii  
**DAFTAR TABEL** ................................................... iv

**BAB I PENDAHULUAN** .............................................. 1  
1.1 Latar Belakang ................................................. 1  
1.2 Rumusan Masalah ................................................ 2  
1.3 Tujuan ......................................................... 2  
1.4 Manfaat ........................................................ 3  
1.5 Batasan Masalah ................................................ 3  
1.6 Pembagian Tugas Tim ............................................ 4

**BAB II LANDASAN TEORI** .......................................... 4  
2.1 Object-Oriented Programming .................................... 4  
2.2 Prinsip-Prinsip OOP ............................................ 5  
2.3 Design Patterns ................................................ 7  
2.4 Java Swing Framework ........................................... 9  
2.5 MVC Architecture ............................................... 10

**BAB III ANALISIS DAN PERANCANGAN** ............................... 11  
3.1 Analisis Kebutuhan ............................................. 11  
3.2 Perancangan Arsitektur ......................................... 12  
3.3 Perancangan Database ........................................... 14  
3.4 Perancangan User Interface ..................................... 15

**BAB IV IMPLEMENTASI** ............................................ 16  
4.1 Implementasi Model Layer ....................................... 16  
4.2 Implementasi Business Logic Layer .............................. 18  
4.3 Implementasi Presentation Layer ................................ 20  
4.4 Implementasi Design Patterns ................................... 22

**BAB V PENGUJIAN DAN PEMBAHASAN** ................................. 24  
5.1 Pengujian Fungsional ........................................... 24  
5.2 Pengujian Non-Fungsional ....................................... 26  
5.3 Analisis Hasil Pengujian ....................................... 27

**BAB VI PENUTUP** ................................................. 28  
6.1 Kesimpulan ..................................................... 28  
6.2 Saran .......................................................... 29

**DAFTAR PUSTAKA** ................................................. 30

---

## BAB I  
## PENDAHULUAN

### 1.1 Latar Belakang

Manajemen tugas merupakan aspek penting dalam kehidupan sehari-hari, terutama bagi mahasiswa dan profesional yang memiliki banyak aktivitas. Aplikasi manajemen tugas digital dapat membantu pengguna mengorganisir pekerjaan dengan lebih efisien dan terstruktur.

Dalam pengembangan aplikasi modern, penerapan prinsip Object-Oriented Programming (OOP) menjadi fundamental untuk menciptakan software yang maintainable, scalable, dan reusable. OOP menyediakan paradigma pemrograman yang memungkinkan developer untuk memodelkan real-world entities sebagai objects dengan attributes dan behaviors.

Todo Application dikembangkan sebagai implementasi praktis dari konsep-konsep OOP menggunakan bahasa pemrograman Java. Aplikasi ini dirancang dengan menerapkan empat pilar utama OOP (Encapsulation, Abstraction, Modularity, dan Composition) serta beberapa design patterns industry-standard seperti Singleton, MVC, dan Factory Method.

### 1.2 Rumusan Masalah

Berdasarkan latar belakang di atas, rumusan masalah dalam project ini adalah:

1. Bagaimana mengimplementasikan prinsip-prinsip OOP dalam aplikasi manajemen tugas?
2. Bagaimana menerapkan design patterns untuk meningkatkan kualitas arsitektur aplikasi?
3. Bagaimana merancang user interface yang intuitif dan responsive?
4. Bagaimana mengimplementasikan data persistence menggunakan CSV file?

### 1.3 Tujuan

Tujuan dari pengembangan aplikasi ini adalah:

1. Mengimplementasikan prinsip-prinsip OOP secara komprehensif
2. Menerapkan design patterns industry-standard (Singleton, MVC, Factory Method, Strategy)
3. Membuat aplikasi desktop yang user-friendly dengan Java Swing
4. Mengimplementasikan CRUD operations dengan data validation
5. Menyediakan fitur filtering, searching, dan statistics
6. Mengimplementasikan automatic theme detection (dark/light mode)

### 1.4 Manfaat

Manfaat yang diharapkan dari aplikasi ini:

**Bagi Pengguna:**
- Memudahkan manajemen tugas sehari-hari
- Interface yang intuitif dan mudah digunakan
- Automatic theme adaptation sesuai preferensi sistem

**Bagi Developer:**
- Referensi implementasi OOP yang baik
- Contoh penerapan design patterns
- Clean code architecture yang maintainable

**Bagi Akademisi:**
- Studi kasus implementasi konsep OOP
- Dokumentasi lengkap untuk pembelajaran

### 1.5 Batasan Masalah

Batasan masalah dalam pengembangan aplikasi ini:

1. Aplikasi bersifat standalone (single-user)
2. Data persistence menggunakan CSV file (bukan database)
3. Theme detection hanya support Windows OS
4. Tidak ada fitur cloud synchronization
5. Tidak ada fitur collaboration/sharing

### 1.6 Pembagian Tugas Tim

Project ini dikerjakan oleh **Kelompok 3** dengan total 8 anggota yang dibagi menjadi 4 divisi berdasarkan tanggung jawab masing-masing.

#### 1.6.1 Struktur Tim

**DIVISI 1: Model & Business Logic**
- TRESNA FEBRYAN BERNADINE ICO
- NI WAYAN SINTYA KUMARA DEWI

**DIVISI 2: File Management & Data Persistence**
- NYOMAN BHAYU WIRATANAYA
- SASYA PRADITYA AGNESIA

**DIVISI 3: User Interface & Integration**
- NYOMAN CANDRA NATA INDITAMA

**DIVISI 4: Documentation & Deployment**
- NI WAYAN LISTIADEWI (Ketua)
- VANDA VICADA KARLIE TJOENG
- STEVEN ABIGAIL HASTING RENNIE

#### 1.6.2 Pembagian Tanggung Jawab

**Divisi 1 - Model & Business Logic:**
- Implementasi class `Task` dengan semua attributes dan methods
- Implementasi class `TaskManager` untuk CRUD operations
- Implementasi filtering dan searching functionality
- Unit testing untuk model layer

**Divisi 2 - File Management & Data Persistence:**
- Implementasi class `FileManager` untuk save/load operations
- Handling CSV file format dan error handling
- Membuat sample data untuk testing (100 tasks)
- Testing file operations dengan berbagai skenario

**Divisi 3 - User Interface & Integration:**
- Setup GitHub repository dan project structure
- Implementasi `MainFrame` (main window dengan table view)
- Implementasi `TaskDialog` (form untuk add/edit task)
- Implementasi `ThemeManager` (dark/light mode detection)
- Integrasi semua komponen sistem
- End-to-end testing

**Divisi 4 - Documentation & Deployment:**
- Membuat UML Class Diagram
- Menulis laporan project (BAB I - BAB VI)
- Build aplikasi ke JAR file
- Membuat user manual
- Membuat slide presentasi
- Persiapan presentasi UAS

#### 1.6.3 Timeline Pengerjaan

**Minggu 1 (15-22 Desember 2025):**
- Setup project dan GitHub repository
- Desain UML Class Diagram
- Development model dan business logic layer
- Development file management layer

**Minggu 2 (23-30 Desember 2025):**
- Development UI layer
- System integration
- Testing dan bug fixing
- Dokumentasi dan laporan
- Build JAR dan deployment
- Persiapan presentasi

#### 1.6.4 Deliverables Per Divisi

**Divisi 1:**
- Task.java (200 LOC)
- TaskManager.java (230 LOC)
- Unit test cases

**Divisi 2:**
- FileManager.java (260 LOC)
- DataValidator.java (190 LOC)
- Sample data (tasks.csv - 100 records)

**Divisi 3:**
- MainFrame.java (1,100 LOC)
- TaskDialog.java (490 LOC)
- ThemeManager.java (185 LOC)
- Main.java (60 LOC)

**Divisi 4:**
- UML Class Diagram (PNG/PDF)
- Laporan Project (PDF - 20+ halaman)
- User Manual (PDF - 5 halaman)
- Slide Presentasi (18 slides)
- Executable JAR file

---

## BAB II  
## LANDASAN TEORI

### 2.1 Object-Oriented Programming

Object-Oriented Programming (OOP) adalah paradigma pemrograman yang mengorganisir software design di sekitar data (objects) daripada functions dan logic. Object adalah instance dari class yang memiliki attributes (data) dan methods (behaviors).

**Karakteristik OOP:**
- Fokus pada objects daripada procedures
- Data dan methods dikemas dalam satu unit (class)
- Mendukung code reusability melalui inheritance
- Mendukung polymorphism untuk flexibility

### 2.2 Prinsip-Prinsip OOP

#### 2.2.1 Encapsulation

Encapsulation adalah prinsip menyembunyikan internal details dari sebuah object dan hanya mengekspos interface yang diperlukan. Ini dicapai dengan:
- Membuat attributes private
- Menyediakan public getter/setter methods
- Melindungi data dari akses tidak sah

**Implementasi dalam Todo App:**
```java
public class Task {
    private String id;        // Hidden
    private String title;     // Hidden
    
    public String getTitle() {
        return title;
    }
}
```

#### 2.2.2 Abstraction

Abstraction adalah proses menyembunyikan implementation details dan hanya menampilkan functionality kepada user. Fokus pada "what it does" bukan "how it does".

**Implementasi dalam Todo App:**
```java
public List<Task> searchTasks(String keyword) {
    // Complex filtering logic hidden
    return tasks.stream()
        .filter(task -> matches(task, keyword))
        .collect(Collectors.toList());
}
```

#### 2.2.3 Modularity

Modularity adalah prinsip memecah sistem menjadi modules yang independent dan cohesive. Setiap module memiliki single responsibility.

**Implementasi dalam Todo App:**
- FileManager: Handle file I/O
- TaskManager: Handle business logic
- DataValidator: Handle validation

#### 2.2.4 Composition

Composition adalah teknik building complex objects dengan combining simpler objects. Menggunakan "has-a" relationship.

**Implementasi dalam Todo App:**
```java
public class MainFrame {
    private TaskManager taskManager;    // Has-a
    private ThemeManager themeManager;  // Has-a
}
```

### 2.3 Design Patterns

#### 2.3.1 Singleton Pattern

Pattern yang memastikan class hanya memiliki satu instance dan menyediakan global access point.

**Use Case:** ThemeManager - satu instance untuk seluruh aplikasi

#### 2.3.2 MVC Pattern

Pattern yang memisahkan aplikasi menjadi tiga komponen:
- **Model:** Data dan business logic
- **View:** User interface
- **Controller:** Mediator antara Model dan View

#### 2.3.3 Factory Method Pattern

Pattern yang menyediakan interface untuk creating objects dengan letting subclasses decide which class to instantiate.

**Use Case:** Task constructors dengan different creation strategies

#### 2.3.4 Strategy Pattern

Pattern yang mendefinisikan family of algorithms dan membuat mereka interchangeable.

**Use Case:** Multiple date parsing strategies di FileManager

### 2.4 Java Swing Framework

Java Swing adalah GUI toolkit untuk Java yang menyediakan:
- Rich set of components (JFrame, JTable, JButton, dll)
- Pluggable look and feel
- Event-driven programming model
- Layout managers untuk responsive design

### 2.5 MVC Architecture

Model-View-Controller adalah architectural pattern yang memisahkan aplikasi menjadi tiga interconnected components:

**Model:**
- Represents data dan business rules
- Independent dari UI
- Notifies observers tentang changes

**View:**
- Presents data kepada user
- Receives user input
- Updates when model changes

**Controller:**
- Handles user input
- Updates model
- Selects view untuk response

---

## BAB III  
## ANALISIS DAN PERANCANGAN

### 3.1 Analisis Kebutuhan

#### 3.1.1 Kebutuhan Fungsional

1. **Task Management**
   - Create new task
   - Read/view task details
   - Update existing task
   - Delete task

2. **Filtering & Searching**
   - Filter by status (Pending, In Progress, Completed, Cancelled)
   - Filter by priority (Low, Medium, High)
   - Filter by category
   - Search by keyword

3. **Data Persistence**
   - Save tasks to CSV file
   - Load tasks from CSV file
   - Auto-save after operations

4. **User Interface**
   - Display tasks in table
   - Form dialog untuk add/edit
   - Statistics dashboard
   - Theme support (dark/light)

#### 3.1.2 Kebutuhan Non-Fungsional

1. **Performance:** Response time < 1 detik
2. **Usability:** Intuitive interface
3. **Reliability:** Data integrity terjaga
4. **Maintainability:** Clean code architecture
5. **Portability:** Cross-platform (Windows, Mac, Linux)

### 3.2 Perancangan Arsitektur

#### 3.2.1 Package Structure

```
src/
├── Main.java              # Entry point
├── model/                 # Domain layer
│   └── Task.java
├── manager/               # Business logic
│   ├── TaskManager.java
│   ├── FileManager.java
│   └── DataValidator.java
└── ui/                    # Presentation
    ├── MainFrame.java
    ├── TaskDialog.java
    └── ThemeManager.java
```

#### 3.2.2 Class Diagram

**Task (Model)**
- Attributes: id, title, description, deadline, status, priority, category, createdAt
- Methods: getters, setters, isOverdue(), toCsvString()

**TaskManager (Controller)**
- Attributes: tasks, fileManager
- Methods: addTask(), updateTask(), deleteTask(), filterByStatus(), searchTasks()

**MainFrame (View)**
- Attributes: taskManager, themeManager, table, buttons
- Methods: initComponents(), layoutComponents(), setupEventHandlers()

### 3.3 Perancangan Database

#### 3.3.1 CSV File Structure

```csv
id,title,description,deadline,status,priority,category,createdAt
```

**Field Specifications:**
- id: UUID string (unique identifier)
- title: String (3-100 chars)
- description: String (max 500 chars)
- deadline: LocalDateTime (yyyy-MM-dd HH:mm:ss)
- status: Enum (Pending, In Progress, Completed, Cancelled)
- priority: Enum (Low, Medium, High)
- category: String (predefined list)
- createdAt: LocalDateTime (auto-generated)

### 3.4 Perancangan User Interface

#### 3.4.1 Main Window

**Components:**
- Menu bar (File, Edit, View, Help)
- Toolbar (Add, Edit, Delete, Refresh)
- Filter panel (Status, Priority, Category)
- Search panel (Keyword input)
- Table (Task list display)
- Statistics panel (Bottom status bar)

#### 3.4.2 Task Dialog

**Components:**
- Title input (JTextField)
- Description input (JTextArea)
- Deadline date picker (JSpinner)
- Deadline time picker (JSpinner)
- Priority dropdown (JComboBox)
- Category dropdown (JComboBox)
- Action buttons (Save, Cancel)

---

## BAB IV  
## IMPLEMENTASI

### 4.1 Implementasi Model Layer

#### 4.1.1 Task Class

**Constants:**
```java
public static final String STATUS_PENDING = "Pending";
public static final String STATUS_IN_PROGRESS = "In Progress";
public static final String STATUS_COMPLETED = "Completed";
public static final String PRIORITY_LOW = "Low";
public static final String PRIORITY_MEDIUM = "Medium";
public static final String PRIORITY_HIGH = "High";
```

**Constructors:**
- Constructor untuk new task (auto-generate ID)
- Constructor untuk loading dari file (preserve ID)

**Key Methods:**
- `isOverdue()`: Check apakah task sudah melewati deadline
- `toCsvString()`: Convert task ke CSV format

### 4.2 Implementasi Business Logic Layer

#### 4.2.1 TaskManager

**CRUD Operations:**
```java
public void addTask(Task task)
public boolean updateTask(String taskId, Task updatedTask)
public boolean deleteTask(String taskId)
public Task getTask(String taskId)
public List<Task> getAllTasks()
```

**Filtering & Searching:**
```java
public List<Task> filterByStatus(String status)
public List<Task> filterByPriority(String priority)
public List<Task> filterByCategory(String category)
public List<Task> searchTasks(String keyword)
```

**Statistics:**
```java
public String getStatistics()
```

#### 4.2.2 FileManager

**File Operations:**
```java
public void saveToFile(List<Task> tasks) throws IOException
public List<Task> loadFromFile() throws IOException
```

**CSV Parsing:**
- Handle quoted fields dengan koma
- Support multiple date formats
- Error handling untuk corrupted data

#### 4.2.3 DataValidator

**Validation Methods:**
```java
public static boolean isValidTitle(String title)
public static boolean isValidDescription(String description)
public static boolean isValidStatus(String status)
public static boolean isValidPriority(String priority)
```

### 4.3 Implementasi Presentation Layer

#### 4.3.1 MainFrame

**Initialization:**
```java
private void initComponents()
private void layoutComponents()
private void setupEventHandlers()
```

**Event Handlers:**
- Add button → Show TaskDialog
- Edit button → Load task data ke dialog
- Delete button → Confirm dan delete
- Filter combo → Apply filter
- Search field → Real-time search

**Custom Table Renderer:**
- Color-coded status cells
- Color-coded priority cells
- Dynamic opacity based on theme

#### 4.3.2 TaskDialog

**Form Validation:**
- Validate semua inputs sebelum save
- Show error messages untuk invalid data
- Prevent save jika ada validation errors

**Date/Time Pickers:**
- JSpinner untuk date selection
- JSpinner untuk time selection
- Default value: current date/time

#### 4.3.3 ThemeManager

**Theme Detection:**
```java
private void detectOSTheme()
private String getWindowsRegistryValue(String key, String valueName)
```

**Color Palettes:**
- Light mode colors
- Dark mode colors
- Semantic colors (primary, success, warning, danger)

### 4.4 Implementasi Design Patterns

#### 4.4.1 Singleton Pattern

```java
public class ThemeManager {
    private static ThemeManager instance;
    
    private ThemeManager() {
        initializeColorPalettes();
        detectOSTheme();
    }
    
    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }
}
```

#### 4.4.2 MVC Pattern

**Separation:**
- Model: Task.java (data + business logic)
- View: MainFrame.java, TaskDialog.java (UI)
- Controller: TaskManager.java (orchestration)

#### 4.4.3 Factory Method Pattern

**Task Creation:**
```java
// Factory method for new task
public Task(String title, String description, ...) {
    this.id = UUID.randomUUID().toString();
    this.status = STATUS_PENDING;
    this.createdAt = LocalDateTime.now();
}
```

#### 4.4.4 Strategy Pattern

**Date Parsing:**
```java
if (deadlineStr.contains(" ")) {
    deadline = LocalDateTime.parse(deadlineStr, FORMATTER);
} else {
    deadline = LocalDate.parse(deadlineStr, DATE_FORMATTER)
            .atTime(23, 59, 59);
}
```

---

## BAB V  
## PENGUJIAN DAN PEMBAHASAN

### 5.1 Pengujian Fungsional

#### 5.1.1 Test Case: Create Task

**Input:**
- Title: "Buat Laporan UAS"
- Description: "Menulis laporan lengkap"
- Deadline: 2025-12-25
- Priority: High
- Category: Academic

**Expected Output:**
- Task berhasil dibuat
- Muncul di tabel
- Data tersimpan di CSV
- Statistics terupdate

**Result:** ✅ PASS

#### 5.1.2 Test Case: Filter by Status

**Input:** Filter = "Pending"

**Expected Output:**
- Hanya task dengan status Pending yang ditampilkan
- Task lain disembunyikan

**Result:** ✅ PASS

#### 5.1.3 Test Case: Search Functionality

**Input:** Keyword = "laporan"

**Expected Output:**
- Task dengan "laporan" di title/description ditampilkan
- Case-insensitive matching

**Result:** ✅ PASS

### 5.2 Pengujian Non-Fungsional

#### 5.2.1 Performance Testing

**Test:** Load 100 tasks

**Result:**
- Load time: < 500ms
- UI responsive
- No lag saat filtering/searching

**Conclusion:** ✅ PASS

#### 5.2.2 Usability Testing

**Criteria:**
- Intuitive interface
- Clear labels
- Consistent design

**Result:** ✅ PASS (feedback dari 5 users)

### 5.3 Analisis Hasil Pengujian

**Strengths:**
- Semua fitur berfungsi sesuai requirement
- Performance baik dengan 100+ tasks
- UI intuitif dan responsive
- Theme detection bekerja dengan baik

**Weaknesses:**
- Theme detection hanya support Windows
- Tidak ada undo/redo functionality
- Tidak ada data backup otomatis

---

## BAB VI  
## PENUTUP

### 6.1 Kesimpulan

Berdasarkan hasil implementasi dan pengujian, dapat disimpulkan:

1. **Prinsip OOP berhasil diterapkan:**
   - Encapsulation: Private attributes dengan public getters/setters
   - Abstraction: Complex logic disembunyikan dari caller
   - Modularity: Clear separation of concerns
   - Composition: Has-a relationships

2. **Design Patterns terimplementasi dengan baik:**
   - Singleton untuk ThemeManager
   - MVC untuk arsitektur aplikasi
   - Factory Method untuk object creation
   - Strategy untuk flexible algorithms

3. **Aplikasi memenuhi semua requirement fungsional:**
   - CRUD operations complete
   - Filtering & searching berfungsi
   - Data persistence reliable
   - UI responsive dan intuitif

4. **Code quality tinggi:**
   - Clean code principles
   - Comprehensive documentation
   - Proper error handling
   - Maintainable architecture

### 6.2 Saran

**Untuk Pengembangan Lebih Lanjut:**

1. **Database Integration:**
   - Migrasi dari CSV ke SQLite/MySQL
   - Better query performance
   - Transaction support

2. **Advanced Features:**
   - Task dependencies
   - Recurring tasks
   - Email notifications
   - File attachments

3. **Cross-Platform Theme Detection:**
   - Support macOS dan Linux
   - Custom theme editor

4. **Collaboration Features:**
   - Multi-user support
   - Cloud synchronization
   - Task sharing

5. **Testing:**
   - Unit tests untuk semua methods
   - Integration tests
   - Automated testing dengan JUnit

---

## DAFTAR PUSTAKA

1. Gamma, E., Helm, R., Johnson, R., & Vlissides, J. (1994). *Design Patterns: Elements of Reusable Object-Oriented Software*. Addison-Wesley.

2. Martin, R. C. (2008). *Clean Code: A Handbook of Agile Software Craftsmanship*. Prentice Hall.

3. Bloch, J. (2018). *Effective Java* (3rd ed.). Addison-Wesley.

4. Oracle. (2024). *Java SE Documentation*. Retrieved from https://docs.oracle.com/javase/

5. Horstmann, C. S., & Cornell, G. (2019). *Core Java Volume I - Fundamentals* (11th ed.). Prentice Hall.

---

**LAMPIRAN**

**Lampiran A:** Source Code  
**Lampiran B:** Screenshot Aplikasi  
**Lampiran C:** User Manual  
**Lampiran D:** Test Results

---

**End of Report**
