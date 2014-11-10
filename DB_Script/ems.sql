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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcq_choice`
--

LOCK TABLES `mcq_choice` WRITE;
/*!40000 ALTER TABLE `mcq_choice` DISABLE KEYS */;
INSERT INTO `mcq_choice` VALUES (1,1,'A','public'),(2,1,'B','protected'),(3,1,'C','private'),(4,1,'D','All of the above');
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
INSERT INTO `module` VALUES ('CS1101','Introduction to Programming',0,1),('CS1102','Data Structure and Algorithm',0,1),('CS2101','Computer Organization',0,1),('CS2102','Software Engineering',0,1),('MA1105','Discrete Mathematics',0,1);
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
  `Status` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `q_mcode_idx` (`Module_Code`),
  KEY `q_type_idx` (`Type`),
  KEY `q_uid_idx` (`Created_By`),
  KEY `q_qid_idx` (`QID`),
  CONSTRAINT `q_mcode` FOREIGN KEY (`Module_Code`) REFERENCES `module` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `q_type` FOREIGN KEY (`Type`) REFERENCES `question_type` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `q_uid` FOREIGN KEY (`Created_By`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'CS1101',1,1,1,'2014-11-10 21:53:48',1,1,'Which of these access specifiers can be used for an interface?',NULL);
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
INSERT INTO `user` VALUES (1,'admin','efacc4001e857f7eba4ae781c2932dedf843865e','Tan Ah Kow',1),(2,'lecturer','6a82d9d36486c4ec35be47fa46ccf033f6b207be','Lim Ah Thet',1),(3,'student','7acb896e2328413de34c2eff02398f02eae3c441','Chau Ah Kua',1),(4,'test',NULL,'Allen Wake',1),(5,'tester',NULL,'Shirley Ong',1),(6,'beng',NULL,'Ah Beng',1),(7,'lian',NULL,'Ah Lian',1),(8,'boy',NULL,'Ah Boy',1),(9,'girl',NULL,'Ah Gal',1),(10,'chua',NULL,'Chua Chu Kang',1),(11,'yeo',NULL,'Yeo Chu Kang',1),(12,'ang',NULL,'Ang Ah Moh',1);
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
  CONSTRAINT `um_uid` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `um_mcode` FOREIGN KEY (`Module_Code`) REFERENCES `module` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  CONSTRAINT `ur_uid` FOREIGN KEY (`User_Id`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ur_rid` FOREIGN KEY (`Role_Id`) REFERENCES `role` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
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

-- Dump completed on 2014-11-10 22:20:38
