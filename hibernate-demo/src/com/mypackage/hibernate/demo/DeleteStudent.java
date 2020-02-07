package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Student;



public class DeleteStudent {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int id = 8;

			//get a new session and start transasction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on id
			Student myStudent = session.get(Student.class, id);
			System.out.println("Get complete: " + myStudent);
			
			//delete the student
			System.out.println("Deleting student");
			session.delete(myStudent);
				
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			
			//ANOTHER UPDATE TRANSACTION
			
			//get a new session and start transasction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//delete student based on email @demo.com
			session.createQuery("delete Student s where s.email LIKE '%@demo.com'").executeUpdate();
					
					
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
					
		}
		finally {
			factory.close();
		}

	}

}
