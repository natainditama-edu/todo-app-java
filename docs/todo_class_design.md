# Desain Class - Aplikasi Manajemen Tugas (Todo App)

## 1. Class Task (Model)

**Deskripsi:** Class untuk merepresentasikan data tugas

### Atribut:
| Atribut | Tipe Data | Akses | Deskripsi |
|---------|-----------|-------|-----------|
| id | String | private | ID unik tugas (auto-generate) |
| title | String | private | Judul tugas |
| description | String | private | Deskripsi detail tugas |
| deadline | LocalDateTime | private | Batas waktu tugas |
| status | String | private | Status tugas (Pending/In Progress/Completed/Cancelled) |
| priority | String | private | Prioritas tugas (Low/Medium/High) |
| category | String | private | Kategori/label tugas |
| createdAt | LocalDateTime | private | Waktu pembuatan tugas |

### Method:
| Method | Return Type | Parameter | Deskripsi |
|--------|-------------|-----------|-----------|
| Task() | - | title, description, deadline, priority, category | Constructor untuk membuat objek Task baru |
| getId() | String | - | Mengambil ID tugas |
| getTitle() | String | - | Mengambil judul tugas |
| setTitle() | void | String title | Mengubah judul tugas |
| getDescription() | String | - | Mengambil deskripsi tugas |
| setDescription() | void | String description | Mengubah deskripsi tugas |
| getDeadline() | LocalDateTime | - | Mengambil deadline tugas |
| setDeadline() | void | LocalDateTime deadline | Mengubah deadline tugas |
| getStatus() | String | - | Mengambil status tugas |
| setStatus() | void | String status | Mengubah status tugas |
| getPriority() | String | - | Mengambil prioritas tugas |
| setPriority() | void | String priority | Mengubah prioritas tugas |
| getCategory() | String | - | Mengambil kategori tugas |
| setCategory() | void | String category | Mengubah kategori tugas |
| getCreatedAt() | LocalDateTime | - | Mengambil waktu pembuatan |
| isOverdue() | boolean | - | Mengecek apakah tugas sudah melewati deadline |
| toString() | String | - | Mengubah objek menjadi string untuk display |

---

## 2. Class TaskManager (Business Logic)

**Deskripsi:** Class untuk mengelola semua operasi tugas (CRUD dan filtering)

### Atribut:
| Atribut | Tipe Data | Akses | Deskripsi |
|---------|-----------|-------|-----------|
| taskList | List\<Task\> | private | Daftar semua tugas |
| fileManager | FileManager | private | Objek untuk mengelola file |

### Method:
| Method | Return Type | Parameter | Deskripsi |
|--------|-------------|-----------|-----------|
| TaskManager() | - | - | Constructor, inisialisasi taskList dan fileManager |
| addTask() | void | Task task | Menambah tugas baru ke dalam list |
| updateTask() | void | String taskId, Task task | Mengupdate tugas berdasarkan ID |
| deleteTask() | void | String taskId | Menghapus tugas berdasarkan ID |
| getTask() | Task | String taskId | Mengambil satu tugas berdasarkan ID |
| getAllTasks() | List\<Task\> | - | Mengambil semua tugas |
| filterByStatus() | List\<Task\> | String status | Filter tugas berdasarkan status |
| filterByPriority() | List\<Task\> | String priority | Filter tugas berdasarkan prioritas |
| filterByCategory() | List\<Task\> | String category | Filter tugas berdasarkan kategori |
| searchTask() | List\<Task\> | String keyword | Mencari tugas berdasarkan keyword di title/description |
| loadData() | void | - | Memuat data tugas dari file |
| saveData() | void | - | Menyimpan data tugas ke file |

---

## 3. Class FileManager (Data Persistence)

**Deskripsi:** Class untuk mengelola penyimpanan dan pembacaan data dari file

### Atribut:
| Atribut | Tipe Data | Akses | Deskripsi |
|---------|-----------|-------|-----------|
| fileName | String | private | Nama file untuk menyimpan data (tasks.txt/csv) |

### Method:
| Method | Return Type | Parameter | Deskripsi |
|--------|-------------|-----------|-----------|
| FileManager() | - | String fileName | Constructor, set nama file |
| saveToFile() | void | List\<Task\> tasks | Menyimpan list tugas ke file |
| loadFromFile() | List\<Task\> | - | Membaca data tugas dari file dan mengembalikan list |

---

## 4. Class MainFrame (GUI - Main Window)

