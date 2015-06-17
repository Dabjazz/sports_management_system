/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import models.Resource_Model;
import views.Manage_Resource_View;

/**
 *
 * @author MANALI
 */
public class Resource_Controller {
    
    public Manage_Resource_View resourceform = null;

    public void addResource(String resource_name, int resource_quantity) {
        Resource_Model resource = new Resource_Model(resource_name, resource_quantity);
        if(resource.addResource()){
            resourceform.showMsg("Resource details added successfully");
        }
        else{
            resourceform.showMsg("Sorry! Resource details could not be added");
        }
    }
    
}
