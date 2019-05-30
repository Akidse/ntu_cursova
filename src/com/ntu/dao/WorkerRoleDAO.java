package com.ntu.dao;

import com.ntu.domain.WorkerRole;

import java.util.List;

public interface WorkerRoleDAO {

    WorkerRole getWorkerRoleById(int id);
    WorkerRole getWorkerRoleByName(String name);
    List<WorkerRole> getAllWorkerRoles();

    boolean insertWorkerRole(WorkerRole workerRole);
    boolean updateWorkerRole(WorkerRole workerRole);
    boolean deleteWorkerRole(int id);

}