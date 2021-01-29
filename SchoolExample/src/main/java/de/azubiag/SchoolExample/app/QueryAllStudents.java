package de.azubiag.SchoolExample.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Student;

public class QueryAllStudents {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT s FROM Student s WHERE id IS NOT NULL";
			TypedQuery<Student> query = em.createQuery(queryString, Student.class);

			List<Student> list = query.getResultList();

			for (Student s : list) {
				System.out.println(s.getFname() + " " + s.getLname());
			}

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}

	}

}
