package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.dao.TecnicoDAO;

/**
 * Servlet implementation class RimozioneTecnicoController
 */
public class RimozioneTecnicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimozioneTecnicoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("PannelloDiControlloAmministratoreController");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("amministratore") == null) {
			response.sendRedirect("AccessoAmministratoreController?error=Accesso non autorizzato senza autenticazione");
		} else {
			String temp = request.getParameter("idTecnico");
			
			if(temp == null)
				response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore nella rimozione del tecnico#tecnici");
			
			int idTecnico = Integer.parseInt(temp);
			
			try {
				TecnicoDAO.rimuoviTecnico(idTecnico);
			} catch (SQLException e) {
				response.sendRedirect("PannelloDiControlloAmministratoreController?error=Errore del database#tecnici");
			}
			response.sendRedirect("PannelloDiControlloAmministratoreController#tecnici");
		}
	}

}
