package es.marialamo.jspejemp.AdivinarNumero;
import java.util.*;

public class BeanAdivinarNumero {

    int respuesta;
    boolean acertado;
    String pista;
    int numeroIntentos;

    public BeanAdivinarNumero() {
        volverAEmpezar();
    }

    public void pruebaNumero(String prueba) {
	numeroIntentos++;
	int numeroAProbar;
	try {
            numeroAProbar = Integer.parseInt(prueba);
	}
	catch (NumberFormatException e) {
            numeroAProbar = -1;
	}
	if (numeroAProbar == respuesta) {
            acertado = true;
	}
	else if (numeroAProbar == -1) {
            pista = "auténtico la próxima vez";
	}
	else if (numeroAProbar < respuesta) {
            pista = "mayor";
	}
	else if (numeroAProbar > respuesta) {
            pista = "menor";
	}
    }

    public boolean hasAcertado() {
	return acertado;
    }

    public String devuelvePista() {
	return "" + pista;
    }

    public int devuelveNumeroIntentos() {
        return numeroIntentos;
    }

    public void volverAEmpezar() {
        respuesta = Math.abs(new Random().nextInt() % 100) + 1;
        acertado = false;
        numeroIntentos = 0;
    }
}
