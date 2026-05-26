package principal;

import java.time.LocalDateTime;

public class Inversion {
	private LocalDateTime fechaDeConstitucion;
    private int plazo;
    protected double montoInvertido;
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
        return this.montoInvertido * 1.10; //No lo termine todavia ni me fije bien como hacerlo
    }

    public void ejecutarPrecancelacion() { 
    if (this.esPrecancelable && this.estado.equals("Activa")) {
            this.estado = "Precancelada";
        } else {
            throw new IllegalStateException("La inversión no es precancelable o no está activa.");
        }
        
    
        
    }

    public void actualizarEstado() {//va a ir rotando de estado si es activa pasa a finalizada FALTA VERIFICAS QUE SE CUMPLEN LOS PLAZOS Y DEMAS
        if (this.estado.equals("Activa")){ 
            this.estado = "Finalizada";
        }
        
        
    }


}
