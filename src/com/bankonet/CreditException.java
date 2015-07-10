package com.bankonet;

public class CreditException extends Exception{

	private String message;
	
	public CreditException(){
		this.message = "Crédit non autorisé !";
	}
	
	public CreditException(String msg){
		this.message = msg;
	}
	
	public String getMessage(){
		return this.message;
	}
}
