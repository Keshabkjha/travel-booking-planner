package com.travelplanner.dao;

import com.travelplanner.model.Destination;
import java.sql.*;
import java.util.*;

public class DestinationDAO {
    private Connection conn;

    public DestinationDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Destination> searchDestinations(String keyword) throws SQLException {
        String sql = "SELECT * FROM destinations WHERE name LIKE ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, "%" + keyword + "%");
        ResultSet rs = stmt.executeQuery();
        List<Destination> list = new ArrayList<>();
        while (rs.next()) {
            Destination d = new Destination();
            d.setId(rs.getInt("id"));
            d.setName(rs.getString("name"));
            d.setDescription(rs.getString("description"));
            d.setImageUrl(rs.getString("image_url"));
            list.add(d);
        }
        return list;
    }

    public Destination getDestinationById(int id) throws SQLException {
        String sql = "SELECT * FROM destinations WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            Destination d = new Destination();
            d.setId(rs.getInt("id"));
            d.setName(rs.getString("name"));
            d.setDescription(rs.getString("description"));
            d.setImageUrl(rs.getString("image_url"));
            return d;
        }
        return null;
    }

    public List<Destination> getAllDestinations() throws SQLException {
        String sql = "SELECT * FROM destinations";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Destination> list = new ArrayList<>();
        while (rs.next()) {
            Destination d = new Destination();
            d.setId(rs.getInt("id"));
            d.setName(rs.getString("name"));
            d.setDescription(rs.getString("description"));
            d.setImageUrl(rs.getString("image_url"));
            list.add(d);
        }
        return list;
    }
}
