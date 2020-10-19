CREATE DATABASE IF NOT EXISTS tinyurldb;
USE tinyurldb;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: tinyurldb
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table card
--

DROP TABLE IF EXISTS card;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE card
(
    id          int NOT NULL AUTO_INCREMENT,

    title
                varchar(50)  DEFAULT NULL,
    description varchar(256) DEFAULT NULL,
    urlId       int NOT NULL,
    groupId     int NOT NULL,
    PRIMARY KEY
        (id
            )
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table card
--

LOCK TABLES card WRITE;
/*!40000 ALTER TABLE card
    DISABLE KEYS */;
INSERT INTO card
VALUES (1, 'Test Card 12', 'Test Card 1 description', 2, 1),
       (2, 'first time creating full card', 'full card', 3, 1),
       (3, 'i am creating a new card to see nothing is broken', 'nothing is broken', 4, 1),
       (4, 'test2234', 'test ', 34, 2),
       (5, 'test 4', 'test 4', 35, 1),
       (6, 'Test 5', 'my demo ke liye', 36, 1),
       (8, 'another test card', 'hello', 40, 2),
       (9, 'Test Card Creation', 'Test Card Creation', 44, 8),
       (10, 'sg', 'sg', 47, 9);
/*!40000 ALTER TABLE card
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table groupadmin
--

DROP TABLE IF EXISTS groupadmin;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE groupadmin
(

    userId
        int
        NOT
            NULL,

    groupId
        int
        NOT
            NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table groupadmin
--

LOCK TABLES groupadmin WRITE;
/*!40000 ALTER TABLE groupadmin
    DISABLE KEYS */;
/*!40000 ALTER TABLE groupadmin
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table groups1
--

DROP TABLE IF EXISTS groups1;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE groups1
(

    id
                int
        NOT
            NULL
        AUTO_INCREMENT,

    groupName
                varchar(100) DEFAULT NULL,
    clusterName varchar(100) DEFAULT NULL,
    tribeName   varchar(100) DEFAULT NULL,
    ftName      varchar(100) DEFAULT NULL,
    PRIMARY KEY
        (id
            )
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table groups1
--

LOCK TABLES groups1 WRITE;
/*!40000 ALTER TABLE groups1
    DISABLE KEYS */;
INSERT INTO groups1
VALUES (1, 'gr', 'cluster', 'tribe', NULL),
       (2, 'test', 'test', 's', 'test');
/*!40000 ALTER TABLE groups1
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table org_group
--

DROP TABLE IF EXISTS org_group;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE org_group
(

    id
                int
        NOT
            NULL
        AUTO_INCREMENT,

    groupName
                varchar(100) DEFAULT NULL,
    clusterName varchar(100) DEFAULT NULL,
    tribeName   varchar(100) DEFAULT NULL,
    ftName      varchar(100) DEFAULT NULL,
    PRIMARY KEY
        (id
            )
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table org_group
--

LOCK TABLES org_group WRITE;
/*!40000 ALTER TABLE org_group
    DISABLE KEYS */;
INSERT INTO org_group
VALUES (1, NULL, 'test', NULL, NULL),
       (2, 'this is a test group so enjoy', 'FRM', 'CCR', 'CM'),
       (3, 'this is a test group 2', 'FRM', 'CCR', 'CM transfo2'),
       (4, 'Test Group 3', 'PRM', 'RFS', 'REA'),
       (5, 'test4', 'test 5', 'ress', 'awetre'),
       (6, 'Test 5', 'Amhi', 'Terra', 'Nova'),
       (7, 'Test 15', 'Test 15', 'Test 15 ', 'Test 15'),
       (8, 'Test Grouping', 'Test Grouping', 'Test Grouping', 'Test Grouping'),
       (9, 'for sg demo', 'sg', 'sg', 'sg');
/*!40000 ALTER TABLE org_group
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table tenant_user
--

DROP TABLE IF EXISTS tenant_user;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE tenant_user
(

    id
        int
                     NOT
                         NULL
        AUTO_INCREMENT,

    email
        varchar(255) NOT NULL,
    PRIMARY KEY
        (id
            )
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table tenant_user
--

LOCK TABLES tenant_user WRITE;
/*!40000 ALTER TABLE tenant_user
    DISABLE KEYS */;
/*!40000 ALTER TABLE tenant_user
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table tenantuser
--

DROP TABLE IF EXISTS tenantuser;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE tenantuser
(

    id
        int
                     NOT
                         NULL
        AUTO_INCREMENT,

    email
        varchar(255) NOT NULL,
    PRIMARY KEY
        (id
            )
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table tenantuser
--

LOCK TABLES tenantuser WRITE;
/*!40000 ALTER TABLE tenantuser
    DISABLE KEYS */;
INSERT INTO tenantuser
VALUES (1, 'rhishikesh.akole@gmail.com'),
       (2, 'rhishikesh.akolein@gmail.com');
/*!40000 ALTER TABLE tenantuser
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table url
--

DROP TABLE IF EXISTS url;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE url
(

    id
             int
                          NOT
                              NULL
        AUTO_INCREMENT,

    url
             varchar(255) NOT NULL,
    prefix   varchar(50) DEFAULT NULL,
    hash     varchar(15)  NOT NULL,
    isActive bit(1)      DEFAULT NULL,
    userId   int         DEFAULT NULL,
    date     date        DEFAULT NULL,
    PRIMARY KEY
        (id
            )
) ENGINE = InnoDB
  AUTO_INCREMENT = 48
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table url
--

LOCK TABLES url WRITE;
/*!40000 ALTER TABLE url
    DISABLE KEYS */;
INSERT INTO url
VALUES (1, 'http://www.ogle.com', 'ccr', 'xxere2', _binary '', NULL, '2020-09-15'),
       (2, 'xyz.com', NULL, 'x23aa', _binary '', NULL, NULL),
       (3, 'https://amazon.com', 'ccr', 'AKKK1w', _binary '', NULL, '2200-09-16'),
       (4, 'http://www.socgen.com', 'crc', 'wR3dhx', _binary '', NULL, '2200-09-16'),
       (6, 'http://www.bbbc.com', NULL, '/5G5g/', _binary '', NULL, '2020-09-23'),
       (7, 'http://amazon.in/433', NULL, 'RppWp4', _binary '', NULL, '2020-09-23'),
       (8, 'http://amazon.in/433', NULL, '/wp4R+', _binary '', NULL, '2020-09-23'),
       (9, 'http://amazon.in/4332332', NULL, 'qrKQdi', _binary '', NULL, '2020-09-23'),
       (10, 'http://amazon.in/433', NULL, 'N8pcx+', _binary '', NULL, '2020-09-23'),
       (11, 'http://www.bbbc.com', NULL, 'GkSkoZ', _binary '', NULL, '2020-09-23'),
       (12, 'http://www.bbbc.com', NULL, 'gAfi', _binary '', NULL, '2020-09-23'),
       (13, 'http://amazon.in/4332332', NULL, 'qiXOKO', _binary '', NULL, '2020-09-23'),
       (14, 'http://www.bbbc.com', NULL, 'z5keYm', _binary '', NULL, '2020-09-23'),
       (15, 'http://www.bbsbc.com', NULL, 'oi772g', _binary '', NULL, '2020-09-23'),
       (16, 'http://www.bbsbc.com', NULL, 'oI7oo7', _binary '', NULL, '2020-09-24'),
       (17, 'http://www.bbsbc.com', NULL, 'QpZvik', _binary '', NULL, '2020-09-24'),
       (18, 'http://www.bbbc.com', NULL, 'mAmWSz', _binary '', NULL, '2020-09-24'),
       (19, 'http://amazon.in/4332332', NULL, 'rOFYqC', _binary '', NULL, '2020-09-24'),
       (20, 'http://www.bbsbc.com/vsdvddsdsf', NULL, 'r2rOPs', _binary '', NULL, '2020-09-24'),
       (21, 'http://www.bbsbc.com/vsdvddsdsf', NULL, 'lHr4', _binary '', NULL, '2020-09-24'),
       (22, 'http://www.bbsbc.com/vsdvddsdsf', NULL, 'p2pDhP', _binary '', NULL, '2020-09-24'),
       (23, 'http://www.alexa.com', NULL, 'sbRs2B', _binary '', NULL, '2020-09-24'),
       (24, 'http://www.alexa.com', NULL, 'Vmb2Ai', _binary '', NULL, '2020-09-24'),
       (25, 'http://www.bbsbc.com', NULL, 'eo9oZQ', _binary '', NULL, '2020-09-24'),
       (26, 'http://www.bbsbc.com', NULL, 'QoQkoL', _binary '', NULL, '2020-09-24'),
       (27, 'http://amazon.in/4332332', NULL, 'KXOOdh', _binary '', NULL, '2020-09-24'),
       (28, 'http://amazon.in/433', NULL, '+B48+4', _binary '', NULL, '2020-09-24'),
       (29, 'http://amazon.in/4332332ss', NULL, 'Tj0wYU', _binary '', NULL, '2020-09-24'),
       (30, 'http://amazon.in/4332332sss', NULL, 'wryXyc', _binary '', NULL, '2020-09-24'),
       (31, 'http://www.bbsbc.com/23232', NULL, '202UaB', _binary '', 1, '2020-09-24'),
       (32, 'http://amazon.in/433', NULL, 'pN848H', _binary '', 1, '2020-09-25'),
       (33, 'http://www.bbbc.com', NULL, 'SSgkZ5', _binary '', 1, '2020-09-25'),
       (34, 'http://socgen.com', 'test', 'K+ETzM', _binary '', NULL, '2200-09-18'),
       (35, 'http://www.reddit.com', 'test 5', 'B21gDT', _binary '', NULL, '2200-09-18'),
       (36, 'http://gmail.com', 'ccr', 'dB4mTy', _binary '', NULL, '2200-09-18'),
       (37, 'https://dashboard.heroku.com/apps', NULL, 'xVlBll', _binary '', 1, '2020-09-25'),
       (38, 'http://dummy.com', NULL, 'e5JP5Y', _binary '', 1, '2020-09-25'),
       (39, 'http://www.facebook.com', NULL, 'VshNRv', _binary '', 1, '2020-09-25'),
       (40, 'https://www.facebook.com', 'prefix', 'YwoIVv', _binary '', NULL, '2200-09-18'),
       (41, 'https://www.quora.com', NULL, 'K66Qy6', _binary '', 1, '2020-10-05'),
       (42, 'https://play.ogle.com/store/apps/details?id=com.kunal.misfit', NULL, 'EENAEO', _binary '', NULL,
        '2020-10-14'),
       (43, 'https://play.ogle.com/', NULL, 'MwMz7f', _binary '', NULL, '2020-10-14'),
       (44, 'https://mail.ogle.com/', 'Test Card Creation', 'wOZgzy', _binary '', NULL, '2200-10-07'),
       (45, 'https://www.w3schools.com/sql/sql_primarykey.ASP', NULL, 'dQJQFO', _binary '', 1, '2020-10-25'),
       (46,
        'https://docs.microsoft.com/en-us/sql/ssms/download-sql-server-management-studio-ssms?view=sql-server-ver15',
        NULL, '+g+8ee', _binary '', 1, '2020-10-25'),
       (47, 'https://www.thegeekstuff.com/2014/03/mssql-to-mysql/', 'sg', 'iOOzmR', _binary '', NULL, '2200-10-18');
/*!40000 ALTER TABLE url
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2020-10-19 22:52:30
