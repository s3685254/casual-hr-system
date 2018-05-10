/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Request;

import CasualHRSystem.DatabaseDriver;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author jye
 */
public class CourseApplication extends Request {
    public static final String APPLICATION_ID_FIELD_NAME = "applicationID";
    public static final String RESUME_FIELD_NAME = "resume";
    @DatabaseField(generatedId=true, columnName = APPLICATION_ID_FIELD_NAME)
    int applicationID;
    @DatabaseField(columnName = RESUME_FIELD_NAME)
    String resume;
    
    public CourseApplication(){
        
    }
    
    public CourseApplication(int courseID, int userID){
        super(courseID, userID, LocalDateTime.now().toString());
    }
    
            public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<CourseApplication, Integer> courseApplicationDao = DaoManager.createDao(conn, CourseApplication.class);
            courseApplicationDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
    public void updateInDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<CourseApplication, Integer> courseApplicationDao = DaoManager.createDao(conn, CourseApplication.class);
            courseApplicationDao.update(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
}
