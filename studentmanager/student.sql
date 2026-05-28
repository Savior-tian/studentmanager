CREATE DATABASE IF NOT EXISTS `students` DEFAULT CHARACTER SET gbk;
USE `students`;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `sex` varchar(2) default NULL,
  `age` int(11) default NULL,
  `grade` varchar(20) default NULL,
  `score` float default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
INSERT INTO `student` VALUES (1,'张国强','男',22,'12网编2班',80);
INSERT INTO `student` VALUES (2,'张国红','女',21,'12网编1班',91.2);
INSERT INTO `student` VALUES (3,'Tom','F',20,'3',70);
