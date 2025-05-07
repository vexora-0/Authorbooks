<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Books</h2>
<a href="<c:url value='/books/new' />" class="btn btn-success">Add New Book</a>

<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>ISBN</th>
            <th>Publication Date</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.author.name}</td>
                <td>${book.isbn}</td>
                <td>${book.publicationDate}</td>
                <td>$${book.price}</td>
                <td>
                    <a href="<c:url value='/books/${book.id}/edit' />" class="btn">Edit</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<%@ include file="../common/footer.jsp" %> 