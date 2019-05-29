package it.polito.tdp.model;

public class Gruppo {
	
	private int numeroPersone;

	private int durataPermanenza;
	private float tolleranza;

	private int oraArrivo;
	Tavolo tavolo;
	

	public Gruppo(int numeroPersone, float tolleranza, int oraArrivo, int durataPermanenza) {
		super();
		this.numeroPersone = numeroPersone;
		this.tolleranza = tolleranza;
		this.oraArrivo = oraArrivo;
		this.durataPermanenza= durataPermanenza;
		tavolo = null;
	}



	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}



	public Tavolo getTavolo() {
		return tavolo;
	}



	public int getOraArrivo() {
		return oraArrivo;
	}



	public int getDurataPermanenza() {
		return durataPermanenza;
	}



	public void setDurataPermanenza(int durataPermanenza) {
		this.durataPermanenza = durataPermanenza;
	}



	public float getTolleranza() {
		return tolleranza;
	}



	public int getNumeroPersone() {
		return numeroPersone;
	}



	public void incrementaNumeroPersone(int numeroPersone) {
		this.numeroPersone += numeroPersone;
	}



	public void incrementaTolleranza(float tolleranza) {
		this.tolleranza += tolleranza;
	}

}
