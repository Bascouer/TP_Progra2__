package principal;

import java.util.LinkedList;

public class Cuenta {
	private String cvu;
    private String alias;
    private double saldo;
    private double dineroInvertido;
    private double dineroEnCuenta;
    private int contadorTransacciones;
    private LinkedList<Actividad> historialLocal;

    // Operaciones (Constructor)
    public Cuenta(String cvu, String alias) {
        
    }

    public void transferirDinero(double monto) {
        
    }

    public void agregarDinero(double monto) {
        
    }

    public void extraerDinero(double monto) {
        
    }

    public double consultarSaldo() {
        return 0.0;
    }

    public void invertirDinero(double monto) {
        
    }

    public void aumentarContadorTransacciones() {
        
    }

    public boolean validarReglas() {
        return false;
    }
}
	