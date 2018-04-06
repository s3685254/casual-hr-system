/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Course;

import com.j256.ormlite.field.DatabaseField;
import java.util.ArrayList;

/**
 *
 * @author jye
 */
public class Course {
    @DatabaseField(id = true)
    int courseID;
    @DatabaseField(canBeNull = false)
    String courseName;
    
    public Course(){
        
    };
    
    public Course(int id, String name){
        this.courseID = id;
        this.courseName = name;
    }
}
