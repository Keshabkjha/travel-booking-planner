package com.travelplanner.controller;

import com.travelplanner.dao.DestinationDAO;

import com.travelplanner.model.Destination;
import com.travelplanner.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        if (keyword == null) keyword = "";
        try (Connection conn = DBConnection.getConnection()) {
            DestinationDAO dao = new DestinationDAO(conn);
            List<Destination> results = dao.searchDestinations(keyword);
            request.setAttribute("results", results);
            request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Search failed: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
        }
    }
}
