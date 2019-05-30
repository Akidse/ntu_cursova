package com.ntu.dao;

import com.ntu.ConnectionFactory;
import com.ntu.domain.Car;
import com.ntu.domain.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    @Override
    public List<Client> getAllClientsByDealershipId(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT clients.*, COUNT(sales.sale_id) as cars_count FROM clients LEFT JOIN sales" +
                    " ON sales.client_id = clients.client_id WHERE clients.dealership_id = ? GROUP BY clients.client_id")) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            List<Client> clients = new ArrayList<>();

            while(rs.next()) {
                Client client = extractClientFromResultSet(rs);
                clients.add(client);
            }
            return clients;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertClient(Client client) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.
                    prepareStatement("INSERT INTO clients (dealership_id, first_name, last_name)" +
                            "VALUES (?, ?, ?)")
        ){
            ps.setInt(1, client.getDealershipId());
            ps.setString(2, client.getFirstName());
            ps.setString(3, client.getLastName());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.
                    prepareStatement("UPDATE clients SET first_name = ?, last_name = ? WHERE client_id = ?")
        ){
            ps.setString(1, client.getFirstName());
            ps.setString(2, client.getLastName());
            ps.setInt(3, client.getClientId());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCLient(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM clients WHERE client_id = ?")
        ){
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client extractClientFromResultSet(ResultSet rs) throws SQLException {
        Client client = new Client();

        client.setClientId(rs.getInt("client_id"));
        client.setFirstName(rs.getString("first_name"));
        client.setLastName(rs.getString("last_name"));
        client.setFirstVisit(rs.getTimestamp("first_visit"));
        client.setLastVisit(rs.getTimestamp("last_visit"));
        client.setCarsCount(rs.getInt("cars_count"));

        return client;
    }
}
