package org.example.library.books;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BooksDaoTest {

    private IBooksDao underTest;

    @Before
    public void setup() {
        underTest = new JdbcBooksDao();
    }

    @Test
    public void shouldReturnNonEmptyList() {
        //when
        List<Book> nonEmptyList = underTest.listAllMatchingPhraseTitle("czyli");

        //then
        Assert.assertFalse(nonEmptyList.isEmpty());
    }

    @Test
    public void shouldReturnEmptyList() {
        //when
        List<Book> nonEmptyList = underTest.listAllMatchingPhraseTitle("lub");

        //then
        Assert.assertTrue(nonEmptyList.isEmpty());
    }
}
