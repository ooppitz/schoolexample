module de.azubiag.SchoolExample {
  
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.persistence;
	requires java.sql;
	requires org.hibernate.orm.core;
	requires org.hibernate.commons.annotations;
		
   // opens de.azubiag.TestNumber6 to javafx.fxml;
	
	// WICHTIGSTER TRICK
	
	// Hiermit hebelt man weitgehend den 
	// Sicherheitsmechanismus aus
	opens de.azubiag.SchoolExample;
	opens de.azubiag.SchoolExample.app;
	opens de.azubiag.SchoolExample.model;
	opens de.azubiag.SchoolExample.util;
    
    
 
    exports de.azubiag.SchoolExample;
}