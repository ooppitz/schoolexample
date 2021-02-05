package de.azubiag.SchoolExample.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.util.Util;

public class CreateNewStudent {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();

		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			// Student w/o an assigned course
			Student s = new Student("Erich", "Semlak");

			em.persist(s);

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
		
		Student.printTable();

	}

	



	

}
