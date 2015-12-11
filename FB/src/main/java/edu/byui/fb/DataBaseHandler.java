package edu.byui.fb;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler {

    private static final DataBaseHandler DBH_INSTANCE = new DataBaseHandler();

    private String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    private String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    private String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    private String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://" + host + ":" + port + "/holidatabase";

    // TODO: Is there any other member variable or function/method that is needed?
    DataBaseHandler() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataBaseHandler getInstance() {
        return DBH_INSTANCE;
    }

    // TODO: getImages
    // TODO: getUser
    // TODO: addImage
    // TODO: Image getImage(id)
    public Image getImage(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Image image = new Image();

        try {
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM images WHERE id = " + id + " LIMIT 1;";
            rs = stmt.executeQuery(sql);
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

    public List getImages() {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        List<Image> images = new ArrayList<>();
        try {
            //connect
            if (host == null) {
                host = "localhost";
                port = "3306";
                username = "root";
                password = "homestar";
            }
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            System.out.println("Connected!");
            sql = "SELECT * FROM images;";
            rs = stmt.executeQuery(sql);
            System.out.println("Executed!");
            while (rs.next()) {
                System.out.println(rs.getString("title"));
                System.out.println(rs.getBlob("image"));
                System.out.println(rs.getString("user_id"));
                System.out.println(rs.getInt("id"));
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

    public InputStream findImage(int id) {
        Connection conn = null;
        Statement stmt = null;
        String sql = null;
        ResultSet rs = null;
        InputStream is = null;
        try {
            //connect
            if (host == null) {
                host = "localhost";
                port = "3306";
                username = "root";
                password = "homestar";
            }
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            System.out.println("Connected!");
            sql = "SELECT TOP 1 image FROM images WHERE id = '" + id + "';";
            rs = stmt.executeQuery(sql);
            System.out.println("Executed!");

            is = rs.getBinaryStream("image");


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

        return is;
    }

    public User getUser(int id) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = new User();

        try {
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE user_id = " + id + " LIMIT 1;";
            rs = stmt.executeQuery(sql);
            rs.next();
            user.setUsername(rs.getString("user_name"));
            user.setId(rs.getInt("user_id"));
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
