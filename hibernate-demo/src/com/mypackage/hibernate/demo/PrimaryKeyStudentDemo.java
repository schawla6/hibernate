package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Student;

public class PrimaryKeyStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create a student object
			System.out.println("Creating a new student objects");
			Student student1 = new Student("John","Day","john.day@gmail.com");
			Student student2 = new Student("Mary","Schoe","mary.schoe@gmail.com");
			Student student3 = new Student("Fred","Meyers","fred.meyers@gmail.com");

			// start a transaction
			session.beginTransaction();

			// save the student object
			System.out.println("Saving the student objects");
			session.save(student1);
			session.save(student2);
			session.save(student3);

			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}
}
