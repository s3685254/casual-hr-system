/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Request;

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
   
}
