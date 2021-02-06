package de.azubiag.SchoolExample.app;

import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;

public class CreateNewStudent {

	public static void main(String[] args) {
		
		EntityTransaction et = Model.em.getTransaction();
		et.begin();

		try {

			// Create Student w/o an assigned course
			Student s = new Student("Martin", "Kaltenbrunner");
			Model.em.persist(s);
			
			System.out.println("*** Table with new student");
			Student.printTable();

		} catch (Exception e) {

			e.printStackTrace();
		
		}
		et.rollback();
		
		System.out.println("*** Table after rollback");
		Student.printTable();

	}

	



	

}
