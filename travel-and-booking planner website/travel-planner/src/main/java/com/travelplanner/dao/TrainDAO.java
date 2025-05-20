package com.travelplanner.dao;

import com.travelplanner.model.Trains;
import java.sql.*;
import java.util.*;

public class TrainDAO {
    private Connection conn;

    public TrainDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Trains> getAllTrains() throws SQLException {
        String sql = "SELECT * FROM trains";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Trains> trains = new ArrayList<>();
        while (rs.next()) {
            trains.add(mapTrain(rs));
        }
        return trains;
    }

    public List<Trains> getTrainsByDestination(int destinationId) throws SQLException {
        String sql = "SELECT * FROM trains WHERE destination_id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, destinationId);
        ResultSet rs = stmt.executeQuery();
        List<Trains> trains = new ArrayList<>();
        while (rs.next()) {
            trains.add(mapTrain(rs));
        }
        return trains;
    }

    public Trains getTrainById(int id) throws SQLException {
        String sql = "SELECT * FROM trains WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return mapTrain(rs);
        }
        return null;
    }

    private Trains mapTrain(ResultSet rs) throws SQLException {
        Trains t = new Trains();
        t.setId(rs.getInt("id"));
        t.setDestinationId(rs.getInt("destination_id"));
        t.setOperator(rs.getString("operator"));
        t.setDeparture(rs.getString("departure"));
        t.setArrival(rs.getString("arrival"));
        t.setPrice(rs.getDouble("price"));
        t.setImageUrl(rs.getString("image_url"));
        return t;
    }
}