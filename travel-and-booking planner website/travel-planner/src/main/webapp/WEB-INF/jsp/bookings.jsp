<%@ page contentType="text/html;charset=UTF-8" %>


<%@ page import="java.util.List" %>
<%@ page import="com.travelplanner.model.Booking" %>
<%@ page import="com.travelplanner.dao.BookingDAO" %>
<%@ page import="com.travelplanner.service.DBConnection" %>
<%@ page import="com.travelplanner.model.User" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("${pageContext.request.contextPath}/login");
        return;
    }
    List<Booking> bookings = null;
    try {
        BookingDAO bookingDAO = new BookingDAO(DBConnection.getConnection());
        bookings = bookingDAO.getBookingsByUser(user.getId());
    } catch (Exception e) {
        bookings = null;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>My Bookings - Travel Planner</title>
    <link rel="stylesheet" href="../static/css/style.css">
    <style>
        body {
            background: #f7fafd;
            margin: 0;
            padding: 0;
        }
        .navbar {
            width: 100%;
            background: linear-gradient(90deg, #2980b9 0%, #6dd5fa 100%);
            color: #fff;
            padding: 0.7em 0;
            box-shadow: 0 2px 8px rgba(41,128,185,0.08);
            display: flex;
            align-items: center;
            justify-content: space-between;
            position: sticky;
            top: 0;
            z-index: 100;
        }
        .navbar .logo {
            font-size: 1.7em;
            font-weight: bold;
            margin-left: 2em;
            letter-spacing: 2px;
        }
        .navbar ul {
            list-style: none;
            display: flex;
            margin: 0;
            padding: 0;
        }
        .navbar ul li {
            margin: 0 1.2em;
        }
        .navbar ul li a {
            color: #fff;
            text-decoration: none;
            font-size: 1.1em;
            font-weight: 500;
            transition: color 0.2s;
            padding: 6px 12px;
            border-radius: 6px;
        }
        .navbar ul li a:hover, .navbar ul li a.active {
            background: rgba(255,255,255,0.18);
            color: #fff;
        }
        .navbar .user-info {
            margin-right: 2em;
            font-size: 1em;
            font-weight: 500;
            display: flex;
            align-items: center;
        }
        .navbar .user-info span {
            margin-right: 1em;
        }
        .bookings-section {
            max-width: 900px;
            margin: 2.5em auto 0 auto;
            padding: 2em 1em;
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 8px 32px rgba(41,128,185,0.10);
        }
        .bookings-title {
            color: #2980b9;
            font-size: 2em;
            font-weight: bold;
            margin-bottom: 1.2em;
            text-align: center;
        }
        .bookings-table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
            border-radius: 12px;
            overflow: hidden;
            box-shadow: 0 2px 8px rgba(41,128,185,0.08);
        }
        .bookings-table th, .bookings-table td {
            padding: 14px 10px;
            text-align: left;
        }
        .bookings-table th {
            background: #2980b9;
            color: #fff;
            font-weight: 600;
            border: none;
        }
        .bookings-table tr {
            border-bottom: 1px solid #eaeaea;
        }
        .bookings-table tr:last-child {
            border-bottom: none;
        }
        .bookings-table td {
            color: #333;
            background: #f9fbfd;
        }
        .status-confirmed {
            color: #27ae60;
            font-weight: bold;
        }
        .status-cancelled {
            color: #e74c3c;
            font-weight: bold;
        }
        .status-pending {
            color: #f39c12;
            font-weight: bold;
        }
        .type-icon {
            font-size: 1.3em;
            margin-right: 0.5em;
        }
        .cancel-btn {
            background: #e74c3c;
            color: #fff;
            border: none;
            padding: 7px 18px;
            border-radius: 6px;
            font-size: 1em;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.2s;
        }
        .cancel-btn:hover {
            background: #c0392b;
        }
        .no-bookings {
            text-align: center;
            color: #888;
            font-size: 1.2em;
            margin-top: 2em;
        }
        @media (max-width: 700px) {
            .bookings-section {
                padding: 1em 0.2em;
            }
            .bookings-table th, .bookings-table td {
                padding: 10px 4px;
                font-size: 0.95em;
            }
        }
    </style>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
    <div class="navbar">
        <div class="logo">Travel Planner</div>
        <ul>
            <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-home"></i> Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/search"><i class="fa fa-search"></i> Search</a></li>
            <li><a href="${pageContext.request.contextPath}/bookings" class="active"><i class="fa fa-suitcase"></i> My Bookings</a></li>
        </ul>
        <div class="user-info">
            <span><i class="fa fa-user-circle"></i> <%= user.getName() %></span>
        </div>
    </div>
    <div class="bookings-section">
        <div class="bookings-title">
            <i class="fa fa-suitcase-rolling"></i> My Bookings
        </div>
        <%
            if (bookings != null && !bookings.isEmpty()) {
        %>
        <table class="bookings-table">
            <tr>
                <th>Booking ID</th>
                <th>Type</th>
                <th>Status</th>
                <th>Payment ID</th>
                <th>Action</th>
            </tr>
            <%
                for (Booking b : bookings) {
                    String icon = "fa-question-circle";
                    if ("destination".equalsIgnoreCase(b.getType())) icon = "fa-map-marker-alt";
                    else if ("flight".equalsIgnoreCase(b.getType())) icon = "fa-plane";
                    else if ("hotel".equalsIgnoreCase(b.getType())) icon = "fa-hotel";
                    else if ("activity".equalsIgnoreCase(b.getType())) icon = "fa-hiking";
                    else if ("bus".equalsIgnoreCase(b.getType())) icon = "fa-bus";
                    else if ("train".equalsIgnoreCase(b.getType())) icon = "fa-train";
            %>
            <tr>
                <td><%= b.getId() %></td>
                <td>
                    <i class="fa <%= icon %> type-icon"></i>
                    <%= b.getType().substring(0,1).toUpperCase() + b.getType().substring(1) %>
                </td>
                <td class="status-<%= b.getStatus().toLowerCase() %>">
                    <%= b.getStatus().substring(0,1).toUpperCase() + b.getStatus().substring(1) %>
                </td>
                <td><%= b.getPaymentId() %></td>
                <td>
                    <form action="${pageContext.request.contextPath}/cancelBooking" method="post" style="display:inline;">
                        <input type="hidden" name="bookingId" value="<%= b.getId() %>">
                        <button type="submit" class="cancel-btn" onclick="return confirm('Are you sure you want to cancel this booking?');">
                            <i class="fa fa-trash"></i> Cancel
                        </button>
                    </form>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <%
            } else {
        %>
        <div class="no-bookings">
            <i class="fa fa-calendar-times"></i> You have no bookings yet.
        </div>
        <%
            }
        %>
        <div style="text-align:center; margin-top:2em;">
            <a href="${pageContext.request.contextPath}/search" style="color:#2980b9; font-weight:bold; text-decoration:none;">
                <i class="fa fa-search-location"></i> Book a new trip
            </a>
        </div>
    </div>
</body>
</html>