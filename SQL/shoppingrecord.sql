/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.36 : Database - shoppingvipmanagesystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shoppingvipmanagesystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shoppingvipmanagesystem`;

/*Table structure for table `shoppingrecord` */

DROP TABLE IF EXISTS `shoppingrecord`;

CREATE TABLE `shoppingrecord` (
  `id` int(11) NOT NULL,
  `vipname` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `comname` varchar(30) NOT NULL,
  `price` double NOT NULL,
  `amount` double NOT NULL,
  `totalMoney` double DEFAULT NULL,
  `date` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `shoppingrecord` */

insert  into `shoppingrecord`(`id`,`vipname`,`phone`,`comname`,`price`,`amount`,`totalMoney`,`date`) values (1,'王逸彬','18858718185','香蕉',5,5,25,'2021-11-20 03:35'),(2,'王逸彬','18858718185','牛奶',22,20,440,'2021-11-20 03:35'),(3,'王逸彬','18858718185','牛奶',22,20,440,'2021-11-20 03:35'),(77,'王逸彬','18858718185','香蕉',5,95,475,'2021-11-24 09:58'),(222,'周老师','1234','牛犊',5,1,5,'2021-11-27 02:41'),(452,'周老师','1234','猪肉',50,199,9950,'2021-11-24 10:07'),(777,'王逸彬','18858718185','牛肉',50,360,18000,'2021-11-24 10:01');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
