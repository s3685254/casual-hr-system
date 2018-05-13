
package CasualHRSystem.Request;

import CasualHRSystem.DatabaseDriver;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;

public class StaffPlacement {
    public static final String PROPOSAL_ID_FIELD_NAME = "proposalID";
    public static final String COURSE_ID_FIELD_NAME = "courseID";
    public static final String COURSE_NAME_FIELD_NAME = "courseName";
    public static final String PLACEMENT_FIELD_NAME = "placementID";
    public static final String APPLICATION_ID_FIELD_NAME = "applicationID";
    public static final String STAFF_ID_FIELD_NAME = "staffID";
    public static final String TASK_ID_FIELD_NAME = "taskID";
    public static final String ACTIVITY_ID_FIELD_NAME = "activityID";
    public static final String PENDING_FIELD_NAME = "pending";
    public static final String APPROVED_FIELD_NAME = "approved";
    @DatabaseField(generatedId=true, columnName = PLACEMENT_FIELD_NAME)
    private int placementID;
    @DatabaseField(canBeNull = false, columnName = COURSE_ID_FIELD_NAME)
    private int courseID;
    @DatabaseField(canBeNull = false, columnName = COURSE_NAME_FIELD_NAME)
    private String courseName;
    @DatabaseField(canBeNull = false, columnName = PROPOSAL_ID_FIELD_NAME)
    private int proposalID;
    @DatabaseField(canBeNull = false, columnName = APPLICATION_ID_FIELD_NAME)
    private int applicationID;
    @DatabaseField(canBeNull = false, columnName = STAFF_ID_FIELD_NAME)
    private int staffID;
    @DatabaseField(canBeNull = true, columnName = TASK_ID_FIELD_NAME)
    private int taskID;
    @DatabaseField(canBeNull = true, columnName = ACTIVITY_ID_FIELD_NAME)
    private int activityID;
    @DatabaseField(canBeNull = true, columnName = PENDING_FIELD_NAME)
    private boolean pending;
    @DatabaseField(canBeNull = true, columnName = APPROVED_FIELD_NAME)
    private boolean approved;
    
    public StaffPlacement(){

    }
    
    public StaffPlacement(int courseID, String courseName, int proposalID, int applicationID, int staffID, int sessionID, String type){
        if(type.equals("a")){
            this.activityID = sessionID;
        } else if(type.equals("t")){
            this.taskID = sessionID;
        }
        
        this.courseID = courseID;
        this.courseName = courseName;
        this.proposalID = proposalID;
        this.staffID = staffID;
        this.pending = true;
        this.approved = false;
        this.applicationID = applicationID;
    }
    
    
    public void addToDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<StaffPlacement, Integer> placementDao = DaoManager.createDao(conn, StaffPlacement.class);
            placementDao.create(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
    }

        public void updateInDB(){
        ConnectionSource conn = DatabaseDriver.connectToDB(DatabaseDriver.dbLoc);
        try{
            Dao<StaffPlacement, Integer> staffPlacementDao = DaoManager.createDao(conn, StaffPlacement.class);
            staffPlacementDao.update(this);
        } catch (SQLException e){
            System.out.println(e.getCause());
        }
        DatabaseDriver.disconnectFromDB(conn);
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
     * @return the applicationID
     */
    public int getApplicationID() {
        return applicationID;
    }

    /**
     * @param applicationID the applicationID to set
     */
    public void setApplicationID(int applicationID) {
        this.applicationID = applicationID;
    }

    /**
     * @return the staffID
     */
    public int getStaffID() {
        return staffID;
    }

    /**
     * @param staffID the staffID to set
     */
    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    /**
     * @return the taskID
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * @param taskID the taskID to set
     */
    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    /**
     * @return the activityID
     */
    public int getActivityID() {
        return activityID;
    }

    /**
     * @param activityID the activityID to set
     */
    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }

    /**
     * @return the pending
     */
    public boolean isPending() {
        return pending;
    }

    /**
     * @param pending the pending to set
     */
    public void setPending(boolean pending) {
        this.pending = pending;
    }

    /**
     * @return the approved
     */
    public boolean isApproved() {
        return approved;
    }

    /**
     * @param approved the approved to set
     */
    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    /**
     * @return the placementID
     */
    public int getPlacementID() {
        return placementID;
    }

    /**
     * @param placementID the placementID to set
     */
    public void setPlacementID(int placementID) {
        this.placementID = placementID;
    }

    /**
     * @return the courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
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

