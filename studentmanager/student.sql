DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `sex` varchar(2) default NULL,
  `age` int default NULL,
  `grade` varchar(20) default NULL,
  `score` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `student` VALUES (1,'张国强','男',22,'12网编2班',80);
INSERT INTO `student` VALUES (2,'张国红','女',21,'12网编1班',91.2);
INSERT INTO `student` VALUES (3,'Tom','F',20,'3',70);
