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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.TypedQuery;

@Entity
public class Course extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCourse;

	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "courses")
	protected Set<Student> students = new HashSet<Student>();

	@ManyToOne
	@JoinColumn(name = "idteacher")
	Teacher teacher;

	/** Default constructor required by hibernate */
	public Course() {
		super();
	}

	/**
	 * Create Course object.
	 * 
	 * @param name of the course
	 */
	public Course(String name) {
		super();
		this.name = name;
	}

	/**
	 * Queries the database for a course with the passed name.
	 * 
	 * @implNote in case of multiple matches, the first match is returned
	 * @implNote inefficient implementation as it reads complete table and iterates
	 *           over it to find a matching element
	 * @return the course if found, otherwise null
	 */

	/*
	 * TODO: use more effecient implementation String queryString =
	 * "SELECT c from Course c WHERE c.name='Französisch'"; TypedQuery<Course> query
	 * = em.createQuery(queryString, Course.class); List<Course> courseList =
	 * query.getResultList();
	 */
	public static Course find(String name) {
		List<Course> courses = Course.getAll();
		for (Course c : courses) {
			if (c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}

	/**
	 * Assigns a teacher to this course
	 * 
	 * @param teacher to assign
	 */
	public void assign(Teacher teacher) {
		this.setTeacher(teacher);
		teacher.getCourses().add(this);
	}

	/**
	 * Assign a student to the course
	 * 
	 * @param student to add
	 */
	public void assign(Student student) {
		this.students.add(student);
		student.getCourses().add(this);
	}

	/**
	 * Remove a student from the course
	 * 
	 * @param student
	 */
	public void remove(Student student) {
		this.students.remove(student);
	}

	/**
	 * Removes references that are kept in the join table. This is required before
	 * being able to remove the object
	 */
	public void prepareToRemove() {

		// From the still attending students remove the reference to the soon invalid
		// course

		System.out.println("Removing all Students from course " + this.getName() + ":");

		Set<Student> studentList = this.getStudents();
		for (Student s : studentList) {
			System.out.println("Removing " + s.getFname() + " " + s.getLname());
			s.remove(this);
		}

		// Remove the references to attending students
		this.students = null;

	}

	/** Overrides the standard toString() method */
	@Override
	public String toString() {

		String result = "";

		String teacherName = (this.getTeacher() != null) ? this.getTeacher().getName() : "";

		result += this.getName() + " [ " + teacherName + " ] (";
		Set<Student> students = this.getStudents();
		for (Student s : students) {
			result += s.getName() + ", ";
		}
		result = result.substring(0, result.length() - 2); // remove last ", "
		result += ")";

		return result;
	}

	/**
	 * Queries the table Course for all records
	 * 
	 * @return all courses or null in case of error
	 */
	public static List<Course> getAll() {

		EntityManager em = Model.em;

		try {

			String queryString = "SELECT c FROM Course c WHERE id IS NOT NULL";
			TypedQuery<Course> query = em.createQuery(queryString, Course.class);
			List<Course> list = query.getResultList();

			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		}

	}

	/** Prints all the courses including the details */
	public static void printTable() {

		for (Course c : Course.getAll()) {
			System.out.println(c);
		}

	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
}
