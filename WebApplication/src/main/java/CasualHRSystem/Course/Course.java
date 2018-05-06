/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Course;

import CasualHRSystem.DatabaseDriver;
import CasualHRSystem.User.CourseCoordinator;
import CasualHRSystem.User.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
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
    @DatabaseField(canBeNull = true)
    private String courseCoordinator;
    @DatabaseField(canBeNull = false)
    private String courseStartDate;
    @DatabaseField(canBeNull = false)
    private String courseEndDate;
    
    public Course(){
        
    };
    
    public Course(int id, String name, String courseCoordinator, String courseStartDate, String courseEndDate){
        this.courseID = id;
        this.courseName = name;
        this.courseCoordinator = courseCoordinator;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
    }
    
    public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<Course, Integer> courseDao = DaoManager.createDao(conn, Course.class);
            courseDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
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

    /**
     * @return the courseStartDate
     */
    public String getCourseStartDate() {
        return courseStartDate;
    }

    /**
     * @param courseStartDate the courseStartDate to set
     */
    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    /**
     * @return the courseEndDate
     */
    public String getCourseEndDate() {
        return courseEndDate;
    }

    /**
     * @param courseEndDate the courseEndDate to set
     */
    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }
}
