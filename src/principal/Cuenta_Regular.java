package principal;

public class Cuenta_Regular extends Cuenta {

	public Cuenta_Regular(String cvu, String alias) {
		super(cvu, alias);
	
	}
	  public boolean validarReglas() {
	        if (this.saldo >= 5000000) {
	        	throw new IllegalArgumentException("El saldo sobrepasa el limite permitido");
	        }
			return true;
	    }
	  @Override 
	  public double obtenerFactorDeCalculo() {
	        return 1.0;
	    }

}
