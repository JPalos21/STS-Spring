package goya.daw2.miprimerformulario;

import jakarta.validation.constraints.NotNull;

public class Persona {
	
	@NotNull
	private double peso;
	
	@NotNull
	private double cintura;
	
	private double indice;
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public double getCintura() {
		return cintura;
	}
	
	public void setCintura(double cintura) {
		this.cintura = cintura;
	}
	
	public double getIndice() {
		return indice;
	}
	
	public void setIndice(double indice) {
		this.indice = ((peso -(((peso * 0.85)+28)-(cintura * 0.35))) * 100)/peso;;
	}
	
	public String toString() {
		return "Persona(Peso: " + this.peso + ", Cintura: " + this.cintura + ", IGC: " + this.indice;
	}
	
}
