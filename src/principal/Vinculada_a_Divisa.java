package principal;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Vinculada_a_Divisa extends Inversion {
	// Datos
    private String divisaReferente;
    private double interesPorDivisa;
    private double cotizacionOriginal;
    // Constructor
    public Vinculada_a_Divisa(int idInversion, LocalDateTime fechaConstitucion, int plazo, double montoInvertido, String divisaReferente, double interesPorDivisa) {
        super(idInversion, fechaConstitucion, plazo, montoInvertido);
        this.divisaReferente = divisaReferente;
        this.interesPorDivisa = interesPorDivisa;
        this.cotizacionOriginal = Utilitarios.consultarCotizacion(divisaReferente);
    }


    @Override
    public double calcularResultado(Cuenta cuenta) {
        double divisasEquivalente = montoInvertido / cotizacionOriginal;
        long dias = ChronoUnit.DAYS.between(fechaDeConstitucion.toLocalDate(), Utilitarios.hoy());
        double interesesEnDivisas = divisasEquivalente * (interesPorDivisa / 365) * dias;
        return interesesEnDivisas * Utilitarios.consultarCotizacion(divisaReferente);
    }

    public double calcularMontoFinal(Cuenta cuenta) {
    	double divisasEquivalente = montoInvertido / cotizacionOriginal;
    	long dias = ChronoUnit.DAYS.between(fechaDeConstitucion.toLocalDate(), Utilitarios.hoy());
    	double interesesEnDivisas = divisasEquivalente * (interesPorDivisa / 365) * dias;
    	return (divisasEquivalente + interesesEnDivisas / 2) * Utilitarios.consultarCotizacion(divisaReferente);
    }
     
}
