/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jye
 */
public class Notification {
    private ArrayList recipients;
    private String content;
    private Date timeSent;
    
    Notification(String content){
        this.recipients = new ArrayList();
        this.content = content;
        this.timeSent = new Date();
    }
    
    void send(){
        
    }

    /**
     * @return the recipients
     */
    public ArrayList getRecipients() {
        return recipients;
    }

    /**
     * @param recipients the recipients to set
     */
    public void setRecipients(ArrayList recipients) {
        this.recipients = recipients;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the timeSent
     */
    public Date getTimeSent() {
        return timeSent;
    }

    /**
     * @param timeSent the timeSent to set
     */
    public void setTimeSent(Date timeSent) {
        this.timeSent = timeSent;
    }
    
    
    
}
