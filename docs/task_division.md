# Pembagian Tugas Kelompok 3 - Aplikasi Manajemen Tugas

## 👥 Anggota Kelompok
**Kelompok 3: Manajemen Tugas (Todo App)**

---

## 📅 Timeline Project

**Kickoff:** Senin, 15 Desember 2025 (Minggu 1)  
**Deadline UML:** Senin, 22 Desember 2025  
**Deadline Final:** Senin, 30 Desember 2025  
**Total Waktu:** +-2 Minggu

---

## 📋 Pembagian Tugas Per Divisi

### 🔷 **DIVISI 1: Model & Business Logic**
**Anggota:** 
- **TRESNA FEBRYAN BERNADINE ICO**
- **NI WAYAN SINTYA KUMARA DEWI**

**Tanggung Jawab:** Implementasi Model dan Business Logic

**Tugas:**
- ✅ **Class Task** (Task.java):
  - Semua atribut (id, title, description, deadline, status, priority, category, createdAt)
  - Constructor
  - Semua Getter & Setter
  - Method isOverdue()
  - Method toString()
  
- ✅ **Class TaskManager** (TaskManager.java):
  - Method addTask(), updateTask(), deleteTask()
  - Method getTask(), getAllTasks()
  - Method filterByStatus(), filterByPriority(), filterByCategory()
  - Method searchTask()
  - Method loadData() & saveData()
  
- ✅ **Testing**: Unit test untuk Task dan TaskManager

**Pembagian Internal:**
- **Tresna**: Class Task + TaskManager (CRUD methods)
- **Sintya**: TaskManager (Filter & Search methods) + Testing

**Estimasi Waktu:** 6-8 jam per orang  
**Deadline:** Jumat, 21 Desember 2025

---

### 🔶 **DIVISI 2: File Management & Data Persistence**
**Anggota:**
- **NYOMAN BHAYU WIRATANAYA**
- **SASYA PRADITYA AGNESIA**

**Tanggung Jawab:** Pengelolaan File dan Data

**Tugas:**
- ✅ **Class FileManager** (FileManager.java):
  - Method saveToFile() - simpan task list ke CSV
  - Method loadFromFile() - baca task list dari CSV
  - Handle error (file tidak ada, korup, permission denied)
  - Format CSV yang konsisten
  
- ✅ **Data Management**:
  - Membuat sample data dummy untuk testing (minimal 10 tasks)
  - Setup struktur folder data
  - Validasi format data
  
- ✅ **Testing**: Testing save/load operations dengan berbagai skenario

**Pembagian Internal:**
- **Bhayu**: Class FileManager + error handling
- **Sasya**: Sample data + Testing file operations + validasi

**Estimasi Waktu:** 5-7 jam per orang  
**Deadline:** Jumat, 21 Desember 2025

---

### 🔷 **DIVISI 3: User Interface, Integration & GitHub**
**Anggota:**
- **NYOMAN CANDRA NATA INDITAMA**

**Tanggung Jawab:** Implementasi GUI, Integrasi Sistem, dan GitHub Management

**Tugas:**

#### **A. GitHub Management** (Dikerjakan di Minggu 1)
- ✅ **Setup GitHub Repository**:
  - Membuat repository baru dengan nama yang sesuai
  - Setup .gitignore untuk Java project
  - Membuat struktur project folder yang rapi
  - Membuat README.md initial (outline project)
  - Invite semua anggota sebagai collaborator
  - Setup branch structure (main, dev)
  
**Struktur Folder:**
```
todo-app/
├── src/
│   └── com/todoapp/
│       ├── model/          [Divisi 1]
│       ├── manager/        [Divisi 1 & 2]
│       ├── ui/             [Divisi 3]
│       └── Main.java       [Divisi 3]
├── data/
│   └── tasks.csv           [Divisi 2]
├── docs/
│   ├── UML/                [Divisi 4]
│   └── laporan/            [Divisi 4]
├── .gitignore
└── README.md
```

**Deadline GitHub Setup:** Rabu, 18 Desember 2025

---

