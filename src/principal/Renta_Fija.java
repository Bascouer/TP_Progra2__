package principal;

import java.time.LocalDateTime;

public class Renta_Fija extends Inversion {
	private double tasaDeInteres;

    // Constructor
    public Renta_Fija(int idInversion, LocalDateTime fechaConstitucion, int plazo, double montoInvertido, double tasaDeInteres) {
        super(idInversion, fechaConstitucion, plazo, montoInvertido);
        this.tasaDeInteres = tasaDeInteres;

}
    @Override
   public double calcularResultado(Cuenta cuenta) {
        return super.montoInvertido * tasaDeInteres * cuenta.obtenerFactorDeCalculo(); //Creo que calcularia de esta manera porque la anterior seria un valor base y ese es el variable de los intereses
}

  
  
}