package com.travelplanner.dao;

import com.travelplanner.model.Bus;
import java.sql.*;
import java.util.*;

public class BusDAO {
    private Connection conn;

    public BusDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Bus> getAllBuses() throws SQLException {
        String sql = "SELECT * FROM buses";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Bus> buses = new ArrayList<>();
        while (rs.next()) {
            buses.add(mapBus(rs));
        }
        return buses;
    }

    public List<Bus> getBusesByDestination(int destinationId) throws SQLException {
        String sql = "SELECT * FROM buses WHERE destination_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, destinationId);
        ResultSet rs = stmt.executeQuery();
        List<Bus> buses = new ArrayList<>();
        while (rs.next()) {
            buses.add(mapBus(rs));
        }
        return buses;
    }

    public Bus getBusById(int id) throws SQLException {
        String sql = "SELECT * FROM buses WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return mapBus(rs);
        }
        return null;
    }

    private Bus mapBus(ResultSet rs) throws SQLException {
        Bus b = new Bus();
        b.setId(rs.getInt("id"));
        b.setDestinationId(rs.getInt("destination_id"));
        b.setOperator(rs.getString("operator"));
        b.setDeparture(rs.getString("departure"));
        b.setArrival(rs.getString("arrival"));
        b.setPrice(rs.getDouble("price"));
        b.setImageUrl(rs.getString("image_url"));
        return b;
    }
}