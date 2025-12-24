/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.20 : Database - rbac
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rbac` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `rbac`;

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `job_id` bigint(20) DEFAULT NULL,
  `source_id` bigint(20) DEFAULT NULL,
  `seller_id` bigint(20) DEFAULT NULL,
  `inputUser_id` bigint(20) DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`id`,`name`,`age`,`gender`,`tel`,`qq`,`job_id`,`source_id`,`seller_id`,`inputUser_id`,`input_time`,`status`) values (1,'王五',22,0,'1370000000','10086',54,5,5,1,'2018-07-01 15:41:42',0),(2,'张三',18,0,'1570000000','10087',3,33,4,1,'2018-08-03 15:17:57',1),(3,'周粥',25,0,'1770000000','10088',2,34,5,1,'2018-08-03 15:56:30',3),(4,'李四',17,1,'1880000000','10089',2,34,6,1,'2018-08-03 16:24:09',4),(5,'大飞',1,1,'1600000000','11008',2,5,5,1,'2018-09-28 10:53:52',0),(6,'逍遥',10,1,'1340000000','11009',1,33,1,1,'2018-09-28 10:53:48',2),(7,'12',22,1,'12','12',2,5,3,1,'2019-02-01 15:44:20',3);

/*Table structure for table `customertracehistory` */

DROP TABLE IF EXISTS `customertracehistory`;

CREATE TABLE `customertracehistory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trace_time` date DEFAULT NULL,
  `trace_details` varchar(255) DEFAULT NULL,
  `trace_type_id` bigint(20) DEFAULT NULL,
  `trace_result` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `input_user_id` bigint(20) DEFAULT NULL,
  `input_time` datetime DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `customertracehistory` */

insert  into `customertracehistory`(`id`,`trace_time`,`trace_details`,`trace_type_id`,`trace_result`,`remark`,`customer_id`,`input_user_id`,`input_time`,`type`) values (1,'2018-08-02','无人接听电话，联系不上',67,2,'无人接听电话，联系不上',1,1,'2018-08-03 17:27:20',0),(2,'2018-08-04','考虑中',68,2,'考虑中',4,1,'2018-08-04 15:49:18',0),(3,'2018-08-04','了解使用感受',65,3,'了解使用感受                ',4,1,'2018-08-04 18:40:00',1),(4,'2018-08-04','暂无培训想法',65,3,'暂无培训想法',3,2,'2018-08-04 18:55:08',0),(5,'2018-09-27','电话接通就挂掉，待跟进中',65,3,'电话接通就挂掉，待跟进中',6,1,'2018-09-25 13:21:12',0),(6,'2018-09-19','表示不认识，打错了',65,2,'表示不认识，打错了',6,1,'2018-09-28 13:21:00',0),(7,'2019-02-11','222222',67,3,'222',1,1,'2019-02-01 15:07:01',0),(8,'2019-02-01','2',65,3,'22',7,1,'2019-02-01 15:45:07',0),(9,'2019-05-04','xxx',65,2,'                            ',1,1,'2019-05-04 15:36:50',0);

/*Table structure for table `customertransfer` */

DROP TABLE IF EXISTS `customertransfer`;

CREATE TABLE `customertransfer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL,
  `operator_id` bigint(20) DEFAULT NULL,
  `operate_time` datetime DEFAULT NULL,
  `oldSeller_id` bigint(20) DEFAULT NULL,
  `newSeller_id` bigint(20) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table `customertransfer` */

insert  into `customertransfer`(`id`,`customer_id`,`operator_id`,`operate_time`,`oldSeller_id`,`newSeller_id`,`reason`) values (1,1,1,'2018-08-04 16:44:11',2,1,'离职资源移交'),(2,1,1,'2018-08-04 17:13:38',2,1,'休产假移交'),(3,1,1,'2018-08-04 17:19:54',2,1,'离职资源移交'),(4,1,1,'2018-08-04 17:45:38',1,1,'调岗移交'),(5,1,1,'2018-08-04 17:53:03',1,1,'离职资源移交'),(6,1,1,'2018-08-04 17:54:58',1,1,'离职资源移交'),(7,2,1,'2018-08-04 18:41:51',1,1,'工作调配移交'),(8,6,1,'2018-09-28 14:36:46',5,3,'离职资源移交'),(9,5,1,'2018-09-28 14:37:08',3,5,'工作调配移交'),(10,5,1,'2018-09-28 14:41:39',5,3,'离职资源移交'),(16,6,1,'2018-09-28 16:21:30',3,1,'工作调配移交'),(17,5,1,'2018-09-29 09:47:15',3,1,'离职资源移交'),(18,5,1,'2018-09-29 09:47:30',1,5,'调岗移交'),(19,1,1,'2019-02-01 15:06:00',3,3,''),(20,1,1,'2019-02-01 15:06:06',3,3,'1'),(21,7,1,'2019-02-01 15:45:21',5,3,'22'),(25,1,1,'2019-05-04 16:28:48',3,5,'');

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `department` */

insert  into `department`(`id`,`name`,`sn`) values (1,'总经办2','General Deparment2'),(2,'人力资源部','Human Resources Department'),(3,'采购部','Order Department'),(4,'仓储部','Warehousing Department'),(6,'技术部','Technolog Department '),(10,'666','6666');

