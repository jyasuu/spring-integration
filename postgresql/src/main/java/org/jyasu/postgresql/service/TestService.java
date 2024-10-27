package org.jyasu.postgresql.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestService {

    @Autowired
    private JdbcTemplate db1JdbcTemplate;

    
    @Autowired
    private JdbcTemplate db2JdbcTemplate;

    @Transactional
    public void tx()
    {
        db1JdbcTemplate.update("update user set age = ? where name = ?",30,"aaa");
        db2JdbcTemplate.update("update user set age = ? where name = ?",30,"aaa");
    }

    @Transactional
    public void tx2()
    {
        db1JdbcTemplate.update("update user set age = ? where name = ?",30,"aaa");
        db2JdbcTemplate.update("update user set age = ? where name = ?",30,"aaa");
        throw new RuntimeException();
    }
}
