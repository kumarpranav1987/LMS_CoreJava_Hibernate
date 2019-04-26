package com.livetechstudy.hibernate.library;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.livetechstudy.hibernate.library.dao.HibernateSessionFactory;
import com.livetechstudy.hibernate.library.entity.Book;

public class CacheExample {

	public static void main(String[] args) {
		Session firstSession = HibernateSessionFactory.getSessionFactory().openSession();
		Session secondSession = HibernateSessionFactory.getSessionFactory().openSession();
		Transaction firstTransaction = null;
		Transaction secondTransaction = null;
		try {
			firstTransaction= firstSession.beginTransaction();
			secondTransaction= secondSession.beginTransaction();
			
			Book book1 = firstSession.load(Book.class, "1234");		
			System.out.println(book1);
			Book book2 = firstSession.load(Book.class, "1234");
			System.out.println(book2);
			
			Book book3 = secondSession.load(Book.class, "1234");
			System.out.println(book3);
			
		} finally {
			firstTransaction.commit();
			secondTransaction.commit();
			firstSession.close();
			secondSession.close();
			HibernateSessionFactory.shutdown();
		}
	}

}
