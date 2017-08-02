<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login gérant</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="/resources/js/script.js"></script>
</head>
<body>
	<div class="container" id="container">

		<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/HeroesProjectWeb">ProxiBanque</a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="/home.xhtml">Accueil</a></li>
				</ul>
			</div>
		</div>
		</nav>

		<div class="row" id="titreFormulaire">
			<h3>Connexion en tant que gérant</h3>
			<form class="form-horizontal" action="j_security_check" method="POST">
				<div class="form-group">
					<label class="control-label col-sm-4">Login : </label>
					<div class="col-sm-4">
						<input type="text" class="form-control" name="j_username">
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-4">Mot de passe :
					</label>
					<div class="col-sm-4">
						<input type="password" class="form-control" id="nom" name="j_password">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-2">
						<button type="submit" class="btn btn-default">Connexion</button>
					</div>
					<div class="col-sm-offset-1 col-sm-2">
						<button type="reset" class="btn btn-danger">Effacer</button>
					</div>
				</div>
			</form>
		</div>

	</div>
</body>
</html>