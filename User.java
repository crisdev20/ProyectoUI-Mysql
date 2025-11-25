
public class User {

    private int idUsuario;
    private String usuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String contraseña;
    private String fechaRegistro;
    private String estado;

    // este constructor crea un user con toda su info
    public User(int idUsuario, String usuario, String nombre, String apellido,
                String telefono, String email, String contraseña,
                String fechaRegistro, String estado) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.contraseña = contraseña;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // getter y setter para cada objeto
    public int getIdUsuario() {
         return idUsuario; }

    public String getUsuario() { 
        return usuario; }

    public String getNombre() { 
        return nombre; }

    public String getApellido() { 
        return apellido; }

    public String getTelefono() { 
        return telefono; }

    public String getEmail() { 
        return email; }

    public String getContraseña() { 
        return contraseña; }

    public String getFechaRegistro() { 
        return fechaRegistro; }

    public String getEstado() { 
        return estado; }

    // con esto cambia todo lo del user
    public void setIdUsuario(int idUsuario) { 
        this.idUsuario = idUsuario; }

    public void setUsuario(String usuario) { 
        this.usuario = usuario; }

    public void setNombre(String nombre) { 
        this.nombre = nombre; }
        
    public void setApellido(String apellido) { 
        this.apellido = apellido; }

    public void setTelefono(String telefono) { 
        this.telefono = telefono; }

    public void setEmail(String email) { 
        this.email = email; }

    public void setContraseña(String contraseña) { 
        this.contraseña = contraseña; }

    public void setFechaRegistro(String fechaRegistro) { 
        this.fechaRegistro = fechaRegistro; }

    public void setEstado(String estado) { 
        this.estado = estado; }
}
