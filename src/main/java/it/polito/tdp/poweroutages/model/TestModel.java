package it.polito.tdp.poweroutages.model;

import java.util.LinkedList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
//		Nerc nerc = new Nerc(2,"HECO");
//		List<PowerOutage>poList= new LinkedList<>( model.getListEvent(nerc,200,2));
//		System.out.println(poList);
	}

}
