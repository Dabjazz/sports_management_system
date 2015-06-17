package models;
import java.sql.*;

public class DB {
    
    static Connection con=null;
    
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/sports_management_system","root","root123");
            return con;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }      
        
    } 
    
}
