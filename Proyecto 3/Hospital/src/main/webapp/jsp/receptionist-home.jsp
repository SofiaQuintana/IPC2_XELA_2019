<%-- 
    Document   : receptionist-home
    Created on : Nov 20, 2019, 3:37:56 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
            <a class="navbar-brand" href="/Hospital/jsp/PatientController?action=1">
                <img src="../img/hospital.png" style=" width: 10%" alt="General Hospital">
                GT Hospital
            </a>
            <div class="dropdown">
                <button class="btn btn-outline-light" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Check-In
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu2">
                    <a class="dropdown-item" href="/Hospital/jsp/PatientController?action=2">Patient Registration</a>
                    <a class="dropdown-item" href="/Hospital/jsp/medicine-selling-registration.jsp">Medic Consultation Registration</a>
                    <a class="dropdown-item" href="#">Assign Nurse/Doctor</a>
                </div>
            </div> 
        </nav>
        
        <div class="card mb-3 dual-card">
            <div class="card-body">
                <h4 class="mb-3 text-center">Pacientes Internados</h4>
                <br>
                <table class="table alertTable table-hover">
                    <thead class="alertHeader">
                        <tr>
                            <th scope="col">Patient CUI</th>
                            <th scope="col">Room</th>
                            <th scope="col">Initial Date</th>
                            <th scope="col">Action</th>
                            <th scope="col">Other</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="patient" items="${sessionScope.admitted}" >
                            <tr>
                                <td>${patient.patientCui}</td>
                                <td>${patient.idRoom}</td>
                                <td>${patient.initialDate}</td>
                                <td>
                                    <a href="/Hospital/jsp/PatientController?action=13&id=${patient.patientCui}" class="btn btn-link btn-sm" role="button">Assign Doctor/Nurse</a>                                      
                                </td>
                                <td>
                                    <a href="/Hospital/jsp/PatientController?action=15&id=${patient.patientCui}" class="btn btn-link btn-sm" role="button">Facturar</a>                                      
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <footer class="page-footer">
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
