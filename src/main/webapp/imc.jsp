<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="java.lang.*,java.io.*,java.util.*,java.math.*,java.text.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Calculo do IMC</title>

<link rel="stylesheet"
	href="bootstrap-3.3.5/css/bootstrap.min.css" />

</head>
<body>
<!-- Componente do icone do nutrileigos -->
	<div class="container center-block">
		<div class="col-md-12">
			<div class="col-md-1">
				<img src="images/iconnutri.jpg" class="img-responsive"
					alt="Responsive image">
			</div>
			<!-- Botão voltar -->
			<div class="row wrapper text-right">
				<input type="button" class="btn btn-success" value="Voltar"
					onClick="history.go(-1)">
			</div>
		<div class="row">
			<div class="col-md-4">
				<div class="panel panel-success">
					<div class="panel-heading text-center">
						<h4>Tabela de refer&ecirc;ncia</h4>
					</div>

					<div class="panel-success">
						<table class="table table-striped">
							<thead>
								<tr>
									<td><b>Resultado</b></td>
									<td><b>Situa&ccedil;&atilde;o</b></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Abaixo de 17</td>
									<td>Muito abaixo do <em>peso</em></td>
								</tr>
								<tr>
									<td>Entre 17 e 18,49</td>
									<td>Abaixo do <em>peso</em></td>
								</tr>
								<tr>
									<td>Entre 18,5 e 24,99</td>
									<td><em>Peso</em> normal</td>
								</tr>
								<tr>
									<td>Entre 25 e 29,99</td>
									<td>Acima do <em>peso</em></td>
								</tr>
								<tr>
									<td>Entre 30 e 34,99</td>
									<td><em>Obesidade</em> I</td>
								</tr>
								<tr>
									<td>Entre 35 e 39,99</td>
									<td><em>Obesidade</em> II (severa)</td>
								</tr>
								<tr>
									<td>Acima de 40</td>
									<td><em>Obesidade</em> III (m&oacute;rbida)</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="col-md-8">
				<div class="panel panel-success">
					<div class="panel-heading text-center">
						<h4>IMC: &Iacute;ndice de Massa C&oacute;rporea</h4>
					</div>
					<div class="panel-body">
						<!-- <form name="imcForm" action="imc.jsp" method="POST">-->
						<form name="imcForm" action="CalculoIMC">
							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Digite o seu peso (em kg)" name="peso" required>
							</div>

							<div class="form-group">
								<input type="text" class="form-control"
									placeholder="Digite a sua altura (em m)" name="altura" required>
							</div>

							<br>

							<div id='toolbar'>
								<div class='wrapper text-center'>
									<div class="btn-group pagination-centered" role="group">
										<button type="submit" class="btn btn-success btn-lg" value="">
											<span class="glyphicon glyphicon-dashboard" aria-hidden="true"/>
											Calcular
										</button>

										<button type="submit" class="btn btn-warning btn-lg" value="">
											<span class="glyphicon glyphicon-erase" aria-hidden="true"/>
											Limpar
										</button>
									</div>
								</div>
							</div>

							<div class="row">
								<div class="wrapper text-center">

									<br></br>
									<%
										// Obtem o peso do parametro da requisicao (la do formulario)
										String peso = request.getParameter("peso");

										// Faco o mesmo com a altura...tambem do parametro da requisicao (la do formulario)
										String altura = request.getParameter("altura");

										if (peso != null) {
											// Injeto esse trecho de HTML na pagina, com o peso e a altura...
											out.println("<b>Sua massa &eacute; de " + peso + " kg e sua altura &eacute; de " + altura + " m.</b><br/></br>");

											Double imc = Double.parseDouble(peso) / Math.pow(Double.parseDouble(altura), 2);

											DecimalFormat df = new DecimalFormat("#.##");

											// StringBuilder: classe do Java para concatenar Strings (metodo append)
											StringBuilder sb = new StringBuilder();

											// Injeto esse trecho de HTML na pagina, com o IMC ja calculado...
											sb.append("<br> Seu IMC &eacute; de <h4>" + df.format(imc) + "</h4>");


											// if-else com os valores possiveis de IMC. Pra cada faixa de valor, eu gero uma mensagem diferente
											if (imc < 17) {
												// muito abaixo do peso
											} else if (imc >= 17 && imc <= 18.49) {
												// abaixo do peso
											} else if (imc >= 18.5 && imc <= 24.99) {
												sb.append("<div class=\"alert alert-success alert-dismissible\" role=\"alert\">");
												sb.append("<h3>Parab&eacute;ns!</h3><br> Voc&ecirc; est&aacute; com o <b>peso ideal</b> ;) <br>");
												sb.append(".</div>");
											} else if (imc >= 25 && imc <= 29.99) {
												// acima do peso
											} else if (imc >= 30 && imc <= 34.99) {
												// obesidade 1
											} else if (imc >= 35 && imc <= 39.99) {
												// obesidade 2 severa
											} else if (imc >= 40) {
												// obesidade 3 morbida
											}

											// Injeto o conteudo da minha StringBuilder com o alerta criado durante o if-else...
											out.println(sb.toString());
										}
									%>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Rodape da pagina -->
		<footer>
			<div class="container">
				<div class="row wrapper text-center">
					<div class="col-md-12 ">

						<div class="well well-sm">
							Nutrileigos - 2015 <br> Desenvolvido por: <a
								href="https://github.com/AnaLeticia" target="_blank">Ana
								Letícia</a>
						</div>
					</div>
				</div>
			</div>
</body>
</html>