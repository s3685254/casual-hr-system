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
public class Task {
    public static final String COURSE_ID_FIELD_NAME = "courseID";
    public static final String TASK_ID_FIELD_NAME = "taskID";
    public static final String NAME_FIELD_NAME = "activityName";
    public static final String LOCATION_FIELD_NAME = "location";
    public static final String STAFF_FIELD_NAME = "staff";
    public static final String NUM_STAFF_FIELD_NAME = "numStaff";
    public static final String STUDENTS_FIELD_NAME = "students";
    public static final String DAY_FIELD_NAME = "day";
    public static final String REPEATING_FIELD_NAME = "repeating";
    public static final String FREQUENCY_FIELD_NAME = "frequency";
    public static final String DATE_FIELD_NAME = "date";
    public static final String TIME_FIELD_NAME = "time";
    
    @DatabaseField(generatedId = true, columnName = TASK_ID_FIELD_NAME)
    private int taskID;
    @DatabaseField(columnName = COURSE_ID_FIELD_NAME)
    private int courseID;    
    @DatabaseField(columnName = NAME_FIELD_NAME)
    private int name;
    @DatabaseField(canBeNull = false, columnName = LOCATION_FIELD_NAME)
    private String location;
    @DatabaseField(canBeNull = false, columnName = STUDENTS_FIELD_NAME)
    private int students;
    @DatabaseField(canBeNull = false, columnName = STAFF_FIELD_NAME)
    private String staff;
    @DatabaseField(canBeNull = false, columnName = NUM_STAFF_FIELD_NAME)
    private int numStaff;
    @DatabaseField(canBeNull = false, columnName = DAY_FIELD_NAME)
    private String day;
    @DatabaseField(canBeNull = false, columnName = REPEATING_FIELD_NAME)
    private String repeating;
    @DatabaseField(canBeNull = false, columnName = FREQUENCY_FIELD_NAME)
    private String frequency;
    @DatabaseField(canBeNull = true, columnName = TIME_FIELD_NAME)
    private String time;
    
    public Task(){
        
    }
    
    public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<Task, Integer> taskDao = DaoManager.createDao(conn, Task.class);
            taskDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
    public void updateInDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<Task, Integer> taskDao = DaoManager.createDao(conn, Task.class);
            taskDao.update(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
    void modifyTask(){
        
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
     * @return the name
     */
    public int getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(int name) {
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
    public String getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(String staff) {
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
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the repeating
     */
    public String getRepeating() {
        return repeating;
    }

    /**
     * @param repeating the repeating to set
     */
    public void setRepeating(String repeating) {
        this.repeating = repeating;
    }

    /**
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
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
    
}

