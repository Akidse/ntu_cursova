package com.ntu.dao;

import com.ntu.ConnectionFactory;
import com.ntu.domain.WorkerRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerRoleDAOImpl implements WorkerRoleDAO {

    @Override
    public WorkerRole getWorkerRoleById(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM workers_roles WHERE role_id = ?")
        ) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            List<WorkerRole> roles = new ArrayList<>();
            while(rs.next()) {
                WorkerRole role = extractWorkerRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles.get(0); //only 1 result
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WorkerRole getWorkerRoleByName(String name) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM workers_roles WHERE name = ?")
        ) {
            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            List<WorkerRole> roles = new ArrayList<>();
            while(rs.next()) {
                WorkerRole role = extractWorkerRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles.get(0); //only 1 result
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<WorkerRole> getAllWorkerRoles() {
        try(Connection connection = ConnectionFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `workers_roles`")
        ) {
            List<WorkerRole> roles = new ArrayList<>();

            while(rs.next()) {
                WorkerRole role = extractWorkerRoleFromResultSet(rs);
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertWorkerRole(WorkerRole workerRole) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("INSERT INTO workers_roles (role_id, name) VALUES (?, ?)")
        ){
            ps.setInt(1, workerRole.getRoleId());
            ps.setString(2, workerRole.getName());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateWorkerRole(WorkerRole workerRole) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("UPDATE workers_roles SET name = ? WHERE role_id = ?")) {
            ps.setString(1, workerRole.getName());
            ps.setInt(2, workerRole.getRoleId());

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteWorkerRole(int id) {
        try(Connection connection = ConnectionFactory.getConnection();
            PreparedStatement ps = connection.prepareStatement("DELETE FROM workers_roles WHERE role_id = ?")
        ) {
            ps.setInt(1, id);

            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    WorkerRole extractWorkerRoleFromResultSet(ResultSet rs) throws SQLException {

        WorkerRole role = new WorkerRole();
        role.setRoleId(rs.getInt("role_id"));
        role.setName(rs.getString("name"));

        return role;
    }
}
