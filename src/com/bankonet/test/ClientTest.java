package com.bankonet.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bankonet.ClientComparator;
import com.bankonet.model.Client;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class ClientTest {
	
	public static void main(String[] args){
		
		List<Client> clientList = new ArrayList<Client>();
		
		CompteCourant compteCourant = new CompteCourant(1, "Compte Courant Test", 20000f, 1000f);
		CompteEpargne compteEpargne = new CompteEpargne(11, "Compte Epargne Test", 100f, 1.0d, 10000f);
		
		CompteCourant compteCourant2 = new CompteCourant(2, "Compte Courant Test 2", 2000f, 2000f);
		CompteEpargne compteEpargne2 = new CompteEpargne(22, "Compte Epargne Test 2", 200f, 2.0d, 5000f);
		
		CompteCourant compteCourant3 = new CompteCourant(3, "Compte Courant Test 3", 3000f, 0f);

		Client client1 = new Client("1", "Pato", "Beurre");
		client1.creerCompte(compteCourant2);
		client1.creerCompte(compteEpargne);
		client1.creerCompte(compteCourant);
		Client client2 = new Client("2", "Tomate", "Moza");
		client2.creerCompte(compteEpargne2);
		client2.creerCompte(compteCourant2);
		Client client3 = new Client("3", "Galette", "Saucisse");
		client3.creerCompte(compteCourant3);

		clientList.add(client2);
		clientList.add(client3);
		clientList.add(client1);
		
		Collections.sort(clientList, new ClientComparator());
		for(Client client : clientList){
			System.out.println(client.toString());
		}
		
	}
}
