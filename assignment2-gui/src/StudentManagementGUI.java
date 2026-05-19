import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StudentManagementGUI extends JFrame implements ActionListener {
    private JTextField idField;
    private JTextField nameField;
    private JTextField courseField;
    private JTextField marksField;

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;

    private JTable studentTable;
    private DefaultTableModel tableModel;
    private StudentManager studentManager;

    public StudentManagementGUI() {
        studentManager = new StudentManager();

        setTitle("Student Management System");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        createFormPanel();
        createTable();
        createButtonPanel();
    }

    private void createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        idField = new JTextField(18);
        nameField = new JTextField(18);
        courseField = new JTextField(18);
        marksField = new JTextField(18);

        addFormRow(formPanel, gbc, 0, "Student ID:", idField);
        addFormRow(formPanel, gbc, 1, "Name:", nameField);
        addFormRow(formPanel, gbc, 2, "Course:", courseField);
        addFormRow(formPanel, gbc, 3, "Marks:", marksField);

        add(formPanel, BorderLayout.NORTH);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField textField) {
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    private void createTable() {
        String[] columns = {"ID", "Name", "Course", "Marks"};
        tableModel = new DefaultTableModel(columns, 0);
        studentTable = new JTable(tableModel);

        // When a row is selected, copy that row's data into the form fields.
        studentTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && studentTable.getSelectedRow() != -1) {
                int selectedRow = studentTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                courseField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                marksField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            }
        });

        add(new JScrollPane(studentTable), BorderLayout.CENTER);
    }

    private void createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        addButton.addActionListener(this);
        updateButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();

        if (source == addButton) {
            addStudent();
        } else if (source == updateButton) {
            updateStudent();
        } else if (source == deleteButton) {
            deleteStudent();
        } else if (source == clearButton) {
            clearFields();
        }
    }

    private void addStudent() {
        Student student = readStudentFromForm();

        if (student == null) {
            return;
        }

        if (studentManager.addStudent(student)) {
            refreshTable();
            clearFields();
            showMessage("Student added successfully.");
        } else {
            showMessage("A student with this ID already exists.");
        }
    }

    private void updateStudent() {
        Student student = readStudentFromForm();

        if (student == null) {
            return;
        }

        if (studentManager.updateStudent(student.getId(), student.getName(), student.getCourse(), student.getMarks())) {
            refreshTable();
            clearFields();
            showMessage("Student updated successfully.");
        } else {
            showMessage("Student not found.");
        }
    }

    private void deleteStudent() {
        String idText = idField.getText().trim();

        if (idText.isEmpty()) {
            showMessage("Please enter the student ID to delete.");
            return;
        }

        try {
            int id = Integer.parseInt(idText);

            if (studentManager.deleteStudent(id)) {
                refreshTable();
                clearFields();
                showMessage("Student deleted successfully.");
            } else {
                showMessage("Student not found.");
            }
        } catch (NumberFormatException exception) {
            showMessage("Student ID must be a number.");
        }
    }

    private Student readStudentFromForm() {
        String idText = idField.getText().trim();
        String name = nameField.getText().trim();
        String course = courseField.getText().trim();
        String marksText = marksField.getText().trim();

        if (idText.isEmpty() || name.isEmpty() || course.isEmpty() || marksText.isEmpty()) {
            showMessage("All fields are required.");
            return null;
        }

        try {
            int id = Integer.parseInt(idText);
            double marks = Double.parseDouble(marksText);

            if (marks < 0 || marks > 100) {
                showMessage("Marks must be between 0 and 100.");
                return null;
            }

            return new Student(id, name, course, marks);
        } catch (NumberFormatException exception) {
            showMessage("Student ID and marks must be valid numbers.");
            return null;
        }
    }

    private void refreshTable() {
        tableModel.setRowCount(0);

        for (Student student : studentManager.getAllStudents()) {
            Object[] row = {
                student.getId(),
                student.getName(),
                student.getCourse(),
                student.getMarks()
            };

            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        courseField.setText("");
        marksField.setText("");
        studentTable.clearSelection();
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementGUI app = new StudentManagementGUI();
            app.setVisible(true);
        });
    }
}
