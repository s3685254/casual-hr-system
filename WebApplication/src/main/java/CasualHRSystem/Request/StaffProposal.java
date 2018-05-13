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
public class StaffProposal extends Request {
    public static final String PROPOSAL_ID_FIELD_NAME = "proposalID";
    public static final String COURSE_NAME_FIELD_NAME = "courseName";
    @DatabaseField(generatedId=true, columnName = PROPOSAL_ID_FIELD_NAME)
    private int proposalID;
    @DatabaseField(columnName = COURSE_NAME_FIELD_NAME)
    private String courseName;
        
    public StaffProposal(){
        
    }
    
    public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<StaffProposal, Integer> proposalDao = DaoManager.createDao(conn, StaffProposal.class);
            proposalDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
            public void updateInDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<StaffProposal, Integer> staffProposalDao = DaoManager.createDao(conn, StaffProposal.class);
            staffProposalDao.update(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
    public StaffProposal(int coordinatorID, String courseName){
       super.setUserID(coordinatorID);
       super.setPending(true);
       this.courseName = courseName;
    }

    /**
     * @return the proposalID
     */
    public int getProposalID() {
        return proposalID;
    }

    /**
     * @param proposalID the proposalID to set
     */
    public void setProposalID(int proposalID) {
        this.proposalID = proposalID;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