#### **B. User Interface (GUI)** (Dikerjakan di Minggu 2)
- ✅ **Class MainFrame** (MainFrame.java):
  - Setup JFrame utama dengan layout yang rapi
  - JTable untuk menampilkan daftar tugas
  - Panel tombol (Add, Edit, Delete, Refresh)
  - Filter panel (ComboBox untuk status, priority, category)
  - Search field dengan real-time search
  - Method refreshTable()
  - Event handler untuk semua tombol dan filter
  
- ✅ **Class TaskDialog** (TaskDialog.java):
  - Dialog form untuk input/edit tugas
  - Input fields: title, description, priority, category, deadline
  - Validasi input user
  - Method showDialog() yang return Task object
  - Handle mode Add dan Edit dalam satu dialog
  
- ✅ **Class Main/App** (Main.java):
  - Entry point aplikasi
  - Initialize semua komponen
  - Load data awal

---

#### **C. Integrasi Sistem** (Dikerjakan di Minggu 2)
- ✅ **Integration Tasks**:
  - Menghubungkan MainFrame dengan TaskDialog
  - Menghubungkan GUI dengan TaskManager
  - Menghubungkan TaskManager dengan FileManager
  - Ensure semua komponen berjalan harmonis
  
- ✅ **Testing End-to-End**:
  - Testing semua user flow (Add, Edit, Delete, Filter, Search)
  - Testing integrasi antar komponen
  - Bug fixing
  
- ✅ **Finalisasi GitHub**:
  - Update README.md dengan cara instalasi dan usage
  - Dokumentasi code
  - Final commit & push

**Estimasi Waktu:** 12-15 jam total  
**Deadline:** Minggu, 30 Desember 2025

**Catatan:** Kamu solo karena ini critical path dan butuh koordinasi intensif dengan semua divisi

---

### 🔶 **DIVISI 4: Documentation, Report & Deployment**
**Anggota:**
- **NI WAYAN LISTIADEWI** (Ketua)
- **VANDA VICADA KARLIE TJOENG**
- **STEVEN ABIGAIL HASTING RENNIE**

**Tanggung Jawab:** UML, Dokumentasi, Laporan, dan Deployment

**Tugas:**

#### **A. Desain UML** (Vanda - Dikerjakan di Minggu 1)
- ✅ Membuat **UML Class Diagram** final (PlantUML/draw.io)
- ✅ Export diagram dalam format PNG/PDF (high quality)
- ✅ Dokumentasi desain class:
  - Penjelasan setiap class dan fungsinya
  - Atribut dan method dengan deskripsi
  - Relationship antar class

**Deadline:** Senin, 22 Desember 2025 (PENTING!)

---

#### **B. Laporan Proyek** (Listiadewi, Vanda, Steven - Dikerjakan di Minggu 2)

**Pembagian:**

**Listiadewi (Ketua) - Koordinator Laporan:**
- ✅ **Bab 1: Pendahuluan**
  - Latar belakang
  - Tujuan pembuatan aplikasi
  - Fitur utama aplikasi
  
- ✅ **Bab 2: Pembagian Tugas**
  - Tabel pembagian tugas per anggota
  - Kontribusi masing-masing anggota
  
- ✅ **Bab 7: Kesimpulan & Saran**
  - Kesimpulan project
  - Saran pengembangan

- ✅ **Koordinasi & Assembly:**
  - Review semua chapter dari anggota lain
  - Menyatukan semua chapter jadi satu laporan utuh
  - Formatting & proofreading final
  - Export ke PDF

**Vanda - Technical Writer:**
- ✅ **Bab 3: Desain Sistem**
  - Insert UML Class Diagram
  - Penjelasan struktur class
  - Penjelasan relationship
  
- ✅ **Bab 4: Implementasi PBO**
  - Penerapan Encapsulation (dengan contoh code)
  - Penerapan Inheritance (jika ada, dengan contoh)
  - Penerapan Polymorphism (jika ada, dengan contoh)
  - Penerapan Abstraction
  - Konsep OOP lain yang digunakan

**Steven - Tester & Documentation:**
- ✅ **Bab 5: Implementasi Sistem**
  - Screenshot aplikasi (main window, dialog, fitur-fitur)
  - Penjelasan fitur-fitur dengan detail
  - Cara penggunaan step-by-step
  
