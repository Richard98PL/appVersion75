package com.website.websiteVaadin.DataUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.website.websiteVaadin.postgreSQL.CreateDataSourceForJdbcTemplate;

import java.util.List;


public class UserService {

	JdbcTemplate jdbcTemplate = new JdbcTemplate(CreateDataSourceForJdbcTemplate.getDataSource());

    public List<User> findAll() {
        return jdbcTemplate.query(
            "SELECT id, login, password, email FROM users",
                (rs, rowNum) -> new User(
				                		rs.getLong("id"),
						                rs.getString("login"), 
						                rs.getString("password"),
						                rs.getString("email")
						                )
                				);
    }
    
    

    public void update(User user) { //metoda do updatu 
        jdbcTemplate.update(
            "UPDATE users SET login=?, password=?, email=? WHERE id=?",
			            		user.getLogin(), 
			            		user.getPassword(), 
			            		user.getEmail(),
			            		user.getId()
        					);
    }
    
    
    
    
    public void insert(String newLogin, String newPassword, String newEmail) { //metoda do insertowania dsaas daseq
    	jdbcTemplate.update(
	            "INSERT INTO users(login,password,email) VALUES (?,?,?)",
	            				newLogin,
	            				newPassword,
	            				newEmail
	            			);
    }
    
    
    
    
    
    public void delete(String newLogin, String newPassword) { //metoda do usuwania
    	jdbcTemplate.update(
    			"DELETE FROM user WHERE login=(?) AND password=(?)",
    							newLogin,
    							newPassword
    						);
    }
    
    
    
    
    public int checkLogin(String loginToCheck) {
    	String toExecute = "SELECT count(*) FROM users WHERE login = ?";
    	int exists = jdbcTemplate.queryForObject(toExecute, new Object[] {loginToCheck}, Integer.class);
    	return exists;
    }
    
    
    
    
    
    public int checkEmail (String emailToCheck) {
    	String toExecute = "SELECT count(*) FROM users WHERE email = ?";
    	int exists = jdbcTemplate.queryForObject(toExecute, new Object[] {emailToCheck}, Integer.class);
    	return exists;
    }
    
    
    
    
    public boolean checkPasswordCorrectness(String loginPodany, String hasloPodane) {
    	String toExecute = "SELECT password FROM users WHERE login =?";
    	String pobraneHaslo = jdbcTemplate.queryForObject(toExecute, new Object[] {loginPodany}, String.class);
    	if(pobraneHaslo.equals(hasloPodane)) return true;
    	else
    	return false;
    }
    
    
    
    
    public void clearTable() {
    	jdbcTemplate.update(
    			"DELETE FROM users WHERE true");
    }
}