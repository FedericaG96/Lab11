package it.polito.tdp.model;

public class Tavolo {
	
	private int numeroPosti;
	private boolean disponibile = true;

	public Tavolo(int numeroPosti) {
		super();
		this.numeroPosti = numeroPosti;
	}

	public int getNumeroPosti() {
		return numeroPosti;
	}

	public void setNumeroPosti(int numeroPosti) {
		this.numeroPosti = numeroPosti;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

}
