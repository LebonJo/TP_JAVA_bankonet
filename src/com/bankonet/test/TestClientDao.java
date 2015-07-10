package com.bankonet.test;

import java.util.ArrayList;
import java.util.List;

import com.bankonet.jdbc.ClientDao;
import com.bankonet.jdbc.CreationConnexionException;
import com.bankonet.model.Client;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class TestClientDao {
	
	public static void main(String[] args){
		try {
			ClientDao clientDao = new ClientDao();

			
			Client newClient = new Client("4", "Jambon", "Ninja");
			CompteCourant newCompteCourant = new CompteCourant(1337, "courant test", 1337f, 1337f);
			CompteEpargne newCompteEpargne = new CompteEpargne(2337, "epargne test", 1337f, 3.3f, 3000f);
			newClient.creerCompte(newCompteCourant);
			newClient.creerCompte(newCompteEpargne);
			

			List<Client> listClients = clientDao.lireClients();

//			System.out.println("ECRITURE DU NOUVEAU CLIENT");
//			System.out.println("==========================");
//
//			clientDao.ecrireClient(newClient);
//			
//			System.out.println("LECTURE DES CLIENTS");
//			System.out.println("===================");
//
//			for(Client client : listClients){
//				System.out.println(
//						"ID : " + client.getIdentifiant() +
//						", NOM : " + client.getNom() +
//						", PRENOM : " + client.getPrenom());
//				List<CompteCourant> listComptesCourants = clientDao.lireComptesCourants(Integer.valueOf(client.getIdentifiant()));
//				for(CompteCourant compteCourant : listComptesCourants){
//					System.out.println(compteCourant.toString());
//				}
//				List<CompteEpargne> listComptesEpargnes = clientDao.lireComptesEpargnes(Integer.valueOf(client.getIdentifiant()));
//				for(CompteEpargne compteEpargne : listComptesEpargnes){
//					System.out.println(compteEpargne.toString());
//				}
//			}
			
			System.out.println("SUPPRESSION DU NOUVEAU CLIENT");
			System.out.println("=============================");
			
			clientDao.supprimerClientEtComptes(newClient);
			
			System.out.println("LECTURE DES CLIENTS");
			System.out.println("===================");
			
			for(Client client : listClients){
				System.out.println(
						"ID : " + client.getIdentifiant() +
						", NOM : " + client.getNom() +
						", PRENOM : " + client.getPrenom());
				List<CompteCourant> listComptesCourants = clientDao.lireComptesCourants(Integer.valueOf(client.getIdentifiant()));
				for(CompteCourant compteCourant : listComptesCourants){
					System.out.println(compteCourant.toString());
				}
				List<CompteEpargne> listComptesEpargnes = clientDao.lireComptesEpargnes(Integer.valueOf(client.getIdentifiant()));
				for(CompteEpargne compteEpargne : listComptesEpargnes){
					System.out.println(compteEpargne.toString());
				}
			}
		} catch (CreationConnexionException e) {
			System.out.println("LA");
			e.printStackTrace();
		}
	}

}
