package edu.byui.fb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler {

    private static final DataBaseHandler DBH_INSTANCE = new DataBaseHandler();

    private String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
    private String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
    private String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
    private String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:myql://" + host + ":" + port + "/holidatabase";

    // TODO: Is there any other member variable or function/method that is needed?
    private DataBaseHandler() {
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
        String sql = null;
        ResultSet rs = null;
        Image image = new Image();
        try {
            conn = DriverManager.getConnection(DB_URL, username, password);
            stmt = conn.createStatement();
            sql = "SELECT * FROM images WHERE id = " + id + " LIMIT 1;";
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
}
