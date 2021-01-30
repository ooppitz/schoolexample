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
import de.azubiag.SchoolExample.util.Util;

public class RemoveCourse {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	public static void main(String[] args) {

		EntityManager em = SchoolDBApp.ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Student.printTable(em);

		try {
			et.begin();

			String queryString = "SELECT c from Course c WHERE c.name='Französisch'";
			TypedQuery<Course> query = em.createQuery(queryString, Course.class);
			List<Course> courseList = query.getResultList();
			for (Course course : courseList) {

				course.prepareToRemove();
				em.remove(course);
			}
			et.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}

		Student.printTable(em);

	}

}
