/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Course;

import CasualHRSystem.Course.Course;
import CasualHRSystem.User.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jye
 */
public class CourseDriver {
    /*
    public static void createCourse(){
            ConnectionSource conn = null;
            try{
                conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
                Dao<Course, Integer> courseDao = DaoManager.createDao(conn, Course.class);
                System.out.println("Please enter the name of the course you wish to add:");
                Scanner scanner = new Scanner(System.in);
                String courseName = scanner.next();
  
                
            QueryBuilder<Course, Integer> courseQuery = courseDao.queryBuilder();
            courseQuery.orderBy("courseID", false).limit(1L).prepare();
            PreparedQuery<Course> preparedQuery = courseQuery.prepare();

        
        
        List<Course> courseList = courseDao.query(preparedQuery);
        
        if(courseList.isEmpty()){
                        Course newCourse = new Course(0, courseName);
                courseDao.create(newCourse);
  
        } else {
            Course newCourse = new Course(courseList.get(0).getCourseID()+1, courseName);
                courseDao.create(newCourse);
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
    
    }
*/
}

