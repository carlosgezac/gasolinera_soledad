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
-- Dumping data for table `checador`
--

LOCK TABLES `checador` WRITE;
/*!40000 ALTER TABLE `checador` DISABLE KEYS */;
INSERT INTO `checador` VALUES (36,'2020-10-20 20:05:16.168000','Entrada',30),(37,'2020-10-21 12:17:37.302000','Salida',30),(38,'2020-10-21 12:17:40.176000','Entrada',30),(39,'2020-10-21 12:17:42.421000','Salida',30),(40,'2020-10-21 12:17:44.961000','Entrada',30),(41,'2020-10-21 12:17:49.127000','Salida',30),(42,'2020-10-21 12:17:53.334000','Entrada',30),(43,'2020-10-21 12:17:56.472000','Salida',30),(44,'2020-10-21 12:17:59.950000','Entrada',30),(45,'2020-10-21 12:18:14.504000','Salida',30),(46,'2020-10-21 12:18:19.881000','Entrada',30),(47,'2020-10-21 12:18:28.977000','Salida',30),(48,'2020-10-21 12:23:26.712000','Entrada',30),(49,'2020-10-20 20:24:16.196000','Salida',30),(50,'2020-10-22 07:59:19.841000','Salida',30),(51,'2020-10-22 07:59:43.904000','Entrada',31);
/*!40000 ALTER TABLE `checador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contacto`
--

LOCK TABLES `contacto` WRITE;
/*!40000 ALTER TABLE `contacto` DISABLE KEYS */;
INSERT INTO `contacto` VALUES (129,'4931009752','4931009752','carlos@hotmail.com'),(130,'345','1234234','345345234234'),(145,'e','e','e'),(148,'2','22','2'),(152,'w','carlosw','w'),(154,'e','3425','e');
/*!40000 ALTER TABLE `contacto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `contrato`
--

LOCK TABLES `contrato` WRITE;
/*!40000 ALTER TABLE `contrato` DISABLE KEYS */;
/*!40000 ALTER TABLE `contrato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `direccion`
--

LOCK TABLES `direccion` WRITE;
/*!40000 ALTER TABLE `direccion` DISABLE KEYS */;
INSERT INTO `direccion` VALUES (117,'Fco I Madero','1A','99','Bañuelos','Fresnillo','Zacatecas',NULL),(118,'333','','dfsgdsfg','dfsg','dfgdfgf24234','Zacatecas',NULL),(133,'e','e','e','e','e','Zacatecas',NULL),(136,'2','2','2','2','2','Zacatecas',NULL),(140,'w','w','w','w','wcarlos','Zacatecas',NULL),(142,'e','e','e','e','e','Zacatecas',NULL);
/*!40000 ALTER TABLE `direccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (30,'1','Carlos','Gonzalez','Escobedo','1991-08-31','goec00000000000000','goec000000000','Educación Superior','Cajero','100-5089-9876','Activo','1_1602038416925.jpg','2020-10-08','Masculino','enfermedad','dfgerfg tewrtrewt ertrew twertrew t rewtrewtw rewtrewtwret rewtrewtwret frgewrt rqwewerqwer ertrewtret  wetwret wretwret rewtwret rewtrewt sdrtgrewt tywretret rewtrewt rewtrewt rewtrewtretrew tretrew ','aaaaaaa aaaaaaaaaaaaaa aaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaa aaaaaaaa aaaaaa aaaaaaaa aaaaaaaaa sssssssssss ssssssssssss sssssssssssssssss sssssssssssssssssssssssssssssssssss ssssssssssss','Seleccione la línea del empleado cuyos datos desea visualizar. Seleccione Tratar ® Empl ados ® Mostrar datos adicionales. Aparece la ventana de diálogo Datos del empleado. Seleccione los datos deseado',117,129),(31,'2','pedri','ert','1','2020-10-23','0ipìopòp','trewt','Educación Básica','erterw','3452423','Activo','2_1602038208381.jpg','2019-10-23','Masculino','','','','gsdg12122342134214',118,130),(46,'56','Edduardo','Lopez','Perez','2020-10-20','e','e','Educación Media Superior','e','e','Activo','e_1602047612333.jpg','2020-10-15','Femenino',NULL,NULL,NULL,'ty             ty4y            try',133,145),(49,'6','Eddgardo','Ruiz','Prieto','2020-10-16','2','2','Educación Básica','2','2','Activo','67_1602047643916.jpg','2020-10-29','Femenino',NULL,NULL,NULL,'rfaseasrtew',136,148),(53,'22','w','w','w','2020-10-30','w','w','Educación Básica','w','w','Activo','22_1602047729428.jpg','2020-10-23','Femenino',NULL,NULL,NULL,'dasfdas dasf dasfdas                                   dsf safdsf',140,152),(55,'55','e','ee','35tw','2020-10-28','e','e','Educación Media Superior','e','e','Inactivo','55_1602270346921.jpg','2020-10-21','Femenino',NULL,NULL,NULL,'43255 drftkljrewt kljrlewtwret',142,154);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-10-24  1:30:15
