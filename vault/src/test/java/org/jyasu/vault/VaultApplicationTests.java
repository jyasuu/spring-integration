package org.jyasu.vault;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class VaultApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Test
	void contextLoads() {
	}

    @Test
    public void testQueryCurrentUser() {
        // Query to get the current user from PostgreSQL
        String sql = "SELECT current_user";

        // Execute the query and retrieve the current user
        String currentUser = jdbcTemplate.queryForObject(sql, String.class);

        // Assert that the current user is not null
        assertNotNull(currentUser);

        // Optionally, print the current user
        System.out.println("Current PostgreSQL User: " + currentUser);

        // You can also assert that the current user matches a specific role/username
		assertEquals("", currentUser);
        assertTrue(currentUser.contains("expected_user"), "Current user should match the expected user");
    }
}
