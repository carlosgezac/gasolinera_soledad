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
  `id_checador` int NOT NULL AUTO_INCREMENT,
  `fecha_hora` timestamp(6) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_checador`),
  KEY `id_empleado_idx` (`id_empleado`),
  CONSTRAINT `id_empleado` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary view structure for view `checadorview`
--

DROP TABLE IF EXISTS `checadorview`;
/*!50001 DROP VIEW IF EXISTS `checadorview`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `checadorview` AS SELECT 
 1 AS `id_checador`,
 1 AS `fecha_hora`,
 1 AS `tipo`,
 1 AS `numero_empleado`,
 1 AS `nombre_empleado`*/;
SET character_set_client = @saved_cs_client;

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
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contrato`
--

DROP TABLE IF EXISTS `contrato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contrato` (
  `id_contrato` int NOT NULL AUTO_INCREMENT,
  `fecha_inicial` date NOT NULL,
  `fecha_final` date DEFAULT NULL,
  `fecha_finiquito` date DEFAULT NULL,
  `id_empleado` int NOT NULL,
  PRIMARY KEY (`id_contrato`),
  KEY `id contrato_idx` (`id_contrato`),
  KEY `id_empleado_idx` (`id_empleado`),
  CONSTRAINT `id_empleado_fk` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

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
  `nss_imss` varchar(45) NOT NULL,
  `estatus` varchar(45) NOT NULL,
  `foto` varchar(250) DEFAULT NULL,
  `fecha_ingreso` date NOT NULL,
  `genero` varchar(45) NOT NULL,
  `enfermedad` varchar(50) DEFAULT NULL,
  `incidentes_laborales` varchar(200) DEFAULT NULL,
  `cursos_capacitacion` varchar(200) DEFAULT NULL,
  `observaciones` varchar(200) DEFAULT NULL,
  `id_direccion` int NOT NULL,
  `id_contacto` int NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `numero_empleado_UNIQUE` (`numero_empleado`),
  KEY `fk_empleado_direccion_idx` (`id_direccion`),
  KEY `fk_empleado_contacto1_idx` (`id_contacto`),
  CONSTRAINT `fk_empleado_contacto1` FOREIGN KEY (`id_contacto`) REFERENCES `contacto` (`id_contacto`),
  CONSTRAINT `fk_empleado_direccion` FOREIGN KEY (`id_direccion`) REFERENCES `direccion` (`id_direccion`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Final view structure for view `checadorview`
--

/*!50001 DROP VIEW IF EXISTS `checadorview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `checadorview` AS select `c`.`id_checador` AS `id_checador`,`c`.`fecha_hora` AS `fecha_hora`,`c`.`tipo` AS `tipo`,`e`.`numero_empleado` AS `numero_empleado`,concat(`e`.`nombre`,' ',`e`.`apellido_paterno`,' ',`e`.`apellido_materno`) AS `nombre_empleado` from (`checador` `c` join `empleado` `e` on((`c`.`id_empleado` = `e`.`id_empleado`))) order by `c`.`fecha_hora` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-16 17:50:19
