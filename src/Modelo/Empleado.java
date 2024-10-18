package Modelo;

import java.math.BigDecimal;
import java.sql.Date;

public class Empleado {

    private String id;
    private String nombre;
    private String apellido;
    private String tipoDocumento;
    private String numDocumento;
    private String direccion;
    private String telefono;
    private String correo;
    private String sexo;
    private String cargo;
    private String departamento;
    private BigDecimal salario;
    private String estadoCivil;
    private java.sql.Date fechaNacimiento; // Cambiado a Date
    private java.sql.Date fechaIngreso; // Cambiado a Date
    private boolean estado; // Activo/Inactivo
    private String tipoPago;  // Nuevo campo
    private int anosExperiencia;  // Nuevo campo

    // Constructor vacío
    public Empleado() {
    }

    // Constructor
    public Empleado(String id, String nombre, String apellido, String tipoDocumento,
            String numDocumento, String direccion, String telefono, String correo,
            java.sql.Date fechaNacimiento, String estadoCivil, String sexo, String cargo,
            String departamento, BigDecimal salario, java.sql.Date fechaIngreso,
            boolean estado, String tipoPago, int anosExperiencia) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipoDocumento = tipoDocumento;
        this.numDocumento = numDocumento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.sexo = sexo;
        this.cargo = cargo;
        this.departamento = departamento;
        this.salario = salario;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.tipoPago = tipoPago;
        this.anosExperiencia = anosExperiencia;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getAnosExperiencia() {
        return anosExperiencia;
    }

    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public java.sql.Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public java.sql.Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
