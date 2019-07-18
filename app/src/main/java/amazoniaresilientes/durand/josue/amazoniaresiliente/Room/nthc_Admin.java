package amazoniaresilientes.durand.josue.amazoniaresiliente.Room;

public class nthc_Admin {
    private int idnthc_usuario;
    private String primer_nombre, segundo_nombre, apellido_paterno, apellido_materno,estado_civil,dni,correo,password,celular;

    public nthc_Admin(int idnthc_usuario, String primer_nombre, String segundo_nombre, String apellido_paterno, String apellido_materno, String estado_civil, String dni, String correo, String celular) {
        this.idnthc_usuario = idnthc_usuario;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.estado_civil = estado_civil;
        this.dni = dni;
        this.correo = correo;

        this.celular = celular;
    }

    public int getId() {
        return idnthc_usuario;
    }

    public void setId(int idnthc_usuario) {
        this.idnthc_usuario = idnthc_usuario;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return segundo_nombre;
    }

    public void setSegundo_nombre(String segundo_nombre) {
        this.segundo_nombre = segundo_nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }





    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
