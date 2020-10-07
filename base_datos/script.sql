CREATE DATABASE  IF NOT EXISTS `gasolinera_soledad` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gasolinera_soledad`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: gasolinera_soledad
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `checador`
--

DROP TABLE IF EXISTS `checador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checador` (
  `id_check` int NOT NULL,
  `hora` timestamp(6) NULL DEFAULT NULL,
  `id_tipo_check` int NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_check`),
  KEY `fk_checador_empleado1_idx` (`id_empleado`),
  KEY `fk_checador_tipo_check1_idx` (`id_tipo_check`),
  CONSTRAINT `fk_checador_empleado1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`),
  CONSTRAINT `fk_checador_tipo_check1` FOREIGN KEY (`id_tipo_check`) REFERENCES `tipo_check` (`id_tipo_check`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checador`
--

LOCK TABLES `checador` WRITE;
/*!40000 ALTER TABLE `checador` DISABLE KEYS */;
/*!40000 ALTER TABLE `checador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacto`
--

DROP TABLE IF EXISTS `contacto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contacto` (
  `id_contacto` int NOT NULL AUTO_INCREMENT,
  `telefono_casa` varchar(20) DEFAULT NULL,
  `celular` varchar(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_contacto`)
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
INSERT INTO `contacto` VALUES (129,'TEL CASA','CELULAR1','CORREO'),(130,'345','1','345345'),(145,'e','e','e'),(148,'2','22','2'),(152,'w','carlosw','w');
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direccion`
--

DROP TABLE IF EXISTS `direccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direccion` (
  `id_direccion` int NOT NULL AUTO_INCREMENT,
  `calle` varchar(50) NOT NULL,
  `numero_interior` varchar(10) DEFAULT NULL,
  `numero_exterior` varchar(10) NOT NULL,
  `colonia` varchar(50) NOT NULL,
  `municipio` varchar(50) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `datos_adicionales` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id_direccion`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (117,'CALLE','NUM I','NUM E','COLONIA','MUNICIPIO','Baja California Sur',NULL),(118,'333','21','dfsgdsfg','dfsg','dfgdfgf','Zacatecas',NULL),(133,'e','e','e','e','e','Zacatecas',NULL),(136,'2','2','2','2','2','Zacatecas',NULL),(140,'w','w','w','w','wcarlos','Zacatecas',NULL);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `id_empleado` int NOT NULL AUTO_INCREMENT,
  `numero_empleado` varchar(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido_paterno` varchar(50) NOT NULL,
  `apellido_materno` varchar(50) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `curp` varchar(18) NOT NULL,
  `rfc` varchar(13) NOT NULL,
  `escolaridad` varchar(50) NOT NULL,
  `puesto` varchar(50) NOT NULL,
  `id_turno` int NOT NULL,
  `nss_imss` varchar(45) NOT NULL,
  `estatus` varchar(45) NOT NULL,
  `foto` varchar(250) DEFAULT NULL,
  `fecha_ingreso` date NOT NULL,
  `genero` varchar(45) NOT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `id_direccion` int NOT NULL,
  `id_contacto` int NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `numero_empleado_UNIQUE` (`numero_empleado`),
  KEY `fk_empleado_direccion_idx` (`id_direccion`),
  KEY `fk_empleado_contacto1_idx` (`id_contacto`),
  KEY `fk_empleado_turno1_idx` (`id_turno`),
  CONSTRAINT `fk_empleado_contacto1` FOREIGN KEY (`id_contacto`) REFERENCES `contacto` (`id_contacto`),
  CONSTRAINT `fk_empleado_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direccion` (`id_direccion`),
  CONSTRAINT `fk_empleado_turno1` FOREIGN KEY (`id_turno`) REFERENCES `turno` (`id_turno`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (30,'1','Carlos','Gonzalez','Escobedo','1991-08-31','CURP','RFC','Educación Media Superior','Puesto',2,'NSS','Activo','1_1602038416925.jpg','2020-10-05','Masculino','DATOS ADICf',117,129),(31,'2','ert','ert','1','2020-10-23','33','trewt','Educación Básica','erterw',2,'nss','Activo','2_1602038208381.jpg','2019-10-23','Masculino','gsdg1212',118,130),(46,'e','e','e','e','2020-10-20','e','e','Educación Media Superior','e',1,'e','Activo','e_1602047612333.jpg','2020-10-15','Femenino','e	e',133,145),(49,'67','2','2','2','2020-10-16','2','2','Educación Básica','2',1,'2','Activo','67_1602047643916.jpg','2020-10-29','Femenino','2	',136,148),(53,'22','w','w','w','2020-10-30','w','w','Educación Básica','w',1,'w','Inactivo','22_1602047729428.jpg','2020-10-23','Femenino','w	w	carlos',140,152);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_check`
--

DROP TABLE IF EXISTS `tipo_check`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_check` (
  `id_tipo_check` int NOT NULL,
  `tipo` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tipo_check`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_check`
--

LOCK TABLES `tipo_check` WRITE;
/*!40000 ALTER TABLE `tipo_check` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipo_check` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `turno`
--

DROP TABLE IF EXISTS `turno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `turno` (
  `id_turno` int NOT NULL,
  `turno` varchar(45) NOT NULL,
  PRIMARY KEY (`id_turno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turno`
--

LOCK TABLES `turno` WRITE;
/*!40000 ALTER TABLE `turno` DISABLE KEYS */;
INSERT INTO `turno` VALUES (1,'Matutino'),(2,'Vespertino');
/*!40000 ALTER TABLE `turno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-07  0:24:44
