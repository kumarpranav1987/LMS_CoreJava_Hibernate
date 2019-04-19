package com.livetechstudy.hibernate.library.dao;

import java.util.List;

import com.livetechstudy.hibernate.library.entity.Book;

public interface BookDao {
	public void insertBook(Book book);
	public int deleteBook(String bookID);
	public List<Book>getAllBooks();
	public Book findBookByID(String bookID);
	public List<Book> findBooksByTitle(String title);
}
