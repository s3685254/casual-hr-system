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
          fileCreated = file.createNewFile();
            } catch( IOException e) {
                System.out.println("Could not create database, aborting!");
            }
        };
        
        DatabaseDriver.initDB();
        
       
        ConnectionSource conn= null;
        
        System.out.println("--Welcome to Casual HR System (v. " + VERSION_NUMBER + ") --");
            
        try {
            conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
            //System.out.println("Connection to SQLite has been established.");

            User currentUser;
            currentUser = DatabaseDriver.login();
            if(currentUser!=null){
                currentUser.welcomeMessage();
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please select an option below:");
                System.out.println("  (1) Add User/Staff");
                System.out.println("  (2) View Staff");
                System.out.println("  (3) Create Course");
                int optionChosen = scanner.nextInt();
                if(optionChosen==1){
                    DatabaseDriver.addStaff();
                } else if(optionChosen==2){
                    DatabaseDriver.viewStaff();
                } else if(optionChosen==3){
                    CourseDriver.createCourse();
                } else {
                    System.out.println("Invalid selection, please try again.");
                }
                
            } else {
                System.out.println("Incorrect Credentials.");
            }
            // instantiate the dao (database access object)
            ////TableUtils.createTable(conn, User.class);
            ///Dao<User, String> accountDao = DaoManager.createDao(conn, User.class);


            ////User user = new User(2, "Jye", "Lake", "jyelake2@hotmail.com", "admin", "password");
            ////accountDao.create(user);


            ////User user2 = accountDao.queryForId("0");
            ////System.out.println("Account: " + user2.getFirstName());

            // get our query builder from the DAO


      // query for all accounts that have "qwerty" as a password

            conn.close();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        
       }
    
}
