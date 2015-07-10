package com.bankonet.test;

import com.bankonet.Compte;
import com.bankonet.model.Client;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class TestClient {
	
	public static void main(String[] args){
		
		Client[] clientTab = new Client[3];
		
		CompteCourant compteCourant = new CompteCourant(1, "Compte Courant Test", 20000f, 1000f);
		CompteEpargne compteEpargne = new CompteEpargne(11, "Compte Epargne Test", 100f, 1.0d, 10000f);
		
		CompteCourant compteCourant2 = new CompteCourant(2, "Compte Courant Test 2", 2000f, 2000f);
		CompteEpargne compteEpargne2 = new CompteEpargne(22, "Compte Epargne Test 2", 200f, 2.0d, 5000f);
		
		CompteCourant compteCourant3 = new CompteCourant(3, "Compte Courant Test 3", 3000f, 0f);

		clientTab[0] = new Client("1", "Pato", "Beurre");
		clientTab[0].creerCompte(compteCourant);
		clientTab[0].creerCompte(compteEpargne);
		clientTab[0].creerCompte(compteCourant2);
		clientTab[1] = new Client("2", "Tomate", "Moza");
		clientTab[1].creerCompte(compteCourant2);
		clientTab[1].creerCompte(compteEpargne2);
		clientTab[2] = new Client("3", "Galette", "Saucisse");
		clientTab[2].creerCompte(compteCourant3);
		
//		for(Client client : clientTab){
//			System.out.println(client.toString());
//		}
//		System.out.println("Depart");
//		System.out.println(clientTab[0].toString());
//		System.out.println("Suppression du compte courant");
//		clientTab[0].supprimerCompte(compteCourant);
//		System.out.println(clientTab[0].toString());
//		System.out.println("Création du compte courant");
//		clientTab[0].creerCompte(compteCourant);
//		System.out.println(clientTab[0].toString());
//		System.out.println("Suppression du compte 2 (qui n'existe pas dans cette liste)");
//		clientTab[0].supprimerCompte(2);
//		System.out.println("Suppression du compte 11 (compte epargne)");
//		clientTab[0].supprimerCompte(11);
//		System.out.println(clientTab[0].toString());
//		clientTab[0].creerCompte(compteEpargne);
//		clientTab[0].creerCompte(compteEpargne);
//		clientTab[0].creerCompte(compteEpargne);
//		System.out.println(clientTab[0].toString());
//		System.out.println("Suppression du premier compte 11 (compte epargne)");
//		clientTab[0].supprimerCompte(11);
//		System.out.println(clientTab[0].toString());
//		System.out.println("Suppression des autres comptes épargnes");
//		clientTab[0].supprimerCompte(compteEpargne);
//		clientTab[0].supprimerCompte(compteEpargne);
//		System.out.println(clientTab[0].toString());
		
		System.out.println("Nombre de comptes courants : " + clientTab[0].getNombreComptesCourants());
		System.out.println("Nombre de comptes épargnes : " + clientTab[0].getNombreComptesEpargnes());
		
	}
}
