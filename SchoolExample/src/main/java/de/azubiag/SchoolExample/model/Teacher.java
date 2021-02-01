package de.azubiag.SchoolExample.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.TypedQuery;

@Entity
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idteacher;

	@Column(name = "firstname")
	String firstname;

	@Column(name = "lastname")
	String lastname;

	@OneToMany( targetEntity=Course.class )
	@JoinTable(name = "jo_teacher_course", 
	joinColumns = @JoinColumn(name = "fk_teacherid"), 
	inverseJoinColumns = @JoinColumn(name = "fk_courseid"))
	
	Set<Course> courses = new HashSet<Course>();

	/** Default constructor for use by Hibernate */
	public Teacher() {
		super();
	}

	/**
	 * Create a teacher object
	 * 
	 * @param fname first name
	 * @param lname last name
	 */
	public Teacher(String fname, String lname) {

		this.firstname = fname;
		this.lastname = lname;
	}

	/**
	 * Add a course to the teacher's courses
	 * 
	 * @param course to add
	 */
	public void add(Course course) {
		this.courses.add(course);
	}

	/**
	 * Return description of the teacher object as String
	 */
	@Override
	public  String toString() {
		String r = "";
		r += this.firstname + " " + this.lastname + " (";
		Set<Course> courseList = this.courses;
		for(Course c : courseList) {
			r += c.getName() + " ";
		}
		r += ")";
		return r;
	}

	/** Prints all the students including the details */
	public static void printTable(EntityManager em) {

		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT t FROM Teacher t WHERE t.idteacher IS NOT NULL";
			TypedQuery<Teacher> query = em.createQuery(queryString, Teacher.class);
			List<Teacher> list = query.getResultList();
			for (Teacher s : list) {
				System.out.println(s);
			}
			System.out.println();

			et.commit();

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
	}
	
	
}
