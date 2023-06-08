/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.moyportillo.impl;

import com.moyportillo.patron_factory_method.IDBAdapter;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import com.moyportillo.util.PropertiesUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author mbustillo
 */
public class SQLServerDBAdapterB implements IDBAdapter{
    
    private static final String DB_PROPERTIES = "META_INF/DBSqlserverB.properties";
    private static final String DB_NAMEDB_PROP= "dbname";
    private static final String DB_HOST_PROP = "host";
    private static final String DB_PORT_PROP = "port";
    private static final String DB_USER_PROP = "user";
    private static final String DB_PASSWORD_PROP = "password";
    
    static{
        try{
            new SQLServerDriver();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public Connection getConnection(){
        try {
            String connectionString = createConnectionString();
            Connection connection = DriverManager.getConnection(connectionString);
            System.out.println("Coonection class ==> " + connection.getClass().getName());
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String createConnectionString(){
        Properties prop = PropertiesUtil.loadProperty(DB_PROPERTIES);
        String host = prop.getProperty(DB_HOST_PROP);
        String port = prop.getProperty(DB_PORT_PROP);
        String name = prop.getProperty(DB_NAMEDB_PROP);
        String user = prop.getProperty(DB_USER_PROP);
        String password = prop.getProperty(DB_PASSWORD_PROP);
        
        String connectionString = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + name + ";user="+user+";password="+password;
        System.out.println("ConnectionString ==>" + connectionString);
        return connectionString;
    }
}
