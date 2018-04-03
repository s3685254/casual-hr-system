/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CasualHRSystem;

import CasualHRSystem.User.User;
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
public class DatabaseDriver {
    
    public int currentUser;
    
    public void login(){
        
    ConnectionSource conn = null;
        try{
            
            conn = new JdbcConnectionSource("jdbc:sqlite:chrsDB.db");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Login");
            System.out.print("Email: ");
            String emailToCheck = scanner.next();
            System.out.println();
            System.out.print("Password: ");
            String passwordToCheck = scanner.next();
            Dao<User, String> accountDao = DaoManager.createDao(conn, User.class);
        QueryBuilder<User, String> userQuery = accountDao.queryBuilder();
      // the 'password' field must be equal to "qwerty"
      userQuery.where().eq(User.EMAIL_FIELD_NAME, emailToCheck);
      // prepare our sql statement
      PreparedQuery<User> preparedQuery = userQuery.prepare();
      // query for all accounts that have "qwerty" as a password

        List<User> accountList = accountDao.query(preparedQuery);
        for(User i:accountList){
            if(passwordToCheck.equals(i.getPassword())){
               currentUser = i.getUserID();
               i.showMenu();
           }
        }
      } catch (Exception e) {
            System.out.println(e.getMessage());
      }  finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    
}
