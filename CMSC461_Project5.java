import java.util.Scanner;
import java.sql.*;

public class CMSC461_Project5{
   public static void main(String []args){
      Scanner scan = new Scanner(System.in);
      String userID = "";
      String password = "";
      
      System.out.println("Welcome. Please enter your USERNAME and PASSWORD.");
      System.out.print("USERNAME: ");
      userID = scan.next();
      System.out.print("PASSWORD: ");
      password = scan.next();
      JDBC(userID, password);
   }
   
   public static void JDBC(String userID, String password){
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",userID,password);
         Statement stmt = conn.createStatement();
         System.out.println("Connection Established!");
         System.out.println("\nQuery 1: Selection");
         try{
            System.out.println("Name-Course_Name-Section_ID-Semester-" + 
                               "Year-Building-Room#");
            System.out.println("--------------------------------------------------------" + 
                               "-----------------------");
            String sql1 = "SELECT distinct INSTRUCTOR.NAME, TEACHES.COURSE_ID, TEACHES.SEC_ID," + 
                          " TEACHES.SEMESTER, TEACHES.YEAR, SECTION.BUILDING, SECTION.ROOM_NUMBER" +
                          " from INSTRUCTOR join TEACHES on TEACHES.ID = INSTRUCTOR.ID join SECTION" + 
                          " on TEACHES.COURSE_ID = SECTION.COURSE_ID" +
                          " where INSTRUCTOR.DEPT_NAME = 'Comp. Sci.' and" + 
                          " TEACHES.SEMESTER = 'Spring'" + 
                          " order by INSTRUCTOR.NAME, TEACHES.COURSE_ID, TEACHES.SEC_ID, TEACHES.YEAR";
            ResultSet rs = stmt.executeQuery(sql1);
            
            while (rs.next()){
               String output = rs.getString(1) + "-" +
                               rs.getString(2) + "-" +
                               rs.getString(3) + "-" +
                               rs.getString(4) + "-" +
                               rs.getString(5) + "-" +
                               rs.getString(6) + "-" + 
                               rs.getString(7);
               System.out.println(output);
            }
         }
         catch(SQLException sqle){
            System.out.println("Exception: " + sqle);
         }
         
         System.out.println("\nQuery 2: Insertion");
         try{
            System.out.println("Inserting Flacco, Ripken, Smith, and Freese");
           
           String sqlInsert1 = "";
           String sqlInsert2 = "";
           String sqlInsert3 = "";
           String sqlInsert4 = "";
            /*
            INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED)
            VALUES('99999','Flacco', 'Biology', 80)
            
            INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED)
            VALUES('99998','Ripken', 'History', 120);
            
            INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED)
            VALUES('45679','Smith', 'Finance', 5);
            
            INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED)
            VALUES('00022','Freese', 'History', 100)
            */
            
            String sql2 = "SELECT distinct * " +
                          " from STUDENT" + 
                          " order by STUDENT.NAME";
            ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs2.next()){
               String output = rs2.getString(2);
               System.out.println(output);
            }
         }
         catch(SQLException sqle){
            System.out.println("Exception: " + sqle);
         }
         
         System.out.println("\nQuery 3: Deletion");
         try{
            System.out.println("Deleting Mozart and Crick");
            
            /*
            DELETE FROM INSTRUCTOR
            WHERE NAME = 'Mozart'; 
            DELETE FROM INSTRUCTOR
            WHERE NAME = 'Crick';
            */
            
            String sql3 = "SELECT distinct * " +
                          " from INSTRUCTOR" + 
                          " order by INSTRUCTOR.NAME";
            ResultSet rs3 = stmt.executeQuery(sql3);
            while (rs3.next()){
               String output = rs3.getString(2);
               System.out.println(output);
            }
         }
         catch(SQLException sqle){
            System.out.println("Exception: " + sqle);
         }
         
      }
      catch(Exception sqle){
         System.out.println("Exception: " + sqle);
      }
   }
}