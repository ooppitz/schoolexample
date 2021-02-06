package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;

public class CreateNewCourse {

	public static void main(String[] args) {

		EntityManager em = Model.em;
		EntityTransaction et = em.getTransaction();
		
		try {

			et.begin();

			Course c = new Course("Chormusik");

			em.persist(c);

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
		
		System.out.println("=== Courses ==============================");
		Course.printTable();

	}

	



	

}
