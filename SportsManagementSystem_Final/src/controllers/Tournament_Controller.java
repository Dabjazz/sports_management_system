/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Date;
import java.util.List;
import models.Tournament_Model;
import views.Manage_Tournament_View;

/**
 *
 * @author MANALI
 */
public class Tournament_Controller {
    
    public Manage_Tournament_View tourform = null;
    
    public void createManageTournamentView(){
        tourform = new Manage_Tournament_View();
        tourform.controller = this;
    }

    public void addTournament(String tour_name, String tour_venue, Date tour_start_date, Date tour_end_date, int game_id) {
        Tournament_Model tournament = new Tournament_Model(tour_name, tour_venue, new java.sql.Date(tour_start_date.getTime()), new java.sql.Date(tour_end_date.getTime()), game_id);
        if(tournament.addTournament()){
            tourform.showMsg("Tournament details added successfully");
        }
        else{
            tourform.showMsg("Sorry! Tournament details coud not be added");
        }
    }

    public List<Tournament_Model> getAllTournaments() {
        return Tournament_Model.getAllTournaments();
    }

    public void updateTournament(String u_tour_name, String u_tour_venue, Date u_tour_start_date, Date u_tour_end_date, int u_game_id) {
        Tournament_Model update_tournament = new Tournament_Model(u_tour_name, u_tour_venue, new java.sql.Date(u_tour_start_date.getTime()), new java.sql.Date(u_tour_end_date.getTime()), u_game_id);
        if(update_tournament.updateTournament()){
            tourform.showMsg("Tournament details updated successfully");
        }
        else{
            tourform.showMsg("Sorry! Tournament details could not be updated");
        }
    }

    public void deleteTournament(Tournament_Model tournament) {
        if(tournament.deleteTournament()){
            tourform.showMsg("Tournament details deleted successfully");
        }
        else{
            tourform.showMsg("Sorry! Tournament details could not be deleted");
        }
    }
    
}
