package principal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Renta_Fija extends Inversion {
	private double tasaDeInteres;

    // Constructor
    public Renta_Fija(int idInversion, LocalDateTime fechaConstitucion, int plazo, double montoInvertido, double tasaDeInteres) {
        super(idInversion, fechaConstitucion, plazo, montoInvertido);
        this.tasaDeInteres = tasaDeInteres;

}
    @Override
   public double calcularResultado(Cuenta cuenta) {
    	long dias = ChronoUnit.DAYS.between(fechaDeConstitucion.toLocalDate(), Utilitarios.hoy());
    	return montoInvertido * (tasaDeInteres / 365) * dias;
}

  
  
}