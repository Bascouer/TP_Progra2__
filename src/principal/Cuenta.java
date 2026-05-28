package principal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Cuenta {
	private String cvu;
    private String alias;
    protected double saldo;
    private double dineroInvertido;
    private double dineroEnCuenta;
    private int contadorTransacciones;
    private LinkedList<Actividad> historialLocal;
    private HashMap<Integer, Inversion> inversiones;

    // Operaciones (Constructor)
    public Cuenta(String cvu, String alias) {
    	this.cvu = cvu;
        this.alias = alias;
        this.saldo = 0.0;
        this.dineroInvertido = 0.0;
        this.dineroEnCuenta = 0.0;
        this.contadorTransacciones = 0;
        this.historialLocal = new LinkedList<>();
        
    }
    public String ConseguirElCvu() {
        return this.cvu;
    }

    public Actividad transferirDinero(double monto, String cvuDestino, boolean esInterna) {
        // 1. Validamos que haya plata suficiente (opcional, pero buena práctica)
        if (this.saldo < monto) {
            throw new IllegalArgumentException("Saldo insuficiente para transferir.");
        }

        // 2. Lógica financiera: restamos la plata y sumamos al contador
        this.saldo -= monto;
        this.contadorTransacciones++;

        // 3. Generamos un ID único para este ticket (usamos la hora del sistema como truco fácil)
        String idTicket = "TRX-" + System.currentTimeMillis();
        LocalDateTime fechaActual = LocalDateTime.now();

        // 4. CREAMOS LA ACTIVIDAD (Imprimimos el ticket)
        // Pasamos "this" en cuentaOrigen porque esta misma cuenta es la que hace la operación
        Registro_Transferencia ticket = new Registro_Transferencia(idTicket, fechaActual, monto, this, cvuDestino, esInterna);

        // 5. Lo guardamos en el historial de la cuenta
        this.historialLocal.add(ticket);
        aumentarContadorDeTransacciones();
        return ticket;
    }

    public void agregarDinero(double monto) {
    	if (monto <= 0) {
            throw new IllegalArgumentException("Error: El monto a agregar debe ser mayor a cero.");
        }
        
        this.saldo += monto;
        actualizarDineroEnCuenta();
        validarReglas();
        
    }

    private void actualizarDineroEnCuenta() {
    	this.dineroEnCuenta = this.saldo + this.dineroInvertido;
		
	}
	public void extraerDinero(double monto) {
		if(monto < 0 ) {
			throw new IllegalArgumentException("El saldo no puede ser negativo");
		}
		if (this.saldo < monto) {
			throw new IllegalArgumentException("Saldo Insuficiente");	
		}
		this.saldo -= monto;
		actualizarDineroEnCuenta();
		validarReglas();
    }

    public double consultarSaldo() {
        return this.saldo;
    }

    public Actividad invertirDinero(double monto, Inversion inversion, String tipoOperacion) {
        // 1. Validamos saldo
        if (this.saldo < monto) {
            throw new IllegalArgumentException("Saldo insuficiente para invertir.");
        }

        // 2. Lógica financiera: sacamos de saldo y ponemos en dineroInvertido
        this.saldo -= monto;
        this.dineroInvertido += monto;
        actualizarDineroEnCuenta();
        validarReglas();
        

        // 3. Generamos datos del ticket
        String idTicket = "INV-" + System.currentTimeMillis();
        LocalDateTime fechaActual = LocalDateTime.now();

        // 4. CREAMOS LA ACTIVIDAD
        Registro_Inversion ticket = new Registro_Inversion(idTicket, fechaActual, monto, this, tipoOperacion);

        // 5. Lo guardamos en el historial local
        this.historialLocal.add(ticket);
        inversiones.put(inversion.obtenerId(), inversion);
        return ticket;
    }
   

    public boolean validarReglas() {
        return false;
    }
    
    public double obtenerFactorDeCalculo() { // esta funcion se utiliza para hacer el calculo de benefiicios de cada cuenta; por ejemplo si es una cuenta premium tiene unas mejores comisiones
        return 1.0;
    }
    
    public Inversion obtenerInversion(int idInversion) {
        return inversiones.get(idInversion);
    }
    public Actividad registrarCancelacion(double monto) {
        String idTicket = "CAN-" + System.currentTimeMillis();
        LocalDateTime fechaActual = LocalDateTime.now();
        Registro_Inversion ticket = new Registro_Inversion(idTicket, fechaActual, monto, this, "Cancelacion");
        this.historialLocal.add(ticket);
        return ticket;
    }

	public List<String> consultarHistorial() {
		List<String> resultado = new ArrayList<>();
		for(Actividad actividad : historialLocal) {
		    resultado.add(actividad.obtenerDetalle());
		}
		return resultado;
	}
	public void aumentarContadorDeTransacciones() {
		contadorTransacciones +=1;
	}
	public int obtenerContadorDeTransacciones() {
		return this.contadorTransacciones;
	}


}
	