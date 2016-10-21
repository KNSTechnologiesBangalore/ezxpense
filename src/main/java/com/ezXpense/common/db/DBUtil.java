package com.ezXpense.common.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



/**
 * 
 * @author KNS-ACCONTS
 *
 */

public class DBUtil {

	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public Connection establishConnection() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		Properties properties = new Properties();
        String propFileName = "ezXpense.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        properties.load(inputStream);
        String dcn=properties.getProperty("driverClassName");
        String url=properties.getProperty("databaseurl");	
        Class c=Class.forName(dcn);
		Driver d=(Driver)c.newInstance();		
		return d.connect(url, properties);
	}
	
	
	public Connection establishDBConnection(String dbSchema)throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		Properties properties = new Properties();
        String propFileName = "ezXpense.properties";
        String dbName=null;
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        properties.load(inputStream);
        String dcn=properties.getProperty("driverClassName");
        Class.forName(dcn);
        String username=properties.getProperty("user");
        String password=properties.getProperty("password");
        String ipaddress=properties.getProperty("ipaddress");
        String port=properties.getProperty("port");
        System.out.println("username "+username);
        System.out.println("password "+password);
        System.out.println("ipaddress "+ipaddress);
        System.out.println("port "+port);
        File configFile = new File("ezXpense.properties");
        if(dbSchema==null){
        	try {
        		FileReader reader = new FileReader(configFile);
        		Properties props = new Properties();
        		props.load(reader);
        		dbName = props.getProperty("dbName");
        		System.out.println("db name is: " +dbName);
        		reader.close();
        	} catch (FileNotFoundException ex) {
            // file does not exist
        	} catch (IOException ex) {
        	// I/O error
        	}
        }
        else{
        	dbName=dbSchema;
        }
        /*Connection con=DriverManager.getConnection("jdbc:postgresql://"+ipaddress+":"+port+"/"+dbSchema, username, password);*/
        /*Connection con=DriverManager.getConnection("jdbc:postgresql://54.83.13.55:5432/knsezdb","+username+","+password+");*/
        Connection con=DriverManager.getConnection("jdbc:postgresql://"+ipaddress+":"+port+"/"+dbName,username,password);
        
        return con;	
        
	}
	
	
	public static Connection getDBConnection(String dbSchema)throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		DBUtil util=new DBUtil();
		Connection con=util.establishDBConnection(dbSchema);
		return con;		
	}
	
	
	
	
	
	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public static Connection getConnection() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		DBUtil util=new DBUtil();
		Connection con=util.establishConnection();
		return con;		
	}
	
	
	
}
