<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>User management/Browse</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/browse" method="post">
    <table id="UserTable" border=1>
        <tr>
            <th></th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Date of Birth</th>
        </tr>
        <c:forEach var="user" items="${sessionScope.users}">
            <tr>
                <td>
                    <input type="radio" name="id" id="id" value="${user.id}">
                </td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td><fmt:formatDate value="${user.dateOfBirth}" type="date" dateStyle="medium"/></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" name="addButton" value="Add">
    <input type="submit" name="editButton" value="Edit">
    <input type="submit" name="deleteButton" id="deleteButton" value="Delete">
    <input type="submit" name="detailsButton" value="Details">
</form>
<c:if test="${requestScope.error != null}">
    <script>
        alert('${requestScope.error != null}')
    </script>
</c:if>
<script>
    var deleteButton = document.getElementById("deleteButton");
    deleteButton.addEventListener("click", function (e) {
        if (!isCheck("id")) {
            e.preventDefault();
            alert("You have to select user");
            return;
        }
        if (!confirm("Do you really want to delete this user?")) {
            e.preventDefault();
        }
    });

    function isCheck(name) {
        return document.querySelector('input[name="' + name + '"]:checked');
    }
</script>
</body>
</html>