package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.UtenteBean;
import com.mvc.dao.UtenteDAO;

/**
 * Servlet implementation class RicaricaCreditoController
 */
public class RicaricaCreditoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RicaricaCreditoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("utente") != null) {
			if(request.getParameter("idCarta") != null && request.getParameter("idCarta") != "" &&
			   request.getParameter("euroRicarica") != null && request.getParameter("euroRicarica") != "") {
				
				UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
				
				int centesimiRicarica = 100 * Integer.parseInt(request.getParameter("euroRicarica"));
				
				try {
					UtenteDAO.ricaricaCredito(utente, centesimiRicarica);
					utente.setCentesimiCredito(utente.getCentesimiCredito() + centesimiRicarica);
					request.getSession().setAttribute("utente", utente);
					
				} catch (SQLException e) {
					response.sendRedirect("CarteDiCreditoController?error=Errore del database#ricarica");
				}
				
				response.sendRedirect("CarteDiCreditoController?message=Credito aggiunto con successo#ricarica");
			} else {
				response.sendRedirect("CarteDiCreditoController?error=Riempire tutti i campi#ricarica");
			}
		} else {
			response.sendRedirect("AccessoUtenteController");
		}
	}
}
