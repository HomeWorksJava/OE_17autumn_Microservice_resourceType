/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.microservices.homework.resourcetype.resourcetypes_dropwizardservice.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oliver
 */
public class DBManager {

    Connection connection = null;

    public DBManager() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connect();
    }

    public ResultSet ExecuteQuery(String sqlStatement) throws SQLException {
        if(connection == null || connection.isClosed()) {
            try {
                Connect();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sqlStatement);
        return result;
    }

    public void ExecuteUpdate(String sqlStatement) throws SQLException {
         if(connection == null || connection.isClosed()) {
            try {
                Connect();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlStatement);

    }

    public void Close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private void Connect() throws SQLException, ClassNotFoundException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ResourceTypeDB", "service_user", "Aa123456");
    }

}
