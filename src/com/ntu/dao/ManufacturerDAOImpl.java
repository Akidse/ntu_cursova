package com.ntu.dao;

import com.ntu.ConnectionFactory;
import com.ntu.domain.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAOImpl implements ManufacturerDAO {
    @Override
    public Manufacturer getManufacturerById(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM manufacturers WHERE manufacturer_id = ?")
        ) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            List<Manufacturer> manufacturers = new ArrayList<>();
            while(rs.next()) {
                Manufacturer manufacturer = extractManufacturerFromResultSet(rs);
                manufacturers.add(manufacturer);
            }
            return manufacturers.get(0); //only 1 result
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Manufacturer getManufacturerByName(String name) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM manufacturers WHERE name = ?")
        ) {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            List<Manufacturer> roles = new ArrayList<>();
            while(rs.next()) {
                Manufacturer role = extractManufacturerFromResultSet(rs);
                roles.add(role);
            }
            return roles.get(0); //only 1 result
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Manufacturer> getAllManufacturers() {
        try(Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `manufacturers`")
        ) {
            List<Manufacturer> manufacturers = new ArrayList<>();

            while(rs.next()) {
                Manufacturer manufacturer = extractManufacturerFromResultSet(rs);
                manufacturers.add(manufacturer);
            }
            return manufacturers;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertManufacturer(Manufacturer manufacturer) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO manufacturers (name) VALUES (?)")
        ){
            ps.setString(1, manufacturer.getName());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateManufacturer(Manufacturer manufacturer) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE manufacturers SET name = ? WHERE manufacturer_id = ?")) {
            ps.setString(1, manufacturer.getName());
            ps.setInt(2, manufacturer.getManufacturerId());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteManufacturer(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM manufacturers WHERE manufacturer_id = ?")
        ) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    Manufacturer extractManufacturerFromResultSet(ResultSet rs) throws SQLException {

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerId(rs.getInt("manufacturer_id"));
        manufacturer.setName(rs.getString("name"));

        return manufacturer;
    }
}
