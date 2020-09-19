package org.example.jdbc.starter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryTest {

    private ConnectionFactory underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new ConnectionFactory("");
    }

    @Test
    public void shouldReturnConnection() throws SQLException {
        //given
        //when
        Connection connection = underTest.getConnection();

        //then
        Assert.assertNotNull(connection);
    }
}
