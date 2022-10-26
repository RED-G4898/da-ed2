package uia.com.agendafx.agendafx;

public class Recordatorio {
    private String tipo;
    private String nombre;
    private String fechaRecordatorio;
    private String fecha;


    public Recordatorio(String tipo, String nombre, String fechaRecordatorio, String fecha) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.fechaRecordatorio = fechaRecordatorio;
        this.fecha = fecha;
    }

    public Recordatorio() {
    }


    public String getNombre() {
        return nombre;
    }

    public String getFechaRecordatorio() {
        return fechaRecordatorio;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;
    }


    public void setTipo(String text) {
        this.tipo = text;

    }

    public void setNombre(String text) {
        this.nombre = text;

    }

    public void setFecha(String text) {
        this.fechaRecordatorio = text;
    }

    public void setFechaRecordatorio(String text) {
        this.fecha = text;

    }
}
