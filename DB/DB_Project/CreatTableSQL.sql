-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema iotservice
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema iotservice
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iotservice` DEFAULT CHARACTER SET utf8mb3 ;
USE `iotservice` ;

-- -----------------------------------------------------
-- Table `iotservice`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iotservice`.`customer` ;

CREATE TABLE IF NOT EXISTS `iotservice`.`customer` (
  `CustomerID` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(50) NOT NULL COMMENT '고객메일',
  `Name` VARCHAR(20) NOT NULL COMMENT '고객성명',
  `Passwd` VARCHAR(20) NOT NULL COMMENT '패스워드',
  PRIMARY KEY (`CustomerID`),
  UNIQUE INDEX `CustomerID_UNIQUE` (`CustomerID` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `iotservice`.`flowerbed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iotservice`.`flowerbed` ;

CREATE TABLE IF NOT EXISTS `iotservice`.`flowerbed` (
  `FlowerBedID` INT NOT NULL,
  `cust_ID` INT NOT NULL,
  `FlowerBedName` VARCHAR(15) NOT NULL COMMENT '화단이름',
  PRIMARY KEY (`FlowerBedID`),
  INDEX `fk_flowerbed_customer1_idx` (`cust_ID` ASC) VISIBLE,
  CONSTRAINT `fk_flowerbed_customer1`
    FOREIGN KEY (`cust_ID`)
    REFERENCES `iotservice`.`customer` (`CustomerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 20220906
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `iotservice`.`flowerbed_controlinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iotservice`.`flowerbed_controlinfo` ;

CREATE TABLE IF NOT EXISTS `iotservice`.`flowerbed_controlinfo` (
  `BedControlSetID` INT NOT NULL COMMENT '화단 제어 ID',
  `flowerbed_BedID_fk` INT NOT NULL COMMENT '화단고유ID',
  `Valve_Info` INT NULL DEFAULT NULL COMMENT '수조밸브설정',
  `Valve_ControlDATE` TIMESTAMP NOT NULL COMMENT '밸브설정날짜',
  `UV_Ramp_Info` INT NULL DEFAULT NULL COMMENT 'UV램프설정',
  `UVRamp_ControlDATE` TIMESTAMP NOT NULL COMMENT 'UV램프설정날짜',
  `AirSupply_Info` INT NULL DEFAULT NULL COMMENT '산소공급기설정',
  `AirSupply_ControlDATE` TIMESTAMP NOT NULL COMMENT '산소공급기설정날짜',
  PRIMARY KEY (`BedControlSetID`),
  INDEX `FlowerBedID_idx` (`flowerbed_BedID_fk` ASC) VISIBLE,
  CONSTRAINT `bed_ID`
    FOREIGN KEY (`flowerbed_BedID_fk`)
    REFERENCES `iotservice`.`flowerbed` (`FlowerBedID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `iotservice`.`flowerbed_plantsetting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iotservice`.`flowerbed_plantsetting` ;

CREATE TABLE IF NOT EXISTS `iotservice`.`flowerbed_plantsetting` (
  `BedSettingID` VARCHAR(45) NOT NULL,
  `Bed_ID_fk` INT NOT NULL,
  `AutoPlant` INT NOT NULL COMMENT '자동재배여부',
  `Valve_MIN` INT NOT NULL COMMENT '밸브최소길이값',
  `Valve_MAX` INT NOT NULL COMMENT '밸브최대길이값',
  `AirCycle_Min` INT NOT NULL COMMENT '산소공급기동작주기값',
  `AirCycle_Sec` INT NOT NULL COMMENT '산소공급기동작시간값',
  `UVRampCycle_Min` INT NOT NULL COMMENT 'UV램프동작주기값',
  `UVRampCycle_Sec` INT NOT NULL COMMENT 'UV램프동작시간값',
  INDEX `fk_flowerbed_plantsetting_flowerbed1_idx` (`Bed_ID_fk` ASC) VISIBLE,
  PRIMARY KEY (`BedSettingID`),
  CONSTRAINT `fk_flowerbed_plantsetting_flowerbed1`
    FOREIGN KEY (`Bed_ID_fk`)
    REFERENCES `iotservice`.`flowerbed` (`FlowerBedID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `iotservice`.`flowerpot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iotservice`.`flowerpot` ;

CREATE TABLE IF NOT EXISTS `iotservice`.`flowerpot` (
  `FlowerpotID` INT NOT NULL,
  `bed_ID` INT NOT NULL COMMENT '화단고유ID',
  `FlowerName` VARCHAR(20) NOT NULL COMMENT '화분이름',
  `FlowerRegDate` DATE NOT NULL COMMENT '화분등록날짜',
  `Plant_Type` INT NOT NULL COMMENT '화분식물종류',
  PRIMARY KEY (`FlowerpotID`),
  INDEX `FlowerBedID_idx` (`bed_ID` ASC) VISIBLE,
  CONSTRAINT `FlowerBedID`
    FOREIGN KEY (`bed_ID`)
    REFERENCES `iotservice`.`flowerbed` (`FlowerBedID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `iotservice`.`flowerpot_controlinfo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iotservice`.`flowerpot_controlinfo` ;

CREATE TABLE IF NOT EXISTS `iotservice`.`flowerpot_controlinfo` (
  `PotControlSetID` INT NOT NULL COMMENT '화분제어고유ID',
  `potID_fk` INT NOT NULL,
  `FlowerPotID` INT NOT NULL COMMENT '화분고유ID',
  `WaterSupply_Info` INT NULL DEFAULT NULL COMMENT '물공급값',
  `WaterSupply_ControlDATE` TIMESTAMP NOT NULL COMMENT '물공급설정날짜',
  `Heater_Info` INT NULL DEFAULT NULL COMMENT '히터설정',
  `Heater_ControlDATE` TIMESTAMP NOT NULL COMMENT '히터설정날짜',
  `Humidifier_Info` INT NULL DEFAULT NULL COMMENT '가습기설정',
  `Humidifier_ContorlDATE` TIMESTAMP NOT NULL COMMENT '가습기설정날짜',
  `LED_Info` INT NULL DEFAULT NULL COMMENT 'LED설정',
  `LED_ControlDATE` TIMESTAMP NOT NULL COMMENT 'LED설정날짜',
  `Fan_Info` INT NULL DEFAULT NULL COMMENT '팬설정',
  `Fan_ControlDATE` TIMESTAMP NOT NULL COMMENT '팬설정날짜',
  PRIMARY KEY (`PotControlSetID`),
  INDEX `FlowerPotID_idx` (`FlowerPotID` ASC) VISIBLE,
  INDEX `fk_flowerpot_controlinfo_flowerpot1_idx` (`potID_fk` ASC) VISIBLE,
  CONSTRAINT `fk_flowerpot_controlinfo_flowerpot1`
    FOREIGN KEY (`potID_fk`)
    REFERENCES `iotservice`.`flowerpot` (`FlowerpotID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `iotservice`.`flowerpot_plantsetting`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `iotservice`.`flowerpot_plantsetting` ;

CREATE TABLE IF NOT EXISTS `iotservice`.`flowerpot_plantsetting` (
  `PotSettingID` INT NOT NULL COMMENT '화분설정고유ID',
  `pot_ID` INT NOT NULL,
  `AutoPlant` INT NOT NULL COMMENT '자동재배여부',
  `Temperature_MIN` INT NOT NULL COMMENT '최소온도',
  `Temperature_MAX` INT NOT NULL COMMENT '최대온도',
  `Humidity_MIN` INT NOT NULL COMMENT '최소습도',
  `Humidity_MAX` INT NOT NULL COMMENT '최대습도',
  `IlluminationTime_ON` DATE NOT NULL COMMENT '조도켜진시각',
  `IlluminationTime_OFF` DATE NOT NULL COMMENT '조도꺼진시각',
  `Lux_Value` INT NOT NULL COMMENT '조도값\\n',
  `Cycle_Min` INT NOT NULL COMMENT '동작주기값',
  `Cycle_Sec` INT NOT NULL COMMENT '동작시간값',
  `Plant_DATE` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '심은날짜',
  PRIMARY KEY (`PotSettingID`),
  INDEX `fk_flowerpot_plantsetting_flowerpot1_idx` (`pot_ID` ASC) VISIBLE,
  CONSTRAINT `fk_flowerpot_plantsetting_flowerpot1`
    FOREIGN KEY (`pot_ID`)
    REFERENCES `iotservice`.`flowerpot` (`FlowerpotID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
