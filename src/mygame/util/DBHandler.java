/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.util;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Israel
 */
public  class DBHandler {
    
    Connection c = null;
    Statement stmt = null;
    boolean user_Is_Valid =false;
    
    public  DBHandler() {
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:User.db");
        System.out.println("Opened database successfully");

        stmt = c.createStatement();
        
        String sql = "CREATE TABLE USER_TABLE " +
                     "( ID INTEGER PRIMARY KEY   AUTOINCREMENT," +
                     " NAME    TEXT   NOT NULL, " + 
                     " AGE       INT     NOT NULL); " ; 
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        //System.exit(0);
      }
      System.out.println("Table created successfully");
    }
    
    public void register(String name,int age){
        try {
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:User.db");
        System.out.println("Opened database successfully");

        
        stmt = c.createStatement();
        
        String sql = "Insert INTO  USER_TABLE('NAME',AGE)  VALUES ( '"+name+"',"+age+");"; 
        stmt.executeUpdate(sql);
        stmt.close();
        c.close();
      } catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.out.println("oooooppppsss");
        //System.exit(0);
      }
      System.out.println("Table inserted successfully");
    
    }
    
    public void LoginUser(String UserName,int age){
         user_Is_Valid=false;
        System.out.println("Got"+UserName+age);
       
        try{
            
            Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:User.db");
        System.out.println("Opened database successfully");

        
            ResultSet rs = stmt.executeQuery("select * from USER_TABLE;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("NAME"));
                System.out.println("age= " + rs.getString("AGE"));
                System.out.println("valid-->"+String.valueOf(user_Is_Valid));
                if(rs.getString("NAME").equals(UserName) && rs.getString("AGE").equals(String.valueOf(age)))
                {
                    System.out.println("Valid User");
                    user_Is_Valid=true;
                        if(user_Is_Valid==true){
                            
                            break;
                        }
                }
            }
            rs.close();
            c.close();
        }catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.out.println("oooooppppsss");
        //System.exit(0);
      }
        
        
    }
     public ArrayList<String> getAllUsers(){
       ArrayList<String> names=new ArrayList<String>();
        try{
            
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:User.db");
            System.out.println("Opened database successfully");
            ResultSet rs = stmt.executeQuery("select * from USER_TABLE;");
            while (rs.next()) {
                System.out.println("name = " + rs.getString("NAME"));
                names.add(rs.getString("NAME"));
                
            }
            rs.close();
            c.close();
        }catch ( Exception e ) {
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.out.println("oooooppppsss");
         }
        return names;
    }
    
            
  }
    
    
    

