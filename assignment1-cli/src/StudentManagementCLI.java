import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementCLI {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManager studentManager = new StudentManager();

    public static void main(String[] args) {
        int choice;

        do {
            showMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    System.out.println("Thank you for using Student Management System.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("===== Student Management System =====");
        System.out.println("1. Add Student");
        System.out.println("2. View Students");
        System.out.println("3. Update Student");
        System.out.println("4. Delete Student");
        System.out.println("5. Search Student");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
        System.out.println();
        int id = readInt("Enter Student ID: ");

        if (studentManager.findStudentById(id) != null) {
            System.out.println("A student with this ID already exists.");
            return;
        }

        String name = readRequiredText("Enter Student Name: ");
        String course = readRequiredText("Enter Course: ");
        double marks = readMarks("Enter Marks: ");

        Student student = new Student(id, name, course, marks);

        if (studentManager.addStudent(student)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Unable to add student.");
        }
    }

    private static void viewStudents() {
        System.out.println();
        ArrayList<Student> students = studentManager.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        System.out.println("----- Student List -----");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void updateStudent() {
        System.out.println();
        int id = readInt("Enter Student ID to update: ");

        if (studentManager.findStudentById(id) == null) {
            System.out.println("Student not found.");
            return;
        }

        String name = readRequiredText("Enter New Name: ");
        String course = readRequiredText("Enter New Course: ");
        double marks = readMarks("Enter New Marks: ");

        if (studentManager.updateStudent(id, name, course, marks)) {
            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Unable to update student.");
        }
    }

    private static void deleteStudent() {
        System.out.println();
        int id = readInt("Enter Student ID to delete: ");

        if (studentManager.deleteStudent(id)) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void searchStudent() {
        System.out.println();
        int id = readInt("Enter Student ID to search: ");
        Student student = studentManager.findStudentById(id);

        if (student == null) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Student found:");
            System.out.println(student);
        }
    }

    private static int readInt(String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            }

            System.out.println("Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private static double readMarks(String message) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();
                scanner.nextLine();

                if (value >= 0 && value <= 100) {
                    return value;
                }

                System.out.println("Marks must be between 0 and 100.");
            } else {
                System.out.println("Please enter valid marks.");
                scanner.nextLine();
            }
        }
    }

    private static String readRequiredText(String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("This field cannot be empty.");
        }
    }
}
