package com.jyasu.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class Schedule {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Scheduled(initialDelayString="1000",
            fixedDelayString = "1000")
    void doSomething() {
        log.info("do something");
    }
    
}
