import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

// esta es la ventana del login
public class LoginForm extends JFrame {

    // aqui esta donde escribes
    JTextField campoUsuario;
    JPasswordField campoPassword;

    public LoginForm() {
        // configuro como se vera la ventana
        setTitle("Login - Sistema");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // creo el panel donde van todo los  componente
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // el titulo que es ta arriba
        JLabel titulo = new JLabel("INICIAR SESION");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(80, 20, 200, 30);
        panel.add(titulo);

        // la etiqueta que dice usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setBounds(50, 70, 100, 25);
        panel.add(lblUsuario);

        // el campo donde pone el usuario
        campoUsuario = new JTextField();
        campoUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        campoUsuario.setBounds(50, 100, 250, 30);
        panel.add(campoUsuario);

        // la etiqueta que dice contraseña
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPassword.setBounds(50, 140, 100, 25);
        panel.add(lblPassword);

        // el campo donde pone la contraseña
        campoPassword = new JPasswordField();
        campoPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        campoPassword.setBounds(50, 170, 250, 30);
        panel.add(campoPassword);

        // el boton para entrar
        JButton botonLogin = new JButton("LOGIN");
        botonLogin.setFont(new Font("Arial", Font.BOLD, 16));
        botonLogin.setBounds(50, 220, 120, 35);
        botonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hacerLogin();
            }
        });
        panel.add(botonLogin);

        // el boton para registrarte
        JButton botonRegistrar = new JButton("REGISTRAR");
        botonRegistrar.setFont(new Font("Arial", Font.BOLD, 16));
        botonRegistrar.setBounds(180, 220, 120, 35);
        botonRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new RegistrationForm();
            }
        });
        panel.add(botonRegistrar);

        add(panel);
        setVisible(true);
    }

    // este metodo revisa si tu usuario y contraseña son validos
    void hacerLogin() {
        // agarro lo que escribiste
        String usuario = campoUsuario.getText();
        String password = new String(campoPassword.getPassword());

        // miro que no te lo dejaste vacio
        if (usuario.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this,
                "debe ingresar su usuario y contraseña, si no esta registrado debe registrarse",
                "error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("intentando login: " + usuario);

        // me conecto a mysql
        try {
            Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_login", "root", "");

            // busco si ese user existe con esa clave
            String sql = "SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ? AND estado = 'activo'";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet resultado = ps.executeQuery();

            if (resultado.next()) {
                // si lo encuentra imprime login correcto
                System.out.println("Login correcto");

                // creo el objeto con todo lo datos del user
                User usuarioLogueado = new User(
                    resultado.getInt("id_usuario"),
                    resultado.getString("usuario"),
                    resultado.getString("nombre"),
                    resultado.getString("apellido"),
                    resultado.getString("telefono"),
                    resultado.getString("email"),
                    resultado.getString("contraseña"),
                    resultado.getString("fecha_registro"),
                    resultado.getString("estado")
                );

                // abro el dashboard
                MainFrame dashboard = new MainFrame(usuarioLogueado, LoginForm.this);
                dashboard.initialize(true);
                setVisible(false);

            } else {
                // si no lo encontre ta mal el usuario o la clave
                System.out.println("login incorrecto");
                JOptionPane.showMessageDialog(this,
                    "usuario o contraseña incorrectos",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
            }

            resultado.close();
            ps.close();
            conexion.close();

        } catch (Exception error) {
            System.out.println("error: " + error.getMessage());
            error.printStackTrace();
        }
    }

    
    public static void main(String[] args) { // el main
        new LoginForm();
    }
}
