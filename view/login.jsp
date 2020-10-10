<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
	<!-- Настройка viewport -->
	<link rel="stylesheet" type="text/css" href="resources/css/my.css">
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="resources/css/bootstrap.grid.css">
	<script type="text/javascript" src="../resources/js/bootstrap.bundle.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/popper.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<title>Sign in</title>
</head>
<body>
	<div class="form-group">
		<form class="form-signin" method="post" name="loginForm">
			<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1> 
			<label for="inputEmail" class="sr-only">Email address</label>
			<input type="email" id="inputEmail" name="login" class="form-control" placeholder="Email address" required="" autofocus="">
			<label for="inputPassword" class="sr-only">Password</label>
			<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required="">
			<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
		</form>
	</div>
</body>
</html>
