package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;

public class SchoolDBApp {

	public static void main(String[] args) {

		EntityManager em = Model.em;
		EntityTransaction et = em.getTransaction();
		et.begin();

		try {

			Course course = new Course("Geometrisches Zeichnen");
			Course course2 = new Course("Informatik");

			Student student1 = new Student("Martin", "Spinka", course);
			Student student2 = new Student("Joachim", "Winter", course);
			Student student3 = new Student("Andreas", "Fetz", course);

			em.persist(student1);
			em.persist(student2);
			em.persist(student3);

			em.persist(course);
			em.persist(course2);
	

		} catch (Exception e) {

			e.printStackTrace();

		}

		et.rollback();

	}
}
