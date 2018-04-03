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
public class Admin extends User{
    
    Admin(){
        super();
        this.setUserType("admin");
    }
     
    public void showMenu(){
        System.out.println("    (1) Manage Payrolls");
        System.out.println("    (2) Generate Report");
        System.out.println("    (3) Add Staff");
    }
    
}
