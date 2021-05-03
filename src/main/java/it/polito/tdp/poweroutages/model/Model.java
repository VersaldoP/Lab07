package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO dao;
	private List<PowerOutage> parziale;
	List<PowerOutage> finale;
	private List<PowerOutage>poList;
	private int maxOre;
	private int maxAnni;
	public int maxPersone;
	public double oreDisservizio;
	
	
	public Model() {
		dao = new PowerOutageDAO();
		maxOre=0;
		maxAnni=0;
		maxPersone=0;
		oreDisservizio=0;
		
		
	}
	
	public List<Nerc> getNercList() {
		return dao.getNercList();
	}
	
	public List<PowerOutage> getListEvent(Nerc nerc,int maxOre,int maxAnni){
		this.maxOre=maxOre;
		this.maxAnni=maxAnni;
		
		poList = new LinkedList<PowerOutage>(dao.getListPO(nerc,maxOre));
		
		parziale = new LinkedList<>();
		finale = new LinkedList<>();
	
		int liv =0;
		int numPersone =0;
		double totOreD=0;
		ricercaRicorsivo(parziale,liv,totOreD,numPersone);
		
		
		
//		StringBuilder result = null;
//		result.append("Numero di persone colpite: "+this.maxPersone +"\n" +"Totale ore di disservizio: " +(int)this.oreDisservizio +"\n");
//		for(PowerOutage p:finale)
//		result.append(p);
		
		return finale ;
	}

	private void ricercaRicorsivo(List<PowerOutage> parziale, int liv, double totOreD, int numPersone) {
		// Calcolo se la soluzione parziale è ancora valida o è uscita dal range anni e ore
		if(totOreD>this.maxOre||calcolaAnni(parziale)>this.maxAnni)
			return;
		// Controllo che la parziale sia la ottima o meno 
		if(totOreD<=this.maxOre&&calcolaAnni(parziale)<=this.maxAnni&&numPersone>maxPersone) {
			maxPersone=numPersone;
			oreDisservizio = totOreD;
			finale = new LinkedList<>(parziale);
		}
		//Condizione 0 o caso terminale 
		if(liv>=poList.size())
			return;
		
		//Costruisco la soluzione parziale inserendo il prossimo PO e non inserendolo se inserendo il PO genera una sol invalida sarà il primo if ad escluderla
		LinkedList newParziale = new LinkedList<PowerOutage>(parziale);
		PowerOutage newPO= poList.get(liv);
		int numPersonenew = numPersone+newPO.getCustomers_affected();
		double totOreDnew = totOreD+newPO.getDifferenza();
		//lo inserisco 
		ricercaRicorsivo(newParziale,liv++,totOreDnew,numPersonenew);
		//non lo inserisco
		ricercaRicorsivo(parziale,liv++,totOreD,numPersone);
	}

	private int calcolaAnni(List<PowerOutage> parziale) {
		int annoMax=0;
		int annoMin=3000;
		
		if(parziale.isEmpty())
			return -1;
		
		for(PowerOutage p : parziale) {
			if(p.getData_fine().getYear()>annoMax)
				annoMax = p.getData_fine().getYear();
			if(p.getData_inizio().getYear()<annoMin)
				annoMin = p.getData_inizio().getYear();
		}
		
		return (annoMax-annoMin);
	}
	

}
