-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.19-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema portalbase
--

CREATE DATABASE IF NOT EXISTS portalbase;
USE portalbase;

--
-- Definition of table `category_level_mapping`
--

DROP TABLE IF EXISTS `category_level_mapping`;
CREATE TABLE `category_level_mapping` (
  `category_level_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `level_no` int(10) NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`category_level_mapping_id`),
  KEY `FK_category_level_mapping_1` (`category_id`),
  CONSTRAINT `FK_category_level_mapping_1` FOREIGN KEY (`category_id`) REFERENCES `category_master` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=495 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category_level_mapping`
--

/*!40000 ALTER TABLE `category_level_mapping` DISABLE KEYS */;
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (316,1,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (317,1,2,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (318,2,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (319,2,2,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (320,3,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (321,3,2,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (322,4,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (323,4,2,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (324,5,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (325,6,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (326,7,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (327,8,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (328,9,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (329,10,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (330,11,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (331,12,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (332,13,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (333,14,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (334,15,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (335,16,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (336,17,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (337,18,1,1,'system','2014-04-12 21:00:18','system','2014-04-12 21:00:18'),
 (338,19,2,1,'system','2014-04-12 21:03:20','system','2014-04-12 21:03:20'),
 (339,19,3,1,'system','2014-04-12 21:03:20','system','2014-04-12 21:03:20');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (340,20,2,1,'system','2014-04-12 21:03:20','system','2014-04-12 21:03:20'),
 (341,20,3,1,'system','2014-04-12 21:03:20','system','2014-04-12 21:03:20'),
 (342,21,2,1,'system','2014-04-12 21:03:20','system','2014-04-12 21:03:20'),
 (343,21,3,1,'system','2014-04-12 21:03:20','system','2014-04-12 21:03:20'),
 (344,22,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (345,23,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (346,24,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (347,26,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (348,27,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (349,28,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (350,25,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (351,25,3,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (352,29,3,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (353,30,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (354,30,3,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (355,31,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (356,32,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (357,33,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (358,34,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (359,35,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (360,35,3,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (361,36,3,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (362,37,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (363,38,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (364,39,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (365,40,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (366,41,3,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (367,42,3,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (368,37,2,1,'system','2014-04-12 21:06:22','system','2014-04-12 21:06:22'),
 (421,43,2,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17'),
 (422,43,3,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17'),
 (423,44,2,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17'),
 (424,44,3,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17'),
 (425,45,2,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17'),
 (426,46,2,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17'),
 (427,47,2,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (428,48,3,1,'system','2014-04-12 21:08:17','system','2014-04-12 21:08:17'),
 (429,49,2,1,'system','2014-04-12 21:09:32','system','2014-04-12 21:09:32'),
 (430,50,2,1,'system','2014-04-12 21:09:32','system','2014-04-12 21:09:32'),
 (431,51,2,1,'system','2014-04-12 21:09:32','system','2014-04-12 21:09:32'),
 (432,52,2,1,'system','2014-04-12 21:09:32','system','2014-04-12 21:09:32'),
 (433,53,2,1,'system','2014-04-12 21:10:06','system','2014-04-12 21:10:06'),
 (434,54,2,1,'system','2014-04-12 21:10:06','system','2014-04-12 21:10:06'),
 (435,55,2,1,'system','2014-04-12 21:10:06','system','2014-04-12 21:10:06'),
 (436,56,2,1,'system','2014-04-12 21:10:06','system','2014-04-12 21:10:06'),
 (437,57,2,1,'system','2014-04-12 21:10:06','system','2014-04-12 21:10:06'),
 (438,58,2,1,'system','2014-04-12 21:10:56','system','2014-04-12 21:10:56'),
 (439,59,2,1,'system','2014-04-12 21:10:56','system','2014-04-12 21:10:56');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (440,60,2,1,'system','2014-04-12 21:10:56','system','2014-04-12 21:10:56'),
 (441,61,2,1,'system','2014-04-12 21:10:56','system','2014-04-12 21:10:56'),
 (442,62,2,1,'system','2014-04-12 21:10:56','system','2014-04-12 21:10:56'),
 (443,63,2,1,'system','2014-04-12 21:10:56','system','2014-04-12 21:10:56'),
 (444,64,2,1,'system','2014-04-12 21:10:56','system','2014-04-12 21:10:56'),
 (459,65,2,1,'system','2014-04-12 21:12:00','system','2014-04-12 21:12:00'),
 (460,66,2,1,'system','2014-04-12 21:12:18','system','2014-04-12 21:12:18'),
 (461,67,2,1,'system','2014-04-12 21:12:31','system','2014-04-12 21:12:31'),
 (462,68,2,1,'system','2014-04-12 21:12:40','system','2014-04-12 21:12:40'),
 (463,69,2,1,'system','2014-04-12 21:12:49','system','2014-04-12 21:12:49'),
 (464,70,2,1,'system','2014-04-12 21:12:57','system','2014-04-12 21:12:57'),
 (473,71,2,1,'system','2014-04-12 21:15:22','system','2014-04-12 21:15:22');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (474,72,2,1,'system','2014-04-12 21:15:29','system','2014-04-12 21:15:29'),
 (475,73,2,1,'system','2014-04-12 21:15:36','system','2014-04-12 21:15:36'),
 (476,74,2,1,'system','2014-04-12 21:15:43','system','2014-04-12 21:15:43'),
 (477,75,2,1,'system','2014-04-12 21:17:04','system','2014-04-12 21:17:04'),
 (478,76,2,1,'system','2014-04-12 21:17:04','system','2014-04-12 21:17:04'),
 (479,77,2,1,'system','2014-04-12 21:17:04','system','2014-04-12 21:17:04'),
 (480,71,2,1,'system','2014-04-12 21:17:04','system','2014-04-12 21:17:04'),
 (481,79,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (482,80,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (483,81,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (484,82,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (485,83,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02');
INSERT INTO `category_level_mapping` (`category_level_mapping_id`,`category_id`,`level_no`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (486,84,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (487,85,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (488,86,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (489,75,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (490,88,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (491,89,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (492,90,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (493,91,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02'),
 (494,92,2,1,'system','2014-04-12 21:19:02','system','2014-04-12 21:19:02');
/*!40000 ALTER TABLE `category_level_mapping` ENABLE KEYS */;


--
-- Definition of table `category_master`
--

DROP TABLE IF EXISTS `category_master`;
CREATE TABLE `category_master` (
  `category_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_code` varchar(25) DEFAULT NULL,
  `category_name` varchar(60) DEFAULT NULL,
  `category_description` varchar(100) DEFAULT NULL,
  `seo_title` varchar(100) DEFAULT NULL,
  `seo_keyword` varchar(100) DEFAULT NULL,
  `seo_description` text,
  `image_url` varchar(4000) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category_master`
--

/*!40000 ALTER TABLE `category_master` DISABLE KEYS */;
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,'CLOHTING','Clothing','Clothing','Clothing.jpg','Clothing','Clothing',NULL,1,'system','2014-04-12 20:55:57','admin','2016-10-22 23:52:37'),
 (2,'FOOTWEAR','Footwear','Footwear','Footwear','Footwear','Footwear','Footwear.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (3,'WATCHES','Watches','Watches','Watches','Watches','Watches','Watches.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (4,'SUNGLASSES','SunGlasses','SunGlasses','SunGlasses','SunGlasses','SunGlasses','SunGlasses.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (5,'BAGS','Bags','Bags','Bags','Bags','Bags','Bags.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (6,'BELTS','Belts','Belts','Belts','Belts','Belts','Belts.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (7,'WALLET','Wallet','Wallet','Wallet','Wallet','Wallet','Wallet.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (8,'GROOMING_&_WELLNESS','Grooming & Wellness','Grooming & Wellness','Grooming & Wellness','Grooming & Wellness','Grooming & Wellness','Grooming_&_Wellness.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (9,'JEWELLERY','Jewellery','Jewellery','Jewellery','Jewellery','Jewellery','Jewellery.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (10,'GIRLS','Girls','Girls','Girls','Girls','Girls','Girls.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (11,'BOYS','Boys','Boys','Boys','Boys','Boys','Boys.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (12,'DIAPERS','Diapers','Diapers','Diapers','Diapers','Diapers','Diapers.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (13,'COMPUTER_&_ASSECORIES','Computer & Accessories','Computer & Accessories','Computer & Accessories','Computer & Accessories','Computer & Accessories','Computer & Accessories.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (14,'MOBILES','Mobiles','Mobiles','Mobiles','Mobiles','Mobiles','Mobiles.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (15,'TABLETS','Tablets','Tablets','Tablets','Tablets','Tablets','Tablets.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (16,'HOME_EQUIPMENT','Home Equipment','Home Equipment','Home Equipment','Home Equipment','Home Equipment','Home_Equipment.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (17,'CAMERA','Camera','Camera','Camera','Camera','Camera','Camera.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (18,'AUDIO_&_VIDEO','Audio & Video','Audio & Video','Audio & Video','Audio & Video','Audio & Video','Audio_&_Video.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (19,'JEANS','Jeans','Jeans','Jeans','Jeans','Jeans','Jeans.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (20,'SHIRTS','Shirts','Shirts','Shirts','Shirts','Shirts','Shirts.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (21,'T_SHIRTS','T Shirts','T Shirts','T Shirts','T Shirts','T Shirts','T_Shirts.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (22,'TROUSERS','Trousers','Trousers','Trousers','Trousers','Trousers','Trousers.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (23,'SPORTS_WEAR','Sports Wear','Sports Wear','Sports Wear','Sports Wear','Sports Wear','Sports_Wear.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (24,'CARGO_SHORTS','Cargo Shorts','Cargo Shorts','Cargo Shorts','Cargo Shorts','Cargo Shorts','Cargo_Shorts.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (25,'DRESSES_&_SKIRTS','Dresses & Skirts','Dresses & Skirts','Dresses & Skirts','Dresses & Skirts','Dresses & Skirts','Dresses_&_Skirts.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (26,'SHIRTS_TOPS_&_TUNICS','Shirts, Tops & Tunics','Shirts, Tops & Tunics','Shirts, Tops & Tunics','Shirts, Tops & Tunics','Shirts, Tops & Tunics','Shirts_Tops_&_Tunics.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (27,'SAREES','Sarees','Sarees','Sarees','Sarees','Sarees','Sarees.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (28,'JEANS_&_SHORTS','Jeans & Shorts','Jeans & Shorts','Jeans & Shorts','Jeans & Shorts','Jeans & Shorts','Jeans & Shorts.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (29,'TEES_&_TOPS','Tees & Tops','Tees & Tops','Tees & Tops','Tees & Tops','Tees & Tops','Tees_&_Tops.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (30,'FLIP_FLOP','Flip Flop','Flip Flop','Flip Flop','Flip Flop','Flip Flop','Flip_Flop.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (31,'SNEAKERS','Sneakers','Sneakers','Sneakers','Sneakers','Sneakers','Sneakers.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (32,'RUNNING_SHOES','Running Shoes','Running Shoes','Running Shoes','Running Shoes','Running Shoes','Running_Shoes.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (33,'LOAFERS','Loafers','Loafers','Loafers','Loafers','Loafers','Loafers.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (34,'SPORTS_SHOES','Sports Shoes','Sports Shoes','Sports Shoes','Sports Shoes','Sports Shoes','Sports_Shoes.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (35,'CAUSAL_SHOES','Causal Shoes','Causal Shoes','Causal Shoes','Causal Shoes','Causal Shoes','Causal_Shoes.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (36,'FORMAL_SHOES','Formal Shoes','Formal Shoes','Formal Shoes','Formal Shoes','Formal Shoes','Formal_Shoes.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (37,'FLATS','Flats','Flats','Flats','Flats','Flats','Flats.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (38,'HEELS','Heels','Heels','Heels','Heels','Heels','Heels.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (39,'BELLIES','Bellies','Bellies','Bellies','Bellies','Bellies','Bellies.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (40,'WEDGES','Wedges','Wedges','Wedges','Wedges','Wedges','Wedges.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (41,'SANDALS','Sandals','Sandals','Sandals','Sandals','Sandals','Sandals.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (42,'CLOGS','Clogs','Clogs','Clogs','Clogs','Clogs','Clogs.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (43,'FASTTRACK','FastTrack','FastTrack','FastTrack','FastTrack','FastTrack','FastTrack.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (44,'TITAN','Titan','Titan','Titan','Titan','Titan','Titan.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (45,'CASIO','Casio','Casio','Casio','Casio','Casio','Casio.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (46,'FOSSIL','Fossil','Fossil','Fossil','Fossil','Fossil','Fossil.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (47,'CITIZEN','Citizen','Citizen','Citizen','Citizen','Citizen','Citizen.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (48,'RADO','Rado','Rado','Rado','Rado','Rado','Rado.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (49,'AVIATOR','Aviator','Aviator','Aviator','Aviator','Aviator','Aviator.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (50,'WAYFARER','Way Farer','Way Farer','Way Farer','Way Farer','Way Farer','Way_Farer.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (51,'OVER-SIZED','Over Sized','Over Sized','Over Sized','Over Sized','Over Sized','Over_Sized.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (52,'OVAL','Oval','Oval','Oval','Oval','Oval','Oval.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (53,'LAPTOP_BAGS','Laptop Bags','Laptop Bags','Laptop Bags','Laptop Bags','Laptop Bags','Laptop_Bags.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (54,'BACK_PACKS','Back Packs','Back Packs','Back Packs','Back Packs','Back Packs','Back_Packs.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (55,'HAND_BAGS','Hand Bags','Hand Bags','Hand Bags','Hand Bags','Hand Bags','Hand_Bags.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (56,'TOTES','Totes','Totes','Totes','Totes','Totes','Totes.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (57,'SLING_BAGS','Sling Bags','Sling Bags','Sling Bags','Sling Bags','Sling Bags','Sling_Bags.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (58,'SHAVING','Shaving','Shaving','Shaving','Shaving','Shaving','Shaving.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (59,'SKIN_CARE','Skin Care','Skin Care','Skin Care','Skin Care','Skin Care','Skin_Care.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (60,'HAIR_CARE','Hair Care','Hair Care','Hair Care','Hair Care','Hair Care','Hair_Care.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (61,'BATH_&_SPA','Bath & Spa','Bath & Spa','Bath & Spa','Bath & Spa','Bath & Spa','Bath_Spa.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (62,'EARRINGS','Earrings','Earrings','Earrings','Earrings','Earrings','Earrings.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (63,'PENDANTS_&_LOCKETS','Pendant & Lockets','Pendant & Lockets','Pendant & Lockets','Pendant & Lockets','Pendant & Lockets','Pendant_&_Lockets.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (64,'RINGS','Rings','Rings','Rings','Rings','Rings','Rings.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (65,'EXTERNAL_HARD_DISK','External Hard Disk','External Hard Disk','External Hard Disk','External Hard Disk','External Hard Disk','External_Hard_Disk.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (66,'INTERNAL_HARD_DISK','Internal Hard Disk','Internal Hard Disk','Internal Hard Disk','Internal Hard Disk','Internal Hard Disk','Internal_Hard_Disk.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (67,'MOUSE','Mouse','Mouse','Mouse','Mouse','Mouse','Mouse.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (68,'MONITOR','Monitor','Monitor','Monitor','Monitor','Monitor','Monitor.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (69,'MOTHERBOARD','MotherBoard','MotherBoard','MotherBoard','MotherBoard','MotherBoard','MotherBoard.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (70,'PEN_DRIVES','Pen Drives','Pen Drives','Pen Drives','Pen Drives','Pen Drives','Pen_Drives.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (71,'SAMSUNG','Samsung','Samsung','Samsung','Samsung','Samsung','Samsung.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (72,'MOTOROLA','Motorola','Motorola','Motorola','Motorola','Motorola','Motorola.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (73,'LG','LG','LG','LG','LG','LG','LG.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (74,'MICROMAX','MicroMax','MicroMax','MicroMax','MicroMax','MicroMax','MicroMax.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (75,'SONY','Sony','Sony','Sony','Sony','Sony','Sony.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (76,'iPAD','iPAD','iPAD','iPAD','iPAD','iPAD','iPAD.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (77,'NEXUS7','Nexus 7','Nexus 7','Nexus 7','Nexus 7','Nexus 7','Nexus_7.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (79,'VACCUM_CLEANER','Vaccum Cleaner','Vaccum Cleaner','Vaccum Cleaner','Vaccum Cleaner','Vaccum Cleaner','Vaccum_Cleaner.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (80,'LIGHTNING','Lightning','Lightning','Lightning','Lightning','Lightning','Lightning.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (81,'IRONS','Irons','Irons','Irons','Irons','Irons','Irons.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (82,'SANDWICH_&_ROTI_MAKER','Sandwich & Roti Maker','Sandwich & Roti Maker','Sandwich & Roti Maker','Sandwich & Roti Maker','Sandwich & Roti Maker','Sandwich_&_Roti_Maker.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (83,'INDUCTION_COOK_TOPS','Induction Cook Tops','Induction Cook Tops','Induction Cook Tops','Induction Cook Tops','Induction Cook Tops','Induction_Cook_Tops.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (84,'MIXER/JUICER/GRINDER','Mixer/Juicer/Grinder','Mixer/Juicer/Grinder','Mixer/Juicer/Grinder','Mixer/Juicer/Grinder','Mixer/Juicer/Grinder','Mixer/Juicer/Grinder.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
INSERT INTO `category_master` (`category_id`,`category_code`,`category_name`,`category_description`,`seo_title`,`seo_keyword`,`seo_description`,`image_url`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (85,'CANON','Canon','Canon','Canon','Canon','Canon','Canon.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (86,'NIKON','Nikon','Nikon','Nikon','Nikon','Nikon','Nikon.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (88,'DSLR','DSLR','DSLR','DSLR','DSLR','DSLR','DSLR.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (89,'iPODS','iPods','iPods','iPods','iPods','iPods','iPods.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (90,'MP3_PLAYER','Mp3 Player','Mp3 Player','Mp3 Player','Mp3 Player','Mp3 Player','Mp3_Player.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (91,'SPEAKERS','Speakers','Speakers','Speakers','Speakers','Speakers','Speakers.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57'),
 (92,'VIDEO_PLAYER','Video Player','Video Player','Video Player','Video Player','Video Player','Video_Player.jpg',1,'system','2014-04-12 20:55:57','system','2014-04-12 20:55:57');
/*!40000 ALTER TABLE `category_master` ENABLE KEYS */;


--
-- Definition of table `category_property_mapping`
--

DROP TABLE IF EXISTS `category_property_mapping`;
CREATE TABLE `category_property_mapping` (
  `category_property_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `property_id` int(10) unsigned NOT NULL,
  `property_sequence` int(10) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`category_property_mapping_id`),
  KEY `FK_category_property_mapping_1` (`category_id`),
  KEY `FK_category_property_mapping_2` (`property_id`),
  CONSTRAINT `FK_category_property_mapping_1` FOREIGN KEY (`category_id`) REFERENCES `category_master` (`category_id`),
  CONSTRAINT `FK_category_property_mapping_2` FOREIGN KEY (`property_id`) REFERENCES `property_master` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category_property_mapping`
--

/*!40000 ALTER TABLE `category_property_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_property_mapping` ENABLE KEYS */;


--
-- Definition of table `category_size_mapping`
--

DROP TABLE IF EXISTS `category_size_mapping`;
CREATE TABLE `category_size_mapping` (
  `category_size_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `category_id` int(10) unsigned NOT NULL,
  `size_id` int(10) unsigned NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`category_size_mapping_id`),
  KEY `FK_core_category_size_mapping_1` (`category_id`),
  KEY `FK_core_category_size_mapping_2` (`size_id`),
  CONSTRAINT `FK_core_category_size_mapping_1` FOREIGN KEY (`category_id`) REFERENCES `category_master` (`category_id`),
  CONSTRAINT `FK_core_category_size_mapping_2` FOREIGN KEY (`size_id`) REFERENCES `core_size_master` (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category_size_mapping`
--

/*!40000 ALTER TABLE `category_size_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `category_size_mapping` ENABLE KEYS */;


--
-- Definition of table `core_billing_address`
--

DROP TABLE IF EXISTS `core_billing_address`;
CREATE TABLE `core_billing_address` (
  `billing_address_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `address_line3` varchar(100) DEFAULT NULL,
  `country_code` varchar(25) DEFAULT NULL,
  `state_code` varchar(25) DEFAULT NULL,
  `city_code` varchar(25) DEFAULT NULL,
  `zip_code` varchar(25) DEFAULT NULL,
  `email_1` varchar(50) DEFAULT NULL,
  `email_2` varchar(50) DEFAULT NULL,
  `contact_person_1` varchar(400) DEFAULT NULL,
  `contact_person_2` varchar(400) DEFAULT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`billing_address_id`),
  KEY `FK_core_billing_address_1` (`country_code`),
  KEY `FK_core_billing_address_2` (`state_code`),
  KEY `FK_core_billing_address_3` (`city_code`),
  CONSTRAINT `FK_core_billing_address_1` FOREIGN KEY (`country_code`) REFERENCES `core_country_master` (`country_code`),
  CONSTRAINT `FK_core_billing_address_2` FOREIGN KEY (`state_code`) REFERENCES `core_state_master` (`state_code`),
  CONSTRAINT `FK_core_billing_address_3` FOREIGN KEY (`city_code`) REFERENCES `core_city_master` (`city_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_billing_address`
--

/*!40000 ALTER TABLE `core_billing_address` DISABLE KEYS */;
INSERT INTO `core_billing_address` (`billing_address_id`,`address_line1`,`address_line2`,`address_line3`,`country_code`,`state_code`,`city_code`,`zip_code`,`email_1`,`email_2`,`contact_person_1`,`contact_person_2`,`landmark`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (5,'104, Marawah House','Marwah Marg',NULL,'IN','MAH','MU','400072','vsantlal@yahoo.com',NULL,'9658658',NULL,'Tata Power House','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 (6,'4th floor, span land mark','Chakala',NULL,'IN','MAH','MU','400096','santlal.vishwakarma1989@gmail.com',NULL,'5856969',NULL,'TSD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
/*!40000 ALTER TABLE `core_billing_address` ENABLE KEYS */;


--
-- Definition of table `core_charges_apply`
--

DROP TABLE IF EXISTS `core_charges_apply`;
CREATE TABLE `core_charges_apply` (
  `core_charges_apply_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `charges_mode` varchar(45) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `mode_desciption` varchar(500) DEFAULT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(100) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`core_charges_apply_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `core_charges_apply`
--

/*!40000 ALTER TABLE `core_charges_apply` DISABLE KEYS */;
INSERT INTO `core_charges_apply` (`core_charges_apply_id`,`charges_mode`,`is_active`,`mode_desciption`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,'Yes',1,'Choose yes if you plan to apply separate charges for different categories and countries on shopping cart page.','system','2013-04-08 08:40:56','tsd','2013-04-08 08:40:56'),
 (2,'No',0,'Choose No if you do not plan to apply any charges on the Shopping cart page.','system','2013-04-08 08:40:56','tsd','2013-04-08 08:40:56'),
 (3,'Default',0,'Choose Default if you plan to apply deafult charges for all countries and for all products at category level 1.','system','2013-04-08 08:40:56','tsd','2013-04-08 08:40:56');
/*!40000 ALTER TABLE `core_charges_apply` ENABLE KEYS */;


--
-- Definition of table `core_city_master`
--

DROP TABLE IF EXISTS `core_city_master`;
CREATE TABLE `core_city_master` (
  `city_code` varchar(25) NOT NULL,
  `city_name` varchar(60) NOT NULL,
  `city_description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `state_code` varchar(25) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`city_code`),
  UNIQUE KEY `UK_core_city_master_1` (`city_code`),
  KEY `FK_core_city_master_1` (`state_code`),
  CONSTRAINT `FK_core_city_master_1` FOREIGN KEY (`state_code`) REFERENCES `core_state_master` (`state_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_city_master`
--

/*!40000 ALTER TABLE `core_city_master` DISABLE KEYS */;
INSERT INTO `core_city_master` (`city_code`,`city_name`,`city_description`,`is_active`,`state_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MU','Mumbai','Mumbai',1,'MAH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PU','Pune','Pune',1,'MAH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SU','Surat','Surat',1,'GJ','system','2014-09-28 19:19:15','system','2014-09-28 19:19:15');
/*!40000 ALTER TABLE `core_city_master` ENABLE KEYS */;


--
-- Definition of table `core_country_master`
--

DROP TABLE IF EXISTS `core_country_master`;
CREATE TABLE `core_country_master` (
  `country_code` varchar(25) NOT NULL,
  `country_name` varchar(60) NOT NULL,
  `country_description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `country_code_iso3` varchar(100) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`country_code`),
  UNIQUE KEY `UK_core_country_master_1` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_country_master`
--

/*!40000 ALTER TABLE `core_country_master` DISABLE KEYS */;
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('AD','Andorra','Andorra',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AE','United Arab Emirates','United Arab Emirates',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AF','Afghanistan','Afghanistan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AG','Antigua and Barbuda','Antigua and Barbuda',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AI','Anguilla','Anguilla',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AL','Albania','Albania',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AM','Armenia','Armenia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AN','Netherlands Antilles','Netherlands Antilles',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AO','Angola','Angola',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('AQ','Antarctica','Antarctica',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AR','Argentina','Argentina',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AS','American Samoa','American Samoa',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AT','Austria','Austria',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AU','Australia','Australia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AW','Aruba','Aruba',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('AZ','Azerbaijan','Azerbaijan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BA','Bosnia and Herzegovina','Bosnia and Herzegovina',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BB','Barbados','Barbados',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BD','Bangladesh','Bangladesh',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BE','Belgium','Belgium',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BF','Burkina Faso','Burkina Faso',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BG','Bulgaria','Bulgaria',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BH','Bahrain','Bahrain',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BI','Burundi','Burundi',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BJ','Benin','Benin',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BM','Bermuda','Bermuda',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BN','Brunei','Brunei',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BO','Bolivia','Bolivia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BR','Brazil','Brazil',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BS','Bahamas','Bahamas',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BT','Bhutan','Bhutan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BV','Bouvet Island','Bouvet Island',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BW','Botswana','Botswana',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BY','Belarus','Belarus',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('BZ','Belize','Belize',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CA','Canada','Canada',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CC','Cocos (Keeling) Islands','Cocos (Keeling) Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CD','Congo','Congo',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CF','Central African Republic','Central African Republic',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CG','Congo','Congo',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CH','Switzerland','Switzerland',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CI','C?te d?Ivoire','C?te d?Ivoire',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CK','Cook Islands','Cook Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CL','Chile','Chile',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CM','Cameroon','Cameroon',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CN','China','China',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CO','Colombia','Colombia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CR','Costa Rica','Costa Rica',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CU','Cuba','Cuba',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CV','Cape Verde','Cape Verde',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CX','Christmas Island','Christmas Island',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CY','Cyprus','Cyprus',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('CZ','Czech Republic','Czech Republic',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('DE','Germany','Germany',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('DF','Default','Default',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('DJ','Djibouti','Djibouti',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('DK','Denmark','Denmark',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('DM','Dominica','Dominica',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('DO','Dominican Republic','Dominican Republic',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('DZ','Algeria','Algeria',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('EC','Ecuador','Ecuador',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('EE','Estonia','Estonia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('EG','Egypt','Egypt',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('EH','Western Sahara','Western Sahara',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ER','Eritrea','Eritrea',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ES','Spain','Spain',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ET','Ethiopia','Ethiopia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('EU','Europe','Europe',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('FI','Finland','Finland',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('FJ','Fiji Islands','Fiji Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('FK','Falkland Islands','Falkland Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('FM','Micronesia','Micronesia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('FO','Faroe Islands','Faroe Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('FR','France','France',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('GA','Gabon','Gabon',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GB','United Kingdom','United Kingdom',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GD','Grenada','Grenada',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GE','Georgia','Georgia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GF','French Guiana','French Guiana',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GH','Ghana','Ghana',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GI','Gibraltar','Gibraltar',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GL','Greenland','Greenland',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GM','Gambia','Gambia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GN','Guinea','Guinea',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('GP','Guadeloupe','Guadeloupe',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GQ','Equatorial Guinea','Equatorial Guinea',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GR','Greece','Greece',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GS','South Georgia and the Sou','South Georgia and the South Sandwich Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GT','Guatemala','Guatemala',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GU','Guam','Guam',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GW','Guinea-Bissau','Guinea-Bissau',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('GY','Guyana','Guyana',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('HK','Hong Kong','Hong Kong',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('HM','Heard Island and McDonald','Heard Island and McDonald Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('HN','Honduras','Honduras',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('HR','Croatia','Croatia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('HT','Haiti','Haiti',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('HU','Hungary','Hungary',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ID','Indonesia','Indonesia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('IE','Ireland','Ireland',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('IL','Israel','Israel',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('IN','India','India',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('IO','British Indian Ocean Terr','British Indian Ocean Territory',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('IQ','Iraq','Iraq',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('IR','Iran','Iran',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('IS','Iceland','Iceland',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('IT','Italy','Italy',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('JM','Jamaica','Jamaica',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('JO','Jordan','Jordan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('JP','Japan','Japan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KE','Kenya','Kenya',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KG','Kyrgyzstan','Kyrgyzstan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KH','Cambodia','Cambodia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KI','Kiribati','Kiribati',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KM','Comoros','Comoros',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KN','Saint Kitts and Nevis','Saint Kitts and Nevis',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KP','North Korea','North Korea',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KR','South Korea','South Korea',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KW','Kuwait','Kuwait',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KY','Cayman Islands','Cayman Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('KZ','Kazakstan','Kazakstan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LA','Laos','Laos',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LB','Lebanon','Lebanon',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LC','Saint Lucia','Saint Lucia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LI','Liechtenstein','Liechtenstein',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LK','Sri Lanka','Sri Lanka',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LR','Liberia','Liberia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LS','Lesotho','Lesotho',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LT','Lithuania','Lithuania',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LU','Luxembourg','Luxembourg',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('LV','Latvia','Latvia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LY','Libyan Arab Jamahiriya','Libyan Arab Jamahiriya',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MA','Morocco','Morocco',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MC','Monaco','Monaco',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MD','Moldova','Moldova',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MG','Madagascar','Madagascar',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MH','Marshall Islands','Marshall Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MK','Macedonia','Macedonia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ML','Mali','Mali',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MM','Myanmar','Myanmar',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MN','Mongolia','Mongolia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MO','Macao','Macao',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MP','Northern Mariana Islands','Northern Mariana Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MQ','Martinique','Martinique',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MR','Mauritania','Mauritania',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MS','Montserrat','Montserrat',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MT','Malta','Malta',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MU','Mauritius','Mauritius',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MV','Maldives','Maldives',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MW','Malawi','Malawi',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MX','Mexico','Mexico',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MY','Malaysia','Malaysia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('MZ','Mozambique','Mozambique',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NA','Namibia','Namibia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NC','New Caledonia','New Caledonia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NE','Niger','Niger',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NF','Norfolk Island','Norfolk Island',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NG','Nigeria','Nigeria',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NI','Nicaragua','Nicaragua',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('NL','Netherlands','Netherlands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NO','Norway','Norway',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NP','Nepal','Nepal',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NR','Nauru','Nauru',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NU','Niue','Niue',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('NZ','New Zealand','New Zealand',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('OM','Oman','Oman',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PA','Panama','Panama',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PE','Peru','Peru',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PF','French Polynesia','French Polynesia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PG','Papua New Guinea','Papua New Guinea',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PH','Philippines','Philippines',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PK','Pakistan','Pakistan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PL','Poland','Poland',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PM','Saint Pierre and Miquelon','Saint Pierre and Miquelon',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PN','Pitcairn','Pitcairn',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PR','Puerto Rico','Puerto Rico',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PS','Palestine','Palestine',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PT','Portugal','Portugal',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PW','Palau','Palau',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('PY','Paraguay','Paraguay',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('QA','Qatar','Qatar',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('RE','R?union','R?union',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('RO','Romania','Romania',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('RS','Rest of the world','Rest of the world',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('RU','Russian Federation','Russian Federation',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('RW','Rwanda','Rwanda',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SA','Saudi Arabia','Saudi Arabia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SB','Solomon Islands','Solomon Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SC','Seychelles','Seychelles',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SD','Sudan','Sudan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SE','Sweden','Sweden',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SG','Singapore','Singapore',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SH','Saint Helena','Saint Helena',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SI','Slovenia','Slovenia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SJ','Svalbard and Jan Mayen','Svalbard and Jan Mayen',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SK','Slovakia','Slovakia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SL','Sierra Leone','Sierra Leone',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SM','San Marino','San Marino',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SN','Senegal','Senegal',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SO','Somalia','Somalia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SR','Suriname','Suriname',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ST','Sao Tome and Principe','Sao Tome and Principe',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SV','El Salvador','El Salvador',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SY','Syria','Syria',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('SZ','Swaziland','Swaziland',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TC','Turks and Caicos Islands','Turks and Caicos Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TD','Chad','Chad',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TF','French Southern territori','French Southern territories',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TG','Togo','Togo',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TH','Thailand','Thailand',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TJ','Tajikistan','Tajikistan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TK','Tokelau','Tokelau',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TM','Turkmenistan','Turkmenistan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TN','Tunisia','Tunisia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TO','Tonga','Tonga',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TP','East Timor','East Timor',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TR','Turkey','Turkey',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TT','Trinidad and Tobago','Trinidad and Tobago',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TV','Tuvalu','Tuvalu',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TW','Taiwan','Taiwan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('TZ','Tanzania','Tanzania',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('UA','Ukraine','Ukraine',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('UG','Uganda','Uganda',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('UM','United States Minor Outly','United States Minor Outlying Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('US','United States','United States',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('UY','Uruguay','Uruguay',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('UZ','Uzbekistan','Uzbekistan',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('VA','Holy See (Vatican City St','Holy See (Vatican City State)',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('VC','Saint Vincent and the Gre','Saint Vincent and the Grenadines',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('VE','Venezuela','Venezuela',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('VG','Virgin Islands','Virgin Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('VI','Virgin Islands','Virgin Islands',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('VN','Vietnam','Vietnam',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
INSERT INTO `core_country_master` (`country_code`,`country_name`,`country_description`,`is_active`,`country_code_iso3`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('VU','Vanuatu','Vanuatu',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('WF','Wallis and Futuna','Wallis and Futuna',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('WS','Samoa','Samoa',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('YE','Yemen','Yemen',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('YT','Mayotte','Mayotte',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('YU','Yugoslavia','Yugoslavia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ZA','South Africa','South Africa',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ZM','Zambia','Zambia',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30'),
 ('ZW','Zimbabwe','Zimbabwe',1,NULL,'system','2014-08-07 15:31:30','system','2014-08-07 15:31:30');
/*!40000 ALTER TABLE `core_country_master` ENABLE KEYS */;


--
-- Definition of table `core_courier_master`
--

DROP TABLE IF EXISTS `core_courier_master`;
CREATE TABLE `core_courier_master` (
  `courier_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `courier_code` varchar(25) NOT NULL,
  `courier_name` varchar(60) NOT NULL,
  `courier_description` varchar(255) DEFAULT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(100) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`courier_id`),
  UNIQUE KEY `UK_core_courier_master_1` (`courier_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_courier_master`
--

/*!40000 ALTER TABLE `core_courier_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_courier_master` ENABLE KEYS */;


--
-- Definition of table `core_currency_conversion_mapping`
--

DROP TABLE IF EXISTS `core_currency_conversion_mapping`;
CREATE TABLE `core_currency_conversion_mapping` (
  `currency_conversion_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `parent_currency_code` varchar(25) NOT NULL,
  `mapped_currency_code` varchar(25) NOT NULL,
  `conversion_multiplier` float(15,3) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`currency_conversion_mapping_id`),
  KEY `FK_core_currency_conversion_mapping_1` (`parent_currency_code`),
  KEY `FK_core_currency_conversion_mapping_2` (`mapped_currency_code`),
  CONSTRAINT `FK_core_currency_conversion_mapping_1` FOREIGN KEY (`parent_currency_code`) REFERENCES `core_currency_master` (`currency_code`),
  CONSTRAINT `FK_core_currency_conversion_mapping_2` FOREIGN KEY (`mapped_currency_code`) REFERENCES `core_currency_master` (`currency_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_currency_conversion_mapping`
--

/*!40000 ALTER TABLE `core_currency_conversion_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_currency_conversion_mapping` ENABLE KEYS */;


--
-- Definition of table `core_currency_master`
--

DROP TABLE IF EXISTS `core_currency_master`;
CREATE TABLE `core_currency_master` (
  `currency_code` varchar(25) NOT NULL,
  `currency_name` varchar(60) NOT NULL,
  `currency_symbol` varchar(5) NOT NULL,
  `country_code` varchar(25) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`currency_code`),
  UNIQUE KEY `UK_core_currency_master_1` (`currency_code`),
  KEY `FK_core_currency_master_1` (`country_code`),
  CONSTRAINT `FK_core_currency_master_1` FOREIGN KEY (`country_code`) REFERENCES `core_country_master` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_currency_master`
--

/*!40000 ALTER TABLE `core_currency_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_currency_master` ENABLE KEYS */;


--
-- Definition of table `core_hierarchy_category_charge_details`
--

DROP TABLE IF EXISTS `core_hierarchy_category_charge_details`;
CREATE TABLE `core_hierarchy_category_charge_details` (
  `hierarchy_category_charge_details_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hierarchy_id` int(10) unsigned NOT NULL,
  `category_level_1_id` int(10) unsigned DEFAULT NULL,
  `category_level_2_id` int(10) unsigned DEFAULT NULL,
  `category_level_3_id` int(10) unsigned DEFAULT NULL,
  `category_level_4_id` int(10) unsigned DEFAULT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(100) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`hierarchy_category_charge_details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_hierarchy_category_charge_details`
--

/*!40000 ALTER TABLE `core_hierarchy_category_charge_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_hierarchy_category_charge_details` ENABLE KEYS */;


--
-- Definition of table `core_hierarchy_category_country_charge_mapping`
--

DROP TABLE IF EXISTS `core_hierarchy_category_country_charge_mapping`;
CREATE TABLE `core_hierarchy_category_country_charge_mapping` (
  `hierarchy_category_country_charge_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `country_code` varchar(25) DEFAULT NULL,
  `currency_id` int(10) DEFAULT NULL,
  `hierarchy_category_charge_details_id` int(10) unsigned NOT NULL,
  `delivery_charge` float(15,3) DEFAULT NULL,
  `processing_charge` float(15,3) DEFAULT NULL,
  `duties_charge` float(15,3) DEFAULT NULL,
  `express_charge` float(15,3) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`hierarchy_category_country_charge_mapping_id`),
  KEY `FK_core_hierarchy_category_country_charge_mapping_1` (`country_code`),
  KEY `FK_core_hierarchy_category_country_charge_mapping_2` (`hierarchy_category_charge_details_id`),
  CONSTRAINT `FK_core_hierarchy_category_country_charge_mapping_1` FOREIGN KEY (`country_code`) REFERENCES `core_country_master` (`country_code`),
  CONSTRAINT `FK_core_hierarchy_category_country_charge_mapping_2` FOREIGN KEY (`hierarchy_category_charge_details_id`) REFERENCES `core_hierarchy_category_charge_details` (`hierarchy_category_charge_details_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_hierarchy_category_country_charge_mapping`
--

/*!40000 ALTER TABLE `core_hierarchy_category_country_charge_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_hierarchy_category_country_charge_mapping` ENABLE KEYS */;


--
-- Definition of table `core_newsletter_subscription`
--

DROP TABLE IF EXISTS `core_newsletter_subscription`;
CREATE TABLE `core_newsletter_subscription` (
  `newsletter_subscription_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email_address` varchar(255) NOT NULL,
  `user_login` varchar(100) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`newsletter_subscription_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_newsletter_subscription`
--

/*!40000 ALTER TABLE `core_newsletter_subscription` DISABLE KEYS */;
INSERT INTO `core_newsletter_subscription` (`newsletter_subscription_id`,`email_address`,`user_login`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (3,'vsantlal@yahoo.com','guest','system','2014-12-01 12:48:57','system','2014-12-01 12:48:57');
/*!40000 ALTER TABLE `core_newsletter_subscription` ENABLE KEYS */;


--
-- Definition of table `core_page_navigation`
--

DROP TABLE IF EXISTS `core_page_navigation`;
CREATE TABLE `core_page_navigation` (
  `core_page_navigation_id` int(11) NOT NULL AUTO_INCREMENT,
  `page_code` varchar(50) NOT NULL,
  `page_url` varchar(500) NOT NULL,
  `page_display_name` varchar(100) NOT NULL,
  `role_ids` varchar(1000) DEFAULT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(100) NOT NULL,
  `modified_date` datetime NOT NULL,
  `page_title` varchar(500) DEFAULT NULL,
  `page_contents_href` varchar(500) DEFAULT NULL,
  `page_contents_title` varchar(500) DEFAULT NULL,
  `page_start_href` varchar(500) DEFAULT NULL,
  `page_start_title` varchar(500) DEFAULT NULL,
  `page_canonical_href` varchar(500) DEFAULT NULL,
  `page_canonical_title` varchar(500) DEFAULT NULL,
  `page_description` varchar(500) DEFAULT NULL,
  `page_keywords` varchar(500) DEFAULT NULL,
  `page_abstract` varchar(500) DEFAULT NULL,
  `page_robots` varchar(500) DEFAULT NULL,
  `page_type` varchar(500) DEFAULT NULL,
  `seo_enabled` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`core_page_navigation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=217 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `core_page_navigation`
--

/*!40000 ALTER TABLE `core_page_navigation` DISABLE KEYS */;
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (0,'RETAIL_START_PAGE','/ui/retail/modules/home/home.xhtml','','','system','2011-10-17 13:27:55','system','2011-10-17 13:27:55','Zodiac',NULL,NULL,NULL,NULL,NULL,NULL,'Description stuff','Keywords Stuff',NULL,'index,follow',NULL,0),
 (179,'/','/ui/retail/modules/home/home.jsf','                ','','system','2011-10-17 13:27:55','optimus','2013-11-19 15:46:18','White Shirts, Premium Branded Shirts, Formal Office and Business Shirts India : Zodiac Online ',NULL,NULL,NULL,NULL,NULL,NULL,'Buy best white shirts, business shirts, linen shirts, best office wear, formal shirts from the wide range of premium branded shirts by Zodiac at great prices.','zodiac shirts, zodiac, white shirts, business shirts, white shirts india, formal shirts, business shirts, office shirts, premium shirts, branded shirts','Premium Branded Quality Shirts, Formal Office and Business Shirts India : Zodiac Online ','index,follow',NULL,1),
 (182,'/retail/information/termsandconditions','/ui/retail/modules/static/termsandconditions.xhtml','                ',NULL,'system','2011-10-21 20:21:20','optimus','2013-11-19 15:46:18','Formal Office Wear | Buy formal shirts | Shirts online | Terms and Conditions Zodiac Shirts','/retail/information',NULL,'/retail',NULL,'/retail/information/termsandconditions',NULL,'Buy formal office wear, office shirts for men by Zodiac. Read the entire terms and conditions before buying formal shirts from our website','zodiac, zodiac shirts, zodiac terms and conditions, buy shirts online, buy formal shirts, buy office formal wear','Terms and Conditions -Zodiac','index,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (183,'/retail/information/shippingpolicy','/ui/retail/modules/static/shippingpolicy.xhtml','                ',NULL,'system','2011-10-21 20:21:20','optimus','2013-11-19 15:46:18','Shipping and Delivery Policy -Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/shippingpolicy',NULL,NULL,'shipping and delivery policy','Shipping Policy - Zodiac','index,follow',NULL,1),
 (184,'/retail/information/privacypolicy','/ui/retail/modules/static/privacypolicy.xhtml','                ',NULL,'system','2011-10-21 20:21:20','optimus','2013-11-19 15:46:18','Privacy Policy - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/privacypolicy',NULL,NULL,'privacy policy, zodiac','Privacy Policy - Zodiac','index,follow',NULL,1),
 (185,'/retail/information/returnpolicy','/ui/retail/modules/static/returnpolicy.xhtml','                ',NULL,'system','2011-10-21 20:21:20','optimus','2013-11-19 15:46:18','Return and Exchange Policy - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/returnpolicy',NULL,NULL,'return and exchange policy , zodiac','Zodiac return Policy','index,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (186,'BROWSE_PRODUCTS_PAGE','/ui/retail/modules/browseproducts/browseproducts.xhtml','~product.categories~ - Products                 ','','system','2011-10-25 15:47:46','optimus','2013-11-19 15:46:18','Best White Shirts : Buy Shirts Online, Shirts India, Linen Shirts, Office Shirts, Men\'s Clothing Online , Buy Online Casual Shirts for Men in India | Zodiac Online,  Mens Fashion Clothes | Shop Online Latest Fashion Mens Clothes| Zodiac Online','/retail/products',NULL,'/retail/products',NULL,'/retail/products/~product.categories.uri~',NULL,'Buy from the wide range of white shirts, formal shirts,  business shirts, linen shirts online for office and causal outing for men.Buy branded casual shirts for men in India from Zodiac Online Store. We provide wide range of shirts like kenton check shirts, crescent shirts and many more at affordable prices.Zodiac Online is a leading online shopping website in India. Here you can get the best deal','white shirts, zodiac white shirts, linen white shirts, best white shirts, business shirts, linen shirts, office shirts, office wear for men, mens clothing line,Casual Shirts, Shirts for Men, Online Clothes Shopping, Mens Fashion, Online Shopping Fashion','~product.categories.reverse~ - Products -  - Zodiac','index,follow',NULL,1),
 (187,'/retail/product','/ui/retail/modules/readmorepanel/readmorewrapper.xhtml','Product Details                ','','system','2011-11-02 17:54:53','optimus','2013-11-19 15:46:18','~product.name~ - Product','/retail/products',NULL,'/retail/products',NULL,'/retail/product/~product.uri~',NULL,'~product.name~ - Product - Zodiac','~product.name~ - Product - Zodiac','~product.name~ - Product -  Zodiac','index,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (189,'SYSTEMOWNER_HOME_PAGE','/ui/systemowner/modules/home/home.jsf','','','system','2011-11-07 10:32:57','system','2011-11-07 10:32:57',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (190,'/admin','/ui/systemowner/modules/home/home.jsf','','','system','2011-11-07 10:34:57','system','2011-11-07 10:34:57',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0),
 (191,'/retail/information/contactus','/ui/common/modules/contactus/contactus.xhtml','                ','','system','2011-11-16 17:11:40','optimus','2013-11-19 15:46:18','Contact Us - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/contactus',NULL,NULL,'contact us, zodiac','Contact Us - Zodiac','index,follow',NULL,1),
 (192,'/retail/shoppingcart','/ui/retail/modules/shoppingcart/shoppingcart.xhtml','Shopping Cart                ','','system','2011-11-19 11:03:19','optimus','2013-11-19 15:46:18','Shopping Cart - Zodiac','/retail',NULL,'/retail',NULL,'/retail/shoppingcart',NULL,'Shopping Cart - Zodiac','Shopping Cart,Zodiac','Shopping Cart - Zodiac','noindex,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (193,'/retail/information/faqs','/ui/retail/modules/static/faqs.xhtml','                ','','system','2011-11-28 10:57:15','optimus','2013-11-19 15:46:18','FAQ\'s','/retail/information',NULL,'/retail',NULL,'/retail/information/faqs',NULL,NULL,NULL,'Faqs - Zodiac','index,follow',NULL,1),
 (194,'/retail/information/careers','/ui/retail/modules/static/careers.xhtml','                ','','system','2011-11-28 10:57:15','optimus','2013-11-19 15:46:18','Careers - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/careers',NULL,'Careers - Zodiac','Careers, Zodiac','Careers - Zodiac','index,follow',NULL,1),
 (197,'/retail/information/aboutus','/ui/retail/modules/static/aboutus.xhtml','               ','','system','2012-01-03 23:32:20','optimus','2013-11-19 15:46:19','Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/aboutus',NULL,'Zodiac','Zodiac','Zodiac','index,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (198,'/retail/information/disclaimer','/ui/retail/modules/static/disclaimer.xhtml','               ','','system','2012-01-03 23:39:48','optimus','2013-11-19 15:46:19','Disclaimer- Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/disclaimer',NULL,'Zodiac','disclaimer, Zodiac','Information about Zodiac','index,follow',NULL,1),
 (199,'/retail/information/refundpolicy','/ui/retail/modules/static/refundpolicy.xhtml','               ','','system','2012-01-03 23:39:48','optimus','2013-11-19 15:46:19','Cancellation and Refund - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/refundpolicy',NULL,'Cancellation and Refund - Zodiac','Cancellation, Refund, Zodiac','Information about Zodiac','index,follow',NULL,1),
 (202,'/retail/information/settingseducation','/ui/retail/modules/static/settings.xhtml','               ',NULL,'system','2012-01-26 23:37:53','optimus','2013-11-19 15:46:19','Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/settingseducation',NULL,'Zodiac','Zodiac','Zodiac','index,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (205,'/retail/information/company','/ui/retail/modules/static/company.xhtml','          ',NULL,'system','2012-10-10 10:52:33','optimus','2013-11-19 15:46:19','Company - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/company',NULL,'Company Zodiac.','company','Company - Zodiac','index,follow',NULL,1),
 (206,'/retail/information/storelocator','/ui/retail/modules/static/storelocator.xhtml','          ',NULL,'system','2012-10-10 10:52:44','optimus','2013-11-19 15:46:19','Store - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/storelocator',NULL,'Store Zodiac.','store','Store - Zodiac','index,follow',NULL,1),
 (207,'/retail/information/styleguide','/ui/retail/modules/static/styleguide.xhtml','                 ',NULL,'system','2012-10-15 19:01:05','optimus','2013-11-19 15:46:19','Style Guide - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/styleguide',NULL,'Style Guide','style guide','Style Guide - Zodiac','index,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (208,'/retail/information/customercare','/ui/retail/modules/static/customercare.xhtml','                 ',NULL,'system','2012-10-15 19:01:20','optimus','2013-11-19 15:46:19','Customer Care - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/customercare',NULL,'Customer Care Zodiac.','customer care','Customer Care - Zodiac','index,follow',NULL,1),
 (210,'/retail/information/investorrelations','/ui/common/modules/displaymedia/financialreports.xhtml','          ',NULL,'system','2012-10-29 20:54:02','optimus','2013-11-19 15:46:19','Investor Relations-Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/investorrelations',NULL,'Investor Relations','investor relations','Investor Relations - Zodiac','index,follow',NULL,1),
 (211,'/retail/information/complaints','/ui/retail/modules/complaints/complaints.xhtml','    ',NULL,'system','2013-01-02 18:58:08','optimus','2013-11-19 15:46:19','Complaints - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/complaints',NULL,'Complaints - Zodiac','Complaints','Complaints about Zodiac','index,follow',NULL,1);
INSERT INTO `core_page_navigation` (`core_page_navigation_id`,`page_code`,`page_url`,`page_display_name`,`role_ids`,`created_by`,`created_date`,`modified_by`,`modified_date`,`page_title`,`page_contents_href`,`page_contents_title`,`page_start_href`,`page_start_title`,`page_canonical_href`,`page_canonical_title`,`page_description`,`page_keywords`,`page_abstract`,`page_robots`,`page_type`,`seo_enabled`) VALUES 
 (216,'/retail/information/trackmycomplaints','/ui/retail/modules/complaints/trackmycomplaints.xhtml','    ',NULL,'system','2013-01-02 19:01:44','optimus','2013-11-19 15:46:19','Track My Complaints - Zodiac','/retail/information',NULL,'/retail',NULL,'/retail/information/trackmycomplaints',NULL,'Track My Complaints - Zodiac','Track My Complaints',' track My Complaints about Zodiac','index,follow',NULL,1);
/*!40000 ALTER TABLE `core_page_navigation` ENABLE KEYS */;


--
-- Definition of table `core_parameter_master`
--

DROP TABLE IF EXISTS `core_parameter_master`;
CREATE TABLE `core_parameter_master` (
  `parameter_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `param_code` varchar(35) NOT NULL,
  `sequence_number` int(4) unsigned NOT NULL,
  `param_description` varchar(255) NOT NULL,
  `value_data_type` char(1) DEFAULT NULL,
  `value_text` varchar(255) DEFAULT NULL,
  `value_numeric` float DEFAULT NULL,
  `value_date` date DEFAULT NULL,
  `record_deleted` int(10) unsigned DEFAULT NULL,
  `effective_from` date DEFAULT NULL,
  `effective_to` date DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`parameter_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_parameter_master`
--

/*!40000 ALTER TABLE `core_parameter_master` DISABLE KEYS */;
INSERT INTO `core_parameter_master` (`parameter_id`,`param_code`,`sequence_number`,`param_description`,`value_data_type`,`value_text`,`value_numeric`,`value_date`,`record_deleted`,`effective_from`,`effective_to`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,'PAYMENT_STATUS',1,'PENDING','S','Pending',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:20:52','system','2014-09-08 20:20:52'),
 (2,'PAYMENT_STATUS',2,'Status for current payment transaction','S','Received',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:20:52','system','2014-09-08 20:20:52'),
 (3,'PAYMENT_STATUS',3,'Status for current payment transaction','S','Rejected',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:20:52','system','2014-09-08 20:20:52'),
 (4,'ORDER_STATUS',1,'Status for current order transaction','S','Order Initiated',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27'),
 (5,'ORDER_STATUS',2,'Status for current order transaction','S','Order Cancelled',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27'),
 (6,'ORDER_STATUS',3,'Status for current order transaction','S','Saved to wishlist',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27');
INSERT INTO `core_parameter_master` (`parameter_id`,`param_code`,`sequence_number`,`param_description`,`value_data_type`,`value_text`,`value_numeric`,`value_date`,`record_deleted`,`effective_from`,`effective_to`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (7,'ORDER_STATUS',4,'Status for current order transaction','S','Payment Initiated',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27'),
 (8,'ORDER_STATUS',5,'Status for current order transaction','S','Order Confirmed',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27'),
 (9,'ORDER_STATUS',6,'Status for current order transaction','S','Shipped',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27'),
 (10,'ORDER_STATUS',7,'Status for current order transaction','S','Delivered',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27'),
 (11,'ORDER_STATUS',8,'Status for current order transaction','S','Order Offline',NULL,NULL,0,'1900-01-01','2999-12-31','system','2014-09-08 20:27:27','system','2014-09-08 20:27:27');
/*!40000 ALTER TABLE `core_parameter_master` ENABLE KEYS */;


--
-- Definition of table `core_role_master`
--

DROP TABLE IF EXISTS `core_role_master`;
CREATE TABLE `core_role_master` (
  `role_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(25) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_role_master`
--

/*!40000 ALTER TABLE `core_role_master` DISABLE KEYS */;
INSERT INTO `core_role_master` (`role_id`,`role_code`,`role_name`,`role_description`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,'RETAIL_USER','Retail User','Retail B2C User','system','2014-09-30 19:59:22','system','2014-09-30 19:59:22'),
 (2,'WHOLESALE_USER','Wholesale User','Wholesale B2B User','system','2014-09-30 19:59:22','system','2014-09-30 19:59:22'),
 (3,'OPERATIONAL_USER','Operational User','Operational User for System Owner Users','system','2014-09-30 19:59:22','system','2014-09-30 19:59:22'),
 (4,'SYSTEM_OWNER','System Owner','System Owner','system','2014-09-30 19:59:22','system','2014-09-30 19:59:22'),
 (5,'ADMINISTRATOR','Administrator','Administrator for System Owner Users','system','2014-09-30 19:59:22','system','2014-09-30 19:59:22'),
 (6,'COMPANY_ADMINISTRATOR','Company Administrator','Company Administrator for Client Company','system','2014-09-30 19:59:22','system','2014-09-30 19:59:22'),
 (7,'REPORTS_USER','Reports User','Reports User for System Owner Users','system','2014-09-30 19:59:22','system','2014-09-30 19:59:22');
/*!40000 ALTER TABLE `core_role_master` ENABLE KEYS */;


--
-- Definition of table `core_roles`
--

DROP TABLE IF EXISTS `core_roles`;
CREATE TABLE `core_roles` (
  `core_roles_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role_code` varchar(25) NOT NULL,
  `role_name` varchar(25) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(100) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`core_roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_roles`
--

/*!40000 ALTER TABLE `core_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_roles` ENABLE KEYS */;


--
-- Definition of table `core_secondry_addresses`
--

DROP TABLE IF EXISTS `core_secondry_addresses`;
CREATE TABLE `core_secondry_addresses` (
  `secondry_addresses_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `secondry_address_name` varchar(50) NOT NULL,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `address_line3` varchar(100) DEFAULT NULL,
  `country_code` varchar(25) DEFAULT NULL,
  `state_code` varchar(25) DEFAULT NULL,
  `city_code` varchar(25) DEFAULT NULL,
  `zip_code` varchar(25) DEFAULT NULL,
  `email_1` varchar(50) DEFAULT NULL,
  `email_2` varchar(50) DEFAULT NULL,
  `contact_person_1` varchar(400) DEFAULT NULL,
  `contact_person_2` varchar(400) DEFAULT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`secondry_addresses_id`),
  KEY `FK_core_secondry_addresses_1` (`country_code`),
  KEY `FK_core_secondry_addresses_2` (`state_code`),
  KEY `FK_core_secondry_addresses_3` (`city_code`),
  CONSTRAINT `FK_core_secondry_addresses_1` FOREIGN KEY (`country_code`) REFERENCES `core_country_master` (`country_code`),
  CONSTRAINT `FK_core_secondry_addresses_2` FOREIGN KEY (`state_code`) REFERENCES `core_state_master` (`country_code`),
  CONSTRAINT `FK_core_secondry_addresses_3` FOREIGN KEY (`city_code`) REFERENCES `core_city_master` (`city_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_secondry_addresses`
--

/*!40000 ALTER TABLE `core_secondry_addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_secondry_addresses` ENABLE KEYS */;


--
-- Definition of table `core_shipping_address`
--

DROP TABLE IF EXISTS `core_shipping_address`;
CREATE TABLE `core_shipping_address` (
  `shipping_address_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address_line1` varchar(100) DEFAULT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `address_line3` varchar(100) DEFAULT NULL,
  `country_code` varchar(25) DEFAULT NULL,
  `state_code` varchar(25) DEFAULT NULL,
  `city_code` varchar(25) DEFAULT NULL,
  `zip_code` varchar(25) DEFAULT NULL,
  `email_1` varchar(50) DEFAULT NULL,
  `email_2` varchar(50) DEFAULT NULL,
  `contact_person_1` varchar(400) DEFAULT NULL,
  `contact_person_2` varchar(400) DEFAULT NULL,
  `landmark` varchar(50) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`shipping_address_id`),
  KEY `FK_core_shipping_address_1` (`country_code`),
  KEY `FK_core_shipping_address_2` (`state_code`),
  KEY `FK_core_shipping_address_3` (`city_code`),
  CONSTRAINT `FK_core_shipping_address_1` FOREIGN KEY (`country_code`) REFERENCES `core_country_master` (`country_code`),
  CONSTRAINT `FK_core_shipping_address_2` FOREIGN KEY (`state_code`) REFERENCES `core_state_master` (`state_code`),
  CONSTRAINT `FK_core_shipping_address_3` FOREIGN KEY (`city_code`) REFERENCES `core_city_master` (`city_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_shipping_address`
--

/*!40000 ALTER TABLE `core_shipping_address` DISABLE KEYS */;
INSERT INTO `core_shipping_address` (`shipping_address_id`,`address_line1`,`address_line2`,`address_line3`,`country_code`,`state_code`,`city_code`,`zip_code`,`email_1`,`email_2`,`contact_person_1`,`contact_person_2`,`landmark`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,'Abhilakh nagar','Lalgi pada',NULL,'IN','MAH','MU','400067','vsantlal@yahoo.com',NULL,'98668956',NULL,'New Link Road','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
/*!40000 ALTER TABLE `core_shipping_address` ENABLE KEYS */;


--
-- Definition of table `core_size_master`
--

DROP TABLE IF EXISTS `core_size_master`;
CREATE TABLE `core_size_master` (
  `size_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `size_code` varchar(25) DEFAULT NULL,
  `size_name` varchar(60) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_size_master`
--

/*!40000 ALTER TABLE `core_size_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_size_master` ENABLE KEYS */;


--
-- Definition of table `core_state_master`
--

DROP TABLE IF EXISTS `core_state_master`;
CREATE TABLE `core_state_master` (
  `state_code` varchar(25) NOT NULL,
  `state_name` varchar(60) NOT NULL,
  `state_description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `country_code` varchar(25) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`state_code`),
  UNIQUE KEY `UK_core_state_master_1` (`state_code`),
  KEY `FK_core_state_master_1` (`country_code`),
  CONSTRAINT `FK_core_state_master_1` FOREIGN KEY (`country_code`) REFERENCES `core_country_master` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_state_master`
--

/*!40000 ALTER TABLE `core_state_master` DISABLE KEYS */;
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('0','ReykjavÃÆÃâÃâ Ã¢â','ReykjavÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­k',1,'IS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('1','Berat','Berat',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('10','ShkodÃÆÃâÃâ Ã¢â¬â','ShkodÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«r',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('101','KÃÆÃâÃâ Ã¢â¬â¢Ã','KÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¸benhavn',1,'DK','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('11','TiranÃÆÃâÃâ Ã¢â¬â','TiranÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('110','Nairobi Municipality','Nairobi Municipality',1,'KE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('12','VlorÃÆÃâÃâ Ã¢â¬â','VlorÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('13','Dhaka zila','Dhaka zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('14','Dinajpur zila','Dinajpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('147','Frederiksberg','Frederiksberg',1,'DK','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('15','Faridpur zila','Faridpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('16','Feni zila','Feni zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('17','Gopalganj zila','Gopalganj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('18','Gazipur zila','Gazipur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('19','Gaibandha zila','Gaibandha zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('2','DurrÃÆÃâÃâ Ã¢â¬â','DurrÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«s',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('20','Habiganj zila','Habiganj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('200','Central','Central',1,'KE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('21','Jamalpur zila','Jamalpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('22','Jessore zila','Jessore zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('23','Jhenaidah zila','Jhenaidah zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('24','Jaipurhat zila','Jaipurhat zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('25','Jhalakati zila','Jhalakati zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('26','Kishorganj zila','Kishorganj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('27','Khulna zila','Khulna zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('28','Kurigram zila','Kurigram zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('29','Khagrachari zila','Khagrachari zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('2A','Corse-du-Sud','Corse-du-Sud',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('2B','Haute-Corse','Haute-Corse',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('3','Elbasan','Elbasan',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('30','Kushtia zila','Kushtia zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('300','Coast','Coast',1,'KE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('31','Lakshmipur zila','Lakshmipur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('32','Lalmonirhat zila','Lalmonirhat zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('33','Manikganj zila','Manikganj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('34','Mymensingh zila','Mymensingh zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('35','Munshiganj zila','Munshiganj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('36','Madaripur zila','Madaripur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('37','Magura zila','Magura zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('38','Moulvibazar zila','Moulvibazar zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('39','Meherpur zila','Meherpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('4','Fier','Fier',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('40','Narayanganj zila','Narayanganj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('400','Eastern','Eastern',1,'KE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('41','Netrakona zila','Netrakona zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('42','Narsingdi zila','Narsingdi zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('43','Narail zila','Narail zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('44','Natore zila','Natore zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('45','Nawabganj zila','Nawabganj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('46','Nilphamari zila','Nilphamari zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('47','Noakhali zila','Noakhali zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('48','Naogaon zila','Naogaon zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('49','Pabna zila','Pabna zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('5','GjirokastÃÆÃâÃâ Ã¢','GjirokastÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«r',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('50','Pirojpur zila','Pirojpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('500','North-Eastern Kaskazini M','North-Eastern Kaskazini Mashariki',1,'KE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('51','Patuakhali zila','Patuakhali zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('52','Panchagarh zila','Panchagarh zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('53','Rajbari zila','Rajbari zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('54','Rajshahi zila','Rajshahi zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('55','Rangpur zila','Rangpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('56','Rangamati zila','Rangamati zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('57','Sherpur zila','Sherpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('58','Satkhira zila','Satkhira zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('59','SirajOanj zila','SirajOanj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('6','KorÃÆÃâÃâ Ã¢â¬â¢','KorÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ§ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('60','Sylhet zila','Sylhet zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('61','SunamOanj zila','SunamOanj zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('62','Shariatpur zila','Shariatpur zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('63','Tangail zila','Tangail zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('64','Thakurgaon zila','Thakurgaon zila',1,'BD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('65','Xinjiang','Xinjiang',1,'CN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('66','PyrÃÆÃâÃâ Ã¢â¬â¢','PyrÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©nÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©es-Orientales',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('67','PÃÆÃâÃâ Ã¢â¬â¢Ã','PÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤rnu County',1,'EE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('68','Haut-Rhin','Haut-Rhin',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('69','RhÃÆÃâÃâ Ã¢â¬â¢Ã','RhÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ´ne',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('7','KurkÃÆÃâÃâ Ã¢â¬â','KurkÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«s',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('70','ÃÆÃâÃâ Ã¢â¬â¢ÃÆ','ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃâÃÂ¢ÃÆÃÂ¢ÃÂ¢Ã¢âÂ¬ÃÂ¡ÃâÃÂ¬ÃÆÃ¢â¬Å¡ÃâÃÂ¦rhus',1,'DK','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('700','Rift Valley','Rift Valley',1,'KE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('71','SaÃÆÃâÃâ Ã¢â¬â¢Ã','SaÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ´ne-et-Loire',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('72','Sarthe','Sarthe',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('73','Savoie','Savoie',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('74','Saare County','Saare County',1,'EE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('75','Paris','Paris',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('76','Viborg','Viborg',1,'DK','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('77','Seine-et-Marne','Seine-et-Marne',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('78','Tartu County','Tartu County',1,'EE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('79','Deux-SÃÆÃâÃâ Ã¢â¬','Deux-SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¨vres',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('8','LezhÃÆÃâÃâ Ã¢â¬â','LezhÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('80','Nordjylland','Nordjylland',1,'DK','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('81','Tarn','Tarn',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('82','Valga County','Valga County',1,'EE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('83','Var','Var',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('84','Viljandi County','Viljandi County',1,'EE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('85','VendÃÆÃâÃâ Ã¢â¬â','VendÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©e',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('86','VÃÆÃâÃâ Ã¢â¬â¢Ã','VÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂµru County',1,'EE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('87','Haute-Vienne','Haute-Vienne',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('88','Vosges','Vosges',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('89','Yonne','Yonne',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('9','DibÃÆÃâÃâ Ã¢â¬â¢','DibÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ«r',1,'AL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('90','Territoire-de-Belfort','Territoire-de-Belfort',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('900','Western Magharibi','Western Magharibi',1,'KE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('91','Hong Kong','Hong Kong',1,'CN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('92','Macau','Macau',1,'CN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('93','Seine-Saint-Denis','Seine-Saint-Denis',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('94','Val-de-Marne','Val-de-Marne',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('95','Val-d\'Oise','Val-d\'Oise',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('96','Narathiwat','Narathiwat',1,'TH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('99','Isla de la Juventud','Isla de la Juventud',1,'CU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('A','Salta','Salta',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('A1','Attiki','Attiki',1,'GR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AA','Addis Ababa','Addis Ababa',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AB','ÃÆÃâÃâ Ã¢â¬â¢ÃÆ','ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃâÃÂ¢ÃÆÃÂ¢ÃÂ¢Ã¢âÂ¬ÃÂ¡ÃâÃÂ¬ÃÆÃ¢â¬Â¦ÃâÃÂ¾li Bayramli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ABD','Aberdeenshire','Aberdeenshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ABE','Aberdeen City','Aberdeen City',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ABS','Abseron','Abseron',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AC','Acre','Acre',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AD','Adamaoua','Adamaoua',1,'CM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AG','Aragacotn','Aragacotn',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AGA','Agstafa','Agstafa',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AGB','Argyll and Bute','Argyll and Bute',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AGC','AgcabÃÆÃâÃâ Ã¢â¬â','AgcabÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤di',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('AGM','Agdam','Agdam',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AGS','Agdas','Agdas',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AGU','Agsu','Agsu',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AGY','Isle of Anglesey','Isle of Anglesey',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AH','Ashanti','Ashanti',1,'GH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AI','AisÃÆÃâÃâ Ã¢â¬â¢','AisÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©n del General Carlos IbÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢',1,'CL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AJ','Ajman','Ajman',1,'AE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('AK','Atakora','Atakora',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AKM','Aqmola','Aqmola',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AKT','Aqtobe','Aqtobe',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AL','Alibori','Alibori',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ALA','Almaty','Almaty',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ALM','Almaty','Almaty',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ALT','Altayakiy kray','Altayakiy kray',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ALX','Al Iskandarlyah','Al Iskandarlyah',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AM','Amazonas','Amazonas',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AMA','Amazonea','Amazonea',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('AMU','Amurskaya oblast\'','Amurskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AN','Antofagasta','Antofagasta',1,'CL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ANC','Ancash','Ancash',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ANS','Angus','Angus',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ANT','Antioquia','Antioquia',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AO','Aosta','Aosta',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AP','Amapa','Amapa',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('APA','Apac','Apac',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('APU','ApurÃÆÃâÃâ Ã¢â¬â','ApurÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­mac',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AQ','Atlantique','Atlantique',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('AR','Ararat','Ararat',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ARA','Arauca','Arauca',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ARD','Ards','Ards',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ARE','Arequipa','Arequipa',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ARM','Armagh','Armagh',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ARR','Arkhangel,akaya oblast\'','Arkhangel,akaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ARU','Arua','Arua',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AS','Ali Sabiah','Ali Sabiah',1,'DJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ASN','Aswan','Aswan',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AST','Astara','Astara',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('AT','Atacama','Atacama',1,'CL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ATL','AtlÃÆÃâÃâ Ã¢â¬â¢','AtlÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡ntico',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ATY','Atyrau','Atyrau',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AUK','Auckland N','Auckland N',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AV','Armavir','Armavir',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AYA','Ayacucho','Ayacucho',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('AZ','Abu Zaby','Abu Zaby',1,'AE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('B','Buenos Aires Province','Buenos Aires Province',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BA','BÃÆÃâÃâ Ã¢â¬â¢Ã','BÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ki',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BAB','BabÃÆÃâÃâ Ã¢â¬â¢','BabÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤k',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BAL','Balkh province','Balkh province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BAM','Bamian province','Bamian province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BAR','BÃÆÃâÃâ Ã¢â¬â¢Ã','BÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤rdÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BAS','Bath and North East Somer','Bath and North East Somerset (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BB','Bubanza','Bubanza',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BBD','Blackburn with Darwen','Blackburn with Darwen',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BBZ','Belize','Belize',1,'BZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BC','British Columbia','British Columbia',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BCN','Baja California','Baja California',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BCS','Baja California Sur','Baja California Sur',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BD','Norrbotten','Norrbotten',1,'SE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BDF','Bedfordshire (county)','Bedfordshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BDG','Badghis province','Badghis province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BDS','Badakhshan province','Badakhshan province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BE','Belait','Belait',1,'BN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BE-VLI','Limburg','Limburg',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BEL','Belgorodakaya oblast\'','Belgorodakaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BEN','Brent (London borough)','Brent (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BEX','Bexley (London borough)','Bexley (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BEY','BeylÃÆÃâÃâ Ã¢â¬â','BeylÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤gan',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BF','Boffa','Boffa',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BFS','Belfast','Belfast',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BG','Bergamo','Bergamo',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BGE','Bridgend','Bridgend',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BGF','Bangui','Bangui',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BGL','Baghlan province','Baghlan province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BGO','Bengo','Bengo',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BGU','Benguela province','Benguela province',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BGW','Blaenau Gwent','Blaenau Gwent',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BH','Barahona','Barahona',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BI','Bimini','Bimini',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BIE','BiÃÆÃâÃâ Ã¢â¬â¢Ã','BiÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BIH','Federacija Bosna i Herceg','Federacija Bosna i Hercegovina',1,'BA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BIL','BilÃÆÃâÃâ Ã¢â¬â¢','BilÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤suvar',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BIR','Birmingham (West Midlands','Birmingham (West Midlands district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BJ','Bujumbura','Bujumbura',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BK','Baase-Kotto','Baase-Kotto',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BKM','Buckinghamshire (county)','Buckinghamshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BL','Basel-Landschaft','Basel-Landschaft',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BLA','Ballymena','Ballymena',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BLY','Ballymoney','Ballymoney',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BM','Brunei-Muara','Brunei-Muara',1,'BN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BMH','Bournemouth','Bournemouth',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BN','Bandundu','Bandundu',1,'CD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BNB','Banbridge','Banbridge',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BNE','Barnet (London borough)','Barnet (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BNH','Brighton and Hove','Brighton and Hove',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BNS','Bani Suwayf','Bani Suwayf',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BO','Borgou','Borgou',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BOL','BolÃÆÃâÃâ Ã¢â¬â¢','BolÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­var',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BOP','Bay of Plenty N','Bay of Plenty N',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BOY','BoyacÃÆÃâÃâ Ã¢â¬â','BoyacÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BP','Bia|a Podlaska','Bia|a Podlaska',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BPL','Blackpool','Blackpool',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BR','Bururi','Bururi',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BRC','Bracknell Forest','Bracknell Forest',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BRD','Bradford (West Yorkshire ','Bradford (West Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BRY','Bromley (London borough)','Bromley (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BS','Basel-Stadt','Basel-Stadt',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BST','Bristol, City of','Bristol, City of',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BT','Banten','Banten',1,'ID','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BU','Burgos','Burgos',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BUN','Bundibugyo','Bundibugyo',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BUR','Bury (Manchester borough)','Bury (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BUS','Bushenyi','Bushenyi',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BV','Boa Vista','Boa Vista',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BW','Baden-WÃÆÃâÃâ Ã¢â','Baden-WÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¼rttemberg',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BY','Bavaria (Bayern)','Bavaria (Bayern)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('BZ','Borsod-AbaÃÆÃâÃâ Ã','Borsod-AbaÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂºj-ZemplÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©n',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('BZV','Brazzaville','Brazzaville',1,'CG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('C','Distrito Federal','Distrito Federal',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('C :','Al Qahirah','Al Qahirah',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CA','Cankuzo','Cankuzo',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAB','Cabinda','Cabinda',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAJ','Cajamarca','Cajamarca',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAL','CÃÆÃâÃâ Ã¢â¬â¢Ã','CÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤lilabad',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAM','Cambridgeshire (county)','Cambridgeshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CAN','Canterbury S','Canterbury S',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAQ','CaquetÃÆÃâÃâ Ã¢â¬','CaquetÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAS','Casanare','Casanare',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAU','Cauca','Cauca',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CAY','Caerphilly','Caerphilly',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CB','Campobasso','Campobasso',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CC','CÃÆÃâÃâ Ã¢â¬â¢Ã','CÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡ceres',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CCU','Cuando-Cubango','Cuando-Cubango',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CE','Ceara','Ceara',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CES','Cesar','Cesar',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CG','Crna Gora','Crna Gora',1,'YU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CGN','Ceredigion','Ceredigion',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CGV','Craigavon','Craigavon',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CH','Chobe','Chobe',1,'BW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CHA','Chagang-do','Chagang-do',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CHE','Chelyabinskaya oblast\'','Chelyabinskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CHH','Chihuahua','Chihuahua',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CHI','Chitinskaya oblast\'','Chitinskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CHO','ChocÃÆÃâÃâ Ã¢â¬â','ChocÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CHP','Chiapas','Chiapas',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CHS','Cheshire (county)','Cheshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CHU','Chukotakiy avtonomnyy okr','Chukotakiy avtonomnyy okrug',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CI','Cibitoke','Cibitoke',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CJ','Cluj','Cluj',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CKF','Carrickfergus','Carrickfergus',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CKT','Cookstown','Cookstown',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CL','Castilla y LeÃÆÃâÃâ','Castilla y LeÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³n',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CLD','Calderdale (West Yorkshir','Calderdale (West Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CLK','Clackmannanshire','Clackmannanshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CLR','Coleraine','Coleraine',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CM','Castilla-La Mancha','Castilla-La Mancha',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CMA','Cumbria (county)','Cumbria (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CMD','Camden (London borough)','Camden (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CMN','Carmarthenshire','Carmarthenshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CN','Canarias','Canarias',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CNN','Cunene','Cunene',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CNO','Cuanza Norte','Cuanza Norte',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CO','Collines','Collines',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('COA','Coahuila','Coahuila',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('COL','Colima','Colima',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CON','Cornwall (county)','Cornwall (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('COR','CÃÆÃâÃâ Ã¢â¬â¢Ã','CÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³rdoba',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('COV','Coventry (West Midlands d','Coventry (West Midlands district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CP','Central','Central',1,'GH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CQ','Chiquimula','Chiquimula',1,'GT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CR','Santa Cruz','Santa Cruz',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CRF','Cardiff','Cardiff',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CRY','Croydon (London borough)','Croydon (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CS','Calheta de SÃÆÃâÃâ','Calheta de SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ£o Miguel',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CSR','Castlereagh','Castlereagh',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CT','Australian Capital Territ','Australian Capital Territory',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CU','Cuenca','Cuenca',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CUL','Culfa','Culfa',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CUN','Cundinamarca','Cundinamarca',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('CUS','Cuanza Sul','Cuanza Sul',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CV','Covasna','Covasna',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CW','Carlow','Carlow',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CWY','Conwy','Conwy',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CY','Cayo','Cayo',1,'BZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CYI','Chisyi','Chisyi',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CZ','Catanzaro','Catanzaro',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('CZL','Corozal','Corozal',1,'BZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('D','San Luis','San Luis',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DA','Dajabon','Dajabon',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('DAL','Darlington (unitary autho','Darlington (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DAS','DaskÃÆÃâÃâ Ã¢â¬â','DaskÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤sÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤n',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DAV','DÃÆÃâÃâ Ã¢â¬â¢Ã','DÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤vÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DB','Dabola','Dabola',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('DBY','Derbyshire (county)','Derbyshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DC','Distrito Capltal de Santa','Distrito Capltal de Santa Fe de BogotÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ£',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DD','Dire Dawa','Dire Dawa',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DE','Demerara-Mahaica','Demerara-Mahaica',1,'GY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DEN','Denbighshire','Denbighshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DER','Derby','Derby',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DEV','Devon (county)','Devon (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('DF','Distrito Federal','Distrito Federal',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DGN','Dungannon','Dungannon',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DGY','Dumfries and Galloway','Dumfries and Galloway',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DH','Dhawalagiri','Dhawalagiri',1,'NP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DI','Dikhil','Dikhil',1,'DJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DIF','Distrito Federal','Distrito Federal',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DJ','Djibouti','Djibouti',1,'DJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DK','Ad Daqahllyah','Ad Daqahllyah',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DL','Dalaba','Dalaba',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('DN','Distrito Nacional (Santo ','Distrito Nacional (Santo Domingo)',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DNC','Doncaster (South Yorkshir','Doncaster (South Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DND','Dundee City','Dundee City',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DO','Donga','Donga',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DOR','Dorset (county)','Dorset (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DOW','Down','Down',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DR','Drenthe','Drenthe',1,'NL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DRY','Derry','Derry',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DT','DumyÃÆÃâÃâ Ã¢â¬â','DumyÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ t',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('DU','Dubayy','Dubayy',1,'AE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DUD','Dudley (West Midlands dis','Dudley (West Midlands district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DUR','Durham','Durham',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DY','Dayr az Zawr','Dayr az Zawr',1,'SY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('DZ-05','Batna','Batna',1,'DZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('E','Entre Rios','Entre Rios',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EAL','Ealing (London borough)','Ealing (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EAY','East Ayrshire','East Ayrshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EB','East Berbice-Corentyne','East Berbice-Corentyne',1,'GY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('EC','Eastern Cape','Eastern Cape',1,'ZA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ED','Edinet','Edinet',1,'MD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EDH','Edinburgh, City of','Edinburgh, City of',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EDU','East Dunbartonshire','East Dunbartonshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EG','Eger','Eger',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EL','Elblag','Elblag',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ELN','East Lothian','East Lothian',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ELS','Eilean Siar','Eilean Siar',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EN','Far North','Far North',1,'CM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ENF','Enfield (London borough)','Enfield (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EP','Espaillat','Espaillat',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EQ','ÃÆÃâÃâ Ã¢â¬â¢ÃÆ','ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃâÃÂ¢ÃÆÃÂ¢ÃÂ¢Ã¢âÂ¬ÃÂ¡ÃâÃÂ¬ÃÆÃ¢â¬Å¡ÃâÃÂ°quateur',1,'CD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ER','Erevan','Erevan',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ERW','East Renfrewshire','East Renfrewshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ERY','East Riding of Yorkshire','East Riding of Yorkshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ES','Espirito Santo','Espirito Santo',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ESS','Essex (county)','Essex (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ESX','East Sussex (county)','East Sussex (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-AF','Afar','Afar',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-AM','Amara','Amara',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-BE','Benshangul-Gumaz','Benshangul-Gumaz',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-GA','Gambela Peoples','Gambela Peoples',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-HA','Harari People','Harari People',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-OR','Oromia','Oromia',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ET-SN','Southern Nations, Nationa','Southern Nations, Nationalities and Peoples',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-SO','Somali','Somali',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ET-TI','Tigrai','Tigrai',1,'ET','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EVE','Evenkiyakiy avtonomoyy ok','Evenkiyakiy avtonomoyy okrug',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('EX','Exuma','Exuma',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('F','La Rioja','La Rioja',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FA','Faranah','Faranah',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FAL','Falkirk','Falkirk',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FC','Fresh Creek','Fresh Creek',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('FD','Florida','Florida',1,'UY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FE','FejÃÆÃâÃâ Ã¢â¬â¢','FejÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©r',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FER','Fermanagh','Fermanagh',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FG','Foggia','Foggia',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FI','Florence','Florence',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FIF','Fife','Fife',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FL','Flacq','Flacq',1,'MU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FLN','Flintshire','Flintshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('FM','Francisco MorazÃÆÃâÃ','Francisco MorazÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡n',1,'HN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FO','ForÃÆÃâÃâ Ã¢â¬â¢','ForÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©cariah',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FP','Freeport','Freeport',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FR','Fribourg','Fribourg',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FRA','Farah province','Farah province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('FS','Flores','Flores',1,'UY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FU','Al Fujayrah','Al Fujayrah',1,'AE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FUZ','Fuzuli','Fuzuli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FYB','Faryab province','Faryab province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('FYM','Al FayyÃÆÃâÃâ Ã¢â','Al FayyÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¦ÃÆÃÂ¢ÃÂ¢Ã¢â¬Å¡ÃÂ¬Ãâ¦Ã¢â¬Åm',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('G','Santiago del Estero','Santiago del Estero',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GA','GÃÆÃâÃâ Ã¢â¬â¢Ã','GÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ncÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('GAD','GÃÆÃâÃâ Ã¢â¬â¢Ã','GÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤dÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤bÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃ',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GAT','Gateshead','Gateshead',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GB','Gash-Barka','Gash-Barka',1,'ER','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GB-S','ND Sunderland','ND Sunderland',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('GC','Las Palmas','Las Palmas',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GD','Gracias a Dios','Gracias a Dios',1,'HN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GE','Geneva','Geneva',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GG','Grand Gedeh','Grand Gedeh',1,'LR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GH','Governor\'s Harbour','Governor\'s Harbour',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GHA','Ghazni province','Ghazni province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GHO','Ghowr province','Ghowr province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GI','Gitega','Gitega',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GIS','Gisborne N','Gisborne N',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('GJ','Gujarat','Gujarat',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GK','Grand Kru','Grand Kru',1,'LR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GL','Glarus','Glarus',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GLG','Glasgow City','Glasgow City',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GLS','Gloucestershire (county)','Gloucestershire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GO','Goias','Goias',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GOR','Goranboy','Goranboy',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GOY','GÃÆÃâÃâ Ã¢â¬â¢Ã','GÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¶yÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ§ay',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GP','Grand Port','Grand Port',1,'MU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('GR','Gegark\'unik\'','Gegark\'unik\'',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GRE','Greenwich (London borough','Greenwich (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GRO','Guerrero','Guerrero',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GS','GyÃÆÃâÃâ Ã¢â¬â¢Ã','GyÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂµr-Moson-Sopron',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GT','Green Turtle Cay','Green Turtle Cay',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GU','Guadalajara','Guadalajara',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GUA','GuainÃÆÃâÃâ Ã¢â¬â','GuainÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­a',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('GUL','Gulu','Gulu',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GUV','Guaviare','Guaviare',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GWN','Gwynedd','Gwynedd',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GY','GyÃÆÃâÃâ Ã¢â¬â¢Ã','GyÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³r',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('GZ','Al JÃÆÃâÃâ Ã¢â¬â','Al JÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ®zah',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('H','Chaco','Chaco',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('HA','Hefa','Hefa',1,'IL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HAB','Hamgyong-bukto','Hamgyong-bukto',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HAC','Haciqabul','Haciqabul',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HAL','Halton (unitary authority','Halton (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HAM','Hampshire (county)','Hampshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HAN','Hamgyong-namdo','Hamgyong-namdo',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HAV','Havering (London borough)','Havering (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HB','Bremen (city-state)','Bremen (city-state)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HCK','Hackney (London borough)','Hackney (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('HD','Hunedoara','Hunedoara',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HE','Hes (Hesn)','Hes (Hesn)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HEF','Herefordshire, County of ','Herefordshire, County of (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HEL','Helmand province','Helmand province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HER','Herat province','Herat province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HH','Hamburg (city-state)','Hamburg (city-state)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HI','Harbour Island','Harbour Island',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HID','Hidalgo','Hidalgo',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HIL','Hillingdon (London boroug','Hillingdon (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('HJ','Hajjah','Hajjah',1,'YE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HK','Haute-Kotto','Haute-Kotto',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HKB','Hawke\'s Bay N','Hawke\'s Bay N',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HL','Halab','Halab',1,'SY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HLD','Highland','Highland',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HM','Haut-Mbomou','Haut-Mbomou',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HMF','Hammersmith and Fulham (L','Hammersmith and Fulham (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HNS','Hounslow (London borough)','Hounslow (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HO','Homyel voblast','Homyel voblast',1,'BY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('HOI','Hoima','Hoima',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HP','Himachal Pradesh','Himachal Pradesh',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HPL','Hartlepool (unitary autho','Hartlepool (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HR','High Rock','High Rock',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HRT','Hertfordshire (county)','Hertfordshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HRW','Harrow (London borough)','Harrow (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HRY','Haringey (London borough)','Haringey (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HS','MambÃÆÃâÃâ Ã¢â¬â','MambÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©rÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©-KadÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃ',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('HSZ','Hsinchu','Hsinchu',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HU','Huesca','Huesca',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HUA','Huambo province','Huambo province',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HUC','HuÃÆÃâÃâ Ã¢â¬â¢Ã','HuÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡nuco',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HUI','HuÃÆÃâÃâ Ã¢â¬â¢Ã','HuÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­la province',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HUV','Huancavelica','Huancavelica',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('HV','HÃÆÃâÃâ Ã¢â¬â¢Ã','HÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ´dmezÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³vÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâ',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HWB','Hwanghae-bukto','Hwanghae-bukto',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('HWN','Hwanghae-namdo','Hwanghae-namdo',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('I','Imbabura','Imbabura',1,'EC','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('IA','Iowa','Iowa',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IB','Illes Balears','Illes Balears',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ICA','Ica','Ica',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ID','Idlib','Idlib',1,'SY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IGA','Iganga','Iganga',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IJ','Papua','Papua',1,'ID','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IL','Ialomita','Ialomita',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ILA','Ilan','Ilan',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IM','Imereti','Imereti',1,'GE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IMI','Imisli','Imisli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('IN','Inagua','Inagua',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IOS','Isles of Scilly','Isles of Scilly',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IOW','Isle of Wight (county)','Isle of Wight (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IRR','Irkutakaya oblast\'','Irkutakaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IS','Al Isma ÃÆÃâÃâ Ã¢â','Al Isma ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ®llyah',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ISL','Islington (London borough','Islington (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ISM','Ismayilli','Ismayilli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('IVA','IvanovÃÆÃâÃâ Ã¢â¬','IvanovÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³kaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IVC','Inverclyde','Inverclyde',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('IZ','Izabal','Izabal',1,'GT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('J','San Juan','San Juan',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JA','Jalapa','Jalapa',1,'GT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JAL','Jalisco','Jalisco',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JC','South Bohemian Region (Ji','South Bohemian Region (JihoceskÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ½ kraj)',1,'CZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('JG','Jelenia GÃÆÃâÃâ Ã¢','Jelenia GÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³ra',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JH','Jharkhand','Jharkhand',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JI','Jawa Timur','Jawa Timur',1,'ID','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JIN','Jinja','Jinja',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JK','Jakarta Raya','Jakarta Raya',1,'ID','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JM','South Moravian Region (Ji','South Moravian Region (JihomoravskÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ½ kraj)',1,'CZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JN','JÃÆÃâÃâ Ã¢â¬â¢Ã','JÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡sz-Nagykun-Szolnok',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('JOW','Jowzjan province','Jowzjan province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JR','Jawa Barat','Jawa Barat',1,'ID','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JS','JanÃÆÃâÃâ Ã¢â¬â¢','JanÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ»b SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ®na\'',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JT','Jawa Tengah','Jawa Tengah',1,'ID','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('JU','Jura','Jura',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('JUN','JunÃÆÃâÃâ Ã¢â¬â¢','JunÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­n',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('K','Catamarca','Catamarca',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KA','Katanga','Katanga',1,'CD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KAB','Kabul province','Kabul province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KAE','Kaesong Industrial Region','Kaesong Industrial Region',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KAL','KÃÆÃâÃâ Ã¢â¬â¢Ã','KÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤lbÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤cÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃ',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KAM','Kamchatskaya oblast\'','Kamchatskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KAN','Kandahar province','Kandahar province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KAP','Kapisa province','Kapisa province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KAR','Qaraghandy','Qaraghandy',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KAS','Kasese','Kasese',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KB','Kemps Bay','Kemps Bay',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KBL','Kabale','Kabale',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KBR','Kabarole','Kabarole',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KC','Karachayevo-Cherkesskaya ','Karachayevo-Cherkesskaya Respublika Karacajevo-Cerkesskaja',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KD','Kindia','Kindia',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KDA','Krasnodarakiy kray','Krasnodarakiy kray',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KDZ','Kondoz province','Kondoz province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KE','Kasai-Oriental','Kasai-Oriental',1,'CD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KEC','Kensington and Chelsea (L','Kensington and Chelsea (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KEE','Keelung','Keelung',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KEM','Kemerovskaya oblast\'','Kemerovskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KEN','Kent (county)','Kent (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KFS','Kafr ash Shaykh','Kafr ash Shaykh',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KG','Kgalagadi','Kgalagadi',1,'BW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KGD','Kaliningradskaya oblast\'','Kaliningradskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KGN','Kurganskaya oblast\'','Kurganskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KH','Khammouane','Khammouane',1,'LA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KHA','Khabarovakiy kray','Khabarovakiy kray',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KHH','Kaohsiung','Kaohsiung',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KHL','Kingston upon Hull, City ','Kingston upon Hull, City of (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KHM','Khanty-Mansiyskiy avtonom','Khanty-Mansiyskiy avtonomnyy okrug Hanty-Mansijakij avtonomnyj okrug',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KI','Kirundo','Kirundo',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KIB','Kiboga','Kiboga',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KIR','Kirklees (West Yorkshire ','Kirklees (West Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KIS','Kisoro','Kisoro',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KIT','Kitgum','Kitgum',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KK','Kvemo Kartli','Kvemo Kartli',1,'GE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KL','Kgatleng','Kgatleng',1,'BW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KLA','Kampala','Kampala',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KLE','Kibeale','Kibeale',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KLG','Kalangala','Kalangala',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KLI','Kamuli','Kamuli',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KLU','Kaluzhakaya oblast\'','Kaluzhakaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KM','KecÃÆÃâÃâ Ã¢â¬â¢','KecÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©kemÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©t',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KN','Kinshasa','Kinshasa',1,'CD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KNR','Konar province','Konar province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KO','Kouffo','Kouffo',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KOP','Komi-Permyatskiy avtonomn','Komi-Permyatskiy avtonomnyy okrug Komi-Penmjatakij avtonomnyj okrug',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KOR','Koryakskiy avtonomnyy okr','Koryakskiy avtonomnyy okrug',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KOS','Kostromskaya oblast\'','Kostromskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KOT','Kotido','Kotido',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KR','Karuzi','Karuzi',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KRS','Kurakaya oblast\'','Kurakaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KS','Kissidougou','Kissidougou',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KSA','Kosrae','Kosrae',1,'FM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KT','Kotayk\'','Kotayk\'',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KTT','Kingston upon Thames (Lon','Kingston upon Thames (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KU','Al Kuwayt','Al Kuwayt',1,'KW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KUM','Kumi','Kumi',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KUR','KurdÃÆÃâÃâ Ã¢â¬â','KurdÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤mir',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KUS','Qustanay','Qustanay',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('KV','KaposvÃÆÃâÃâ Ã¢â¬','KaposvÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡r',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KW','Kweneng','Kweneng',1,'BW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KWL','Knowsley (metropolitan bo','Knowsley (metropolitan borough of Merseyside)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KY','Kayanza','Kayanza',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KYA','Krasnoyarskiy kray','Krasnoyarskiy kray',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('KZY','Qyzylorda','Qyzylorda',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('L','La Pampa','La Pampa',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LA','LÃÆÃâÃâ Ã¢â¬â¢Ã','LÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤nkÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ran',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LAC','LaÃÆÃâÃâ Ã¢â¬â¢Ã','LaÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ§in',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LAG','Laghman province','Laghman province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LAL','La Libertad','La Libertad',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LAM','Lambayeque','Lambayeque',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LAN','LÃÆÃâÃâ Ã¢â¬â¢Ã','LÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤nkÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ran',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LB','Lobaye','Lobaye',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LBH','Lambeth (London borough)','Lambeth (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LC','Lecco','Lecco',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LCE','Leicester (unitary author','Leicester (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LD','Longford','Longford',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LDS','Leeds (West Yorkshire dis','Leeds (West Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LE','LeÃÆÃâÃâ Ã¢â¬â¢Ã','LeÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³n',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LEC','Leicestershire (county)','Leicestershire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LEN','Leningradskaya oblast\'','Leningradskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LER','Lerik','Lerik',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LEW','Lewisham (London borough)','Lewisham (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LG','Legnica','Legnica',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LH','Louth','Louth',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LI','Littoral','Littoral',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LIM','Lima','Lima',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LIN','Lincolnshire (county)','Lincolnshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LIP','Lipetskaya oblast\'','Lipetskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LIR','Lira','Lira',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LIV','Liverpool','Liverpool',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LK','Limerick','Limerick',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LL','Los Lagos','Los Lagos',1,'CL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LM','Leitrim','Leitrim',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LMV','Limavady','Limavady',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LND','London, City of','London, City of',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LNO','Lunda Norte','Lunda Norte',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LO','Lory','Lory',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LOR','Loreto','Loreto',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LOW','Lowgar province','Lowgar province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('LP','La Paz','La Paz',1,'HN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LRN','Larne','Larne',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LS','Western Finland','Western Finland',1,'FI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LSB','Lisburn','Lisburn',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LSU','Lunda Sul','Lunda Sul',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LT','Littoral','Littoral',1,'CM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LU','Lucerne','Lucerne',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LUA','Luanda','Luanda',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LUT','Luton (unitary authority)','Luton (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('LUW','Luwero','Luwero',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('M','Mendoza','Mendoza',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MA','Makamba','Makamba',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MAG','Magdalena','Magdalena',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MAH','Maharashtra','Maharashtra',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MAL','Malange','Malange',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MAN','Manchester (Manchester bo','Manchester (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MAS','Masalli','Masalli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MB','Manitoba','Manitoba',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MBH','Marlborough S','Marlborough S',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MBL','Mbale','Mbale',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MBR','Mbarara','Mbarara',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MC','Monte Cristi','Monte Cristi',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MD','Mandiana','Mandiana',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MDB','Middlesbrough (unitary au','Middlesbrough (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MDD','Madre de Dios','Madre de Dios',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MDW','Medway (unitary authority','Medway (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ME','Messina','Messina',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MET','Meta','Meta',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MEX','Mexico','Mexico',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MFT','Magherafelt','Magherafelt',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MG','Minas Gerais','Minas Gerais',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MH','Marsh Harbour','Marsh Harbour',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MI','MingÃÆÃâÃâ Ã¢â¬â','MingÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ§evir',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MIA','Miaoli','Miaoli',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MIC','MichoacÃÆÃâÃâ Ã¢â','MichoacÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡n',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MIK','Milton Keynes','Milton Keynes',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ML','Maule','Maule',1,'CL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MLN','Midlothian','Midlothian',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MM','NaxÃÆÃâÃâ Ã¢â¬â¢','NaxÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ§ivan',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MN','MonseÃÆÃâÃâ Ã¢â¬â','MonseÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ±or Nouel',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MNF','Al Minuflyah','Al Minuflyah',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MO','Mono','Mono',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MON','Monmouthshire','Monmouthshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MOQ','Moquegua','Moquegua',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MOR','Morelos','Morelos',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MOS','Moskovskaya oblast\'','Moskovskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MOW','Moskva','Moskva',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MOX','Moxico','Moxico',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MOY','Moyo','Moyo',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MP','Ombella-Mpoko','Ombella-Mpoko',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MPI','Mpigi','Mpigi',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MR','Marijampoles Apskritis','Marijampoles Apskritis',1,'LT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MRT','Merton (London borough)','Merton (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MRY','Moray','Moray',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MS','Mato Grosso do Sul','Mato Grosso do Sul',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MSI','Masindi','Masindi',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MSK','Masaka','Masaka',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MT','Mato Grosso','Mato Grosso',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MTY','Merthyr Tydfil','Merthyr Tydfil',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MU','Muramvya','Muramvya',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MUB','Mubende','Mubende',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MUK','Mukono','Mukono',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MUR','Murmanskaya oblast\'','Murmanskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MV','Mecklenburg-Western Pomer','Mecklenburg-Western Pomerania (Mecklenburg-Vorpommern)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MW','Mwaro','Mwaro',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MWT','Manawatu-Wanganui N','Manawatu-Wanganui N',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MY','Muyinga','Muyinga',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('MYL','Moyle','Moyle',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('MZ','Mizoram','Mizoram',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('N','Misiones','Misiones',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NA','Naftalan','Naftalan',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NAJ','Rason (Rajin-Sonbong) Chi','Rason (Rajin-Sonbong) Chik\'alshi',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NAM','Namibe','Namibe',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NAN','Nangrahar province','Nangrahar province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NAR','NariÃÆÃâÃâ Ã¢â¬â','NariÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ±o',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NAY','North Ayrshire','North Ayrshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('NB','Nicholls Town and Berry I','Nicholls Town and Berry Islands',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NBL','Northumberland','Northumberland',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NC','New Caledonia','New Caledonia',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ND','Nord','Nord',1,'HT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NDN','North Down NIR','North Down NIR',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NE','North-East','North-East',1,'BW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NEB','Nebbi','Nebbi',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NEF','Neftcala','Neftcala',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NEL','North East Lincolnshire (','North East Lincolnshire (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('NEN','Nenetskiy avtonomoyy okru','Nenetskiy avtonomoyy okrug',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NET','Newcastle upon Tyne','Newcastle upon Tyne',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NFK','Norfolk (county)','Norfolk (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NG','Ngozi','Ngozi',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NGM','Nottingham','Nottingham',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NGR','Novgorodskaya oblast\'','Novgorodskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NH','Noord Holland','Noord Holland',1,'NL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NI','Lower Saxony (Niedersachn','Lower Saxony (Niedersachn)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NIM','Nimruz province','Nimruz province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('NIZ','Nizhegorodskaya oblast\'','Nizhegorodskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NJ','New Jersey','New Jersey',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NK','Nord-Kivu','Nord-Kivu',1,'CD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NL','Newfoundland and Labrador','Newfoundland and Labrador',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NLE','Nuevo Leon','Nuevo Leon',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NLK','North Lanarkshire','North Lanarkshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NLN','North Lincolnshire (unita','North Lincolnshire (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NM','Nana-MambÃÆÃâÃâ Ã¢','Nana-MambÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©rÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('NO','North','North',1,'CM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NP','New Providence','New Providence',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NS','New South Wales','New South Wales',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NSA','Norte de Santander','Norte de Santander',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NSM','North Somerset (unitary a','North Somerset (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NSN','Nelson S','Nelson S',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NT','Northern Territory','Northern Territory',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NTA','Newtownabbey','Newtownabbey',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NTH','Northamptonshire (county)','Northamptonshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('NTL','Neath Port Talbot','Neath Port Talbot',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NTT','Nottinghamshire (county)','Nottinghamshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NTU','Ntungamo','Ntungamo',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NTY','North Tyneside (unitary a','North Tyneside (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NU','Nunavut','Nunavut',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NV','Nevada','Nevada',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NVS','Novosibirskaya oblast\'','Novosibirskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NW','Nidwalden','Nidwalden',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NWM','Newham (London borough)','Newham (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('NWP','Newport','Newport',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NY','NyÃÆÃâÃâ Ã¢â¬â¢Ã','NyÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­regyhÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡za',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NYK','North Yorkshire (unitary ','North Yorkshire (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NYM','Newry and Mourne','Newry and Mourne',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('NZ','NzÃÆÃâÃâ Ã¢â¬â¢Ã','NzÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©rÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©korÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâ',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('O','Oruro','Oruro',1,'BO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OAX','Oaxaca','Oaxaca',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OB','Obock','Obock',1,'DJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OC','Ocotepeque','Ocotepeque',1,'HN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OG','Ogun','Ogun',1,'NG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OGU','Oguz','Oguz',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OH','Ohio','Ohio',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OI','Oio','Oio',1,'GW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OK','Oklahoma','Oklahoma',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OL','Olomouc Region (OlomouckÃ','Olomouc Region (OlomouckÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ½ kraj)',1,'CZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('OLD','Oldham (Manchester boroug','Oldham (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OMH','Omagh','Omagh',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OMS','Omskaya oblast\'','Omskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ON','Ontario','Ontario',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OP','Ouham-PendÃÆÃâÃâ Ã','Ouham-PendÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OR','Orientale','Orientale',1,'CD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ORD','Ordubad','Ordubad',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ORE','Orenburgskaya oblast\'','Orenburgskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ORK','Orkney Islands','Orkney Islands',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ORL','Orlovskaya oblast\'','Orlovskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ORU','Oruzgan province','Oruzgan province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OS','Osun','Osun',1,'NG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OT','Olt','Olt',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OTA','Otago S','Otago S',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OU','OuÃÆÃâÃâ Ã¢â¬â¢Ã','OuÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©mÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OV','Overijsl','Overijsl',1,'NL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('OW','Orange Walk','Orange Walk',1,'BZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OXF','Oxfordshire (county)','Oxfordshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('OY','Offaly','Offaly',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('P','Formosa','Formosa',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PA','Para','Para',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PAL','Pallisa','Pallisa',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PAR','Parwan province','Parwan province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PAS','Pasco','Pasco',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PAV','Paylodar','Paylodar',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PB','Paraiba','Paraiba',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PC','Piacenza','Piacenza',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PD','Padua','Padua',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PE','Pernambuco','Pernambuco',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PEM','Pembrokeshire','Pembrokeshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PEN','Penghu','Penghu',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PER','Permskaya oblast\'','Permskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PF','French Polynesia','French Polynesia',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PG','Perugia','Perugia',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PH','Phongsaly','Phongsaly',1,'LA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PI','Piaui','Piaui',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PIA','Paktia province','Paktia province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PIF','Pingtung','Pingtung',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PIU','Piura','Piura',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PKA','Paktika province','Paktika province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PKN','Perth and Kinross','Perth and Kinross',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PL','Plateau','Plateau',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PLY','Plymouth, England','Plymouth, England',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PM','San Pedro de MacorÃÆÃâ','San Pedro de MacorÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­s',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PN','Porto Novo','Porto Novo',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PNI','Pohnpei','Pohnpei',1,'FM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PNZ','Penzenskaya oblast\'','Penzenskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PO','Pontevedra','Pontevedra',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('POL','Poole','Poole',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('POR','Portsmouth','Portsmouth',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('POW','Powys','Powys',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PP','Puerto Plata','Puerto Plata',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PR','Parana','Parana',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PRI','Primorakiy kray','Primorakiy kray',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PS','PÃÆÃâÃâ Ã¢â¬â¢Ã','PÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©cs',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PSK','Pskovskaya oblast\'','Pskovskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PT','Potaro-Siparuni','Potaro-Siparuni',1,'GY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PTE','Peterborough (unitary aut','Peterborough (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PTS','BÃÆÃâÃâ Ã¢â¬â¢Ã','BÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ»r Sa\'ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ®d',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PUE','Puebla','Puebla',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PUN','Puno','Puno',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PUT','Putumayo','Putumayo',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PV','PaÃÆÃâÃâ Ã¢â¬â¢Ã','PaÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­s Vasco',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PW','Plaines Wilhems','Plaines Wilhems',1,'MU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY','Pondicherry','Pondicherry',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-1','ConcepciÃÆÃâÃâ Ã¢â','ConcepciÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³n',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-10','Alto ParanÃÆÃâÃâ Ã','Alto ParanÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PY-11','Central','Central',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-12','ÃÆÃâÃâ Ã¢â¬â¢ÃÆ','ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃâÃÂ¢ÃÆÃÂ¢ÃÂ¢Ã¢âÂ¬ÃÂ¡ÃâÃÂ¬ÃÆÃ¢â¬Â¹Ãâ¦Ã¢â¬Åeembucu',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-13','Amambay','Amambay',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-14','CanindeyÃÆÃâÃâ Ã¢â','CanindeyÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂº',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-15','Presidente Hayes','Presidente Hayes',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PY-16','Alto Paraguay','Alto Paraguay',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-19','BoquerÃÆÃâÃâ Ã¢â¬','BoquerÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³n',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-2','San Pedro','San Pedro',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-3','Cordillera','Cordillera',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-4','GuairÃÆÃâÃâ Ã¢â¬â','GuairÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-5','CaeguazÃÆÃâÃâ Ã¢â','CaeguazÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂº',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PY-6','Caazapl','Caazapl',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-7','Itapua','Itapua',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-8','Miaiones','Miaiones',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-9','ParaguarÃÆÃâÃâ Ã¢â','ParaguarÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PY-ASU','AsunciÃÆÃâÃâ Ã¢â¬','AsunciÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³n',1,'PY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PYB','P\'yongan-bukto','P\'yongan-bukto',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('PYN','P\'yongan-namdo','P\'yongan-namdo',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PYO','P\'yongyang Chik\'alshi','P\'yongyang Chik\'alshi',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('PZ','Potenza','Potenza',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('Q','Neuquen','Neuquen',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QA','Qashqadaryo','Qashqadaryo',1,'UZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QAB','QÃÆÃâÃâ Ã¢â¬â¢Ã','QÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤bÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤lÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃ',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QAX','Qax','Qax',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('QAZ','Qazax','Qazax',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QB','Quatre Bornes','Quatre Bornes',1,'MU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QBA','Quba','Quba',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QBI','Qubadli','Qubadli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QC','Quebec','Quebec',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QL','Queensland','Queensland',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QOB','Qobustan','Qobustan',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QR','Qoraqalpog\'iston Republik','Qoraqalpog\'iston Republikasi',1,'UZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QU','Quloara','Quloara',1,'GW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('QUE','QuerÃÆÃâÃâ Ã¢â¬â','QuerÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©taro',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QUI','QuindÃÆÃâÃâ Ã¢â¬â','QuindÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­o',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QUS','Qusar','Qusar',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('QZ','Quezaltenango','Quezaltenango',1,'GT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('R','Rio Negro','Rio Negro',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('RA','Ravenna','Ravenna',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RAD','Adygeya, Respublika','Adygeya, Respublika',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RAK','Rakai','Rakai',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RC','Reggio di Calabria','Reggio di Calabria',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RCC','Redcar and Cleveland','Redcar and Cleveland',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RCH','Rochdale (Manchester boro','Rochdale (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RCT','Rhondda Cynon Taf','Rhondda Cynon Taf',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RD','Rif Dimashq','Rif Dimashq',1,'SY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RDB','Redbridge (London Borough','Redbridge (London Borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('RDG','Reading','Reading',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RE','Reta.thuleu','Reta.thuleu',1,'GT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RFW','Renfrewshire','Renfrewshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RG','Ribeira Grande','Ribeira Grande',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RI','Ragged Island','Ragged Island',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RIC','Richmond upon Thames (Lon','Richmond upon Thames (London Borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RIS','Risaralda','Risaralda',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RJ','Rio de Janeiro','Rio de Janeiro',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RK','R\'as al Khaymah','R\'as al Khaymah',1,'AE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('RL','Racha Lechkhumi and Kvemo','Racha Lechkhumi and Kvemo Svaneti',1,'GE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RM','Region Metropolitana de S','Region Metropolitana de Santiago',1,'CL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RN','Rio Grande do Norte','Rio Grande do Norte',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RO','Rondonia','Rondonia',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ROO','Quintana Roo','Quintana Roo',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ROS','Rostov\"kaya oblast\'','Rostov\"kaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ROT','Rotherham (South Yorkshir','Rotherham (South Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RP','Rhineland-Palatinate (Rhe','Rhineland-Palatinate (Rheinland-Pfalz)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('RR','Roraima','Roraima',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RS','Rio Grande do Sul','Rio Grande do Sul',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RT','Rutana','Rutana',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RUK','Rukungiri','Rukungiri',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RUT','Rutland (county)','Rutland (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RV','Rivera','Rivera',1,'UY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RY','Ruyigi','Ruyigi',1,'BI','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RYA','Ryazanskaya oblast\'','Ryazanskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('RZ','RzeszÃÆÃâÃâ Ã¢â¬â','RzeszÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ³w',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('S','Santa Fe','Santa Fe',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SA','South Australia','South Australia',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAB','Sabirabad','Sabirabad',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAD','Sadarak','Sadarak',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAH','Sahbuz','Sahbuz',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAK','SÃÆÃâÃâ Ã¢â¬â¢Ã','SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ki',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAL','Salyan','Salyan',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SAM','Samangan province','Samangan province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAN','Santander','Santander',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAP','San AndrÃÆÃâÃâ Ã¢â','San AndrÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©s, Providencia y Santa Catalina',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAR','Sar-e Pol province','Sar-e Pol province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAT','Saatli','Saatli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAW','Sandwell (West Midlands d','Sandwell (West Midlands district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SAY','South Ayrshire','South Ayrshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SB','Santa BÃÆÃâÃâ Ã¢â','Santa BÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡rbara',1,'HN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SC','Santa Catarina','Santa Catarina',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SCB','Scottish Borders, The','Scottish Borders, The',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SD','SÃÆÃâÃâ Ã¢â¬â¢Ã','SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ£o Domingos',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SE','South-East','South-East',1,'BW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SEV','Soltustik Qazaqstan','Soltustik Qazaqstan',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SF','SÃÆÃâÃâ Ã¢â¬â¢Ã','SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ£o Filipe',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SFK','Suffolk (county)','Suffolk (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SFT','Sefton (Merseyside boroug','Sefton (Merseyside borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SG','St. Gallen','St. Gallen',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SGC','South Gloucestershire (co','South Gloucestershire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SH','Ash Shariqah','Ash Shariqah',1,'AE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SHF','Sheffield (South Yorkshir','Sheffield (South Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SHG','SuhÃÆÃâÃâ Ã¢â¬â¢','SuhÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¢j',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SHN','St Helens (Merseyside bor','St Helens (Merseyside borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SHR','Ash Sharqiyah','Ash Sharqiyah',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SI','Siguiri','Siguiri',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SIN','ShamÃÆÃâÃâ Ã¢â¬â','ShamÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¢l SinÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ£\'',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SIY','SiyÃÆÃâÃâ Ã¢â¬â¢','SiyÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤zÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤n',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SJ','San JosÃÆÃâÃâ Ã¢â','San JosÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©',1,'CR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SK','Saskatchewan','Saskatchewan',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SKP','Stockport (Manchester bor','Stockport (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SKR','SÃÆÃâÃâ Ã¢â¬â¢Ã','SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤mkir',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SL','Sal','Sal',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SLF','Salford (Manchester borou','Salford (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SLG','Slough (unitary authority','Slough (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SLK','South Lanarkshire','South Lanarkshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SLP','San Luis PotosÃÆÃâÃ','San Luis PotosÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SM','Sumqayit','Sumqayit',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SMI','Samaxi','Samaxi',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SMO','Smolenskaya oblast\'','Smolenskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SMX','Samux','Samux',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SN','SÃÆÃâÃâ Ã¢â¬â¢Ã','SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ£o Nicolau',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SO','Southern','Southern',1,'BW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SOL','Solihull (West Midlands d','Solihull (West Midlands district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SOM','Somerset (county)','Somerset (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SON','Sonora','Sonora',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SOR','Soroti','Soroti',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SOS','Southend-on-Sea','Southend-on-Sea',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SP','Sao Paulo','Sao Paulo',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SPE','Sankt-Peterburg','Sankt-Peterburg',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SR','San Salvador and Rum Cay','San Salvador and Rum Cay',1,'BS','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SRP','Republika Srpska','Republika Srpska',1,'BA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SRY','Surrey (county)','Surrey (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SS','Susa','Susa',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ST','Central Bohemian Region (','Central Bohemian Region (StredoceskÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ½ kraj)',1,'CZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STA','Stavropol\'skiy kray','Stavropol\'skiy kray',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('STB','Strabane','Strabane',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STE','Stoke-on-Trent (unitary a','Stoke-on-Trent (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STG','Stirling','Stirling',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STH','Southampton','Southampton',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STL','Southland S','Southland S',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STN','Sutton (London borough)','Sutton (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STS','Staffordshire (county)','Staffordshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STT','Stockton-on-Tees','Stockton-on-Tees',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('STY','South Tyneside','South Tyneside',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SU','Syunik\'','Syunik\'',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SUC','Sucre','Sucre',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SUS','Susa','Susa',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SUZ','As Suways','As Suways',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SV','SÃÆÃâÃâ Ã¢â¬â¢Ã','SÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ£o Vicente',1,'CV','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SVE','Sverdlovskaya oblast\'','Sverdlovskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SW','South','South',1,'CM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('SWA','Swansea','Swansea',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SWD','Swindon','Swindon',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SWK','Southwark (London borough','Southwark (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('SZ','Schwyz','Schwyz',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('T','Tucuman','Tucuman',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('T0','ToruÃÆÃâÃâ Ã¢â¬â','ToruÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ±',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TA','TarapacÃÆÃâÃâ Ã¢â','TarapacÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¡',1,'CL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TAB','Tabasco','Tabasco',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TAC','Tacna','Tacna',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TAK','Takhar province','Takhar province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TAM','Tameside (Manchester boro','Tameside (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TAO','Taoyuan','Taoyuan',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TAR','Tartar','Tartar',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TAS','Tasman S','Tasman S',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TAY','Taymyrakiy (Dolgano-Nenet','Taymyrakiy (Dolgano-Nenetskiy)',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TB','Tbilisi','Tbilisi',1,'GE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TC','Centre','Centre',1,'TG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TE','Temburong','Temburong',1,'BN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TF','Santa Cruz De Tenerife','Santa Cruz De Tenerife',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TFW','Telford and Wrekin (unita','Telford and Wrekin (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TG','Thurgau','Thurgau',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TH','Thuringia (ThÃÆÃâÃâ','Thuringia (ThÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¼ringen)',1,'DE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('THR','Thurrock (unitary authori','Thurrock (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TI','Ticino','Ticino',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TK','Kara','Kara',1,'TG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TKI','Taranaki N','Taranaki N',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TL','Tulcea','Tulcea',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TLA','Tlaxcala','Tlaxcala',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TM','Timis','Timis',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TN','Tamil Nadu','Tamil Nadu',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TNN','Tainan','Tainan',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TO','Tocantins','Tocantins',1,'BR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TOB','Torbay','Torbay',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TOF','Torfaen','Torfaen',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TOL','Toledo','Toledo',1,'BZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TOM','Tomskaya oblast\'','Tomskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TOR','Tororo','Tororo',1,'UG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TOV','Tovuz','Tovuz',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TP','Trapani','Trapani',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TPE','Taipei','Taipei',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TR','Tripura','Tripura',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TRF','Trafford (Manchester boro','Trafford (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TRK','Chuuk','Chuuk',1,'FM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TS','Tasmania','Tasmania',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TT','Treinta y Tres','Treinta y Tres',1,'UY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TTT','Taitung','Taitung',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TU','Tutong','Tutong',1,'BN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TUL','Tul\'skaya oblast\'','Tul\'skaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TUM','Tumbes','Tumbes',1,'PE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TV','Tavus','Tavus',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TVE','Tverskaya oblast\'','Tverskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TWH','Tower Hamlets (London bor','Tower Hamlets (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('TX','Texas','Texas',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TXG','Taichung','Taichung',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TY','Trashi Yangtse','Trashi Yangtse',1,'BT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('TYU','Tyumenskaya oblast\'','Tyumenskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('U','Chubut','Chubut',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('U0B','Ust\'-Ordynskiy Buryatskiy','Ust\'-Ordynskiy Buryatskiy',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UCA','Ucar','Ucar',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UD','Upper Demerara-Berbice','Upper Demerara-Berbice',1,'GY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UE','Upper East','Upper East',1,'GH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('UIG','UÃÆÃâÃâ Ã¢â¬â¢Ã','UÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­ge',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UK','Ouaka','Ouaka',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UL','Uttaranchal','Uttaranchal',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ULY','Ul\'yanovskaya oblast\'','Ul\'yanovskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UN','Ungheni','Ungheni',1,'MD','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UP','Uttar Pradesh','Uttar Pradesh',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UQ','Umm al Qaywayn','Umm al Qaywayn',1,'AE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('UR','Uri','Uri',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('US','Usti nad Labem Region (Ã','Usti nad Labem Region (ÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¦ÃÆÃ¢â¬Å¡ÃâÃÂ¡steckÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ½ kraj)',1,'CZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UT','Upper Takutu-Upper Essequ','Upper Takutu-Upper Essequibo',1,'GY','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('UW','Upper West','Upper West',1,'GH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('V','Tierra del Fuego','Tierra del Fuego',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('VA','Valverde','Valverde',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VAC','Valle del Cauca','Valle del Cauca',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VAN','Antwerpen','Antwerpen',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VAU','VaupÃÆÃâÃâ Ã¢â¬â','VaupÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©s',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VB','Verbano-Cusio-Ossola','Verbano-Cusio-Ossola',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VBR','Vlaams Brabant','Vlaams Brabant',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VC','Valenciana, Comunidad','Valenciana, Comunidad',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('VD','Vayoc Jor','Vayoc Jor',1,'AM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VE','La Vega','La Vega',1,'DO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VER','Veracruz','Veracruz',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VGG','Volgogradskaya oblast\'','Volgogradskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VGL','Vale of Glamorgan','Vale of Glamorgan',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VI','Victoria','Victoria',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VID','Vichada','Vichada',1,'CO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VL','Vilniaus Apskritis','Vilniaus Apskritis',1,'LT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VLA','Vladimirskaya oblast\'','Vladimirskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('VLG','Vologodskaya oblast\'','Vologodskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VM','VeezprÃÆÃâÃâ Ã¢â¬','VeezprÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ©m',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VN','Vrancea','Vrancea',1,'RO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VO','Vojvodina','Vojvodina',1,'YU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VOR','Voronezhskaya oblast\'','Voronezhskaya oblast\'',1,'RU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VOS','Shyghys Qazaqstan','Shyghys Qazaqstan',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VOV','Oost-Vlaanderen','Oost-Vlaanderen',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('VP','Vacoas-Phoenix','Vacoas-Phoenix',1,'MU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VR','Vakaga','Vakaga',1,'CF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VS','Valais','Valais',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VT','Viterbo','Viterbo',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VV','Vibo Valentia','Vibo Valentia',1,'IT','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VWV','West-Vlaanderen','West-Vlaanderen',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('VY','Vysocina Region (kraj Vys','Vysocina Region (kraj Vysocina)',1,'CZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('W','Corrientes','Corrientes',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WA','Western Australia','Western Australia',1,'AU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('WAD','Al Wadi al JadÃÆÃâÃ','Al Wadi al JadÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ®d',1,'EG','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WAR','Wardak province','Wardak province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WB','West Bengal','West Bengal',1,'IN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WBK','West Berkshire (unitary a','West Berkshire (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WBR','Brabant Wallon','Brabant Wallon',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WC','Western Cape','Western Cape',1,'ZA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WD','Waterford','Waterford',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('WDU','West Dunbartonshire','West Dunbartonshire',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WF','Wallis and Futuna','Wallis and Futuna',1,'FR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WFT','Waltham Forest (London bo','Waltham Forest (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WGN','Wigan (Manchester borough','Wigan (Manchester borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WH','Westmeath','Westmeath',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WHT','Hainaut','Hainaut',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WI','Wisconsin','Wisconsin',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WIL','Wiltshire (county)','Wiltshire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WKF','Wakefield (West Yorkshire','Wakefield (West Yorkshire district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('WKO','Waikato N','Waikato N',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WL','W|oc|awek','W|oc|awek',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WLG','LiÃÆÃâÃâ Ã¢â¬â¢Ã','LiÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¨ge',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WLL','Walsall (West Midlands di','Walsall (West Midlands district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WLN','West Lothian','West Lothian',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WLV','Wolverhampton (West Midla','Wolverhampton (West Midlands district)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WLX','Luxembourg','Luxembourg',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('WN','Wele-Nzas','Wele-Nzas',1,'GQ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WNA','Namur','Namur',1,'BE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WND','Wandsworth (London boroug','Wandsworth (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WNM','Windsor and Maidenhead (u','Windsor and Maidenhead (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WOK','Wokingham (unitary author','Wokingham (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WOR','Worcestershire (county)','Worcestershire (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WP','Western','Western',1,'GH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WR','Wroc|aw','Wroc|aw',1,'PL','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('WRL','Wirral (metropolitan boro','Wirral (metropolitan borough of Merseyside)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WRT','Warrington (unitary autho','Warrington (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WRX','Wrexham','Wrexham',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WSM','Westminster (London borou','Westminster (London borough)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WSX','West Sussex (county)','West Sussex (county)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WTC','West Coast S','West Coast S',1,'NZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WV','West Virginia','West Virginia',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WW','Wicklow','Wicklow',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('WX','Wexford','Wexford',1,'IE','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('WY','Wyoming','Wyoming',1,'US','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('X','Cordoba','Cordoba',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XA','Xankandi','Xankandi',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XAC','Xacmaz','Xacmaz',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XAN','Xanlar','Xanlar',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XCI','Xocali','Xocali',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XE','Xekong','Xekong',1,'LA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XI','Xieng Khouang','Xieng Khouang',1,'LA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XIZ','Xizi','Xizi',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('XN','Saysomboun','Saysomboun',1,'LA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XO','Xorazm','Xorazm',1,'UZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('XVD','Xocavand','Xocavand',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('Y','Jujuy','Jujuy',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YAN','Yanggang-do','Yanggang-do',1,'KP','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YAP','Yap','Yap',1,'FM','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YAR','Yardimli','Yardimli',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YE','Yevlax','Yevlax',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YEV','Yevlax','Yevlax',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YO','Yomou','Yomou',1,'GN','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('YOR','York (unitary authority)','York (unitary authority)',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YT','Yukon Territory','Yukon Territory',1,'CA','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YUC','Yucatan','Yucatan',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YUN','Yunlin','Yunlin',1,'TW','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('YUZ','Ongtustik Qazaqstan','Ongtustik Qazaqstan',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('Z','Santa Cruz','Santa Cruz',1,'AR','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZA','Zamora','Zamora',1,'ES','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZAB','Zabol province','Zabol province',1,'AF','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZAC','Zacatecas','Zacatecas',1,'MX','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ZAI','Zaire','Zaire',1,'AO','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZAN','ZÃÆÃâÃâ Ã¢â¬â¢Ã','ZÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤ngilan',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZAP','Baty Qazaqstan','Baty Qazaqstan',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZAQ','Zaqatala','Zaqatala',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZAR','ZÃÆÃâÃâ Ã¢â¬â¢Ã','ZÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ¤rdab',1,'AZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZE','Zalaegerszeg','Zalaegerszeg',1,'HU','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
INSERT INTO `core_state_master` (`state_code`,`state_name`,`state_description`,`is_active`,`country_code`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 ('ZET','Shetland Islands','Shetland Islands',1,'GB','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZG','Zug','Zug',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZH','Zurich','Zurich',1,'CH','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZHA','Zhambyl','Zhambyl',1,'KZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZL','Zlin Region (ZlÃÆÃâÃ','Zlin Region (ZlÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ­nskÃÆÃâÃâ Ã¢â¬â¢ÃÆÃ¢â¬Â ÃÂ¢Ã¢âÂ¬Ã¢âÂ¢ÃÆÃâÃÂ¢Ã¢âÂ¬ÃÂ¡ÃÆÃ¢â¬Å¡ÃâÃÂ½ kraj)',1,'CZ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08'),
 ('ZO','Zou','Zou',1,'BJ','system','2014-08-07 15:37:08','system','2014-08-07 15:37:08');
/*!40000 ALTER TABLE `core_state_master` ENABLE KEYS */;


--
-- Definition of table `core_status_master`
--

DROP TABLE IF EXISTS `core_status_master`;
CREATE TABLE `core_status_master` (
  `status_code` varchar(25) NOT NULL,
  `status_name` varchar(60) NOT NULL,
  `status_description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`status_code`),
  UNIQUE KEY `UK_core_status_master_1` (`status_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_status_master`
--

/*!40000 ALTER TABLE `core_status_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_status_master` ENABLE KEYS */;


--
-- Definition of table `core_store_location_master`
--

DROP TABLE IF EXISTS `core_store_location_master`;
CREATE TABLE `core_store_location_master` (
  `store_location_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `store_location_code` varchar(25) NOT NULL,
  `store_location_name` varchar(60) NOT NULL,
  `store_location_description` varchar(255) DEFAULT NULL,
  `store_address_line1` varchar(100) NOT NULL,
  `store_address_line2` varchar(100) DEFAULT NULL,
  `store_address_line3` varchar(100) DEFAULT NULL,
  `store_address_city` varchar(50) NOT NULL,
  `store_address_zip_code` varchar(15) NOT NULL,
  `store_address_state` varchar(100) NOT NULL,
  `store_address_country` varchar(100) NOT NULL,
  `store_image_url` varchar(255) DEFAULT NULL,
  `store_phone_number` varchar(15) DEFAULT NULL,
  `store_fax_number` varchar(15) DEFAULT NULL,
  `store_email_id` varchar(60) DEFAULT NULL,
  `store_area` varchar(50) NOT NULL,
  `latitude` varchar(30) DEFAULT NULL,
  `longitude` varchar(30) DEFAULT NULL,
  `store_time` datetime DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`store_location_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_store_location_master`
--

/*!40000 ALTER TABLE `core_store_location_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_store_location_master` ENABLE KEYS */;


--
-- Definition of table `core_user_master`
--

DROP TABLE IF EXISTS `core_user_master`;
CREATE TABLE `core_user_master` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_login` varchar(50) NOT NULL,
  `login_password` varchar(50) DEFAULT NULL,
  `first_name` varchar(60) NOT NULL,
  `middle_name` varchar(60) DEFAULT NULL,
  `last_name` varchar(60) NOT NULL,
  `billing_address_id` int(10) unsigned DEFAULT NULL,
  `shipping_address_id` int(10) unsigned DEFAULT NULL,
  `primary_email_id` varchar(50) DEFAULT NULL,
  `alternate_email_id` varchar(50) DEFAULT NULL,
  `primary_phone_number` varchar(15) DEFAULT NULL,
  `alternate_phone_number` varchar(15) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `anniversary_date` date DEFAULT NULL,
  `marital_status` int(10) unsigned DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  `condition_accepted` tinyint(1) DEFAULT NULL,
  `newsletter_subscription` tinyint(1) DEFAULT NULL,
  `sms_alert_subscription` tinyint(1) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`user_id`),
  KEY `FK_core_user_master_1` (`billing_address_id`),
  KEY `FK_core_user_master_2` (`shipping_address_id`),
  CONSTRAINT `FK_core_user_master_1` FOREIGN KEY (`billing_address_id`) REFERENCES `core_billing_address` (`billing_address_id`),
  CONSTRAINT `FK_core_user_master_2` FOREIGN KEY (`shipping_address_id`) REFERENCES `core_shipping_address` (`shipping_address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_user_master`
--

/*!40000 ALTER TABLE `core_user_master` DISABLE KEYS */;
INSERT INTO `core_user_master` (`user_id`,`user_login`,`login_password`,`first_name`,`middle_name`,`last_name`,`billing_address_id`,`shipping_address_id`,`primary_email_id`,`alternate_email_id`,`primary_phone_number`,`alternate_phone_number`,`birth_date`,`anniversary_date`,`marital_status`,`is_admin`,`is_active`,`condition_accepted`,`newsletter_subscription`,`sms_alert_subscription`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (7,'admin','21232f297a57a5a743894a0e4a801fc3','Santlal',NULL,'Vishwakarma',5,1,'vsantlal@yahoo.com',NULL,'9821772662',NULL,NULL,NULL,NULL,1,1,1,0,0,'system','2014-10-02 12:13:58','system','2014-10-02 12:13:58'),
 (8,'abc@yahoo.com','8f4031bfc7640c5f267b11b6fe0c2507','dasda',NULL,'dasd',6,1,'abc@yahoo.com',NULL,'dasda',NULL,NULL,NULL,NULL,0,1,1,0,0,'system','2014-11-30 18:54:02','system','2014-11-30 18:54:02'),
 (9,'abc123@yahoo.co','196b0f14eba66e10fba74dbf9e99c22f','dasd',NULL,'dasd',NULL,NULL,'abc123@yahoo.co',NULL,'dasd',NULL,NULL,NULL,NULL,0,1,1,0,0,'system','2014-11-30 18:56:42','system','2014-11-30 18:56:42');
/*!40000 ALTER TABLE `core_user_master` ENABLE KEYS */;


--
-- Definition of table `core_user_secondry_address_mapping`
--

DROP TABLE IF EXISTS `core_user_secondry_address_mapping`;
CREATE TABLE `core_user_secondry_address_mapping` (
  `user_id` int(10) unsigned NOT NULL,
  `secondry_addresses_id` int(10) unsigned NOT NULL,
  `secondry_address_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`secondry_addresses_id`),
  KEY `FK_core_user_secondry_address_mapping_1` (`user_id`),
  KEY `FK_core_user_secondry_address_mapping_2` (`secondry_addresses_id`),
  CONSTRAINT `FK_core_user_secondry_address_mapping_1` FOREIGN KEY (`user_id`) REFERENCES `core_user_master` (`user_id`),
  CONSTRAINT `FK_core_user_secondry_address_mapping_2` FOREIGN KEY (`secondry_addresses_id`) REFERENCES `core_secondry_addresses` (`secondry_addresses_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_user_secondry_address_mapping`
--

/*!40000 ALTER TABLE `core_user_secondry_address_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_user_secondry_address_mapping` ENABLE KEYS */;


--
-- Definition of table `core_users_roles_mapping`
--

DROP TABLE IF EXISTS `core_users_roles_mapping`;
CREATE TABLE `core_users_roles_mapping` (
  `users_roles_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `role_id` int(10) unsigned NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`users_roles_mapping_id`),
  KEY `fk_core_users_roles_mapping_1` (`role_id`),
  KEY `fk_core_users_roles_mapping_2` (`user_id`),
  CONSTRAINT `fk_core_users_roles_mapping_1` FOREIGN KEY (`role_id`) REFERENCES `core_role_master` (`role_id`),
  CONSTRAINT `fk_core_users_roles_mapping_2` FOREIGN KEY (`user_id`) REFERENCES `core_user_master` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_users_roles_mapping`
--

/*!40000 ALTER TABLE `core_users_roles_mapping` DISABLE KEYS */;
INSERT INTO `core_users_roles_mapping` (`users_roles_mapping_id`,`user_id`,`role_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (7,7,4,'vsantlal@yahoo.com','2014-10-02 12:13:58','vsantlal@yahoo.com','2014-10-02 12:13:58'),
 (8,8,1,'abc@yahoo.com','2014-11-30 18:54:02','abc@yahoo.com','2014-11-30 18:54:02'),
 (9,9,1,'abc123@yahoo.co','2014-11-30 18:56:42','abc123@yahoo.co','2014-11-30 18:56:42');
/*!40000 ALTER TABLE `core_users_roles_mapping` ENABLE KEYS */;


--
-- Definition of table `core_website`
--

DROP TABLE IF EXISTS `core_website`;
CREATE TABLE `core_website` (
  `core_website_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `website_name` varchar(25) NOT NULL,
  `website_url` varchar(255) NOT NULL,
  `created_by` varchar(100) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(100) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`core_website_id`),
  UNIQUE KEY `idx_core_website_website_url_uk` (`website_url`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `core_website`
--

/*!40000 ALTER TABLE `core_website` DISABLE KEYS */;
/*!40000 ALTER TABLE `core_website` ENABLE KEYS */;


--
-- Definition of table `customer_order_product_details`
--

DROP TABLE IF EXISTS `customer_order_product_details`;
CREATE TABLE `customer_order_product_details` (
  `customer_order_product_details_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `OrderId` varchar(50) NOT NULL,
  `Product` varchar(50) DEFAULT NULL,
  `Quantity` int(10) DEFAULT NULL,
  `Measure` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`customer_order_product_details_id`),
  KEY `fk_customer_order_product_details_1` (`OrderId`),
  CONSTRAINT `fk_customer_order_product_details_1` FOREIGN KEY (`OrderId`) REFERENCES `germany` (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer_order_product_details`
--

/*!40000 ALTER TABLE `customer_order_product_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer_order_product_details` ENABLE KEYS */;


--
-- Definition of table `germany`
--

DROP TABLE IF EXISTS `germany`;
CREATE TABLE `germany` (
  `OrderId` varchar(50) NOT NULL,
  `Customer` varchar(50) DEFAULT NULL,
  `Address` varchar(250) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `State` varchar(50) DEFAULT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Zip` varchar(50) DEFAULT NULL,
  `Shipping` varchar(50) DEFAULT NULL,
  `TrackingNum` varchar(50) DEFAULT NULL,
  `IsReShipment` varchar(50) DEFAULT NULL,
  `ReshipmentReason` varchar(50) DEFAULT NULL,
  `Price` float(15,3) DEFAULT NULL,
  `ShippingType` varchar(50) DEFAULT NULL,
  `ShippingcCharge` float(15,3) DEFAULT NULL,
  `Date` datetime DEFAULT NULL,
  `client` varchar(50) DEFAULT NULL,
  `ClientCountry` varchar(50) DEFAULT NULL,
  `Total` float(15,3) DEFAULT NULL,
  `comment` varchar(150) DEFAULT NULL,
  `ClentDateFileName` varchar(50) DEFAULT NULL,
  `vendorProductCost` float(15,3) DEFAULT NULL,
  `vendorShippingCharge` float(15,3) DEFAULT NULL,
  `vendorTotal` float(15,3) DEFAULT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `germany`
--

/*!40000 ALTER TABLE `germany` DISABLE KEYS */;
/*!40000 ALTER TABLE `germany` ENABLE KEYS */;


--
-- Definition of table `hierarchy_category_mapping`
--

DROP TABLE IF EXISTS `hierarchy_category_mapping`;
CREATE TABLE `hierarchy_category_mapping` (
  `hierarchy_category_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hierarchy_id` int(10) unsigned NOT NULL,
  `category_level_1` int(10) unsigned NOT NULL,
  `category_level_2` int(10) unsigned DEFAULT NULL,
  `category_level_3` int(10) unsigned DEFAULT NULL,
  `category_level_4` int(10) unsigned DEFAULT NULL,
  `category_level_5` int(10) unsigned DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`hierarchy_category_mapping_id`),
  KEY `FK_hierarchy_category_mapping_1` (`hierarchy_id`),
  KEY `FK_hierarchy_category_mapping_2` (`category_level_1`),
  KEY `FK_hierarchy_category_mapping_3` (`category_level_2`),
  KEY `FK_hierarchy_category_mapping_4` (`category_level_3`),
  KEY `FK_hierarchy_category_mapping_5` (`category_level_4`),
  KEY `FK_hierarchy_category_mapping_6` (`category_level_5`),
  CONSTRAINT `FK_hierarchy_category_mapping_1` FOREIGN KEY (`hierarchy_id`) REFERENCES `hierarchy_master` (`hierarchy_id`),
  CONSTRAINT `FK_hierarchy_category_mapping_2` FOREIGN KEY (`category_level_1`) REFERENCES `category_master` (`category_id`),
  CONSTRAINT `FK_hierarchy_category_mapping_3` FOREIGN KEY (`category_level_2`) REFERENCES `category_master` (`category_id`),
  CONSTRAINT `FK_hierarchy_category_mapping_4` FOREIGN KEY (`category_level_3`) REFERENCES `category_master` (`category_id`),
  CONSTRAINT `FK_hierarchy_category_mapping_5` FOREIGN KEY (`category_level_4`) REFERENCES `category_master` (`category_id`),
  CONSTRAINT `FK_hierarchy_category_mapping_6` FOREIGN KEY (`category_level_5`) REFERENCES `category_master` (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hierarchy_category_mapping`
--

/*!40000 ALTER TABLE `hierarchy_category_mapping` DISABLE KEYS */;
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,1,1,19,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (2,1,1,20,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (3,1,1,22,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (4,1,1,24,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (5,1,1,23,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (6,1,1,21,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (7,1,2,30,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (8,1,2,31,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (9,1,2,32,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (10,1,2,33,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (11,1,2,34,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (12,1,2,35,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (13,1,2,36,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (14,1,3,43,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (15,1,3,45,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (16,1,3,44,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (17,1,3,46,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (18,1,3,47,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (19,1,4,49,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (20,1,4,50,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (21,1,5,53,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (22,1,5,54,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (23,1,6,NULL,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (24,1,7,NULL,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (25,1,8,58,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (26,1,8,59,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (27,1,8,60,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17'),
 (28,1,8,61,NULL,NULL,NULL,1,'system','2014-04-12 21:55:17','system','2014-04-12 21:55:17');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (29,2,1,25,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (30,2,1,26,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (31,2,1,27,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (32,2,1,28,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (33,2,1,22,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (34,2,1,21,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (35,2,2,37,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (36,2,2,38,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (37,2,2,39,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (38,2,2,40,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (39,2,2,34,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (40,2,2,35,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (41,2,3,43,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (42,2,3,45,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (43,2,3,44,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (44,2,3,46,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (45,2,3,47,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (46,2,4,51,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (47,2,4,50,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (48,2,4,52,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (49,2,5,55,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (50,2,5,54,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (51,2,5,56,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (52,2,5,57,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (53,2,9,62,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (54,2,9,63,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55'),
 (55,2,9,64,NULL,NULL,NULL,1,'system','2014-04-12 22:17:55','system','2014-04-12 22:17:55');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (56,3,11,1,21,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (57,3,11,1,20,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (58,3,11,1,19,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (59,3,11,2,35,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (60,3,11,2,41,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (61,3,11,2,30,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (62,3,11,3,43,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (63,3,11,3,44,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (64,3,10,1,29,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (65,3,10,1,25,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (66,3,10,1,19,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (67,3,10,2,35,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (68,3,10,2,41,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (69,3,10,2,42,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (70,3,10,3,30,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (71,3,10,3,43,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (72,3,10,3,44,NULL,NULL,1,'system','2014-04-12 23:38:42','system','2014-04-12 23:38:42'),
 (73,4,13,65,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (74,4,13,66,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (75,4,13,67,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (76,4,13,68,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (77,4,13,69,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (78,4,13,70,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (79,4,14,71,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (80,4,14,72,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (81,4,14,73,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (82,4,14,74,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (83,4,14,75,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (84,4,15,76,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (85,4,15,71,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (86,4,15,77,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (87,4,16,79,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (88,4,16,80,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (89,4,16,81,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (90,4,16,82,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (91,4,16,83,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (92,4,16,84,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (93,4,17,85,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45');
INSERT INTO `hierarchy_category_mapping` (`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (94,4,17,86,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (95,4,17,88,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (96,4,17,75,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (97,4,18,89,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (98,4,18,90,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (99,4,18,91,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45'),
 (100,4,18,92,NULL,NULL,NULL,1,'system','2014-04-12 23:56:45','system','2014-04-12 23:56:45');
/*!40000 ALTER TABLE `hierarchy_category_mapping` ENABLE KEYS */;


--
-- Definition of table `hierarchy_master`
--

DROP TABLE IF EXISTS `hierarchy_master`;
CREATE TABLE `hierarchy_master` (
  `hierarchy_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `hierarchy_code` varchar(25) DEFAULT NULL,
  `hierarchy_name` varchar(60) DEFAULT NULL,
  `hierarchy_description` varchar(255) DEFAULT NULL,
  `hierarchy_sequence` int(10),
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`hierarchy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hierarchy_master`
--

/*!40000 ALTER TABLE `hierarchy_master` DISABLE KEYS */;
INSERT INTO `hierarchy_master` (`hierarchy_id`,`hierarchy_code`,`hierarchy_name`,`hierarchy_description`,`hierarchy_sequence`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,'MEN','Men',NULL,1,1,'system','2014-04-07 22:47:01','system','2014-04-07 22:47:01'),
 (2,'WOMEN','Women',NULL,2,1,'system','2014-04-07 22:47:01','system','2014-04-07 22:47:01'),
 (3,'KIDS','Kids',NULL,3,1,'system','2014-04-07 22:47:01','system','2014-04-07 22:47:01'),
 (4,'ELECTRONICS','Electronics',NULL,4,1,'system','2014-04-11 08:11:40','system','2014-04-11 08:11:40');
/*!40000 ALTER TABLE `hierarchy_master` ENABLE KEYS */;


--
-- Definition of table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail` (
  `order_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_header_id` int(10) unsigned NOT NULL,
  `product_id` int(10) unsigned NOT NULL,
  `product_sku_id` int(10) unsigned NOT NULL,
  `price_per_piece` float(15,3) NOT NULL,
  `original_price_per_piece` float(15,3) NOT NULL,
  `total_price` float(15,3) NOT NULL,
  `original_total_price` float(15,3) NOT NULL,
  `quantity` int(11) NOT NULL,
  `delivery_date` date DEFAULT NULL,
  `jewellery_valuation` tinyint(1) DEFAULT NULL,
  `customization_text` varchar(25) DEFAULT NULL,
  `customization_font` varchar(50) DEFAULT NULL,
  `size_id` int(10) unsigned DEFAULT NULL,
  `product_shipping_charges` float(15,3) DEFAULT NULL,
  `product_shipping_charges_original` float(15,3) DEFAULT NULL,
  `product_shipping_duties` float(15,3) DEFAULT NULL,
  `product_shipping_duties_original` float(15,3) DEFAULT NULL,
  `product_shipping_express_charges` float(15,3) DEFAULT NULL,
  `product_shipping_express_charges_original` float(15,3) DEFAULT NULL,
  `product_shipping_processing_charges` float(15,3) DEFAULT NULL,
  `product_shipping_processing_charges_original` float(15,3) DEFAULT NULL,
  `product_shipping_mapped` float(15,3) DEFAULT NULL,
  `store_location_id` int(10) unsigned DEFAULT NULL,
  `shipping_status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`order_detail_id`),
  KEY `FK_order_detail_1` (`order_header_id`),
  KEY `FK_order_detail_2` (`size_id`),
  KEY `FK_order_detail_3` (`store_location_id`),
  CONSTRAINT `FK_order_detail_1` FOREIGN KEY (`order_header_id`) REFERENCES `order_header` (`order_header_id`),
  CONSTRAINT `FK_order_detail_2` FOREIGN KEY (`size_id`) REFERENCES `core_size_master` (`size_id`),
  CONSTRAINT `FK_order_detail_3` FOREIGN KEY (`store_location_id`) REFERENCES `core_store_location_master` (`store_location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_detail`
--

/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`order_detail_id`,`order_header_id`,`product_id`,`product_sku_id`,`price_per_piece`,`original_price_per_piece`,`total_price`,`original_total_price`,`quantity`,`delivery_date`,`jewellery_valuation`,`customization_text`,`customization_font`,`size_id`,`product_shipping_charges`,`product_shipping_charges_original`,`product_shipping_duties`,`product_shipping_duties_original`,`product_shipping_express_charges`,`product_shipping_express_charges_original`,`product_shipping_processing_charges`,`product_shipping_processing_charges_original`,`product_shipping_mapped`,`store_location_id`,`shipping_status`) VALUES 
 (1,2,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (2,3,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (3,4,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (4,5,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `order_detail` (`order_detail_id`,`order_header_id`,`product_id`,`product_sku_id`,`price_per_piece`,`original_price_per_piece`,`total_price`,`original_total_price`,`quantity`,`delivery_date`,`jewellery_valuation`,`customization_text`,`customization_font`,`size_id`,`product_shipping_charges`,`product_shipping_charges_original`,`product_shipping_duties`,`product_shipping_duties_original`,`product_shipping_express_charges`,`product_shipping_express_charges_original`,`product_shipping_processing_charges`,`product_shipping_processing_charges_original`,`product_shipping_mapped`,`store_location_id`,`shipping_status`) VALUES 
 (5,5,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (6,6,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (7,7,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (8,8,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `order_detail` (`order_detail_id`,`order_header_id`,`product_id`,`product_sku_id`,`price_per_piece`,`original_price_per_piece`,`total_price`,`original_total_price`,`quantity`,`delivery_date`,`jewellery_valuation`,`customization_text`,`customization_font`,`size_id`,`product_shipping_charges`,`product_shipping_charges_original`,`product_shipping_duties`,`product_shipping_duties_original`,`product_shipping_express_charges`,`product_shipping_express_charges_original`,`product_shipping_processing_charges`,`product_shipping_processing_charges_original`,`product_shipping_mapped`,`store_location_id`,`shipping_status`) VALUES 
 (9,9,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (10,10,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (11,11,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 (12,12,1,1,500.000,500.000,500.000,0.000,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;


--
-- Definition of table `order_header`
--

DROP TABLE IF EXISTS `order_header`;
CREATE TABLE `order_header` (
  `order_header_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL,
  `user_login` varchar(50) DEFAULT NULL,
  `order_no` varchar(100) NOT NULL,
  `total_quantity` int(10) NOT NULL,
  `lead_time` int(11) DEFAULT NULL,
  `total_amount` float(15,3) NOT NULL,
  `original_total_amount` float(15,3) NOT NULL,
  `express_delivery_charge` float(15,3) DEFAULT NULL,
  `original_express_delivery_charge` float(15,3) DEFAULT NULL,
  `duties` float(15,3) DEFAULT NULL,
  `original_duties` float(15,3) DEFAULT NULL,
  `currency_conversion_rate` float(15,3) DEFAULT NULL,
  `currency_code` varchar(25) DEFAULT NULL,
  `currency_symbol_flag` int(1) DEFAULT NULL,
  `payment_status` varchar(25) DEFAULT NULL,
  `order_status` varchar(25) DEFAULT NULL,
  `billing_status` varchar(25) DEFAULT NULL,
  `billing_first_name` varchar(50) NOT NULL,
  `billing_middle_name` varchar(50) DEFAULT NULL,
  `billing_last_name` varchar(50) NOT NULL,
  `billing_email_address_1` varchar(100) NOT NULL,
  `billing_email_address_2` varchar(100) DEFAULT NULL,
  `billing_mobile_1` varchar(25) NOT NULL,
  `billing_mobile_2` varchar(25) DEFAULT NULL,
  `billing_address_line_1` varchar(100) NOT NULL,
  `billing_address_line_2` varchar(100) DEFAULT NULL,
  `billing_address_line_3` varchar(100) DEFAULT NULL,
  `billing_city` varchar(25) DEFAULT NULL,
  `billing_zip_code` varchar(25) DEFAULT NULL,
  `billing_state` varchar(25) NOT NULL,
  `billing_country` varchar(25) NOT NULL,
  `order_tracking_number` varchar(45) DEFAULT NULL,
  `order_date` date DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `courier_id` int(10) unsigned DEFAULT NULL,
  `voucher_code` varchar(25) DEFAULT NULL,
  `voucher_discount_percent` float(15,3) DEFAULT NULL,
  `voucher_discount_absolute` float(15,3) DEFAULT NULL,
  `discount_value` float(15,3) DEFAULT NULL,
  `gift_wrapping_required` tinyint(1) DEFAULT NULL,
  `order_comments` varchar(500) DEFAULT NULL,
  `email_regenerated_by` varchar(100) DEFAULT NULL,
  `promotion_source` varchar(100) DEFAULT NULL,
  `referer_source` varchar(100) DEFAULT NULL,
  `payment_status_modifier` varchar(100) DEFAULT NULL,
  `website_id` int(10) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`order_header_id`),
  KEY `FK_order_header_1` (`user_id`),
  KEY `FK_order_header_2` (`currency_code`),
  KEY `FK_order_header_3` (`courier_id`),
  KEY `FK_order_header_4` (`billing_city`),
  KEY `FK_order_header_5` (`billing_state`),
  KEY `FK_order_header_6` (`billing_country`),
  CONSTRAINT `FK_order_header_1` FOREIGN KEY (`user_id`) REFERENCES `core_user_master` (`user_id`),
  CONSTRAINT `FK_order_header_2` FOREIGN KEY (`currency_code`) REFERENCES `core_currency_master` (`currency_code`),
  CONSTRAINT `FK_order_header_3` FOREIGN KEY (`courier_id`) REFERENCES `core_courier_master` (`courier_id`),
  CONSTRAINT `FK_order_header_4` FOREIGN KEY (`billing_city`) REFERENCES `core_city_master` (`city_code`),
  CONSTRAINT `FK_order_header_5` FOREIGN KEY (`billing_state`) REFERENCES `core_state_master` (`state_code`),
  CONSTRAINT `FK_order_header_6` FOREIGN KEY (`billing_country`) REFERENCES `core_country_master` (`country_code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_header`
--

/*!40000 ALTER TABLE `order_header` DISABLE KEYS */;
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (2,7,'vsantlal@yahoo.com','PORTAL-2',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'9821772662',NULL,'asdasd','dasdas',NULL,'SU','400067','GJ','IN',NULL,'2014-10-02','2014-10-02',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'vsantlal@yahoo.com','2014-10-02 12:14:03','vsantlal@yahoo.com','2014-10-02 12:14:03');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (3,NULL,'guest','PORTAL-3',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'Santlal',NULL,'Vishwakarma','vsad@yahh.com',NULL,'9821772662',NULL,'dasda','dasd',NULL,'SU','71444','GJ','IN',NULL,'2014-10-02','2014-10-02',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'guest','2014-10-02 22:33:02','guest','2014-10-02 22:33:02');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (4,NULL,'guest','PORTAL-4',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'Santlal',NULL,'Vishwakarma','dasda@ga.com',NULL,'98989',NULL,'dasdas','dasdas',NULL,'SU','4544','GJ','IN',NULL,'2014-10-02','2014-10-02',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'guest','2014-10-02 22:36:35','guest','2014-10-02 22:36:35');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (5,8,'abc@yahoo.com','PORTAL-5',2,0,1000.000,1000.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'dasda',NULL,'dasd','abc@yahoo.com',NULL,'dasda','dasas','dasda','dasdas',NULL,'SU','4545','GJ','IN',NULL,'2014-11-30','2014-11-30',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'abc@yahoo.com','2014-11-30 18:54:08','abc@yahoo.com','2014-11-30 18:54:08');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (6,9,'abc123@yahoo.co','PORTAL-6',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'dasd',NULL,'dasd','abc123@yahoo.co',NULL,'dasd','dasd','dasda','dasda',NULL,'SU','45454545','GJ','IN',NULL,'2014-11-30','2014-11-30',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'abc123@yahoo.co','2014-11-30 18:56:47','abc123@yahoo.co','2014-11-30 18:56:47');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (7,7,'vsantlal@yahoo.com','PORTAL-7',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'9658658',NULL,'104, Marawah House','Marwah Marg',NULL,'MU','400072','MAH','IN',NULL,'2015-01-11','2015-01-11',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'vsantlal@yahoo.com','2015-01-11 00:08:01','vsantlal@yahoo.com','2015-01-11 00:08:01');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (8,7,'vsantlal@yahoo.com','PORTAL-8',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'9658658',NULL,'104, Marawah House','Marwah Marg',NULL,'MU','400072','MAH','IN',NULL,'2015-01-11','2015-01-11',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'vsantlal@yahoo.com','2015-01-11 00:40:40','vsantlal@yahoo.com','2015-01-11 00:40:40');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (9,7,'vsantlal@yahoo.com','PORTAL-9',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'9658658',NULL,'104, Marawah House','Marwah Marg',NULL,'MU','400072','MAH','IN',NULL,'2015-01-11','2015-01-11',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'vsantlal@yahoo.com','2015-01-11 00:43:39','vsantlal@yahoo.com','2015-01-11 00:43:39');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (10,NULL,'guest','PORTAL-10',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'dasdas',NULL,'dasdas','abc@gmail.com',NULL,'565656','dasdas','dsadasd','dasdas',NULL,'SU','asdasdasd','GJ','IN',NULL,'2015-05-31','2015-05-31',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'guest','2015-05-31 10:48:05','guest','2015-05-31 10:48:05');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (11,NULL,'guest','PORTAL-11',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'asd',NULL,'asd','a@a.cm',NULL,'588',NULL,'asd','fr',NULL,'SU','525','GJ','IN',NULL,'2015-06-21','2015-06-21',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'guest','2015-06-21 13:55:58','guest','2015-06-21 13:55:58');
INSERT INTO `order_header` (`order_header_id`,`user_id`,`user_login`,`order_no`,`total_quantity`,`lead_time`,`total_amount`,`original_total_amount`,`express_delivery_charge`,`original_express_delivery_charge`,`duties`,`original_duties`,`currency_conversion_rate`,`currency_code`,`currency_symbol_flag`,`payment_status`,`order_status`,`billing_status`,`billing_first_name`,`billing_middle_name`,`billing_last_name`,`billing_email_address_1`,`billing_email_address_2`,`billing_mobile_1`,`billing_mobile_2`,`billing_address_line_1`,`billing_address_line_2`,`billing_address_line_3`,`billing_city`,`billing_zip_code`,`billing_state`,`billing_country`,`order_tracking_number`,`order_date`,`delivery_date`,`courier_id`,`voucher_code`,`voucher_discount_percent`,`voucher_discount_absolute`,`discount_value`,`gift_wrapping_required`,`order_comments`,`email_regenerated_by`,`promotion_source`,`referer_source`,`payment_status_modifier`,`website_id`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (12,NULL,'guest','PORTAL-12',1,0,500.000,500.000,0.000,0.000,NULL,NULL,NULL,NULL,NULL,'1','4',NULL,'dasda',NULL,'dasda','ac@gmail.com',NULL,'323233232',NULL,'dasda','dasdas',NULL,'SU','4545','GJ','IN',NULL,'2016-04-26','2016-04-26',NULL,NULL,NULL,NULL,0.000,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'guest','2016-04-26 01:00:55','guest','2016-04-26 01:00:55');
/*!40000 ALTER TABLE `order_header` ENABLE KEYS */;


--
-- Definition of table `order_payment_mode_detail`
--

DROP TABLE IF EXISTS `order_payment_mode_detail`;
CREATE TABLE `order_payment_mode_detail` (
  `order_payment_mode_detail_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_header_id` int(10) unsigned DEFAULT NULL,
  `payment_type` int(10) DEFAULT NULL,
  `amount` float(15,3) DEFAULT NULL,
  `transaction_no` varchar(100) DEFAULT NULL,
  `bank_code` varchar(25) DEFAULT NULL,
  `is_confirm` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`order_payment_mode_detail_id`),
  KEY `FK_order_payment_mode_detail_1` (`order_header_id`),
  CONSTRAINT `FK_order_payment_mode_detail_1` FOREIGN KEY (`order_header_id`) REFERENCES `order_header` (`order_header_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_payment_mode_detail`
--

/*!40000 ALTER TABLE `order_payment_mode_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_payment_mode_detail` ENABLE KEYS */;


--
-- Definition of table `order_shipping_mapping`
--

DROP TABLE IF EXISTS `order_shipping_mapping`;
CREATE TABLE `order_shipping_mapping` (
  `order_shipping_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_header_id` int(10) unsigned NOT NULL,
  `order_detail_id` int(10) unsigned NOT NULL,
  `shipping_first_name` varchar(50) NOT NULL,
  `shipping_middle_name` varchar(50) DEFAULT NULL,
  `shipping_last_name` varchar(50) NOT NULL,
  `shipping_email_address_1` varchar(100) NOT NULL,
  `shipping_email_address_2` varchar(100) DEFAULT NULL,
  `shipping_mobile_1` varchar(25) NOT NULL,
  `shipping_mobile_2` varchar(25) DEFAULT NULL,
  `shipping_address_line_1` varchar(100) NOT NULL,
  `shipping_address_line_2` varchar(100) DEFAULT NULL,
  `shipping_address_line_3` varchar(100) DEFAULT NULL,
  `shipping_city` varchar(25) NOT NULL,
  `shipping_zip_code` varchar(15) NOT NULL,
  `shipping_state` varchar(25) NOT NULL,
  `shipping_country` varchar(25) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`order_shipping_mapping_id`),
  KEY `FK_order_shipping_mapping_1` (`order_header_id`),
  KEY `FK_order_shipping_mapping_2` (`order_detail_id`),
  KEY `FK_order_shipping_mapping_3` (`shipping_city`),
  KEY `FK_order_shipping_mapping_4` (`shipping_state`),
  KEY `FK_order_shipping_mapping_5` (`shipping_country`),
  CONSTRAINT `FK_order_shipping_mapping_1` FOREIGN KEY (`order_header_id`) REFERENCES `order_header` (`order_header_id`),
  CONSTRAINT `FK_order_shipping_mapping_2` FOREIGN KEY (`order_detail_id`) REFERENCES `order_detail` (`order_detail_id`),
  CONSTRAINT `FK_order_shipping_mapping_3` FOREIGN KEY (`shipping_city`) REFERENCES `core_city_master` (`city_code`),
  CONSTRAINT `FK_order_shipping_mapping_4` FOREIGN KEY (`shipping_state`) REFERENCES `core_state_master` (`state_code`),
  CONSTRAINT `FK_order_shipping_mapping_5` FOREIGN KEY (`shipping_country`) REFERENCES `core_country_master` (`country_code`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_shipping_mapping`
--

/*!40000 ALTER TABLE `order_shipping_mapping` DISABLE KEYS */;
INSERT INTO `order_shipping_mapping` (`order_shipping_mapping_id`,`order_header_id`,`order_detail_id`,`shipping_first_name`,`shipping_middle_name`,`shipping_last_name`,`shipping_email_address_1`,`shipping_email_address_2`,`shipping_mobile_1`,`shipping_mobile_2`,`shipping_address_line_1`,`shipping_address_line_2`,`shipping_address_line_3`,`shipping_city`,`shipping_zip_code`,`shipping_state`,`shipping_country`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,2,1,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'9821772662',NULL,'asdasd','dasdas',NULL,'SU','400067','GJ','IN','vsantlal@yahoo.com','2014-10-02 12:14:03','vsantlal@yahoo.com','2014-10-02 12:14:03'),
 (2,3,2,'Santlal',NULL,'Vishwakarma','vsad@yahh.com',NULL,'9821772662',NULL,'dasda','dasd',NULL,'SU','71444','GJ','IN','guest','2014-10-02 22:33:02','guest','2014-10-02 22:33:02'),
 (3,4,3,'Santlal',NULL,'Vishwakarma','dasda@ga.com',NULL,'98989',NULL,'dasdas','dasdas',NULL,'SU','4544','GJ','IN','guest','2014-10-02 22:36:35','guest','2014-10-02 22:36:35'),
 (4,5,4,'dasda',NULL,'dasd','abc@yahoo.com',NULL,'dasda','dasas','dasda','dasdas',NULL,'SU','4545','GJ','IN','abc@yahoo.com','2014-11-30 18:54:08','abc@yahoo.com','2014-11-30 18:54:08');
INSERT INTO `order_shipping_mapping` (`order_shipping_mapping_id`,`order_header_id`,`order_detail_id`,`shipping_first_name`,`shipping_middle_name`,`shipping_last_name`,`shipping_email_address_1`,`shipping_email_address_2`,`shipping_mobile_1`,`shipping_mobile_2`,`shipping_address_line_1`,`shipping_address_line_2`,`shipping_address_line_3`,`shipping_city`,`shipping_zip_code`,`shipping_state`,`shipping_country`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (5,5,5,'dasda',NULL,'dasd','abc@yahoo.com',NULL,'dasda','dasas','dasda','dasdas',NULL,'SU','4545','GJ','IN','abc@yahoo.com','2014-11-30 18:54:08','abc@yahoo.com','2014-11-30 18:54:08'),
 (6,6,6,'dasd',NULL,'dasd','abc123@yahoo.co',NULL,'dasd','dasd','dasda','dasda',NULL,'SU','45454545','GJ','IN','abc123@yahoo.co','2014-11-30 18:56:47','abc123@yahoo.co','2014-11-30 18:56:47'),
 (7,7,7,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'98668956',NULL,'Abhilakh nagar','Lalgi pada',NULL,'MU','400067','MAH','IN','vsantlal@yahoo.com','2015-01-11 00:08:02','vsantlal@yahoo.com','2015-01-11 00:08:02'),
 (8,8,8,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'98668956',NULL,'Abhilakh nagar','Lalgi pada',NULL,'MU','400067','MAH','IN','vsantlal@yahoo.com','2015-01-11 00:40:40','vsantlal@yahoo.com','2015-01-11 00:40:40');
INSERT INTO `order_shipping_mapping` (`order_shipping_mapping_id`,`order_header_id`,`order_detail_id`,`shipping_first_name`,`shipping_middle_name`,`shipping_last_name`,`shipping_email_address_1`,`shipping_email_address_2`,`shipping_mobile_1`,`shipping_mobile_2`,`shipping_address_line_1`,`shipping_address_line_2`,`shipping_address_line_3`,`shipping_city`,`shipping_zip_code`,`shipping_state`,`shipping_country`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (9,9,9,'Santlal',NULL,'Vishwakarma','vsantlal@yahoo.com',NULL,'98668956',NULL,'Abhilakh nagar','Lalgi pada',NULL,'MU','400067','MAH','IN','vsantlal@yahoo.com','2015-01-11 00:43:39','vsantlal@yahoo.com','2015-01-11 00:43:39'),
 (10,10,10,'dasdas',NULL,'dasdas','abc@gmail.com',NULL,'565656','dasdas','dsadasd','dasdas',NULL,'SU','asdasdasd','GJ','IN','guest','2015-05-31 10:48:06','guest','2015-05-31 10:48:06'),
 (11,11,11,'asd',NULL,'asd','a@a.cm',NULL,'588',NULL,'asd','fr',NULL,'SU','525','GJ','IN','guest','2015-06-21 13:55:58','guest','2015-06-21 13:55:58'),
 (12,12,12,'dasda',NULL,'dasda','ac@gmail.com',NULL,'323233232',NULL,'dasda','dasdas',NULL,'SU','4545','GJ','IN','guest','2016-04-26 01:00:55','guest','2016-04-26 01:00:55');
/*!40000 ALTER TABLE `order_shipping_mapping` ENABLE KEYS */;


--
-- Definition of table `product_header`
--

DROP TABLE IF EXISTS `product_header`;
CREATE TABLE `product_header` (
  `product_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_code` varchar(25) DEFAULT NULL,
  `product_name` varchar(60) DEFAULT NULL,
  `product_description` varchar(255) DEFAULT NULL,
  `status_code` varchar(25) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_header`
--

/*!40000 ALTER TABLE `product_header` DISABLE KEYS */;
INSERT INTO `product_header` (`product_id`,`product_code`,`product_name`,`product_description`,`status_code`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,'product1','John Miller Shirt','this shirt is very nice','ACTIVE',1,'admin','2014-06-15 14:03:01','admin','2016-10-23 00:49:47'),
 (2,'product_2','Raymond',NULL,'ACTIVE',1,'admin','2014-06-15 14:03:01','admin','2014-06-15 14:03:01');
/*!40000 ALTER TABLE `product_header` ENABLE KEYS */;


--
-- Definition of table `product_hierarchy_category_mapping`
--

DROP TABLE IF EXISTS `product_hierarchy_category_mapping`;
CREATE TABLE `product_hierarchy_category_mapping` (
  `product_hierarchy_category_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned DEFAULT NULL,
  `hierarchy_category_mapping_id` int(10) unsigned NOT NULL,
  `hierarchy_id` int(10) DEFAULT NULL,
  `category_level_1` int(10) DEFAULT NULL,
  `category_level_2` int(10) DEFAULT NULL,
  `category_level_3` int(10) DEFAULT NULL,
  `category_level_4` int(10) DEFAULT NULL,
  `category_level_5` int(10) DEFAULT NULL,
  `is_default` tinyint(1) NOT NULL DEFAULT '0',
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_hierarchy_category_mapping_id`),
  KEY `FK_product_hierarchy_category_mapping_1` (`product_id`),
  KEY `FK_product_hierarchy_category_mapping_2` (`hierarchy_category_mapping_id`),
  CONSTRAINT `FK_product_hierarchy_category_mapping_1` FOREIGN KEY (`product_id`) REFERENCES `product_header` (`product_id`),
  CONSTRAINT `FK_product_hierarchy_category_mapping_2` FOREIGN KEY (`hierarchy_category_mapping_id`) REFERENCES `hierarchy_category_mapping` (`hierarchy_category_mapping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_hierarchy_category_mapping`
--

/*!40000 ALTER TABLE `product_hierarchy_category_mapping` DISABLE KEYS */;
INSERT INTO `product_hierarchy_category_mapping` (`product_hierarchy_category_mapping_id`,`product_id`,`hierarchy_category_mapping_id`,`hierarchy_id`,`category_level_1`,`category_level_2`,`category_level_3`,`category_level_4`,`category_level_5`,`is_default`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,1,1,1,1,19,NULL,NULL,NULL,0,1,'admin','2014-06-15 15:25:52','admin','2014-06-15 15:25:52'),
 (2,2,2,1,1,20,NULL,NULL,NULL,0,1,'admin','2014-06-15 15:25:52','admin','2014-06-15 15:25:52');
/*!40000 ALTER TABLE `product_hierarchy_category_mapping` ENABLE KEYS */;


--
-- Definition of table `product_sku_header`
--

DROP TABLE IF EXISTS `product_sku_header`;
CREATE TABLE `product_sku_header` (
  `product_sku_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `sku_code` varchar(25) DEFAULT NULL,
  `sku_name` varchar(60) DEFAULT NULL,
  `sku_description` varchar(255) DEFAULT NULL,
  `sku_property_text` varchar(512) DEFAULT NULL,
  `seo_title` varchar(100) DEFAULT NULL,
  `seo_keyword` varchar(100) DEFAULT NULL,
  `seo_description` text,
  `default_thumbnail_image_url` varchar(4000) DEFAULT NULL,
  `default_zoom_image_url` varchar(4000) DEFAULT NULL,
  `base_price` float(15,3) DEFAULT NULL,
  `discount_amount` float(15,3) DEFAULT NULL,
  `discount_percent` float(15,3) DEFAULT NULL,
  `final_base_price` float(15,3) DEFAULT NULL,
  `default_sku` tinyint(1) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_sku_id`),
  KEY `FK_product_sku_header_1` (`product_id`),
  CONSTRAINT `FK_product_sku_header_1` FOREIGN KEY (`product_id`) REFERENCES `product_header` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_sku_header`
--

/*!40000 ALTER TABLE `product_sku_header` DISABLE KEYS */;
INSERT INTO `product_sku_header` (`product_sku_id`,`product_id`,`sku_code`,`sku_name`,`sku_description`,`sku_property_text`,`seo_title`,`seo_keyword`,`seo_description`,`default_thumbnail_image_url`,`default_zoom_image_url`,`base_price`,`discount_amount`,`discount_percent`,`final_base_price`,`default_sku`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,1,'product_1_john_miller_1','John Miller shirt wow','It is very nice shirt. popular in TSD',NULL,'portal john miller shirts','portal',NULL,NULL,NULL,500.000,NULL,NULL,500.000,1,1,'admin','2014-06-15 14:03:01','admin','2014-06-15 14:03:01'),
 (2,1,'product_1_john_miller_2','John Miller  T-shirt wow','t-shirt. popular in FJ',NULL,'seo product',NULL,NULL,NULL,NULL,1000.000,50.000,NULL,950.000,NULL,1,'admin','2014-06-15 14:03:01','admin','2014-06-15 14:03:01'),
 (3,1,'product_1_john_miller_3','John Miller  Pants','new pants in market',NULL,'pants john miller','yes javed',NULL,NULL,NULL,4000.000,NULL,10.000,3600.000,NULL,1,'admin','2014-06-15 14:03:01','admin','2014-06-15 14:03:01'),
 (4,2,'product_1_raymond_1','Raymond Shirts','Raymond shirts for men',NULL,'wow','wow javed',NULL,NULL,NULL,3200.000,NULL,NULL,3200.000,1,1,'admin','2014-06-15 14:03:01','admin','2014-06-15 14:03:01');
INSERT INTO `product_sku_header` (`product_sku_id`,`product_id`,`sku_code`,`sku_name`,`sku_description`,`sku_property_text`,`seo_title`,`seo_keyword`,`seo_description`,`default_thumbnail_image_url`,`default_zoom_image_url`,`base_price`,`discount_amount`,`discount_percent`,`final_base_price`,`default_sku`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (5,2,'product_1_raymond_2','Raymond Jeans','New jeans ',NULL,'raymon jeans',NULL,NULL,NULL,NULL,5000.000,2000.000,NULL,3000.000,NULL,1,'admin','2014-06-15 14:03:01','admin','2014-06-15 14:03:01');
/*!40000 ALTER TABLE `product_sku_header` ENABLE KEYS */;


--
-- Definition of table `product_sku_header_detail_mapping`
--

DROP TABLE IF EXISTS `product_sku_header_detail_mapping`;
CREATE TABLE `product_sku_header_detail_mapping` (
  `product_sku_header_detail_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned DEFAULT NULL,
  `product_sku_id` int(10) unsigned DEFAULT NULL,
  `default_sku` tinyint(1) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_sku_header_detail_mapping_id`),
  KEY `FK_product_sku_header_detail_mapping_1` (`product_id`),
  KEY `FK_product_sku_header_detail_mapping_2` (`product_sku_id`),
  CONSTRAINT `FK_product_sku_header_detail_mapping_1` FOREIGN KEY (`product_id`) REFERENCES `product_header` (`product_id`),
  CONSTRAINT `FK_product_sku_header_detail_mapping_2` FOREIGN KEY (`product_sku_id`) REFERENCES `product_sku_header` (`product_sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_sku_header_detail_mapping`
--

/*!40000 ALTER TABLE `product_sku_header_detail_mapping` DISABLE KEYS */;
INSERT INTO `product_sku_header_detail_mapping` (`product_sku_header_detail_mapping_id`,`product_id`,`product_sku_id`,`default_sku`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,1,1,1,'admin','2016-10-23 01:17:40','admin','2016-10-23 01:17:40'),
 (2,2,3,1,'admin','2016-10-23 01:17:40','admin','2016-10-23 01:17:40'),
 (3,1,5,NULL,'admin','2016-10-23 01:17:40','admin','2016-10-23 01:17:40');
/*!40000 ALTER TABLE `product_sku_header_detail_mapping` ENABLE KEYS */;


--
-- Definition of table `product_sku_image_mapping`
--

DROP TABLE IF EXISTS `product_sku_image_mapping`;
CREATE TABLE `product_sku_image_mapping` (
  `product_sku_image_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_sku_id` int(10) unsigned NOT NULL,
  `image_type` int(10) DEFAULT NULL,
  `thumbnail_image_url` varchar(4000) DEFAULT NULL,
  `zoom_image_url` varchar(4000) DEFAULT NULL,
  `sequence_no` int(10) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_sku_image_mapping_id`),
  KEY `FK_product_sku_image_mapping_1` (`product_sku_id`),
  CONSTRAINT `FK_product_sku_image_mapping_1` FOREIGN KEY (`product_sku_id`) REFERENCES `product_sku_header` (`product_sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_sku_image_mapping`
--

/*!40000 ALTER TABLE `product_sku_image_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_sku_image_mapping` ENABLE KEYS */;


--
-- Definition of table `product_sku_property_mapping`
--

DROP TABLE IF EXISTS `product_sku_property_mapping`;
CREATE TABLE `product_sku_property_mapping` (
  `product_sku_property_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `product_sku_id` int(10) unsigned NOT NULL,
  `property_id` int(10) unsigned NOT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_sku_property_mapping_id`),
  KEY `FK_product_sku_property_mapping_1` (`product_id`),
  KEY `FK_product_sku_property_mapping_2` (`product_sku_id`),
  KEY `FK_product_sku_property_mapping_3` (`property_id`),
  CONSTRAINT `FK_product_sku_property_mapping_1` FOREIGN KEY (`product_id`) REFERENCES `product_header` (`product_id`),
  CONSTRAINT `FK_product_sku_property_mapping_2` FOREIGN KEY (`product_sku_id`) REFERENCES `product_sku_header` (`product_sku_id`),
  CONSTRAINT `FK_product_sku_property_mapping_3` FOREIGN KEY (`property_id`) REFERENCES `property_master` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_sku_property_mapping`
--

/*!40000 ALTER TABLE `product_sku_property_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_sku_property_mapping` ENABLE KEYS */;


--
-- Definition of table `product_sku_size_mapping`
--

DROP TABLE IF EXISTS `product_sku_size_mapping`;
CREATE TABLE `product_sku_size_mapping` (
  `product_sku_size_mapping_id` int(10) unsigned NOT NULL DEFAULT '0',
  `product_id` int(10) unsigned DEFAULT NULL,
  `product_sku_id` int(10) unsigned DEFAULT NULL,
  `size_id` int(10) unsigned DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_sku_size_mapping_id`),
  KEY `FK_product_sku_size_mapping_product_id` (`product_id`),
  KEY `FK_product_sku_size_mapping_sku_id` (`product_sku_id`),
  KEY `FK_product_sku_size_mapping_size_id` (`size_id`),
  CONSTRAINT `FK_product_sku_size_mapping_product_id` FOREIGN KEY (`product_id`) REFERENCES `product_header` (`product_id`),
  CONSTRAINT `FK_product_sku_size_mapping_size_id` FOREIGN KEY (`size_id`) REFERENCES `core_size_master` (`size_id`),
  CONSTRAINT `FK_product_sku_size_mapping_sku_id` FOREIGN KEY (`product_sku_id`) REFERENCES `product_sku_header` (`product_sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_sku_size_mapping`
--

/*!40000 ALTER TABLE `product_sku_size_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_sku_size_mapping` ENABLE KEYS */;


--
-- Definition of table `product_sku_stock_level`
--

DROP TABLE IF EXISTS `product_sku_stock_level`;
CREATE TABLE `product_sku_stock_level` (
  `product_sku_stock_level_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(10) unsigned NOT NULL,
  `product_sku_id` int(10) unsigned NOT NULL,
  `size_id` int(10) unsigned DEFAULT NULL,
  `reorder_level` float(15,3) DEFAULT NULL,
  `available_quantity` float(15,3) DEFAULT NULL,
  `blocked_quantity` float(15,3) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`product_sku_stock_level_id`),
  KEY `FK_product_stock_level_1` (`product_id`),
  KEY `FK_product_stock_level_2` (`product_sku_id`),
  KEY `FK_product_stock_level_3` (`size_id`),
  CONSTRAINT `FK_product_stock_level_1` FOREIGN KEY (`product_id`) REFERENCES `product_header` (`product_id`),
  CONSTRAINT `FK_product_stock_level_13` FOREIGN KEY (`size_id`) REFERENCES `core_size_master` (`size_id`),
  CONSTRAINT `FK_product_stock_level_2` FOREIGN KEY (`product_sku_id`) REFERENCES `product_sku_header` (`product_sku_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_sku_stock_level`
--

/*!40000 ALTER TABLE `product_sku_stock_level` DISABLE KEYS */;
INSERT INTO `product_sku_stock_level` (`product_sku_stock_level_id`,`product_id`,`product_sku_id`,`size_id`,`reorder_level`,`available_quantity`,`blocked_quantity`,`is_active`,`created_by`,`created_date`,`modified_by`,`modified_date`) VALUES 
 (1,1,1,NULL,1.000,38.000,14.000,1,'admin','2014-06-15 15:25:52','admin','2014-06-15 15:25:52'),
 (2,1,2,NULL,1.000,500.000,10.000,1,'admin','2014-06-15 15:25:52','admin','2014-06-15 15:25:52'),
 (3,1,3,NULL,1.000,100.000,4.000,1,'admin','2014-06-15 15:25:52','admin','2014-06-15 15:25:52'),
 (4,2,4,NULL,1.000,500.000,2.000,1,'admin','2014-06-15 15:25:52','admin','2014-06-15 15:25:52'),
 (5,2,5,NULL,1.000,400.000,2.000,1,'admin','2014-06-15 15:25:52','admin','2014-06-15 15:25:52');
/*!40000 ALTER TABLE `product_sku_stock_level` ENABLE KEYS */;


--
-- Definition of table `property_master`
--

DROP TABLE IF EXISTS `property_master`;
CREATE TABLE `property_master` (
  `property_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `property_code` varchar(25) DEFAULT NULL,
  `property_name` varchar(60) DEFAULT NULL,
  `property_description` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `property_master`
--

/*!40000 ALTER TABLE `property_master` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_master` ENABLE KEYS */;


--
-- Definition of table `property_value_mapping`
--

DROP TABLE IF EXISTS `property_value_mapping`;
CREATE TABLE `property_value_mapping` (
  `property_value_mapping_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `property_id` int(10) unsigned NOT NULL,
  `property_value` varchar(100) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` datetime NOT NULL,
  `modified_by` varchar(50) NOT NULL,
  `modified_date` datetime NOT NULL,
  PRIMARY KEY (`property_value_mapping_id`),
  KEY `FK_property_value_mapping_1` (`property_id`),
  CONSTRAINT `FK_property_value_mapping_1` FOREIGN KEY (`property_id`) REFERENCES `property_master` (`property_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `property_value_mapping`
--

/*!40000 ALTER TABLE `property_value_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `property_value_mapping` ENABLE KEYS */;


--
-- Definition of function `fn_core_get_city_name`
--

DROP FUNCTION IF EXISTS `fn_core_get_city_name`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_core_get_city_name`(p_city_code VARCHAR(50)) RETURNS varchar(100) CHARSET utf8
    READS SQL DATA
BEGIN
	DECLARE v_city_name VARCHAR(60);

        SELECT  ccm.city_name
        INTO    v_city_name
        FROM    core_city_master ccm
        WHERE   ccm.city_code = p_city_code LIMIT 1;

	RETURN(v_city_name);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_core_get_country_name`
--

DROP FUNCTION IF EXISTS `fn_core_get_country_name`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_core_get_country_name`(p_country_code VARCHAR(50)) RETURNS varchar(100) CHARSET utf8
    READS SQL DATA
BEGIN
	DECLARE v_country_name VARCHAR(60);

        SELECT  ccm.country_name
        INTO    v_country_name
        FROM    core_country_master ccm
        WHERE   ccm.country_code = p_country_code LIMIT 1;

	RETURN(v_country_name);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_core_get_state_name`
--

DROP FUNCTION IF EXISTS `fn_core_get_state_name`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_core_get_state_name`(p_state_code VARCHAR(50)) RETURNS varchar(100) CHARSET utf8
    READS SQL DATA
BEGIN
	DECLARE v_state_name VARCHAR(60);

        SELECT  csm.state_name
        INTO    v_state_name
        FROM    core_state_master csm
        WHERE   csm.state_code = p_state_code LIMIT 1;

	RETURN(v_state_name);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_core_get_status_name`
--

DROP FUNCTION IF EXISTS `fn_core_get_status_name`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_core_get_status_name`(p_stauts_code VARCHAR(60)) RETURNS varchar(60) CHARSET utf8
    READS SQL DATA
BEGIN
DECLARE output_text VARCHAR(60);
       
        SELECT  status_name INTO output_text
        FROM    core_status_master cum
        WHERE   cum.status_code = p_stauts_code LIMIT 1;       
        
RETURN(output_text);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_category_code_based_on_id`
--

DROP FUNCTION IF EXISTS `fn_get_category_code_based_on_id`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_category_code_based_on_id`(p_category_id INT) RETURNS varchar(25) CHARSET utf8
    READS SQL DATA
BEGIN DECLARE v_category_code varchar(25);
IF p_category_id IS NOT NULL THEN
SELECT category_code
INTO   v_category_code
FROM   category_master
WHERE category_id = p_category_id;
END IF;
RETURN(v_category_code);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_category_code_based_on_name`
--

DROP FUNCTION IF EXISTS `fn_get_category_code_based_on_name`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_category_code_based_on_name`(p_category_name VARCHAR(500)) RETURNS varchar(500) CHARSET utf8
    READS SQL DATA
BEGIN
	DECLARE v_category_code VARCHAR(60);

        SELECT  category_code
        INTO    v_category_code
        FROM    category_master
        WHERE   category_name = p_category_name LIMIT 1;

	RETURN(v_category_code);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_category_id_based_on_code`
--

DROP FUNCTION IF EXISTS `fn_get_category_id_based_on_code`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_category_id_based_on_code`(p_category_code VARCHAR(500)) RETURNS int(11)
    READS SQL DATA
BEGIN
	DECLARE v_category_id INT(10);
        SELECT  category_id
        INTO    v_category_id
        FROM    category_master
        WHERE   category_code = p_category_code LIMIT 1;
	RETURN(v_category_id);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_category_name_based_on_id`
--

DROP FUNCTION IF EXISTS `fn_get_category_name_based_on_id`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_category_name_based_on_id`(p_category_id INT) RETURNS varchar(60) CHARSET utf8
    READS SQL DATA
BEGIN DECLARE v_category_name varchar(60);
IF p_category_id IS NOT NULL THEN
SELECT category_name
INTO   v_category_name
FROM   category_master
WHERE category_id = p_category_id;
END IF;
RETURN(v_category_name);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_hierarchy_code_based_on_id`
--

DROP FUNCTION IF EXISTS `fn_get_hierarchy_code_based_on_id`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_hierarchy_code_based_on_id`(p_hierarchy_id INT) RETURNS varchar(25) CHARSET utf8
    READS SQL DATA
BEGIN DECLARE v_hierarchy_code varchar(25);
IF p_hierarchy_id IS NOT NULL THEN
SELECT hierarchy_code
INTO   v_hierarchy_code
FROM   hierarchy_master
WHERE hierarchy_id = p_hierarchy_id;
END IF;
RETURN(v_hierarchy_code);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_hierarchy_id_based_on_name`
--

DROP FUNCTION IF EXISTS `fn_get_hierarchy_id_based_on_name`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_hierarchy_id_based_on_name`(p_hierarchy_name VARCHAR(500)) RETURNS int(10)
    READS SQL DATA
BEGIN
	DECLARE v_hierarchy_id INT(10);
        SELECT  hierarchy_id
        INTO    v_hierarchy_id
        FROM    hierarchy_master
        WHERE   hierarchy_name = p_hierarchy_name LIMIT 1;
	RETURN(v_hierarchy_id);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_hierarchy_name_based_on_id`
--

DROP FUNCTION IF EXISTS `fn_get_hierarchy_name_based_on_id`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_hierarchy_name_based_on_id`(p_hierarchy_id INT) RETURNS varchar(60) CHARSET utf8
    READS SQL DATA
BEGIN DECLARE v_hierarchy_name varchar(60);
IF p_hierarchy_id IS NOT NULL THEN
SELECT hierarchy_name
INTO   v_hierarchy_name
FROM   hierarchy_master
WHERE hierarchy_id = p_hierarchy_id;
END IF;
RETURN(v_hierarchy_name);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_parameter_sequence`
--

DROP FUNCTION IF EXISTS `fn_get_parameter_sequence`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_parameter_sequence`(p_parameter_id INT(10)) RETURNS int(4)
    READS SQL DATA
BEGIN
		DECLARE v_sequence_number INT(4);

        SELECT  sequence_number
        INTO    v_sequence_number
        FROM    core_parameter_master
        WHERE   parameter_id = p_parameter_id LIMIT 1;

		RETURN v_sequence_number;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_get_parameter_value_text`
--

DROP FUNCTION IF EXISTS `fn_get_parameter_value_text`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_get_parameter_value_text`(p_parameter_id INT(10)) RETURNS varchar(255) CHARSET utf8
    READS SQL DATA
BEGIN
		DECLARE v_value_text VARCHAR(255);

        SELECT  value_text
        INTO    v_value_text
        FROM    core_parameter_master
        WHERE   parameter_id = p_parameter_id LIMIT 1;

		RETURN v_value_text;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of function `fn_product_get_product_sku_image`
--

DROP FUNCTION IF EXISTS `fn_product_get_product_sku_image`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` FUNCTION `fn_product_get_product_sku_image`(p_product_id INT(10), p_product_sku_id INT(10)) RETURNS text CHARSET utf8
    READS SQL DATA
BEGIN
DECLARE output_text TEXT;
          
IF p_product_id IS NOT NULL AND p_product_sku_id IS NULL THEN
		
	SELECT 	GROUP_CONCAT(pim.sequence_no,'~',pim.zoom_image_url,'~',pim.thumbnail_image_url ORDER BY pim.sequence_no SEPARATOR ';;') INTO output_text
	FROM 	product_sku_image_mapping pim,
			product_sku_header psh
	WHERE 	pim.product_id = p_product_id
  	AND     psh.product_sku_id = pim.product_sku_id
  	AND 	psh.product_id = pim.product_id;

  
ELSEIF p_product_sku_id IS NOT NULL THEN
		
	SELECT 	GROUP_CONCAT(psim.sequence_no,'~',psim.zoom_image_url,'~',psim.thumbnail_image_url ORDER BY psim.sequence_no  SEPARATOR ';;') INTO output_text
	FROM 	product_sku_image_mapping psim,
			product_sku_header psh
	WHERE 	psh.product_sku_id = p_product_sku_id
  	AND     psh.product_sku_id = psim.product_sku_id;
  	
  
END IF;
	
RETURN(output_text);
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_core_get_shipping_charges_yes`
--

DROP PROCEDURE IF EXISTS `sp_core_get_shipping_charges_yes`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_core_get_shipping_charges_yes`(
IN p_country_code VARCHAR(25),
IN p_hierarchy_id INT,
IN p_level1_category_id INT,
IN p_level2_category_id INT,
IN p_level3_category_id INT,
IN p_level4_category_id INT
)
BEGIN
	DECLARE v_selected_hierarchy_category_charge_id INT(10);
	DECLARE v_count_country INT;
	DECLARE v_default_country VARCHAR(25);
	DECLARE v_delivery_charge FLOAT(15,3);
	DECLARE v_processing_charge FLOAT(15,3);
	DECLARE v_duties_charge FLOAT(15,3);
	DECLARE v_express_charge FLOAT(15,3);
    
    
	SELECT  hierarchy_category_charge_details_id 
	INTO	v_selected_hierarchy_category_charge_id
	FROM	core_hierarchy_category_charge_details
	WHERE	hierarchy_id = p_hierarchy_id
	AND		category_level_1_id = p_level1_category_id
	AND		category_level_2_id = p_level2_category_id
	AND		category_level_3_id = p_level3_category_id
	AND		category_level_4_id = p_level4_category_id;
	
	SELECT country_code INTO v_default_country FROM core_country_master WHERE country_name = 'Default';
	
	IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
		SELECT COUNT(*) INTO  v_count_country 
		FROM core_hierarchy_category_country_charge_mapping 
		WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
		IF v_count_country = 1 THEN
			SELECT delivery_charge, processing_charge, duties_charge, express_charge 
			INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
			FROM core_hierarchy_category_country_charge_mapping
			WHERE country_code = p_country_code 
			AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
		END IF;	
		
		IF v_count_country = 0 THEN
			
			SELECT delivery_charge, processing_charge, duties_charge, express_charge 
			INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = v_default_country 
			AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
								
		END IF;
	
	END IF;
	
	
	IF v_delivery_charge IS NULL THEN
		
		
		 SET v_selected_hierarchy_category_charge_id = NULL;
		
		SELECT hierarchy_category_charge_details_id INTO v_selected_hierarchy_category_charge_id
		FROM core_hierarchy_category_charge_details 
		WHERE	hierarchy_id = p_hierarchy_id
		AND		category_level_1_id = p_level1_category_id
		AND		category_level_2_id = p_level2_category_id
		AND		category_level_3_id = p_level3_category_id
		AND		category_level_4_id = NULL;
		
		IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
			SELECT COUNT(*) INTO  v_count_country 
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
			IF v_count_country = 1 THEN
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping
				WHERE country_code = p_country_code 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
			END IF;	
			
			IF v_count_country = 0 THEN
				
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping 
				WHERE country_code = v_default_country 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
									
			END IF;
	
		END IF;
		
	END IF;
	
	
	IF v_delivery_charge IS NULL THEN
		
		 SET v_selected_hierarchy_category_charge_id = NULL;
		
		SELECT hierarchy_category_charge_details_id INTO v_selected_hierarchy_category_charge_id
		FROM core_hierarchy_category_charge_details 
		WHERE	hierarchy_id = p_hierarchy_id
		AND		category_level_1_id = p_level1_category_id
		AND		category_level_2_id = p_level2_category_id
		AND		category_level_3_id = NULL
		AND		category_level_4_id = NULL;
		
		IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
			SELECT COUNT(*) INTO  v_count_country 
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
			IF v_count_country = 1 THEN
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping
				WHERE country_code = p_country_code 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
			END IF;	
			
			IF v_count_country = 0 THEN
				
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping 
				WHERE country_code = v_default_country 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
									
			END IF;
	
		END IF;
		
	END IF;
	
	
	
	IF v_delivery_charge IS NULL THEN
		
		 SET v_selected_hierarchy_category_charge_id = NULL;
		
		SELECT hierarchy_category_charge_details_id INTO v_selected_hierarchy_category_charge_id
		FROM core_hierarchy_category_charge_details 
		WHERE	hierarchy_id = p_hierarchy_id
		AND		category_level_1_id = p_level1_category_id
		AND		category_level_2_id = NULL
		AND		category_level_3_id = NULL
		AND		category_level_4_id = NULL;
		
		IF v_selected_hierarchy_category_charge_id IS NOT NULL THEN
	
			SELECT COUNT(*) INTO  v_count_country 
			FROM core_hierarchy_category_country_charge_mapping 
			WHERE country_code = p_country_code AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
	
			IF v_count_country = 1 THEN
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping
				WHERE country_code = p_country_code 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
			END IF;	
			
			IF v_count_country = 0 THEN
				
				SELECT delivery_charge, processing_charge, duties_charge, express_charge 
				INTO v_delivery_charge, v_processing_charge, v_duties_charge,v_express_charge
				FROM core_hierarchy_category_country_charge_mapping 
				WHERE country_code = v_default_country 
				AND hierarchy_category_charge_details_id = v_selected_hierarchy_category_charge_id;
									
			END IF;
	
		END IF;
		
	END IF;
	
	
	
	 SELECT v_selected_hierarchy_category_charge_id, v_delivery_charge, v_processing_charge, v_duties_charge, v_express_charge;
	
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_product_get_product_sku_details`
--

DROP PROCEDURE IF EXISTS `sp_product_get_product_sku_details`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_product_get_product_sku_details`(
IN p_product_id INT(10),
IN p_product_sku_id INT(10)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN

DECLARE v_hierarchy TINYINT(1);
DECLARE v_properties TINYINT(1);
DECLARE v_rm TINYINT(1);
DECLARE v_process_variation TINYINT(1);
DECLARE v_complementary_sku TINYINT(1);
DECLARE v_similar_sku TINYINT(1);
DECLARE v_props TINYINT(1);
DECLARE v_catalog TINYINT(1);
DECLARE v_images TINYINT(1);
DECLARE v_items TINYINT(1);

IF p_product_id IS NOT NULL AND p_product_sku_id IS NULL THEN	

	SELECT 	'HDR', ph.product_id, ph.product_code, ph.product_name, ph.product_description,
			ph.status_code, fn_core_get_status_name(ph.status_code) AS  status_name, ph.is_active,
			fn_product_get_product_sku_image(p_product_id, p_product_sku_id) AS image_url, ph.created_by, ph.created_date, ph.modified_by, ph.modified_date
	FROM 	product_header ph
	WHERE 	ph.product_id =  p_product_id;
	
	SELECT 	'DTL',psh.product_sku_id, psh.sku_code,sku_name, psh.sku_description, psh.sku_property_text, psh.seo_title, psh.seo_keyword, psh.seo_description, psh.default_thumbnail_image_url,
			psh.default_zoom_image_url, psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, psh.created_by, psh.created_date, psh.modified_by, psh.modified_date,
			psh.default_sku
	FROM 	product_header ph, product_sku_header psh
	WHERE 	ph.product_id =  p_product_id
	AND		psh.product_id = ph.product_id;
	
ELSEIF p_product_sku_id IS NOT NULL THEN
	
	SELECT 	'DTL',psh.product_id, psh.product_sku_id, psh.sku_code, psh.sku_name, psh.sku_description,
			psh.is_active AS sku_is_active, psh.modified_date,
			ph.product_code, ph.product_name, ph.product_description,
			fn_product_get_product_sku_image(p_product_id, p_product_sku_id) AS image_url, ph.created_by, ph.created_date, ph.modified_by, ph.modified_date
	FROM 	product_sku_header psh,
			product_header ph
	WHERE 	psh.product_sku_id =  p_product_sku_id
	AND 	psh.product_id = ph.product_id;
	
	
END IF;

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_product_save_product_details`
--

DROP PROCEDURE IF EXISTS `sp_product_save_product_details`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_product_save_product_details`(
IN p_product_id INT(10), 
IN p_product_code VARCHAR(25),
IN p_product_name VARCHAR(60),
IN p_product_description VARCHAR(255),
IN p_is_active TINYINT(1),
IN p_user_login VARCHAR(50),
IN p_modified_date VARCHAR(25),
OUT p_last_inserted_id INT(10),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
    
    DECLARE v_last_inserted_id INT(10);       
    DECLARE v_is_active TINYINT(1);
    DECLARE v_modified_date DATETIME ;
    DECLARE v_counter INT;
    DECLARE v_product_code_counter INT;
    DECLARE v_product_name_counter INT;
    DECLARE v_user_login VARCHAR(50);
    DECLARE v_status_code VARCHAR(25);
    
    SET v_modified_date = DATE_FORMAT(p_modified_date, '%Y-%m-%d %H:%i:%s');
    
	IF p_product_id IS NOT NULL THEN
	    	
	    	SELECT  COUNT(1) INTO v_counter
		    FROM 	product_header
		    WHERE   product_id = p_product_id
		    AND     modified_date  = v_modified_date;          
		      
		    IF v_counter > 0 THEN  
		    
			        SELECT  COUNT(1) INTO v_product_name_counter
			        FROM    product_header
			        WHERE   product_name = p_product_name
			        AND     product_id != p_product_id;
		        	
			        IF v_product_name_counter = 0 THEN
		        	    
			            UPDATE  product_header
			            SET     product_name = p_product_name, 
			            		product_description = p_product_description, 
			            		is_active = p_is_active, 
			                    modified_by = p_user_login,
			                    modified_date = NOW()
			            WHERE   product_id = p_product_id;
			            
				ELSE
		    		SET p_error_code = 'uk_prod_name_cd';
	            	SET p_error_message = NULL; 
	            	
	            
		    	END IF;       			               
			ELSE
	        
				SELECT  modified_by INTO v_user_login 
				FROM    product_header
			    WHERE   product_id = p_product_id;
			            
			    SET p_error_code = 'no_data_update_db_excep_msg';
			    SET p_error_message = CONCAT( 'Product has been updated by (',v_user_login,'). Please search for the updated product to view or make changes.');
			         
			END IF;
	
	    ELSE
	        
	        SELECT  COUNT(1) INTO v_product_name_counter
	        FROM    product_header
	        WHERE   product_name = p_product_name;
	        
	        
        	
	        IF v_product_name_counter = 0 THEN 
		        
	            INSERT INTO product_header
	            (   product_code, product_name, product_description, is_active,created_by, created_date, modified_by, modified_date
	            )  
	            VALUES
	            (   p_product_code, p_product_name, p_product_description, p_is_active, 
	                p_user_login, NOW(), p_user_login, NOW()
	            );
	            
	            SELECT LAST_INSERT_ID() INTO v_last_inserted_id;
	            SET p_last_inserted_id = v_last_inserted_id;
	
	    	ELSE
	    		SET p_error_code = 'uk_prod_name_cd';
            	SET p_error_message = NULL; 
            	
            
	    	END IF;
	    END IF; 	
	
    SELECT  modified_date INTO v_modified_date
    FROM    product_header
    WHERE   product_id = IFNULL(p_last_inserted_id,p_product_id);
        
    SELECT IFNULL(p_last_inserted_id,p_product_id) AS product_id, v_modified_date AS modified_date, p_error_code, p_error_message;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_product_search_products`
--

DROP PROCEDURE IF EXISTS `sp_product_search_products`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_product_search_products`(
IN p_product_categories VARCHAR(500),
IN p_product_code VARCHAR(25),
IN p_product_name VARCHAR(60),
IN p_product_description VARCHAR(255),
IN p_sku_code VARCHAR(25),
IN p_sku_description VARCHAR(255),
IN p_product_hierarchies_id VARCHAR(500),
IN p_product_properties TEXT,
IN p_status_code VARCHAR(25),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN

DECLARE v_string_product_categories VARCHAR(500) DEFAULT '';
DECLARE v_string_product_code VARCHAR(250) DEFAULT '';
DECLARE v_string_product_name VARCHAR(250) DEFAULT '';
DECLARE v_string_product_description VARCHAR(250) DEFAULT '';
DECLARE v_string_sku_code VARCHAR(250) DEFAULT '';
DECLARE v_string_sku_description VARCHAR(250) DEFAULT '';
DECLARE v_string_status_code VARCHAR(250) DEFAULT '';
DECLARE  v_counter INT;

  
DROP TEMPORARY TABLE IF EXISTS temp_hierarchies;
CREATE TEMPORARY TABLE temp_hierarchies
(hierarchy_id INT(10));

DROP TEMPORARY TABLE IF EXISTS temp_hierarchy_products;
CREATE TEMPORARY TABLE temp_hierarchy_products
(product_id INT(10));

DROP TEMPORARY TABLE IF EXISTS temp_categories;
CREATE TEMPORARY TABLE temp_categories
(category_id INT(10));

  IF  p_product_categories IS NOT NULL OR p_product_code IS NOT NULL OR p_product_name IS NOT NULL OR p_product_description IS NOT NULL 
      OR p_sku_code IS NOT NULL OR p_sku_description IS NOT NULL 
      OR p_product_hierarchies_id IS NOT NULL OR p_product_properties IS NOT NULL OR p_status_code IS NOT NULL THEN 
  	 
      IF p_product_code IS NOT NULL THEN
          
          SET v_string_product_code = " AND ph.product_code LIKE '";
          SET v_string_product_code = CONCAT(v_string_product_code  , p_product_code,"%'");
      END IF;
      
      IF p_product_name IS NOT NULL THEN
          
          SET v_string_product_name = " AND ph.product_name LIKE '";
          SET v_string_product_name = CONCAT(v_string_product_name, p_product_name,"%'");
      END IF;
	  
      IF p_status_code IS NOT NULL THEN
          
          SET v_string_status_code = " AND ph.status_code = '";
          SET v_string_status_code = CONCAT(v_string_status_code, p_status_code,"'");
      END IF;
      
      IF p_product_description IS NOT NULL THEN
          
          SET v_string_product_description = " AND ph.product_description LIKE '";
          SET v_string_product_description = CONCAT(v_string_product_description, p_product_description,"%'");
      END IF;
      
      IF p_sku_code IS NOT NULL THEN
          
          SET v_string_sku_code = " AND psh.sku_code LIKE '";
          SET v_string_sku_code = CONCAT(v_string_sku_code, p_sku_code,"%'");
      END IF;
      
      IF p_sku_description IS NOT NULL THEN
          
          SET v_string_sku_description = " AND psh.sku_description LIKE '";
          SET v_string_sku_description = CONCAT(v_string_sku_description, p_sku_description,"%'");
      END IF;
      
      IF p_product_hierarchies_id IS NOT NULL THEN
      
          CALL sp_common_parsing_logic(p_product_hierarchies_id);
          INSERT INTO temp_hierarchies
          (hierarchy_id)
          SELECT parse_code FROM temp_parse_data;
          
          	INSERT INTO temp_hierarchy_products          
          	SELECT 	pshdm.product_id  
		 	FROM 	product_hierarchy_mapping phm,
		         	product_sku_header_detail_mapping pshdm,
		         	temp_hierarchies th
			WHERE 	th.hierarchy_id = phm.product_hierarchy_id
		  	AND     pshdm.table_name = 'product_hierarchy_mapping'
		  	AND     pshdm.details_id = phm.product_hierarchy_mapping_id
		  	AND 	pshdm.product_sku_id IS NULL;
  	
      END IF;
      
      IF p_product_categories IS NOT NULL THEN
      
          CALL sp_common_parsing_logic(p_product_categories);
          INSERT INTO temp_categories
          (category_id)
          SELECT parse_code FROM temp_parse_data;
          
          SELECT 	COUNT(1) INTO v_counter 
	      FROM 		temp_categories;
	      
	      INSERT INTO temp_hierarchies
	      (			hierarchy_id
	      )                  
	      SELECT 	phcm.product_hierarchy_id 
	      FROM 		product_hierarchy_category_mapping phcm, 
	      			temp_categories tc,
	      			product_category_master pcm
	      WHERE 	phcm.product_category_id = tc.category_id
	      AND 		pcm.product_category_id = phcm.product_category_id 
		  GROUP BY 	phcm.product_hierarchy_id
		  HAVING 	COUNT(pcm.product_category_id) = v_counter;
		  
		  INSERT INTO temp_hierarchy_products          
          SELECT 	pshdm.product_id  
		  FROM 		product_hierarchy_mapping phm,
		         	product_sku_header_detail_mapping pshdm,
		         	temp_hierarchies th
		  WHERE 	th.hierarchy_id = phm.product_hierarchy_id
		  AND     pshdm.table_name = 'product_hierarchy_mapping'
		  AND     pshdm.details_id = phm.product_hierarchy_mapping_id
		  AND 	pshdm.product_sku_id IS NULL;
      END IF;
            	  
      IF (p_product_hierarchies_id IS NOT NULL OR p_product_categories IS NOT NULL) AND (p_sku_code IS NOT NULL OR p_sku_description IS NOT NULL)      
      AND p_product_code IS NOT NULL OR p_product_name IS NOT NULL OR p_product_description IS NOT NULL THEN
      	
      	SET @v_sql_statement = CONCAT(
        " SELECT  DISTINCT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code, ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				  fn_product_get_product_sku_image(ph.product_id,psh.product_sku_id) AS image_url   
          FROM    product_header ph,
				  temp_product_search_product_ids tpspi,       
				  product_sku_header psh,
				  temp_hierarchy_products thm
                  -- product_hierarchy_mapping phm,
                  -- temp_hierarchies th
          WHERE   -- ph.product_id = phm.product_id AND	  
				  ph.product_id = psh.product_id
		  AND     ph.product_id = tpspi.product_id
		  AND     thm.product_id = ph.product_id 
          -- AND     th.hierarchy_id = phm.product_hierarchy_id  
		  ", v_string_sku_code, v_string_sku_description, 
          v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
      
      	  PREPARE stmnt3 FROM  @v_sql_statement;
          EXECUTE stmnt3;                    
          
      ELSEIF (p_product_hierarchies_id IS NOT NULL OR p_product_categories IS NOT NULL) AND (p_sku_code IS NOT NULL OR p_sku_description IS NOT NULL) THEN
      	
      	SET @v_sql_statement = CONCAT(
        " SELECT  DISTINCT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code, ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				  fn_product_get_product_sku_image(ph.product_id,psh.product_sku_id) AS image_url  
          FROM    product_header ph,
				  product_sku_header psh,
				  temp_hierarchy_products thm
                  -- product_hierarchy_mapping phm,                  
                  -- temp_hierarchies th
          WHERE   -- ph.product_id = phm.product_id	  AND	  
				  ph.product_id = psh.product_id
		  AND 	  thm.product_id = ph.product_id  
          -- AND     th.hierarchy_id = phm.product_hierarchy_id  
		  ", v_string_sku_code, v_string_sku_description, 
          v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
          
          PREPARE stmnt3 FROM  @v_sql_statement;
          EXECUTE stmnt3;    
          
      ELSEIF p_product_hierarchies_id IS NOT NULL  OR p_product_categories IS NOT NULL THEN
      
        SET @v_sql_statement = CONCAT(
        " SELECT  DISTINCT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code , ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				  fn_product_get_product_sku_image(ph.product_id,psh.product_sku_id) AS image_url	 
          FROM    product_header ph
				  LEFT JOIN product_sku_header psh
				  ON (ph.product_id = psh.product_id),
				  temp_hierarchy_products thm				  
                  -- product_hierarchy_mapping phm,
                  -- temp_hierarchies th				  
          WHERE   -- ph.product_id = phm.product_id
				  ph.product_id = thm.product_id 
          -- AND     th.hierarchy_id = phm.product_hierarchy_id  
		  ", 
          v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
          
          PREPARE stmnt3 FROM  @v_sql_statement;
          EXECUTE stmnt3;                
          
      ELSE
      
      	SET @v_sql_statement = CONCAT(
        "SELECT ph.product_id, ph.product_code,ph.product_name, ph.product_description, psh.product_sku_id, psh.sku_code, psh.sku_description, ph.status_code, ph.is_active, fn_core_get_status_name(ph.status_code) AS status_name,
				psh.default_thumbnail_image_url AS thumbnail_image_url, psh.default_zoom_image_url AS zoom_image_url 
        FROM   	product_header ph,
				product_sku_header psh
        WHERE   psh.product_id = ph.product_id
		AND		psh.default_sku = 1 ", v_string_product_code, v_string_product_name, v_string_product_description, v_string_status_code);
          
        PREPARE stmnt3 FROM  @v_sql_statement;
        EXECUTE stmnt3;
        
      END IF;         
      
  ELSE
  
      SET p_error_code = 'param_null';
      SET p_error_message = 'The parameters entered are null';            
      SELECT p_error_code, p_error_message;
                         
  END IF;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_retail_generate_new_order`
--

DROP PROCEDURE IF EXISTS `sp_retail_generate_new_order`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_retail_generate_new_order`(
IN p_user_login VARCHAR(100),
IN p_total_quantity INT,  
IN p_total_amount FLOAT,
IN p_lead_time INT,  
IN p_purchased_delivery_charges FLOAT,
IN p_customer_billing_fname VARCHAR(45),  
IN p_customer_billing_sname VARCHAR(45),  
IN p_customer_billing_address_line1 VARCHAR(100),  
IN p_customer_billing_address_line2 VARCHAR(100),  
IN p_customer_billing_address_line3 VARCHAR(100),  
IN p_customer_billing_address_city VARCHAR(45),  
IN p_customer_billing_address_zip_code VARCHAR(25),  
IN p_customer_billing_address_state VARCHAR(25),  
IN p_customer_billing_address_country VARCHAR(25),  
IN p_customer_shipping_fname VARCHAR(45),  
IN p_customer_shipping_sname VARCHAR(45),  
IN p_customer_shipping_address_line1 VARCHAR(100),  
IN p_customer_shipping_address_line2 VARCHAR(100),  
IN p_customer_shipping_address_line3 VARCHAR(100),  
IN p_customer_shipping_address_city VARCHAR(25),  
IN p_customer_shipping_address_zip_code VARCHAR(25),  
IN p_customer_shipping_address_state VARCHAR(25),  
IN p_customer_shipping_address_country VARCHAR(25),  
IN p_customer_billing_primary_phone_number VARCHAR(45),  
IN p_customer_billing_alternate_phone_number VARCHAR(45),  
IN p_customer_shipping_primary_phone_number VARCHAR(45),
IN p_customer_shipping_alternate_phone_number VARCHAR(45),
IN p_customer_billing_email_id VARCHAR(255),
IN p_customer_shipping_email_id VARCHAR(255),
IN p_order_details_string VARCHAR(4000),
IN p_promo_code VARCHAR(45),
IN p_voucher_discount FLOAT,  
IN p_voucher_id INT,
IN p_gift_wrapping_required TINYINT(1),
IN p_domain_name VARCHAR(45),
IN p_currency_conversion_rate FLOAT,
IN p_converted_currency_symbol VARCHAR(45),
IN p_original_total_amount FLOAT,
IN p_currency_symbol_flag BOOLEAN,
IN p_original_purchased_delivery_charges FLOAT
)
BEGIN
	
	
	DECLARE v_login_name VARCHAR(50);
	DECLARE v_lead_time INT;
	DECLARE v_order_header_id INT;
	DECLARE v_order_number VARCHAR(45);
	DECLARE v_tmp_counter INT;
	DECLARE v_order_date DATE;
    DECLARE v_delivery_date DATE;
	
	
	
	DECLARE v_current_node                  TEXT;
	DECLARE v_current_index                 INT(4) DEFAULT 1;
	DECLARE v_previous_index                INT(4) DEFAULT 1;
	DECLARE v_start_point                   INT(4);
	DECLARE v_end_point                     INT(4);
	DECLARE v_inner_current_node            TEXT;
	DECLARE v_inner_current_index           INT(4) DEFAULT 1;
	DECLARE v_inner_previous_index          INT(4) DEFAULT 1;
	DECLARE v_inner_start_point             INT(4);
	DECLARE v_inner_end_point               INT(4);
	DECLARE v_inner_current_node1           TEXT;
	DECLARE v_inner_current_index1          INT(4) DEFAULT 1;
	DECLARE v_inner_previous_index1         INT(4) DEFAULT 1;
	DECLARE v_inner_start_point1            INT(4);
	DECLARE v_inner_end_point1              INT(4);
	DECLARE	v_row_id                        INT(4);
	DECLARE v_property_row_VALUES           TEXT;
	DECLARE v_column_sequence_number        INT(10);
	
	DECLARE v_product_id INT(10);
	DECLARE v_product_sku_id INT(10);
	DECLARE v_base_price FLOAT;
	DECLARE v_discount_price FLOAT;
	DECLARE v_quantity INT;
	DECLARE v_sub_total FLOAT;
	DECLARE v_comments TEXT;
	DECLARE v_original_base_price FLOAT;
	DECLARE v_original_discount_price FLOAT;
	DECLARE v_original_sub_total FLOAT;
	DECLARE v_price_per_piece FLOAT;
	DECLARE v_original_price_per_piece FLOAT;
	DECLARE v_order_details_id INT;
	DECLARE v_current_stock_product_sku_id INT;
	
	
    DROP TEMPORARY TABLE IF EXISTS product_stock_levels_tmp;
	CREATE TEMPORARY TABLE IF NOT EXISTS product_stock_levels_tmp  
	(	product_id INT(10),
		product_sku_id INT(10),
		product_code VARCHAR(250),
		product_name VARCHAR(100),
		available_quantity INT(4),
		blocked_quantity INT(4),
		reorder_level INT(4)
	);
	
	
	 IF p_user_login IS NULL THEN
	    SET v_login_name = 'guest';
	ELSE
	    SET v_login_name = p_user_login;    
	END IF;
	
	SET v_lead_time = IFNULL(p_lead_time,0);
	
	INSERT INTO order_header (user_id, user_login, order_no, total_quantity, lead_time, total_amount, original_total_amount, express_delivery_charge,
	original_express_delivery_charge, currency_conversion_rate, currency_code, currency_symbol_flag, payment_status, order_status, billing_first_name, 
	billing_last_name, billing_email_address_1, billing_email_address_2, billing_mobile_1, billing_mobile_2, billing_address_line_1, billing_address_line_2, billing_address_line_3,
	billing_city, billing_zip_code, billing_state, billing_country, order_tracking_number, order_date, delivery_date, courier_id, voucher_code,
	discount_value, gift_wrapping_required, email_regenerated_by, created_by, created_date, modified_by, modified_date) 
	VALUES ((SELECT user_id from core_user_master where user_login = v_login_name) , v_login_name, UUID() ,p_total_quantity, v_lead_time, p_total_amount ,p_original_total_amount, 
	IFNULL(p_purchased_delivery_charges,0) ,IFNULL(p_original_purchased_delivery_charges,0) ,
	p_currency_conversion_rate ,p_converted_currency_symbol ,p_currency_symbol_flag , (SELECT parameter_id from core_parameter_master where param_code='PAYMENT_STATUS' and sequence_number=1), 
	(SELECT parameter_id from core_parameter_master where param_code='ORDER_STATUS' and sequence_number=1), p_customer_billing_fname , p_customer_billing_sname, 
	p_customer_billing_email_id, NULL, p_customer_billing_primary_phone_number, p_customer_billing_alternate_phone_number, p_customer_billing_address_line1, p_customer_billing_address_line2,
	p_customer_shipping_address_line3, p_customer_billing_address_city, p_customer_billing_address_zip_code, p_customer_billing_address_state, p_customer_billing_address_country, NULL, 
	NOW(), DATE_ADD(NOW(), INTERVAL v_lead_time day), NULL, p_promo_code, p_voucher_discount, p_gift_wrapping_required, NULL, p_user_login, NOW(), p_user_login, NOW());
	
	
	SELECT last_insert_id() INTO v_order_header_id;
	
	SET v_order_number = CONCAT('PORTAL-',v_order_header_id);
	
	UPDATE order_header SET order_no = v_order_number WHERE order_header_id = v_order_header_id;
	
	SELECT order_date, delivery_date 
	INTO v_order_date, v_delivery_date
	FROM order_header
	WHERE order_header_id = v_order_header_id;
	
	
	IF p_order_details_string IS NOT NULL THEN     
                        
		outer_parse: WHILE v_current_index > 0
		DO
		  
			SET v_previous_index = v_current_index;
			SELECT LOCATE(';', p_order_details_string, v_current_index) INTO v_current_index;
			IF v_current_index > 0
			THEN
				SET v_start_point = v_previous_index;
				SET v_end_point = v_current_index - v_start_point;
				SET v_current_index = v_current_index + 1;	
			ELSE
				SET v_start_point = v_previous_index;
				SET v_end_point = LENGTH(p_order_details_string);
			END IF;
	
			SET v_current_node = SUBSTR(p_order_details_string,v_start_point,v_end_point);
	    SET v_row_id = v_current_index ;
	    SET  v_property_row_VALUES = v_current_node;
	     
	        inner_parse: WHILE v_inner_current_index > 0
	        DO
	          SET v_inner_previous_index = v_inner_current_index;
	          SELECT LOCATE(';', v_current_node, v_inner_current_index) INTO v_inner_current_index;
	
	          IF v_inner_current_index > 0
	          THEN
	            SET v_inner_start_point = v_inner_previous_index;
	            SET v_inner_end_point = v_inner_current_index - v_inner_start_point;
	            SET v_inner_current_index = v_inner_current_index + 1;
	          ELSE
	            SET v_inner_start_point = v_inner_previous_index;
	            SET v_inner_end_point = LENGTH(v_current_node);
	          END IF;
	      
	          SET v_inner_current_node = SUBSTR(v_current_node,v_inner_start_point,v_inner_end_point);
	          
	            SET  v_property_row_VALUES = v_inner_current_node;
	          IF v_inner_current_node = '' OR v_inner_current_node = 'null' THEN
	            SET v_inner_current_node = NULL;
	          END IF;
	          
	          SET v_column_sequence_number = 1;
	          
	            inner_parse1: WHILE v_inner_current_index1> 0
	                    DO
	                      SET v_inner_previous_index1 = v_inner_current_index1;
	                      SELECT LOCATE('~', v_inner_current_node, v_inner_current_index1) INTO v_inner_current_index1;
	                      IF v_inner_current_index1 > 0
	                      
	                      THEN
	                        SET v_inner_start_point1 = v_inner_previous_index1;
	                        SET v_inner_end_point1 = v_inner_current_index1 - v_inner_start_point1;
	                        SET v_inner_current_index1 = v_inner_current_index1 + 1;
	                      ELSE
	                        SET v_inner_start_point1 = v_inner_previous_index1;
	                        SET v_inner_end_point1 = LENGTH(v_inner_current_node);
	                      END IF;
	                    
	                      SET v_inner_current_node1 = SUBSTR(v_inner_current_node,v_inner_start_point1,v_inner_end_point1);
	                      IF v_inner_current_node1 = '' OR v_inner_current_node1 = 'null' THEN
	                        SET v_inner_current_node1 = NULL;
	                      END IF;
	                      
	                       		  IF v_column_sequence_number = 1 THEN
		                         	 SET v_product_id = v_inner_current_node1;	  
		                          ELSEIF v_column_sequence_number = 2 THEN 
		                          	 SET v_product_sku_id = v_inner_current_node1;
		                          ELSEIF v_column_sequence_number = 3 THEN 
		                          	 SET v_base_price = v_inner_current_node1;	 
							      ELSEIF v_column_sequence_number = 4 THEN 
							         SET v_discount_price = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 5 THEN
							         SET v_quantity = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 6 THEN
							         SET v_sub_total = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 7 THEN
							         SET v_comments = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 8 THEN
							         SET v_original_base_price = v_inner_current_node1;
							      ELSEIF v_column_sequence_number = 9 THEN
							         SET v_original_discount_price = v_inner_current_node1;	 
							      ELSEIF v_column_sequence_number = 10 THEN
							         SET v_original_sub_total = v_inner_current_node1;
							      END IF;   
							      
							      SET v_column_sequence_number = v_column_sequence_number +1;   
							      END WHILE inner_parse1;
							                       
							      SET v_inner_current_index1 = 1;
							      
							      	IF v_discount_price IS NOT NULL THEN
							      	
							      		SET v_price_per_piece = v_discount_price;
							      	ELSE
							      	
							      		SET v_price_per_piece = v_base_price;
							      		
							      	END IF;
							      	
							      	IF v_original_discount_price IS NOT NULL THEN
							      	
							      		SET v_original_price_per_piece = v_original_discount_price;
							      	ELSE
							      	
							      		SET v_original_price_per_piece = v_original_base_price;
							      		
							      	END IF;
							      
							      
							      	INSERT INTO order_detail
							      	(order_header_id, product_id, product_sku_id, price_per_piece, original_price_per_piece, total_price, original_total_price, quantity)
									VALUES(v_order_header_id, v_product_id, v_product_sku_id, v_price_per_piece, v_original_price_per_piece, v_sub_total, v_original_sub_total, v_quantity);
									
									SELECT last_insert_id() INTO v_order_details_id;
									
									
									INSERT INTO order_shipping_mapping(order_header_id, order_detail_id, shipping_first_name, shipping_last_name, shipping_email_address_1, 
									shipping_mobile_1, shipping_mobile_2, shipping_address_line_1, shipping_address_line_2, shipping_address_line_3, shipping_city, shipping_zip_code, 
									shipping_state, shipping_country, created_by, created_date, modified_by, modified_date)
									VALUES(v_order_header_id, v_order_details_id, p_customer_shipping_fname, p_customer_shipping_sname, p_customer_shipping_email_id, p_customer_shipping_primary_phone_number,
									p_customer_shipping_alternate_phone_number, p_customer_shipping_address_line1, p_customer_shipping_address_line2, p_customer_shipping_address_line3, p_customer_shipping_address_city,
									p_customer_shipping_address_zip_code, p_customer_shipping_address_state, p_customer_shipping_address_country, p_user_login, NOW(), p_user_login, NOW());
									
									
									UPDATE 	 product_sku_stock_level
									SET		 available_quantity = IFNULL(available_quantity,0) - v_quantity,
              								 blocked_quantity = IFNULL(blocked_quantity,0) + v_quantity
									WHERE    product_id = v_product_id
									AND      product_sku_id = v_product_sku_id;
									
									 SELECT product_sku_id
						             INTO v_current_stock_product_sku_id
						             FROM product_stock_levels_tmp
						             WHERE product_id = v_product_id and product_sku_id = v_product_sku_id;
						             
									
									 IF v_current_stock_product_sku_id IS NOT NULL THEN
									 
						              UPDATE product_stock_levels_tmp a, product_sku_stock_level b
						              SET a.available_quantity = b.available_quantity,
						              a.blocked_quantity = b.blocked_quantity,
						              
						              a.reorder_level = b.reorder_level
						              WHERE a.product_sku_id = b.product_sku_id
						              AND a.product_id = b.product_id
						              AND b.product_id = v_product_id
						              AND b.product_sku_id = v_product_sku_id;
						              
						             ELSE
						             
						              INSERT INTO product_stock_levels_tmp(product_id, product_sku_id, product_code, product_name, available_quantity,blocked_quantity,reorder_level)
						              SELECT v_product_id, v_product_sku_id, psh.sku_code, psh.sku_name, cpsl.available_quantity, cpsl.blocked_quantity, cpsl.reorder_level
						              FROM product_sku_stock_level cpsl, product_header ph, product_sku_header psh
						              WHERE cpsl.product_id = v_product_id
						              AND cpsl.product_sku_id = v_product_sku_id
						              AND cpsl.product_id = ph.product_id
						              AND cpsl.product_id = psh.product_id;
						              
						             END IF;  
									
							      
							      
							    SET v_product_id = NULL;
							    SET v_product_sku_id = NULL;
							    SET v_base_price = NULL;
		                        SET v_discount_price = NULL;
								SET v_quantity = NULL;
								SET v_sub_total = NULL;
								SET v_comments = NULL;
								SET v_original_base_price = NULL;  
								SET v_original_discount_price = NULL;
								SET v_original_sub_total = NULL;
															                                                      
	                END WHILE inner_parse;
	                      
	        SET v_inner_current_index = 1;              
	                                          
	      END WHILE outer_parse;
      
	END IF;
	
   SELECT COUNT(*)
   INTO  v_tmp_counter
   FROM product_stock_levels_tmp;
   
   
   IF  v_tmp_counter IS NULL OR  v_tmp_counter = 0 THEN
   select v_order_header_id , v_order_number, v_order_date, v_delivery_date;
   ELSE
   select v_order_header_id , v_order_number, v_order_date, v_delivery_date,
    product_id, product_sku_id, product_code, product_name, available_quantity, blocked_quantity, reorder_level
   FROM product_stock_levels_tmp;
   END IF;
	
   
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_retail_register_user`
--

DROP PROCEDURE IF EXISTS `sp_retail_register_user`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_retail_register_user`(
IN p_login_name VARCHAR(100),
IN p_first_name VARCHAR(50),
IN p_last_name VARCHAR(50),
IN p_primary_email_id VARCHAR(100),
IN p_conditions_accepted TINYINT(1),
IN p_password VARCHAR(50),
IN p_primary_phone_no VARCHAR(50),
OUT p_error_code VARCHAR(25),
OUT p_error_message VARCHAR(255)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
	DECLARE v_success_flag VARCHAR(1) ;
	DECLARE v_error_message VARCHAR(100);
	DECLARE v_row_count INT(4) DEFAULT 0;
	DECLARE v_parameter_yes_id INT;
	DECLARE v_user_id INT;
  	
	INSERT INTO core_user_master(
		user_login,
		login_password,
		first_name,
		last_name,
		primary_email_id,
		primary_phone_number,
		permanent_address_country,
		condition_accepted,
		newsletter_subscription,
		sms_alert_subscription,
		is_admin,
		is_active,
		created_by,
		created_date,
		modified_by,
		modified_date)
	VALUES(
		p_login_name,
		MD5(p_password),
		p_first_name,
		p_last_name,
		p_primary_email_id,
		p_primary_phone_no,
		NULL,
		p_conditions_accepted,
		0,
		0,
		0,
		1,
		'system',
		NOW(),
		'system',
		NOW());		
	
	SELECT last_insert_id() INTO v_user_id;
    
    IF v_user_id IS NOT NULL THEN
	 INSERT INTO core_users_roles_mapping (
	  users_roles_mapping_id,
	  user_id,
	  role_id,
	  created_by,
	  created_date,
	  modified_by,
	  modified_date) VALUES(
	  NULL,
	  v_user_id,
	  (SELECT role_id from core_role_master where role_code = 'RETAIL_USER'),
	  p_login_name,
	  NOW(),
	  p_login_name,
	  NOW()
	  );
    END IF;
	
	SELECT ROW_COUNT() INTO v_row_count;
	
	IF v_user_id IS NOT NULL AND v_row_count != 0 THEN
		SET p_error_code = NULL;
	ELSE
		SET p_error_code = 'User Registration Failed at insert';
		SET p_error_message = 'Not able to create user';
	END IF;	
	
	select v_user_id, p_error_code, p_error_message;	

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_retail_register_user_details`
--

DROP PROCEDURE IF EXISTS `sp_retail_register_user_details`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_retail_register_user_details`(
IN p_login_name VARCHAR(100),
IN p_password VARCHAR(50),
IN p_billing_first_name VARCHAR(50),
IN p_billing_last_name VARCHAR(50),
IN p_primary_email_id VARCHAR(100),
IN p_billing_phone VARCHAR(15),
IN p_permanent_address_line1 VARCHAR(100),
IN p_permanent_address_line2 VARCHAR(100),
IN p_permanent_address_city VARCHAR(50),
IN p_permanent_address_zip_code VARCHAR(15),
IN p_permanent_address_country VARCHAR(25),
IN p_permanent_address_state VARCHAR(25),
IN p_shipping_address_line1 VARCHAR(100),
IN p_shipping_address_line2 VARCHAR(100),
IN p_shipping_address_city VARCHAR(50),
IN p_shipping_address_zip_code VARCHAR(15),
IN p_shipping_address_country VARCHAR(25),
IN p_conditions_accepted TINYINT(1),
IN P_shipping_address_state VARCHAR(25),
OUT p_error_code VARCHAR(25),
OUT p_error_message VARCHAR(255)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
	DECLARE v_success_flag VARCHAR(1) ;
	DECLARE v_error_message VARCHAR(100);
	DECLARE v_row_count INT(4) DEFAULT 0;
	DECLARE v_parameter_yes_id INT;
	DECLARE v_user_id INT;
  	
	INSERT INTO core_user_master(
		user_login,
		login_password,
		first_name,
		last_name,
		primary_email_id,
		primary_phone_number,
		permanent_address_country,
		condition_accepted,
		newsletter_subscription,
		sms_alert_subscription,
		is_admin,
		is_active,
		created_by,
		created_date,
		modified_by,
		modified_date,
		permanent_address_line1,
		permanent_address_line2,
		permanent_address_city,
		permanent_address_zip_code,
		permanent_address_state,
		shipping_address_line1,
		shipping_address_line2,
		shipping_address_city,
		shipping_address_zip_code,
		shipping_address_state,
		shipping_address_country)
	VALUES(
		p_login_name,
		MD5(p_password),
		p_billing_first_name,
		p_billing_last_name,
		p_primary_email_id,
		p_billing_phone,
		p_permanent_address_country,
		p_conditions_accepted,
		0,
		0,
		0,
		1,
		'system',
		NOW(),
		'system',
		NOW(),
		p_permanent_address_line1,
		p_permanent_address_line2,
		p_permanent_address_city,
		p_permanent_address_zip_code,
		p_permanent_address_state,
		p_shipping_address_line1,
		p_shipping_address_line2,
		p_shipping_address_city,
		p_shipping_address_zip_code,
		P_shipping_address_state,
		p_shipping_address_country);		
	
	SELECT last_insert_id() INTO v_user_id;
    
    IF v_user_id IS NOT NULL THEN
	 INSERT INTO core_users_roles_mapping (
	  users_roles_mapping_id,
	  user_id,
	  role_id,
	  created_by,
	  created_date,
	  modified_by,
	  modified_date) VALUES(
	  NULL,
	  v_user_id,
	  (SELECT role_id from core_role_master where role_code = 'RETAIL_USER'),
	  p_login_name,
	  NOW(),
	  p_login_name,
	  NOW()
	  );
    END IF;
	
	SELECT ROW_COUNT() INTO v_row_count;
	
	IF v_user_id IS NOT NULL AND v_row_count != 0 THEN
		SET p_error_code = NULL;
	ELSE
		SET p_error_code = 'User Registration Failed at insert';
		SET p_error_message = 'Not able to create user';
	END IF;	
	
	select v_user_id, p_error_code, p_error_message;	

END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_retail_subscribe_to_news_letter`
--

DROP PROCEDURE IF EXISTS `sp_retail_subscribe_to_news_letter`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_retail_subscribe_to_news_letter`(
IN p_email_id VARCHAR(25),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(25),
OUT p_error_message VARCHAR(255)
)
BEGIN
	
	DECLARE v_counter INT;
	
	SELECT COUNT(1) INTO v_counter
	FROM core_newsletter_subscription
	WHERE email_address = p_email_id;
	
	IF v_counter > 0 THEN
	
		SET p_error_code = "user_already_exist";
		SET p_error_message = CONCAT("email address ", p_email_id, " already subscribe for news letter");
	
	END IF;
	
	IF p_error_code IS NULL THEN
	
		INSERT INTO core_newsletter_subscription(email_address, user_login, created_by, created_date, modified_by, modified_date) 
		VALUES(p_email_id,p_user_login,'system',NOW(),'system',NOW());
	
	END IF;

	SELECT p_error_code, p_error_message;
	
	
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_save_edit_category`
--

DROP PROCEDURE IF EXISTS `sp_save_edit_category`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_save_edit_category`(
IN p_product_category_id INT(10), 
IN p_product_category_code VARCHAR(25),
IN p_product_category_name VARCHAR(60),
IN p_product_category_description VARCHAR(100),
IN p_seo_title VARCHAR(100),
IN p_seo_keyword VARCHAR(100),
IN p_seo_description TEXT,
IN p_image_url VARCHAR(4000),
IN p_is_active TINYINT(1),
IN p_user_login VARCHAR(50),
OUT p_error_code VARCHAR(50),
OUT p_error_message VARCHAR(500)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN

	DECLARE v_last_inserted_id INT(10);

	IF p_product_category_id IS NOT NULL THEN
		UPDATE	category_master
		SET		category_code = p_product_category_code,
				category_name = p_product_category_name,
				category_description = p_product_category_description,
				seo_title = p_seo_title,
				seo_keyword = p_seo_keyword,
				seo_description = p_seo_description,
				image_url = p_image_url,
				is_active = p_is_active,
				modified_by = p_user_login,
				modified_date = NOW()
		WHERE	category_id = p_product_category_id;

		SET v_last_inserted_id = p_product_category_id;

	ELSE

		INSERT INTO category_master(category_code, category_name, category_description, seo_title, seo_keyword, seo_description, image_url,
									is_active, created_by, modified_by, created_date, modified_date)
							 VALUES(p_product_category_code, p_product_category_name, p_product_category_description, p_seo_title,
							 		p_seo_keyword, p_seo_description, p_image_url, p_user_login, NOW(), p_user_login, NOW());
		
		SELECT LAST_INSERT_ID() INTO v_last_inserted_id;

	END IF;

	SELECT v_last_inserted_id AS category_id;
END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_search_products_on_category`
--

DROP PROCEDURE IF EXISTS `sp_search_products_on_category`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_search_products_on_category`(
 IN p_hierarchy_name VARCHAR(500),
 IN p_level_1_category_code VARCHAR(500),
 IN p_level_2_category_code VARCHAR(500),
 IN p_level_3_category_code VARCHAR(500),
 IN p_level_4_category_code VARCHAR(500),
 IN p_user_login VARCHAR(500),
 IN p_website_id INT(10)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
DECLARE v_hierarchy_id INT(10);
DECLARE v_level_1_category_id INT(10);
DECLARE v_level_2_category_id INT(10);
DECLARE v_level_3_category_id INT(10);
DECLARE v_level_4_category_id INT(10);

SET v_hierarchy_id = fn_get_hierarchy_id_based_on_name(p_hierarchy_name);

IF p_level_4_category_code IS NOT NULL THEN

	SET v_level_4_category_id = fn_get_category_id_based_on_code(p_level_4_category_code);

END IF;

IF p_level_3_category_code IS NOT NULL THEN

	SET v_level_3_category_id = fn_get_category_id_based_on_code(p_level_3_category_code);

END IF;

IF p_level_2_category_code IS NOT NULL THEN

	SET v_level_2_category_id = fn_get_category_id_based_on_code(p_level_2_category_code);

END IF;

IF p_level_1_category_code IS NOT NULL THEN

	SET v_level_1_category_id = fn_get_category_id_based_on_code(p_level_1_category_code);

END IF;

IF p_level_4_category_code IS NOT NULL THEN
	
	SELECT v_level_4_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name', fn_get_category_name_based_on_id(phcm.category_level_2) 'category_level_2_name',
			fn_get_category_name_based_on_id(phcm.category_level_3) 'category_level_3_name', fn_get_category_name_based_on_id(phcm.category_level_4) 'category_level_4_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_4 = v_level_4_category_id
	AND		phcm.category_level_3 = v_level_3_category_id
	AND		phcm.category_level_2 = v_level_2_category_id
	AND		phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;

	
ELSEIF p_level_3_category_code IS NOT NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_3_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name', fn_get_category_name_based_on_id(phcm.category_level_2) 'category_level_2_name',
			fn_get_category_name_based_on_id(phcm.category_level_3) 'category_level_3_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_3 = v_level_3_category_id
	AND		phcm.category_level_2 = v_level_2_category_id
	AND		phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;
	

ELSEIF p_level_2_category_code IS NOT NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_2_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name', fn_get_category_name_based_on_id(phcm.category_level_2) 'category_level_2_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_2 = v_level_2_category_id
	AND		phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;
	
	
ELSEIF p_level_1_category_code IS NOT NULL AND p_level_2_category_code IS NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_1_category_id;

	SELECT  psh.sku_code, psh.sku_name, psh.sku_description, psh.product_sku_id, psh.product_id, psh.sku_property_text, psh.default_thumbnail_image_url, psh.default_zoom_image_url, ph.product_id,
			psh.base_price, psh.discount_amount, psh.discount_percent, psh.final_base_price, psh.is_active, phcm.hierarchy_id, phcm.category_level_1, phcm.category_level_2, phcm.category_level_3,
			phcm.category_level_4, phcm.category_level_5, fn_get_category_name_based_on_id(phcm.category_level_1) 'category_level_1_name'
	FROM	product_hierarchy_category_mapping phcm, product_header ph, product_sku_header psh, product_sku_stock_level pssl
	WHERE	phcm.category_level_1 = v_level_1_category_id
	AND		phcm.hierarchy_id = v_hierarchy_id
	AND		phcm.product_id = ph.product_id
	AND		ph.is_active = 1
	AND		ph.product_id = psh.product_id
	AND		psh.is_active = 1
	AND		pssl.product_sku_id = psh.product_sku_id
	AND		pssl.available_quantity > pssl.blocked_quantity;


END IF;





END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;

--
-- Definition of procedure `sp_search_sub_category_based_on_main_category`
--

DROP PROCEDURE IF EXISTS `sp_search_sub_category_based_on_main_category`;

DELIMITER $$

/*!50003 SET @TEMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_search_sub_category_based_on_main_category`(
 IN p_hierarchy_name VARCHAR(500),
 IN p_level_1_category_code VARCHAR(500),
 IN p_level_2_category_code VARCHAR(500),
 IN p_level_3_category_code VARCHAR(500),
 IN p_level_4_category_code VARCHAR(500)
)
    MODIFIES SQL DATA
    DETERMINISTIC
BEGIN
DECLARE v_hierarchy_id INT(10);
DECLARE v_level_1_category_id INT(10);
DECLARE v_level_2_category_id INT(10);
DECLARE v_level_3_category_id INT(10);
DECLARE v_level_4_category_id INT(10);


SET v_hierarchy_id = fn_get_hierarchy_id_based_on_name(p_hierarchy_name);

IF p_level_4_category_code IS NOT NULL THEN

	SET v_level_4_category_id = fn_get_category_id_based_on_code(p_level_4_category_code);

END IF;

IF p_level_3_category_code IS NOT NULL THEN

	SET v_level_3_category_id = fn_get_category_id_based_on_code(p_level_3_category_code);

END IF;

IF p_level_2_category_code IS NOT NULL THEN

	SET v_level_2_category_id = fn_get_category_id_based_on_code(p_level_2_category_code);

END IF;

IF p_level_1_category_code IS NOT NULL THEN

	SET v_level_1_category_id = fn_get_category_id_based_on_code(p_level_1_category_code);

END IF;

	
IF p_level_3_category_code IS NOT NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_3_category_id;

	SELECT  DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active
	FROM 	hierarchy_category_mapping hcm, category_master cm
	WHERE	hcm.hierarchy_id = v_hierarchy_id
	AND		hcm.category_level_1 = v_level_1_category_id
	AND		hcm.category_level_2 = v_level_2_category_id
	AND		hcm.category_level_3 = v_level_3_category_id
	AND		hcm.category_level_4 = cm.category_id
	AND		hcm.is_active = 1
	AND 	cm.is_active = 1;
	

ELSEIF p_level_2_category_code IS NOT NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_2_category_id;

	SELECT  DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active
	FROM 	hierarchy_category_mapping hcm, category_master cm
	WHERE	hcm.hierarchy_id = v_hierarchy_id
	AND		hcm.category_level_1 = v_level_1_category_id
	AND		hcm.category_level_2 = v_level_2_category_id
	AND		hcm.category_level_3 = cm.category_id
	AND		hcm.is_active = 1
	AND 	cm.is_active = 1;	
	
ELSEIF p_level_1_category_code IS NOT NULL AND p_level_2_category_code IS NULL AND p_level_3_category_code IS NULL AND p_level_4_category_code IS NULL THEN

	SELECT v_level_1_category_id;

	SELECT  DISTINCT cm.category_id, cm.category_code, cm.category_name, cm.category_description, cm.seo_title, cm.seo_keyword, cm.seo_description, cm.image_url, cm.is_active
	FROM 	hierarchy_category_mapping hcm, category_master cm
	WHERE	hcm.hierarchy_id = v_hierarchy_id
	AND		hcm.category_level_1 = v_level_1_category_id
	AND		hcm.category_level_2 = cm.category_id
	AND		hcm.is_active = 1
	AND 	cm.is_active = 1;	


END IF;





END $$
/*!50003 SET SESSION SQL_MODE=@TEMP_SQL_MODE */  $$

DELIMITER ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
