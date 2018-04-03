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
    
    void uploadResume(String filePath){
        this.resumeFilePath = filePath;
    }
    
    void applyForPosition(){
        
    }
    
}
