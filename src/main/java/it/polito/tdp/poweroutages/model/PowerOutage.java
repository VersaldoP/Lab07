package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PowerOutage {
	private int id ;
	private LocalDateTime data_inizio;
	private LocalDateTime data_fine;
	private int customers_affected;
	private int differenza ;//differenza tra inizio e fine ;
	public PowerOutage(int id, LocalDateTime data_inizio, LocalDateTime data_fine, int customers_affected) {
		super();
		this.id = id;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.customers_affected = customers_affected;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getData_inizio() {
		return data_inizio;
	}
	public void setData_inizio(LocalDateTime data_inizio) {
		this.data_inizio = data_inizio;
	}
	public LocalDateTime getData_fine() {
		return data_fine;
	}
	public void setData_fine(LocalDateTime data_fine) {
		this.data_fine = data_fine;
	}
	public int getCustomers_affected() {
		return customers_affected;
	}
	public void setCustomers_affected(int customers_affected) {
		this.customers_affected = customers_affected;
	}
	public int getDifferenza() {
		return differenza;
	}
	public void setDifferenza(int differenza) {
		this.differenza = differenza;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}
	

}
