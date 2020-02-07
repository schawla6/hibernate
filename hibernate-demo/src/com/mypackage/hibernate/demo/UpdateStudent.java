package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Student;



public class UpdateStudent {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			int id = 5;

			//get a new session and start transasction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on id
			Student myStudent = session.get(Student.class, id);
			System.out.println("Get complete: " + myStudent);
			
			//update the student
			System.out.println("Updating student");
			myStudent.setFirstName("Scooby");
				
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			
			//ANOTHER UPDATE TRANSACTION
			
			//get a new session and start transasction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//update student email based on firstName and lastName
			session.createQuery("update Student s set email = 'scooby.temp@gmail.com' where"
					+ " s.firstName = 'Scooby' AND s.lastName = 'Temp'").executeUpdate();
					
					
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
					
		}
		finally {
			factory.close();
		}

	}

}
