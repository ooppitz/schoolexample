package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;

public class SchoolDBApp {
	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	public static void main(String[] args) {

		/*
		 * String[] names = { "Franz", "Klammer", "Roswita", "Röll", "Werner",
		 * "Heisenberg" };
		 * 
		 * for (int i = 0; i < names.length; i += 2) {
		 * 
		 * String fname = names[i]; String lname = names[i + 1];
		 * 
		 * Student student = new Student(fname, lname);
		 * 
		 */
		Course course = new Course("Latein");
		
		Course course2 = new Course("Französisch");
		Course course3 = new Course("Griechisch");


		Student student1 = new Student("Christoph", "Wintersteller", course);
		Student student2 = new Student("Leonhard", "Klinglmeier", course);
		Student student3 = new Student("Alfons", "Mandorfer", course);

		course.add(student1);
		course.add(student2);
		course.add(student3);

		
		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		try {
			et = em.getTransaction();
			et.begin();

			em.persist(student1);
			em.persist(student2);
			em.persist(student3);
			
			em.persist(course);
			em.persist(course2);
			em.persist(course3);


			et.commit();
		} catch (Exception e) {
			
			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}


	}
}
