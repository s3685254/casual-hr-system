/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem.Request;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.PreparedQuery;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jye
 */
public class RequestDriver {
    
//<<<<<<< HEAD
    public static void showMenu(){
        System.out.println("    (1) Create Request");
        System.out.println("    (1) View Request");
        System.out.println("    (2) Approve Request");
        System.out.println("    (3) Decline Request");
        System.out.println("    (4) Notify Applicant");
    }
    
//    public static void createRequest(){
//        Request newRequest = new Request();
//        ConnectionSource conn = null;
//        Scanner scanner = new Scanner(System.in);
//        
//        try{
//            conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
//            
//            Dao<Request, Integer> accountDao = DaoManager.createDao(conn, Request.class);
//            QueryBuilder<Request, Integer> requestQuery = accountDao.queryBuilder();
//            requestQuery.orderBy("requestID", false).limit(1L).prepare();
//            PreparedQuery<Request> preparedQuery = requestQuery.prepare();
//            
//            List<Request> accountList = accountDao.query(preparedQuery);
//            
//            newRequest.setRequestID(accountList.get(0).requestID+1);
//            
//            System.out.println("Please enter the details of the new request:");
//            System.out.print("User ID: ");
//            newRequest.setMessage(scanner.next());
//            System.out.println();
//            System.out.print("Course ID: ");
//            newRequest.setMessage(scanner.next());
//            System.out.println();
//            System.out.print("Message: ");
//            newRequest.setMessage(scanner.next());         
//            System.out.println();
//            System.out.print("User Type (1 for course application, 2 for request, "
//                + "3 for staff proposal, 4 for variation request): ");
//        
//            int user_type = scanner.nextInt();
//            switch(user_type){
//                case 1:
//                    newRequest.setType("course application");
//                    break;
//
//                case 2:
//                    newRequest.setType("request");
//                    break;
//
//                case 3:
//                    newRequest.setType("staff proposal");
//                    break;
//
//                case 4:
//                    newRequest.setType("variation request");
//                    break;
//            }
//            
//            newRequest.setDateSubmitted();
//            accountDao.create(newRequest);
//            conn.close();
//            
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//      } finally {
//            try {
//                if (conn != null) {
//                    conn.close();
//                }
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//        }
//    }
    
    public static void viewRequest(){
        
    }
    
    public static void notifyApplicant(){
//=======
//    void createRequest(){
//        
//    }
//    
//    void viewRequests(){
//        
//    }
//    
//    void generateReport(){
////>>>>>>> master
//        
//    }
    
    }
}