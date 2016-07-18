-- MySQL dump 10.13  Distrib 5.6.24, for Win32 (x86)
--
-- Host: localhost    Database: kuremusicdb
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `music_information`
--

DROP TABLE IF EXISTS `music_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `music_information` (
  `MUSIC_ID` int(11) NOT NULL,
  `MUSIC_name` char(64) NOT NULL,
  `MUSIC_url` char(128) NOT NULL,
  `MUSIC_value` int(11) NOT NULL,
  `MUSIC_lyric_url` char(128) DEFAULT NULL,
  PRIMARY KEY (`MUSIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `music_information`
--

LOCK TABLES `music_information` WRITE;
/*!40000 ALTER TABLE `music_information` DISABLE KEYS */;
INSERT INTO `music_information` VALUES (1,'Alex、夏天 - 多少次我告诫自己.mp3','resource/music/Alex、夏天 - 多少次我告诫自己.mp3',0,NULL),(2,'S.h.e - 天亮了.mp3','resource/music/S.h.e - 天亮了.mp3',0,NULL),(3,'by2 - 爱丫爱丫.mp3','resource/music/by2 - 爱丫爱丫.mp3',0,NULL),(4,'丁当 - 漂洋过海来看你.mp3','resource/music/丁当 - 漂洋过海来看你.mp3',0,NULL),(5,'七朵组合 - 落花情.mp3','resource/music/七朵组合 - 落花情.mp3',0,NULL),(6,'仙剑奇侠传 - 生生世世的爱 - 3片头曲 完整版.mp3','resource/music/仙剑奇侠传 - 生生世世的爱 - 3片头曲 完整版.mp3',0,NULL),(7,'周传雄 - 寂寞沙洲冷.mp3','resource/music/周传雄 - 寂寞沙洲冷.mp3',0,NULL),(8,'周杰伦 - 稻香.mp3','resource/music/周杰伦 - 稻香.mp3',0,NULL),(9,'周杰伦 - 菊花台.mp3','resource/music/周杰伦 - 菊花台.mp3',0,NULL),(10,'周杰伦 - 青花瓷.mp3','resource/music/周杰伦 - 青花瓷.mp3',0,NULL),(11,'品冠 - 陪你一起老.mp3','resource/music/品冠 - 陪你一起老.mp3',0,NULL),(12,'唐磊 - 丁香花.mp3','resource/music/唐磊 - 丁香花.mp3',0,NULL),(13,'回音哥 - 芊芊.mp3','resource/music/回音哥 - 芊芊.mp3',0,NULL),(14,'小贱、星弟 - 不想做朋友.mp3','resource/music/小贱、星弟 - 不想做朋友.mp3',0,NULL),(15,'小贱、星弟 - 当我唱起这首歌.mp3','resource/music/小贱、星弟 - 当我唱起这首歌.mp3',0,NULL),(16,'开始的开始 我们都是孩子.mp3','resource/music/开始的开始 我们都是孩子.mp3',0,NULL),(17,'张悬 - Scream.mp3','resource/music/张悬 - Scream.mp3',0,NULL),(18,'张悬 - 宝贝.mp3','resource/music/张悬 - 宝贝.mp3',0,NULL),(19,'张韶涵 - 隐形的翅膀.mp3','resource/music/张韶涵 - 隐形的翅膀.mp3',0,NULL),(20,'星弟 - 静悄悄.mp3','resource/music/星弟 - 静悄悄.mp3',0,NULL),(21,'林志颖 - 十七岁的雨季.mp3','resource/music/林志颖 - 十七岁的雨季.mp3',0,NULL),(22,'汪苏泷 - 苦笑.mp3','resource/music/汪苏泷 - 苦笑.mp3',0,NULL),(23,'爱朵女孩 - 纯真年代.mp3','resource/music/爱朵女孩 - 纯真年代.mp3',0,NULL),(24,'王露凝 - 眼泪的错觉.mp3','resource/music/王露凝 - 眼泪的错觉.mp3',0,NULL),(25,'网络歌手 - 挥剑问情 - 仙剑奇侠传三主题曲.mp3','resource/music/网络歌手 - 挥剑问情 - 仙剑奇侠传三主题曲.mp3',0,NULL),(26,'范玮琪 - 那些花儿.mp3','resource/music/范玮琪 - 那些花儿.mp3',0,NULL),(27,'许嵩 - 断桥残雪.mp3','resource/music/许嵩 - 断桥残雪.mp3',0,NULL),(28,'郁可唯 - 时间煮雨.mp3','resource/music/郁可唯 - 时间煮雨.mp3',0,NULL),(29,'郑智化 - 水手.mp3','resource/music/郑智化 - 水手.mp3',0,NULL),(30,'陈瑞 - 我是你千百年前放生的白狐.mp3','resource/music/陈瑞 - 我是你千百年前放生的白狐.mp3',0,NULL),(31,'陈绮贞 - 旅行的意义.mp3','resource/music/陈绮贞 - 旅行的意义.mp3',0,NULL),(32,'陈翔 - 一生有你.mp3','resource/music/陈翔 - 一生有你.mp3',0,NULL),(33,'马郁 - 下辈子如果我还记得你.mp3','resource/music/马郁 - 下辈子如果我还记得你.mp3',0,NULL),(34,'齐秦 - 你的样子.mp3','resource/music/齐秦 - 你的样子.mp3',0,NULL),(35,'齐秦 - 大约在冬季.mp3','resource/music/齐秦 - 大约在冬季.mp3',0,NULL);
/*!40000 ALTER TABLE `music_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ownner_music`
--

DROP TABLE IF EXISTS `ownner_music`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ownner_music` (
  `CLIENT_ID` int(11) NOT NULL,
  `MUSIC_ID` int(11) NOT NULL,
  KEY `ownner_music_CLIENT_ID_idx` (`CLIENT_ID`),
  KEY `ownner_music_MUSIC_ID_idx` (`MUSIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ownner_music`
--

LOCK TABLES `ownner_music` WRITE;
/*!40000 ALTER TABLE `ownner_music` DISABLE KEYS */;
INSERT INTO `ownner_music` VALUES (1111,1),(1111,4),(1111,5),(1111,8),(1111,9),(1111,10),(1111,16),(1111,18),(1111,35);
/*!40000 ALTER TABLE `ownner_music` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_client`
--

DROP TABLE IF EXISTS `user_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_client` (
  `CLIENT_ID` int(11) NOT NULL,
  `CLIENT_password` char(30) NOT NULL,
  `CLIENT_name` char(20) NOT NULL,
  `CLIENT_money` int(11) NOT NULL,
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_client`
--

LOCK TABLES `user_client` WRITE;
/*!40000 ALTER TABLE `user_client` DISABLE KEYS */;
INSERT INTO `user_client` VALUES (1111,'1111','林锦泽',999999),(2222,'2222','测试',0);
/*!40000 ALTER TABLE `user_client` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-07-18 21:08:46