- ✅ **Bab 6: Testing & Hasil**
  - Skenario testing (test case)
  - Hasil testing (pass/fail)
  - Bug yang ditemukan & cara fixing

**Estimasi Waktu:** 5-7 jam per orang  
**Deadline Laporan:** Jumat, 27 Desember 2025

---

#### **C. Build, Deployment & Presentation** (Steven & Listiadewi - Akhir Minggu 2)

**Steven:**
- ✅ **Deploy aplikasi ke JAR file**:
  - Compile project menjadi executable JAR
  - Testing JAR di berbagai environment (minimal 2 komputer)
  - Membuat batch file untuk run JAR (opsional tapi bagus)
- ✅ **Packaging**:
  - Folder distribusi lengkap (JAR + data + docs)
  - Include sample data
- ✅ **User Manual** (PDF, 3-5 halaman):
  - System requirements
  - Cara instalasi/menjalankan
  - Cara menggunakan setiap fitur
  - Troubleshooting common issues

**Estimasi Waktu:** 4-6 jam  
**Deadline JAR:** Sabtu, 28 Desember 2025

**Listiadewi:**
- ✅ **Membuat Slide Presentasi UAS** (PowerPoint/Google Slides, 10-15 slides):
  - Cover (judul, kelompok, anggota)
  - Latar belakang & tujuan
  - Fitur utama aplikasi
  - UML Class Diagram
  - Screenshot aplikasi & demo
  - Implementasi konsep OOP
  - Pembagian tugas & kontribusi
  - Kesimpulan
  
- ✅ **Persiapan Presentasi**:
  - Script presentasi
  - Rehearsal dengan tim
  - Persiapan demo aplikasi

**Estimasi Waktu:** 4-5 jam  
**Deadline Presentasi:** Minggu, 30 Desember 2025

---

## 📊 Timeline Detail (2 Minggu)

### **Minggu 1: Setup, Design & Core Development**

**Senin, 15 Des (Hari 1) - SUDAH LEWAT:**
- ✅ Kickoff meeting
- ✅ Pembagian kelompok
- ✅ Penjelasan project
- ✅ Diskusi design class

**Selasa-Rabu, 16-17 Des (Hari 2-3):**
- **Divisi 1**: Mulai coding Task & TaskManager
- **Divisi 2**: Mulai coding FileManager
- **Divisi 3 (Candra)**: Setup GitHub repository & struktur folder ✅
- **Divisi 4 (Vanda)**: Mulai buat UML

**Kamis-Jumat, 18-19 Des (Hari 4-5):**
- **Divisi 1**: Lanjut coding + testing
- **Divisi 2**: Lanjut coding + sample data
- **Divisi 3 (Candra)**: Finalisasi GitHub setup
- **Divisi 4 (Vanda)**: Finalisasi UML

**Sabtu-Minggu, 20-21 Des (Hari 6-7):**
- **Divisi 1**: Finalisasi & push code ✅
- **Divisi 2**: Finalisasi & push code ✅
- **Divisi 3 (Candra)**: Review code dari Divisi 1 & 2
- **Divisi 4 (Vanda)**: Final touch UML

**Senin, 22 Des (Hari 8) - CHECKPOINT 1:**
- **UML HARUS SUDAH SELESAI** ✅ (Vanda)
- **Code Divisi 1 & 2 harus sudah di GitHub** ✅
- **Candra mulai coding GUI**

---

### **Minggu 2: Integration, Testing & Documentation**

**Selasa-Kamis, 23-25 Des (Hari 9-11):**
- **Divisi 1 & 2**: Standby untuk bug fixing
- **Divisi 3 (Candra)**: Coding GUI + integrasi sistem
- **Divisi 4**: Mulai tulis laporan (masing-masing chapter)

**Jumat, 26 Des (Hari 12):**
- **Divisi 3 (Candra)**: Finalisasi GUI & integrasi ✅
- **Divisi 4**: Lanjut laporan

**Sabtu, 27 Des (Hari 13) - CHECKPOINT 2:**
- **Code Lengkap & Terintegrasi** ✅ (Candra)
- **Laporan Draft Selesai** ✅ (Listiadewi, Vanda, Steven)
- **Steven mulai build JAR**

