DROP TABLE IF EXISTS `student`, `teacher`, `admin`, `user`, `course`, `open_course`,`class`,`building`,`faculty`, `options`;
CREATE TABLE IF NOT EXISTS `user` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
    `username` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
    `password` varchar(225) COLLATE utf8_unicode_ci NOT NULL, /*MD5 Encrypted*/
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `student` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `userID` int(10) unsigned NOT NULL REFERENCES `user`,
    `number` int(10) unsigned NOT NULL,
    `credit_limit` int(10) unsigned NOT NULL,
    `current_credit` int(10) unsigned NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `number` (`number`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `teacher` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `userID` int(10) unsigned NOT NULL REFERENCES `user`,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userID` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `admin` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `userID` int(10) unsigned NOT NULL REFERENCES `user`,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userID` (`userID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `course` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(100) NOT NULL,
    `code` varchar(8) NOT NULL,
    `credits` smallint(1) unsigned NOT NULL,
    `facultyID` int(10) unsigned NOT NULL REFERENCES `faculty`,
    `length` smallint(5) unsigned NOT NULL, 
    PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

CREATE TABLE IF NOT EXISTS `open_course` (
    `CRN` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `courseID` int(10) NOT NULL REFERENCES `course`,
    `quota` smallint(3) NOT NULL,
    `current_student_count` smallint(3) NOT NULL,
    `teacherID` int(10) unsigned NOT NULL REFERENCES `teacher`,
    `classID` int(10) unsigned NOT NULL REFERENCES `class`,
    `begin_time` time NULL, 
    `end_time` time NULL,
    PRIMARY KEY (`CRN`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10000 ;

CREATE TABLE IF NOT EXISTS `class` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `quota` smallint(3) NOT NULL,
    `name` varchar(100) NOT NULL,
    `buildingID` int(10) NOT NULL REFERENCES `building`,
    `is_lab` boolean NOT NULL, 
    PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `building` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `code` varchar(10) NOT NULL,
    `name` varchar(100) NOT NULL,
    `location` varchar(100) NOT NULL,
    PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `faculty` (
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `building` int(10) NOT NULL REFERENCES `building`,
    `name` varchar(100) NOT NULL,
    `code` varchar(10) NOT NULL,
    PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `options` (
       `isAddDrop` boolean NOT NULL
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE IF NOT EXISTS `student_course` (
       `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
       `userID` int(10) unsigned NOT NULL REFERENCES `user`,
       `CRN` int(10) unsigned NOT NULL REFERENCES `open_course`,
       PRIMARY KEY (`id`)
)ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1;

INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Berker Sönmez', 'sonmezbe', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Oğuz Onur Kul', 'kulo', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `student` (`userID`, `number`, `credit_limit`) VALUES (1, 40100101, 25);
INSERT INTO `student` (`userID`, `number`, `credit_limit`) VALUES (2, 40100105, 30);

INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Teacher1', 'tcr1', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Teacher2', 'tcr2', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `teacher` (`userID`) VALUES (3);
INSERT INTO `teacher` (`userID`) VALUES (4);

INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Admin1', 'admin', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Admin2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `admin` (`userID`) VALUES (5);
INSERT INTO `admin` (`userID`) VALUES (6); 

INSERT INTO `building` (`code`, `name`, `location`) VALUES ('EEB', 'Elektrik Elektronik Fakültesi Binası', 'Maslak Kampüsü'); 
INSERT INTO `building` (`code`, `name`, `location`) VALUES ('FEB', 'Fen Edebiyat Fakültesi Binası', 'Maslak Kampüsü'); 

INSERT INTO `faculty` (`id`, `building`, `name`, `code`) VALUES ('4', 1, 'Elektrik Elektronik Fakültesi', 'EE'); 
INSERT INTO `faculty` (`id`, `building`, `name`, `code`) VALUES ('3', 2, 'Fen Edebiyat Fakültesi', 'FE'); 

INSERT INTO `class` (`quota`, `name`, `buildingID`, `is_lab` ) VALUES ('100', '5301', 1, 0); 
INSERT INTO `class` (`quota`, `name`, `buildingID`, `is_lab` ) VALUES ('120', '5302', 2, 0); 
INSERT INTO `class` (`quota`, `name`, `buildingID`, `is_lab` ) VALUES ('30', 'F-Lab', 2, 1);

INSERT INTO `course` (`name`, `code`, `credits`, `facultyID`, `length` ) VALUES ('Mathematic 1', 'MAT101', 5, 3, 6);
INSERT INTO `course` (`name`, `code`, `credits`, `facultyID`, `length` ) VALUES ('Turkish 1', 'TUR101', 2, 3, 2);

INSERT INTO `open_course` (`courseId`, `quota`, `current_student_count`, `teacherID`, `classID`, `begin_time`, `end_time` ) VALUES (1, 80, 60, 1, 1, '8:30', '10:30');
INSERT INTO `open_course` (`courseId`, `quota`, `current_student_count`, `teacherID`, `classID`, `begin_time`, `end_time` ) VALUES (2, 50, 40, 2, 2, '8:30', '11:30');

INSERT INTO `course` (`name`, `code`, `credits`, `facultyID`, `length`) VALUES ('Database Management Systems', 'BLG361E', 3, 1, 3); 

INSERT INTO `options` (`isAddDrop`) VALUES (true); 