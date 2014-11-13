CREATE DATABASE  IF NOT EXISTS `ems` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ems`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: ems
-- ------------------------------------------------------
-- Server version	5.6.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mcq_choice`
--

DROP TABLE IF EXISTS `mcq_choice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mcq_choice` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Question_Id` int(11) DEFAULT NULL,
  `Choice` varchar(1) DEFAULT NULL COMMENT 'A, B, C, D, E',
  `Choice_Text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `mcq_qid_idx` (`Question_Id`),
  CONSTRAINT `mcq_qid` FOREIGN KEY (`Question_Id`) REFERENCES `question` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcq_choice`
--

LOCK TABLES `mcq_choice` WRITE;
/*!40000 ALTER TABLE `mcq_choice` DISABLE KEYS */;
INSERT INTO `mcq_choice` VALUES (1,1,'A','public'),(2,1,'B','protected'),(3,1,'C','private'),(4,1,'D','All of the above'),(5,2,'A','upper'),(6,2,'B','super'),(7,2,'C','this'),(8,2,'D','None of the above'),(9,5,'A','Straight merging'),(10,5,'B','Natural merging'),(11,5,'C','Polyphase sort'),(12,5,'D','Distribution of Initial runs'),(13,11,'A','1'),(14,11,'B','n - 1'),(15,11,'C','n log n'),(16,11,'D','n^2'),(17,12,'A','O(n log n) sorts '),(18,12,'B','Divide-and-conquer sorts '),(19,12,'C','Interchange sorts'),(20,12,'D','Average time is quadratic. '),(21,13,'A','21'),(22,13,'B','41'),(23,13,'C','42'),(24,13,'D','43'),(25,14,'A','Each component of the array requires a large amount of memory. '),(26,14,'B','Each component of the array requires a small amount of memory. '),(27,14,'C','The array has only a few items out of place. '),(28,14,'D','The processor speed is fast. '),(29,15,'A','O(log n) '),(30,15,'B','O(n) '),(31,15,'C',' O(n log n) '),(32,15,'D','O(n²) '),(33,16,'A','O(log n) '),(34,16,'B','O(n) '),(35,16,'C',' O(n log n) '),(36,16,'D','O(n²) ');
/*!40000 ALTER TABLE `mcq_choice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `Code` varchar(255) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Question_Count` int(11) DEFAULT '0',
  `Status` int(11) DEFAULT '1',
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES ('CS1101','Introduction to Programming',3,1),('CS1102','Data Structure and Algorithm',12,1),('CS2101','Computer Organization',0,1),('CS2102','Software Engineering',0,1),('MA1105','Discrete Mathematics',0,1);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Module_Code` varchar(255) DEFAULT NULL,
  `QID` int(11) DEFAULT NULL,
  `Version` int(11) DEFAULT '1',
  `Type` int(11) DEFAULT NULL,
  `Created_On` datetime DEFAULT NULL,
  `Created_By` int(11) DEFAULT NULL,
  `Mark` double DEFAULT NULL,
  `Question_Text` text,
  `Status` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`),
  KEY `q_mcode_idx` (`Module_Code`),
  KEY `q_type_idx` (`Type`),
  KEY `q_uid_idx` (`Created_By`),
  KEY `q_qid_idx` (`QID`),
  CONSTRAINT `q_mcode` FOREIGN KEY (`Module_Code`) REFERENCES `module` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `q_type` FOREIGN KEY (`Type`) REFERENCES `question_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `q_uid` FOREIGN KEY (`Created_By`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'CS1101',1,1,1,'2014-11-10 21:53:48',1,1,'Which of these access specifiers can be used for an interface?',1),(2,'CS1101',2,1,1,'2014-11-10 21:53:48',1,1,'Which of these keywords is used to refer to member of base class from a sub class?',1),(3,'CS1101',3,1,3,'2014-11-10 22:53:48',1,2,'What is the difference between StringBuffer and StringBuilder class?',1),(4,'CS1102',1,1,1,'2014-11-10 22:53:48',1,1,'This is a deprecated question',0),(5,'CS1102',1,2,1,'2014-11-10 22:53:48',1,1,'What are the methods available in storing sequential files?',1),(6,'CS1102',2,1,3,'2014-11-10 22:53:48',1,5,'Write two or three clear sentences to describe how a heapsort works. ',1),(7,'CS1102',3,1,3,'2014-11-14 00:57:41',2,5,'Give a concise accurate description of a good way for quicksort to improve its performance by using insertionsort. ',1),(8,'CS1102',4,1,3,'2014-11-14 01:00:48',2,5,'Give a concise accurate description of a good way for quicksort to choose a pivot element. Your approach should be better than \"use the entry at location [0]\". ',1),(9,'CS1102',5,1,3,'2014-11-14 01:02:04',2,5,'Describe a case where quicksort will result in quadratic behavior. ',1),(10,'CS1102',6,1,3,'2014-11-14 01:03:03',2,8,'Suppose that you implement quicksort nonrecursively using a stack, as in your last programming assignment. You use your algorithm to sort an array of 100 items, and at the start of the final iteration of the while loop, the stack contains just two numbers: 10 (on top) and 90 (on bottom). Write one or two clear sentences to describe which parts of the array are sorted at this point, and which parts of the array remain to be sorted. ',1),(11,'CS1102',7,1,1,'2014-11-14 01:05:31',2,2,'In a selectionsort of n elements, how many times is the swap function called in the complete execution of the algorithm? ',1),(12,'CS1102',8,1,1,'2014-11-14 01:06:12',2,2,'Selectionsort and quicksort both fall into the same category of sorting algorithms. What is this category? ',1),(13,'CS1102',9,1,1,'2014-11-14 01:06:42',2,2,'Suppose that a selectionsort of 100 items has completed 42 iterations of the main loop. How many items are now guaranteed to be in their final spot (never to be moved again)?',1),(14,'CS1102',10,1,1,'2014-11-14 01:07:10',2,2,'When is insertionsort a good choice for sorting an array? ',1),(15,'CS1102',11,1,1,'2014-11-14 01:11:35',2,2,'What is the worst-case time for mergesort to sort an array of n elements? ',1),(16,'CS1102',12,1,1,'2014-11-14 01:12:25',2,2,'What is the worst-case time for quicksort to sort an array of n elements? ',1);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_tag`
--

DROP TABLE IF EXISTS `question_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_tag` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `qt_qid_idx` (`question_id`),
  KEY `qt_tid_idx` (`tag_id`),
  CONSTRAINT `qt_qid` FOREIGN KEY (`question_id`) REFERENCES `question` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `qt_tid` FOREIGN KEY (`tag_id`) REFERENCES `subject_tag` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_tag`
