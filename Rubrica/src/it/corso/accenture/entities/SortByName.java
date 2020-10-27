package it.corso.accenture.entities;

import java.util.*;

public class SortByName implements Comparator<Contatto>{

	@Override
	public int compare(Contatto o1, Contatto o2) {
		return o1.getNome().compareTo(o2.getNome());
	}
	
}
