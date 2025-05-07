<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Authors</h2>
<a href="<c:url value='/authors/new' />" class="btn btn-success">Add New Author</a>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Bio</th>
            <th>Books Count</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${authors}" var="author">
            <tr>
                <td>${author.id}</td>
                <td>${author.name}</td>
                <td>${author.email}</td>
                <td>${author.bio}</td>
                <td>${author.books.size()}</td>
                <td>
                    <a href="<c:url value='/authors/${author.id}/edit' />" class="btn">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %> 