/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.User;

import java.util.Scanner;

/**
 *
 * @author jye
 */
public class Approvals extends User {
    
    Approvals(){
        super();
        this.setUserType("approvals");
    }
    
    public Approvals(int userID, String firstName, String lastName, String email, String userType, String password, String date) {
        super(userID, firstName, lastName, email, userType, password);
        this.setDateAdded(date);
    }
    
    public static void showMenu(){
        System.out.println("    (1) View Applications/Requests");
    }
}
