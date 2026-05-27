package principal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Billetera implements IBilletera {
	private HashMap<String, Usuario> usuarios;
    private HashMap<String, Empresa> empresas; 
    private HashMap<String, Cuenta> cuentasPorCvu;
    private HashMap<String, String> cvuPorAlias;
    private ArrayList<Actividad> historialGlobal;
    
    private int generadorCvu;
    private int generadorIdInversion;

	public Billetera() {
		this.usuarios = new HashMap<>();
        this.empresas = new HashMap<>();
        this.cuentasPorCvu = new HashMap<>();
        this.cvuPorAlias = new HashMap<>();
        this.historialGlobal = new ArrayList<>();
        
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
		return cvu;

		
	}

	@Override
	public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> obtenerCuentas(String dniUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double obtenerSaldoDisponible(String cvu) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
		// TODO Auto-generated method stub

	}

	@Override
	public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa,
			double tasa) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void precancelarInversion(String dni, String cvu, int idInversion) {
		// TODO Auto-generated method stub

	}

	@Override
	public String consultarCvu(String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultarHistorialGlobal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultarHistorialCuenta(String cvu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> consultarHistorialUsuario(String dniUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double obtenerTotalInvertido(String dniUsuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> cuentasConMayorVolumen(int cantidadTop) {
		// TODO Auto-generated method stub
		return null;
	}

}
