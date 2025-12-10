package vallegrade.edu.pe.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Equipo {
    private int id;
    private String codigo;
    private String tipo;
    private String marcas;
    private String modelo;
    private String so;
    private int almacenamiento;
    private int ram;
    private String estado;
    private LocalDate mantenimiento;
    private LocalDate fechaRegistro;

    public Equipo() {}

    public Equipo(String codigo, String tipo, String marcas, String modelo, String so, int almacenamiento, int ram, String estado, LocalDate fechaRegistro) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.marcas = marcas;
        this.modelo = modelo;
        this.so = so;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
    }

    public Equipo(String codigo, String tipo, String marcas, String modelo, String so, int almacenamiento, int ram, String estado, LocalDate mantenimiento, LocalDate fechaRegistro) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.marcas = marcas;
        this.modelo = modelo;
        this.so = so;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
        this.estado = estado;
        this.mantenimiento = mantenimiento;
        this.fechaRegistro = fechaRegistro;
    }

    public Equipo(int id, String codigo, String tipo, String marcas, String modelo, String so, int almacenamiento, int ram, String estado, LocalDate mantenimiento, LocalDate fechaRegistro) {
        this.id = id;
        this.codigo = codigo;
        this.tipo = tipo;
        this.marcas = marcas;
        this.modelo = modelo;
        this.so = so;
        this.almacenamiento = almacenamiento;
        this.ram = ram;
        this.estado = estado;
        this.mantenimiento = mantenimiento;
        this.fechaRegistro = fechaRegistro;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMarcas() { return marcas; }
    public void setMarcas(String marcas) { this.marcas = marcas; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getSo() { return so; }
    public void setSo(String so) { this.so = so; }

    public int getAlmacenamiento() { return almacenamiento; }
    public void setAlmacenamiento(int almacenamiento) { this.almacenamiento = almacenamiento; }

    public int getRam() { return ram; }
    public void setRam(int ram) { this.ram = ram; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDate getMantenimiento() { return mantenimiento; }
    public void setMantenimiento(LocalDate mantenimiento) { this.mantenimiento = mantenimiento; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Equipo{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", tipo='" + tipo + '\'' +
                ", marcas='" + marcas + '\'' +
                ", modelo='" + modelo + '\'' +
                ", so='" + so + '\'' +
                ", almacenamiento=" + almacenamiento +
                ", ram=" + ram +
                ", estado='" + estado + '\'' +
                ", mantenimiento=" + (mantenimiento != null ? mantenimiento.format(formatter) : "null") +
                ", fechaRegistro=" + (fechaRegistro != null ? fechaRegistro.format(formatter) : "null") +
                '}';
    }
}
