package com.bankonet.test;

import com.bankonet.model.CompteCourant; // on est plus dans le meme package donc on importe la classe compteCourant

public class TestCompteCourant {
	
	public static void main(String[] args){

		CompteCourant[] compteCourantTab;
		
		compteCourantTab = new CompteCourant[3];
		compteCourantTab[0] = new CompteCourant(1, "compte 1", 1000f, 0f);
		compteCourantTab[1] = new CompteCourant(2, "compte 2", 2000f, 200f);
		compteCourantTab[2] = new CompteCourant(3, "compte 3", 3000f, 300f);
	}
	
}
