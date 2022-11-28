//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.sql.DriverManager;
//import java.io.IOException;
//import java.sql.SQLException;
//import javax.swing.table.DefaultTableModel;
///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//
///**
// *
// * @author hanchaelyeong
// */
//public class UserDefaultJTabelDAO {
//    Connection con;
//    Statement st;
//    PreparedStatement ps;
//    ResultSet rs;
//    String jdbc_driver = "com.mysql.cj.jdbc.Driver";
//    String jdbc_url = "jdbc:mysql://127.0.0.1:3306/sys?characterEncoding=UTF-8&serverTimezone=UTC";
//    String strUser = "root";
//    
//    
//    public UserDefaultJTabelDAO(){ 
//        try{
//            Class.forName(jdbc_driver);
//            con = DriverManager.getConnection(jdbc_url);
//        }catch(ClassNotFoundException e){
//            System.out.println(e + "=> 로드 fail");
//        }catch(SQLException e){
//            System.out.println(e + "=> 연결 fail");
//        }
//    }//생성자
//    
//    
//    public void dbClose(){
//        try{
//            if(rs != null) rs.close();
//            if(st != null) st.close();
//            if(ps != null) ps.close();
//        }catch(Exception e){
//            System.out.println(e + "=> dbClose fail");
//        }
//    }
//    
//    
//    
//     /**
//     * 인수로 들어온 ID에 해당하는 레코드 검색하여 중복여부 체크하기 리턴값이 true =사용가능 , false = 중복임
//     * */
//    
//    public boolean getIdByCheck(String userid){
//       boolean result = true;
//       
//        try{
//            ps = con.prepareStatement("select * form member where userid =?");
//            ps.setString(1, userid.trim());
//            rs =ps.executeQuery();
//            if(rs.next())
//                result = false; //레코드가 존재하면 false
//        
//    
//          }catch(SQLException e){
//            System.out.println(e+" => getIdByCheck fail");
//          }finally{
//           dbClose();
//         }    
//        
//       return result;
//        
//    
//    }  
//    
//    
//    /**
//     * userlist 회원가입하는 기능 메소드
//     * */
//    public int userListInsert(MainFrame user) {
//        int result = 0;
//      
//        
//        
//        try {
//            ps = con.prepareStatement("insert into member values(?,?,?,?)");
//            ps.setString(1, user.memID.getText());
//            ps.setString(2, user.memPW.getText());
//            ps.setString(3, user.memTEL.getText());
//            ps.setString(4, user.memNAME.getText());
// 
//            result = ps.executeUpdate(); //실행 -> 저장
// 
//        } catch (SQLException e) {
//            System.out.println(e + "=> userListInsert fail");
//        } finally {
//            dbClose();
//        }
// 
//        return result;
// 
//    }//userListInsert()
//    
//    
//    
//    
//    
//    /**
//     * userlist의 모든 레코드 조회
//     * */
//    public void userSelectAll(DefaultTableModel t_model) {
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery("select * from member order by userid");
// 
//            // DefaultTableModel에 있는 기존 데이터 지우기
//            for (int i = 0; i < t_model.getRowCount();) {
//                t_model.removeRow(0);
//            }
// 
//            while (rs.next()) {
//                Object data[] = { rs.getString(1), rs.getString(2),
//                        rs.getString(3), rs.getString(4) };
// 
//                t_model.addRow(data); //DefaultTableModel에 레코드 추가
//            }
// 
//        } catch (SQLException e) {
//            System.out.println(e + "=> userSelectAll fail");
//        } finally {
//            dbClose();
//        }
//        
//    }//userSelectAll()
// 
//
//     /**
//     * ID에 해당하는 레코드 삭제하기
//     * */
//    public int userDelete(String id) {
//        int result = 0;
//        try {
//            ps = con.prepareStatement("delete member where userid = ? ");
//            ps.setString(1, id.trim());
//            result = ps.executeUpdate();
// 
//        } catch (SQLException e) {
//            System.out.println(e + "=> userDelete fail");
//        }finally {
//            dbClose();
//        }
// 
//        return result;
//    }//userDelete()
//
//     /**
//     * ID에 해당하는 레코드 수정하기//도서 수정하
//     * */
////    public int userUpdate(MainFrame user) {
////        int result = 0;
////        String sql = "UPDATE member SET NAME=?, age=? , addr=? WHERE id=?";
//// 
////        try {
////            ps = con.prepareStatement(sql);
////            // ?의 순서대로 값 넣기
////            ps.setString(1, user.name.getText());
////            ps.setString(2, user.age.getText());
////            ps.setString(3, user.addr.getText());
////            ps.setString(4, user.id.getText().trim());
//// 
////            // 실행하기
////            result = ps.executeUpdate();
//// 
////        } catch (SQLException e) {
////            System.out.println(e + "=> userUpdate fail");
////        } finally {
////            dbClose();
////        }
//// 
////        return result;
////    }//userUpdate()
//// 
//    
//    
//    
//    
////    /**
////     * 검색단어에 해당하는 레코드 검색하기 (like연산자를 사용하여 _, %를 사용할때는 PreparedStatemnet안된다. 반드시
////     * Statement객체를 이용함)
////     * */
////    public void getUserSearch(DefaultTableModel dt, String fieldName,
////            String word) {
////        String sql = "SELECT * FROM TB_USERLIST WHERE " + fieldName.trim()
////                + " LIKE '%" + word.trim() + "%'";
//// 
////        try {
////            st = con.createStatement();
////            rs = st.executeQuery(sql);
//// 
////            // DefaultTableModel에 있는 기존 데이터 지우기
////            for (int i = 0; i < dt.getRowCount();) {
////                dt.removeRow(0);
////            }
//// 
////            while (rs.next()) {
////                Object data[] = { rs.getString(1), rs.getString(2),
////                        rs.getInt(3), rs.getString(4) };
//// 
////                dt.addRow(data);
////            }
//// 
////        } catch (SQLException e) {
////            System.out.println(e + "=> getUserSearch fail");
////        } finally {
////            dbClose();
////        }
//// 
////    }//getUserSearch()
//// 
////     
//    
//}