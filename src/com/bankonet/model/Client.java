package com.bankonet.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bankonet.Compte;
import com.bankonet.Compte.TypeCompte;
import com.bankonet.CompteNonTrouveException;

public class Client implements Comparable{
	private String identifiant;
	private String nom;
	private String prenom;
	//private List<Compte> compteList = new ArrayList<Compte>();
	private Map<Integer,Compte> compteMap = new HashMap<Integer,Compte>();
	private List compteCourantList = new ArrayList();
	private List compteEpargneList = new ArrayList();
	
	// Constructeur
	public Client(String identifiant, String nom, String prenom){
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Client(String identifiant, String nom, String prenom, List compteCourantList, List compteEpargneList){
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.compteCourantList = compteCourantList;
		this.compteEpargneList = compteEpargneList;
	}

	// Getters et Setters
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

//	public List<Compte> getListComptes(){
//		return this.compteList;
//	}

	public Map<Integer,Compte> getMapComptes(){
		return this.compteMap;
	}
	
	public List getComptes(){
		List result = new ArrayList();
		result.add(this.compteCourantList);
		result.add(this.compteEpargneList);
		return result;
	}
	
	// Fonctions persos
	
	public double calculerAvoirGlobal(){
		double avoirGlobal = 0d;
		
//		for(Compte compte : compteList){
//			avoirGlobal += compte.getSolde();
//		}
		
		for(Map.Entry<Integer, Compte> entry : compteMap.entrySet()){
			avoirGlobal += entry.getValue().getSolde();
		}
		
		return avoirGlobal;
	}
	
	public String toString(){
		String report = this.nom + " " + this.prenom;
//		for(Compte compte : compteList){
//			report = report + "\n" + compte.toString();
//		}
		for(Map.Entry<Integer, Compte> entry : compteMap.entrySet()){
			report = report + "\n" + entry.getValue().toString();
		}
		report = report + "\n" + this.calculerAvoirGlobal();
		return report;
	}
	
	public void creerCompte(Compte compte){
		this.compteMap.put(compte.getIdentifiant(), compte);
	}
	
	public void supprimerCompte(Compte compte){
		this.compteMap.remove(compte.getIdentifiant(), compte);
	}
	
//	public Compte retournerCompte(int identifiant) throws CompteNonTrouveException{
//		Compte currentCompte;
//		for(int i=0; i<compteMap.size(); i++){
//			currentCompte = compteMap.get(i);
//			if(compteMap.get(i).getIdentifiant() == identifiant) return currentCompte;
//		}
//		throw new CompteNonTrouveException("Aucun compte ayant pour identifiant : \"" + identifiant + "\" n'a été trouvé !");
//	}
	
	public void bCompteExiste(int identifiant) throws CompteNonTrouveException{
		if(!(compteMap.get(identifiant) != null)) throw new CompteNonTrouveException("Ce compte moisi n'existe pas");
	}
	
	public void supprimerCompte(int identifiant){
		try{
			//Compte compteASupprimer = retournerCompte(identifiant);
			bCompteExiste(identifiant);
			this.compteMap.remove(identifiant);
		}catch(CompteNonTrouveException e){
			e.printStackTrace();
			e.getMessage();
		}
		
	}
	
	private int getNombreComptes(TypeCompte type){
		int compteur = 0;
		for(Map.Entry<Integer, Compte> entry : compteMap.entrySet()){
			if(entry.getValue().getTypeCompte().equals(type.getTypeCompte())) compteur++;
		}		
		return compteur;
	}
	
	public int getNombreComptesCourants(){
		return getNombreComptes(TypeCompte.COURANT);
	}
	
	public int getNombreComptesEpargnes(){
		return getNombreComptes(TypeCompte.EPARGNE);
	}

	@Override
	public int compareTo(Object aClient) throws ClassCastException{
		if(!(aClient instanceof Client)) throw new ClassCastException();
		else{
			Client attrClient = (Client) aClient;
			return attrClient.getIdentifiant().compareTo(this.getIdentifiant());
		}
	}
}
