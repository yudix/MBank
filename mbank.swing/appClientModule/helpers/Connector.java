package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("got driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306",
					"root", "admin");
			System.out.println("got connection");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * CREATE SCHEMA IF NOT EXISTS `MBANK` ;
	 * 
	 * CREATE TABLE `MBANK`.`clients` ( `client_id` INT NOT NULL AUTO_INCREMENT,
	 * `client_name` VARCHAR(45) NOT NULL, `client_password` VARCHAR(45) NOT
	 * NULL, `type` VARCHAR(45) NOT NULL, `address` VARCHAR(45) NULL, `email`
	 * VARCHAR(45) NULL, `phone` VARCHAR(45) NULL, `comment` VARCHAR(45) NULL,
	 * PRIMARY KEY (`client_id`), UNIQUE INDEX `client_id_UNIQUE` (`client_id`
	 * ASC), UNIQUE INDEX `client_name_UNIQUE` (`client_name` ASC));
	 * 
	 * CREATE TABLE `mbank`.`accounts` ( `account_id` INT NOT NULL
	 * AUTO_INCREMENT, `client_id` INT NOT NULL, `balance` DOUBLE NOT NULL,
	 * `credit_limit` DOUBLE NOT NULL, `comment` VARCHAR(45) NULL, PRIMARY KEY
	 * (`account_id`), UNIQUE INDEX `account_id_UNIQUE` (`account_id` ASC),
	 * UNIQUE INDEX `client_id_UNIQUE` (`client_id` ASC));
	 * 
	 * CREATE TABLE `mbank`.`deposits` ( `deposit_id` INT NOT NULL
	 * AUTO_INCREMENT, `client_id` VARCHAR(45) NOT NULL, `balance` DOUBLE NOT
	 * NULL, `type` VARCHAR(45) NOT NULL, `estimated_balance` DOUBLE NOT NULL,
	 * `opening_date` DATETIME NOT NULL, `closing_date` DATETIME NOT NULL,
	 * PRIMARY KEY (`deposit_id`), UNIQUE INDEX `deposit_id_UNIQUE`
	 * (`deposit_id` ASC));
	 * 
	 * CREATE TABLE `mbank`.`activity` ( `activity_id` INT NOT NULL
	 * AUTO_INCREMENT, `client_id` INT NOT NULL, `amount` DOUBLE NOT NULL,
	 * `activity_date` DATETIME NOT NULL, `commission` DOUBLE NOT NULL,
	 * `description` VARCHAR(45) NOT NULL, PRIMARY KEY (`activity_id`), UNIQUE
	 * INDEX `activity_id_UNIQUE` (`activity_id` ASC));
	 * 
	 * CREATE TABLE `mbank`.`properties` ( `prop_key` VARCHAR(45) NOT NULL,
	 * `prop_value` VARCHAR(45) NOT NULL, PRIMARY KEY (`prop_key`), UNIQUE INDEX
	 * `prop_key_UNIQUE` (`prop_key` ASC));
	 * 
	 * 	INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('regular_deposit_rate', '10000');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('gold_deposit_rate', '100000');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('platinum_deposit_rate', '1000000');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('regular_deposit_commission', '1.5/100');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('gold_deposit_commission', '1/100');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('platinum_deposit_commission', '0.5/100');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('regular_credit_limit', '100000');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('gold_credit_limit', '1000000');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('platinum_credit_limit', 'unlimited');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('commission_rate', '0.5');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('regular_daily_interest', '5/365');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('gold _daily_interest', '7/365');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('platinum _daily_interest', '8/365');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('pre_open_fee', '1/100');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('admin_username', 'system');
		INSERT INTO `mbank`.`properties` (`prop_key`, `prop_value`) VALUES ('admin_password', 'admin');

	 */

}
