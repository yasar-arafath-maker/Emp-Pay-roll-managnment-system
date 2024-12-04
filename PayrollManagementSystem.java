import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Employee {
    String id, name, designation;
    double basicSalary, hra, da, deductions;

    Employee(String id, String name, String designation, double basicSalary, double hra, double da, double deductions) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.da = da;
        this.deductions = deductions;
    }

    double calculateNetSalary() {
        return basicSalary + hra + da - deductions;
    }
}

public class PayrollManagementSystem extends JFrame {
    private ArrayList<Employee> employees = new ArrayList<>();

    private JTextField txtId, txtName, txtDesignation, txtBasicSalary, txtHRA, txtDA, txtDeductions;
    private JTextArea displayArea;

    public PayrollManagementSystem() {
        setTitle("Employee Payroll Management System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        txtId = new JTextField();
        txtName = new JTextField();
        txtDesignation = new JTextField();
        txtBasicSalary = new JTextField();
        txtHRA = new JTextField();
        txtDA = new JTextField();
        txtDeductions = new JTextField();

        inputPanel.add(new JLabel("Employee ID:"));
        inputPanel.add(txtId);
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(txtName);
        inputPanel.add(new JLabel("Designation:"));
        inputPanel.add(txtDesignation);
        inputPanel.add(new JLabel("Basic Salary:"));
        inputPanel.add(txtBasicSalary);
        inputPanel.add(new JLabel("HRA:"));
        inputPanel.add(txtHRA);
        inputPanel.add(new JLabel("DA:"));
        inputPanel.add(txtDA);
        inputPanel.add(new JLabel("Deductions:"));
        inputPanel.add(txtDeductions);

        add(inputPanel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Add Employee");
        JButton btnDisplay = new JButton("Display Employees");
        JButton btnExit = new JButton("Exit");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDisplay);
        buttonPanel.add(btnExit);
        add(buttonPanel, BorderLayout.CENTER);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBorder(BorderFactory.createTitledBorder("Employee Records"));
        add(new JScrollPane(displayArea), BorderLayout.SOUTH);

        // Button Actions
        btnAdd.addActionListener(e -> addEmployee());
        btnDisplay.addActionListener(e -> displayEmployees());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void addEmployee() {
        try {
            String id = txtId.getText();
            String name = txtName.getText();
            String designation = txtDesignation.getText();
            double basicSalary = Double.parseDouble(txtBasicSalary.getText());
            double hra = Double.parseDouble(txtHRA.getText());
            double da = Double.parseDouble(txtDA.getText());
            double deductions = Double.parseDouble(txtDeductions.getText());

            Employee employee = new Employee(id, name, designation, basicSalary, hra, da, deductions);
            employees.add(employee);

            JOptionPane.showMessageDialog(this, "Employee added successfully!");
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for salary fields.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayEmployees() {
        if (employees.isEmpty()) {
            displayArea.setText("No employee records found.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Employee emp : employees) {
                sb.append("ID: ").append(emp.id).append(", Name: ").append(emp.name)
                        .append(", Designation: ").append(emp.designation)
                        .append(", Net Salary: ₹").append(String.format("%.2f", emp.calculateNetSalary()))
                        .append("\n");
            }
            displayArea.setText(sb.toString());
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtDesignation.setText("");
        txtBasicSalary.setText("");
        txtHRA.setText("");
        txtDA.setText("");
        txtDeductions.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PayrollManagementSystem().setVisible(true));
    }
}