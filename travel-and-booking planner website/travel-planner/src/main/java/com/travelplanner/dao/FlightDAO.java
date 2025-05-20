package com.travelplanner.dao;

import com.travelplanner.model.Flight;
import java.sql.*;
import java.util.*;

public class FlightDAO {
    private Connection conn;

    public FlightDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Flight> getAllFlights() throws SQLException {
        String sql = "SELECT * FROM flights";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            flights.add(mapFlight(rs));
        }
        return flights;
    }

    public List<Flight> getFlightsByDestination(int destinationId) throws SQLException {
        String sql = "SELECT * FROM flights WHERE destination_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, destinationId);
        ResultSet rs = stmt.executeQuery();
        List<Flight> flights = new ArrayList<>();
        while (rs.next()) {
            flights.add(mapFlight(rs));
        }
        return flights;
    }

    public Flight getFlightById(int id) throws SQLException {
        String sql = "SELECT * FROM flights WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return mapFlight(rs);
        }
        return null;
    }

    private Flight mapFlight(ResultSet rs) throws SQLException {
        Flight f = new Flight();
        f.setId(rs.getInt("id"));
        f.setDestinationId(rs.getInt("destination_id"));
        f.setAirline(rs.getString("airline"));
        f.setDeparture(rs.getString("departure"));
        f.setArrival(rs.getString("arrival"));
        f.setPrice(rs.getDouble("price"));
        f.setImageUrl(rs.getString("image_url"));
        return f;
    }
}