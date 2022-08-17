package com.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.helper.DBHelper;
import com.mvc.bean.CartaDiCreditoBean;

public class CartaDiCreditoDAO {
	public static boolean inserisciCarta(CartaDiCreditoBean cartaDiCredito) {
		String numeroCarta = cartaDiCredito.getNumeroCarta();
		String nomeSullaCarta = cartaDiCredito.getNomeSullaCarta();
		Date dataScadenza = cartaDiCredito.getDataScadenza();
		int idUtente = cartaDiCredito.getIdUtente();
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("INSERT INTO CartaDiCredito (numeroCarta, nomeSullaCarta, dataScadenza, idUtente) values (?, ?, ?, ?)");
			pstmt.setString(1, numeroCarta);
			pstmt.setString(2, nomeSullaCarta);
			pstmt.setDate(3, dataScadenza);
			pstmt.setInt(4, idUtente);
			int result = pstmt.executeUpdate();
			
			pstmt.close();
			connection.close();
			
			return result > 0;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static ArrayList<CartaDiCreditoBean> getCarte(int idUtente) {
		ArrayList<CartaDiCreditoBean> listaCarte = new ArrayList<CartaDiCreditoBean>();
		
		try {
			Connection connection = DBHelper.connectToDB();
			
			PreparedStatement pstmt = null;
			pstmt = connection.prepareStatement("SELECT * FROM CartaDiCredito WHERE idUtente = ?");
			pstmt.setInt(1, idUtente);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartaDiCreditoBean cdc = new CartaDiCreditoBean();
				cdc.setNumeroCarta(rs.getString("numeroCarta"));
				cdc.setNomeSullaCarta(rs.getString("nomeSullaCarta"));
				cdc.setDataScadenza(rs.getDate("dataScadenza"));
				
				listaCarte.add(cdc);
			}
			
			pstmt.close();
			connection.close();
			
			return listaCarte;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}