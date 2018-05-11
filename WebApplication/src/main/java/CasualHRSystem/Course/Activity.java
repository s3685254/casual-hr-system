/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Course;

import CasualHRSystem.DatabaseDriver;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

/**
 *
 * @author jye
 */
public class Activity {
    public static final String COURSE_ID_FIELD_NAME = "courseID";
    public static final String TASK_ID_FIELD_NAME = "taskID";
    public static final String ID_FIELD_NAME = "activityID";
    public static final String NAME_FIELD_NAME = "activityName";
    public static final String LOCATION_FIELD_NAME = "location";
    public static final String STAFF_FIELD_NAME = "staff";
    public static final String NUM_STAFF_FIELD_NAME = "numStaff";
    public static final String STUDENTS_FIELD_NAME = "students";
    public static final String DATE_FIELD_NAME = "date";
    public static final String TIME_FIELD_NAME = "time";
    public static final String DURATION_FIELD_NAME = "duration";
    
    @DatabaseField(generatedId=true, columnName = ID_FIELD_NAME)
    private int activityID;
    @DatabaseField(canBeNull = false, columnName = COURSE_ID_FIELD_NAME)
    private int courseID;
    @DatabaseField(canBeNull = true, columnName = TASK_ID_FIELD_NAME)
    private int taskID;
    @DatabaseField(canBeNull = false, columnName = NAME_FIELD_NAME)
    private String name;    
    @DatabaseField(canBeNull = true, columnName = STUDENTS_FIELD_NAME)
    private int students;
    @DatabaseField(canBeNull = true, columnName = STAFF_FIELD_NAME)
    private int staff;
    @DatabaseField(canBeNull = true, columnName = NUM_STAFF_FIELD_NAME)
    private int numStaff;
    @DatabaseField(canBeNull = false, columnName = LOCATION_FIELD_NAME)
    private String location;
    @DatabaseField(canBeNull = false, columnName = DATE_FIELD_NAME)
    private String date;
    @DatabaseField(canBeNull = false, columnName = TIME_FIELD_NAME)
    private String time;
    @DatabaseField(canBeNull = false, columnName = DURATION_FIELD_NAME)
    private String duration;
    
    
    public Activity(){
        
    }

    public Activity(int courseID, String name, int students, int numStaff, String location, String date, String time, String duration) {
        this.courseID = courseID;
        this.name = name;
        this.students = students;
        this.numStaff = numStaff;
        this.location = location;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }
    
    
    
        public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Activity, Integer> activityDao = DaoManager.createDao(conn, Activity.class);
            activityDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
    public void updateInDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Activity, Integer> activityDao = DaoManager.createDao(conn, Activity.class);
            activityDao.update(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
    
    void modifyActivity(){
        
    }
    
    void createNotification(){
        
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
     * @return the taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * @return the acitivityID
     */
    public int getActivityID() {
        return activityID;
    }

    /**
     * @param acitivityID the acitivityID to set
     */
    public void setActivityID(int acitivityID) {
        this.activityID = acitivityID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the students
     */
    public int getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(int students) {
        this.students = students;
    }

    /**
     * @return the staff
     */
    public int getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(int staff) {
        this.staff = staff;
    }

    /**
     * @return the numStaff
     */
    public int getNumStaff() {
        return numStaff;
    }

    /**
     * @param numStaff the numStaff to set
     */
    public void setNumStaff(int numStaff) {
        this.numStaff = numStaff;
    }

    /**
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
}
