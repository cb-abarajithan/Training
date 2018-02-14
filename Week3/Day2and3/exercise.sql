-- MySQL dump 10.13  Distrib 5.6.39, for macos10.13 (x86_64)
--
-- Host: localhost    Database: Reservation_System
-- ------------------------------------------------------
-- Server version	5.6.39

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
-- Table structure for table `Bookings`
--

DROP TABLE IF EXISTS `Bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Bookings` (
  `BookingRefNo` int(11) NOT NULL,
  `RouteId` int(11) DEFAULT NULL,
  `TrainNo` int(11) DEFAULT NULL,
  `CoachCode` int(11) DEFAULT NULL,
  `DateOfJourney` date DEFAULT NULL,
  `DateOfBooking` date DEFAULT NULL,
  `NoOfTickets` int(11) DEFAULT NULL,
  PRIMARY KEY (`BookingRefNo`),
  KEY `RouteId` (`RouteId`),
  KEY `TrainNo` (`TrainNo`),
  KEY `CoachCode` (`CoachCode`),
  CONSTRAINT `bookings_ibfk_1` FOREIGN KEY (`RouteId`) REFERENCES `TrainRouteMaps` (`RouteId`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`TrainNo`) REFERENCES `TrainRouteMaps` (`TrainNo`),
  CONSTRAINT `bookings_ibfk_3` FOREIGN KEY (`CoachCode`) REFERENCES `Coaches` (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Bookings`
--

LOCK TABLES `Bookings` WRITE;
/*!40000 ALTER TABLE `Bookings` DISABLE KEYS */;
INSERT INTO `Bookings` VALUES (0,0,3,3,'2005-01-14','2005-01-01',4),(1,8,2,2,'2005-02-14','2005-01-20',10),(2,5,3,4,'2005-08-14','2005-07-01',9),(3,3,1,1,'2005-07-14','2005-06-18',5),(4,2,2,4,'2005-06-14','2005-06-19',8),(5,6,1,1,'2005-01-14','2005-01-16',7),(6,9,3,2,'2005-05-14','2005-05-01',11),(7,1,2,1,'2005-09-14','2005-09-09',3),(8,2,1,2,'2005-11-14','2005-10-09',14),(9,4,3,3,'2005-10-14','2005-09-09',6),(10,10,4,4,'2005-10-14','2005-10-13',10),(11,15,4,3,'2005-11-14','2005-11-01',4),(12,14,4,3,'2005-05-14','2005-05-02',1),(13,11,4,3,'2005-09-14','2005-08-05',8),(14,13,4,2,'2005-04-14','2005-03-05',12),(15,12,4,1,'2005-05-14','2005-05-09',1);
/*!40000 ALTER TABLE `Bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Coaches`
--

DROP TABLE IF EXISTS `Coaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Coaches` (
  `CoachCode` int(11) NOT NULL,
  `CostPerKm` float(5,2) DEFAULT NULL,
  PRIMARY KEY (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Coaches`
--

LOCK TABLES `Coaches` WRITE;
/*!40000 ALTER TABLE `Coaches` DISABLE KEYS */;
INSERT INTO `Coaches` VALUES (1,2.00),(2,4.00),(3,6.00),(4,10.00);
/*!40000 ALTER TABLE `Coaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Routes`
--

DROP TABLE IF EXISTS `Routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Routes` (
  `RouteId` int(11) NOT NULL,
  `OriginStationId` int(11) DEFAULT NULL,
  `DestinationStationId` int(11) DEFAULT NULL,
  `DistanceInKms` int(11) DEFAULT NULL,
  PRIMARY KEY (`RouteId`),
  KEY `OriginStationId` (`OriginStationId`),
  KEY `DestinationStationId` (`DestinationStationId`),
  CONSTRAINT `routes_ibfk_1` FOREIGN KEY (`OriginStationId`) REFERENCES `Stations` (`StationId`),
  CONSTRAINT `routes_ibfk_2` FOREIGN KEY (`DestinationStationId`) REFERENCES `Stations` (`StationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Routes`
--

LOCK TABLES `Routes` WRITE;
/*!40000 ALTER TABLE `Routes` DISABLE KEYS */;
INSERT INTO `Routes` VALUES (0,1,2,480),(1,2,1,480),(2,1,3,500),(3,3,1,550),(4,1,4,700),(5,4,1,720),(6,2,3,250),(7,3,2,220),(8,2,4,400),(9,4,2,388),(10,1,4,600),(11,2,4,456),(12,3,4,550),(13,4,2,456),(14,4,1,610),(15,4,3,556),(16,5,1,600),(17,1,5,600),(18,2,5,400),(19,5,3,460),(20,4,5,332);
/*!40000 ALTER TABLE `Routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrainCoaches`
--

DROP TABLE IF EXISTS `TrainCoaches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrainCoaches` (
  `TrainNo` int(11) DEFAULT NULL,
  `CoachCode` int(11) DEFAULT NULL,
  `NoOfSeats` int(11) DEFAULT NULL,
  KEY `TrainNo` (`TrainNo`),
  KEY `CoachCode` (`CoachCode`),
  CONSTRAINT `traincoaches_ibfk_1` FOREIGN KEY (`TrainNo`) REFERENCES `Trains` (`TrainNo`),
  CONSTRAINT `traincoaches_ibfk_2` FOREIGN KEY (`CoachCode`) REFERENCES `Coaches` (`CoachCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrainCoaches`
--

LOCK TABLES `TrainCoaches` WRITE;
/*!40000 ALTER TABLE `TrainCoaches` DISABLE KEYS */;
INSERT INTO `TrainCoaches` VALUES (1,1,100),(1,2,100),(1,3,200),(1,4,300),(2,1,100),(2,2,130),(2,3,220),(2,4,350),(3,1,100),(3,2,150),(3,3,250),(3,4,280),(4,1,110),(4,2,150),(4,3,210),(4,4,260);
/*!40000 ALTER TABLE `TrainCoaches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TrainRouteMaps`
--

DROP TABLE IF EXISTS `TrainRouteMaps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TrainRouteMaps` (
  `RouteId` int(11) NOT NULL DEFAULT '0',
  `TrainNo` int(11) NOT NULL DEFAULT '0',
  `ArrivalTime` time DEFAULT NULL,
  `DepartureTime` time DEFAULT NULL,
  `DurationInMins` int(5) DEFAULT NULL,
  PRIMARY KEY (`RouteId`,`TrainNo`),
  KEY `TrainNo` (`TrainNo`),
  CONSTRAINT `trainroutemaps_ibfk_1` FOREIGN KEY (`RouteId`) REFERENCES `Routes` (`RouteId`),
  CONSTRAINT `trainroutemaps_ibfk_2` FOREIGN KEY (`TrainNo`) REFERENCES `Trains` (`TrainNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TrainRouteMaps`
--

LOCK TABLES `TrainRouteMaps` WRITE;
/*!40000 ALTER TABLE `TrainRouteMaps` DISABLE KEYS */;
INSERT INTO `TrainRouteMaps` VALUES (0,1,'23:00:00','07:00:00',480),(1,1,'00:00:00','06:00:00',480),(2,3,'23:15:00','08:00:00',550),(3,3,'00:00:00','06:00:00',480),(4,2,'01:00:00','08:00:00',480),(5,1,'03:00:00','11:00:00',480),(6,3,'04:00:00','12:00:00',480),(7,1,'06:00:00','08:00:00',480),(8,2,'07:00:00','09:00:00',480),(9,2,'08:00:00','09:30:00',480),(10,4,'12:00:00','18:00:00',360),(11,4,'00:00:00','07:00:00',420),(12,4,'21:00:00','25:00:00',240),(13,1,'22:00:00','08:00:00',600),(14,2,'00:00:00','09:00:00',720),(15,4,'01:00:00','09:00:00',480),(16,4,'12:00:00','16:30:00',270),(17,1,'14:00:00','18:30:00',270),(18,4,'17:00:00','19:30:00',150),(19,1,'16:00:00','20:30:00',270),(20,2,'12:00:00','22:30:00',630);
/*!40000 ALTER TABLE `TrainRouteMaps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trains`
--

DROP TABLE IF EXISTS `Trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trains` (
  `TrainNo` int(11) NOT NULL,
  `TrainName` text,
  PRIMARY KEY (`TrainNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trains`
--

LOCK TABLES `Trains` WRITE;
/*!40000 ALTER TABLE `Trains` DISABLE KEYS */;
INSERT INTO `Trains` VALUES (1,'Pandiyan Express'),(2,'Chennai Express'),(3,'Pearl Express'),(4,'Kolkata Express');
/*!40000 ALTER TABLE `Trains` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `UserId` int(11) NOT NULL,
  `LoginId` int(11) DEFAULT NULL,
  `LPassword` text,
  PRIMARY KEY (`UserId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stations`
--

DROP TABLE IF EXISTS `stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stations` (
  `StationId` int(11) NOT NULL,
  `StationCode` text,
  PRIMARY KEY (`StationId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stations`
--

LOCK TABLES `stations` WRITE;
/*!40000 ALTER TABLE `stations` DISABLE KEYS */;
INSERT INTO `stations` VALUES (1,'MDU'),(2,'CHN'),(3,'BLR'),(4,'MUM'),(5,'KOAA');
/*!40000 ALTER TABLE `stations` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-14 19:22:19
