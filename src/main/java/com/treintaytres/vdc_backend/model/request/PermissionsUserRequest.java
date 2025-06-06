package com.treintaytres.vdc_backend.model.request;

public class PermissionsUserRequest {
    private int permissions;

    public PermissionsUserRequest() {}
    public PermissionsUserRequest(int permissions) {
        this.permissions = permissions;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }
}
