<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>${book.id == null ? 'Create' : 'Edit'} Book</h2>

<form:form action="${book.id == null ? '/books' : '/books/'.concat(book.id)}" method="post" modelAttribute="book">
    <div class="form-group">
        <form:label path="title">Title</form:label>
        <form:input path="title" class="form-control" required="true" />
    </div>
    
    <div class="form-group">
        <label for="authorId">Author</label>
        <select name="authorId" id="authorId" class="form-control" required>
            <option value="">-- Select Author --</option>
            <c:forEach items="${authors}" var="author">
                <option value="${author.id}" ${book.author.id == author.id ? 'selected' : ''}>${author.name}</option>
            </c:forEach>
        </select>
    </div>
    
    <div class="form-group">
        <form:label path="isbn">ISBN</form:label>
        <form:input path="isbn" class="form-control" />
    </div>
    
    <div class="form-group">
        <form:label path="description">Description</form:label>
        <form:textarea path="description" class="form-control" rows="4" />
    </div>
    
    <div class="form-group">
        <form:label path="publicationDate">Publication Date</form:label>
        <form:input path="publicationDate" type="date" class="form-control" />
    </div>
    
    <div class="form-group">
        <form:label path="price">Price</form:label>
        <form:input path="price" type="number" step="0.01" class="form-control" />
    </div>
    
    <button type="submit" class="btn btn-primary">Save</button>
    <a href="<c:url value='/books' />" class="btn">Cancel</a>
</form:form>

<%@ include file="../common/footer.jsp" %> 