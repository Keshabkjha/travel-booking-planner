<%@ page contentType="text/html;charset=UTF-8" %>

<%@ page import="com.travelplanner.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.travelplanner.dao.DestinationDAO" %>
<%@ page import="com.travelplanner.model.Destination" %>
<%@ page import="com.travelplanner.dao.BookingDAO" %>
<%@ page import="com.travelplanner.model.Booking" %>
<%@ page import="com.travelplanner.service.DBConnection" %>
<%@ page session="true" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null || !"admin".equals(user.getRole())) {
        response.sendRedirect("login.jsp");
        return;
    }
    List<Destination> destinations = null;
    List<Booking> bookings = null;
    try {
        DestinationDAO destDao = new DestinationDAO(DBConnection.getConnection());
        destinations = destDao.getAllDestinations();
        BookingDAO bookingDao = new BookingDAO(DBConnection.getConnection());
        bookings = bookingDao.getBookingsByUser(-1); // -1 means get all bookings (see note below)
    } catch (Exception e) {
        destinations = null;
        bookings = null;
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Panel - Travel Planner</title>
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
        .admin-panel-section {
            max-width: 1100px;
            margin: 2.5em auto 0 auto;
            padding: 2em 1em;
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 8px 32px rgba(41,128,185,0.10);
        }
        .admin-title {
            color: #2980b9;
            font-size: 2em;
            font-weight: bold;
            margin-bottom: 1.2em;
            text-align: center;
        }
        .tabs {
            display: flex;
            justify-content: center;
            margin-bottom: 2em;
        }
        .tab-btn {
            background: #eaf6fb;
            color: #2980b9;
            border: none;
            padding: 12px 32px;
            font-size: 1.1em;
            font-weight: bold;
            border-radius: 8px 8px 0 0;
            margin: 0 0.5em;
            cursor: pointer;
            transition: background 0.2s, color 0.2s;
            outline: none;
        }
        .tab-btn.active, .tab-btn:hover {
            background: #2980b9;
            color: #fff;
        }
        .tab-content {
            display: none;
        }
        .tab-content.active {
            display: block;
            animation: fadeIn 0.5s;
        }
        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(20px);}
            to { opacity: 1; transform: translateY(0);}
        }
        .destinations-grid {
            display: flex;
            flex-wrap: wrap;
            gap: 2em;
            justify-content: center;
        }
        .destination-card {
            background: linear-gradient(120deg, #6dd5fa 0%, #2980b9 100%);
            color: #fff;
            border-radius: 16px;
            box-shadow: 0 4px 16px rgba(41,128,185,0.10);
            width: 260px;
            max-width: 90vw;
            margin-bottom: 1.5em;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 1.5em 1em 1.2em 1em;
        }
        .destination-card img {
            width: 100%;
            max-width: 220px;
            height: 130px;
            object-fit: cover;
            border-radius: 12px;
            margin-bottom: 1em;
            box-shadow: 0 2px 8px rgba(41,128,185,0.10);
        }
        .destination-card h3 {
            margin: 0.2em 0 0.5em 0;
            font-size: 1.3em;
            font-weight: bold;
        }
        .destination-card p {
            font-size: 1em;
            margin-bottom: 1.2em;
            color: #f0f6fa;
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
        .no-data {
            text-align: center;
            color: #888;
            font-size: 1.2em;
            margin-top: 2em;
        }
        @media (max-width: 700px) {
            .admin-panel-section {
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
    <script>
        function showTab(tab) {
            document.getElementById('destinationsTab').classList.remove('active');
            document.getElementById('bookingsTab').classList.remove('active');
            document.getElementById('destBtn').classList.remove('active');
            document.getElementById('bookBtn').classList.remove('active');
            document.getElementById(tab + 'Tab').classList.add('active');
            document.getElementById(tab + 'Btn').classList.add('active');
        }
        window.onload = function() {
            showTab('destinations');
        }
    </script>
</head>
<body>
    <div class="navbar">
        <div class="logo">Travel Planner</div>
        <ul>
            <li><a href="${pageContext.request.contextPath}/dashboard"><i class="fa fa-home"></i> Dashboard</a></li>
            <li><a href="${pageContext.request.contextPath}/search"><i class="fa fa-search"></i> Search</a></li>
            <li><a href="${pageContext.request.contextPath}/bookings"><i class="fa fa-suitcase"></i> My Bookings</a></li>
            <li><a href="${pageContext.request.contextPath}/admin_dashboard" class="active"><i class="fa fa-cogs"></i> Admin Panel</a></li>
        </ul>
        <div class="user-info">
            <span><i class="fa fa-user-circle"></i> <%= user.getName() %></span>
        </div>
    </div>
    <div class="admin-panel-section">
        <div class="admin-title">
            <i class="fa fa-cogs"></i> Admin Panel
        </div>
        <div class="tabs">
            <button class="tab-btn" id="destBtn" onclick="showTab('destinations')"><i class="fa fa-map-marked-alt"></i> Destinations</button>
            <button class="tab-btn" id="bookBtn" onclick="showTab('bookings')"><i class="fa fa-suitcase-rolling"></i> Bookings</button>
        </div>
        <!-- Destinations Tab -->
        <div class="tab-content" id="destinationsTab">
            <div class="destinations-grid">
                <%
                    if (destinations != null && !destinations.isEmpty()) {
                        for (Destination dest : destinations) {
                %>
                    <div class="destination-card">
                        <img src="<%= dest.getImageUrl() %>" alt="<%= dest.getName() %>">
                        <h3><%= dest.getName() %></h3>
                        <p><%= dest.getDescription() %></p>
                    </div>
                <%
                        }
                    } else {
                %>
                    <div class="no-data">
                        <i class="fa fa-map-marked-alt"></i> No destinations found.
                    </div>
                <%
                    }
                %>
            </div>
        </div>
        <!-- Bookings Tab -->
        <div class="tab-content" id="bookingsTab">
            <%
                if (bookings != null && !bookings.isEmpty()) {
            %>
            <table class="bookings-table">
                <tr>
                    <th>Booking ID</th>
                    <th>User ID</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Payment ID</th>
                </tr>
                <%
                    for (Booking b : bookings) {
                        String icon = "fa-question-circle";
                        if ("destination".equalsIgnoreCase(b.getType())) icon = "fa-map-marker-alt";
                        else if ("flight".equalsIgnoreCase(b.getType())) icon = "fa-plane";
                        else if ("hotel".equalsIgnoreCase(b.getType())) icon = "fa-hotel";
                        else if ("activity".equalsIgnoreCase(b.getType())) icon = "fa-hiking";
                %>
                <tr>
                    <td><%= b.getId() %></td>
                    <td><%= b.getUserId() %></td>
                    <td>
                        <i class="fa <%= icon %> type-icon"></i>
                        <%= b.getType().substring(0,1).toUpperCase() + b.getType().substring(1) %>
                    </td>
                    <td class="status-<%= b.getStatus().toLowerCase() %>">
                        <%= b.getStatus().substring(0,1).toUpperCase() + b.getStatus().substring(1) %>
                    </td>
                    <td><%= b.getPaymentId() %></td>
                </tr>
                <%
                    }
                %>
            </table>
            <%
                } else {
            %>
            <div class="no-data">
                <i class="fa fa-calendar-times"></i> No bookings found.
            </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
