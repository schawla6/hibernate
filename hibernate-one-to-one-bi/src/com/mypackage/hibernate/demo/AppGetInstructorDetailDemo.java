package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;



public class AppGetInstructorDetailDemo {

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
			int id = 1;
			
			InstructorDetail theInstructorDetail = session.get(InstructorDetail.class, id);
			
			System.out.println("Found instructor details: "+theInstructorDetail);
			System.out.println("Taking instructor from instructor details: "+theInstructorDetail.getInstructor());
						
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//handle connection leak by closing connection
			session.close();
			
			factory.close();
		}

	}

}
