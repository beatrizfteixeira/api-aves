CREATE DATABASE  IF NOT EXISTS `brazilianBirds`;
USE `brazilianBirds`;

DROP TABLE IF EXISTS `birds`;

CREATE TABLE `birds` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `commonName` varchar(45) DEFAULT NULL,
                         `scientificName` varchar(45) DEFAULT NULL,
                         `description` varchar(180) DEFAULT NULL,
                         PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 ;


INSERT INTO `birds` VALUES
                        (1,'Brazilian Tinamou','Crypturellus strigulosus','The Brazilian tinamou is a type of tinamou found in tropical moist lowland forest in regions of Amazonian South America'),
                        (2,'Ruddy Pigeon','Patagioenas subvinacea','The ruddy pigeon is a largish pigeon which breeds from Costa Rica south to western Ecuador, Bolivia, and central Brazil.');