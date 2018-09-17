<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<title>Holy Note</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link href="css/materialize.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="css/style.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>
<body>
	<nav class="deep-orange darken-4" role="navigation">
		<div class="nav-wrapper container">
			<a id="logo-container" href="./index.jsp" class="brand-logo">Holy Note</a>
			<ul class="right">
			</ul>
		</div>
	</nav>
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<br>
			<div class="row">
				<div class="col s12 m12">
					<div class="card">
						<div class="card-content">
							<form action="updateUser" method="post">
								<div class="row">
									<div class="input-field col s12">
										<input id="username" type="text" class="validate" name="new_username"> 
										<label for="username">Edit Username</label>
									</div>
								</div>
								<div class="row">
									<div class="input-field col s12">
										<input id="pass" type="password" class="validate" name="old_password"> 
										<label for="pass"> Old Password</label>
									</div>
								</div>
								<div class="row">
									<div class="input-field col s12">
										<input id="pass" type="password" class="validate" name="new_password"> 
										<label for="pass"> New Password</label>
									</div>
								</div>								
								<div class="row" style="margin: 0px;">
									<button class="btn waves-effect waves-light yellow darken-4" type="submit">Save</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<br>
			<br>
		</div>
	</div>



	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>

</body>
</html>
