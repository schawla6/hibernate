package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;
import com.mypackage.hibernate.entity.Student;



public class AppCreateDemo {

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
			
			//create the objects
			Instructor theInstructor = new Instructor("Sean","William","sean.william@gmail.com");
			
			InstructorDetail theInstructorDetail = 
					new InstructorDetail(
							"http://youtube.com/rafting-instructor", 
							"rafting");
			
			//associate the objects
			theInstructor.setInstructorDetail(theInstructorDetail);
			
			// start a transaction
			session.beginTransaction();
		
			//save the instructor
			//
			//Note: this will also save the instructor details object
			//because of the CascadeType.ALL affect
			//
			System.out.println("Saving the instructor and instructor details");
			session.save(theInstructor);
						
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

}
