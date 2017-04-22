import java.util.Scanner;
import java.sql.*;

public class CMSC461_Project5{
   public static void main(String []args){
      System.out.println("Hello World");   
      JDBC("testID", "Test1234");
   }
   
   public static void JDBC(String userID, String password){
      try{
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("USER_ID = " + userID + ", PSW = " + password);
      }
      catch(Exception sqle){
         System.out.println("Exception: " + sqle);
      }
   }
}