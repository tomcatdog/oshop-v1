SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `o2o` ;
CREATE SCHEMA IF NOT EXISTS `o2o` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `o2o` ;

-- -----------------------------------------------------
-- Table `o2o`.`m_contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`m_contract` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`m_contract` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(60) NULL COMMENT '联系人名称' ,
  `phone` VARCHAR(30) NULL COMMENT '联系人电话' ,
  `mobile` VARCHAR(11) NULL COMMENT '联系人手机' ,
  `email` VARCHAR(45) NULL COMMENT '联系人邮箱' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`t_city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`t_city` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`t_city` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL COMMENT '城市名称' ,
  `grade` VARCHAR(45) NULL COMMENT '城市级别， 1为国家 2为省份 3 城市 4 区域' ,
  `p_id` BIGINT NOT NULL COMMENT '所属城市， 关联本表id' ,
  PRIMARY KEY (`id`, `p_id`) ,
  INDEX `fk_t_city_t_city1_idx` (`p_id` ASC) ,
  CONSTRAINT `fk_t_city_t_city1`
    FOREIGN KEY (`p_id` )
    REFERENCES `o2o`.`t_city` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`m_trade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`m_trade` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`m_trade` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL COMMENT '行业名称' ,
  `p_id` BIGINT NOT NULL COMMENT '自关联' ,
  `create_time` DATETIME NULL ,
  `update_time` DATETIME NULL ,
  PRIMARY KEY (`id`, `p_id`) ,
  INDEX `fk_m_trade_m_trade1_idx` (`p_id` ASC) ,
  CONSTRAINT `fk_m_trade_m_trade1`
    FOREIGN KEY (`p_id` )
    REFERENCES `o2o`.`m_trade` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`m_merchant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`m_merchant` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`m_merchant` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL COMMENT '商家名称' ,
  `grade` INT NULL DEFAULT 1 COMMENT '商家级别， 0为免费商家 1为1级商家 2为2级商家 9 为特殊商家' ,
  `address` VARCHAR(180) NULL COMMENT '商家的地址， 区域一下， 如渝中区大坪正街76号' ,
  `contract_id` BIGINT NOT NULL ,
  `city_id` BIGINT NOT NULL COMMENT '所属城市id' ,
  `area_id` BIGINT NOT NULL COMMENT '所属区域id' ,
  `trade_id` BIGINT NOT NULL ,
  `update_time` DATETIME NULL ,
  `create_time` DATETIME NULL ,
  PRIMARY KEY (`id`, `contract_id`, `city_id`, `area_id`, `trade_id`) ,
  INDEX `fk_merchant_contract_idx` (`contract_id` ASC) ,
  INDEX `fk_m_merchant_t_city1_idx` (`city_id` ASC) ,
  INDEX `fk_m_merchant_t_city2_idx` (`area_id` ASC) ,
  INDEX `fk_m_merchant_trade1_idx` (`trade_id` ASC) ,
  CONSTRAINT `fk_merchant_contract`
    FOREIGN KEY (`contract_id` )
    REFERENCES `o2o`.`m_contract` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_merchant_t_city1`
    FOREIGN KEY (`city_id` )
    REFERENCES `o2o`.`t_city` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_merchant_t_city2`
    FOREIGN KEY (`area_id` )
    REFERENCES `o2o`.`t_city` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_m_merchant_trade1`
    FOREIGN KEY (`trade_id` )
    REFERENCES `o2o`.`m_trade` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`sec_login_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`sec_login_account` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`sec_login_account` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `login_name` VARCHAR(45) NULL ,
  `login_pwd` VARCHAR(45) NULL ,
  `grade` BIGINT NULL ,
  `sec_email` VARCHAR(45) NULL ,
  `sec_mobile` VARCHAR(11) NULL ,
  `state` INT NULL COMMENT '状态，0禁用  1正常 2锁定 3密码过期 4账号废弃' ,
  `m_merchant_id` BIGINT NOT NULL ,
  `update_time` DATETIME NULL ,
  `create_time` DATETIME NULL ,
  `failure_count` INT NULL ,
  PRIMARY KEY (`id`, `m_merchant_id`) ,
  INDEX `fk_security_login_account_m_merchant1_idx` (`m_merchant_id` ASC) ,
  CONSTRAINT `fk_security_login_account_m_merchant1`
    FOREIGN KEY (`m_merchant_id` )
    REFERENCES `o2o`.`m_merchant` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`t_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`t_log` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`t_log` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `account_id` BIGINT NOT NULL ,
  `user_name` VARCHAR(45) NULL ,
  `ip` VARCHAR(128) NOT NULL ,
  `create_time` DATETIME NOT NULL ,
  `operation` VARCHAR(256) NULL ,
  `update_time` DATETIME NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`t_param`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`t_param` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`t_param` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `parm_group` VARCHAR(45) NULL ,
  `parm_name` VARCHAR(45) NULL ,
  `parm_value` VARCHAR(120) NULL ,
  `modifiable` INT NULL DEFAULT 1 COMMENT '是否允许修改， 默认为1 -- 可修改  ，  0为不可修改' ,
  `remark` VARCHAR(250) NULL ,
  `create_time` DATETIME NULL ,
  `update_time` DATETIME NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`sec_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`sec_role` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`sec_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `role_name` VARCHAR(45) NULL COMMENT '角色名' ,
  `role_desc` VARCHAR(150) NULL COMMENT '角色描述' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`sec_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`sec_permission` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`sec_permission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL COMMENT '权限名' ,
  `desc` VARCHAR(45) NULL COMMENT '权限描述' ,
  `url` VARCHAR(100) NULL COMMENT '权限相关url， 用于在前台控制菜单显示' ,
  `operation` VARCHAR(45) NULL COMMENT '权限相关操作， 可将权限控制粒度控制到具体操作' ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`sec_role_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`sec_role_permission` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`sec_role_permission` (
  `permission_id` BIGINT NULL ,
  `role_id` BIGINT NULL ,
  INDEX `fk_src_role_permission_sec_permission1_idx` (`permission_id` ASC) ,
  INDEX `fk_src_role_permission_sec_role1_idx` (`role_id` ASC) ,
  CONSTRAINT `fk_src_role_permission_sec_permission1`
    FOREIGN KEY (`permission_id` )
    REFERENCES `o2o`.`sec_permission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_src_role_permission_sec_role1`
    FOREIGN KEY (`role_id` )
    REFERENCES `o2o`.`sec_role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `o2o`.`sec_account_role_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `o2o`.`sec_account_role_permission` ;

SHOW WARNINGS;
CREATE  TABLE IF NOT EXISTS `o2o`.`sec_account_role_permission` (
  `account_id` BIGINT NOT NULL ,
  `role_id` BIGINT NULL ,
  `permission_id` BIGINT NULL ,
  INDEX `fk_sec_accountr_role_per_s_login_account1_idx` (`account_id` ASC) ,
  INDEX `fk_sec_account_role_permission_sec_role1_idx` (`role_id` ASC) ,
  INDEX `fk_sec_account_role_permission_sec_permission1_idx` (`permission_id` ASC) ,
  CONSTRAINT `fk_sec_accountr_role_per_s_login_account1`
    FOREIGN KEY (`account_id` )
    REFERENCES `o2o`.`sec_login_account` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sec_account_role_permission_sec_role1`
    FOREIGN KEY (`role_id` )
    REFERENCES `o2o`.`sec_role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sec_account_role_permission_sec_permission1`
    FOREIGN KEY (`permission_id` )
    REFERENCES `o2o`.`sec_permission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
USE `o2o` ;

-- -----------------------------------------------------
-- Placeholder table for view `o2o`.`v_province`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `o2o`.`v_province` (`id` INT, `name` INT, `grade` INT, `p_id` INT);
SHOW WARNINGS;

-- -----------------------------------------------------
-- Placeholder table for view `o2o`.`v_city`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `o2o`.`v_city` (`id` INT, `name` INT, `grade` INT, `p_id` INT);
SHOW WARNINGS;

-- -----------------------------------------------------
-- Placeholder table for view `o2o`.`v_area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `o2o`.`v_area` (`id` INT, `name` INT, `grade` INT, `p_id` INT);
SHOW WARNINGS;

-- -----------------------------------------------------
-- View `o2o`.`v_province`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `o2o`.`v_province` ;
SHOW WARNINGS;
DROP TABLE IF EXISTS `o2o`.`v_province`;
SHOW WARNINGS;
USE `o2o`;
CREATE  OR REPLACE VIEW `o2o`.`v_province` AS select * from t_city where grade=2
;
SHOW WARNINGS;

-- -----------------------------------------------------
-- View `o2o`.`v_city`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `o2o`.`v_city` ;
SHOW WARNINGS;
DROP TABLE IF EXISTS `o2o`.`v_city`;
SHOW WARNINGS;
USE `o2o`;
CREATE  OR REPLACE VIEW `o2o`.`v_city` AS select * from t_city where grade=3
;
SHOW WARNINGS;

-- -----------------------------------------------------
-- View `o2o`.`v_area`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `o2o`.`v_area` ;
SHOW WARNINGS;
DROP TABLE IF EXISTS `o2o`.`v_area`;
SHOW WARNINGS;
USE `o2o`;
CREATE  OR REPLACE VIEW `o2o`.`v_area` AS select * from t_city where grade=4
;
SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
