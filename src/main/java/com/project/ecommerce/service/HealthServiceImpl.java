package com.project.ecommerce.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthServiceImpl implements HealthService{

    private final JdbcTemplate jdbcTemplate;

    public HealthServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<String> checkDatabase() {
        try{
        return jdbcTemplate.queryForList("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public'",String.class);
    } catch(Exception e) {
        throw new RuntimeException("Database Connection Failed");
        }
    }
}
