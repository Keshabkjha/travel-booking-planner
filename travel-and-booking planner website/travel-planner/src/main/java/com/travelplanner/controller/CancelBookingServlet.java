package com.travelplanner.controller;

import com.travelplanner.dao.BookingDAO;
import com.travelplanner.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/cancelBooking")
public class CancelBookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingIdStr = request.getParameter("bookingId");
        if (bookingIdStr != null) {
            try (Connection conn = DBConnection.getConnection()) {
                BookingDAO bookingDAO = new BookingDAO(conn);
                int bookingId = Integer.parseInt(bookingIdStr);
                bookingDAO.deleteBooking(bookingId);
            } catch (Exception e) {
                // Optionally, set an error message in session/request
            }
        }
        response.sendRedirect("bookings");
    }
}
