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
        if (this.cuentas.containsKey(nuevaCuenta.ConseguirElCvu())) {
            throw new IllegalArgumentException("Ya existe una cuenta con el mismo CVU.");
        }
        else {
            this.cuentas.put(nuevaCuenta.ConseguirElCvu(), nuevaCuenta);
        }
    
    	
    }
    public boolean tieneAutorizacion(String cuitEmpresa) {
        return this.empresasDelegadas.contains(cuitEmpresa); //falta implementar mejor porque deberia de buscar el cuit dentro de las empresas
        
    	
    }
    public double obtenerTotalInvertido() {
        return this.totalInvertido;
    }
    public void actualizarTotalInvertido(double monto) {
        this.totalInvertido += monto;
    }
    public Cuenta obtenerCuenta(String cvu) {
        return this.cuentas.get(cvu);
    	
    }
}
