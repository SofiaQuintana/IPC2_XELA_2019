<%-- 
    Document   : index
    Created on : Nov 1, 2019, 9:57:46 PM
    Author     : zofia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="html/stylesheets.html" %>
        <title>General Hospital</title>
    </head>
    <body>
        <nav id="navbar" class="navbar navbar-light" style="background-color: #90c6dc">
          <a class="navbar-brand text-muted" href="index.jsp">
              <img src="img/hospital.png" style=" width: 10%" alt="General Hospital">
              General Hospital
          </a>
        </nav>
       <%@include file="html/scripts.html"%> 
        
    </body>
</html>
