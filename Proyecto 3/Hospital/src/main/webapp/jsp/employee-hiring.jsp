<%-- 
    Document   : employee-hiring
    Created on : Nov 4, 2019, 10:12:20 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <title>Employee Hiring</title>
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
                <h4 class="mb-3 text-center">Register employee</h4>
                <form class="needs-validation" action="EmployeeController" method="GET" novalidate="">                   
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <input type="text" class="form-control" id="firstName" placeholder="First Name" name="name" value="" required="">
                            <div class="invalid-feedback">
                              Valid first name is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <input type="text" class="form-control" id="lastName" placeholder="Last Name" name="lastName" value="" required="">
                            <div class="invalid-feedback">
                              Valid last name is required.
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="cui">CUI</label>
                        <input type="text" class="form-control" id="cui" placeholder="CUI" name="cui" required="">
                        <div class="invalid-feedback">
                          Valid CUI is required.
                        </div>                        
                    </div>
                    <div class="row">
                        <div class="col-md-4 mb-3">                           
                            <label for="date">Hiring date</label>
                            <input type="date" class="form-control" id="date" name="date" value="${date}" required="">
                            <div class="invalid-feedback">
                                Please select a valid date.
                            </div>  
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="area">Area</label>
                            <select class="custom-select d-block w-100" id="area" name="area" required="">
                                <option value="">Choose...</option>
                                <c:forEach var="item" items="${sessionScope.areas}">
                                    <option value="${item}">${item}</option>
                                </c:forEach>
                                <!editar forEach RECORDAR> 
                            </select>
                            <div class="invalid-feedback">
                              Please select a valid area.
                            </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="salary">Salary</label>
                            <input type="text" class="form-control" id="salary" name="salary" placeholder="Salary" required="">
                            <div class="invalid-feedback">
                              Valid salary is required
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label for="discount">
                            IGSS Discount
                            <span class="text-muted">(Optional)</span>
                        </label>
                        <div class="input-group">                           
                            <input type="text" class="form-control" id="discount" name="discount" placeholder="Discount" value="0">
                            <div class="invalid-feedback" style="width: 100%;">
                                Valid discount is required.
                            </div>
                            <div class="input-group-append">
                                <span class="input-group-text">%</span>
                            </div>
                        </div>        
                        <small class="text-muted">Example: 5, 10, 20, etc</small>
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
