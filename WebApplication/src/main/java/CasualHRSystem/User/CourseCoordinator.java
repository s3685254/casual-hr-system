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
public class CourseCoordinator extends User {
    
    public CourseCoordinator(){
        super();
        this.setUserType("course_coordinator");
    }    
        
    public CourseCoordinator(int userID, String firstName, String lastName, String email, String userType, String password, String date) {
        super(userID, firstName, lastName, email, userType, password);
        this.setDateAdded(date);
    }
    
    public static void showMenu(){
        System.out.println("    (1) Manage Requests");
        System.out.println("    (2) Assign Tasks");
        System.out.println("    (3) Add Course");
        System.out.println("    (4) Modify Course");
        System.out.println("    (5) View Timetable");
    }
}
