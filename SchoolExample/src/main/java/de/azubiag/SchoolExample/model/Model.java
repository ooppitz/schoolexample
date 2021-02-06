package de.azubiag.SchoolExample.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class Model {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");
	
	public static  EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		
}
