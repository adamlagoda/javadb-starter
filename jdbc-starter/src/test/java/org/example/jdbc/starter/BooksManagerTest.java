package org.example.jdbc.starter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BooksManagerTest {

    private BooksManager underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new BooksManager();
    }

    @Test
    public void shouldReturnListOfBooks() {
        //when
        List<String> titles = underTest.listNonReservedBooks();

        //then
        Assert.assertFalse(titles.isEmpty());
    }

    @Test
    public void shouldAddNewBook() {
        //when
        boolean result = underTest.addBook("Książka 3");

        //then
        Assert.assertTrue(result);
    }
}