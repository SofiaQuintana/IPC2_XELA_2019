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
        <div class="card mb-3 log-in">
            <form class="needs-validation was-validated" action="SignInController" method="GET" novalidate="">
                <div class="card-body"> 
                
                </div>
            </form>
        </div>
        
        
        <%@include file="../html/general-footer.html" %>
    </body>
</html>
