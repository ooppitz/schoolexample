package de.azubiag.SchoolExample.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Model {

	/**
	 * The factory is privated in order to force all the code to use the
	 * EntityManager em
	 */
	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");

	/**
	 * This shall be the only EntityManager used in all the application. In this way
	 * all objects read from the DB <b>remain attached</b> and can be used easily in
	 * any way, form and shape
	 */
	public static EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();

	/**
	 * Remove the teacher object from the DB. Before doing so, remove all references
	 * to the teacher object.
	 * 
	 */
	public void remove() {

		prepareRemove();

		// Remove the object from the DB
		em.remove(this);
	}

	/**
	 * Removes references to other objects before the DB objects can be removed
	 * 
	 * @implNote Required for tables connected with join tables. If not required,
	 *           implement an empty method
	 */
	public abstract void prepareRemove();

}
