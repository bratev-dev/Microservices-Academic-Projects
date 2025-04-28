package com.unicauca.CompanyService.presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CompanyForm extends JFrame {

    private JTextField  passwordField, nameField, emailField, sectorField, contactNamesField, contactLastNamesField, contactPhoneField, contactPositionField;
    private JButton submitButton, cancelButton;

    public CompanyForm() {
        // Configuración de la ventana
        setTitle("Registro de empresa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel izquierdo con fondo azul
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.BLUE);
        leftPanel.setLayout(new BorderLayout());  // Usar BorderLayout para que cubra toda la ventana
        leftPanel.setBounds(0, 0, 600, 400);



        // Título en la parte superior
        JLabel titleLabel = new JLabel("Registro de empresa", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setPreferredSize(new Dimension(600, 40));

        // Panel derecho para los formularios
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1, 10, 10)); // 2 filas: Datos de la empresa y Datos de contacto

        // Sección para "Datos de la empresa"
        JPanel companyPanel = new JPanel();
        companyPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 campos de texto con etiquetas
        companyPanel.setBorder(BorderFactory.createTitledBorder("Datos de la empresa"));

        // Campos de la empresa
        companyPanel.add(new JLabel("Nombre:"));
        nameField = new JTextField();
        companyPanel.add(nameField);

        companyPanel.add(new JLabel("Correo:"));
        emailField = new JTextField();
        companyPanel.add(emailField);

        companyPanel.add(new JLabel("Contraseña:"));
        passwordField= new JTextField();
        companyPanel.add(passwordField);

        companyPanel.add(new JLabel("Sector:"));
        sectorField = new JTextField();
        companyPanel.add(sectorField);

        // Sección para "Datos de contacto"
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 campos de texto con etiquetas
        contactPanel.setBorder(BorderFactory.createTitledBorder("Datos de contacto"));

        // Campos de contacto
        contactPanel.add(new JLabel("Nombres:"));
        contactNamesField = new JTextField();
        contactPanel.add(contactNamesField);

        contactPanel.add(new JLabel("Apellidos:"));
        contactLastNamesField = new JTextField();
        contactPanel.add(contactLastNamesField);

        contactPanel.add(new JLabel("Teléfono:"));
        contactPhoneField = new JTextField();
        contactPanel.add(contactPhoneField);

        contactPanel.add(new JLabel("Cargo:"));
        contactPositionField = new JTextField();
        contactPanel.add(contactPositionField);

        // Panel inferior con botón de enviar
        JPanel bottomPanel = new JPanel();
        submitButton = new JButton("Registrar empresa");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes manejar lo que sucede al enviar los datos
                // Por ejemplo, mostrar los datos en la consola
                String name = nameField.getText();
                String email = emailField.getText();
                String sector = sectorField.getText();
                String contactNames = contactNamesField.getText();
                String contactLastNames = contactLastNamesField.getText();
                String contactPhone = contactPhoneField.getText();
                String contactPosition = contactPositionField.getText();

                System.out.println("Nombre empresa: " + name);
                System.out.println("Correo: " + email);
                System.out.println("Sector: " + sector);
                System.out.println("Nombres contacto: " + contactNames);
                System.out.println("Apellidos contacto: " + contactLastNames);
                System.out.println("Teléfono contacto: " + contactPhone);
                System.out.println("Cargo contacto: " + contactPosition);
            }
        });
        bottomPanel.add(submitButton);

        // Botón cancelar que redirige a otro GUI
        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirigir a otro GUI
                //new AnotherGUI();  // Reemplaza `AnotherGUI` con el nombre de tu otro formulario
                dispose();  // Cerrar el formulario actual
            }
        });
        bottomPanel.add(cancelButton);


        // Agregar los paneles a la ventana principal
        rightPanel.add(companyPanel);
        rightPanel.add(contactPanel);
        add(leftPanel, BorderLayout.WEST);
        add(titleLabel, BorderLayout.NORTH);
        add(rightPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Llamar al formulario para que el usuario ingrese los datos
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CompanyForm();
            }
        });
    }
}
