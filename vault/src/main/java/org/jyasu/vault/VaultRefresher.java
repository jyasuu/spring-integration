package org.jyasu.vault;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.refresh.ContextRefresher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
class VaultRefresher {
    private final Log log = LogFactory.getLog(getClass());
    
    @Autowired
    private JdbcTemplate jdbcTemplate;


    private final ContextRefresher contextRefresher;

    VaultRefresher(ContextRefresher contextRefresher) {
        this.contextRefresher = contextRefresher;
    }

    @Scheduled(initialDelayString="1000",
            fixedDelayString = "1000")
    void refresher() {
        contextRefresher.refresh();
        log.info("refresh key-value secret");
        
        // Query to get the current user from PostgreSQL
        String sql = "SELECT current_user";

        // Execute the query and retrieve the current user
        String currentUser = jdbcTemplate.queryForObject(sql, String.class);
        log.info(currentUser);
    }
}