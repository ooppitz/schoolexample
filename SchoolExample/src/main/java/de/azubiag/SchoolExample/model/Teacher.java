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
public class Teacher extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int idteacher;

	@Column(name = "firstname")
	String firstname;

	@Column(name = "lastname")
	String lastname;

	// @OneToMany One Teacher instance can have many Course instances
	@OneToMany(mappedBy = "teacher")
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
	 * Queries the database for a teacher with the passed name.
	 * 
	 * @param firstname of the teacher to search for
	 * @param lastname  of the teacher to search for
	 * @return the course if found, otherwise null
	 */
	public static Teacher find(String firstname, String lastname) {
		List<Teacher> courses = Teacher.getAll();
		for (Teacher t : courses) {
			if (t.getFirstname().equals(firstname) && t.getLastname().equals(lastname)) {
				return t;
			}
		}
		return null;
	}

	/**
	 * Add a course to the teacher's courses
	 * 
	 * @param course to add
	 */
	public void add(Course course) {
		this.courses.add(course);
		course.setTeacher(this);
	}

	/**
	 * Return description of the teacher object as String
	 */
	@Override
	public String toString() {
		String r = "";
		r += this.firstname + " " + this.lastname + " (";
		Set<Course> courseList = this.getCourses();
		for (Course c : courseList) {
			r += c.getName() + " ";
		}
		r += ")";
		return r;
	}

	/**
	 * Queries the table Teacher and returns all records.
	 * 
	 * @return a List of all records
	 */
	public static List<Teacher> getAll() {

		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

		EntityTransaction et = em.getTransaction();
		try {

			et.begin();

			String queryString = "SELECT t FROM Teacher t WHERE t.idteacher IS NOT NULL";
			TypedQuery<Teacher> query = em.createQuery(queryString, Teacher.class);
			List<Teacher> list = query.getResultList();

			et.commit();

			return list;

		} catch (Exception e) {

			e.printStackTrace();
			if (et != null) {
				et.rollback();
			}
		}
		return null;
	}

	/** Prints all the students including the details */
	public static void printTable() {

		for (Teacher s : Teacher.getAll()) {
			System.out.println(s);
		}
		System.out.println();

	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

}
