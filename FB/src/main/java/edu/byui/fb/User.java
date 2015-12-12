/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.byui.fb;

/**
 *
 * @author Grant
 */
public class User {
    private String username;
    private String password;
    private int userType;
    private int id;

    /**
     * Default Constructor
     */
    public User() {
        
    }
    
    /**
     * Constructor
     *    Sets username, password, userType, and id.
     * @param username
     * @param password
     * @param userType
     * @param id 
     */
    public User(String username, String password, int userType, int id) {
        this.id       = id;
        this.password = password;
        this.userType = userType;
        this.username = username;
    }
    
    /**
     * username Getter
     * @return 
     */
    public String getUsername() {
        return username;
    }

    /**
     * username Setter
     * @param username 
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * password Getter
     * @return 
     */
    public String getPassword() {
        return password;
    }

    /**
     * password Setter
     * @param password 
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * userType Getter
     * @return 
     */
    public int getUserType() {
        return userType;
    }

    /**
     * userType Setter
     * @param userType 
     */
    public void setUserType(int userType) {
        this.userType = userType;
    }

    /**
     * id Getter
     * @return 
     */
    public int getId() {
        return id;
    }

    /**
     * id Setter
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
}
