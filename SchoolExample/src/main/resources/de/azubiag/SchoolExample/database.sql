use school;

CREATE DATABASE `school` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE `course` (
  `idcourse` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idcourse`),
  UNIQUE KEY `idcourse_UNIQUE` (`idcourse`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `student` (
  `idstudent` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idstudent`),
  UNIQUE KEY `idstudent_UNIQUE` (`idstudent`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE `jo_student_course` (
  `fk_studentid` int(10) unsigned NOT NULL,
  `fk_courseid` int(10) unsigned NOT NULL,
  KEY `studentid` (`fk_studentid`),
  KEY `courseid` (`fk_courseid`),
  CONSTRAINT `courseid` FOREIGN KEY (`fk_courseid`) REFERENCES `course` (`idcourse`),
  CONSTRAINT `studentid` FOREIGN KEY (`fk_studentid`) REFERENCES `student` (`idstudent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/* drop table school.teacher; */

CREATE TABLE `school`.`teacher` (
  `idteacher` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
   PRIMARY KEY (`idteacher`),
   UNIQUE KEY `idteacher_UNIQUE` (`idteacher`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `school`.`teacher` (`firstname`, `lastname`)
VALUES ( "Rupert", "Froschauer"), ("Karl-Heinz", "Meidinger"), ("Anita", "Fröhlich");

/* drop table teacher_course; */

CREATE TABLE `jo_teacher_course` ( 
  `fk_teacherid` int(10) unsigned NOT NULL,
  `fk_courseid` int(10) unsigned NOT NULL,
  KEY `courseid` (`fk_courseid`),
  KEY `teacherid` (`fk_teacherid`),
  CONSTRAINT `teacher_courseid` FOREIGN KEY (`fk_courseid`) REFERENCES `course` (`idcourse`),
  CONSTRAINT `teacher_teacherid` FOREIGN KEY (`fk_teacherid`) REFERENCES `teacher` (`idteacher`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;








