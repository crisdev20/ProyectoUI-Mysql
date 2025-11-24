import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

// ventana pa registrar nuevo user
public class RegistrationForm extends JFrame {

    // campos donde escribe su info
    JTextField campoUsuario;
    JTextField campoNombre;
    JTextField campoApellido;
    JTextField campoTelefono;
    JTextField campoEmail;
    JPasswordField campoPassword;
    JPasswordField campoConfirmarPassword;

    public RegistrationForm() {
        setTitle("Registrar Usuario");
        setSize(400, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // instacia del panel
        JPanel panel = new JPanel();
        panel.setLayout(null);

        
        JLabel titulo = new JLabel("REGISTRO"); // titulo
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(140, 20, 150, 30);
        panel.add(titulo);

        // campo usuario, nombre, apellido, telefono, email, password, confirmar password
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setBounds(50, 70, 100, 25);
        panel.add(lblUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        campoUsuario.setBounds(50, 100, 300, 30);
        panel.add(campoUsuario);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNombre.setBounds(50, 140, 100, 25);
        panel.add(lblNombre);

        campoNombre = new JTextField();
        campoNombre.setFont(new Font("Arial", Font.PLAIN, 16));
        campoNombre.setBounds(50, 170, 300, 30);
        panel.add(campoNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setFont(new Font("Arial", Font.PLAIN, 16));
        lblApellido.setBounds(50, 210, 100, 25);
        panel.add(lblApellido);

        campoApellido = new JTextField();
        campoApellido.setFont(new Font("Arial", Font.PLAIN, 16));
        campoApellido.setBounds(50, 240, 300, 30);
        panel.add(campoApellido);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTelefono.setBounds(50, 280, 100, 25);
        panel.add(lblTelefono);

        campoTelefono = new JTextField();
        campoTelefono.setFont(new Font("Arial", Font.PLAIN, 16));
        campoTelefono.setBounds(50, 310, 300, 30);
        panel.add(campoTelefono);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        lblEmail.setBounds(50, 350, 100, 25);
        panel.add(lblEmail);

        campoEmail = new JTextField();
        campoEmail.setFont(new Font("Arial", Font.PLAIN, 16));
        campoEmail.setBounds(50, 380, 300, 30);
        panel.add(campoEmail);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPassword.setBounds(50, 420, 100, 25);
        panel.add(lblPassword);

        campoPassword = new JPasswordField();
        campoPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        campoPassword.setBounds(50, 450, 300, 30);
        panel.add(campoPassword);

        JLabel lblConfirmarPassword = new JLabel("Confirmar contraseña:");
        lblConfirmarPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblConfirmarPassword.setBounds(50, 490, 200, 25);
        panel.add(lblConfirmarPassword);

        campoConfirmarPassword = new JPasswordField();
        campoConfirmarPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        campoConfirmarPassword.setBounds(50, 520, 300, 30);
        panel.add(campoConfirmarPassword);

        // boton registrar
        JButton botonRegistrar = new JButton("REGISTRAR");
        botonRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        botonRegistrar.setBounds(50, 565, 140, 35);
        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        panel.add(botonRegistrar);

        // boton cancelar
        JButton botonCancelar = new JButton("CANCELAR");
        botonCancelar.setFont(new Font("Arial", Font.BOLD, 16));
        botonCancelar.setBounds(210, 565, 140, 35);
        botonCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(botonCancelar);

        add(panel);
        setVisible(true);
    }

    // metodo pa guardar el user en la base de datos
    void registrarUsuario() {
        String usuario = campoUsuario.getText();
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String telefono = campoTelefono.getText();
        String email = campoEmail.getText();
        String password = new String(campoPassword.getPassword());
        String confirmarPassword = new String(campoConfirmarPassword.getPassword());

        // reviso que todo los campo esten lleno si no marca un error
        if (usuario.equals("")) {
            JOptionPane.showMessageDialog(this, "debe ingresar el usuario", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nombre.equals("")) {
            JOptionPane.showMessageDialog(this, "debe ingresar el nombre", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (apellido.equals("")) {
            JOptionPane.showMessageDialog(this, "debe ingresar el apellido", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (telefono.equals("")) {
            JOptionPane.showMessageDialog(this, "debe ingresar el telefono", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (email.equals("")) {
            JOptionPane.showMessageDialog(this, "debe ingresar el email", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (password.equals("")) {
            JOptionPane.showMessageDialog(this, "debe ingresar la contraseña", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (confirmarPassword.equals("")) {
            JOptionPane.showMessageDialog(this, "debe confirmar la contraseña", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // reviso que las contraseñas son iguales
        if (!password.equals(confirmarPassword)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Registrando: " + usuario);

        // lo guardo en mysql
        try {
            Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_login", "root", "");

            String sql = "INSERT INTO usuarios (usuario, nombre, apellido, telefono, email, contraseña) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, telefono);
            ps.setString(5, email);
            ps.setString(6, password);

            ps.executeUpdate();
            ps.close();
            conexion.close();

            System.out.println("usuario registrado!");
            JOptionPane.showMessageDialog(this, "usuario registrado correctamente", "exito", JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (Exception error) {
            System.out.println("error: " + error.getMessage());
            JOptionPane.showMessageDialog(this, "error al registrar: " + error.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
