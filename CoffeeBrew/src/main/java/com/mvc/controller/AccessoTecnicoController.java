package com.mvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.mvc.bean.TecnicoBean;
import com.mvc.dao.TecnicoDAO;

/**
 * Servlet implementation class AccessoTecnicoController
 */
public class AccessoTecnicoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccessoTecnicoController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/accessoTecnico.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("email") != null && request.getParameter("email") != "" &&
		   request.getParameter("password") != null && request.getParameter("password") != "") {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			TecnicoBean accessoTecnico = new TecnicoBean();
			accessoTecnico.setEmail(email);
			accessoTecnico.setPassword(password);
			
			boolean result = false;
			
			try {
				result = TecnicoDAO.accediTecnico(accessoTecnico);
			} catch (SQLException e) {
				response.sendRedirect("AccessoTecnicoController?error=Errore del database");
			}
			
			if(result) {
				request.getSession().invalidate();
				request.getSession(true).setAttribute("tecnico", accessoTecnico);
				response.sendRedirect("PannelloDiControlloTecnicoController");
			} else {
				response.sendRedirect("AccessoTecnicoController?error=Email o password errata");
			}
		}
	}

}
