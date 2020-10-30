<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Покупка</title>
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

	<div class="container">
	<form method="post" name="check">
		<div class="form-group">
			<h2> Оформление покупки</h2>
			<label for="login">Логин пользователя</label>
			<input class="form-control" type="text" id="login" name="login" value="<c:out value="${sessionScope.userLogin}"/>" readonly>
		</div>
		<div class="form-group">
			<label for="book_name">Информация о товаре</label> <br>
			Название книги: <input class="form-control" type="text" id="book_name" name="book_name" value="<c:out value="${book.name}"/>" readonly>
			Автор книги: <input class="form-control" type="text" id="author" name="author" value="<c:out value="${book.author}"/>" readonly>
			Стоимость: <input class="form-control" type="text" id="price" name="price" value="<c:out value="${book.price}"/>" readonly>
			<input type="hidden" name="book_id" value="<c:out value="${book.id}"/>">
			<button type="submit" class="btn btn-primary mt-2">Купить</button>
			<a href="my_books"><button type="button" class="btn btn-danger mt-2">Отменить покупку</button></a>
		</div>
	</form>
	</div>


</div>
</div>
</body>
</html>