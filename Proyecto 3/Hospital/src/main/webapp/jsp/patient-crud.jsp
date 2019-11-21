<%-- 
    Document   : patient-crud
    Created on : Nov 20, 2019, 5:14:35 PM
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
        
        <%@include file="../html/checkin-header.html" %>

        <div class="modal fade" id="InfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Fecha de ingreso</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form action="PatientController?action=6&id=${patient.cui}" method="POST">
                        <div class="modal-body">
                        <div class="mb-3">                           
                            <input type="date" class="form-control" id="date" name="date" required="">
                            <input type="text" class="form-control" value="${patient.cui}" name="id" hidden="">                          
                        </div>
                        <button type="submit" class="btn btn-light btn-submit">Submit</button>
                    </div>                    
                    </form>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="card mb-3 dual-card">
            <div class="card-body">
                <h4 class="mb-3 text-center">Patient</h4>
                <br>
                <a href="patient-registration.jsp" class="btn btn-light links" role="button">Register Patient</a>
                <table class="table table-hover">
                        <thead class="thead-color">
                            <tr>
                                <th scope="col">Patient CUI</th>
                                <th scope="col">Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Action</th>
                                <th scope="col">Other</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="patient" items="${sessionScope.patients}" >
                                <tr>
                                    <td>${patient.cui}</td>
                                    <td>${patient.name}</td>
                                    <td>${patient.lastName}</td>
                                    <td>
                                        <a href="/Hospital/jsp/PatientController?action=5&id=${patient.cui}" class="btn btn-link btn-sm" role="button">Edit</a>                                      
                                    </td>
                                    <td>
                                        <a href="/Hospital/jsp/PatientController?action=10&id=${patient.cui}" class="btn btn-link btn-sm" role="button">Internar</a>                                      
                                        <a href="/Hospital/jsp/PatientController?action=11&id=${patient.cui}" class="btn btn-link btn-sm" role="button">Registrar Consulta</a>                                      
                                    </td>
                                </tr>
                            </c:forEach>
                     </tbody>
                </table>
            </div>
        </div>
        
        <%@include file="../html/general-footer.html" %>
        <%@include file="../html/scripts.html" %>
        
        <c:if test="${requestScope['error'] != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#InfoModal').modal('show');
                });
            </script>
        </c:if>
            
    </body>
</html>
