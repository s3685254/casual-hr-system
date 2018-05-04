/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.User;

/**
 *
 * @author jye
 */
public class CasualStaffMember extends User {

    String resumeFilePath;
    
    CasualStaffMember(){
        super();
        this.setUserType("casual");
    }
    
    public CasualStaffMember(int userID, String firstName, String lastName, String email, String userType, String password, String date) {
        super(userID, firstName, lastName, email, userType, password);
        this.setDateAdded(date);
    }
    
    void uploadResume(String filePath){
        this.resumeFilePath = filePath;
    }
    
    void applyForPosition(){
        
    }
    
}
