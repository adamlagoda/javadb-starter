package org.example.library.books;

import java.util.List;

public interface IBooksDao {
    List<Book> list();
    void add(Book book);
    void delete(int bookId);
    List<Category> listCategories();
    List<Book> listAllMatchingPhraseTitle(String phrase);
}
