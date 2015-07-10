package com.bankonet.model;

import com.bankonet.Compte;
import com.bankonet.DebitException;

public class CompteCourant extends Compte{
	private float decouvertAutorise;
	
	public CompteCourant(int identifiant, String lib, float solde, float decouvertAutorise){
		super(identifiant, lib, solde);
		this.decouvertAutorise = decouvertAutorise;
	}
	
	public String toString(){
		String report = super.toString();
		if(this.decouvertAutorise > 0){
			report = report + " Autorisation de découvert : " + this.decouvertAutorise + "€.";
		}else{
			report = report + " Pas d'autorisation de découvert.";
		}
		return report;
	}
	
	public boolean debitAutorise(float montant) throws DebitException{
		boolean debitAutorise = (montant < (this.solde + this.decouvertAutorise));
		if(!debitAutorise) throw new DebitException("Le débit d'un montant de " + montant + "€ sur le compte \"" + this.getLibelle() + "\" n'a pas été autorisé");
		else super.debiter(montant);
		return debitAutorise;
	}
	
	public boolean creditAutorise(float montant){
		super.crediter(montant);
		return true;
	}
	
	public String getTypeCompte(){
		return TypeCompte.COURANT.getTypeCompte();
	}

	public float getDecouvertAutorise() {
		return this.decouvertAutorise;
	}

}
