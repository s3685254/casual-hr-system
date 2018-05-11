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
    @DatabaseField(generatedId=true, columnName = PROPOSAL_ID_FIELD_NAME)
    private int proposalID;

    public StaffProposal(){
        
    }
    
    public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB("chrsDB.db");
        try{
            Dao<StaffProposal, Integer> proposalDao = DaoManager.createDao(conn, StaffProposal.class);
            proposalDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }
    
    public StaffProposal(int coordinatorID){
       super.setUserID(coordinatorID);
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

}
