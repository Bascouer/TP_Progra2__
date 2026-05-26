package principal;

import java.time.LocalDateTime;

public class Registro_Inversion extends Actividad {
	
	private String tipoDeOperacion; 

    // Constructor
    public Registro_Inversion(String idActividad, LocalDateTime fecha, double monto, Cuenta cuentaOrigen, String tipoDeOperacion) {
        super(idActividad, fecha, monto, cuentaOrigen);
        this.tipoDeOperacion = tipoDeOperacion;
    }

}
