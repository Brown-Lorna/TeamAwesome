package edu.byui.fb;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler {
    // There must only exist one!
    private static final DataBaseHandler DBH_INSTANCE = new DataBaseHandler();

    // Database information
    private String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    private String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    private String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    private String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://" + host + ":" + port + "/holidatabase";

    /**
     * DataBaseHandler Constructor
     */
    private DataBaseHandler() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * getInstance
     *     Use this to get the only existing database.
     * @return 
     */
    public static DataBaseHandler getInstance() {
        return DBH_INSTANCE;
    }

    /**
     * addImage
     *     Adds and image to the database.
     * @param image
     * @param user 
     */
    public void addImage(Image image, User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            // Connect
            conn = DriverManager.getConnection(DB_URL, username, password);
            
            // Set up statement
            String sql = "INSERT INTO images (image, title, user_id) VALUES (?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            
            // Add in the parameters
            stmt.setBlob(1, image.getBytes());
            stmt.setString(2, image.getName());
            stmt.setInt(3, user.getId());
            
            // Execute the statement
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * getImage
     *     Get an image from the database given an id.
     * @param id
     * @return 
     */
    public Image getImage(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Image image = new Image();

        try {
            // Connect
            conn = DriverManager.getConnection(DB_URL, username, password);
            
            // Prepare statement
            stmt = conn.createStatement();
            String sql = "SELECT * FROM images WHERE id = " + id + " LIMIT 1;";
            
            // Execute Query statement
            rs = stmt.executeQuery(sql);
            
            // Get information about the image.
            rs.next();
            image.setName(rs.getString("title"));
            image.setBytes(rs.getBinaryStream("image"));
            image.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return image;
    }

    /**
     * getImages
     *     Get all images in database
     * @return 
     */
    public List getImages() {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<Image> images = new ArrayList<>();
        
        try {
            // Connect
            conn = DriverManager.getConnection(DB_URL, username, password);
            
            // Prepare statement
            stmt = conn.createStatement();
            sql = "SELECT * FROM images;";
            
            // Execute statement
            rs = stmt.executeQuery(sql);
            
            // Go through each result set and set information
            while (rs.next()) {
                Image image = new Image();
                InputStream is = rs.getBinaryStream("image");

                image.setName(rs.getString("title"));
                image.setBytes(is);
                image.setId(rs.getInt("id"));

                images.add(image);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return images;
    }

    /**
     * deleteImage
     *     Delete an image given some id.
     * @param imageId 
     */
    public void deleteImage(int imageId) {
        Connection conn = null;
        Statement stmt = null;
        String sql = "DELETE FROM images WHERE id = " + imageId;
        
        try {
            // Connect
            conn = DriverManager.getConnection(DB_URL, username, password);
            
            // Prepare statement
            stmt = conn.createStatement();
            
            // Execute statement
            stmt.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * getUser
     *     Get a user from the database given a username
     * @param user_name
     * @return 
     */
    public User getUser(String user_name) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = new User();

        try {
            // Connect
            conn = DriverManager.getConnection(DB_URL, username, password);
            
            // Prepare statement
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE user_name = '" + user_name + "' LIMIT 1;";
            
            // Execute
            rs = stmt.executeQuery(sql);
            
            // Get the result
            rs.next();
            
            // Set the information
            user.setUsername(rs.getString("user_name"));
            user.setId(rs.getInt("user_id"));
            user.setUserType(rs.getInt("user_type"));
            user.setPassword(rs.getString("user_pass"));
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return user;
    }

}