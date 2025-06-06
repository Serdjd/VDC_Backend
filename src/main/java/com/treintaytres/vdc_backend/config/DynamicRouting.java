package com.treintaytres.vdc_backend.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRouting extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String key = DbContext.getDb();
        System.out.println("key: " + key);
        return DbContext.getDb();
    }
}
