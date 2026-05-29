package principal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Fondo_de_Liquidez_Empresarial extends Inversion {
	public Fondo_de_Liquidez_Empresarial(int idInversion, LocalDateTime fechaConstitucion, int plazo, double montoInvertido) {
        super(idInversion, fechaConstitucion, plazo, montoInvertido);
        this.esPrecancelable = false;

}

	@Override
	public double calcularResultado(Cuenta cuenta) {
		long dias = ChronoUnit.DAYS.between(fechaDeConstitucion.toLocalDate(), Utilitarios.hoy());
	    return montoInvertido * (0.08 / 365) * dias;
	}
}
