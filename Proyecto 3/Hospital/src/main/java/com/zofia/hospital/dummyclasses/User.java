/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zofia.hospital.dummyclasses;

import javax.servlet.http.HttpServletRequest;
/**
 *
 * @author zofia
 */
public class User {
    private String username;
    private String cui;
    private String password;

    public User(HttpServletRequest request, String cui) { //Constructor a base de request.
        this.username = request.getParameter("username");
        this.cui = cui;
        this.password = request.getParameter("password");
    }
    
    public User(String username, String cui, String password) {
        this.username = username;
        this.cui = cui;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCui() {
        return cui;
    }

    public void setCui(String cui) {
        this.cui = cui;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
