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
        <h2>Добавить Книгу</h2>
        <form method="post">
            <div class="form-group">
                <label for="author_id">Автор:</label>
                <select class="form-control" type='text' name='author_id' required>
                    <option selected disabled hidden value='Null'>Выберите</option>
                    <c:forEach var="author" items="${authors}">
                    <option selected value="${author.id}">${author.firstname} ${author.patronymic} ${author.lastname}</option>
                </c:forEach>
            </select>
            <label for="book_name">Название книги:</label> <br>
            <input class="form-control" type="text" id="book_name" name="book_name" autocomplete="off" required>
            <label for="genre_id">Жанр:</label> <br>
            <select class="form-control" type='text' name='genre_id' required>
                <option selected disabled hidden value='Null'>Выберите</option>
                <option selected value="1">Роман</option>
                <option selected value="2">Повесть</option>
                <option selected value="3">Рассказ</option>
                <option selected value="4">Стихотворение</option>
                <option selected value="5">Поэма</option>
            </select>
            <label for="description">Описание:</label> <br>
            <input class="form-control" type="text" id="description" name="description" autocomplete="off" required>
            <label for="price">Цена:</label> <br>
            <input class="form-control" type="text" id="price" name="price" autocomplete="off" required>
            <label for="path">Название файла:</label> <br>
            <input class="form-control" type="text" id="path" name="path" autocomplete="off" required>
            <button type="submit" class="btn btn-primary mt-2">Добавить</button>
            <a href="my_admin"><button type="button" class="btn btn-danger mt-2">Вернуться</button></a>
        </div>
    </form>
</div>


</body>