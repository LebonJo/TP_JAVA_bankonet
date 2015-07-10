package com.bankonet.test;

import java.util.ArrayList;
import java.util.List;

import com.bankonet.Compte;
import com.bankonet.CompteStat;
import com.bankonet.model.Client;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class DonneesTest {

	public static CompteStat[] construitEchantillonComptes() {
		
		CompteStat[] tabComptes = new CompteStat[5];
		tabComptes[0] = new CompteCourant(1, "compte courant 1", 0f, 1000f);
		tabComptes[1] = new CompteCourant(2, "compte courant 2", 500f, 200f);
		tabComptes[2] = new CompteCourant(3, "compte courant 3", 20f, 0f);
		tabComptes[3] = new CompteEpargne(4, "compte épargne 4", 1, 2.54d, 1000f);
		tabComptes[4] = new CompteEpargne(5, "compte épargne 5", 1000000,13.42d, 2000f);

		return tabComptes;

	}
	
	public static List<Client> construireEchantillonClients(){
		
		List<Client> clients = new ArrayList<Client>();
		
		Compte compte1 = new CompteCourant(1, "compte courant 1", 0f, 1000f);
		Compte compte2 = new CompteCourant(2, "compte courant 2", 500f, 200f);
		Compte compte3 = new CompteCourant(3, "compte courant 3", 20f, 0f);
		Compte compte4 = new CompteEpargne(4, "compte épargne 4", 1, 2.54d, 1000f);
		Compte compte5 = new CompteEpargne(5, "compte épargne 5", 1000000,13.42d, 2000f);
		
		List listCompteCourant1 = new ArrayList();
		listCompteCourant1.add(compte1);
		listCompteCourant1.add(compte2);
		
		List listCompteCourant2 = new ArrayList();
		listCompteCourant2.add(compte3);
		
		List listCompteEpargne1 = new ArrayList();
		listCompteEpargne1.add(compte4);
		listCompteEpargne1.add(compte5);
		
		List listCompteEpargne2 = new ArrayList();
		listCompteEpargne2.add(compte5);
		
		clients.add(new Client("1234", "Jambon", "Beurre", listCompteCourant1, listCompteEpargne1));
		clients.add(new Client("5678", "Patrick", "Bosso", listCompteCourant2, listCompteEpargne2));
		
		return clients;
	}

}
