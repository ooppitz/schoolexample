package de.azubiag.SchoolExample.app;

import javax.persistence.EntityTransaction;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Teacher;

public class CreateNewTeacher {

	public static void main(String[] args) {

		EntityTransaction et = Model.em.getTransaction();
		et.begin();

		try {

			// Create teacher w/o an assigned course
			Teacher t = new Teacher("Benedikt", "Pitschmann");
			Model.em.persist(t);

			System.out.println("*** Table with new teacher");
			Teacher.printTable();

		} catch (Exception e) {

			e.printStackTrace();

		}

		et.rollback();

		System.out.println("*** Table after rollback");
		Teacher.printTable();

	}

}
