<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User management/Add</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/add" method="post">
    <p>First Name: <input type="text" name="firstName" value=""></p>
    <p>Last Name: <input type="text" name="lastName" value=""></p>
    <p>Date of Birth: <input name="dateOfBirth" value=""></p>
    <input type="submit" name="okButton" value="Ok">
    <input type="submit" name="cancelButton" value="Cancel">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert('${requestScope.error}')
    </script>
</c:if>
</body>
</html>