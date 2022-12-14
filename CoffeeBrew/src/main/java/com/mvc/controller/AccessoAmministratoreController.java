package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.AmministratoreBean;
import com.mvc.dao.AmministratoreDAO;

/**
 * Servlet implementation class AccessoAmministratoreController
 */
public class AccessoAmministratoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoAmministratoreController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/accessoAmministratore.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			AmministratoreBean accessoAmministratore = new AmministratoreBean();
			accessoAmministratore.setEmail(email);
			accessoAmministratore.setPassword(password);
			
			boolean result = false;
			
			try {
				result = AmministratoreDAO.accediAmministratore(accessoAmministratore);
			} catch (SQLException e) {
				response.sendRedirect("AccessoAmministratoreController?error=Errore del database");
			}
			
			if(result) {
				request.getSession().invalidate();
				request.getSession(true).setAttribute("amministratore", accessoAmministratore);
				response.sendRedirect("PannelloDiControlloAmministratoreController");
			} else {
				response.sendRedirect("AccessoAmministratoreController?error=Email o password errata");
			}
		}
	}

}
