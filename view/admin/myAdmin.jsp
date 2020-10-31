<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Книги для пользователей</title>
	<meta charset="utf-8">
    	<!-- Настройка viewport -->
    	<meta http-equiv="Content-Type" content="text/html charset=utf-8"/>
        	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        	<link rel="stylesheet" type="text/css" href="">
    	<link rel="stylesheet" type="text/css" href="resources/css/my.css">
    	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
    	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.grid.css">
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
                <a href="my_admin" class="text-white h6"> <c:out value="${sessionScope.adminLogin}"/></a>
                <a href="logout" class="text-white">X</a>
            </div>
          </div>
           <div class="row align-items-center">
                <div class="col navbar-text text-white h4 ">
                Library (admin)
                </div>
            </div>
          <div class="row align-items-end">
        </div>
    </nav>

   <div class="container">
        <a href="add_author"><button type="submit" class="btn btn-primary mt-2">Добавить автора</button></a> <br>
        <a href="add_book"><button type="submit" class="btn btn-primary mt-2">Добавить книгу</button></a> <br>
        <a href="change_author"><button type="submit" class="btn btn-primary mt-2">Изменить/удалить автора</button></a> <br>
        <a href="change_book"><button type="submit" class="btn btn-primary mt-2">Изменить/удалить книгу</button></a> <br>
        <a href="report"><button type="submit" class="btn btn-primary mt-2">Вывести отчет</button></a> <br>
   </div>

</body>