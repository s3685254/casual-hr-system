/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem;

import CasualHRSystem.User.*;
import CasualHRSystem.Course.*;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.dao.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import java.io.Reader;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;


/**
 *
 * @author jye
 */
public class CasualHRSystem {
    
    static String VERSION_NUMBER = "0.1";
    
    /**
     * @param args the command line arguments
     */
    
    //void queryDB(classType){
    //    Dao<User, String> accountDao = DaoManager.createDao(conn, classType.class);
    //        
    //}
    
    public static void main(String[] args) {
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR"); // Turn off the debug messages...
        File file = new File("chrsDB.db");
        boolean fileCreated= false;
        if(!file.exists()){
            try{
          DatabaseDriver.initDB();
          fileCreated = file.createNewFile();
            } catch( IOException e) {
                System.out.println("Could not create database, aborting!");
                System.exit(0);
            }
        };
        

        
       
        ConnectionSource conn= null;
        

        System.out.println("--Welcome to Casual HR System (v. " + VERSION_NUMBER + ") --");
            
       
            User currentUser;
            currentUser = DatabaseDriver.login();
            if(currentUser!=null){
                currentUser.welcomeMessage();
                Scanner scanner = new Scanner(System.in);
                while(true){
                    System.out.println("Please select an option below:");
                    System.out.println("  (1) Add User/Staff");
                    System.out.println("  (2) View Staff");
                    System.out.println("  (3) Create Course");
                    System.out.println("  (4) View Courses");
                    System.out.println("  (5) Exit");
                    int optionChosen = scanner.nextInt();
                    if(optionChosen==1){
                        DatabaseDriver.addStaff();
                    } else if(optionChosen==2){
                        DatabaseDriver.viewStaff();
                    } else if(optionChosen==3){
                        CourseDriver.createCourse();
                    } else if(optionChosen==4){
                        DatabaseDriver.viewCourses();
                    } else if(optionChosen==5){
                        System.exit(0);
                    } else {
                        System.out.println("Invalid selection, please try again.");
                    }
                }                
            } else {
                System.out.println("Incorrect Credentials.");
            }

    }

}