**Deskripsi:** Class untuk tampilan utama aplikasi

### Atribut:
| Atribut | Tipe Data | Akses | Deskripsi |
|---------|-----------|-------|-----------|
| taskManager | TaskManager | private | Instance TaskManager untuk operasi tugas |
| reminder | ReminderChecker | private | Instance ReminderChecker untuk notifikasi |
| taskTable | JTable | private | Tabel untuk menampilkan daftar tugas |
| btnAdd | JButton | private | Tombol untuk menambah tugas |
| btnEdit | JButton | private | Tombol untuk mengedit tugas |
| btnDelete | JButton | private | Tombol untuk menghapus tugas |
| cmbFilter | JComboBox | private | ComboBox untuk filter (status/prioritas/kategori) |
| txtSearch | JTextField | private | TextField untuk search tugas |

### Method:
| Method | Return Type | Parameter | Deskripsi |
|--------|-------------|-----------|-----------|
| MainFrame() | - | - | Constructor, inisialisasi semua komponen GUI |
| refreshTable() | void | - | Refresh data di tabel sesuai filter/search |
| showAddDialog() | void | - | Menampilkan dialog untuk menambah tugas baru |
| showEditDialog() | void | - | Menampilkan dialog untuk mengedit tugas terpilih |
| showDeleteConfirm() | void | - | Menampilkan konfirmasi sebelum hapus tugas |

---

## 5. Class TaskDialog (GUI - Dialog)

**Deskripsi:** Class untuk dialog input/edit tugas

### Atribut:
| Atribut | Tipe Data | Akses | Deskripsi |
|---------|-----------|-------|-----------|
| txtTitle | JTextField | private | Input field untuk judul tugas |
| txtDescription | JTextArea | private | Input area untuk deskripsi tugas |
| cmbPriority | JComboBox | private | ComboBox untuk memilih prioritas |
| txtCategory | JTextField | private | Input field untuk kategori |
| spnDeadline | JSpinner | private | Spinner untuk memilih tanggal deadline |
| task | Task | private | Objek task yang sedang diedit (null jika mode tambah) |

### Method:
| Method | Return Type | Parameter | Deskripsi |
|--------|-------------|-----------|-----------|
| TaskDialog() | - | JFrame parent, Task task | Constructor, set parent window dan task (null untuk mode add) |
| showDialog() | Task | - | Menampilkan dialog dan return Task hasil input user |
| validateInput() | boolean | - | Validasi input user sebelum create/update task |

---

## Relasi Antar Class:

1. **TaskManager mengelola Task**
   - TaskManager memiliki List\<Task\> untuk menyimpan semua tugas
   - Relasi: One-to-Many (1 TaskManager → many Task)

2. **TaskManager menggunakan FileManager**
   - TaskManager memiliki instance FileManager untuk operasi file
   - Relasi: One-to-One (composition)

3. **MainFrame menggunakan TaskManager**
   - MainFrame memiliki instance TaskManager untuk operasi CRUD
   - Relasi: One-to-One (composition)

4. **MainFrame menggunakan ReminderChecker**
   - MainFrame memiliki instance ReminderChecker untuk notifikasi
   - Relasi: One-to-One (composition)

5. **MainFrame membuat TaskDialog**
   - MainFrame membuat instance TaskDialog saat perlu input
   - Relasi: One-to-Many (dependency)

6. **ReminderChecker memantau TaskManager**
   - ReminderChecker memiliki referensi ke TaskManager
   - Relasi: One-to-One (association)

7. **FileManager menyimpan/membaca Task**
   - FileManager bekerja dengan List\<Task\>
   - Relasi: Dependency

---

## Catatan Implementasi:

### Format File (CSV):
```
id,title,description,deadline,status,priority,category,createdAt
T001,Belajar Java,Membaca chapter 5,2024-12-20T10:00:00,Pending,High,Study,2024-12-15T08:00:00
T002,Meeting Tim,Diskusi proyek akhir,2024-12-18T14:00:00,In Progress,Medium,Work,2024-12-15T09:00:00
```

### Prioritas:
- **Low**: Prioritas rendah
- **Medium**: Prioritas sedang
- **High**: Prioritas tinggi

### Status:
- **Pending**: Belum dikerjakan
- **In Progress**: Sedang dikerjakan
- **Completed**: Sudah selesai
- **Cancelled**: Dibatalkan

### Kategori (Contoh):
- Study, Work, Personal, Shopping, Health, dll. (user dapat menambahkan kategori sendiri)