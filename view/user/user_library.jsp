<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Библиотека книг</title>
	<meta charset="utf-8">
	<!-- Настройка viewport -->
	<meta http-equiv="Content-Type" content="text/html charset=utf-8"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" type="text/css" href="resources/css/my.css">
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.grid.css">
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
	<script type="text/javascript" src="../resources/js/bootstrap.bundle.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/popper.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-dark">
		<div class="container">
			<div class="row align-items-start">
				<div class="col">
					<a href="my_books" class="text-white h6"><c:out value="${sessionScope.userLogin}"/></a>
					<a href="logout" class="text-white">X</a>
				</div>
			</div>
			<div class="row align-items-center">
				<div class="col navbar-text text-white h4 ">
					Library
				</div>
			</div>
			<div class="row align-items-end">
				<a href="library" class="text-white h6">Библиотека</a>
			</div>
		</div>
	</nav>

	<div class="mt-4 container input-group">
		<form class="container" method="get">
			<input name="search" type="text" class="form-control" placeholder="Поиск по книгам (Введите название или автора)">
			<input type="submit" hidden="true" name="">
		</form>
		
	</div>

	<!-- <div class="mt-4 container input-group">
		<input name="search" type="text" class="form-control" placeholder="Поиск по книгам (Введите название или автора)">
		<div class="input-group-append">
			<button id="searchbtn" class="btn btn-secondary" type="button">
				<i class="fa fa-search"></i>
			</button>
		</div>
	</div>
 -->
	<div class="container mt-4">
		<div class="row">
			<div class="col-3 h5">Автор</div>
			<div class="col-3 h5">Название книги</div>
			<div class="col-3 h5">Описание</div>
			<div class="col-3 h5">Действие</div>
		</div>

		<c:forEach var="book" items="${books}">
		<div class="row mt-2">
			<div class="col-3 h6 vertical-allign">${book.author}</div>
			<div class="col-3 h6">${book.name}</div>
			<div class="col-3 h6">${book.description}</div>
			<div class="col-3 h6">
				<button type="button" class="btn btn-success">Читать</button>
				<button type="button" class="btn btn-info">Скачать</button>
			</div>
		</div>
	</c:forEach>


</div>
</div>
</body>
</html>