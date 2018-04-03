/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.User;

import java.util.Date;

/**
 *
 * @author jye
 */
public class Admin extends User{
    
    Admin(){
        super();
        this.setUserType("admin");
    }
     
    public Admin(int userID, String firstName, String lastName, String email, String userType, String password, String date) {
        super(userID, firstName, lastName, email, userType, password);
        this.setDateAdded(date);
    }
    
    public static void showMenu(){
        System.out.println("    (1) Manage Payrolls");
        System.out.println("    (2) Generate Report");
        System.out.println("    (3) Add Staff");
    }
    
}
