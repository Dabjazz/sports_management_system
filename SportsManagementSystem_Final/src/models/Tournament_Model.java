package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tournament_Model {
    
    private int tournament_id;
    private String tournament_name;
    private String tournament_venue;
    private Date tournament_start_date;
    private Date tournament_end_date;
    private int game_id;
    
    public Tournament_Model(int tournament_id, String tournament_name, String tournament_venue, Date tournament_start_date, Date tournament_end_date, int game_id){
        this.tournament_id = tournament_id;
        this.tournament_name = tournament_name;
        this.tournament_venue = tournament_venue;
        this.tournament_start_date = tournament_start_date;
        this.tournament_end_date = tournament_end_date;
        this.game_id = game_id;
    }

    public Tournament_Model(String tournament_name, String tournament_venue, Date tournament_start_date, Date tournament_end_date, int game_id) {
        this.tournament_name = tournament_name;
        this.tournament_venue = tournament_venue;
        this.tournament_start_date = tournament_start_date;
        this.tournament_end_date = tournament_end_date;
        this.game_id = game_id;
    }

    public int getTournament_id() {
        return tournament_id;
    }

    public void setTournament_id(int tournament_id) {
        this.tournament_id = tournament_id;
    }

    public String getTournament_name() {
        return tournament_name;
    }

    public void setTournament_name(String tournament_name) {
        this.tournament_name = tournament_name;
    }

    public String getTournament_venue() {
        return tournament_venue;
    }

    public void setTournament_venue(String tournament_venue) {
        this.tournament_venue = tournament_venue;
    }

    public Date getTournament_start_date() {
        return tournament_start_date;
    }

    public void setTournament_start_date(Date tournament_start_date) {
        this.tournament_start_date = tournament_start_date;
    }

    public Date getTournament_end_date() {
        return tournament_end_date;
    }

    public void setTournament_end_date(Date tournament_end_date) {
        this.tournament_end_date = tournament_end_date;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
    
    public static List<Tournament_Model> getAllTournaments(){
        Connection con = DB.getConnection();
        List<Tournament_Model> tournaments = new ArrayList<Tournament_Model>();
        
        if(con != null){
            try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tournament_details");
                while(rs.next()){
                    tournaments.add(new Tournament_Model(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getInt(6)));
                }
                 return tournaments;
            }
            catch (SQLException ex) {
                Logger.getLogger(Tournament_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } 
        }
        return null;
    }

    @Override
    public String toString() {
        return this.tournament_name; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public static Tournament_Model getTournament(String tournament_name){
        Connection con = DB.getConnection();
        
        if(con != null){
            try{
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM tournament_details WHERE tournament_name='"+tournament_name+"')");
                if(rs.next()){
                 //   return new Tournament_Model(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                }
                else{
                    return null;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Tournament_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } 
        }
        return null;
    }
    
    public boolean addTournament(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("INSERT INTO tournament_details VALUES(NULL,'"+tournament_name+"','"+tournament_venue+"','"+tournament_start_date+"','"+tournament_end_date+"',"+game_id+")");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Tournament_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public boolean updateTournament(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("UPDATE tournament_details SET tournament_venue='"+tournament_venue+"',tournament_start_date='"+tournament_start_date+"',tournament_end_date='"+tournament_end_date+"',game_id="+game_id+" WHERE tournament_name='"+tournament_name+"'");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
            
                Logger.getLogger(Tournament_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
//    public Tournament_Model deleteTournament(String tournament_name){
//        Connection con=DB.getConnection();  
//        
//        if(con != null){
//            try{
//                Statement st=con.createStatement();
//                st.executeUpdate("DELETE FROM tournament_details WHERE tournament_name='"+tournament_name+"')");
//            }
//            catch (SQLException ex) {
//                Logger.getLogger(Tournament_Model.class.getName()).log(Level.SEVERE, null, ex);
//                return null;
//            }
//        }
//        return null;
//    }  
    
    public boolean deleteTournament(){
        Connection con=DB.getConnection();  
        int result = 0;
        if(con != null){
            try{
                Statement st=con.createStatement();
                result = st.executeUpdate("DELETE FROM tournament_details WHERE tournament_id="+tournament_id);
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Tournament_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }  
}
