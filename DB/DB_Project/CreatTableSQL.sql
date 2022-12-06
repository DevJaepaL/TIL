-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema IoTService
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema IoTService
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `IoTService` DEFAULT CHARACTER SET utf8 ;
USE `IoTService` ;

-- -----------------------------------------------------
-- Table `IoTService`.`Customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `IoTService`.`Customer` ;

CREATE TABLE IF NOT EXISTS `IoTService`.`Customer` (
  `CustomerID` INT NOT NULL COMMENT '고객고유번호',
  `Email` VARCHAR(50) NOT NULL COMMENT '고객메일',
  `Name` VARCHAR(20) NOT NULL COMMENT '고객성명',
  `Passwd` VARCHAR(20) NOT NULL COMMENT '패스워드',
  PRIMARY KEY (`CustomerID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IoTService`.`FlowerBed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `IoTService`.`FlowerBed` ;

CREATE TABLE IF NOT EXISTS `IoTService`.`FlowerBed` (
  `FlowerBedID` INT NOT NULL COMMENT '화단고유ID',
  `Customer_id` INT NOT NULL COMMENT '고객고유번호',
  `FlowerBedName` VARCHAR(15) NOT NULL COMMENT '화단이름',
  PRIMARY KEY (`FlowerBedID`),
  INDEX `fk_FlowerBed_Customer1_idx` (`Customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_FlowerBed_Customer1`
    FOREIGN KEY (`Customer_id`)
    REFERENCES `IoTService`.`Customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IoTService`.`FlowerPot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `IoTService`.`FlowerPot` ;

CREATE TABLE IF NOT EXISTS `IoTService`.`FlowerPot` (
  `FlowerPotID` INT NOT NULL COMMENT '화분고유ID',
  `FlowerBedID` INT NOT NULL COMMENT '화단고유ID',
  `FlowerName` VARCHAR(20) NOT NULL COMMENT '화분이름',
  `FlowerRegDate` DATE NOT NULL COMMENT '화분등록날짜',
  `Plant_Type` INT(3) NOT NULL COMMENT '화분식물종류',
  PRIMARY KEY (`FlowerPotID`),
  INDEX `fk_FlowerPot_FlowerBed_idx` (`FlowerBedID` ASC) VISIBLE,
  CONSTRAINT `fk_FlowerPot_FlowerBed`
    FOREIGN KEY (`FlowerBedID`)
    REFERENCES `IoTService`.`FlowerBed` (`FlowerBedID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IoTService`.`FlowerBed_PlantSetting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `IoTService`.`FlowerBed_PlantSetting` ;

CREATE TABLE IF NOT EXISTS `IoTService`.`FlowerBed_PlantSetting` (
  `BedSettingID` INT NOT NULL COMMENT '화단설정고유ID',
  `FlowerBedID` INT NOT NULL COMMENT '화단고유ID',
  `AutoPlant` INT(1) NOT NULL COMMENT '자동재배여부',
  `Valve_MIN` INT NOT NULL COMMENT '밸브최소길이값',
  `Valve_MAX` INT NOT NULL COMMENT '밸브최대길이값',
  `AirCycle_Min` INT NOT NULL COMMENT '산소공급기동작주기값',
  `AirCycle_Sec` INT NOT NULL COMMENT '산소공급기동작시간값',
  `UVRampCycle_Min` INT NOT NULL COMMENT 'UV램프동작주기값',
  `UVRampCycle_Sec` INT NOT NULL COMMENT 'UV램프동작시간값',
  PRIMARY KEY (`BedSettingID`),
  INDEX `fk_FlowerBed_PlantSetting_FlowerBed1_idx` (`FlowerBedID` ASC) VISIBLE,
  CONSTRAINT `fk_FlowerBed_PlantSetting_FlowerBed1`
    FOREIGN KEY (`FlowerBedID`)
    REFERENCES `IoTService`.`FlowerBed` (`FlowerBedID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IoTService`.`FlowerPot_PlantSetting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `IoTService`.`FlowerPot_PlantSetting` ;

CREATE TABLE IF NOT EXISTS `IoTService`.`FlowerPot_PlantSetting` (
  `PotSettingID` INT NOT NULL COMMENT '화분설정고유ID',
  `FlowerPotID` INT NOT NULL COMMENT '화분고유ID',
  `AutoPlant` INT(1) NOT NULL COMMENT '자동재배여부',
  `Temperature_MIN` INT NOT NULL COMMENT '최소온도',
  `Temperature_MAX` INT NOT NULL COMMENT '최대온도',
  `Humidity_MIN` INT NOT NULL COMMENT '최소습도',
  `Humidity_MAX` INT NOT NULL COMMENT '최대습도',
  `IlluminationTime_ON` DATE NOT NULL COMMENT '조도켜진시각',
  `IlluminationTime_OFF` DATE NOT NULL COMMENT '조도꺼진시각',
  `Lux_Value` INT NOT NULL COMMENT '조도값\n',
  `Cycle_Min` INT NOT NULL COMMENT '동작주기값',
  `Cycle_Sec` INT NOT NULL COMMENT '동작시간값',
  `Plant_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '심은날짜',
  PRIMARY KEY (`PotSettingID`),
  INDEX `fk_FlowerPot_PlantSetting_FlowerPot1_idx` (`FlowerPotID` ASC) VISIBLE,
  CONSTRAINT `fk_FlowerPot_PlantSetting_FlowerPot1`
    FOREIGN KEY (`FlowerPotID`)
    REFERENCES `IoTService`.`FlowerPot` (`FlowerPotID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IoTService`.`FlowerPot_ControlInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `IoTService`.`FlowerPot_ControlInfo` ;

CREATE TABLE IF NOT EXISTS `IoTService`.`FlowerPot_ControlInfo` (
  `PotControlSetID` INT NOT NULL COMMENT '화분제어고유ID',
  `FlowerPotID` INT NOT NULL COMMENT '화분고유ID',
  `WaterSupply_Info` INT NULL COMMENT '물공급값',
  `WaterSupply_ControlDATE` TIMESTAMP NOT NULL COMMENT '물공급설정날짜',
  `Heater_Info` INT(1) NULL COMMENT '히터설정',
  `Heater_ControlDATE` TIMESTAMP NOT NULL COMMENT '히터설정날짜',
  `Humidifier_Info` INT(1) NULL COMMENT '가습기설정',
  `Humidifier_ContorlDATE` TIMESTAMP NOT NULL COMMENT '가습기설정날짜',
  `LED_Info` INT(1) NULL COMMENT 'LED설정',
  `LED_ControlDATE` TIMESTAMP NOT NULL COMMENT 'LED설정날짜',
  `Fan_Info` INT(1) NULL COMMENT '팬설정',
  `Fan_ControlDATE` TIMESTAMP NOT NULL DEFAULT CURRENTTIMESTAMP COMMENT '팬설정날짜',
  INDEX `fk_FlowerPot_ControlInfo_FlowerPot1_idx` (`FlowerPotID` ASC) VISIBLE,
  PRIMARY KEY (`PotControlSetID`),
  CONSTRAINT `fk_FlowerPot_ControlInfo_FlowerPot1`
    FOREIGN KEY (`FlowerPotID`)
    REFERENCES `IoTService`.`FlowerPot` (`FlowerPotID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `IoTService`.`FlowerBed_ControlInfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `IoTService`.`FlowerBed_ControlInfo` ;

CREATE TABLE IF NOT EXISTS `IoTService`.`FlowerBed_ControlInfo` (
  `BedControlSetID` INT NOT NULL COMMENT '화단 제어 ID',
  `FlowerBedID` INT NOT NULL COMMENT '화단고유ID',
  `Valve_Info` INT(1) NULL COMMENT '수조밸브설정',
  `Valve_ControlDATE` TIMESTAMP NOT NULL COMMENT '밸브설정날짜',
  `UV_Ramp_Info` INT(1) NULL COMMENT 'UV램프설정',
  `UVRamp_ControlDATE` TIMESTAMP NOT NULL COMMENT 'UV램프설정날짜',
  `AirSupply_Info` INT(1) NULL COMMENT '산소공급기설정',
  `AirSupply_ControlDATE` TIMESTAMP NOT NULL COMMENT '산소공급기설정날짜',
  INDEX `fk_FlowerBed_ControlInfo_FlowerBed1_idx` (`FlowerBedID` ASC) VISIBLE,
  PRIMARY KEY (`BedControlSetID`),
  CONSTRAINT `fk_FlowerBed_ControlInfo_FlowerBed1`
    FOREIGN KEY (`FlowerBedID`)
    REFERENCES `IoTService`.`FlowerBed` (`FlowerBedID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
