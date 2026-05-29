package principal;

import java.time.LocalDateTime;

public  abstract class  Inversion {
	protected LocalDateTime fechaDeConstitucion;
    private int plazo;
    protected double montoInvertido;
   protected boolean esPrecancelable;
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

    public abstract double calcularResultado(Cuenta cuenta);
      
 

    public void ejecutarPrecancelacion(Cuenta cuenta) {
        if (this.esPrecancelable && this.estado.equals("Activa")) {
            this.estado = "Precancelada";
            this.montoInvertido = calcularMontoFinal(cuenta);
        } else {
            throw new IllegalStateException("La inversión no es precancelable o no está activa.");
        }
    }
    public void actualizarEstado() {
        if (this.estado.equals("Activa")){ 
            this.estado = "Finalizada";
        }      
    }
    
    public double calcularMontoFinal(Cuenta cuenta) {
        return montoInvertido + calcularResultado(cuenta) / 2;
    }
    
    public double obtenerMontoInvertido() {
    	return this.montoInvertido;
    	
    }
    
    public int obtenerId() {
    	return this.idInversion;
    }


}
