package com.website.websiteVaadin.postgreSQL;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
 
public class CreateDataSourceForJdbcTemplate {
 
    private static final String driverClassName = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://ec2-54-247-85-251.eu-west-1.compute.amazonaws.com:5432/dalgrrbn2f2rmf";
    private static final String dbUsername = "lexcagotrjlhnb";
    private static final String dbPassword = "322226ec4a5ffc988df83b697f523bde2fbe84ad2748b52e2cfa01edd1582e1d";
 
    private static DataSource dataSource;
     
   public CreateDataSourceForJdbcTemplate() throws Exception {
     
        dataSource = getDataSource();
         
        // JdbcTemplate template = new JdbcTemplate(dataSource); // constructor
         
        JdbcTemplate template = new JdbcTemplate();
        template.setDataSource(dataSource);
         
        System.out.println(dataSource.getClass());
         
    }
     
    public static DriverManagerDataSource getDataSource() {
 
  DriverManagerDataSource dataSource = new DriverManagerDataSource();
 
  dataSource.setDriverClassName(driverClassName);
 
  dataSource.setUrl(url);
 
  dataSource.setUsername(dbUsername);
 
  dataSource.setPassword(dbPassword);
 
  return dataSource;
    }
     
}