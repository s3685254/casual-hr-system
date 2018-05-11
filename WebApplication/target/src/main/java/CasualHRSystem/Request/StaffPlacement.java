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

/**
 *
 * @author jye
 */
public class StaffPlacement {
    public static final String PROPOSAL_ID_FIELD_NAME = "proposalID";
    public static final String PLACEMENT_FIELD_NAME = "placementID";
    public static final String STAFF_ID_FIELD_NAME = "staffID";
    public static final String TASK_ID_FIELD_NAME = "taskID";
    public static final String ACTIVITY_ID_FIELD_NAME = "activityID";
    @DatabaseField(generatedId=true, columnName = PLACEMENT_FIELD_NAME)
    int placementID;
    @DatabaseField(canBeNull = false, columnName = PROPOSAL_ID_FIELD_NAME)
    int proposalID;
    @DatabaseField(canBeNull = false, columnName = STAFF_ID_FIELD_NAME)
    int staffID;
    @DatabaseField(canBeNull = true, columnName = TASK_ID_FIELD_NAME)
    int taskID;
    @DatabaseField(canBeNull = true, columnName = ACTIVITY_ID_FIELD_NAME)
    int activityID;

    public StaffPlacement(){

    }
    
    public StaffPlacement(int proposalID, int staffID, int sessionID, String type){
        if(type.equals("a")){
            this.activityID = sessionID;
        } else if(type.equals("t")){
            this.taskID = sessionID;
        }
        this.proposalID = proposalID;
        this.staffID = staffID;
    }
    
    
    public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<StaffPlacement, Integer> placementDao = DaoManager.createDao(conn, StaffPlacement.class);
            placementDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
}

