package de.azubiag.SchoolExample.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.util.Util;

public class RemoveStudent {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		System.out.println("=== Courses ===========================");
		Course.printTable();
		System.out.println("=== Students ==========================");
		Student.printTable();
		
		try {
			et.begin();

			String queryString = "SELECT s from Student s WHERE s.fname='Hans'";
			TypedQuery<Student> query = em.createQuery(queryString, Student.class);
			List<Student> studentList = query.getResultList();
			for (Student s : studentList) {
				System.out.println("*** Removing student " + s.getLastname());
				s.prepareToRemove();
				em.remove(s);
			}
			et.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("=== Courses ===========================");
		Course.printTable();
		System.out.println("=== Students ==========================");
		Student.printTable();

	}

}
