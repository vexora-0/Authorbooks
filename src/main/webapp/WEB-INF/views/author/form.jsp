<%@ include file="../common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h2>${author.id == null ? 'Create' : 'Edit'} Author</h2>

<form:form action="${author.id == null ? '/authors' : '/authors/'.concat(author.id)}" method="post" modelAttribute="author">
    <div class="form-group">
        <form:label path="name">Name</form:label>
        <form:input path="name" class="form-control" required="true" />
    </div>
    
    <div class="form-group">
        <form:label path="email">Email</form:label>
        <form:input path="email" class="form-control" type="email" />
    </div>
    
    <div class="form-group">
        <form:label path="bio">Bio</form:label>
        <form:textarea path="bio" class="form-control" rows="4" />
    </div>
    
    <button type="submit" class="btn btn-primary">Save</button>
    <a href="<c:url value='/authors' />" class="btn">Cancel</a>
</form:form>

<%@ include file="../common/footer.jsp" %> 