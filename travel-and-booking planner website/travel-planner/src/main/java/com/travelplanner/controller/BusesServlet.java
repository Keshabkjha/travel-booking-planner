package com.travelplanner.controller;

import com.travelplanner.dao.BusDAO;
import com.travelplanner.model.Bus;
import com.travelplanner.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/buses")
public class BusesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            BusDAO busDAO = new BusDAO(conn);
            List<Bus> buses = busDAO.getAllBuses();
            request.setAttribute("buses", buses);
            request.getRequestDispatcher("WEB-INF/jsp/buses.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Could not load buses: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/buses.jsp").forward(request, response);
        }
    }
}
