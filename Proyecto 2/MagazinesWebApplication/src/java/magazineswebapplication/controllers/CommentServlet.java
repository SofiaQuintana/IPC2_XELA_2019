/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package magazineswebapplication.controllers;

import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import magazineswebapplication.dbmanagers.DataBaseController;

/**
 *
 * @author zofia
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet{
    private DataBaseController dataBase = new DataBaseController();
    private Connection connection;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.connection = dataBase.connectToDataBase();
    }
}
