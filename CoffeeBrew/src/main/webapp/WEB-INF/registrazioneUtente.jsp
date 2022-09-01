<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link rel="stylesheet" href="css/style.css">
		<title>CoffeeBrew - Registrazione</title>
	</head>
	<body>
		<%@include file="/includes/navbar.jsp"%>
		<div class="container-fluid row px-0 vh-100">
			<div class="card mx-auto my-auto" style="width: 28rem;">
				<div class="card-body" id="cardForm">
					<h5 class="card-title fw-bold">Registrazione</h5>
					<p class="card-text">
					<% if(request.getParameter("error") != null && request.getParameter("error") != "") { %>
						<div class="alert alert-danger" role="alert">
						  <%= request.getParameter("error") %>
						</div>
					<% } %>
					<form id="formRegistrazioneUtente" action="RegistrazioneUtenteController" method="POST">
						<div class="form-floating formfloating-sm mb-3">
							<input id="nome" type="text" class="form-control" name="nome" placeholder="Nome">
							<label for="nome" class="form-label">Nome</label> 
						</div>
						<div class="form-floating mb-3">
							<input id="cognome" type="text" class="form-control" name="cognome" placeholder="Cognome">
							<label for="cognome" class="form-label">Cognome</label> 
						</div>
						<div class="form-floating mb-3">
							<input id="email" type="email" class="form-control" name="email" placeholder="Indirizzo email">
							<label for="email" class="form-label">Indirizzo email</label> 
						</div>
						<div class="input-group form-floating mb-3">
							<input type="password" class="form-control" id="newPassword" name="password" placeholder="Password" aria-describedby="visualizzaBtn"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" title="almeno un numero, una lettera maiuscola e minuscola, e almeno 8 caratteri" required>
							<button id="visualizzaBtn" type="button" class="btn btn-outline-secondary">Visualizza</button>
							<label for="newPassword" class="form-label">Password</label>
						</div>
						<div class="text-center w-100">
							<button type="submit" class="w-50 btn btn-success text-center">Registrati</button>
						</div>
					</form>
					</p>
				</div>
				<div class="card-body border-top border-3">
					<a class="btn btn-primary w-100 mb-1" href="AccessoUtenteController">Accesso Utenti</a>
					<a class="btn btn-primary w-100 mb-1" href="AccessoTecnicoController">Accesso Tecnici</a>
					<a class="btn btn-primary w-100 mb-1" href="AccessoAmministratoreController">Accesso Amministratore</a>
				</div>
			</div>
		</div>
		<script src="js/formCheck.js"></script>
	</body>
</html>