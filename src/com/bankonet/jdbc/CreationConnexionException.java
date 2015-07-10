package com.bankonet.jdbc;

public class CreationConnexionException extends Exception{

	private String message;
	
	CreationConnexionException(String msg){
		this.message = msg;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
