<%-- 
    Document   : medicine-crud
    Created on : Nov 19, 2019, 9:08:32 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <title>Medicine</title>
    </head>
    <body background="img/background.jpg">
        
        <%@include file="../html/pharmacy-header.html" %>

        <div class="card mb-3 dual-card">
            <div class="card-body">
                <h4 class="mb-3 text-center">Medicine</h4>
                <br>
                <a href="medicine-registration.jsp" class="btn btn-light links" role="button">Register Medicine</a>
                <table class="table table-hover">
                        <thead class="thead-color">
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
                                <tr>
                                    <td>${medicine.idMedicine}</td>
                                    <td>${medicine.name}</td>
                                    <td>${medicine.minimumAmount}</td>
                                    <td>${medicine.amount}</td>
                                    <td>Q${medicine.price}</td>
                                    <td>
                                        <a href="/Hospital/jsp/MedicineController?action=4&id=${medicine.idMedicine}" class="btn btn-link btn-sm" role="button">Edit</a>                                      
                                        <a href="/Hospital/jsp/MedicineController?action=6&id=${medicine.idMedicine}" class="btn btn-link btn-sm" role="button">Purchase</a>                                      
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
