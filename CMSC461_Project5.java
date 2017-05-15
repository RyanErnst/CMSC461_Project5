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
      JDBC(userID, password, scan);
      
      System.out.println("\nProgram Over. Shutting down.");
      System.out.println("Thank you and goodbye.");
   }
   
   public static void JDBC(String userID, String password, Scanner scan){
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",userID,password);
         Statement stmt = conn.createStatement();
         System.out.println("Connection Established!");
         conn.setAutoCommit(false);
         
         System.out.println("\nQuery 1: Selection");
         try{
            System.out.println("Name\tCourse_Name\tSection_ID\tSemester\t" + 
                               "Year\tBuilding\tRoom#");
            System.out.println("--------------------------------------------------------" + 
                               "-----------------------------");
            String sql1 = "SELECT distinct INSTRUCTOR.NAME, TEACHES.COURSE_ID, TEACHES.SEC_ID," + 
                          " TEACHES.SEMESTER, TEACHES.YEAR, SECTION.BUILDING, SECTION.ROOM_NUMBER" +
                          " from INSTRUCTOR join TEACHES on TEACHES.ID = INSTRUCTOR.ID join SECTION" + 
                          " on TEACHES.COURSE_ID = SECTION.COURSE_ID" +
                          " where INSTRUCTOR.DEPT_NAME = 'Comp. Sci.' and" + 
                          " TEACHES.SEMESTER = 'Spring'" + 
                          " order by INSTRUCTOR.NAME, TEACHES.COURSE_ID, TEACHES.SEC_ID, TEACHES.YEAR";
            ResultSet rs = stmt.executeQuery(sql1);
            
            while (rs.next()){
               String output = rs.getString(1) + "\t" +
                               rs.getString(2) + "\t" +
                               rs.getString(3) + "\t" +
                               rs.getString(4) + "\t" +
                               rs.getString(5) + "\t" +
                               rs.getString(6) + "\t" + 
                               rs.getString(7);
               System.out.println(output);
            }
         }
         catch(SQLException se){
            System.out.println("Exception: " + se);
            conn.rollback();
         }
         
         System.out.println("\nQuery 2: Insertion");
         try{
            System.out.println("Inserting Flacco, Ripken, Smith, and Freese");
            
            String sqlInsert1 = "INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED) " +
                                "VALUES('99999','Flacco', 'Biology', 80)";
            stmt.executeUpdate(sqlInsert1);
            
            String sqlInsert2 = "INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED) " +
                                "VALUES('99998','Ripken', 'History', 120)";
            stmt.executeUpdate(sqlInsert2);
            
            String sqlInsert3 = "INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED) " +
                                "VALUES('45679','Smith', 'Finance', 5)";
            stmt.executeUpdate(sqlInsert3);
                                
            String sqlInsert4 = "INSERT INTO STUDENT(ID, NAME, DEPT_NAME, TOT_CRED) " +
                                "VALUES('00022','Freese', 'History', 100)";
            stmt.executeUpdate(sqlInsert4);
            
            
            String sql2 = "SELECT distinct * " +
                          " from STUDENT" + 
                          " order by STUDENT.NAME";
            ResultSet rs2 = stmt.executeQuery(sql2);
            
            System.out.println("\nName\tID\tMajor\tCredits");
            System.out.println("-----------------------------");
            while (rs2.next()){
               String output = rs2.getString(2) + "\t" +
                               rs2.getString(1) + "\t" +
                               rs2.getString(3) + "\t" + 
                               rs2.getString(4);
               System.out.println(output);
            }
            
            System.out.println("\nDo you wish to commit this changes?\nPress \"c\" to commit or \"r\" to rollback.");
            System.out.print("Choice: ");
            String choice1 = scan.next();
            
            switch(choice1){
               case "c":
               case "C":
                  System.out.println("Committing.");
                  conn.commit();
                  break;
               case "r":
               case "R":
                  System.out.println("Rolling back.");
                  conn.rollback();
                  break;
               default:
                  System.out.println("Unable to process. Incorrect Input.");
                  break;
            }//end switch
         }
         catch(SQLException se){
            System.out.println("Exception: " + se);
            conn.rollback();
         }
         
         System.out.println("\nQuery 3: Deletion");
         try{
            System.out.println("Deleting Mozart and Crick");
            
            String sqlDelete1 = "DELETE FROM INSTRUCTOR " +
                                "WHERE NAME = 'Mozart'";
            stmt.executeUpdate(sqlDelete1);
                                
            String sqlDelete2 = "DELETE FROM INSTRUCTOR " +
                                "WHERE NAME = 'Crick'";
            stmt.executeUpdate(sqlDelete2);
            
            String sql3 = "SELECT distinct * " +
                          " from INSTRUCTOR" + 
                          " order by INSTRUCTOR.NAME";
            ResultSet rs3 = stmt.executeQuery(sql3);
            
            System.out.println("\nName\tID\tDepartment\tSalary");
            System.out.println("--------------------------------------------------------" + 
                               "-----------------------------");
            while (rs3.next()){
               String output = rs3.getString(2) + "\t" + 
                               rs3.getString(1) + "\t" +
                               rs3.getString(3) + "\t" +
                               rs3.getString(4);
               System.out.println(output);
            }
            
            System.out.println("\nDo you wish to commit this changes?\nPress \"c\" to commit or \"r\" to rollback.");
            System.out.print("Choice: ");
            String choice2 = scan.next();
            
            switch(choice2){
               case "c":
               case "C":
                  System.out.println("Committing.");
                  conn.commit();
                  break;
               case "r":
               case "R":
                  System.out.println("Rolling back.");
                  conn.rollback();
                  break;
               default:
                  System.out.println("Unable to process. Incorrect Input.");
                  break;
            }//end switch
         }
         catch(SQLException se){
            System.out.println("Exception: " + se);
            conn.rollback();
         }
         
      }
      catch(Exception sqle){
         System.out.println("Exception: " + sqle);
      }
   }
}