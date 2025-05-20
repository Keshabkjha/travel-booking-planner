package com.travelplanner.controller;

import com.travelplanner.dao.*;
import com.travelplanner.model.*;
import com.travelplanner.service.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/pay")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        String idStr = null;
        Object bookingObj = null;

        try (Connection conn = DBConnection.getConnection()) {
            if ("hotel".equals(type)) {
                idStr = request.getParameter("hotelId");
                if (idStr != null) {
                    HotelDAO hotelDAO = new HotelDAO(conn);
                    Hotel hotel = hotelDAO.getHotelById(Integer.parseInt(idStr));
                    bookingObj = hotel;
                    request.setAttribute("hotel", hotel);
                }
            } else if ("flight".equals(type)) {
                idStr = request.getParameter("flightId");
                if (idStr != null) {
                    FlightDAO flightDAO = new FlightDAO(conn);
                    Flight flight = flightDAO.getFlightById(Integer.parseInt(idStr));
                    bookingObj = flight;
                    request.setAttribute("flight", flight);
                }
            } else if ("train".equals(type)) {
                idStr = request.getParameter("trainId");
                if (idStr != null) {
                    TrainDAO trainDAO = new TrainDAO(conn);
                    Trains train = trainDAO.getTrainById(Integer.parseInt(idStr));
                    bookingObj = train;
                    request.setAttribute("train", train);
                }
            } else if ("bus".equals(type)) {
                idStr = request.getParameter("busId");
                if (idStr != null) {
                    BusDAO busDAO = new BusDAO(conn);
                    Bus bus = busDAO.getBusById(Integer.parseInt(idStr));
                    bookingObj = bus;
                    request.setAttribute("bus", bus);
                }
            }

            if (type == null || idStr == null || bookingObj == null) {
                request.setAttribute("error", "Invalid booking type or data not found.");
            } else {
                request.setAttribute("type", type);
                request.setAttribute("refId", Integer.parseInt(idStr));
            }
            request.getRequestDispatcher("WEB-INF/jsp/payments.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("error", "Could not load booking: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/payments.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String type = request.getParameter("type");
        String refIdStr = request.getParameter("refId");
        if (type == null || refIdStr == null) {
            request.setAttribute("error", "Invalid booking type or reference.");
            request.getRequestDispatcher("WEB-INF/jsp/payments.jsp").forward(request, response);
            return;
        }

        int refId;
        try {
            refId = Integer.parseInt(refIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid reference ID.");
            request.getRequestDispatcher("WEB-INF/jsp/payments.jsp").forward(request, response);
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            BookingDAO bookingDAO = new BookingDAO(conn);
            Booking booking = new Booking();
            booking.setUserId(user.getId());
            booking.setType(type);
            booking.setRefId(refId);
            booking.setStatus("confirmed");
            booking.setPaymentId("SIMULATED_PAYMENT");
            bookingDAO.addBooking(booking);
            response.sendRedirect("bookings");
        } catch (Exception e) {
            request.setAttribute("error", "Booking failed: " + e.getMessage());
            request.getRequestDispatcher("WEB-INF/jsp/payments.jsp").forward(request, response);
        }
    }
}