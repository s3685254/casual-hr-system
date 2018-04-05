/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Course;

import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author jye
 */
public class Task {
    @DatabaseField(id = true)
    int courseID;
    @DatabaseField()    
    int id;
    float basePayRate;
    boolean recurring;
    int minStaff;
    
    void addActivity(){
    
    }
    
    void modifyTask(){
        
    }
    
}

