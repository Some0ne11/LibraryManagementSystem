![Demo](<iframe src="https://drive.google.com/file/d/1bMSKYqWgHTryQFqYiUGnnrBnJQY5qc4m/preview" width="640" height="480" allow="autoplay"></iframe>)

<video width="400" controls>
  <source src="mov_bbb.mp4" type="video/mp4">
  <source src="mov_bbb.ogg" type="video/ogg">
  Your browser does not support HTML video.
</video>

# BookStream: Library Management System

## Overview
BookStream is a library management system designed to streamline library operations, including user management and book inventory tracking. This document provides the necessary steps to set up, run, and operate the system using IntelliJ IDEA.

---

## Setup Instructions

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

## Credentials

### Admin Login
- **Username**: `admin`  
- **Password**: `admin`

### User Credentials
- User details, including usernames and passwords, are stored in the file:  
  `Member.csv`

---

## Book Collection
- The library's book inventory is maintained in the file:  
  `Book.csv`

---

## Important Notes
1. **File Integrity**:
   - Do not modify the structure or content of the `Member.csv` and `Book.csv` files manually to avoid system errors.
   - The application handles all necessary file I/O operations.  

2. **System Handling**:
   - Any manual changes to the specified files can disrupt the application's functionality.

---
