package com.treintaytres.vdc_backend.config;

public class DbContext {
    private static final ThreadLocal<String> context = new ThreadLocal<>();

    public static void setType(String type) {
        context.set(type);
    }

    public static String getDb() {
        return context.get();
    }

    public static void clearContext() {
        context.remove();
    }
}
