/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import models.User_Model;
import views.Admin_View;
import views.Coordinator_View;
import views.Login_View;
import views.Manage_Game_View;
import views.Student_Registration_View;
import views.Student_View;

/**
 *
 * @author MANALI
 */
public class User_Controller {
       
    public Admin_View cregform = null;
    public Student_Registration_View sregform = null;
    public Login_View loginform = null;
    public Manage_Game_View gameform = null;
    public Coordinator_View cordform = null;
    
    public User_Controller(){}

    public void createStudentRegistrationView(){
        sregform = new Student_Registration_View();
        sregform.controller = this;
    }
    
    public void createCordinatorView(){
        cregform=new Admin_View();
        cregform.controller=this;
    }
    
    public void createLoginView(){
        loginform = new Login_View();
        loginform.controller = this;
    }
    
    public void createManageGameView(){
        gameform = new Manage_Game_View();
        gameform.ucontroller = this;
    }
    
    public void registerCoordinator(String fname, String lname, String email, String password, String phno) {
        User_Model coordinator = new User_Model(fname, lname, email, password, phno);
        if(coordinator.addCoordinator()){
            cregform.showMsg("Coordinator registered successfully");
        }
        else{
            cregform.showMsg("Sorry! Coordinator could not be registered");
        }
    }

    public List<User_Model> getAllCoordinators(){
        return User_Model.getAllCoordiators();
    }
    
    public void registerStudent(String fname, String lname, String email, String pswd, String phno) {
        User_Model student = new User_Model(fname, lname, email, pswd, phno);
        if(student.addStudent()){
            sregform.showMsg("You are successfully registered");
        }
        else{
            sregform.showMsg("Sorry! You were not registered");
        }
    }   

    public void deleteCoordinator(User_Model coordinator) {
        if(coordinator.deleteCoordinator()){
            cregform.showMsg("Coordinator deleted successfully");
            
        }
        else{
            cregform.showMsg("Sorry! Could not delete the coordinator");
        }
    }

    public void loginUser(String email, String pswd) {
        User_Model login = User_Model.getUser(email, pswd);
        if(login==null){
            loginform.showMsg("Invalid username or password");
            return;
        }
        String user_email = login.getEmail();
        String user_pswd = login.getPassword();
        int user_role = login.getUser_role();
        int user_id = login.getUser_id();

       // System.out.println(user_role);
        
        if(email.equals(user_email) && pswd.equals(user_pswd)){
            if(user_role == 1){
                Admin_View admin = new Admin_View(user_id);
                admin.setVisible(true);
                loginform.dispose();
            }
            if(user_role == 2)
            {
                Coordinator_View coordinator = new Coordinator_View(user_id);
                coordinator.setVisible(true);
                loginform.dispose();
            }
            if(user_role == 3){
                Student_View student = new Student_View(user_id);
                student.setVisible(true);
                loginform.dispose();
            }
            
        }
        else{
            loginform.showMsg("Sorry! Login failed");
        }
    }
    
    public List<User_Model> getAllStudentGamesDetails(String gname) {
        return User_Model.getAllStudentGamesDetails(gname);
    }
    
    public static void main(String[] args) {
        Login_View login = new Login_View();
        login.setVisible(true);
    }
}
