<%-- 
    Document   : pay-rise-registration
    Created on : Nov 15, 2019, 8:13:42 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="../html/stylesheets.html" %>
        <title>Pay rise registration</title>
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
        
        <div class="card-group dual-card">
            <div class="card mb-3 single-card" id="singleCardDiv">           
                <div class="card-body"> 
                    <h4 class="mb-3 text-center">Current salaries</h4>
                    <table class="table table-hover">
                        <thead class="thead-color">
                            <tr>
                                <th scope="col">CUI Identification</th>
                                <th scope="col">Name</th>
                                <th scope="col">Last Name</th>
                                <th scope="col">Actual Salary</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="employee" items="${sessionScope.employees}" >
                                <tr>
                                    <td>${employee.cui}</td>
                                    <td>${employee.name}</td>
                                    <td>${employee.lastName}</td>
                                    <td>Q${employee.salary}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div> 
            <div class="card mb-3 single-card">           
                <div class="card-body"> 
                    <h4 class="mb-3 text-center">Employee pay rise registration</h4>
                    <form class="needs-validation" action="EmployeePaymentsController" method="GET" novalidate="">
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
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="increase">Increased Salary</label>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text">Q</span>
                                    </div>
                                    <input type="text" class="form-control" id="increase" placeholder="Increased Salary" name="increase" value="" required="">
                                    <div class="invalid-feedback">
                                      Valid salary is required.
                                    </div>
                                </div>                            
                            </div>
                            <div class="col-md-6 mb-3">                           
                                <label for="date">Pay rise date</label>                          
                                <input type="date" class="form-control" id="date" name="date" value="${date}" required="">      
                                <div class="invalid-feedback">
                                Please select a valid date.
                                </div>   
                            </div>
                        </div>
                        <br>
                        <button type="submit" class="btn btn-light btn-submit">Submit</button>
                    </form>
                </div>
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
