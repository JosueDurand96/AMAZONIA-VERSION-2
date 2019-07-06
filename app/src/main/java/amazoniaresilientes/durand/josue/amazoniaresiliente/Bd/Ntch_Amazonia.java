package amazoniaresilientes.durand.josue.amazoniaresiliente.Bd;

public class Ntch_Amazonia {
    //TODO: PANTALLA 1
    private int id;
    private  String cultivo;
    private String primer_nombre;
    private String segundo_nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String estado_civil;
    private String dni;
    private String referencia_predio;
    //TODO: PANTALLA 2
    private String departamento_cliente;
    //TODO: PANTALLA 3
    private String poligono;
    private String area;
    private String precision;
    //TODO PANTALLA 4
    private byte[] imagen1;
    private String lat1;
    private String lng1;
    private byte[] imagen2;
    private String lat2;
    private String lng2;
    private byte[] imagen3;
    private String lat3;
    private String lng3;
    private byte[] imagen4;
    private String lat4;
    private String lng4;

    public Ntch_Amazonia(int id, String cultivo, String primer_nombre, String segundo_nombre, String apellido_paterno, String apellido_materno, String estado_civil, String dni, String referencia_predio, String departamento_cliente, String poligono, String area, String precision, byte[] imagen1, String lat1, String lng1, byte[] imagen2, String lat2, String lng2, byte[] imagen3, String lat3, String lng3, byte[] imagen4, String lat4, String lng4) {
        this.id = id;
        this.cultivo = cultivo;
        this.primer_nombre = primer_nombre;
        this.segundo_nombre = segundo_nombre;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.estado_civil = estado_civil;
        this.dni = dni;
        this.referencia_predio = referencia_predio;
        this.departamento_cliente = departamento_cliente;
        this.poligono = poligono;
        this.area = area;
        this.precision = precision;
        this.imagen1 = imagen1;
        this.lat1 = lat1;
        this.lng1 = lng1;
        this.imagen2 = imagen2;
        this.lat2 = lat2;
        this.lng2 = lng2;
        this.imagen3 = imagen3;
        this.lat3 = lat3;
        this.lng3 = lng3;
        this.imagen4 = imagen4;
        this.lat4 = lat4;
        this.lng4 = lng4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCultivo() {
        return cultivo;
    }

    public void setCultivo(String cultivo) {
        this.cultivo = cultivo;
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

    public String getReferencia_predio() {
        return referencia_predio;
    }

    public void setReferencia_predio(String referencia_predio) {
        this.referencia_predio = referencia_predio;
    }

    public String getDepartamento_cliente() {
        return departamento_cliente;
    }

    public void setDepartamento_cliente(String departamento_cliente) {
        this.departamento_cliente = departamento_cliente;
    }

    public String getPoligono() {
        return poligono;
    }

    public void setPoligono(String poligono) {
        this.poligono = poligono;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrecision() {
        return precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public byte[] getImagen1() {
        return imagen1;
    }

    public void setImagen1(byte[] imagen1) {
        this.imagen1 = imagen1;
    }

    public String getLat1() {
        return lat1;
    }

    public void setLat1(String lat1) {
        this.lat1 = lat1;
    }

    public String getLng1() {
        return lng1;
    }

    public void setLng1(String lng1) {
        this.lng1 = lng1;
    }

    public byte[] getImagen2() {
        return imagen2;
    }

    public void setImagen2(byte[] imagen2) {
        this.imagen2 = imagen2;
    }

    public String getLat2() {
        return lat2;
    }

    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }

    public String getLng2() {
        return lng2;
    }

    public void setLng2(String lng2) {
        this.lng2 = lng2;
    }

    public byte[] getImagen3() {
        return imagen3;
    }

    public void setImagen3(byte[] imagen3) {
        this.imagen3 = imagen3;
    }

    public String getLat3() {
        return lat3;
    }

    public void setLat3(String lat3) {
        this.lat3 = lat3;
    }

    public String getLng3() {
        return lng3;
    }

    public void setLng3(String lng3) {
        this.lng3 = lng3;
    }

    public byte[] getImagen4() {
        return imagen4;
    }

    public void setImagen4(byte[] imagen4) {
        this.imagen4 = imagen4;
    }

    public String getLat4() {
        return lat4;
    }

    public void setLat4(String lat4) {
        this.lat4 = lat4;
    }

    public String getLng4() {
        return lng4;
    }

    public void setLng4(String lng4) {
        this.lng4 = lng4;
    }
}
