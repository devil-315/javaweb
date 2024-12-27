package com.devil.scheduleback;

import com.devil.scheduleback.util.JdbcUtilPtimize;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ClassName：JdbcTest
 *
 * @author: Devil
 * @Date: 2024/8/31
 * @Description:
 * @version: 1.0
 */
public class JdbcTest {
    @Test
    public void jdbcTest() throws SQLException {
        Connection connection = JdbcUtilPtimize.getConnection();
        System.out.println(connection);
    }
}
