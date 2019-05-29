package it.polito.tdp.model;

import java.time.Duration;
import java.time.LocalTime;

public class Evento implements Comparable<Evento>{
	
	private int oraArrivo ; // timestamp dell'evento
	private TipoEvento tipo ; // tipologia
	private Gruppo gruppo;
	
	public enum TipoEvento{
		ARRIVO_GRUPPO_CLIENTI,
		USCITA_GRUPPO_CLIENTI,
	}


	public Evento(int oraArrivo, TipoEvento tipo, Gruppo gruppo) {
		super();
		this.oraArrivo = oraArrivo;
		this.tipo = tipo;
		this.gruppo = gruppo;
	}


	public int getOraArrivo() {
		return oraArrivo;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public Gruppo getGruppo() {
		return gruppo;
	}

	@Override
	public int compareTo(Evento other) {
		return this.oraArrivo-(other.oraArrivo);
	}

	
}
