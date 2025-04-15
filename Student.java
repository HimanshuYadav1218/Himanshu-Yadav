package Himanshu;
import java.util.*;

class Student {
    private String studentId;
    private String name;
    private int age;
    private String course;

    // Constructor
    public Student(String studentId, String name, int age, String course) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    // Getters and Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    // Method to display student information
    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Course: " + course);
        System.out.println("---------------");
    }
}

class StudentManagementSystem {
    private Map<String, Student> studentMap = new HashMap<>();

    // Add student
    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        
        if (studentMap.containsKey(studentId)) {
            System.out.println("Student with this ID already exists!");
            return;
        }
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter student course: ");
        String course = scanner.nextLine();

        Student student = new Student(studentId, name, age, course);
        studentMap.put(studentId, student);
        System.out.println("Student added successfully.");
    }

    // View student
    public void viewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to view details: ");
        String studentId = scanner.nextLine();

        if (studentMap.containsKey(studentId)) {
            studentMap.get(studentId).displayStudent();
        } else {
            System.out.println("Student not found!");
        }
    }

    // Update student
    public void updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to update: ");
        String studentId = scanner.nextLine();

        if (studentMap.containsKey(studentId)) {
            Student student = studentMap.get(studentId);
            
            System.out.print("Enter new name (or press Enter to keep current): ");
            String name = scanner.nextLine();
            if (!name.isEmpty()) student.setName(name);

            System.out.print("Enter new age (or press Enter to keep current): ");
            String ageInput = scanner.nextLine();
            if (!ageInput.isEmpty()) student.setAge(Integer.parseInt(ageInput));

            System.out.print("Enter new course (or press Enter to keep current): ");
            String course = scanner.nextLine();
            if (!course.isEmpty()) student.setCourse(course);

            System.out.println("Student updated successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Delete student
    public void deleteStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID to delete: ");
        String studentId = scanner.nextLine();

        if (studentMap.containsKey(studentId)) {
            studentMap.remove(studentId);
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student not found!");
        }
    }

    // List all students
    public void listStudents() {
        if (studentMap.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : studentMap.values()) {
                student.displayStudent();
            }
        }
    }

    // Display menu
    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Student");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. List All Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudent();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    listStudents();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

