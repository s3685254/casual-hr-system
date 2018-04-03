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
public class Approvals extends User {
    
    Approvals(){
        super();
        this.setUserType("approvals");
    }
    
    public static void showMenu(){
        System.out.println("    (1) View Applications/Requests");
    }
}
