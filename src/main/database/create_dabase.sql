CREATE DATABASE IF NOT EXISTS `wegrow`;

USE `wegrow`;

Create TABLE IF NOT EXISTS `address`(
  `address_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `address_line1` VARCHAR(200) NOT NULL,
  `address_line2` VARCHAR(200),
  `address_line3` VARCHAR(200),
  `city` VARCHAR(50),
  `state` VARCHAR(50),
  `pincode` VARCHAR(8),
  `create_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  `lastupdate_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`address_id`)
);

CREATE TABLE IF NOT EXISTS `user`(
  `user_id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL ,
  `last_name` VARCHAR(100),
  `email` VARCHAR(255) NOT NULL ,
  `phone` VARCHAR(19),
  `address_id` INT NOT NULL,
  `create_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  `lastupdate_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`user_id`),
  FOREIGN KEY (`address_id`) REFERENCES `address`(`address_id`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `beneficiary`(
  `beneficiary_id` MEDIUMINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL ,
  `last_name` VARCHAR(100),
  `email` VARCHAR(255),
  `phone` VARCHAR(19),
  `address_id` INT NOT NULL,
  `create_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  `lastupdate_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`beneficiary_id`),
  FOREIGN KEY (`address_id`) REFERENCES `address`(`address_id`) ON DELETE CASCADE
);

CREATE table IF NOT EXISTS `cause`(
  `cause_id` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `cause` VARCHAR(255),
  `cause_desc` LONGTEXT,
  PRIMARY KEY (`cause_id`)
);

CREATE TABLE IF NOT EXISTS `beneficiary_need`(
  `need_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `beneficiary_id` MEDIUMINT NOT NULL,
  `user_id` SMALLINT,
  `cause_id` SMALLINT NOT NULL,
  `need_details` MEDIUMTEXT,
  `amount` DECIMAL(13,2),
  `recurring_indicator` BIT,
  `recurring_frequency` VARCHAR(15),
  `status` VARCHAR(15),
  `privacy_indicator` bit,
  `create_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  `lastupdate_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`need_id`),
  FOREIGN KEY (`beneficiary_id`) REFERENCES `beneficiary`(`beneficiary_id`) ON DELETE RESTRICT,
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE SET NULL ,
  FOREIGN KEY (`cause_id`) REFERENCES `cause`(`cause_id`) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS `contribution`(
  `contribution_id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `need_id` INT NOT NULL,
  `user_id` SMALLINT NOT NULL,
  `amount` DECIMAL(13,2),
  `privacy_indicator` bit,
  `transaction_dtime` DATETIME(3) NOT NULL DEFAULT current_timestamp,
  PRIMARY KEY (`contribution_id`),
  FOREIGN KEY (`need_id`) REFERENCES `beneficiary_need`(`need_id`) ON DELETE CASCADE,
  FOREIGN KEY (`user_id`) REFERENCES `user`(`user_id`) ON DELETE RESTRICT
);