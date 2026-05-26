package principal;

import java.util.HashMap;
import java.util.HashSet;

public class Usuario {
	private String nombre;
    private String correo;
    private String numeroDeTelefono;
    private String cuitPersonal;
    private double totalInvertido;
    private HashMap<String, Cuenta> cuentas; // String = CVU
    private HashSet<String> empresasDelegadas;

    // Operaciones (incluyendo constructor)
    public Usuario(String dni, String nombre, String telefono, String correo) {
    	this.cuitPersonal = dni;
        this.nombre = nombre;
        this.numeroDeTelefono = telefono;
        this.correo = correo;
        this.totalInvertido = 0.0;
        this.cuentas = new HashMap<>();
        this.empresasDelegadas = new HashSet<>();
	}
    public void agregarCuenta(Cuenta nuevaCuenta) {
    	
    }
    public boolean tieneAutorizacion(String cuitEmpresa) {
    	
    }
    public double obtenerTotalInvertido() {
    	
    }
    public void actualizarTotalInvertido(double monto) {
    	
    }
    public Cuenta obtenerCuenta(String cvu) {
    	
    }
}
