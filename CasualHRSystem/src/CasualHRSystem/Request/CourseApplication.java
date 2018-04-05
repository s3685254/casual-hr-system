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
public class CourseApplication extends Request {
    String resume;
    
    public String getResume(){
        return resume;
    }
    
    public void setResume(String resume){
        this.resume = resume;
    }
}
