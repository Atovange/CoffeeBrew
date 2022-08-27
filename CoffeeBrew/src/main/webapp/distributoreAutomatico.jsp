<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	<body>
		<button id="poll" type="button">Polling</button>
		<input type="number" id="idDistributore" name="idDistributore">
		<div id="demo"></div>
		
		<div id="interfacciaUtente">
			<h3>Benvenuto <span id="nomeUtente"></span></h3>
			<p>Credito Residuo: &euro;<span id="credito"></span></p>
			<div id="templateSelezionaProdotto"><span id="nomeProdotto"></span> [&euro;<span id="costoProdotto"></span>]<button id="buttonProdotto">Seleziona</button><br></div>
			<div id="pulsantiProdotti">
			
			</div>
			<!-- 
			Cappuccino		 [&euro;1,50] <button name="prodotto" id="cappuccino"	value="150">Seleziona</button><br>
			Espresso		 [&euro;1,00] <button name="prodotto" id="espresso"		value="100">Seleziona</button><br>
			Cioccolata calda [&euro;1,00] <button name="prodotto" id="cioccolata"	value="100">Seleziona</button><br>
			T� verde		 [&euro;0,50] <button name="prodotto" id="te verde"		value="50"> Seleziona</button><br>
			-->
			<p id="scelta"></p>
			<input type="hidden" id="importo" name="importo" value="">
			<input type="hidden" id="utente" name="idUtente" value="">
			<input id="acquistaButton" type="submit" value="Acquista">
		</div>
		
		<div id="interfacciaTecnico" hidden>
			<h3>Benvenuto <span id="nomeTecnico"></span></h3>
			<div id="listaProdotti">
				<span>Cappuccino:		<span id="qtyCappuccino">	</span><button onclick="ricaricaProdotto('cappuccino')">	Ricarica</button></span><br>
				<span>Espresso:			<span id="qtyEspresso">		</span><button onclick="ricaricaProdotto('espresso')">		Ricarica</button></span><br>
				<span>Cioccolata calda: <span id="qtyCioccolata">	</span><button onclick="ricaricaProdotto('cioccolata')">	Ricarica</button></span><br>
				<span>T� verde:			<span id="qtyTe">			</span><button onclick="ricaricaProdotto('te verde')">		Ricarica</button></span><br>
			</div>
			<input id="exitButton" onclick="exit()" type="submit" value="Esci">
		</div>
		
		<script src="polling.js"></script>
	</body>
</html>