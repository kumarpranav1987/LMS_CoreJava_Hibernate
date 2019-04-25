package com.livetechstudy.hibernate.library;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.livetechstudy.hibernate.library.dao.HibernateSessionFactory;
import com.livetechstudy.hibernate.library.entity.Certificate;
import com.livetechstudy.hibernate.library.entity.Employee;

public class ListExample {
	public static void main(String[] args) {

		List<Certificate> certificates = new ArrayList<>();
		certificates.add(new Certificate("JAVA"));
		certificates.add(new Certificate("Database"));

		Employee e1 = new Employee("ABC", "XYZ", 1000);
		e1.setCertificates(certificates);
		Session session = null;
		try {
			session = HibernateSessionFactory.getSessionFactory().openSession();
			Transaction tx = session.beginTransaction();
			session.save(e1);
			tx.commit();

			//get the data
			List<Employee> fetchedData = session.createQuery("from Employee",Employee.class).getResultList();
			
			for(Employee e:fetchedData) {
				System.out.println(e.getFirstName());
				System.out.println(e.getLastName());
				
				List<Certificate> list = e.getCertificates();
				for(int i=0;i<list.size();i++) {
					System.out.println(list.get(i).getName());
				}
			}
		} finally {
			session.close();
			HibernateSessionFactory.shutdown();
		}

	}
}
