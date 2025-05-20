package com.travelplanner.controller;

import com.travelplanner.dao.TrainDAO;
import com.travelplanner.model.Trains;
import com.travelplanner.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/trains")
public class TrainsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            TrainDAO trainDAO = new TrainDAO(conn);
            List<Trains> trains = trainDAO.getAllTrains();
            request.setAttribute("trains", trains);
            request.getRequestDispatcher("WEB-INF/jsp/trains.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Could not load trains: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/trains.jsp").forward(request, response);
        }
    }
}
