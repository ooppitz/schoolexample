package de.azubiag.SchoolExample.model;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Model {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("schoolDB");
	
}
