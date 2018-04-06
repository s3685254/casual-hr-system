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
    private int courseID;
    @DatabaseField(canBeNull = false)
    private String courseName;
    
    public Course(){
        
    };
    
    public Course(int id, String name){
        this.courseID = id;
        this.courseName = name;
    }

    /**
     * @return the courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
