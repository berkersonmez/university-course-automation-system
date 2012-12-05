DROP TABLE IF EXISTS `student`, `teacher`, `admin`, `user`;
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

INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Berker Sönmez', 'sonmezbe', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Oğuz Onur Kul', 'kulo', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `student` (`userID`, `number`) VALUES (1, 40100101);
INSERT INTO `student` (`userID`, `number`) VALUES (2, 40100105);

INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Teacher1', 'tcr1', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Teacher2', 'tcr2', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `teacher` (`userID`) VALUES (3);
INSERT INTO `teacher` (`userID`) VALUES (4);

INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Admin1', 'admin', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `user` (`name`, `username`, `password`) VALUES ('Admin2', 'admin2', 'e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO `admin` (`userID`) VALUES (5);
INSERT INTO `admin` (`userID`) VALUES (6);