package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User_Model {
    
    private int user_id;
    private String user_first_name;
    private String user_last_name;
    private String email;
    private String phno;
    private String password;
    private int user_role;

    public User_Model(int user_id, String user_first_name, String user_last_name, String email, String password, String phno, int user_role) {
        this.user_id = user_id;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.email = email;
        this.password = password;
        this.phno = phno;
        this.user_role = user_role;
    }  

    public User_Model(String user_first_name, String user_last_name, String email, String password, String phno) {
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.email = email;
        this.password = password;
        this.phno = phno;
    }
    
    public User_Model(int userid,String user_first_name, String user_last_name) {
        this.user_id=userid;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
    }
   
    public User_Model(){}
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_role() {
        return user_role;
    }

    public void setUser_role(int user_role) {
        this.user_role = user_role;
    }  

    @Override
    public String toString() {
        return this.user_first_name+" "+this.user_last_name;
    }
    
    public static List<User_Model> getAllUsers(){
        Connection con=DB.getConnection();
        List<User_Model> users=new ArrayList<User_Model>();
        
        if(con != null){
            try {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM user_details");
                while (rs.next()) {
                    users.add(new User_Model(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
                }
                for(User_Model u:users){
                    System.out.println(u.email);
                }
                return users;
            }
            catch (SQLException ex) {
                Logger.getLogger(User_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }     
        }
        return null;
    }
    
    
    public static List<User_Model> getAllCoordiators(){
        Connection con=DB.getConnection();
        List<User_Model> users=new ArrayList<User_Model>();
        
        if(con != null){
            try {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM user_details WHERE role_id=2");
                while (rs.next()) {
                    users.add(new User_Model(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7)));
                }
                return users;
            }
            catch (SQLException ex) {
                Logger.getLogger(User_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }     
        }
        return null;
    }
    
    public static User_Model getUser(String email, String pswd){        
        Connection con=DB.getConnection();
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM user_details WHERE user_email='"+email+"' AND user_password='"+pswd+"'");
                if(rs.next()){
                    return new User_Model(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7));
                }
                else{
                    return null;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(User_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;    
    }
    
    public boolean addCoordinator(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("INSERT INTO user_details VALUES(NULL,'"+user_first_name+"','"+user_last_name+"','"+email+"','"+password+"','"+phno+"',2)");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(User_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public boolean addStudent(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("INSERT INTO user_details VALUES(NULL,'"+user_first_name+"','"+user_last_name+"','"+email+"','"+password+"','"+phno+"',3)");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(User_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
//    public User_Model updateUser(){
//        Connection con=DB.getConnection();  
//        
//        if(con != null){
//            try{
//                Statement st=con.createStatement();
//                st.executeUpdate("UPDATE user_details SET user_first_name='"+user_first_name+"',user_last_name='"+user_last_name+"',user_email='"+email+"',user_phno='"+phno+"',role_id="+user_role+" WHERE user_id="+user_id);
//            }
//            catch (SQLException ex) {
//                Logger.getLogger(User_Model.class.getName()).log(Level.SEVERE, null, ex);
//                return null;
//            }
//        }
//        return null;
//    }
    
    public boolean deleteCoordinator(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("DELETE FROM user_details WHERE user_email='"+this.email+"'");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(User_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public static List<User_Model> getAllStudentGamesDetails(String gname){
        Connection con=DB.getConnection();
        List<User_Model> stud_game_details = new ArrayList<User_Model>();
        
        if(con != null){
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT b.user_id, b.user_first_name, b.user_last_name FROM game_details a, user_details b, student_game_details c WHERE a.game_id=c.game_id AND b.user_id=c.user_id AND a.game_name='"+gname+"'");
                while (rs.next()) {
                    stud_game_details.add(new User_Model(rs.getInt(1), rs.getString(2), rs.getString(3)));
                }
                return stud_game_details;
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }     
        }
        return null;
    }
    
//    public static void main(String[] args) {
//        User_Model.getAllStudentGamesDetails();
//    }
}
