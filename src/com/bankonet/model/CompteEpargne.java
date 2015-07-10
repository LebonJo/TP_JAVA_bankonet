package com.bankonet.model;

import com.bankonet.Compte;
import com.bankonet.CreditException;
import com.bankonet.DebitException;

public class CompteEpargne extends Compte{
	
	private double tauxInteret;
	private float plafond;
	
	public CompteEpargne(int identifiant, String libelle, float solde, double tauxInteret, float plafond){
		super(identifiant, libelle, solde);
		this.tauxInteret = tauxInteret;
		this.plafond = plafond;
	}
	
	// Getters et Setters
	public double getTauxInteret() {
		return this.tauxInteret;
	}
	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

	public float getPlafond() {
		return this.plafond;
	}
	public void setPlafond(float plafond) {
		this.plafond = plafond;
	}

	public String toString(){
		String report = super.toString();
		report = report + " PLAFOND : " + this.plafond + "€. TAUX D'INTERETS : " + this.tauxInteret + "%.";
		return report;
	}
	
	public boolean debitAutorise(float montant) throws DebitException{
		boolean debitAutorise = (montant < this.solde);
		if(!debitAutorise) throw new DebitException("Le débit d'un montant de " + montant + "€ sur le compte \"" + this.getLibelle() + "\" n'a pas été autorisé");
		else super.debiter(montant);
		return debitAutorise;
	}
	
	public boolean creditAutorise(float montant) throws CreditException{
		boolean creditAutorise = (this.solde + montant < this.plafond);
		if(!creditAutorise)  throw new CreditException("Le crédit d'un montant de " + montant + "€ sur le compte \"" + this.getLibelle() + "\" n'a pas été autorisé");
		else super.crediter(montant);
		return creditAutorise;
	}
	
	public String getTypeCompte(){
		return TypeCompte.EPARGNE.getTypeCompte();
	}
}
