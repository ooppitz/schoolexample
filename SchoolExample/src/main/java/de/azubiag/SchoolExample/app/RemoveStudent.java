package de.azubiag.SchoolExample.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import de.azubiag.SchoolExample.model.Course;
import de.azubiag.SchoolExample.model.Model;
import de.azubiag.SchoolExample.model.Student;
import de.azubiag.SchoolExample.util.Util;

public class RemoveStudent {

	public static void main(String[] args) {

		EntityTransaction et = Model.em.getTransaction();
		et.begin();

		try {

			System.out.println("=== Courses ===========================");
			Course.printTable();
			System.out.println("=== Students ==========================");
			Student.printTable();

			String queryString = "SELECT s from Student s WHERE s.firstname='Christian'";
			TypedQuery<Student> query = Model.em.createQuery(queryString, Student.class);
			List<Student> studentList = query.getResultList();
			for (Student s : studentList) {
				System.out.println("*** Removing student " + s.getLastname());
				s.remove();
			}
			System.out.println();

			System.out.println("=== Courses ===========================");
			Course.printTable();
			System.out.println("=== Students ==========================");
			Student.printTable();

		} catch (Exception e) {
			e.printStackTrace();
		}

		et.rollback();

	}

}
