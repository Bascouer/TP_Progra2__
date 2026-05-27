package principal;

import java.time.LocalDateTime;

public class Actividad {
	private String idActividad;
    private LocalDateTime fecha;
    private double monto;
    private Cuenta cuentaOrigen;

    // Operaciones (Constructor)
    public Actividad(String idActividad, LocalDateTime fecha, double monto, Cuenta cuentaOrigen) {
        this.idActividad = idActividad;
        this.fecha = fecha;
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
    }


        
    
    public String obtenerDetalle(){
        return "Actividad ID: " + this.idActividad + ", Fecha: " + this.fecha + ", Monto: " + this.monto;
    }
}