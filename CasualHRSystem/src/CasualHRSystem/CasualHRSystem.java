/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem;

import CasualHRSystem.User.*;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.dao.*;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.io.Reader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


/**
 *
 * @author jye
 */
public class CasualHRSystem {

    public void createUserTable(){
        ConnectionSource conn= null;
        try {
            conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
            System.out.println("Connection to SQLite has been established.");

            
            // instantiate the dao
            Dao<User, String> accountDao = DaoManager.createDao(conn, User.class);
            TableUtils.createTable(conn, User.class);
            
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
    
    /**
     * @param args the command line arguments
     */
    
    //void queryDB(classType){
    //    Dao<User, String> accountDao = DaoManager.createDao(conn, classType.class);
    //        
    //}
    
    public static void main(String[] args) {
        ConnectionSource conn= null;
        

            
        try {
            conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
            System.out.println("Connection to SQLite has been established.");

            User currentUser;
            currentUser = DatabaseDriver.login();
            if(currentUser!=null){
                currentUser.welcomeMessage();
                currentUser.showMenu();
            } else {
                System.out.println("Incorrect Credentials.");
            }
            // instantiate the dao (database access object)
            ////TableUtils.createTable(conn, User.class);
            ////Dao<User, String> accountDao = DaoManager.createDao(conn, User.class);


            ////User user = new User(0, "Jye", "Lake", "jyelake@hotmail.com", "admin", "password");
            ////accountDao.create(user);


            ////User user2 = accountDao.queryForId("0");
            ////System.out.println("Account: " + user2.getFirstName());

            // get our query builder from the DAO


            
            ////conn.close();


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
