package com.travelplanner.controller;

import com.travelplanner.dao.FlightDAO;
import com.travelplanner.model.Flight;
import com.travelplanner.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection conn = DBConnection.getConnection()) {
            FlightDAO flightDAO = new FlightDAO(conn);
            List<Flight> flights = flightDAO.getAllFlights();
            request.setAttribute("flights", flights);
            request.getRequestDispatcher("WEB-INF/jsp/flights.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Could not load flights: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/flights.jsp").forward(request, response);
        }
    }
}
