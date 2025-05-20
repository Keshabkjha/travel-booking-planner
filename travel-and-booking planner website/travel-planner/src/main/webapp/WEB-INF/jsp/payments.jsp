<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.travelplanner.model.Flight" %>
<%@ page import="com.travelplanner.model.Trains" %>
<%@ page import="com.travelplanner.model.Bus" %>
<%@ page import="com.travelplanner.model.Hotel" %>
<%@ page session="true" %>
<%
    Object userObj = session.getAttribute("user");
    if (userObj == null) {
        response.sendRedirect("login.jsp");
        return;
    }
    String type = (String) request.getAttribute("type");
    int refId = (request.getAttribute("refId") != null) ? (Integer) request.getAttribute("refId") : -1;
    Flight flight = (Flight) request.getAttribute("flight");
    Trains train = (Trains) request.getAttribute("train");
    Bus bus = (Bus) request.getAttribute("bus");
    Hotel hotel = (Hotel) request.getAttribute("hotel");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Payment - Travel Planner</title>
    <link rel="stylesheet" href="../static/css/style.css">
    <style>
        body { background: #f7fafd; margin: 0; padding: 0; }
        .payment-section {
            max-width: 500px;
            margin: 3em auto;
            background: #fff;
            border-radius: 18px;
            box-shadow: 0 8px 32px rgba(41,128,185,0.10);
            padding: 2.5em 2em;
            text-align: center;
        }
        .payment-title {
            color: #2980b9;
            font-size: 2em;
            font-weight: bold;
            margin-bottom: 1.2em;
        }
        .summary {
            margin-bottom: 2em;
        }
        .summary img {
            width: 100%;
            max-width: 320px;
            border-radius: 12px;
            margin-bottom: 1em;
        }
        .pay-btn {
            background: #2980b9;
            color: #fff;
            border: none;
            padding: 14px 40px;
            border-radius: 8px;
            font-size: 1.1em;
            font-weight: bold;
            cursor: pointer;
            transition: background 0.3s;
        }
        .pay-btn:hover {
            background: #6dd5fa;
            color: #2980b9;
        }
        .price {
            font-size: 1.2em;
            font-weight: bold;
            margin-bottom: 1em;
        }
    </style>
    <!-- Font Awesome for icons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>
<body>
    <div class="payment-section">
        <div class="payment-title">
            <i class="fa fa-credit-card"></i> Payment
        </div>
        <div class="summary">
            <% if ("flight".equals(type) && flight != null) { %>
                <img src="<%= flight.getImageUrl() %>" alt="<%= flight.getAirline() %>">
                <h3><%= flight.getAirline() %></h3>
                <p>
                    <i class="fa fa-plane-departure"></i> <b>From:</b> <%= flight.getDeparture() %><br>
                    <i class="fa fa-plane-arrival"></i> <b>To:</b> <%= flight.getArrival() %>
                </p>
                <div class="price">$<%= flight.getPrice() %></div>
            <% } else if ("train".equals(type) && train != null) { %>
                <img src="<%= train.getImageUrl() %>" alt="<%= train.getOperator() %>">
                <h3><%= train.getOperator() %></h3>
                <p>
                    <i class="fa fa-train"></i> <b>From:</b> <%= train.getDeparture() %><br>
                    <i class="fa fa-map-marker-alt"></i> <b>To:</b> <%= train.getArrival() %>
                </p>
                <div class="price">$<%= train.getPrice() %></div>
            <% } else if ("bus".equals(type) && bus != null) { %>
                <img src="<%= bus.getImageUrl() %>" alt="<%= bus.getOperator() %>">
                <h3><%= bus.getOperator() %></h3>
                <p>
                    <i class="fa fa-bus"></i> <b>From:</b> <%= bus.getDeparture() %><br>
                    <i class="fa fa-map-marker-alt"></i> <b>To:</b> <%= bus.getArrival() %>
                </p>
                <div class="price">$<%= bus.getPrice() %></div>
            <% } else if ("hotel".equals(type) && hotel != null) { %>
                <img src="<%= hotel.getImageUrl() %>" alt="<%= hotel.getName() %>">
                <h3><%= hotel.getName() %></h3>
                <p><%= hotel.getAddress() %></p>
                <div class="price">$<%= hotel.getPrice() %> per night</div>
            <% } else { %>
                <div style="color:#e74c3c;">Invalid booking type or data not found.</div>
            <% } %>
        </div>
        <form action="${pageContext.request.contextPath}/pay" method="post">
            <input type="hidden" name="type" value="<%= type %>">
            <input type="hidden" name="refId" value="<%= refId %>">
            <button class="pay-btn" type="submit"><i class="fa fa-check"></i> Pay and Book</button>
        </form>
        <div style="color:#e74c3c; margin-top:1em;"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></div>
    </div>
</body>
</html>