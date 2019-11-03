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
        <link rel="stylesheet" type="text/css" href="css/stylesheet.css">
        
        <title>GT Hospital</title>
    </head>
    <body background="img/background.jpg">
        
        <nav id="navbar" class="navbar navbar-light" style="background-color: #7FA9C1">
          <a class="navbar-brand" href="index.jsp">
              <img src="img/hospital.png" style=" width: 10%" alt="General Hospital">
              GT Hospital
          </a>
        </nav>
        
        <div class="card mb-3 log-in">
            <img src="img/hospital.png" class="logotype" alt="icon">
            <form action="SignInController" method="GET">
                <div class="card-body">
                    <h1 class="h3 font-weight-normal text-center">Sign in</h1>
                    <label for="inputUsername" class="sr-only">Username</label>
                    <input type="text" id="inputUsername" name="inputUsername" class="form-control" placeholder="Username" required="" autofocus="">
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required="">
                    <br>
                    <button type="submit" class="btn btn-light btn-sign">Sign in</button>
                </div>
            </form>
        </div>
        
        <footer class="page-footer fixed-bottom">
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
        
        <c:if test="${requestScope['error'] != null}">
            <script type="text/javascript">
                $(document).ready(function () {
                    $('#InfoModal').modal('show');
                });
            </script>
        </c:if>

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
                        Password Incorrecto, intentelo de nuevo
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-light" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        
       <%@include file="html/scripts.html"%> 
        
    </body>
</html>