**Minggu, 28 Des (Hari 14):**
- **JAR File Ready** ✅ (Steven)
- **Laporan Final & User Manual** ✅ (All Divisi 4)
- **Slide Presentasi** ✅ (Listiadewi)
- **Testing Final** (Semua)

**Senin, 30 Des (Hari 15) - DEADLINE:**
- ✅ Semua deliverables ready
- ✅ Rehearsal presentasi
- ✅ Siap presentasi UAS

---

## 📌 Aturan & Konvensi

### **Komunikasi:**
- 💬 Group chat aktif untuk update harian (minimal 1x sehari)
- 📅 Quick sync online setiap Kamis malam (30 menit max)
- 🚨 Jika ada blocker, langsung report ke ketua atau lead divisi
- ✅ Update progress di grup setiap selesai task

### **GitHub Workflow:**
- **Branch Strategy:**
  - `main` - production ready code
  - `dev` - development branch (semua divisi merge ke sini dulu)
  - `feature/nama-fitur` - untuk development fitur baru
  
- **Commit Message Format:**
  - `[DIV-X] Deskripsi singkat perubahan`
  - Contoh: `[DIV-1] Add Task class with all getters and setters`
  - Contoh: `[DIV-3] Implement MainFrame layout and components`
  
- **Workflow:**
  1. Pull latest dari `dev` sebelum coding
  2. Buat branch `feature/your-feature`
  3. Commit & push ke branch feature
  4. Create pull request ke `dev`
  5. Minta review dari lead divisi atau Candra
  6. Merge setelah approved

### **Konvensi Kode:**

**Package Structure:**
```
com.todoapp/
├── model/
│   └── Task.java
├── manager/
│   ├── TaskManager.java
│   └── FileManager.java
├── ui/
│   ├── MainFrame.java
│   └── TaskDialog.java
└── Main.java
```

**Naming Convention:**
- Class: `PascalCase` (TaskManager, MainFrame)
- Method: `camelCase` (addTask, filterByStatus)
- Variable: `camelCase` (taskList, fileName)
- Constant: `UPPER_SNAKE_CASE` (MAX_TASKS, DEFAULT_STATUS)

**Code Quality:**
- Setiap method public harus ada JavaDoc comment
- Indentasi: 4 spaces
- Max line length: 100 characters
- Meaningful variable names (jangan `x`, `temp`, `data1`)
- Handle exceptions dengan try-catch

**Contoh JavaDoc:**
```java
/**
 * Menambahkan task baru ke dalam list
 * @param task Task yang akan ditambahkan
 * @throws NullPointerException jika task null
 */
public void addTask(Task task) {
    // implementation
}
```

---

## 🎯 Deliverables Akhir

1. ✅ **Source Code** di GitHub (clean & organized)
2. ✅ **Executable JAR file** (tested & working)
3. ✅ **Laporan PDF** (15-20 halaman, format akademis)
4. ✅ **UML Class Diagram** (PNG/PDF high quality)
5. ✅ **User Manual** (PDF, 3-5 halaman)
6. ✅ **README.md** (installation & usage guide)
7. ✅ **Slide Presentasi** (10-15 slides)
8. ✅ **Sample Data** (tasks.csv minimal 10 records)

---

## 💡 Tips Per Divisi

### **Divisi 1 (Tresna & Sintya) - Model & Logic:**
- Pastikan Task class robust dengan validasi di setter
- TaskManager harus efficient (pakai ArrayList, jangan loop berulang-ulang)
- Testing dengan edge cases: empty list, null values, duplikat ID
- Comment code dengan jelas
- Push code secepat mungkin biar Candra bisa mulai

### **Divisi 2 (Bhayu & Sasya) - File & Data:**
- CSV format harus konsisten (header di baris pertama)
- Handle semua error: file not found, permission denied, invalid format
- Gunakan try-catch-finally dengan baik
- Test dengan file kosong, file korup, file read-only
- Sample data harus realistis dan varied (berbagai status & priority)

