package com.bankonet;

public class DebitException extends Exception{
	
	String message;
	
	public DebitException(){
		message = "Une exception de type DebitException a �t� lanc�e !";
	}
	
	public DebitException(String msg){
		this.message = msg;
	}
	
	public String getMessage(){
		//System.out.println(this.message);
		return this.message;
	}
	
}
