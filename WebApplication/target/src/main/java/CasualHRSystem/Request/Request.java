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
    public static final String REQUEST_ID_FIELD_NAME = "requestID";
    public static final String USER_ID_FIELD_NAME = "userID";
    public static final String COURSE_ID_FIELD_NAME = "courseID";
    public static final String MESSAGE_FIELD_NAME = "message";
    public static final String DATE_FIELD_NAME = "date";
    public static final String PENDING_FIELD_NAME = "pending";
    public static final String OUTCOME_FIELD_NAME = "outcome";
    public static final String RESPONSE_FIELD_NAME = "response";
   @DatabaseField(columnName = REQUEST_ID_FIELD_NAME)
   private int requestID;
   @DatabaseField(columnName = USER_ID_FIELD_NAME)
   private int userID;
   @DatabaseField(columnName = COURSE_ID_FIELD_NAME)
   private int courseID;
   @DatabaseField(columnName = MESSAGE_FIELD_NAME)
   private String message;
   @DatabaseField(columnName = DATE_FIELD_NAME)
   private String dateSubmitted;
   @DatabaseField(columnName = PENDING_FIELD_NAME)
   private Boolean pending;
   @DatabaseField(columnName = OUTCOME_FIELD_NAME)
   private Boolean outcome;
   @DatabaseField(columnName = RESPONSE_FIELD_NAME)
   private String responseMessage;
   
   public Request(){
       
   }
   
   public Request(int courseID, int userID, String dateSubmitted){
       this.courseID = courseID;
       this.userID = userID;
       this.dateSubmitted = dateSubmitted;
       this.pending = true;
       this.outcome = false;
   }
   
   void approveRequest(){
       this.setOutcome((Boolean) true);
   }
   
   void declineRequest(){
       this.setOutcome((Boolean) false);
   }
   
   void notifyApplicant(){
       //Notification(userID,content,);
   }

    /**
     * @return the requestID
     */
    public int getRequestID() {
        return requestID;
    }

    /**
     * @param requestID the requestID to set
     */
    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    /**
     * @return the userID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return the courseID
     */
    public int getCourseID() {
        return courseID;
    }

    /**
     * @param courseID the courseID to set
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the dateSubmitted
     */
    public String getDateSubmitted() {
        return dateSubmitted;
    }

    /**
     * @param dateSubmitted the dateSubmitted to set
     */
    public void setDateSubmitted(String dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    /**
     * @return the pending
     */
    public Boolean getPending() {
        return pending;
    }

    /**
     * @param pending the pending to set
     */
    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    /**
     * @return the outcome
     */
    public Boolean getOutcome() {
        return outcome;
    }

    /**
     * @param outcome the outcome to set
     */
    public void setOutcome(Boolean outcome) {
        this.outcome = outcome;
    }

    /**
     * @return the responseMessage
     */
    public String getResponseMessage() {
        return responseMessage;
    }

    /**
     * @param responseMessage the responseMessage to set
     */
    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
   
}
