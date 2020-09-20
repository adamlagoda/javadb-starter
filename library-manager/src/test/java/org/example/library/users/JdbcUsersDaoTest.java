package org.example.library.users;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class JdbcUsersDaoTest {

    @Before
    public void setUp() throws Exception {
        underTest = new JdbcUsersDao();
    }

    private IUsersDao underTest;

    @Test
    public void shouldReturnNonEmptyList() {
        //when
        List<User> nonEmptyList = underTest.list();

        //then
        Assert.assertFalse(nonEmptyList.isEmpty());
    }

}
