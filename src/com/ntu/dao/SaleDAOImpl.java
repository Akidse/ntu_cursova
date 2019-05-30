package com.ntu.dao;

import com.ntu.ConnectionFactory;
import com.ntu.domain.Car;
import com.ntu.domain.Client;
import com.ntu.domain.Sale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaleDAOImpl implements SaleDAO {
    @Override
    public List<Sale> getSalesByClientId(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT sales.*, cars.*" +
                    " FROM SALES LEFT JOIN cars ON cars.car_id = sales.car_id WHERE client_id = ?"))
        {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            List<Sale> sales = new ArrayList<>();

            while(rs.next()) {
                Sale sale = extractSaleFromResultSet(rs);
                sales.add(sale);
            }
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<String> getNameAndPriceCarByClientId(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT sales.*, cars.*" +
                    " FROM SALES LEFT JOIN cars ON cars.car_id = sales.car_id WHERE client_id = ?"))
        {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            List<String> sales = new ArrayList<>();

            while(rs.next()) {
                Sale sale = extractSaleFromResultSet(rs);
                sales.add(sale.getCar().getModel()+" - "+sale.getCar().getPrice()+"грн.");
            }
            return sales;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertSale(Sale sale) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.
                    prepareStatement("INSERT INTO sales (car_id, client_id) VALUES(?,?)")
        ){
            ps.setInt(1, sale.getCarId());
            ps.setInt(2, sale.getClientId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSale(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM sales WHERE sale_id = ?")
        ){
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Sale extractSaleFromResultSet(ResultSet rs) throws SQLException {
        Sale sale = new Sale();

        Car car = new Car();
        car.setCarId(rs.getInt("car_id"));
        car.setModel(rs.getString("model"));
        car.setPrice(rs.getInt("price"));

        sale.setCar(car);
        sale.setCarId(rs.getInt("car_id"));
        sale.setClientId(rs.getInt("client_id"));
        sale.setTimeBought(rs.getTimestamp("time_bought"));

        return sale;
    }
}
