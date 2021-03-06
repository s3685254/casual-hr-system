/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.User;

import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.dao.DaoManager;
import java.util.*;
import sun.util.calendar.BaseCalendar;

/**
 *
 * @author jye
 */


@DatabaseTable(tableName = "users")
public class User {
    public static final String EMAIL_FIELD_NAME = "email";
    
    @DatabaseField(id = true)
    public int userID;
    @DatabaseField(canBeNull = false)
    private String firstName;
    @DatabaseField(canBeNull = false)
    private String lastName;
    @DatabaseField(canBeNull = false, columnName = EMAIL_FIELD_NAME)
    public String email;
    @DatabaseField(canBeNull = false)
    private String userType;
    @DatabaseField(canBeNull = false)
    private String password;
    @DatabaseField(canBeNull = false)
    private String dateAdded;
    
    /* DO NOT MODIFY: the ORM needs an empty constructor to work properly! */
    public User() {
    }

    public User(int userID, String firstName, String lastName, String email, String userType, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
        this.password = password;
        this.dateAdded = new Date().toString();
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * @param userType the userType to set
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * @return the dateAdded
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     * @param dateAdded the dateAdded to set
     */
        public void setDateAdded(String date) {
        this.dateAdded = date;
    }
    
    public void setDateAdded() {
        this.dateAdded = new Date().toString();
    }
    
    public void welcomeMessage(){
        System.out.println("Welcome " + this.getFirstName() + ". You are now logged in.");
    }
    
    public static void showMenu(){
        System.out.println("  Please Select an Option:");
    }
    
}

