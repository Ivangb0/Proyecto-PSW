package com.example.odswix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SingletonSQL {

    private static final String url = "jdbc:mysql://wixserver.mysql.database.azure.com:3306/wixdatabase?useSSL=true";
    private static final String user = "KogMaw";
    private static final String password = "WIXAdmin1";
    private static Connection connection = null;

    public static Connection getConexion() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(url, user, password);
            }  catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static ResultSet consultar(String consulta) {
        ResultSet rs = null;
        try {
            Statement statement = getConexion().createStatement();
            rs = statement.executeQuery(consulta);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
    public static PreparedStatement insertar(String objeto){
        PreparedStatement ps = null;
        try {
            ps = getConexion().prepareStatement(objeto);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    public static void finConexion() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }
}
