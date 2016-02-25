<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Forum de Discussão</title>
<link rel="stylesheet" href="bootstrap-3.3.5/css/bootstrap.min.css" />
</head>
<body>

	<div class="container">
		<br>
		
		<div class="row wrapper text-right"> Bem vindo, <%= request.getAttribute("nomeUsuario") %></div>
		

		<div class="row wrapper text-center">

			<div class="col-md-1"></div>

			<div class="col-md-10">
				<br>
				<h1>Nutrileigos - Fórum</h1>
				<br>

				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<tr>
							<th class="text-center">Título</th>
							<th class="text-center">Autor</th>
							<th class="text-center">Resposta(s)</th>
							<th class="text-center">Data</th>
						</tr>
						<tr>
							<td><a href="foruns/valor-academia.html">Valor da
									Academia</a></td>
							<td>Walison</td>
							<td>0</td>
							<td>11/12/2015:14:55</td>
						</tr>

						<tr>
							<td><a href="foruns/dieta-carboidratos.html">Dieta de
									carboidratos</a></td>
							<td>Alice</td>
							<td>0</td>
							<td>11/12/2015:14:55</td>
						</tr>

						<tr>
							<td><a href="foruns/atividade-fisica-em-casa.html">Atividades
									fisicas para fazer em casa</a></td>
							<td>Ana</td>
							<td>0</td>
							<td>11/12/2015:14:55</td>
						</tr>
					</table>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>


		<div class="row wrapper text-center">
			<div class="col-md-10"></div>
			<div class="col-md-2">
				<input onclick="location.href = 'novo-forum.html';"
					name="novo-forum" id="novo-forum" tabindex="4"
					class="form-control btn btn-success" value="Novo post">
			</div>
		</div>
	</div>


<!-- Botão voltar -->
	<div class="row wrapper text-center">
					<div class="col-md-10"></div>
						<div class="col-md-2">
						<input type="button" value="Voltar" onClick="history.go(-2)">
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
	</footer>

</body>
</html>