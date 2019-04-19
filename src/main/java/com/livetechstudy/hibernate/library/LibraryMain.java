package com.livetechstudy.hibernate.library;

import java.util.List;
import java.util.Scanner;

import com.livetechstudy.hibernate.library.dao.BookDao;
import com.livetechstudy.hibernate.library.dao.BookDaoImpl;
import com.livetechstudy.hibernate.library.dao.HibernateSessionFactory;
import com.livetechstudy.hibernate.library.entity.Book;

public class LibraryMain {
	private static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("*************************************************************");
		System.out.println("         Welcome to Library Management System");
		System.out.println("*************************************************************");
		int option = 0;

		do {
			printUserOptions();
			option = s.nextInt();
			// clear buffer
			s.nextLine();
			switch (option) {
			case 1:
				addBook();
				break;
			case 2:
				deleteBookById();
				break;
			case 3:
				listAllBooks();
				break;
			case 4:
				searchBookByID();
				break;
			case 5:
				searchBookByTitle();
				break;
			case 6:
				HibernateSessionFactory.shutdown();
				System.out.println("Exiting .........");
				break;
			default:
				System.out.println("Not a valid Option");
				break;
			}
		} while (option != 6);
		s.close();
	}

	private static void searchBookByTitle() {
		System.out.println("Enter book title to be searched:");
		String bookTitle = s.nextLine();
		BookDao dao = new BookDaoImpl();
		List<Book> books = dao.findBooksByTitle(bookTitle);
		if(books.size()!=0) {
			for (Book book : books) {
				System.out.println(book);
			}
		}else {
			System.out.println("No book found with title="+bookTitle);
		}
	}

	private static void searchBookByID() {
		System.out.println("Enter bookid to be searched:");
		String bookID = s.nextLine();
		BookDao dao = new BookDaoImpl();
		Book book = dao.findBookByID(bookID);
		if (book != null) {
			System.out.println(book);
		} else {
			System.out.println("Book with ID=" + bookID + " not found");
		}

	}

	private static void listAllBooks() {
		BookDao dao = new BookDaoImpl();
		List<Book> books = dao.getAllBooks();
		for (Book book : books) {
			System.out.println(book);
		}

	}

	private static void deleteBookById() {
		System.out.println("Enter bookid to be deleted:");
		String bookID = s.nextLine();
		BookDao bookDao = new BookDaoImpl();
		int result = bookDao.deleteBook(bookID);
		if (result == 0) {
			System.out.println("Book with id=" + bookID + " not deleted");
		} else {
			System.out.println("Book deletde with ID=" + bookID);
		}
	}

	private static void addBook() {
		System.out.println("Enter Book ID , Title and Author\n");
		String id = s.nextLine();
		String title = s.nextLine();
		String author = s.nextLine();
		Book newBook = new Book(id, title, author);
		BookDao dao = new BookDaoImpl();
		dao.insertBook(newBook);
		System.out.println("New Book Added to library");
		System.out.println(newBook);
	}

	private static void printUserOptions() {
		System.out.println("Enter the option number");
		System.out.println("1)Add a Book");
		System.out.println("2)Delete a Book");
		System.out.println("3)List All Books");
		System.out.println("4)Search Book By BookID");
		System.out.println("5)Search Book Title");
		System.out.println("6)Exit");
	}

}
