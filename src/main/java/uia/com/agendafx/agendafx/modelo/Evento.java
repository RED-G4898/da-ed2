package uia.com.agendafx.agendafx.modelo;


public class Evento {

    Recordatorio recordatorio=null;
    public Evento() {
    }

    public Evento(String tipo, String fecha, String fechaRecordatorio, String nombre)
    {
        recordatorio = new Recordatorio(tipo, fecha, fechaRecordatorio, nombre);
    }

    public Recordatorio getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(Recordatorio recordatorio) {
        this.recordatorio = recordatorio;
    }
}
