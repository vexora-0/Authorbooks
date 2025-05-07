<%@ include file="common/header.jsp" %>

<h2>Welcome to the Bookstore Application</h2>
<p>This application allows you to manage books and authors in a bookstore.</p>

<div style="display: flex; justify-content: space-around; margin-top: 30px;">
    <div style="text-align: center; padding: 20px; background: #f9f9f9; border-radius: 5px; box-shadow: 0 0 5px rgba(0,0,0,0.1); width: 45%;">
        <h3>Authors</h3>
        <p>Manage the authors in the bookstore.</p>
        <a href="<c:url value='/authors' />" class="btn btn-primary">View Authors</a>
    </div>
    
    <div style="text-align: center; padding: 20px; background: #f9f9f9; border-radius: 5px; box-shadow: 0 0 5px rgba(0,0,0,0.1); width: 45%;">
        <h3>Books</h3>
        <p>Manage the books in the bookstore.</p>
        <a href="<c:url value='/books' />" class="btn btn-primary">View Books</a>
    </div>
</div>

<%@ include file="common/footer.jsp" %> 