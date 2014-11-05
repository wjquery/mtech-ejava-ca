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
-- Table structure for table `examinstance`
--

DROP TABLE IF EXISTS `examinstance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examinstance` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) DEFAULT NULL,
  `ExamSessionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `instance_uid_idx` (`UserId`),
  KEY `instance_sid_idx` (`ExamSessionId`),
  CONSTRAINT `instance_sid` FOREIGN KEY (`ExamSessionId`) REFERENCES `examsession` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `instance_uid` FOREIGN KEY (`UserId`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examinstance`
--

LOCK TABLES `examinstance` WRITE;
/*!40000 ALTER TABLE `examinstance` DISABLE KEYS */;
/*!40000 ALTER TABLE `examinstance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examsection`
--

DROP TABLE IF EXISTS `examsection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examsection` (
  `Id` int(11) NOT NULL,
  `TemplateId` int(11) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `TotalMarks` double DEFAULT NULL,
  `Type` varchar(1) DEFAULT NULL COMMENT 'A or M',
  PRIMARY KEY (`Id`),
  KEY `section_template_id_idx` (`TemplateId`),
  CONSTRAINT `section_template_id` FOREIGN KEY (`TemplateId`) REFERENCES `examtemplate` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examsection`
--

LOCK TABLES `examsection` WRITE;
/*!40000 ALTER TABLE `examsection` DISABLE KEYS */;
/*!40000 ALTER TABLE `examsection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examsession`
--

DROP TABLE IF EXISTS `examsession`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examsession` (
  `Id` int(11) NOT NULL,
  `TemplateId` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `StartTime` time DEFAULT NULL,
  `Duration` int(11) DEFAULT NULL,
  `Location` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `session_template_id_idx` (`TemplateId`),
  CONSTRAINT `session_template_id` FOREIGN KEY (`TemplateId`) REFERENCES `examtemplate` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examsession`
--

LOCK TABLES `examsession` WRITE;
/*!40000 ALTER TABLE `examsession` DISABLE KEYS */;
/*!40000 ALTER TABLE `examsession` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `examtemplate`
--

DROP TABLE IF EXISTS `examtemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `examtemplate` (
  `Id` int(11) NOT NULL,
  `ModuleCode` varchar(100) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `template_module_code_idx` (`ModuleCode`),
  CONSTRAINT `template_module_code` FOREIGN KEY (`ModuleCode`) REFERENCES `module` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `examtemplate`
--

LOCK TABLES `examtemplate` WRITE;
/*!40000 ALTER TABLE `examtemplate` DISABLE KEYS */;
/*!40000 ALTER TABLE `examtemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instancequestion`
--

DROP TABLE IF EXISTS `instancequestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instancequestion` (
  `Id` int(11) NOT NULL,
  `InstanceSectionId` int(11) DEFAULT NULL,
  `QuestionId` int(11) DEFAULT NULL,
  `Answer` text,
  PRIMARY KEY (`Id`),
  KEY `instanceq_sid_idx` (`InstanceSectionId`),
  KEY `instanceq_qid_idx` (`QuestionId`),
  CONSTRAINT `instanceq_qid` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `instanceq_sid` FOREIGN KEY (`InstanceSectionId`) REFERENCES `instancesection` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instancequestion`
--

LOCK TABLES `instancequestion` WRITE;
/*!40000 ALTER TABLE `instancequestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `instancequestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instancesection`
--

DROP TABLE IF EXISTS `instancesection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `instancesection` (
  `Id` int(11) NOT NULL,
  `InstanceId` int(11) DEFAULT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `TotalMarks` double DEFAULT NULL,
  `Type` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `instancesection_iid_idx` (`InstanceId`),
  CONSTRAINT `instancesection_iid` FOREIGN KEY (`InstanceId`) REFERENCES `examinstance` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instancesection`
--

LOCK TABLES `instancesection` WRITE;
/*!40000 ALTER TABLE `instancesection` DISABLE KEYS */;
/*!40000 ALTER TABLE `instancesection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `module`
--

DROP TABLE IF EXISTS `module`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `module` (
  `Code` varchar(100) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `QuestionCount` int(11) DEFAULT '0',
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `module`
--

LOCK TABLES `module` WRITE;
/*!40000 ALTER TABLE `module` DISABLE KEYS */;
INSERT INTO `module` VALUES ('CS1101','Introduction to Programming',0),('CS1102','Data Structure and Algorithm',0),('CS2101','Computer Organization',0);
/*!40000 ALTER TABLE `module` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `Id` int(11) NOT NULL,
  `ModuleCode` varchar(100) DEFAULT NULL,
  `QID` int(11) DEFAULT NULL,
  `Version` int(11) DEFAULT NULL,
  `Type` int(11) DEFAULT NULL,
  `CreatedOn` datetime DEFAULT NULL,
  `CreatedBy` varchar(100) DEFAULT NULL,
  `Mark` double DEFAULT NULL,
  `QuestionText` text,
  `Status` binary(1) DEFAULT '0',
  PRIMARY KEY (`Id`),
  KEY `module_code_idx` (`ModuleCode`),
  KEY `created_by_idx` (`CreatedBy`),
  KEY `question_type_idx` (`Type`),
  CONSTRAINT `created_by` FOREIGN KEY (`CreatedBy`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `module_code` FOREIGN KEY (`ModuleCode`) REFERENCES `module` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_type` FOREIGN KEY (`Type`) REFERENCES `questiontype` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questiontag`
--

DROP TABLE IF EXISTS `questiontag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questiontag` (
  `Id` int(11) NOT NULL,
  `QuestionId` int(11) DEFAULT NULL,
  `TagId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `questiontag_qid_idx` (`QuestionId`),
  KEY `questiontag_tid_idx` (`TagId`),
  CONSTRAINT `questiontag_qid` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `questiontag_tid` FOREIGN KEY (`TagId`) REFERENCES `subjecttag` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questiontag`
--

LOCK TABLES `questiontag` WRITE;
/*!40000 ALTER TABLE `questiontag` DISABLE KEYS */;
/*!40000 ALTER TABLE `questiontag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questiontype`
--

DROP TABLE IF EXISTS `questiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questiontype` (
  `Id` int(11) NOT NULL,
  `Type` varchar(45) DEFAULT NULL,
  `Description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questiontype`
--

LOCK TABLES `questiontype` WRITE;
/*!40000 ALTER TABLE `questiontype` DISABLE KEYS */;
/*!40000 ALTER TABLE `questiontype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Admin','Administrator'),(2,'Lecturer','Lecturer'),(3,'Student','Student');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sectionquestion`
--

DROP TABLE IF EXISTS `sectionquestion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sectionquestion` (
  `Id` int(11) NOT NULL,
  `SectionId` int(11) DEFAULT NULL,
  `QuestionId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `secionquestion_sid_idx` (`SectionId`),
  KEY `sectionquestion_qid_idx` (`QuestionId`),
  CONSTRAINT `secionquestion_sid` FOREIGN KEY (`SectionId`) REFERENCES `examsection` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sectionquestion_qid` FOREIGN KEY (`QuestionId`) REFERENCES `question` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sectionquestion`
--

LOCK TABLES `sectionquestion` WRITE;
/*!40000 ALTER TABLE `sectionquestion` DISABLE KEYS */;
/*!40000 ALTER TABLE `sectionquestion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sectiontag`
--

DROP TABLE IF EXISTS `sectiontag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sectiontag` (
  `Id` int(11) NOT NULL,
  `SectionId` int(11) DEFAULT NULL,
  `TagId` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `sectiontag_sid_idx` (`SectionId`),
  KEY `sectiontag_tid_idx` (`TagId`),
  CONSTRAINT `sectiontag_sid` FOREIGN KEY (`SectionId`) REFERENCES `examsection` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `sectiontag_tid` FOREIGN KEY (`TagId`) REFERENCES `subjecttag` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sectiontag`
--

LOCK TABLES `sectiontag` WRITE;
/*!40000 ALTER TABLE `sectiontag` DISABLE KEYS */;
/*!40000 ALTER TABLE `sectiontag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjecttag`
--

DROP TABLE IF EXISTS `subjecttag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjecttag` (
  `Id` int(11) NOT NULL,
  `Name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjecttag`
--

LOCK TABLES `subjecttag` WRITE;
/*!40000 ALTER TABLE `subjecttag` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjecttag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `Id` varchar(100) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1','Administrator','admin','password'),('2','Student','student','password');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usermodule`
--

DROP TABLE IF EXISTS `usermodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usermodule` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) DEFAULT NULL,
  `ModuleCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `usermodule_uid_idx` (`UserId`),
  KEY `usermodule_mid_idx` (`ModuleCode`),
  CONSTRAINT `usermodule_mid` FOREIGN KEY (`ModuleCode`) REFERENCES `module` (`Code`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `usermodule_uid` FOREIGN KEY (`UserId`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usermodule`
--

LOCK TABLES `usermodule` WRITE;
/*!40000 ALTER TABLE `usermodule` DISABLE KEYS */;
/*!40000 ALTER TABLE `usermodule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userrole` (
  `Id` int(11) NOT NULL,
  `UserId` varchar(100) NOT NULL,
  `RoleId` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `user_id_idx` (`UserId`),
  KEY `role_id_idx` (`RoleId`),
  CONSTRAINT `role_id` FOREIGN KEY (`RoleId`) REFERENCES `role` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`UserId`) REFERENCES `user` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'1',1),(2,'1',2),(3,'1',3),(4,'2',3);
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-11-06  1:07:58
