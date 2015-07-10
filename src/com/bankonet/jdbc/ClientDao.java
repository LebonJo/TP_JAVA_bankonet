package com.bankonet.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bankonet.Compte;
import com.bankonet.Compte.TypeCompte;
import com.bankonet.model.Client;
import com.bankonet.model.CompteCourant;
import com.bankonet.model.CompteEpargne;

public class ClientDao {
	
	private final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final String DB_URL = "jdbc:mysql://localhost/banque";
	private final String DB_LOGIN = "root";
	private final String DB_PASSWORD = "";
	private Connection connection;
	
	public ClientDao() throws CreationConnexionException{
		try{
			Class.forName(DRIVER_NAME);
			this.connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
		}catch(ClassNotFoundException ex){
			System.err.println("Impossible de charger le driver : "
					+ "la classe du driver doit se situer dans le classpath");
			throw new CreationConnexionException(
					"Erreur lors de la création de la connexion au référentiel");
		}catch(SQLException e){
			throw new CreationConnexionException(
					"Erreur lors de la création de la connexion au référentiel");
		}
		
	}
	
	public void closeConnection(){
		if(connection != null){
			try {
				this.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeQuery(Object statement, Object result){
		if(statement!=null){
			try{
				if(statement instanceof Statement) ((Statement)statement).close();
				if(statement instanceof PreparedStatement) ((PreparedStatement)statement).close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		if(result!=null){
			try {
				if(result instanceof ResultSet) ((ResultSet)result).close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	public List<Client> lireClients(){
		
		List<Client> listClients = new ArrayList<Client>();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = this.connection.prepareStatement("SELECT ID, NOM, PRENOM FROM CLIENT");
			result = statement.executeQuery();
			while(result.next()){
				Client client = new Client(String.valueOf(result.getInt(1)), result.getString(2), result.getString(3));
				listClients.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuery(statement, result);
		}
		
		return listClients;
	}


	public Client lireClient(int id){
		Client client = new Client("0", "John", "Doe");
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = this.connection.prepareStatement("SELECT ID, NOM, PRENOM FROM CLIENT WHERE ID=?");
			statement.setInt(1, id);
			result = statement.executeQuery();
			while(result.next()){
				client = new Client("" + result.getInt(1), result.getString(2), result.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuery(statement, result);
		}
		
		return client;
	}
	
	public List<CompteCourant> lireComptesCourants(int id_client){
		List<CompteCourant> listComptesCourants = new ArrayList<CompteCourant>();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = this.connection.prepareStatement("SELECT ID, LIBELLE, SOLDE, DECOUVERTAUTORISE FROM COMPTE WHERE DISCRIMINANT=\"CC\" AND ID_CLIENT=?");
			statement.setInt(1, id_client);
			result = statement.executeQuery();
			while(result.next()){
				CompteCourant compte = new CompteCourant(result.getInt(1), result.getString(2), result.getFloat(3), result.getFloat(4));
				listComptesCourants.add(compte);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuery(statement, result);
		}
		
		return listComptesCourants;
	}
	
	public List<CompteEpargne> lireComptesEpargnes(int id_client){
		List<CompteEpargne> listComptesEpargnes = new ArrayList<CompteEpargne>();
		PreparedStatement statement = null;
		ResultSet result = null;
		
		try {
			statement = this.connection.prepareStatement("SELECT ID, LIBELLE, SOLDE, TAUX, PLAFOND FROM COMPTE WHERE DISCRIMINANT=\"CE\" AND ID_CLIENT=?");
			statement.setInt(1, id_client);
			result = statement.executeQuery();
			while(result.next()){
				CompteEpargne compte = new CompteEpargne(result.getInt(1), result.getString(2), result.getFloat(3), result.getDouble(4), result.getFloat(5));
				listComptesEpargnes.add(compte);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeQuery(statement, result);
		}
		
		return listComptesEpargnes;
	}
	
	public void ecrireClient(Client client){
		PreparedStatement statement = null;
		int result = 0;
		
		try{
			statement = this.connection.prepareStatement("INSERT INTO CLIENT(ID, NOM, PRENOM, LOGIN, MOTDEPASSE) VALUES (?, ?, ?, ?, ?)");
			statement.setString(1, client.getIdentifiant()); 
			statement.setString(2, client.getNom());
			statement.setString(3, client.getPrenom());
			statement.setString(4, client.getPrenom().toLowerCase());
			statement.setString(5, client.getPrenom().toLowerCase());
			result = statement.executeUpdate();
			for(Map.Entry<Integer, Compte> entry : client.getMapComptes().entrySet()){
				if(entry.getValue().getTypeCompte().equals(TypeCompte.COURANT.getTypeCompte())) ecrireCompteCourant((CompteCourant) entry.getValue(), client);
				if(entry.getValue().getTypeCompte().equals(TypeCompte.EPARGNE.getTypeCompte())) ecrireCompteEpargne((CompteEpargne) entry.getValue(), client);
			}
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			closeQuery(statement, result);
		}
	}
	
	public void ecrireCompteCourant(CompteCourant compte, Client client) throws SQLException{
		PreparedStatement statement = null;
		int result = 0;

		statement = this.connection.prepareStatement("INSERT INTO COMPTE(ID, ID_CLIENT, DISCRIMINANT, LIBELLE, SOLDE, DECOUVERTAUTORISE) VALUES (?, ?, ?, ?, ?, ?)");
		statement.setInt(1, compte.getIdentifiant()); 
		statement.setInt(2, Integer.parseInt(client.getIdentifiant()));
		statement.setString(3, "CC");
		statement.setString(4, compte.getLibelle());
		statement.setFloat(5, compte.getSolde());
		statement.setFloat(6, compte.getDecouvertAutorise());
		result = statement.executeUpdate();
	}
	
	public void ecrireCompteEpargne(CompteEpargne compte, Client client) throws SQLException{
		PreparedStatement statement = null;
		
		statement = this.connection.prepareStatement("INSERT INTO COMPTE(ID, ID_CLIENT, DISCRIMINANT, LIBELLE, SOLDE, PLAFOND, TAUX) VALUES (?, ?, ?, ?, ?, ?, ?)");
		statement.setInt(1, compte.getIdentifiant()); 
		statement.setInt(2, Integer.parseInt(client.getIdentifiant()));
		statement.setString(3, "CE");
		statement.setString(4, compte.getLibelle());
		statement.setFloat(5, compte.getSolde());
		statement.setFloat(6, compte.getPlafond());
		statement.setDouble(7, compte.getTauxInteret());
		statement.executeUpdate();
		statement.close();
	}
	
	public void supprimerClientEtComptes(Client client){
		PreparedStatement statement = null;
		int result = 0;
		
		try{
			for(Map.Entry<Integer, Compte> entry : client.getMapComptes().entrySet()){
				statement = this.connection.prepareStatement("DELETE FROM COMPTE WHERE ID_CLIENT = ?");
				statement.setInt(1, Integer.parseInt(client.getIdentifiant()));
				result = statement.executeUpdate();
			}
			statement = this.connection.prepareStatement("DELETE FROM CLIENT WHERE ID = ?");
			statement.setInt(1, Integer.parseInt(client.getIdentifiant()));
			result = statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeQuery(statement, result);
		}		
	}
}
