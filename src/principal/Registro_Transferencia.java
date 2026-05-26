package principal;

import java.time.LocalDateTime;

public class Registro_Transferencia extends Actividad {


    private String cvuDestino;
    private boolean esInterna;

    // Constructor
    public Registro_Transferencia(String idActividad, LocalDateTime fecha, double monto, Cuenta cuentaOrigen, String cvuDestino, boolean esInterna) {
        super(idActividad, fecha, monto, cuentaOrigen); 
        this.cvuDestino = cvuDestino;
        this.esInterna = esInterna;
    }
}
