<%-- 
    Document   : room-crud
    Created on : Nov 19, 2019, 1:43:42 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <title>Rooms</title>
    </head>
    <body background="../img/background.jpg">

        <%@include file="../html/human-resources-header.html" %>
        
        <div class="card mb-3 dual-card">
            <div class="card-body">
                <h4 class="mb-3 text-center">Room</h4>
                <br>
                <a href="RoomController?action=2" class="btn btn-light links" role="button">Register Room</a>
                <table class="table table-hover">
                        <thead class="thead-color">
                            <tr>
                                <th scope="col">Room Identification</th>
                                <th scope="col">Maintenance Price</th>
                                <th scope="col">Is Available</th>
                                <th scope="col">Is Active</th>
                                <th scope="col">Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="room" items="${requestScope.rooms}" >
                                <tr>
                                    <td>${room.idRoom}</td>
                                    <td>Q${room.maintenancePrice}</td>
                                    <td>
                                        <c:if test="${room.available}">
                                            Yes
                                        </c:if>     
                                        <c:if test="${not room.available}">
                                            No
                                        </c:if>
                                    </td>
                                    <td>
                                        <c:if test="${room.active}">
                                            Yes
                                        </c:if>     
                                        <c:if test="${not room.active}">
                                            No
                                        </c:if>
                                    </td>
                                    <td>
                                        <a href="RoomController?action=4&room=${room.idRoom}" class="btn btn-link btn-sm" role="button">Edit</a>
                                        <c:if test="${room.active}">
                                            <a href="RoomController?action=5&room=${room.idRoom}" class="btn btn-link btn-sm" role="button">Disable</a>  
                                        </c:if>     
                                        <c:if test="${not room.active}">
                                            <a href="RoomController?action=6&room=${room.idRoom}" class="btn btn-link btn-sm" role="button">Enable</a>
                                        </c:if>
                                    </td>
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
