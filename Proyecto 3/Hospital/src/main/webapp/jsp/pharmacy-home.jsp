<%-- 
    Document   : pharmacy-home
    Created on : Nov 19, 2019, 6:56:34 PM
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
            <a class="navbar-brand" href="/Hospital/jsp/MedicineController?action=1">
                <img src="../img/hospital.png" style=" width: 10%" alt="General Hospital">
                GT Hospital
            </a>
            <div class="dropdown">
                <button class="btn btn-outline-light" type="button" id="dropdownMenu2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Pharmacy
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="dropdownMenu2">
                    <a class="dropdown-item" href="/Hospital/jsp/MedicineController?action=3">Medicine Registration</a>
                    <a class="dropdown-item" href="/Hospital/jsp/medicine-selling-registration.jsp">Medicine Selling Registration</a>
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
                <h4 class="mb-3 text-center">Purchasing Alerts</h4>
                <br>
                <table class="table alertTable table-hover">
                    <thead class="alertHeader">
                        <tr>
                            <th scope="col">Medicine Id</th>
                            <th scope="col">Name</th>
                            <th scope="col">Minimum Amount</th>
                            <th scope="col">Amount</th>
                            <th scope="col">Price</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="medicine" items="${sessionScope.medicines}" >
                            <c:if test="${medicine.amount < medicine.minimumAmount }">
                                <tr>
                                    <td>${medicine.idMedicine}</td>
                                    <td>${medicine.name}</td>
                                    <td>${medicine.minimumAmount}</td>
                                    <td>${medicine.amount}</td>
                                    <td>Q${medicine.price}</td>
                                    <td>
                                        <a href="/Hospital/jsp/MedicineController?action=6&id=${medicine.idMedicine}" class="btn btn-link btn-sm" role="button">Purchase</a>                                      
                                    </td>
                                </tr>
                            </c:if>                               
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
