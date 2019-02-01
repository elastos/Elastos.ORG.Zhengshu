

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ela_blessing_star` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ela_blessing_star`;

/*Table structure for table `blessing_info` */

DROP TABLE IF EXISTS `blessing_info`;

CREATE TABLE `blessing_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(50) NOT NULL DEFAULT '' COMMENT '祝福语内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `blessing_info` */

insert  into `blessing_info`(`id`,`content`) values (1,'遇到悬崖就飞，喜欢什么就追。'),(2,'一路陪你走，这路上什么都有，唯独没有尽头。'),(3,'不是因为我真的执着，而是因为你真的值得。'),(4,'你用青春书写我的热血，我用热血换你一路星光。'),(5,'不是走运才高攀星光，是你勇往直前该被奖励。'),(6,'生而为赢、心想事成；一鼓作气，再接再厉。'),(7,'蓝天白云，晴空万里，花路有你。'),(8,'以我点寸微光，护你万丈荣光。'),(9,'春风十里，不及一路有你。'),(10,'你的名字就足以承载我所有的笑点和泪点。'),(11,'你是我的北极星，告诉我前进的方向。'),(12,'白天有你就有梦，夜晚有梦就有你。'),(13,' 我承认我很花心，你的每个样子我都很喜欢。'),(14,'没想过如影随形，但想过永生不弃。'),(15,'就算荧光棒成了拐杖，你也依旧是我的信仰。'),(16,'时间是最好的证明，证明你值得我为你守候到底。'),(17,'你若一直在，我便一直爱。'),(18,'我三分钟热度却恋你如初。'),(19,'关于你，真的不需要想起，因为从来没有忘记。'),(20,'诸事无常，唯爱不变。');

/*Table structure for table `fans_bless` */

DROP TABLE IF EXISTS `fans_bless`;

CREATE TABLE `fans_bless` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_identity` varchar(100) NOT NULL DEFAULT '' COMMENT '用户标识',
  `user_nick` varchar(20) NOT NULL DEFAULT '' COMMENT '用户昵称',
  `star_id` int(11) NOT NULL DEFAULT '0' COMMENT '明星id',
  `star_name` varchar(20) NOT NULL DEFAULT '' COMMENT '明星姓名',
  `emoticon_id` int(11) NOT NULL COMMENT '表情id',
  `blessing_content` varchar(50) NOT NULL DEFAULT '' COMMENT '祝福语',
  `txid` varchar(128) DEFAULT '' COMMENT 'txid',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `fans_bless` */

/*Table structure for table `star_info` */

DROP TABLE IF EXISTS `star_info`;

CREATE TABLE `star_info` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL,
  `blessing_count` int(11) NOT NULL DEFAULT '0',
  `utime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `star_info` */

insert  into `star_info`(`id`,`name`,`blessing_count`,`utime`) values (1,'蔡徐坤',0,'2019-01-30 14:14:34'),(2,'段奥娟',0,'2019-01-30 14:14:35'),(3,'范丞丞',0,'2019-01-30 14:14:35'),(4,'华晨宇',0,'2019-01-30 14:14:35'),(5,'鹿晗',0,'2019-01-30 14:14:35'),(6,'赖美云',0,'2019-01-30 12:45:45'),(7,'摩登兄弟',0,'2019-01-30 14:14:35'),(8,'罗云熙',0,'2019-01-30 12:45:45'),(9,'吴亦凡',0,'2019-01-30 12:45:45'),(10,'吴宣仪',0,'2019-01-30 12:45:45'),(11,'王子异',0,'2019-01-30 12:45:45'),(12,'尤长靖',0,'2019-01-30 12:45:45'),(13,'杨芸晴',0,'2019-01-30 12:45:45'),(14,'杨洋',0,'2019-01-30 12:45:45'),(15,'炎亚纶',0,'2019-01-30 12:45:45'),(16,'张云雷',0,'2019-01-30 12:45:45'),(17,'张紫宁',0,'2019-01-30 12:45:45'),(18,'朱一龙',0,'2019-01-30 12:45:45'),(19,'朱正廷',0,'2019-01-30 12:45:45');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
