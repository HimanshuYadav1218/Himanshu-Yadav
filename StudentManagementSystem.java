package Himanshu;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame {
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JTextField idField, nameField, courseField, yearField, gpaField;
    private JButton addButton, updateButton, deleteButton, clearButton;
    private ArrayList<Student> students;

    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        students = new ArrayList<>();
        initializeComponents();
        addActionListeners();
    }

    private void initializeComponents() {
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        formPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        formPanel.add(idField);

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Course:"));
        courseField = new JTextField();
        formPanel.add(courseField);

        formPanel.add(new JLabel("Year:"));
        yearField = new JTextField();
        formPanel.add(yearField);

        formPanel.add(new JLabel("GPA:"));
        gpaField = new JTextField();
        formPanel.add(gpaField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        styleButton(addButton, new Color(46, 204, 113));
        styleButton(updateButton, new Color(52, 152, 219));
        styleButton(deleteButton, new Color(231, 76, 60));
        styleButton(clearButton, new Color(149, 165, 166));

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // Table
        String[] columns = {"ID", "Name", "Course", "Year", "GPA"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(studentTable);

        // Main Layout
        setLayout(new BorderLayout());
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void addActionListeners() {
        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());
        clearButton.addActionListener(e -> clearFields());

        studentTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = studentTable.getSelectedRow();
                if (selectedRow != -1) {
                    populateFields(selectedRow);
                }
            }
        });
    }

    private void addStudent() {
        if (validateFields()) {
            String[] rowData = {
                idField.getText(),
                nameField.getText(),
                courseField.getText(),
                yearField.getText(),
                gpaField.getText()
            };
            tableModel.addRow(rowData);
            students.add(new Student(rowData));
            clearFields();
            JOptionPane.showMessageDialog(this, "Student added successfully!");
        }
    }

    private void updateStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1 && validateFields()) {
            tableModel.setValueAt(idField.getText(), selectedRow, 0);
            tableModel.setValueAt(nameField.getText(), selectedRow, 1);
            tableModel.setValueAt(courseField.getText(), selectedRow, 2);
            tableModel.setValueAt(yearField.getText(), selectedRow, 3);
            tableModel.setValueAt(gpaField.getText(), selectedRow, 4);
            
            students.set(selectedRow, new Student(new String[]{
                idField.getText(),
                nameField.getText(),
                courseField.getText(),
                yearField.getText(),
                gpaField.getText()
            }));
            
            clearFields();
            JOptionPane.showMessageDialog(this, "Student updated successfully!");
        } else if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update!");
        }
    }

    private void deleteStudent() {
        int selectedRow = studentTable.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this student?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);
                
            if (confirm == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
                students.remove(selectedRow);
                clearFields();
                JOptionPane.showMessageDialog(this, "Student deleted successfully!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a student to delete!");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        courseField.setText("");
        yearField.setText("");
        gpaField.setText("");
        studentTable.clearSelection();
    }

    private void populateFields(int row) {
        idField.setText(tableModel.getValueAt(row, 0).toString());
        nameField.setText(tableModel.getValueAt(row, 1).toString());
        courseField.setText(tableModel.getValueAt(row, 2).toString());
        yearField.setText(tableModel.getValueAt(row, 3).toString());
        gpaField.setText(tableModel.getValueAt(row, 4).toString());
    }

    private boolean validateFields() {
        if (idField.getText().trim().isEmpty() ||
            nameField.getText().trim().isEmpty() ||
            courseField.getText().trim().isEmpty() ||
            yearField.getText().trim().isEmpty() ||
            gpaField.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "All fields are required!");
            return false;
        }
        
        try {
            Integer.parseInt(yearField.getText().trim());
            Double.parseDouble(gpaField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Year must be an integer and GPA must be a number!");
            return false;
        }
        
        return true;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSystem().setVisible(true);
        });
    }
}

class Student {
    private String id;
    private String name;
    private String course;
    private int year;
    private double gpa;

    public Student(String[] data) {
        this.id = data[0];
        this.name = data[1];
        this.course = data[2];
        this.year = Integer.parseInt(data[3]);
        this.gpa = Double.parseDouble(data[4]);
    }
}