### **Divisi 3 (Candra) - GUI & Integration:**
- **GitHub Setup (Minggu 1):**
  - Buat repo private, invite semua anggota
  - .gitignore untuk Java (*.class, *.jar, .idea/, target/)
  - README outline yang jelas
  - Branch protection untuk `main`
  
- **GUI Development (Minggu 2):**
  - Mulai dari mockup/wireframe di kertas dulu
  - Test UI dengan dummy data hardcode dulu
  - Baru connect ke TaskManager setelah UI stabil
  
- **Integration:**
  - Step-by-step: read dulu, baru write, baru filter/search
  - Testing setiap step sebelum lanjut
  - Komunikasi intensif dengan Divisi 1 & 2
  - Kalau ada bug di code mereka, langsung report dengan detail

### **Divisi 4 (Listiadewi, Vanda, Steven) - Docs & Deploy:**
- **Vanda (UML):**
  - Gunakan PlantUML atau draw.io
  - Pastikan semua class, atribut, method tercantum
  - Relationship harus jelas (composition, association, dependency)
  - Export PNG dengan resolution tinggi (min 1920px width)
  
- **Steven (Testing & Deploy):**
  - Buat test case document (spreadsheet)
  - Screenshot setiap hasil testing
  - JAR harus standalone (include dependencies)
  - Test di Windows & Mac (kalau ada)
  
- **Listiadewi (Laporan & Presentasi):**
  - Laporan pakai template formal (font Times New Roman 12pt)
  - Spacing 1.5, margin 3cm kiri, 2.5cm kanan/atas/bawah
  - Slide jangan terlalu banyak teks (pakai bullet points)
  - Sertakan screenshot yang jelas

---

## 🚨 Critical Dependencies

### **Dependencies Map:**
```
Vanda (UML) ──────────────┐
                          ├──> Presentasi Ready
Divisi 1 (Code) ─┐        │
                 ├──> Candra (Integration) ──> Steven (JAR) ──┤
Divisi 2 (Code) ─┘        │                                   │
                          ├──> Laporan Ready ─────────────────┘
Candra (GitHub) ──────────┘
```

### **Critical Path:**
1. **Senin 22 Des:** UML selesai (Vanda) ✅
2. **Jumat 21 Des:** Code Divisi 1 & 2 selesai ✅
3. **Sabtu 27 Des:** Integrasi selesai (Candra) ✅
4. **Minggu 28 Des:** JAR & Laporan selesai ✅
5. **Senin 30 Des:** Presentasi ready ✅

**⚠️ JANGAN SAMPAI TERLAMBAT!** Setiap delay akan impact divisi lain!

---

## 📞 Point of Contact

- **Ketua & Koordinator Laporan:** NI WAYAN LISTIADEWI
- **Lead Divisi 1:** TRESNA FEBRYAN BERNADINE ICO
- **Lead Divisi 2:** NYOMAN BHAYU WIRATANAYA
- **Lead Divisi 3 & GitHub Manager:** NYOMAN CANDRA NATA INDITAMA ⭐
- **UML & Technical Writer:** VANDA VICADA KARLIE TJOENG
- **Tester & Deployment:** STEVEN ABIGAIL HASTING RENNIE

---

## ✨ Pesan Motivasi

**"Teamwork makes the dream work!"**

Hanya 2 minggu tapi kita pasti bisa! 💪  
Kunci sukses: **Komunikasi** & **Disiplin Timeline** 🔑  
Setiap orang penting, setiap tugas vital! 🚀  

**Mari kita buat project terbaik! 💻✨**

---

## 📝 Quick Reference

### **Kapan Harus Push Code:**
- Divisi 1 & 2: Paling lambat **Jumat 21 Des malam**
- Divisi 3: Paling lambat **Sabtu 27 Des malam**

### **Kapan Harus Submit:**
- UML: **Senin 22 Des**
- Laporan Draft: **Jumat 27 Des**
- Final Deliverables: **Minggu 28 Des**

### **Meeting Schedule:**
- Quick Sync: **Setiap Kamis 20:00** (30 menit)
- Final Rehearsal: **Minggu 28 Des 19:00** (1 jam)

**Good luck kelompok 3! Let's ace this! 🎉**