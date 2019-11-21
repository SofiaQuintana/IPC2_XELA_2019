<%-- 
    Document   : employee-assignment
    Created on : Nov 21, 2019, 12:48:29 AM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <title>Employee Assignment</title>
    </head>
    <body background="img/background.jpg">
        
        <%@include file="../html/checkin-header.html" %>
        
        <div class="modal fade" id="InfoModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Error</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        ${requestScope.message}
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="card mb-3 log-in">           
            <div class="card-body"> 
                <h4 class="mb-3 text-center">Doctor/Nurse Assignment</h4>
                <a href="/Hospital/jsp/PatientController?action=14&id=${patient.cui}" class="btn btn-light links" role="button">Surgery</a>
                <form class="needs-validation" action="PatientController?action=8&patientCui=${patient.cui}" method="POST" novalidate="">
                    <div class="mb-3">
                        <label for="patientCui">Patient CUI</label>
                        <input type="text" class="form-control" id="patientCui" value="${patient.cui}" name="patientCui" disabled="">                    
                    </div>
                    <div class="mb-3">
                        <label for="doctor">Doctor/Nurse</label>
                        <select class="custom-select d-block w-100" id="doctor" name="doctor" required="">
                            <option value="">Choose...</option>
                            <c:forEach var="item" items="${sessionScope.employees}">
                                    <option value="${item.cui}">${item.cui}-${item.name} ${item.lastName}</option>
                            </c:forEach>
                            <!editar forEach RECORDAR> 
                        </select>
                        <div class="invalid-feedback">
                          Please select a valid doctor.
                        </div>   
                    </div> 
                    <div class="mb-3">
                        <label for="specialist">Specialist (Optional)</label>
                        <select class="custom-select d-block w-100" id="specialist" name="specialist" >
                            <option value="">Choose...</option>
                            <c:forEach var="item" items="${specialists}">
                                <c:if test="${item.active}">
                                    <option value="${item.cui}">${item.cui}-${item.name} ${item.lastName}</option>
                                </c:if>
                            </c:forEach>
                            <!editar forEach RECORDAR> 
                        </select>                        
                    </div> 
                    <div class="mb-3">                           
                            <label for="date">Consultation Date</label>
                            <input type="date" class="form-control" id="date" name="date" value="${date}" required="">
                            <div class="invalid-feedback">
                                Please select a valid date.
                            </div>  
                    </div>
                    <br>
                    <button type="submit" class="btn btn-light btn-submit">Submit</button>
                </form>                    
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

        <script>
        (function() {
          'use strict';
          window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
              form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                  event.preventDefault();
                  event.stopPropagation();
                }
                form.classList.add('was-validated');
              }, false);
            });
          }, false);
        })();
        </script>
    </body>
</html>
