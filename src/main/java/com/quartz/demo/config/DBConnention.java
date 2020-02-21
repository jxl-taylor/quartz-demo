package com.quartz.demo.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnention {
	private static Logger logger=Logger.getLogger(DBConnention.class);
	private String DerbyURL="jdbc:derby:users;create=true;";

	Connection  dbConnection=null;
	PreparedStatement prepareStatment=null;
	
	public DBConnention() {
		try {
//			System.setProperty("derby.system.home", "C:\\Program Files\\Java\\jdk1.8.0_181\\javadb");
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
			dbConnection=DriverManager.getConnection(DerbyURL);
			createUsersTable();
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			logger.error(e.toString());
		}
	}

	public Connection getDbConnection() {
		return dbConnection;
	}
	
	public void executeQuery(String sqlQuery) {
		try {
			prepareStatment=dbConnection.prepareStatement(sqlQuery);
			prepareStatment.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}
	
	public ResultSet retrieveResult(String sqlQuery) {
		ResultSet rs=null;
		try {
			prepareStatment=dbConnection.prepareStatement(sqlQuery);
			rs=prepareStatment.executeQuery();
		} catch (SQLException e) {
			logger.error(e.toString());		
		}
		
		return rs;
	}
	
	private void createUsersTable() {
		try {
			String createUserTableSqlQuery ="CREATE TABLE users(id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), userName VARCHAR(255), age VARCHAR(255))";
			DatabaseMetaData schemaMetaData=dbConnection.getMetaData();
			ResultSet rs=schemaMetaData.getTables(null, "APP", "USERS", null);
			while(!(rs.next())) {
				executeQuery(createUserTableSqlQuery);
			}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}
}
