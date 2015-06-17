package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game_Model {
    
    private int game_id;
    private String game_name;
    private int no_of_players;
    private int game_duration;
    private int user_id;


    public Game_Model(int game_id, String game_name, int no_of_players, int game_duration) {
        this.game_id = game_id;
        this.game_name = game_name;
        this.no_of_players = no_of_players;
        this.game_duration = game_duration;
    }

    public Game_Model(String game_name, int no_of_players, int game_duration) {
        this.game_name = game_name;
        this.no_of_players = no_of_players;
        this.game_duration = game_duration;
    }

    public Game_Model(int user_id, int game_id) {
        this.game_id = game_id;
        this.user_id = user_id;
    }
   
    public Game_Model(){}

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getGame_name() {
        return game_name;
    }

    public void setGame_name(String game_name) {
        this.game_name = game_name;
    }

    public int getNo_of_players() {
        return no_of_players;
    }

    public void setNo_of_players(int no_of_players) {
        this.no_of_players = no_of_players;
    }

    public int getGame_duration() {
        return game_duration;
    }

    public void setGame_duration(int game_duration) {
        this.game_duration = game_duration;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

  
    
    public static List<Game_Model> getAllGames(){
        Connection con=DB.getConnection();
        List<Game_Model> games=new ArrayList<Game_Model>();
        
        if(con != null){
            try {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM game_details");
                while (rs.next()) {
                    games.add(new Game_Model(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4)));
                }
                return games;
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }     
        }
        return null;
    }
    
    public static Game_Model getGame(String game_name){        
        Connection con=DB.getConnection();
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM game_details WHERE game_name='"+game_name+"'");
                if(rs.next()){
                    return new Game_Model(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                }
                else{
                    return null;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;    
    }
    
    public static Game_Model getGameById(int game_id){        
        Connection con=DB.getConnection();
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM game_details WHERE game_id="+game_id);
                if(rs.next()){
                    return new Game_Model(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getInt(4));
                }
                else{
                    return null;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;    
    }
    
    public boolean addGame(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("INSERT INTO game_details VALUES(NULL,'"+game_name+"',"+no_of_players+","+game_duration+")");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public boolean updateGame(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("UPDATE game_details SET no_of_players="+no_of_players+",game_duration="+game_duration+" WHERE game_name='"+game_name+"'");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public boolean deleteGame(){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("DELETE FROM game_details WHERE game_name='"+game_name+"')");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }   

    public boolean addStudentGame(int user_id, int game_id){
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("INSERT INTO student_game_details VALUES("+user_id+","+game_id+")");
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    public static Game_Model getStudentGame(int user_id, int game_id){        
        Connection con=DB.getConnection();
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM student_game_details WHERE user_id="+user_id+" AND game_id="+game_id);
                if(rs.next()){
                    return new Game_Model(rs.getInt(1),rs.getInt(2));
                }
                else{
                    return null;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return null;    
    }
    
    public static List<Game_Model> getAllStudentGames(){
        Connection con=DB.getConnection();
        List<Game_Model> stud_games=new ArrayList<Game_Model>();
        
        if(con != null){
            try {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT * FROM student_game_details");
                while (rs.next()) {
                    stud_games.add(new Game_Model(rs.getInt(1), rs.getInt(2)));
                }
                return stud_games;
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }     
        }
        return null;
    }
    
    public static List<Game_Model> getStudentGames(int user_id){
        Connection con=DB.getConnection();
        List<Game_Model> stud_games=new ArrayList<Game_Model>();
        
        if(con != null){
            try {
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery("SELECT b.game_id,b.game_name,b.no_of_players,b.game_duration FROM student_game_details a, game_details b WHERE a.game_id=b.game_id AND a.user_id="+user_id);
                while (rs.next()) {
                    stud_games.add(new Game_Model(rs.getInt(1),rs.getString(2), rs.getInt(3), rs.getInt(4)));
                    System.out.println(stud_games);
                }
                return stud_games;
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }     
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.game_name;
    }

    public boolean deleteSelectedGame(int game_id, int user_id) {
        Connection con=DB.getConnection();  
        
        if(con != null){
            try{
                Statement st=con.createStatement();
                int result = st.executeUpdate("DELETE FROM student_game_details WHERE game_id="+game_id+" AND user_id="+user_id);
                if(result > 0){
                    return true;
                }
                else{
                    return false;
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Game_Model.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
}