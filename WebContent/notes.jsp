<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="keep.Note"%>

<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1.0" />
<jsp:useBean id="dao" class="keep.DAO" />
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
	<%
		HttpSession session = request.getSession(false);
		int id = (Integer) session.getAttribute("idUser");
		List<Note> notes = new ArrayList<Note>();
		List<String> colors = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> filter = (List<String>) session.getAttribute("filter");
		String search = (String) session.getAttribute("search");
		notes = dao.getNotes(id, filter, search);
		String username = dao.getUsername(id);
		session.setAttribute("notes", notes);
		session.setAttribute("username", username);
		pageContext.setAttribute("notes", notes);
		pageContext.setAttribute("username", username);
		colors = dao.getColors(id);
		pageContext.setAttribute("colors", colors);
		System.out.println(colors);
		session.setAttribute("colors", colors);
	%>
	<!-- Dropdown Structure -->
	<ul id="dropdown1" class="dropdown-content">
		<li><a href="./editUser.jsp">EDIT USER</a></li>
		<li class="divider"></li>
		<li class="red"><a href="./index.jsp" style="color: #fff;">SIGN
				OUT</a></li>
	</ul>

	<nav class="deep-orange darken-4">
		<div class="nav-wrapper container">
			<div class="row">
				<a id="logo-container" href="./index.jsp" class="brand-logo">Holy
					Note</a>
				<div class="col s6 offset-s3">
					<div class="input-field center">
						<form action="SearchText" method="post">
							<input placeholder="Search Text or Label" type="text" name="search"
								style="width: 300px; align: right; color: #fff;">
							<button class="btn waves-effect waves-light yellow darken-4"
								type="submit" style="margin: 10px;">Search</button>
						</form>
					</div>
				</div>
				<ul class="right brand-logo">
					<li><a class="dropdown-trigger" href="#!"
						data-target="dropdown1">${username}<i
							class="material-icons right">account_circle</i></a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<br>
			
			<div class="row">
				<div class="col s12">
					<div class="card">
						<div class="card-content">
							<form action="addNote" method="post">
								<div class="row">
									<div class="input-field col s12">
										<input placeholder="New Note" type="text" name="text">
									</div>
									<div class="input-field col s12">
										<input placeholder="Label" type="text" name="label">
									</div>
									<div>
										&nbsp; <label> <input class="with-gap" name="color"
											type="radio" value="blue" /> <span>Blue</span>
										</label> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <label>
											<input class="with-gap" name="color" type="radio"
											value="green" /> <span>Green</span>
										</label> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <label>
											<input class="with-gap" name="color" type="radio"
											value="pink" /> <span>Pink</span>
										</label> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <label>
											<input class="with-gap" name="color" type="radio"
											value="purple" /> <span>Purple</span>
										</label> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <label>
											<input class="with-gap" name="color" type="radio" value="red" />
											<span>Red</span>
										</label> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <label>
											<input class="with-gap" name="color" type="radio"
											value="teal" /> <span>Teal</span>
										</label> &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; <label>
											<input class="with-gap" name="color" type="radio"
											value="white" checked /> <span>White</span>
										</label>
									</div>
								</div>

								<div class="row" style="margin: 0px;">
									<button
										class="btn waves-effect waves-light yellow darken-4 right"
										type="submit">Create Note</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

			<div class="row"
				style="padding-left: 11.25px; padding-right: 11.25px; margin-top: 0px; margin-bottom: 0px;">
				<form action="FilterColor" method="post">
					<c:forEach var="color" items="${colors}" varStatus="id">
						<label> <input type="checkbox" class="filled-in"
							checked="checked" name="color" value="${color}" /><span>${color}</span>
						</label>
						&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
					</c:forEach>
					<button class="btn waves-effect waves-light yellow darken-4 right"
						type="submit">Filter</button>
				</form>
			</div>

			<div class="row">

				<c:forEach var="notes" items="${notes}" varStatus="id">
					<div class="col s4">
						<div class="card">
							<div class="${notes.color} lighten-3 card-content">
								<form action="updateNote" method="post">
									<div class="row">
										<a href="./removeNote?idNote=${notes.id}" style="color: #000;"><i
											class="close material-icons right">close</i></a>

										<div class="input-field col s12" style="margin: 0px;">

											<textarea class="materialize-textarea" name="text">${notes.text}</textarea>
										</div>
									</div>
									<div class="row" style="margin: 0px;">
										<div class="chip">${notes.label}</div>
										<div class="chip">${notes.rightdate}</div>
										<input type="hidden" name="label" value="${notes.label}">
										<input type="hidden" name="idNote" value="${notes.id}">
										<input type="hidden" name="idUser" value="${notes.idUser}">

										<button
											class="btn waves-effect waves-light yellow darken-4 right"
											type="submit">Save</button>
									</div>
								</form>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<br> <br>
		</div>
	</div>



	<!--  Scripts-->
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="js/materialize.js"></script>
	<script src="js/init.js"></script>
</body>
</html>
