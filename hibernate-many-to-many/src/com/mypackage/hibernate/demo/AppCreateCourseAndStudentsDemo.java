package com.mypackage.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.mypackage.hibernate.entity.Course;
import com.mypackage.hibernate.entity.Instructor;
import com.mypackage.hibernate.entity.InstructorDetail;
import com.mypackage.hibernate.entity.Review;
import com.mypackage.hibernate.entity.Student;



public class AppCreateCourseAndStudentsDemo {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								 .configure("hibernate.cfg.xml")
								 .addAnnotatedClass(Instructor.class)
								 .addAnnotatedClass(InstructorDetail.class)
								 .addAnnotatedClass(Course.class)
								 .addAnnotatedClass(Review.class)
								 .addAnnotatedClass(Student.class)
								 .buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
		
			//create a course
			Course course = new Course("Rafting- Intermediate");
			
			//save the course.. and reviews coz of cascading
			System.out.println("Saving the course");		
			session.save(course);
			
			//create the students
			Student student1 = new Student("John", "Doe", "john.doe@gmail.com");
			Student student2 = new Student("Mary", "Public", "mary.public@gmail.com");
			
			//add students to course
			course.addStudent(student1);
			course.addStudent(student2);
			
			//save the students
			System.out.println("Saving students");
			session.save(student1);
			session.save(student2);
			System.out.println("Saved students: "+course.getStudents());
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}

	}

}
