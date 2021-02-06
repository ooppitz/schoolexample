package de.azubiag.SchoolExample.app;

import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;

public class CreateNewCourse {

	public static void main(String[] args) {

		System.out.println("=== Courses before transaction ==============================");
		Course.printTable();
		
		EntityTransaction et = Model.em.getTransaction();
		
		try {

			String courseName = "Griechisch";
			et.begin();

			System.out.println("*** Create course " +  courseName);
			
			Course c = new Course(courseName);
			Model.em.persist(c);

			System.out.println("*** Remove course " +  courseName);
			c.remove();
			

		} catch (Exception e) {

			e.printStackTrace();
			
		}
		
		et.rollback();
		
		System.out.println("=== Courses after transaction ==============================");
		Course.printTable();

	}

	



	

}
