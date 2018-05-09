/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Request;

import com.j256.ormlite.field.DatabaseField;

/**
 *
 * @author jye
 */
public class Request {
    public static final String REQUEST_ID_FIELD_NAME = "courseID";
    public static final String USER_ID_FIELD_NAME = "courseCode";
    public static final String COORDINATOR_FIELD_NAME = "courseCoordinator";
    public static final String START_DATE_FIELD_NAME = "courseStartDate";
    public static final String END_DATE_FIELD_NAME = "courseEndDate";
    public static final String TIMETABLE_COLOUR_FIELD_NAME = "hexColour";
   @DatabaseField(id = true)
   int requestID;
   int userID;
   int courseID;
   String message;
   String dateSubmitted;
   Boolean pending;
   Boolean outcome;
   String responseMessage;
   
   void approveRequest(){
       this.outcome = true;
   }
   
   void declineRequest(){
       this.outcome = false;
   }
   
   void notifyApplicant(){
       //Notification(userID,content,);
   }
   
}
