package com.mypackage.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Student;



public class QueryStudent {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			//create query
			System.out.println("Creating query and retrieving all Students");
			List<Student> allStudents = session.createQuery("from Student").getResultList();
			
			// display the students
			displayStudents(allStudents);
			
			//create query
			System.out.println("Retrieving students with last name Paul");
			allStudents = session.createQuery("from Student s where s.lastName='Paul'").getResultList();
			
			// display the students
			displayStudents(allStudents);
			
			//create query
			System.out.println("Retriving all students with last name Paul or first name John");
			allStudents = session.createQuery("from Student s where"
					+ " s.lastName='Paul' OR s.firstName ='John'").getResultList();
			
			// display the students
			displayStudents(allStudents);
			
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

	public static void displayStudents(List<Student> allStudents) {
		for(Student student : allStudents){
			System.out.println("student is: "+student);
		}
	}

}