/*Table structure for table `employee` */

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `admin` bit(1) DEFAULT NULL,
  `dept_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `employee` */

insert  into `employee`(`id`,`username`,`name`,`password`,`email`,`age`,`admin`,`dept_id`) values (27,'kunkun','猜需困','123456','867781069@qq.com',27,NULL,1),(32,'fanfan','凡凡','123456','867781069@qq.com',35,NULL,6);

/*Table structure for table `employee_role` */

DROP TABLE IF EXISTS `employee_role`;

CREATE TABLE `employee_role` (
  `employee_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `employee_role` */

insert  into `employee_role`(`employee_id`,`role_id`) values (27,13),(32,1);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `expression` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`id`,`name`,`expression`) values (1,'部门管理','kunkun:department'),(2,'员工管理','kunkun:employee'),(3,'角色管理','kunkun:role'),(4,'权限管理','kunkun:permission'),(5,'客户列表','kunkun:customer'),(6,'潜在客户','kunkun:potentialCustomer'),(7,'客户池','kunkun:customerPool'),(8,'失败客户','kunkun:failCustomer'),(9,'正式客户','kunkun:formalCustomer'),(10,'流失客户','kunkun:loseCustomer'),(11,'跟进历史管理','kunkun:toTraceHistoryPage'),(12,'移交历史管理','kunkun:toTransferPage'),(13,'页面实例图','kunkun:toReportsPage');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`sn`) values (1,'人事管理','HR_MGR'),(2,'采购管理','ORDER_MGR'),(3,'仓储管理','WAREHOUSING_MGR'),(4,'行政部管理','Admin_MGR'),(11,'市场经理','Market_Manager'),(12,'市场专员','Market'),(13,'ceo','ceo'),(14,'1231','3132');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_permission` */

insert  into `role_permission`(`role_id`,`permission_id`) values (13,1),(13,2),(13,4),(13,5),(13,3),(13,6),(13,7),(13,8),(13,9),(13,10),(13,11),(13,12),(13,13),(1,1),(1,1),(1,3),(1,4);

/*Table structure for table `systemdictionary` */

DROP TABLE IF EXISTS `systemdictionary`;

CREATE TABLE `systemdictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `sn` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `intro` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `systemdictionary` */

insert  into `systemdictionary`(`id`,`sn`,`title`,`intro`) values (1,'job','职业','客户职业'),(2,'source','来源','客户来源渠道'),(3,'intentionDegree','意向程度','客户意向，用★表示'),(4,'subject','学科','学科分类'),(5,'size','收款类型','学费收款方式'),(6,'property','办学性质','公办/民办'),(7,'importance','客户重要程度',''),(14,'foreignLangLevel','外语水平','各类考证'),(15,'clientType','客户类型',''),(16,'source','客户来源',''),(19,'education','学历','学校的办学层次'),(26,'communicationMethod','交流方式','跟踪潜在学员的方式'),(27,'tracePurpose','跟进目的','营销要达到目标'),(28,'wantedLevel','意向程度','客户意向，用★表示'),(31,'score','评分','客户跟踪评审分数'),(32,'113','113','113');

/*Table structure for table `systemdictionaryitem` */

DROP TABLE IF EXISTS `systemdictionaryitem`;

CREATE TABLE `systemdictionaryitem` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NOT NULL,
  `title` varchar(255) NOT NULL,
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

/*Data for the table `systemdictionaryitem` */

insert  into `systemdictionaryitem`(`id`,`parent_id`,`title`,`sequence`) values (1,1,'老师',2),(2,1,'司机',1),(3,1,'老板',1),(4,2,'自身途径',1),(5,2,'营销广告',4),(6,2,'老学员推荐',1),(7,3,'★',1),(9,3,'★★',1),(13,4,'java',1),(14,4,'ios',1),(15,4,'c#',1),(16,5,'信用卡',1),(17,5,'贷款',1),(18,5,'银行卡',1),(19,5,'支付宝',1),(20,6,'公办',1),(21,6,'民办',1),(22,6,'独立院校',1),(23,6,'其他',1),(28,14,'CET4',1),(29,14,'CET6',1),(30,14,'专八',1),(31,15,'线上',1),(32,15,'线下',1),(33,16,'QQ',1),(34,16,'微信',1),(35,17,'广州校区',1),(36,17,'西安校区',1),(37,17,'上海校区',1),(38,17,'北京校区',1),(39,17,'深圳校区',1),(40,7,'★',1),(41,7,'★★',1),(42,7,'★★★',1),(43,7,'★★★★',1),(44,7,'★★★★★',1),(45,7,'★★★★★★',1),(46,7,'★★★★★★★',1),(47,3,'★★★',1),(48,3,'★★★★',1),(49,3,'★★★★★',1),(50,19,'高中',1),(51,19,'大专',1),(52,19,'本科',1),(53,19,'研究生',1),(54,1,'学生',1),(55,20,'高中',1),(56,20,'大专',1),(57,20,'本科',1),(58,20,'研究生',1),(59,24,'正常',1),(60,24,'正式学员',1),(61,25,'java学院',1),(62,25,'IOS学院',1),(63,25,'UI学院',1),(64,25,'Python学院',1),(65,26,'营销QQ',1),(66,26,'来电咨询',1),(67,26,'去电跟踪',1),(68,26,'短信',1),(69,27,'潜在客户跟进',1),(70,27,'常规跟进',1),(71,27,'正式客户跟进',1),(72,27,'其他',1),(73,28,'★',1),(74,28,'★★',1),(75,28,'★★★',1),(76,29,'高中',1),(77,29,'大专',1),(78,29,'本科',1),(79,31,'差',1),(80,31,'良',1),(81,31,'优',1),(82,1,'秘书',0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
