package it.polito.tdp.model;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.model.Evento.TipoEvento;

public class Simulatore {

	private int NUM_EVENTI = 2000;

	//Coda degli eventi
	private PriorityQueue<Evento> queue = new PriorityQueue<Evento>();
	
	//Stato del mondo
	private List<Tavolo> tavoli;
	private List<Gruppo> gruppi;
	
	//Parametri di simulazione
	private int NUM_TAVOLI = 15;
	private int TAVOLI_2 = 10;
	private int TAVOLI_4 = 8;
	private int TAVOLI_4b = 6;
	private int TAVOLI_5 = 4;
	private int oraInizio ;
	
	
	//Statistiche da calcolare
	Statistiche stat;
	
	//Variabili interne
	private Random rand = new Random(42);
	
	public void init() {
		
		this.queue.clear();
		oraInizio = 0;
		tavoli = new LinkedList<Tavolo>();
		gruppi = new LinkedList<Gruppo>();
		
		for(int i = 0; i<=NUM_TAVOLI; i++) {
			Tavolo tav = null;
			if(i<2) {
				 tav = new Tavolo(TAVOLI_2 );
			} else if (i>=2 && i<6) {
				 tav = new Tavolo(TAVOLI_4);
			}else if (i>=6 && i<10) {
				tav = new Tavolo(TAVOLI_4b);
			}else if (i>=10 ) {
				tav = new Tavolo(TAVOLI_5);
			}
		}
		
		stat = new Statistiche();
		
		for(int i=0; i<=NUM_EVENTI; i++) {
			Gruppo g = new Gruppo(1+rand.nextInt(10),rand.nextFloat(), oraInizio, 60 + rand.nextInt(61));
			queue.add(new Evento(oraInizio, TipoEvento.ARRIVO_GRUPPO_CLIENTI,g));
			oraInizio = 1 + rand.nextInt(10);
		}
	}
	
	public void run () {
		
		while(!queue.isEmpty()) {
			Evento ev = queue.poll();
			
			
			switch(ev.getTipo()) {
			
			case ARRIVO_GRUPPO_CLIENTI:
				
				//Registro numero clienti
				stat.setNumero_totale_clienti(ev.getGruppo().getNumeroPersone());
				Tavolo tavoloTemp = null;
				int min = 11;
				for(Tavolo t : tavoli) {
					if(t.isDisponibile()) {
						if(ev.getGruppo().getNumeroPersone()>=t.getNumeroPosti()*0.5) {
							//Se assegno tavolo incremento numero clienti soddisfatti
							if(t.getNumeroPosti()<min) {
								tavoloTemp = t;
								min = t.getNumeroPosti();
							}
						}
					}
				}
				
				if(tavoloTemp != null) {
					stat.setNumero_clienti_soddisfatti(ev.getGruppo().getNumeroPersone());
					tavoloTemp.setDisponibile(false);
					ev.getGruppo().setTavolo(tavoloTemp);
					queue.add(new Evento(ev.getGruppo().getOraArrivo()+ev.getGruppo().getDurataPermanenza(), TipoEvento.USCITA_GRUPPO_CLIENTI, ev.getGruppo()));
					break;
				}
				
				//Assegno bancone e controllo tolleranza
				if(ev.getGruppo().getTolleranza()<= rand.nextFloat()) {
					stat.setNumero_clienti_soddisfatti(ev.getGruppo().getNumeroPersone());
					queue.add(new Evento(ev.getGruppo().getOraArrivo()+ev.getGruppo().getDurataPermanenza(), TipoEvento.USCITA_GRUPPO_CLIENTI, ev.getGruppo()));
				} else {
					queue.add(new Evento(ev.getGruppo().getOraArrivo(), TipoEvento.USCITA_GRUPPO_CLIENTI, ev.getGruppo()));
					stat.setNumero_clienti_insoddisfatti(ev.getGruppo().getNumeroPersone());
				}
				
				break;
			
			case USCITA_GRUPPO_CLIENTI:
				
				if(ev.getGruppo().getTavolo()!=null) {
					ev.getGruppo().getTavolo().setDisponibile(true);
					
				}
				
				break;
			}
		}
		
	}

	public String getStatistiche() {
		return stat.toString();
	}
	
}
