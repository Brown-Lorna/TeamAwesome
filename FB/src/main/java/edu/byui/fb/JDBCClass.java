package edu.byui.fb;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCClass {

    static String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    static String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    static String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    static String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:myql://" + host + ":" + port + "/ancestor";

    //  Database credentials
    static final String USER = username;
    static final String PASS = password;

    public JDBCClass() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

/*   public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = conn.createStatement();

            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");

    }//end main
*/
    public List getPeople() {
        
            Connection conn = null;
            Statement stmt = null;
            String sql = null;
            ResultSet rs = null;
            List<Person> people = new ArrayList<>();
            try {
                //connect
                conn = DriverManager.getConnection(DB_URL, USER, PASS);
                sql = "SELECT * FROM person;";
                rs = stmt.executeQuery(sql);
            } catch (SQLException ex) {
                Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        try {
            while (rs.next()) {
                Person person = new Person();
                person.setFirst_name(rs.getString("first_name"));
                person.setLast_name(rs.getString("last_name"));
                person.setId(rs.getInt("id"));
                person.setBirthday(rs.getDate("birthday"));
                
                people.add(person);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JDBCClass.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return people;
        
    }



public void getParents(String child) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}//end JDBCExample
