<%-- 
    Document   : magazine
    Created on : Oct 6, 2019, 10:04:45 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="stylesheets.html" %>
        <title>Magazine</title>
    </head>
    <body background="./background.jpg">
        <%@include file="scripts.html"%> 
        
        <c:if test="${Type == 1}">
            <%@include file="subscriptor-header.html"%>               
        </c:if>
        <c:if test="${Type == 2}">
            <%@include file="editor-header-form.html"%>
        </c:if>        
        <c:if test="${Type == 3}">
            <%@include file="manager-header-form.html"%>               
        </c:if>
        
        <script type="text/javascript">
            var user = "<%="Signed in as: "+ session.getAttribute("User")%>";
                $(document).ready(function () {
                    $('#dropdownMenu2').text(user);
                });
        </script>
           
        <c:forEach var="magazines" items="${requestScope.magazine}">
            <c:forEach var="versions" items="${requestScope.version}">
                
                <div class="card text-white bg-dark mb-3" id="profile">
                    <img src="./pdf.png" class="card-img" alt="Magazine Pdf" id="magazine-image">
                    <div class="card-body" id="magazine-body">
                        <h3 class="card-title">${magazines.name} Tomo ${versions.version}</h3>
                        <p class="card-text">Autor: ${magazines.autor}</p>
                        <p class="card-text">Descripcion: ${magazines.description}</p>      
                        <a href="http://localhost:8080/static/PDF/${versions.username}/${versions.pdfUrl}" download>Download: ${magazines.name}</a>
                    </div>                      
                    <div class="card-footer bg-transparent ">
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">Comment:</span>
                            </div>
                                <textarea class="form-control" aria-label="With textarea"></textarea>
                            </div>
                    </div>
                </div>               
            </c:forEach>
            
        </c:forEach>
        
    </body>
</html>
