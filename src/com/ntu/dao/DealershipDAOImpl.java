package com.ntu.dao;

import com.ntu.ConnectionFactory;
import com.ntu.domain.Dealership;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAOImpl implements DealershipDAO {
    @Override
    public Dealership getDealershipById(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dealerships WHERE dealership_id = ?")
        ) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            List<Dealership> roles = new ArrayList<>();
            while(rs.next()) {
                Dealership role = extractWorkerRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles.get(0); //only 1 result
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Dealership getDealershipByName(String name) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM dealerships WHERE title = ?")
        ) {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            List<Dealership> roles = new ArrayList<>();
            while(rs.next()) {
                Dealership role = extractWorkerRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles.get(0); //only 1 result
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dealership> getAllDealerships() {
        try(Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `dealerships`")
        ) {
            List<Dealership> roles = new ArrayList<>();

            while(rs.next()) {
                Dealership role = extractWorkerRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertDealership(Dealership dealership) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO dealerships (title, description, date_created, status) VALUES (?, ?, ?, ?)")
        ){
            ps.setString(1, dealership.getTitle());
            ps.setString(2, dealership.getDescription());
            ps.setTimestamp(3, dealership.getDateCreated());
            ps.setString(4, dealership.getStatus());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateDealership(Dealership dealership) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.
                                   prepareStatement("UPDATE dealerships SET " +
                                                         "title = ?, description = ?, date_created = ?, status = ? " +
                                                         "WHERE dealership_id = ?")) {
            ps.setString(1, dealership.getTitle());
            ps.setString(2, dealership.getDescription());
            ps.setTimestamp(3, dealership.getDateCreated());
            ps.setString(4, dealership.getStatus());
            ps.setInt(5, dealership.getDealershipId());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDealership(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM dealerships WHERE dealership_id = ?")
        ) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    Dealership extractWorkerRoleFromResultSet(ResultSet rs) throws SQLException {

        Dealership dealership = new Dealership();
        dealership.setDealershipId(rs.getInt("dealership_id"));
        dealership.setTitle(rs.getString("title"));
        dealership.setDescription(rs.getString("description"));
        dealership.setDateCreated(rs.getTimestamp("date_created"));
        dealership.setStatus(rs.getString("status"));


        return dealership;
    }
}
