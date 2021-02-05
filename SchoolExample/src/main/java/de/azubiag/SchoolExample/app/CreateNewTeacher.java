package de.azubiag.SchoolExample.app;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.model.Teacher;

public class CreateNewTeacher {

	public static void main(String[] args) {

		EntityManager em = Model.ENTITY_MANAGER_FACTORY.createEntityManager();

		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			// Student w/o an assigned course
			Teacher t = new Teacher("Alfons", "Mandorfer");

			em.persist(t);

			et.commit();
			
			System.out.println("*** Created teacher " + t.getName() + "\n");

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}

		Teacher.printTable();

	}

}
