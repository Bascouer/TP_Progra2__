package principal;

import java.time.LocalDateTime;

public class Inversion {
	private LocalDateTime fechaDeConstitucion;
    private int plazo;
    private double montoInvertido;
    private boolean esPrecancelable;
    private String estado;
	private int idInversion;

   
    public Inversion(int idInversion, LocalDateTime fechaConstitucion, int plazo, double montoInvertido) {
    	this.idInversion = idInversion;
        this.fechaDeConstitucion = fechaConstitucion;
        this.plazo = plazo;
        this.montoInvertido = montoInvertido;
        this.esPrecancelable = true; 
        this.estado = "Activa";
        
    }

    public double calcularResultado() {
        return 0.0;
    }

    public void ejecutarPrecancelacion() {
        
    }

    public void actualizarEstado() {
        
    }

}
