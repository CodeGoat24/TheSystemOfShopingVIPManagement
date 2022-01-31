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

/*Table structure for table `commonityimformation` */

DROP TABLE IF EXISTS `commonityimformation`;

CREATE TABLE `commonityimformation` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `birthPlace` varchar(30) DEFAULT NULL,
  `birthDay` varchar(30) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `introduction` varchar(100) DEFAULT NULL,
  `ps` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `commonityimformation` */

insert  into `commonityimformation`(`id`,`name`,`birthPlace`,`birthDay`,`price`,`stock`,`introduction`,`ps`) values (1,'香蕉','云南','2021.11',5,0,'好吃','热销'),(2,'猪肉','温州','2021.12',50,1,'好吃',''),(4,'鸡蛋','杭州','2021',2,200,'优质鸡蛋','（修改信息处）'),(5,'牛奶','中国西藏','2021.11',22,1959,'（修改处）',''),(6,'牛犊','美国','2021.12',5,29,'','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
