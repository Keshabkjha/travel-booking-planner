package com.travelplanner.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.travelplanner.service.DBConnection;

@WebServlet("/reset-password")
public class ResetPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String email = (String) request.getSession().getAttribute("email");

        // Update password in DB (you'll need to hash it ideally)
		try (Connection conn = DBConnection.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("UPDATE users SET password = ? WHERE email = ?");
	        stmt.setString(1, newPassword);
	        stmt.setString(2, email);
	        stmt.executeUpdate();

	        response.sendRedirect(request.getContextPath() + "/login");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}