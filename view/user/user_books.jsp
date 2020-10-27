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
		    	<a href="" class="text-white h6">Erofeev.99@mail.ru</a>
		    </div>
   		  </div>
		   <div class="row align-items-center">
			    <div class="col navbar-text text-white h4 ">
		    	Library
		    	</div>
		   	</div>
		  <div class="row align-items-end">
   
		</div>
	</div>
	</nav>
	<div class="container mt-4">
		<div class="row">
			<div class="col-4 h5">Автор</div>
			<div class="col-4 h5">Название книги</div>
			<div class="col-4 h5">Скачать</div>
		</div>

        <c:forEach var="book" items="${books}">
                <div class="row mt-2">
                	<div class="col-4 h6 vertical-allign">${book.author}</div>
                	    <div class="col-4 h6">${book.name}</div>
                		<div class="col-4 h6">
                			<button type="button" class="btn btn-success">Читать</button>
                			<button type="button" class="btn btn-info">Скачать</button>
                	    </div>
                </div>
            </c:forEach>
		<!-- цикл -->

		<!-- конец цикла -->

		<!-- <div class="row mt-2">
			<div class="col-4 h6">Пушкин А.С.</div>
			<div class="col-4 h6">А в душе я танцую</div>
			<div class="col-4 h6">
				<button type="button" class="btn btn-success">Читать</button>
				<button type="button" class="btn btn-info">Скачать</button>
			</div>
			-->
		</div>
	</div>
</body>
</html>