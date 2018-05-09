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
    
    public CasualStaffMember(String firstName, String lastName, String email, String userType, String password) {
        super(firstName, lastName, email, userType, password);
    }
    
    void uploadResume(String filePath){
        this.resumeFilePath = filePath;
    }
    
    void applyForPosition(){
        
    }
    
}
