<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title> Spring Boot Example</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
<div>
<div class="container" style="margin:50px">
    <div class="row text-center"><strong> User Details</strong></div>
    <div class="row" style="border:1px solid green;padding:10px">
        <div class="col-md-3 text-center"><strong>First Name</strong></div>
        <div class="col-md-3 text-center"><strong>Last Name</strong></div>
        <div class="col-md-3 text-center"><strong>Email</strong></div>
    </div>
        <c:forEach var="user" items="${users}">
            <div class="row" style="border:1px solid green;padding:10px">
            <div class="col-md-3 text-center">${user.firstName}</div>
            <div class="col-md-3 text-center" >${user.lastName}</div>
                <div class="col-md-3 text-center">${user.email}</div>
            </div>
        </c:forEach>

</div>
</div>
</body>
</html>