<%-- 
    Document   : specialist-crud
    Created on : Nov 18, 2019, 11:11:54 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <title>Specialists</title>
    </head>
    <body background="../img/background.jpg">

        <%@include file="../html/human-resources-header.html" %>
        
        <div class="card mb-3 dual-card">
            <div class="card-body">
                <h4 class="mb-3 text-center">Specialist</h4>
                <br>
                <a href="SpecialistController?action=2" class="btn btn-light links" role="button">Register Specialist</a>
                <table class="table table-hover">
                        <thead class="thead-color">
                            <tr>
                                <th scope="col">CUI Identification</th>
                                <th scope="col">Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Is Active</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="specialist" items="${requestScope.specialists}" >
                                <tr>
                                    <td>${specialist.cui}</td>
                                    <td>${specialist.name}</td>
                                    <td>${specialist.lastName}</td>
                                    <td>${specialist.active}</td>
                                    <c:if test="${specialist.active}">
                                        <td>
                                            <a href="SpecialistController?action=4&cui=${specialist.cui}" class="btn btn-link btn-sm" role="button">Realizar Pago</a>
                                        </td>
                                    </c:if>                                       
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </div>
        
        <%@include file="../html/general-footer.html" %>
        <%@include file="../html/scripts.html" %>
        
    </body>
</html>
