/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.Game_Model;
import models.User_Model;
import views.Manage_Game_View;
import views.Manage_Tournament_View;
import views.Student_View;

/**
 *
 * @author MANALI
 */
public class Game_Controller {
    
    public Manage_Game_View gameform = null;
    public Student_View studform = null;
    public Manage_Tournament_View tourform = null;

    public Game_Controller(){
    }
    
    public void createManageGameView(){
        gameform=new Manage_Game_View();
        gameform.controller=this;
    }
    
    public void createStudentView(){
        studform=new Student_View();
        studform.controller=this;
    }
    
    public void createTournamentView(){
        tourform = new Manage_Tournament_View();
        tourform.gcontroller = this;
    }
    
    public List<Game_Model> getAllGames() {
        return Game_Model.getAllGames();
    }

    public List<Game_Model> getAllStudentGames() {
        return Game_Model.getAllStudentGames();
    }
    
    public List<Game_Model> getStudentGames(int user_id) {
        return Game_Model.getStudentGames(user_id);
    }
    
    public void deleteGame(Game_Model game) {
        if(game.deleteGame()){
            gameform.showMsg("Game details deleted successfully");
        }
        else{
            gameform.showMsg("Sorry! Could not delete the game details");
        }
    }

    public void addGame(String game_name, int game_no_of_players, int game_time_duration) {
        Game_Model game = new Game_Model(game_name, game_no_of_players, game_time_duration);
        if(game.addGame()){
            gameform.showMsg("Game details added successfully");
        }
        else{
            gameform.showMsg("Sorry! Game details could not be added");
        }
    }

    public boolean addStudentGame(int user_id, int game_id) {
        Game_Model get_student_game = Game_Model.getStudentGame(user_id, game_id);
        if(get_student_game != null){
            int uid = get_student_game.getUser_id();
            int gid = get_student_game.getGame_id();
            
            if(game_id == gid && user_id == uid){
                studform.showMsg("You have already added this game");
                return false;
            }
        }
        else{
            Game_Model add_student_game = new Game_Model(user_id, game_id);
            if(add_student_game.addStudentGame(user_id, game_id)){
                studform.showMsg("Your Game detail added successfully");
                return true;
            }
            else{
                studform.showMsg("Sorry! Your Game detail could not be added");
                return false;
            }
        } 
        return true;
    }

    public void updateGame(String u_game_name, int game_no_of_players, int game_time_duration) {
        Game_Model update_game = new Game_Model(u_game_name, game_no_of_players, game_time_duration);
        if(update_game.updateGame()){
            gameform.showMsg("Game details updated successfully");
        }
        else{
            gameform.showMsg("Sorry! Game details could not be updated");
        }
    }   

    public void deleteSelectedGame(Game_Model selected_game,int game_id, int user_id) {
        if(selected_game.deleteSelectedGame(game_id, user_id)){
            studform.showMsg("Your selected Game details deleted successfully");
        }
        else{
            studform.showMsg("Sorry! Could not delete your selected game details");
        }
    }
}
