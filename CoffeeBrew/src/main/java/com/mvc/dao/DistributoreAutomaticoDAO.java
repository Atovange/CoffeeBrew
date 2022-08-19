package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.helper.DBHelper;
import com.mvc.bean.DistributoreAutomaticoBean;
import com.mvc.bean.UtenteBean;

public class DistributoreAutomaticoDAO {
	private Connection conn;
	
	public DistributoreAutomaticoDAO() {
		try {
			conn = DBHelper.connectToDB();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public UtenteBean getOccupante(int idDistributore) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("SELECT nome, centesimiCredito FROM DistributoreAutomatico, Utente WHERE idUtente = occupante AND idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			ResultSet rs = pstmt.executeQuery();
			UtenteBean occupante = new UtenteBean();
			
			if(rs.next()) {
				occupante.setNome(rs.getString("nome"));
				occupante.setCentesimiCredito(rs.getInt("centesimiCredito"));
			} else {
				occupante = null;
			}
			pstmt.close();
			
			return occupante;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void impostaOccupato(int idDistributore, int idUtente) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupante = ? WHERE idDistributore = ?");
			pstmt.setInt(1, idUtente);
			pstmt.setInt(2, idDistributore);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void impostaLibero(int idDistributore) {
		try {
			PreparedStatement pstmt = null;
			pstmt = conn.prepareStatement("UPDATE DistributoreAutomatico SET occupante = NULL WHERE idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean registraDistributoreAutomatico(DistributoreAutomaticoBean registrazioneDistributoreAutomatico) throws SQLException {
		String locazione = registrazioneDistributoreAutomatico.getLocazione();
		Connection connection = null; 
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("INSERT INTO DistributoreAutomatico (locazione) VALUES (?)");
			pstmt.setString(1, locazione);
			int result = pstmt.executeUpdate();
			
			return result > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		return false;
	}
	
	public static ArrayList<DistributoreAutomaticoBean> getDistributori() throws SQLException {
		ArrayList<DistributoreAutomaticoBean> listaDistributori = new ArrayList<DistributoreAutomaticoBean>();
		
		Connection connection = null;	
		PreparedStatement pstmt = null;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("SELECT * FROM DistributoreAutomatico");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				DistributoreAutomaticoBean distr = new DistributoreAutomaticoBean();
				distr.setIdDistributore(rs.getInt("idDistributore"));
				distr.setLocazione(rs.getString("locazione"));
				distr.setOccupante(rs.getInt("occupante"));
				listaDistributori.add(distr);
			}

			return listaDistributori;			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return null;
	}
	
	public static boolean rimuoviDistributoreAutomatico(int idDistributore) throws SQLException {
		Connection connection = null;	
		PreparedStatement pstmt = null;
		int result;
		
		try {
			connection = DBHelper.connectToDB();
			pstmt = connection.prepareStatement("DELETE FROM DistributoreAutomatico WHERE idDistributore = ?");
			pstmt.setInt(1, idDistributore);
			result = pstmt.executeUpdate();
			
			return result > 0;
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connection.close();
		}
		
		return false;
	}
}