package com.livetechstudy.hibernate.library.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.livetechstudy.hibernate.library.entity.Book;

public class BookDaoImpl implements BookDao {
	@Override
	public void insertBook(Book book) {
		Transaction transaction = null;
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.save(book);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}

	@Override
	public int deleteBook(String bookID) {
		int result = 0;
		Transaction transaction = null;
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Query query = session.createQuery("delete from Book where bookId=:id");
			query.setParameter("id", bookID);
			result = query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return result;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = null;
		Transaction transaction = null;
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			books = session.createQuery("from Book", Book.class).list();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return books;
	}

	@Override
	public Book findBookByID(String bookID) {
		Book result = null;
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
			result = session.createQuery("from Book where bookId=:id", Book.class).setParameter("id", bookID)
					.getSingleResult();
		} catch (Exception e) {
		}
		return result;
	}

	@Override
	public List<Book> findBooksByTitle(String title) {
		List<Book> books = null;
		try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
			books = session.createQuery("from Book where bookTitle=:title", Book.class).setParameter("title", title)
					.list();
		} catch (Exception e) {
			
		}
		return books;
	}

}
