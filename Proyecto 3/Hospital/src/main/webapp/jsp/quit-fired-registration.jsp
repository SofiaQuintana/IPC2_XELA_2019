<%-- 
    Document   : quit-fired-registration
    Created on : Nov 13, 2019, 11:10:20 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <title>Quitted/Fired Registration</title>
    </head>
    <body background="../img/background.jpg">

        <%@include file="../html/human-resources-header.html" %>
        
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
                <h4 class="mb-3 text-center">Register Quit/Fired Employee </h4>
                <form class="needs-validation" action="EmployeeRecordController" method="GET" novalidate="">
                    <div class="mb-3">                                              
                        <label for="employeeCui">Employee CUI</label>
                        <select class="custom-select d-block w-100" id="employeeCui" name="employeeCui" required="">
                            <option value="">Choose...</option>
                            <c:forEach var="item" items="${sessionScope.employees}">
                                <option value="${item.cui}">${item.cui} - ${item.name} ${item.lastName}</option>
                            </c:forEach>
                            <!editar forEach RECORDAR> 
                        </select>
                        <div class="invalid-feedback">
                          Please select a valid cui.
                        </div>                    
                    </div>
                    <div class="mb-3">                                              
                        <label for="cause">Cause</label>
                        <select class="custom-select d-block w-100" id="cause" name="cause" required="">
                            <option value="">Choose...</option>
                            <option value="Renuncia">Quit</option>
                            <option value="Despido">Dismiss</option>
                        </select>
                        <div class="invalid-feedback">
                          Please select a valid cause.
                        </div>                    
                    </div>
                    <div class="mb-3">                                               
                        <label for="date">Cause date</label>
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
