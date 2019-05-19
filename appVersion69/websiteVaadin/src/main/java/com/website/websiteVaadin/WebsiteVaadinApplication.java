package com.website.websiteVaadin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
public class WebsiteVaadinApplication  {

		public static String loginZalogowanego;
		public static String emailZalogowanego;
		public static Boolean czyZalogowany=false;
		public static String aktualnieZalogowanyLogin;
		
	public static void main(String[] args) {
		SpringApplication.run(WebsiteVaadinApplication.class, args);
		
		
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-247-85-251.eu-west-1.compute.amazonaws.com:5432/dalgrrbn2f2rmf",
				"lexcagotrjlhnb", "322226ec4a5ffc988df83b697f523bde2fbe84ad2748b52e2cfa01edd1582e1d")) {
			 
            System.out.println("Java JDBC PostgreSQL Example");
            System.out.println("Connected to PostgreSQL database!");
            }
        
		catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }
}
	
	

