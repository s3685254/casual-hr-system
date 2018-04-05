/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Request;

/**
 *
 * @author jye
 */
public class StaffProposal extends Request {
    String course;
    int staffName;
    int staffIDs;
    int staffActivitiesID;
    
    public String getCourse(){
        return course;
    }
    
    public void setCourse(String course){
        this.course = course;
    }
    
    public int getStaffName(){
        return staffName;
    }
    
    public void setStaffName(int staffName){
        this.staffName = staffName;
    }
    
    public int getStaffIDs(){
        return staffIDs;
    }
    
    public void setStaffIDs(int staffIDs){
        this.staffIDs = staffIDs;
    }
    
    public int getStaffAvtivitiesID(){
        return staffActivitiesID;
    }
    
    public void setStaffActivitiesID(int staffActivitiesID){
        this.staffActivitiesID = staffActivitiesID;
    }
}