--

LOCK TABLES `question_tag` WRITE;
/*!40000 ALTER TABLE `question_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `question_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_type`
--

DROP TABLE IF EXISTS `question_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question_type` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_type`
--

LOCK TABLES `question_type` WRITE;
/*!40000 ALTER TABLE `question_type` DISABLE KEYS */;
INSERT INTO `question_type` VALUES (1,'MCQS','Multiple choice single answer'),(2,'MCQM','Multiple choice multiple answer'),(3,'Essay','Written answer'),(4,'Multipart','One or more MCQ or written');
/*!40000 ALTER TABLE `question_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Administrator','Administrator',1),(2,'Lecturer','Lecturer',1),(3,'Student','Student',1);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_tag`
--

DROP TABLE IF EXISTS `subject_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject_tag` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Tag` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_tag`
--

LOCK TABLES `subject_tag` WRITE;
/*!40000 ALTER TABLE `subject_tag` DISABLE KEYS */;
INSERT INTO `subject_tag` VALUES (1,'Java',1),(2,'Algorithm',1),(3,'Data Structure',1),(4,'C#',1),(5,'Software Engineering',1),(6,'Testing',1),(7,'Peer Review',1),(8,'ISO9001',1),(9,'Software Quality Management',1),(10,'Networking',1),(11,'Software Architecture',1),(12,'Business Analysis',1),(13,'System Analysis',1),(14,'Software Requirement Engineering',1),(15,'Software Project Management',1),(16,'Python',1);
/*!40000 ALTER TABLE `subject_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT '1',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','efacc4001e857f7eba4ae781c2932dedf843865e','Tan Ah Kow',1),(2,'lecturer','6a82d9d36486c4ec35be47fa46ccf033f6b207be','Lim Ah Thet',1),(3,'student','7acb896e2328413de34c2eff02398f02eae3c441','Chau Ah Kua',1),(4,'test',NULL,'Allen Wake',0),(5,'tester',NULL,'Shirley Ong',1),(6,'beng',NULL,'Ah Beng',1),(7,'lian',NULL,'Ah Lian',1),(8,'boy',NULL,'Ah Boy',1),(9,'girl',NULL,'Ah Gal',1),(10,'chua',NULL,'Chua Chu Kang',1),(11,'yeo',NULL,'Yeo Chu Kang',1),(12,'ang',NULL,'Ang Ah Moh',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_module`
--

DROP TABLE IF EXISTS `user_module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_module` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Id` int(11) DEFAULT NULL,
  `Module_Code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `user_uid_idx` (`User_Id`),
  KEY `module_mcode_idx` (`Module_Code`),
  CONSTRAINT `um_mcode` FOREIGN KEY (`Module_Code`) REFERENCES `module` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `um_uid` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_module`
--

LOCK TABLES `user_module` WRITE;
/*!40000 ALTER TABLE `user_module` DISABLE KEYS */;
INSERT INTO `user_module` VALUES (1,2,'CS1101'),(2,2,'CS1102');
/*!40000 ALTER TABLE `user_module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `User_Id` int(11) DEFAULT NULL,
  `Role_Id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `user_uid_idx` (`User_Id`),
  KEY `role_rid_idx` (`Role_Id`),
  CONSTRAINT `ur_rid` FOREIGN KEY (`Role_Id`) REFERENCES `role` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ur_uid` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,2),(5,3,3);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-14  1:21:11
