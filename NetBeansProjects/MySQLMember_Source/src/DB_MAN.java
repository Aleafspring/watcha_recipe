import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
 
public class DB_MAN {
     
      String jdbc_driver = "com.mysql.cj.jdbc.Driver";
      String jdbc_url = "jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";
      String strUser = "root";
     // String strPWD = "HANchaer1;
      
      Connection DB_con;
      Statement DB_stmt;
      Statement DB_sub_stmt;
      ResultSet DB_rs;
      ResultSet DB_sub_rs;
 
    public void dbOpen() throws IOException{
      // TODO Auto-generated method stub
     
      
        try {
          
            Class.forName(jdbc_driver);
         
            DB_con = DriverManager.getConnection(jdbc_url, strUser, null);
            DB_stmt = DB_con.createStatement();
 
      
        } 
        catch (Exception e) {
            System.out.println("SQLException"+e.getMessage());
        } 
    }
    public void sub_dbOpen() throws IOException{
      // TODO Auto-generated method stub
     
      
      try {
        DB_sub_stmt = DB_con.createStatement();
 
      
      } catch (Exception e) {
          System.out.println("SQLException"+e.getMessage());
      } 
   }

   public void dbClose()throws IOException{
       try{
           DB_stmt.close();
           DB_con.close();
           
       }catch(Exception e){
            System.out.println("SQLException"+e.getMessage());
       }
   } 
    public void sub_dbClose()throws IOException{
       try{
           DB_sub_stmt.close();
           
       }catch(Exception e){
            System.out.println("SQLException"+e.getMessage());
       }
   } 

}

    
