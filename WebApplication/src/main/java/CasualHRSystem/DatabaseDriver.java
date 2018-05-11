/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem;

import CasualHRSystem.Course.Course;
import CasualHRSystem.Course.Task;
import CasualHRSystem.Course.Activity;
import CasualHRSystem.User.User;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.Where;
import java.io.File;
import java.util.List;
import java.util.Scanner;
import CasualHRSystem.Request.*;
import java.sql.SQLException;

/**
 *
 * @author jye
 */
public class DatabaseDriver {
    
    public final static String dbLoc = "chrsDB.db";

    public static void initDB(String dbLoc){
        ConnectionSource conn = null;
        try{
            conn = new JdbcConnectionSource("jdbc:sqlite:"+dbLoc);
            File db = new File(dbLoc);
            TableUtils.createTable(conn, User.class);
            TableUtils.createTable(conn, Course.class);
            TableUtils.createTable(conn, Activity.class);
            TableUtils.createTable(conn, CourseApplication.class);
            TableUtils.createTable(conn, StaffProposal.class);
            TableUtils.createTable(conn, StaffPlacement.class);
            TableUtils.createTable(conn, Task.class);
            
            /*
            TableUtils.createTable(conn, VariationRequest.class);
            */
            
            
            Dao<User, Integer> accountDao = DaoManager.createDao(conn, User.class);

            User newUser = new User("Admin", "Admin", "admin@example.com", "admin", "admin");
            accountDao.create(newUser);
            
            conn.close();
            
               } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    
    }
    
