package com.bankonet;

public class CompteNonTrouveException extends Exception{

	String message;
	
	public CompteNonTrouveException(){
		this.message = "Compte non trouv� !";
	}
	
	public CompteNonTrouveException(String msg){
		this.message = msg;
	}
	
}
