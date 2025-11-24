import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

// esta e la ventana principal que se abre despue del login
public class MainFrame extends JFrame {

    User usuario;
    LoginForm loginForm; // la ventana de ir de vuelta
    DefaultTableModel modeloTabla; //  guardo los datos de la tabla
    JTable tabla; // donde muestro los datos

    public MainFrame(User usuario, LoginForm loginForm) { // el constructor de todo eso
        this.usuario = usuario;
        this.loginForm = loginForm;
    }

    // este metodo crea toda la ventana
    public void initialize(boolean mostrar) {
        setTitle("Dashboard - Sistema");
        setSize(1200, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // instancia del panel principal
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        // panel de la izquierda con perfil
        JPanel panelPerfil = new JPanel();
        panelPerfil.setBackground(Color.LIGHT_GRAY);
        panelPerfil.setPreferredSize(new Dimension(280, 650));
        panelPerfil.setLayout(null);

        JLabel lblTituloPerfil = new JLabel("PERFIL");
        lblTituloPerfil.setFont(new Font("Arial", Font.BOLD, 24));
        lblTituloPerfil.setBounds(100, 30, 150, 30);
        panelPerfil.add(lblTituloPerfil);


        // muestro el usuario
        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 16));
        lblUsuario.setBounds(30, 90, 100, 25);
        panelPerfil.add(lblUsuario);

        JLabel lblUsuarioValor = new JLabel(usuario.getUsuario());
        lblUsuarioValor.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuarioValor.setBounds(30, 120, 220, 25);
        panelPerfil.add(lblUsuarioValor);