    public static ConnectionSource connectToDB(String db){
        ConnectionSource conn = null;
        try {
            conn = new JdbcConnectionSource("jdbc:sqlite:"+db);
            return conn;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

    public static void disconnectFromDB(ConnectionSource conn){
        try{
            conn.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    public static User login(String emailToCheck, String passwordToCheck){
        
    ConnectionSource conn = null;
        try{
            
            conn = new JdbcConnectionSource("jdbc:sqlite:"+dbLoc);
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

         conn = new JdbcConnectionSource("jdbc:sqlite:"+dbLoc);
         
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void viewStaff(){
        ConnectionSource conn = null;
                try{

         conn = new JdbcConnectionSource("jdbc:sqlite:"+dbLoc);
         
        Dao<User, Integer> accountDao = DaoManager.createDao(conn, User.class);
        QueryBuilder<User, Integer> userQuery = accountDao.queryBuilder();
        userQuery.orderBy("userID", false).prepare();
        PreparedQuery<User> preparedQuery = userQuery.prepare();

        List<User> accountList = accountDao.query(preparedQuery);
        
        for(User i:accountList){
            System.out.println("User #" + i.getUserID() + ": " + i.getFirstName() + " " + i.getLastName() + " | " + i.getUserType());
        }
        
            conn.close();
        
      } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void addCourse(){
        ConnectionSource conn=connectToDB(DatabaseDriver.dbLoc);
        
    }
    
    /*public static Course getCourse(int courseID){
        ConnectionSource conn = null;
        try{

         conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
         
        Dao<Course, Integer> courseDao = DaoManager.createDao(conn, Course.class);
        QueryBuilder<Course, Integer> courseQuery = courseDao.queryBuilder();
        courseQuery.orderBy("courseID", false).prepare();
        PreparedQuery<Course> preparedQuery = courseQuery.prepare();
        

        List<Course> courseList = courseDao.query(preparedQuery);
        
        Course courseChosen=null;
        
        for(Course i:courseList){
            if(i.getCourseID()==courseID){
                courseChosen = i;
                break;
            }
        }
        
        conn.close();
        return courseChosen;

        
      } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    
    }
    */
    
    public static Course getCourse(int courseID){
        ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Course, Integer> courseDao = DaoManager.createDao(conn, Course.class);
            QueryBuilder<Course, Integer> courseQuery = courseDao.queryBuilder();
            Where where = courseQuery.where();
            where.eq(Course.ID_FIELD_NAME, courseID);
            PreparedQuery<Course> preparedQuery = courseQuery.prepare();
            disconnectFromDB(conn);
            return courseDao.query(preparedQuery).get(0);
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public static Course getCourse(String name){
        ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        System.out.println(name);
        try{
            Dao<Course, String> courseDao = DaoManager.createDao(conn, Course.class);
            QueryBuilder<Course, String> courseQuery = courseDao.queryBuilder();
            Where where = courseQuery.where();
            where.eq(Course.NAME_FIELD_NAME, name);
            PreparedQuery<Course> preparedQuery = courseQuery.prepare();
            disconnectFromDB(conn);
            return courseDao.query(preparedQuery).get(0);
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public static List<Course> getCourses(){
         ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Course, Integer> courseDao = DaoManager.createDao(conn, Course.class);
            QueryBuilder<Course, Integer> courseQuery = courseDao.queryBuilder();
            courseQuery.orderBy("courseID", false).prepare();
            PreparedQuery<Course> preparedQuery = courseQuery.prepare();
            disconnectFromDB(conn);
            List<Course> courseList = courseDao.query(preparedQuery);
            return courseList;
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }

            public static Task getTask(int taskID){
        ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Task, Integer> taskDao = DaoManager.createDao(conn, Task.class);
            QueryBuilder<Task, Integer> taskQuery = taskDao.queryBuilder();
            Where where = taskQuery.where();
            where.eq(Task.TASK_ID_FIELD_NAME, taskID);
            PreparedQuery<Task> preparedQuery = taskQuery.prepare();
            disconnectFromDB(conn);
            return taskDao.query(preparedQuery).get(0);
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    
    public static List<Task> getTasks(int courseID){
         ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Task, Integer> taskDao = DaoManager.createDao(conn, Task.class);
            QueryBuilder<Task, Integer> taskQuery = taskDao.queryBuilder();
            Where where = taskQuery.where();
            where.eq(Task.COURSE_ID_FIELD_NAME, courseID);
            taskQuery.orderBy("taskID", false).prepare();
            PreparedQuery<Task> preparedQuery = taskQuery.prepare();
            disconnectFromDB(conn);
            List<Task> taskList = taskDao.query(preparedQuery);
            return taskList;
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
        public static Activity getActivity(int activityID){
        ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Activity, Integer> activityDao = DaoManager.createDao(conn, Activity.class);
            QueryBuilder<Activity, Integer> activityQuery = activityDao.queryBuilder();
            Where where = activityQuery.where();
            where.eq(Activity.ID_FIELD_NAME, activityID);
            PreparedQuery<Activity> preparedQuery = activityQuery.prepare();
            disconnectFromDB(conn);
            return activityDao.query(preparedQuery).get(0);
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    
    public static List<Activity> getActivities(int courseID){
         ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<Activity, Integer> activityDao = DaoManager.createDao(conn, Activity.class);
            QueryBuilder<Activity, Integer> activityQuery = activityDao.queryBuilder();
            Where where = activityQuery.where();
            where.eq(Activity.COURSE_ID_FIELD_NAME, courseID);
            activityQuery.orderBy("activityID", false).prepare();
            PreparedQuery<Activity> preparedQuery = activityQuery.prepare();
            disconnectFromDB(conn);
            List<Activity> activityList = activityDao.query(preparedQuery);
            return activityList;
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
        public static CourseApplication getApplication(int courseApplicationID){
        ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<CourseApplication, Integer> courseApplicationDao = DaoManager.createDao(conn, CourseApplication.class);
            QueryBuilder<CourseApplication, Integer> courseApplicationQuery = courseApplicationDao.queryBuilder();
            Where where = courseApplicationQuery.where();
            where.eq(CourseApplication.APPLICATION_ID_FIELD_NAME, courseApplicationID);
            PreparedQuery<CourseApplication> preparedQuery = courseApplicationQuery.prepare();
            disconnectFromDB(conn);
            return courseApplicationDao.query(preparedQuery).get(0);
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
        public static List<CourseApplication> getApplications(int courseID){
         ConnectionSource conn = connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<CourseApplication, Integer> courseApplicationDao = DaoManager.createDao(conn, CourseApplication.class);
            QueryBuilder<CourseApplication, Integer> courseApplicationQuery = courseApplicationDao.queryBuilder();
            Where where = courseApplicationQuery.where();
            where.eq(Activity.COURSE_ID_FIELD_NAME, courseID);
            courseApplicationQuery.orderBy("applicationID", false).prepare();
            PreparedQuery<CourseApplication> preparedQuery = courseApplicationQuery.prepare();
            disconnectFromDB(conn);
            List<CourseApplication> courseApplicationList = courseApplicationDao.query(preparedQuery);
            return courseApplicationList;
        } catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
    
    public static List<Request> getRequests(int userId){
        return null;
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

