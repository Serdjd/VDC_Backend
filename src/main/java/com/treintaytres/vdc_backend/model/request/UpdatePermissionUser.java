package com.treintaytres.vdc_backend.model.request;

public class UpdatePermissionUser {
    private int id;
    private int permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
}
