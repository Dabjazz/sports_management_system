package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Resource_Model {
    
    private int resource_id;
    private String resource_name;
    private int resource_quantity;
    private int game_id;
    
    public Resource_Model(int resource_id, String resource_name, int resource_quantity){
        this.resource_id = resource_id;
        this.resource_name = resource_name;
        this.resource_quantity = resource_quantity;
    }

    public Resource_Model(String resource_name, int resource_quantity) {
        this.resource_name = resource_name;
        this.resource_quantity = resource_quantity;
    }

    public int getResource_id() {
        return resource_id;
    }

    public void setResource_id(int resource_id) {
        this.resource_id = resource_id;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public int getResource_quantity() {
        return resource_quantity;
    }

    public void setResource_quantity(int resource_quantity) {
        this.resource_quantity = resource_quantity;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
    
    public static List<Resource_Model> getAllResources(){
        Connection con = DB.getConnection();
        
        List<Resource_Model> resources = new ArrayList<Resource_Model>();
        
        if(con != null){
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM resource_details");
                while(rs.next()){
                    resources.add(new Resource_Model(rs.getInt(1), rs.getString(2), rs.getInt(3)));
                }
                
                return resources;
            }
            catch(SQLException ex){
                Logger.getLogger(Resource_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }
    
    public static Resource_Model getResource(String resource_name){
        Connection con = DB.getConnection();
        
        if(con != null){
            try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM resource_details WHERE resource_name='"+resource_name+"'");
                
                if(rs.next()){
                    return new Resource_Model(rs.getInt(1), rs.getString(2), rs.getInt(3));
                }
                else{
                    return null;
                }
            }
            catch(SQLException ex){
                Logger.getLogger(Resource_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;
    }
    
    public boolean addResource(){
        Connection con = DB.getConnection();
        
        if(con != null){
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate("INSERT INTO resource_details VALUES(NULL,'"+resource_name+"',"+resource_quantity+")");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch(SQLException ex){
                Logger.getLogger(Resource_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public boolean updateResource(){
        Connection con = DB.getConnection();
        
        if(con != null){
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate("UPDATE resource_details SET resource_quantity="+resource_quantity+" WHERE resource_name='"+resource_name+"'");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch(SQLException ex){
                Logger.getLogger(Resource_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public boolean deleteResource(){
        Connection con = DB.getConnection();
        
        if(con != null){
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate("DELETE FROM resource_model WHERE resource_name='"+resource_name+"'");   
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch(SQLException ex){
                Logger.getLogger(Resource_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
}
