/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.servlets;

import com.zofia.hospital.dbmanagers.GeneralDBManager;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zofia
 */
@WebServlet("/jsp/MedicineController")
public class MedicineController extends HttpServlet {
    private GeneralDBManager manager = new GeneralDBManager();
    private Connection connection;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.connection = manager.dataBaseConnection();
        int action = Integer.valueOf(request.getParameter("action"));
        try {
            switch(action) {
                case 1:
                break;
            }
        } catch(Exception e) {
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    
    //Metodo encargado de avanzar hacia la pagina correspondiente mediante un request, response y path.
    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response); 
    }
}
