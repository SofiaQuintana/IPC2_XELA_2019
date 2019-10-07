/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;
import magazineswebapplication.dbmanagers.UserDBManager;
import magazineswebapplication.dummyclasses.Magazine;
import magazineswebapplication.dummyclasses.User;

/**
 *
 * @author zofia
 */
@WebServlet("/EditorServlet")
public class EditorServlet extends HttpServlet{
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    private static final String MAGAZINES_QUERY = "SELECT * FROM Post WHERE Username = '";   
    private static final String QUERY = "SELECT * FROM Magazine;";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        List<Magazine> temporal = new ArrayList<>();
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        UserDBManager manager = new UserDBManager(this.connection);
        User user = manager.getUserInList(request.getSession().getAttribute("User").toString());
        String query = MAGAZINES_QUERY + user.getUsername() + "';";
        temporal = magazine.getMagazineByPost(magazine.getVersions(query), magazine.getMagazines(QUERY));
        request.setAttribute("UploadedMagazines", temporal);
        RequestDispatcher dispatcher = request.getRequestDispatcher("uploaded-magazines.jsp");
        dispatcher.forward(request, response);
    }
}
