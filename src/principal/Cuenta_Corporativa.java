package principal;

public class Cuenta_Corporativa extends Cuenta {
	private String cuitEmpresa;

    // Constructor
    public Cuenta_Corporativa(String cvu, String alias, String cuitEmpresa) {
        super(cvu, alias);
        this.cuitEmpresa = cuitEmpresa;
    }

}
