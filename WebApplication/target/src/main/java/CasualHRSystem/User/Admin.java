/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.User;

import CasualHRSystem.DatabaseDriver;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author jye
 */
public class Admin extends User{
    
    Admin(){
        super();
        this.setUserType("admin");
    }
     
    public Admin(String firstName, String lastName, String email, String userType, String password) {
        super(firstName, lastName, email, userType, password);
    }
    
    public static void showMenu(){
        System.out.println("Please select user option set:");
        System.out.println("(1) Admin");
        System.out.println("(2) Approvals");
        System.out.println("(3) Course Coordinator");
        
        Scanner scanner = new Scanner(System.in);
        int user_type = scanner.nextInt();
        
        switch(user_type){
            case 1:
                System.out.println("    (1) Manage Payrolls");
                System.out.println("    (2) Generate Report");
                System.out.println("    (3) Add Staff");
                int optionSelected = scanner.nextInt();
                    switch(optionSelected){
                        case 1:
                            DatabaseDriver.viewPayroll();
                            break;
                        case 2:
                            DatabaseDriver.reportGeneratorMenu();
                            break;
                        case 3:
                            DatabaseDriver.addStaff();
                            break;
                    }
                break;
                
            case 2:
                Approvals.showMenu();
                break;
                
            case 3:
                CourseCoordinator.showMenu();
                break;

        }
        

    }
    
}
