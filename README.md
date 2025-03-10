<h1 align="center" style="font-weight: bold;">BookStream: Library Management System 📚</h1>

<p align="center">
<a href="#overview">Overview</a>
   <span>&nbsp; • &nbsp;</span>
<a href="#setup">Setup Instructions</a>
   <span>&nbsp; • &nbsp;</span>
<a href="#credentials">Credentials</a>
   <span>&nbsp; • &nbsp;</span>
<a href="#collections">Book Collections</a>
      <span>&nbsp; • &nbsp;</span>
<a href="#important">Important Notes</a>
</p>

<h2 id="overview">👀 Overview</h2>
BookStream is a library management system designed to streamline library operations, including user management and book inventory tracking. This document provides the necessary steps to set up, run, and operate the system using IntelliJ IDEA.

https://github.com/user-attachments/assets/0cf80ca9-7d7b-4c7c-8133-ed1712dbed1a

---

<h2 id="setup">⚙️ Setup Instructions</h2>

### System Requirements
- **IDE**: IntelliJ IDEA
- **Java Version**: Ensure the correct Java version is installed as required by the project.

### Steps to Run the Project
1. Launch **IntelliJ IDEA** on your machine.
2. Open the project:
   - Navigate to the **Source Code** folder.
   - Select the **LibrarySystem6** folder and click **OK**.
3. Locate and run the `AppLaunch.java` file to start the application.

---

<h2 id="credentials">🔐 Credentials</h2>

### Admin Login
- **Username**: `admin`  
- **Password**: `admin`

### User Credentials
- User details, including usernames and passwords, are stored in the file:  
  `Member.csv`

---

<h2 id="collections">📃 Book Collection</h2>
- The library's book inventory is maintained in the file:  
  `Book.csv`

---

<h2 id="important">❗ Important Notes</h2>
1. **File Integrity**:
   - Do not modify the structure or content of the `Member.csv` and `Book.csv` files manually to avoid system errors.
   - The application handles all necessary file I/O operations.  

2. **System Handling**:
   - Any manual changes to the specified files can disrupt the application's functionality.

---
