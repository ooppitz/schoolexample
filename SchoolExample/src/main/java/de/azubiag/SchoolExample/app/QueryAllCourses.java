package de.azubiag.SchoolExample.app;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Student;

public class QueryAllCourses {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT c FROM Course c WHERE id IS NOT NULL";
			TypedQuery<Course> query = em.createQuery(queryString, Course.class);

			List<Course> list = query.getResultList();

			for (Course c : list) {
				String name = c.getName();
				Set<Student> students = c.getStudents();

				System.out.println("--------------------------");
				System.out.println("Course '" + name + "':");
				for (Student s : students) {
					System.out.println(s.getFname() + " " + s.getLname());
				}
				System.out.println();
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
