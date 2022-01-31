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

/*Table structure for table `vipimformation` */

DROP TABLE IF EXISTS `vipimformation`;

CREATE TABLE `vipimformation` (
  `name` varchar(30) NOT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `phone` varchar(30) NOT NULL,
  `identification` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `postcode` varchar(30) DEFAULT NULL,
  `joinDate` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `vipimformation` */

insert  into `vipimformation`(`name`,`gender`,`phone`,`identification`,`address`,`postcode`,`joinDate`) values ('周老师','男','1234','3214312','','','2021-11-24'),('王五','男','123456','2341341523','（修改处）','','2021-11-27'),('陈翰墨','男','12389704','23089419081','中国','323143','2021-11-19'),('胡晓雨','女','170127489512','23415451434','中国','234155','2021-11-19'),('陈瑞豪','男','188124245423','330304200108213214','温州','325000','2021-11-20'),('王逸彬','男','18858718185','300304113512341','温州','325002','2021-11-20');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