        // muestro el nombre completo, email y telefono
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 16));
        lblNombre.setBounds(30, 160, 100, 25);
        panelPerfil.add(lblNombre);

        JLabel lblNombreValor = new JLabel(usuario.getNombre() + " " + usuario.getApellido());
        lblNombreValor.setFont(new Font("Arial", Font.PLAIN, 16));
        lblNombreValor.setBounds(30, 190, 220, 25);
        panelPerfil.add(lblNombreValor);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.BOLD, 16));
        lblEmail.setBounds(30, 230, 100, 25);
        panelPerfil.add(lblEmail);

        JLabel lblEmailValor = new JLabel(usuario.getEmail());
        lblEmailValor.setFont(new Font("Arial", Font.PLAIN, 14));
        lblEmailValor.setBounds(30, 260, 220, 25);
        panelPerfil.add(lblEmailValor);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 16));
        lblTelefono.setBounds(30, 300, 100, 25);
        panelPerfil.add(lblTelefono);

        JLabel lblTelefonoValor = new JLabel(usuario.getTelefono());
        lblTelefonoValor.setFont(new Font("Arial", Font.PLAIN, 16));
        lblTelefonoValor.setBounds(30, 330, 220, 25);
        panelPerfil.add(lblTelefonoValor);

        // creo el panel principal del centro
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BorderLayout());

        // titulo de la tabla
        JLabel lblTituloTabla = new JLabel("Usuarios del sistema", SwingConstants.CENTER);
        lblTituloTabla.setFont(new Font("Arial", Font.BOLD, 24));
        panelCentro.add(lblTituloTabla, BorderLayout.NORTH);

        // creo la tabla con sus columnas
        String[] columnas = {"ID", "Usuario", "Nombre", "Apellido", "Telefono", "Email", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return column != 0; // con esto el ID no se edita
            }
        };

        
        cargarDatos(); // cargo los dato de mysql a la tabla


        // creo la tabla y le pongo el modelo
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));
        tabla.setRowHeight(35);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));


        
        JScrollPane scroll = new JScrollPane(tabla);
        panelCentro.add(scroll, BorderLayout.CENTER); // scroll para cuando hay mucho datos

        // BOTONES DE ABAJO
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.LIGHT_GRAY);


        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 16));
        btnGuardar.setPreferredSize(new Dimension(140, 45));
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardar();
            }
        });
        panelBotones.add(btnGuardar);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFont(new Font("Arial", Font.BOLD, 16));
        btnAgregar.setPreferredSize(new Dimension(140, 45));
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregar();
            }
        });
        panelBotones.add(btnAgregar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 16));
        btnEliminar.setPreferredSize(new Dimension(140, 45));
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminar();
            }
        });
        panelBotones.add(btnEliminar);

        JButton btnRecargar = new JButton("Recargar");
        btnRecargar.setFont(new Font("Arial", Font.BOLD, 16));
        btnRecargar.setPreferredSize(new Dimension(140, 45));
        btnRecargar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cargarDatos();
            }
        });
        panelBotones.add(btnRecargar);

        JButton btnSalir = new JButton("Cerrar sesion");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 16));
        btnSalir.setPreferredSize(new Dimension(140, 45));
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                loginForm.campoUsuario.setText("");
                loginForm.campoPassword.setText("");
                loginForm.setVisible(true);
            }
        });
        panelBotones.add(btnSalir);

        //todo junto
        panelPrincipal.add(panelPerfil, BorderLayout.WEST);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        if (mostrar) {
            setVisible(true);
        }
    }

    void cargarDatos() {
        modeloTabla.setRowCount(0);// limpio la tabla


        System.out.println("cargando usuarios");

        // me conecto a mysql
        try {
            Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_login", "root", "");
            String sql = "SELECT * FROM usuarios";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();


            while (resultado.next()) {
                int id = resultado.getInt("id_usuario");
                String user = resultado.getString("usuario");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String telefono = resultado.getString("telefono");
                String email = resultado.getString("email");
                String estado = resultado.getString("estado");

                modeloTabla.addRow(new Object[]{id, user, nombre, apellido, telefono, email, estado});
                System.out.println("- " + user);
            }

            resultado.close();
            ps.close();
            conexion.close();

            System.out.println("datos cargados!");

        } catch (Exception error) {
            System.out.println("error: " + error.getMessage());
        }
    }

    // este metodo guarda lo cambios de mysql
    void guardar() {
        System.out.println("guardando cambios");

        try {
            Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sistema_login", "root", "");

            String sqlUpdate = "UPDATE usuarios SET usuario=?, nombre=?, apellido=?, telefono=?, email=?, estado=? WHERE id_usuario=?"; // consulta de update
            PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate);

            // recorriendo todas las filas de la tabla
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                Object idObj = modeloTabla.getValueAt(i, 0);
                Object userObj = modeloTabla.getValueAt(i, 1);
                Object nombreObj = modeloTabla.getValueAt(i, 2);
                Object apellidoObj = modeloTabla.getValueAt(i, 3);
                Object telefonoObj = modeloTabla.getValueAt(i, 4);
                Object emailObj = modeloTabla.getValueAt(i, 5);
                Object estadoObj = modeloTabla.getValueAt(i, 6);

                // nada debe ser null
                if (idObj == null || userObj == null || nombreObj == null ||
                    apellidoObj == null || telefonoObj == null || emailObj == null || estadoObj == null) {
                    continue;
                }

                // convierto a texto
                int id = Integer.parseInt(idObj.toString());
                String user = userObj.toString();
                String nombre = nombreObj.toString();
                String apellido = apellidoObj.toString();
                String telefono = telefonoObj.toString();
                String email = emailObj.toString();
                String estado = estadoObj.toString();

                //  que no esten vacio
                if (user.trim().equals("") || nombre.trim().equals("") ||
                    apellido.trim().equals("") || telefono.trim().equals("") ||
                    email.trim().equals("") || estado.trim().equals("")) {
                    continue;
                }

                // update en mysql
                psUpdate.setString(1, user);
                psUpdate.setString(2, nombre);
                psUpdate.setString(3, apellido);
                psUpdate.setString(4, telefono);
                psUpdate.setString(5, email);
                psUpdate.setString(6, estado);
                psUpdate.setInt(7, id);
                psUpdate.executeUpdate();
            }

            psUpdate.close();
            conexion.close();

            System.out.println("cambios guardados!");
            JOptionPane.showMessageDialog(this, "cambios guardados correctamente!");

        } catch (Exception error) {
            System.out.println("error: " + error.getMessage());
            JOptionPane.showMessageDialog(this, "error al guardar: " + error.getMessage());
        }
    }

    //  metodo agrega un user
    void agregar() {
        modeloTabla.addRow(new Object[]{0, "nuevo_usuario", "Nombre", "Apellido", "0000000000", "email@ejemplo.com", "activo"});
        JOptionPane.showMessageDialog(this, "fila agregada.");
    }

    // metodo eliminar un user
    void eliminar() {
        int fila = tabla.getSelectedRow();         // para ver la fila q selecciono


        // validamos q ue selecciono una fila
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "selecciona una fila primero");
            return;
        }

        // agarro el id del user
        int id = (int) modeloTabla.getValueAt(fila, 0);

        // para preguntar si esta seguro
        int respuesta = JOptionPane.showConfirmDialog(this,
            "eliminar este usuario?",
            "confirmar",
            JOptionPane.YES_NO_OPTION);

        // si dijo que si entonces lo elimino
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sistema_login", "root", "");

                // elimino de mysql
                String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setInt(1, id);
                ps.executeUpdate();

                ps.close();
                conexion.close();
                modeloTabla.removeRow(fila);

                JOptionPane.showMessageDialog(this, "usuario eliminado!");

            } catch (Exception error) {
                System.out.println("error: " + error.getMessage());
                JOptionPane.showMessageDialog(this, "error al eliminar: " + error.getMessage());
            }
        }
    }
}
