package com.ntu.dao;

import com.ntu.ConnectionFactory;
import com.ntu.domain.Car;
import com.ntu.domain.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {
    private Manufacturer manufacturerFilter = null;
    private int availableFilter = -1;
    private int priceLowLimitFilter = -1;
    private int priceHighLimitFilter = -1;

    public void setManufacturerFilter(Manufacturer manufacturer) {
        manufacturerFilter = manufacturer;
    }

    public void setAvailableFilter(boolean available) {
        availableFilter = available? 1 : 0;
    }

    public void setPriceLimitFilter(int lowerCase, int higherCase) {
        priceLowLimitFilter = lowerCase;
        priceHighLimitFilter = higherCase;
    }

    public void releaseFilters() {
        manufacturerFilter = null;
        availableFilter = -1;
        priceLowLimitFilter = -1;
        priceHighLimitFilter = -1;
    }

    @Override
    public List<Car> getAllCars() {
        try(Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT cars.*, manufacturers.manufacturer_id as manufacturer_data_id, manufacturers.name as manufacturer_data_name" +
                    " FROM `cars` LEFT JOIN manufacturers ON manufacturers.manufacturer_id = cars.manufacturer_id")
        ) {
            List<Car> roles = new ArrayList<>();

            while(rs.next()) {
                Car role = extractCarFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Car> getCarsByDealerShipId(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("SELECT cars.*, manufacturers.manufacturer_id as manufacturer_data_id, manufacturers.name as manufacturer_data_name" +
                    " FROM `cars` LEFT JOIN manufacturers ON manufacturers.manufacturer_id = cars.manufacturer_id WHERE dealership_id = ?")
        ) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            List<Car> roles = new ArrayList<>();

            while(rs.next()) {
                Car role = extractCarFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Car> getFilteredCarsByDealershipId(int id) {
        String filters = "";
        if(manufacturerFilter != null)
            filters += " AND cars.manufacturer_id = "+manufacturerFilter.getManufacturerId();
        if(availableFilter != -1)
            filters += " AND cars.available = "+availableFilter;
        if(priceLowLimitFilter != -1)
            filters += " AND cars.price > "+priceLowLimitFilter;
        if(priceHighLimitFilter != -1)
            filters += " AND cars.price < "+priceHighLimitFilter;

        releaseFilters();
        try(Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            PreparedStatement ps = connection.prepareStatement("SELECT cars.*, manufacturers.manufacturer_id as manufacturer_data_id, manufacturers.name as manufacturer_data_name" +
                    " FROM `cars` LEFT JOIN manufacturers ON manufacturers.manufacturer_id = cars.manufacturer_id WHERE dealership_id = ?"+filters)
        ) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            List<Car> roles = new ArrayList<>();

            while(rs.next()) {
                Car role = extractCarFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public boolean insertCar(Car car) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.
                    prepareStatement("INSERT INTO cars (dealership_id, model, manufacturer_id, date_last_added, count, available, price)" +
                                            "VALUES (?, ?, ?, ?, ?, ?, ?)")
        ){
            ps.setInt(1, car.getDealershipId());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getManufacturerId());
            ps.setTimestamp(4, car.getLastAdded());
            ps.setInt(5, car.getCount());
            ps.setBoolean(6, car.isAvailable());
            ps.setInt(7, car.getPrice());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCar(Car car) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.
                    prepareStatement("UPDATE cars SET dealership_id = ?, model = ?, manufacturer_id = ?, date_last_added = ?, count = ?, available = ?, price = ?" +
                            " WHERE car_id = ?")
        ){
            ps.setInt(1, car.getDealershipId());
            ps.setString(2, car.getModel());
            ps.setInt(3, car.getManufacturerId());
            ps.setTimestamp(4, car.getLastAdded());
            ps.setInt(5, car.getCount());
            ps.setBoolean(6, car.isAvailable());
            ps.setInt(7, car.getPrice());
            ps.setInt(8, car.getCarId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCar(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM cars WHERE car_id = ?")
        ){
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    Car extractCarFromResultSet(ResultSet rs) throws SQLException {

        Car car = new Car();
        Manufacturer manufacturer = new Manufacturer(rs.getInt("manufacturer_data_id"), rs.getString("manufacturer_data_name"));
        car.setCarId(rs.getInt("car_id"));
        car.setDealershipId(rs.getInt("dealership_id"));
        car.setModel(rs.getString("model"));
        car.setManufacturerId(rs.getInt("manufacturer_id"));
        car.setLastAdded(rs.getTimestamp("date_last_added"));
        car.setCount(rs.getInt("count"));
        car.setAvailable(rs.getBoolean("available"));
        car.setPrice(rs.getInt("price"));
        car.setManufacturer(manufacturer);

        return car;
    }
}
