package principal;

public class Cuenta_Premium extends Cuenta {

	public Cuenta_Premium(String cvu, String alias, double depositoInicial) {
		super(cvu, alias);
		agregarDinero(depositoInicial);
	}

	
	 @Override 
	  public double obtenerFactorDeCalculo() {
	        return 1.0;
	    }
	  @Override 
	  public String obtenerTipo() {
			return "Cuenta Premium";
		}



}
