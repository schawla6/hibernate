package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Student;



public class ReadStudent {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//create a student object
			System.out.println("Creating a new student object");
			Student student = new Student("Saul","Temp","saul.temp@gmail.com");
			
			//start a transaction
			session.beginTransaction();
			
			//save the student object
			System.out.println("Saving the student object");
			session.save(student);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done creating the student!");
			
			//find the student's id
			System.out.println("Saved student. Generated Id: "+student.getId());
			
			//now get a new session and start transasction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//retrieve student based on id
			System.out.println("\nGetting student with id: "+student.getId());
			Student myStudent = session.get(Student.class, student.getId());
			System.out.println("Get complete: " + myStudent);
			
			//commit the transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		finally {
			factory.close();
		}

	}

}
