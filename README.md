# Student Management System

A beginner-friendly Java CRUD project for a college assignment. The project contains two versions of the same Student Management System:

- Assignment 1: Menu-driven console/CLI application
- Assignment 2: Java Swing GUI application

Both versions use:

- Classes and objects
- `ArrayList` for storage
- Methods
- Loops and conditionals
- Form/input validation
- Full CRUD operations

Student fields:

- `id`
- `name`
- `course`
- `marks`

## Folder Structure

```text
java-assignment/
├── assignment1-cli/
│   └── src/
│       ├── Student.java
│       ├── StudentManager.java
│       └── StudentManagementCLI.java
├── assignment2-gui/
│   └── src/
│       ├── Student.java
│       ├── StudentManager.java
│       └── StudentManagementGUI.java
├── .gitattributes
└── README.md
```

## Assignment 1: CLI Version

### Features

- Add student
- View all students
- Update student
- Delete student
- Search student by ID
- Menu-driven console interface

### Compile And Run

Open a terminal in the project folder and run:

```bash
cd assignment1-cli/src
javac *.java
java StudentManagementCLI
```

On Windows PowerShell:

```powershell
cd assignment1-cli\src
javac *.java
java StudentManagementCLI
```

### Sample Output

```text
===== Student Management System =====
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Search Student
6. Exit
Enter your choice: 1

Enter Student ID: 101
Enter Student Name: Rahul Sharma
Enter Course: BCA
Enter Marks: 86
Student added successfully.

===== Student Management System =====
1. Add Student
2. View Students
3. Update Student
4. Delete Student
5. Search Student
6. Exit
Enter your choice: 2

ID: 101, Name: Rahul Sharma, Course: BCA, Marks: 86.0
```

## Assignment 2: GUI Version

### Features

- Java Swing form
- Text fields for ID, name, course, and marks
- Buttons: Add, Update, Delete, Clear
- JTable to display student records
- Event handling for all buttons
- Validation for empty fields, duplicate IDs, and invalid marks
- Uses `ArrayList` as storage

### Compile And Run

Open a terminal in the project folder and run:

```bash
cd assignment2-gui/src
javac *.java
java StudentManagementGUI
```

On Windows PowerShell:

```powershell
cd assignment2-gui\src
javac *.java
java StudentManagementGUI
```

## Suggested Screenshots For Submission

Take these screenshots for your assignment file:

1. Project folder structure in your IDE or file explorer
2. CLI menu screen
3. CLI after adding and viewing students
4. GUI empty form
5. GUI after adding students
6. GUI update operation
7. GUI delete operation
8. Validation message, such as duplicate ID or empty field
9. GitHub repository page
10. QR code for the GitHub repository

## How To Upload This Project To GitHub

### Method 1: Using Git Commands

Create a new empty repository on GitHub. Then run these commands from the project folder:

```bash
git init
git add .
git commit -m "Add Student Management System project"
git branch -M main
git remote add origin https://github.com/YOUR-USERNAME/YOUR-REPOSITORY-NAME.git
git push -u origin main
```

Replace:

- `YOUR-USERNAME` with your GitHub username
- `YOUR-REPOSITORY-NAME` with your repository name

If this repository already has a remote connected, use:

```bash
git remote -v
git add .
git commit -m "Add Student Management System project"
git push
```

### Method 2: Using GitHub Desktop

1. Open GitHub Desktop.
2. Click `File > Add local repository`.
3. Select this project folder.
4. Write a commit message.
5. Click `Commit to main`.
6. Click `Publish repository`.

## How To Generate A QR Code For The GitHub Repo

1. Upload the project to GitHub.
2. Open your GitHub repository in the browser.
3. Copy the repository URL.
4. Open a QR code generator website, for example:
   - https://www.qr-code-generator.com/
   - https://www.the-qrcode-generator.com/
5. Paste your GitHub repository URL.
6. Download the generated QR code image.
7. Add the QR code image to your assignment report.

## Notes

- No database is used. Data is stored temporarily in an `ArrayList`.
- Records will be cleared when the program closes.
- This project is intentionally simple and beginner-friendly for college submission.
