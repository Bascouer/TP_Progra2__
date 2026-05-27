package principal;

public class Cuenta_Premium extends Cuenta {

	public Cuenta_Premium(String cvu, String alias, double depositoInicial) {
		super(cvu, alias);
		agregarDinero(depositoInicial);
	}
	public boolean validarReglas() {
        if (this.saldo < 500000) {
        	throw new IllegalArgumentException("El saldo es menor al requerido");
        }
		return true;
    }
	
	 @Override 
	  public double obtenerFactorDeCalculo() {
	        return 1.0;
	    }


}
