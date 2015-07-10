package com.bankonet.test;

import com.bankonet.CompteStat;

public class TestAutomate {

	public static void main(String[] args){
		
		DonneesTest test = new DonneesTest();
		CompteStat[] data = test.construitEchantillonComptes();
		
		float soldeTotal = 0f;
		for(CompteStat compte : data){
			soldeTotal += compte.getSolde();
		}
		System.out.println("La moyenne des soldes des comptes est de " + (soldeTotal/data.length) + "€.");
		
	}
	
}
