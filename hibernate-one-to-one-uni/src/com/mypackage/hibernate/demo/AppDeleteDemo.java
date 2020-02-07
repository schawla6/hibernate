package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;
import com.mypackage.hibernate.entity.Student;



public class AppDeleteDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// start a transaction
			session.beginTransaction();
			
			//get instructor by primary key/id
			int id = 2;
			
			Instructor theInstructor = session.get(Instructor.class, id);
			System.out.println("Found instructor: "+theInstructor);
		
			//delete the instructor
			//
			//NOTE: it'll also delete instructor detail due to cascading
			//
			if(theInstructor != null){
				System.out.println("Deleting: "+theInstructor);
				session.delete(theInstructor);
			}
						
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

}
