/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Request;

import java.util.Date;

/**
 *
 * @author jye
 */
public class Request {
   int requestID;
   int userID;
   String type;
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
   
   public Request(int requestID, int userID, String type, int courseID, String message, String dateSubmitted, Boolean pending){
       this.requestID = requestID;
       this.userID = userID;
       this.type = type;
       this.courseID = courseID;
       this.message = message;
       this.dateSubmitted = dateSubmitted;
       this.pending = true;
       this.dateSubmitted = new Date().toString();
   }
   
   public int getRequestID(){
       return requestID;
   }
   
   public void setRequestID(int requestID){
       this.requestID = requestID;
   }
   
   public int getUserID(){
       return userID;
   }
   
   public void setUserID(int userID){
       this.userID = userID;
   }
   
   public String getType(){
       return type;
   }
   
   public void setType(String type){
       this.type = type;
   }
   
   public int getCourseID(){
       return courseID;
   }
   
   public void setCourseID(int courseID){
       this.courseID = courseID;
   }
   
   public String getMessage(){
       return message;
   }
   
   public void setMessage(String message){
       this.message = message;
   }
   
   public String getDateSubmmited(){
       return dateSubmitted;
   }
   
   public void setDateSubmitted(String dateSubmitted){
       this.dateSubmitted = date;
   }
   
   public void setDateSubmitted() {
        this.dateSubmitted = new Date().toString();
    }
   
   public String getResponseMessage(){
       return responseMessage;
   }
   
   public void setResponseMessage(String responseMessage){
       this.responseMessage = responseMessage;
   }
}
