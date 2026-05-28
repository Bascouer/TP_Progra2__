package principal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Billetera implements IBilletera {
	private HashMap<String, Usuario> usuarios;
    private HashMap<String, Empresa> empresas; 
    private HashMap<String, Cuenta> cuentasPorCvu;
    private HashMap<String, String> cvuPorAlias;
    private HashMap<String, String> cvuPorDni;
    private ArrayList<Actividad> historialGlobal;
    
    private int generadorCvu;
    private int generadorIdInversion;

    
	public Billetera() {
		this.usuarios = new HashMap<>();
        this.empresas = new HashMap<>();
        this.cuentasPorCvu = new HashMap<>();
        this.cvuPorAlias = new HashMap<>();
        this.historialGlobal = new ArrayList<>();
        this.cvuPorDni = new HashMap<>();
        
        // Inicialización de los generadores (pueden arrancar en 1 o el número que prefieras)
        this.generadorCvu = 1; 
        this.generadorIdInversion = 1;
       
	
	}

	@Override
	public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email,
			String nombreContacto) {
		Empresa emprecita = new Empresa(cuit, nombreFantasia, telefono, email, nombreContacto);
		empresas.put(cuit, emprecita);
		
		
		
		

	}

	@Override
	public void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado) {
		if(!usuarios.containsKey(dniAutorizado)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		if(!empresas.containsKey(cuitEmpresa)) {
			throw new IllegalArgumentException(" El cuit no se encuentra asociado a ninguna empresa");
			
		}
		Empresa empresita = empresas.get(cuitEmpresa);
		empresita.agregarUsuarioAutorizado(dniAutorizado);
		Usuario user = usuarios.get(dniAutorizado);
		user.agregarEmpresaAutorizada(cuitEmpresa);

	}

	@Override
	public void registrarUsuario(String dni, String nombre, String telefono, String email) {
		Usuario user = new Usuario(dni , nombre, telefono, email);
		usuarios.put(dni , user);
		

	}

	@Override
	public String crearCuentaRegular(String dniUsuario, String alias) {
		if(!usuarios.containsKey(dniUsuario)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		String cvu = String.valueOf(generadorCvu);
		generadorCvu++;
		Cuenta_Regular cuentaR = new Cuenta_Regular (cvu, alias);
		Usuario user = usuarios.get(dniUsuario);
		user.agregarCuenta(cuentaR);
		cuentasPorCvu.put(cvu , cuentaR);
		cvuPorAlias.put(alias , cvu );
		cvuPorDni.put(cvu, dniUsuario);
		return cvu;
		
	}

	@Override
	public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {
		if(!usuarios.containsKey(dniUsuario)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		String cvu = String.valueOf(generadorCvu);
		generadorCvu++;
		Cuenta_Premium cuentaP = new Cuenta_Premium (cvu, alias, depositoInicial);
		Usuario user = usuarios.get(dniUsuario);
		user.agregarCuenta(cuentaP);
		cuentasPorCvu.put(cvu , cuentaP);
		cvuPorAlias.put(alias , cvu );
		cvuPorDni.put(cvu, dniUsuario);
		return cvu;

		
	}

	@Override
	public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
		if(!usuarios.containsKey(dniUsuario)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		if(!empresas.containsKey(cuitEmpresa)) {
			throw new IllegalArgumentException(" El cuit no se encuentra asociado a ninguna empresa");
	}
		Usuario user = usuarios.get(dniUsuario);
		
		if(!user.tieneAutorizacion(cuitEmpresa)) {
			throw new IllegalArgumentException(" El Usuario no esta autorizado para gestionar la cuenta");	
		}
		
		String cvu = String.valueOf(generadorCvu);
		generadorCvu++;
		Cuenta_Corporativa cuentaC = new Cuenta_Corporativa(cvu , alias , cuitEmpresa);
		user.agregarCuenta(cuentaC);
		cuentasPorCvu.put(cvu , cuentaC);
		cvuPorAlias.put(alias , cvu );
		cvuPorDni.put(cvu, dniUsuario);
		return cvu;
	}

	@Override
	public List<String> obtenerCuentas(String dniUsuario) {
		if(!usuarios.containsKey(dniUsuario)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		Usuario user = usuarios.get(dniUsuario);
		return user.obtenerCvus();
	}

	@Override
	public double obtenerSaldoDisponible(String cvu) {
		if(!cuentasPorCvu.containsKey(cvu)) {
			throw new IllegalArgumentException("La cuenta no existe");
			
		}
		Cuenta cuenta = cuentasPorCvu.get(cvu);
		double saldo = cuenta.consultarSaldo();
		return saldo;
	}

	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
		if(!cuentasPorCvu.containsKey(cvuOrigen)) {
			throw new IllegalArgumentException("La cuenta de origen no existe");	
		}
		if(!cuentasPorCvu.containsKey(cvuDestino)) {
			throw new IllegalArgumentException("La cuenta de destino no existe");
		}
		Cuenta cuenta = cuentasPorCvu.get(cvuOrigen);
		Cuenta cuenta2 = cuentasPorCvu.get(cvuDestino);
		if(cuenta.consultarSaldo() < monto) {
			throw new IllegalArgumentException("No hay suficiente saldo");		
		}
		if(cvuPorDni.get(cvuOrigen).equals(cvuPorDni.get(cvuDestino))) {
			historialGlobal.add(cuenta.transferirDinero(monto, cvuDestino, true));
			cuenta2.agregarDinero(monto);	
		} 
		else {
			historialGlobal.add(cuenta.transferirDinero(monto, cvuDestino, false));
			cuenta2.agregarDinero(monto);
			
		}
	

	}

	@Override
	public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
		if(!usuarios.containsKey(dni)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		if(!cuentasPorCvu.containsKey(cvu)) {
			throw new IllegalArgumentException("La cuenta no existe");	
		}
		LocalDateTime fechaActual = LocalDateTime.now();
		Renta_Fija inversion = new Renta_Fija(generadorIdInversion , fechaActual, plazoDias, monto, 0.10 );
		
		generadorIdInversion++;
		Cuenta cuenta = cuentasPorCvu.get(cvu);
		Actividad ticket = cuenta.invertirDinero(monto, inversion, "Apertura");
		historialGlobal.add(ticket);
		usuarios.get(dni).actualizarTotalInvertido(monto);
		return inversion.obtenerId();
	}

	@Override
	public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa,
			double tasa) {
		if(!usuarios.containsKey(dni)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		if(!cuentasPorCvu.containsKey(cvu)) {
			throw new IllegalArgumentException("La cuenta no existe");	
		}
		LocalDateTime fechaActual = LocalDateTime.now();
		Vinculada_a_Divisa inversion = new Vinculada_a_Divisa (generadorIdInversion , fechaActual, plazoDias, monto, divisa ,  tasa );
		generadorIdInversion++;
		Cuenta cuenta = cuentasPorCvu.get(cvu);
		Actividad ticket = cuenta.invertirDinero(monto, inversion, "InversionPorDivisa");
		historialGlobal.add(ticket);
		usuarios.get(dni).actualizarTotalInvertido(monto);
		return inversion.obtenerId();
	
	}

	@Override
	public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
		if(!usuarios.containsKey(dni)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		if(!cuentasPorCvu.containsKey(cvu)) {
			throw new IllegalArgumentException("La cuenta no existe");	
		}
		if(monto < 20000000) {
			throw new IllegalArgumentException("Monto minimo no alcanzado");	
		}
		Cuenta cuenta = cuentasPorCvu.get(cvu);
		if(!(cuenta instanceof Cuenta_Corporativa)) {
		    throw new IllegalArgumentException("Solo cuentas corporativas pueden invertir en fondos de liquidez");
		}
		LocalDateTime fechaActual = LocalDateTime.now();
		Fondo_de_Liquidez_Empresarial inversion = new Fondo_de_Liquidez_Empresarial (generadorIdInversion , fechaActual, plazoDias, monto);
		generadorIdInversion++;
		
		Actividad ticket = cuenta.invertirDinero(monto, inversion, "Fondo de Liquidez Empresarial");
		historialGlobal.add(ticket);
		usuarios.get(dni).actualizarTotalInvertido(monto);
		return inversion.obtenerId();
		
		
		}

	@Override
	public void precancelarInversion(String dni, String cvu, int idInversion) {
		if(!usuarios.containsKey(dni)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		if(!cuentasPorCvu.containsKey(cvu)) {
			throw new IllegalArgumentException("La cuenta no existe");	
		}
		Cuenta cuenta = cuentasPorCvu.get(cvu);
		Inversion inversion = cuenta.obtenerInversion(idInversion);
		if(inversion == null) {
		    throw new IllegalArgumentException("La inversion no existe");
		}
		inversion.ejecutarPrecancelacion();
		double monto = inversion.obtenerMontoInvertido();
		cuenta.agregarDinero(monto);
		usuarios.get(dni).actualizarTotalInvertido(-monto);
		historialGlobal.add(cuenta.registrarCancelacion(monto));
		}
		
		

	

	@Override
	public String consultarCvu(String alias) {
		if(!cvuPorAlias.containsKey(alias)) {
			throw new IllegalArgumentException("El alias no existe");	
		}
		String cvu = cvuPorAlias.get(alias);
		return cvu;
	}

	@Override
	public List<String> consultarHistorialGlobal() {
		List<String> resultado = new ArrayList<>();
		for(Actividad actividad : historialGlobal) {
		    resultado.add(actividad.obtenerDetalle());
		}
		return resultado;
	}

	@Override
	public List<String> consultarHistorialCuenta(String cvu) {
		if(!cuentasPorCvu.containsKey(cvu)) {
			throw new IllegalArgumentException("La cuenta no existe");			
		}
		Cuenta cuenta = cuentasPorCvu.get(cvu);
		return cuenta.consultarHistorial();
	}

	@Override
	public List<String> consultarHistorialUsuario(String dniUsuario) {
		if(!usuarios.containsKey(dniUsuario)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		Usuario usuario = usuarios.get(dniUsuario);
		List<String> resultado = new ArrayList<>();
		for(String cvu : usuario.obtenerCvus()) {
		    resultado.addAll(consultarHistorialCuenta(cvu));
		}
		return resultado;
	}

	@Override
	public double obtenerTotalInvertido(String dniUsuario) {
		if(!usuarios.containsKey(dniUsuario)) {
			throw new IllegalArgumentException(" El dni no se encuentra asociado a ningun usuario");
		}
		Usuario usuario = usuarios.get(dniUsuario);
		double total = usuario.obtenerTotalInvertido();
		return total;
	}

	@Override
	public List<String> cuentasConMayorVolumen(int cantidadTop) {
		List<Cuenta> cuentas = new ArrayList<>(cuentasPorCvu.values());
		cuentas.sort((a, b) -> b.obtenerContadorDeTransacciones() - a.obtenerContadorDeTransacciones());
		List<String> resultado = new ArrayList<>();
		for(int i = 0; i < cantidadTop && i < cuentas.size(); i++) {
		    resultado.add(cuentas.get(i).ConseguirElCvu());
		}
		return resultado;
	}

}
