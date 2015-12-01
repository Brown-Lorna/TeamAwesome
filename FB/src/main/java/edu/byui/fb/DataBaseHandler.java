package edu.byui.fb;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler {
    private String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    private String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    private String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    private String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:myql://" + host + ":" + port + "/ancestor";

    // TODO: Is there any other member variable or function/method that is needed?
    
    public DataBaseHandler() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // TODO: getImages
    
    // TODO: getUser
    
    // TODO: addImage
    
    // TODO: addUser
}
