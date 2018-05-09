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
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Course {
    public static final String ID_FIELD_NAME = "courseID";
    public static final String CODE_FIELD_NAME = "courseCode";
    public static final String NAME_FIELD_NAME = "courseName";
    public static final String COORDINATOR_FIELD_NAME = "courseCoordinator";
    public static final String START_DATE_FIELD_NAME = "courseStartDate";
    public static final String END_DATE_FIELD_NAME = "courseEndDate";
    public static final String TIMETABLE_COLOUR_FIELD_NAME = "hexColour";
    @DatabaseField(generatedId=true, columnName = ID_FIELD_NAME)
    private int courseID;
    @DatabaseField(canBeNull = false, columnName = CODE_FIELD_NAME)
    private String courseCode;
    @DatabaseField(canBeNull = false, columnName = NAME_FIELD_NAME)
    private String courseName;
    @DatabaseField(canBeNull = true, columnName = COORDINATOR_FIELD_NAME)
    private String courseCoordinator;
    @DatabaseField(canBeNull = false, columnName = START_DATE_FIELD_NAME)
    private String courseStartDate;
    @DatabaseField(canBeNull = false, columnName = END_DATE_FIELD_NAME)
    private String courseEndDate;
    @DatabaseField(canBeNull = false, columnName = TIMETABLE_COLOUR_FIELD_NAME)
    private String hexColour;
    
    public Course(){
        
    };
    
    public Course(String name, String courseCode, String courseCoordinator, String courseStartDate, String courseEndDate){
        this.courseName = name;
        this.courseCode = courseCode;
        this.courseCoordinator = courseCoordinator;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        Random rand = new Random(System.currentTimeMillis());
        int r = (int)(rand.nextFloat()*128)+127;
        int g = (int)(rand.nextFloat()*128)+127;
        int b = (int)(rand.nextFloat()*128)+127;
        String colour = Integer.toHexString(r).toString() + Integer.toHexString(g).toString() + Integer.toHexString(b).toString();
        this.hexColour = colour;
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
    
    public void updateInDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<Course, Integer> courseDao = DaoManager.createDao(conn, Course.class);
            courseDao.update(this);
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

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the courseCoordinator
     */
    public String getCourseCoordinator() {
        return courseCoordinator;
    }

    /**
     * @param courseCoordinator the courseCoordinator to set
     */
    public void setCourseCoordinator(String courseCoordinator) {
        this.courseCoordinator = courseCoordinator;
    }
}
