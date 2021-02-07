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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.TypedQuery;

@Entity
public class Student extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IDSTUDENT;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@ManyToMany
	@JoinTable(name = "jo_student_course", joinColumns = @JoinColumn(name = "fk_studentid"), inverseJoinColumns = @JoinColumn(name = "fk_courseid"))
	Set<Course> courses = new HashSet<>();

	

	/** Default constructor for use by Hibernate */
	public Student() {
		super();
	}

	/** Create a student without an assigned course. */
	public Student(String fname, String lname) {
		this();
		this.firstname = fname;
		this.lastname = lname;
	}

	/**
	 * Create a student object with an assigned course.
	 * 
	 * @param fname first name
	 * @param lname last name
	 */
	public Student(String fname, String lname, Course c) {
		this(fname, lname);
		this.assign(c);
	}

	/**
	 * Add a course to the student's courses
	 * 
	 * @param course to add
	 */
	public void assign(Course course) {
		this.courses.add(course);
		course.getStudents().add(this);
	}

	
	/** Removes the object from the DB and removes all dependencies */
	@Override
	public void prepareRemove() {
		
		// Remove any reference from courses
		for(Course c : this.getCourses()) {
			c.getStudents().remove(this);
		}
		this.courses = null;
	}


	/**
	 * Remove a course from the list of attended courses
	 * 
	 * @param course to remove
	 */
	public void remove(Course course) {
		this.courses.remove(course);
	}

	/** 
	 * Return a string representation of Student, including the assigned courses
	 */
	@Override
	public String toString() {
		String r = this.getFirstname() + " " + this.getLastname() + " (";
		for (Course c : this.courses) {
			r += c.getName() + " ";
		}
		r += ")";
		return r;
	}

	/**
	 * Queries the table Student for all records.
	 * 
	 * @return a list of all records of the table student
	 */
	public static List<Student> getAll() {

		EntityManager em = Model.em;
		try {

			String queryString = "SELECT s FROM Student s WHERE id IS NOT NULL ORDER BY s.lastname";
			TypedQuery<Student> query = em.createQuery(queryString, Student.class);
			List<Student> list = query.getResultList();

			return list;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Prints all the students including the details
	 */
	public static void printTable() {

		for (Student s : Student.getAll()) {
			System.out.println(s);
		}
		System.out.println();
	}

	/**
	 * Finds the object in the DB and returns it
	 * 
	 * @param id of the object to be loaded
	 * @return the found object or null in case of error
	 */

	public static Student find(int id) {

		try {
			String queryString = "SELECT s FROM Student s WHERE id=" + id;
			TypedQuery<Student> query = Model.em.createQuery(queryString, Student.class);
			Student student = query.getSingleResult();
			return student;

		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	public Set<Course> getCourses() {
		return courses;
	}
	
	public String getName() {
		return this.getFirstname() + " " + this.getLastname();
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String fname) {
		this.firstname = fname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLname(String lname) {
		this.lastname = lname;
	}
}