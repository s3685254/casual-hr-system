/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem;

import CasualHRSystem.User.User;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import CasualHRSystem.Course.*;
import CasualHRSystem.Request.*;

/**
 *
 * @author jye
 */
public class DatabaseDriver {
    
    public static void initDB(){
        ConnectionSource conn = null;
        try{
            conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
            File db = new File("chrsDB.db");
            TableUtils.createTable(conn, User.class);
            TableUtils.createTable(conn, Course.class);
            /*
            TableUtils.createTable(conn, Task.class);
            TableUtils.createTable(conn, Activity.class);
            TableUtils.createTable(conn, CourseApplication.class);
            TableUtils.createTable(conn, VariationRequest.class);
            TableUtils.createTable(conn, StaffProposal.class
            */
            
            Dao<User, Integer> accountDao = DaoManager.createDao(conn, User.class);

            User newUser = new User(0, "Admin", "Admin", "admin@example.com", "admin", "admin");
            accountDao.create(newUser);
            
            conn.close();
            
               } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    
    }
    
    public static User login(){
        
    ConnectionSource conn = null;
        try{
            
            conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Login");
            System.out.print("Email: ");
            String emailToCheck = scanner.next();
            System.out.println();
            System.out.print("Password: ");
            String passwordToCheck = scanner.next();
            Dao<User, String> accountDao = DaoManager.createDao(conn, User.class);
        QueryBuilder<User, String> userQuery = accountDao.queryBuilder();
      // the 'password' field must be equal to "qwerty"
      userQuery.where().eq(User.EMAIL_FIELD_NAME, emailToCheck);
      // prepare our sql statement
      PreparedQuery<User> preparedQuery = userQuery.prepare();
      // query for all accounts that have "qwerty" as a password

        List<User> accountList = accountDao.query(preparedQuery);
        for(User i:accountList){
            if(passwordToCheck.equals(i.getPassword())){
               return i;
           }
        }
      } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }
    
    public static void addStaff(){
        User newUser = new User();
            ConnectionSource conn = null;
        Scanner scanner = new Scanner(System.in);
                try{

         conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
         
        Dao<User, Integer> accountDao = DaoManager.createDao(conn, User.class);
        QueryBuilder<User, Integer> userQuery = accountDao.queryBuilder();
        userQuery.orderBy("userID", false).limit(1L).prepare();
        PreparedQuery<User> preparedQuery = userQuery.prepare();

        List<User> accountList = accountDao.query(preparedQuery);
            
        newUser.setUserID(accountList.get(0).userID+1);
         
        System.out.println("Please enter the details of the new user:");
        System.out.print("First Name: ");
        newUser.setFirstName(scanner.next());        
        System.out.println();
        System.out.print("Surname: ");
        newUser.setLastName(scanner.next());         
        System.out.println();
        System.out.print("Email Address: ");
        newUser.setEmail(scanner.next());        
        System.out.println();
        System.out.print("Password: ");
        newUser.setPassword(scanner.next());         
        System.out.println();
        System.out.print("User Type (1 for admin, 2 for approvals, "
                + "3 for course coordinator, 4 for casual staff member): ");
        
        int user_type = scanner.nextInt();
        switch(user_type){
            case 1:
                newUser.setUserType("admin");
                break;
                
            case 2:
                newUser.setUserType("approvals");
                break;
                
            case 3:
                newUser.setUserType("course_coordinator");
                break;
                
            case 4:
                newUser.setUserType("admin");
                break;
        }

        newUser.setDateAdded();
        accountDao.create(newUser);
        conn.close();
        
      } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
        
    
    public static void viewStaff(){
            ConnectionSource conn = null;
        Scanner scanner = new Scanner(System.in);
                try{

         conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
         
        Dao<User, Integer> accountDao = DaoManager.createDao(conn, User.class);
        QueryBuilder<User, Integer> userQuery = accountDao.queryBuilder();
        userQuery.orderBy("userID", false).prepare();
        PreparedQuery<User> preparedQuery = userQuery.prepare();

        List<User> accountList = accountDao.query(preparedQuery);
        
        for(User i:accountList){
            System.out.println("User #" + i.getUserID() + ": " + i.getFirstName() + " " + i.getLastName());
        }
        
            conn.close();
        
      } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public static void viewPayroll(){
        System.out.println("Sorry, this feature is not yet implemented.");
    }
    
    public static void reportGeneratorMenu(){
        System.out.println("Sorry, this feature is not yet implemented.");
    }
    
    public static void generateReport(){
        
    }
    
}

