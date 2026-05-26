package principal;

import java.time.LocalDateTime;

public class Vinculada_a_Divisa extends Inversion {
	// Datos
    private String divisaReferente;
    private double interesPorDivisa;

    // Constructor
    public Vinculada_a_Divisa(int idInversion, LocalDateTime fechaConstitucion, int plazo, double montoInvertido, String divisaReferente, double interesPorDivisa) {
        super(idInversion, fechaConstitucion, plazo, montoInvertido);
        this.divisaReferente = divisaReferente;
        this.interesPorDivisa = interesPorDivisa;
    }

}
