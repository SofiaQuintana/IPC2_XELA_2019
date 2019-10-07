/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;
import magazineswebapplication.dbmanagers.PostMagazineDBManager;

/**
 *
 * @author zofia
 */
@WebServlet("EditMagazineServlet")
public class EditMagazineServlet extends HttpServlet {
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    private static final String SELECT_MAGAZINE = "SELECT * FROM Magazine WHERE MagazineId = '";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
        PostMagazineDBManager magazine = new PostMagazineDBManager(this.connection);
        String magazineId = request.getParameter("r");
        request.setAttribute("editMagazine", magazine.getMagazines(SELECT_MAGAZINE + magazineId + "';")); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit-magazine.jsp");
        dispatcher.forward(request, response); 
    }
    
}
