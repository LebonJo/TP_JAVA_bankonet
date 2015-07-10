package com.bankonet;

import java.util.Comparator;

import com.bankonet.model.Client;

public class ClientComparator implements Comparator{

	@Override
	public int compare(Object o1, Object o2) throws ClassCastException{

		if(!((o1 instanceof Client)&&(o2 instanceof Client))) throw new ClassCastException();
		else{
			Client client1 = (Client) o1;
			Client client2 = (Client) o2;
			
			int resultat = client1.getNom().toUpperCase().compareTo(client2.getNom().toUpperCase());
			if(resultat == 0) return client1.getPrenom().toUpperCase().compareTo(client2.getPrenom().toUpperCase());
			else return resultat;
		}
	}

}
