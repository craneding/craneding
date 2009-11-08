DROP TABLE IF EXISTS `mydatabase`.`user`;
CREATE TABLE  `mydatabase`.`user` (
  `ID` int(10) unsigned NOT NULL auto_increment,
  `Name` varchar(20) NOT NULL,
  `Password` varchar(32) NOT NULL,
  `Email` varchar(45) default NULL,
  `QQ` varchar(11) default NULL,
  `Telephone` varchar(11) default NULL,
  `Address` varchar(100) default NULL,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ª·‘±±Ì';