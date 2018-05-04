/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem;

/**
 *
 * @author jye
 */
public class Payslip {
    int payslipID;
    String date;
    int hoursWorked;
    float amount;
    
    public void sendPayslip(){
        
    }
    
    public void modifyPayslip(int newHoursWorked, float newAmount){
        this.hoursWorked = newHoursWorked;
        this.amount = newAmount;
    };
    
}
