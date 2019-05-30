package com.ntu.domain;

import java.io.Serializable;

public class WorkerRole implements Serializable {
    private int roleId;
    private String name;

    public WorkerRole(int roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public WorkerRole(String name) {
        this.name = name;
    }

    public WorkerRole() {
        super();
    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WorkerRole{" +
                "roleId=" + roleId +
                ", name='" + name + '\'' +
                '}';
    }
}
