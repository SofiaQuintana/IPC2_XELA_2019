<%-- 
    Document   : human-resources-home
    Created on : Nov 4, 2019, 11:01:47 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css">
        
        <title>Home</title>
    </head>
    <body background="img/background.jpg">
        
        <nav id="navbar" class="navbar navbar-light" style="background-color: #7FA9C1">
            <a class="navbar-brand" href="/Hospital/jsp/EmployeeUserAreaController?action=3">
                <img src="../img/hospital.png" style=" width: 10%" alt="General Hospital">
                GT Hospital
            </a>
            <div class="dropdown">
                <button class="btn btn-outline-light" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Human Resources
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu2">
                    <a class="dropdown-item" href="/Hospital/jsp/employee-hiring.jsp">Hiring Registration</a>
                    <a class="dropdown-item" href="/Hospital/jsp/quit-fired-registration.jsp">Quit/Dismiss Registration</a>
                    <a class="dropdown-item" href="/Hospital/jsp/pay-rise-registration.jsp">Pay rise registration</a>
                    <a class="dropdown-item" href="#">Payment Registration</a>
                    <a class="dropdown-item" href="#">Vacation Registration</a>
                    <a class="dropdown-item" href="/Hospital/jsp/RoomController?action=1">Room Registration</a>
                    <a class="dropdown-item" href="/Hospital/jsp/SpecialistController?action=1">Specialist Registration</a>
                    <a class="dropdown-item" href="#">Room Registration</a>

                    <a class="dropdown-item" href="/Hospital/jsp/area-registration.jsp">Area Registration</a>
                </div>
            </div> 
        </nav>
        
        <div class="card mb-3 dual-card">
            <div class="card-body">
                <h4 class="mb-3 text-center">Employees</h4>
                <br>
                <table class="table table-hover">
                        <thead class="thead-color">
                            <tr>
                                <th scope="col">Employee CUI</th>
                                <th scope="col">Id Area</th>
                                <th scope="col">Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Salary</th>
                                <th scope="col">Discounts</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="employee" items="${employees}" >
                                <tr>
                                    <td>${employee.cui}</td>
                                    <td>${employee.idArea}</td>
                                    <td>${employee.name}</td>
                                    <td>${employee.lastName}</td>
                                    <td>Q${employee.salary}</td>
                                    <td>${employee.discount}</td>
                                    <td>
                                        <a href="/Hospital/jsp/EmployeeUserAreaController?action=1&cui=${employee.cui}" class="btn btn-link btn-sm" role="button">Edit</a>                                      
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
            </div>
        </div>
        
        <footer class="page-footer fixed-bottom">
            <div>
                <ul class="list-unstyled list-inline text-center">
                    <li class="list-inline-item btn-floating mx-1">
                        <a href="#">
                            <img class="logo" src="https://cdn2.iconfinder.com/data/icons/black-white-social-media/32/online_social_media_facebook-128.png">
                        </a>
                    </li>
                    <li class="list-inline-item btn-floating mx-1">
                        <a href="#">
                            <img class="logo" src="https://cdn2.iconfinder.com/data/icons/black-white-social-media/32/twitter_online_social_media-128.png">
                        </a>
                    </li>
                    <li class="list-inline-item btn-floating mx-1 ">
                        <a href="#">
                            <img class="logo" src="https://cdn2.iconfinder.com/data/icons/black-white-social-media/32/instagram_online_social_media_photo-128.png">
                        </a>
                    </li>
                    <li class="list-inline-item btn-floating mx-1">
                        <a href="#">
                            <img class="logo" src="https://cdn2.iconfinder.com/data/icons/black-white-social-media/32/online_social_media_google_plus-128.png">
                        </a>
                    </li>
                </ul>
            </div>
            <div class="copyright">
                <p> Copyright GT hospital &copy; 2019, All rights reserved. </p>
            </div>
        </footer>
        
        <%@include file="../html/scripts.html" %>
        
    </body>
</html>
