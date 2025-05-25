package com.treintaytres.vdc_backend.model;

public class Assistance {
    private int confirmed;
    private int cancelled;

    public Assistance() {
        this.confirmed = 0;
        this.cancelled = 0;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void increaseConfirmed() {
        this.confirmed += 1;
    }

    public int getCancelled() {
        return cancelled;
    }

    public void increaseCancelled() {
        this.confirmed += 1;
    }
}
