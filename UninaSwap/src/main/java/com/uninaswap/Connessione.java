package com.uninaswap;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Connessione {
    private static Connection conn = null;
	
	public static Connection getConnection() throws SQLException {
    	if(conn == null) {
    		Properties props = new Properties();
            try (InputStream input = Controller.class.getClassLoader().getResourceAsStream("uninaswap/resources/db.properties")) { 
                if (input == null) throw new RuntimeException("File db.properties non trovato!");
                props.load(input);
                String url = props.getProperty("db.url");
                String username = props.getProperty("db.username");
                String password = props.getProperty("db.password");
                conn = DriverManager.getConnection(url, username, password);
                System.out.println("Connessione creata");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
    	}
    	
    	return conn;
    }
}
