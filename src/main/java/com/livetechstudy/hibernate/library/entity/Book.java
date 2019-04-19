package com.livetechstudy.hibernate.library.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="books")
public class Book {
	//@Id
	private String bookId;
	private String bookTitle;
	private String author;

	public Book() {
	}

	public Book(String bookId, String bookTitle, String author) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.author = author;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", author=" + author + "]";
	}

}
