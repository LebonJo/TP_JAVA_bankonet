package com.bankonet;

public abstract class Compte implements CompteStat{
	private int identifiant;
	private String libelle;
	protected float solde;
	
	public enum TypeCompte {
		COURANT("courant"), 
		EPARGNE("épargne");
		
		private final String typeCompte;
		
		TypeCompte(String typeCompte) {
			this.typeCompte = typeCompte;
		}
		
		public String getTypeCompte(){
			return this.typeCompte;
		}
	}
	
	public Compte(int identifiant, String libelle, float solde){
		this.identifiant = identifiant;
		this.libelle = libelle;
		if(solde < 0f){
			System.out.println("On ne peux pas créer un compte avec un solde négatif !");
			this.solde = 0f;
		}else{
			this.solde = solde;
		}
	}

	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public float getSolde() {
		return solde;
	}
	
	public String toString(){
		return "COMPTE " + this.getTypeCompte() + " N°" + this.identifiant + ". LIBELLE : \"" + this.libelle + "\". SOLDE : " + this.solde + "€.";
	}
	
	public void debiter(float montant){
		this.solde = this.solde - montant;
		System.out.println("Debit de " + montant + "€ sur le compte " + libelle + " effectué");
	}
	
	public void crediter(float montant){
		this.solde = this.solde + montant;
		System.out.println("Credit de " + montant + "€ sur le compte " + libelle + " effectué");
	}
	
	protected abstract boolean debitAutorise(float montant) throws DebitException;
	
	protected abstract boolean creditAutorise(float montant) throws CreditException;
	
	public abstract String getTypeCompte();
	
	public void effectuerVirement(Compte compte, float montant){
		try{
			debitAutorise(montant);
			compte.creditAutorise(montant);
		}catch(DebitException d){
			d.printStackTrace();
			d.getMessage();
		}catch(CreditException c){
			c.printStackTrace();
			c.getMessage();
			try{
				creditAutorise(montant);
			}catch(CreditException e){
				e.printStackTrace();
				e.getMessage();
			}
		}
	}
}
