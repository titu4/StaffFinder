CREATE DATABASE  IF NOT EXISTS `StuffFinder`;
USE `StuffFinder`;

CREATE TABLE IF NOT EXISTS `WIshlist` (
  `idWIshlist` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `Active` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idWIshlist`,`Title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `Place` (
  `idPlace` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Title` varchar(45) NOT NULL,
  `URL` text,
  `depth` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPlace`,`Title`),
  UNIQUE KEY `idPlace_UNIQUE` (`idPlace`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `Appearance` (
  `idAppearance` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `URL` text,
  `idPlace` int(10) unsigned NOT NULL,
  `idWishlist` int(10) unsigned NOT NULL,
  `View` int(11) DEFAULT '1',
  `Desc` text,
  PRIMARY KEY (`idAppearance`),
  KEY `fk_Appearance_Wish_idx` (`idWishlist`),
  KEY `fk_Appearance_Place_idx` (`idPlace`),
  CONSTRAINT `fk_Appearance_Stuff` FOREIGN KEY (`idWishlist`) REFERENCES `WIshlist` (`idWIshlist`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Appearance_Place` FOREIGN KEY (`idPlace`) REFERENCES `Place` (`idPlace`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `Watch` (
  `idPlace` int(10) unsigned NOT NULL,
  `idWishlist` int(10) unsigned NOT NULL,
  PRIMARY KEY (`idPlace`,`idWishlist`),
  KEY `fk_stuff_idx` (`idWishlist`),
  CONSTRAINT `fk_Watch_place` FOREIGN KEY (`idPlace`) REFERENCES `Place` (`idPlace`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Watch_stuff` FOREIGN KEY (`idWishlist`) REFERENCES `WIshlist` (`idWIshlist`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
