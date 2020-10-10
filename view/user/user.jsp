<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<title>Welcome</title>
</head>
<body>
     <% if(session.getAttribute("userID")==null){
            //req.getRequestDispatcher("/").forward(req, resp);

        }
     %>
Welcome here!
        id: <c:out value="${user.id}" /> <br>
		log: <c:out value="${user.login}" /> <br>
		pass: <c:out value="${user.password}" /> <br>

</body>
</html>