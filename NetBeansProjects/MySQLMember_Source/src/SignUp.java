
import java.util.regex.Pattern;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author hanchaelyeong
 */
public class SignUp{
    String userid;
    String pw;
    String pwck;
    String tel;
    String name;
    boolean pw_valid;
    boolean id_valid;
    // 최소 8자, 최대 20자 상수 선언
    final int MIN = 8;
    final int MAX = 20;
    // 패스워드 상태정보 상수 선언
    final int NONE_PW = 0;
    final int PW_DIFF = 1;
    final int PW_SPACE = 2;
    final int PW_LEN = 3;
    final int PW_REX = 4;
    final int PW_REFT = 5;
    final int USE = 6;
    // 유효성 검사 결과 상수 선언
    final boolean VALID = true;
    final boolean INVALID = false;
    
    public SignUp(){
        userid="";
        pw="";
        pwck="";
        tel="";
        name="";
        pw_valid=INVALID;
        id_valid=INVALID;
    }
    public boolean ableCheck(){
        if(userid.equals("")||pw.equals("")||pwck.equals("")||tel.equals("")||name.equals(""))
            return INVALID;
        else if(pw_valid==INVALID||id_valid==INVALID)
            return INVALID;
        else
            return VALID;
    }
    public int isValidPassword() {

        int result=0;
        
        System.out.println(pw);
        System.out.println(pwck);
        

        // 영어, 숫자, 특수문자 포함한 MIN to MAX 글자 정규식
        final String REGEX = 
          "^((?=.*\\d)(?=.*[a-zA-Z])(?=.*[\\W]).{" + MIN + "," + MAX + "})$";
        // 3자리 연속 문자 정규식
        final String SAMEPT = "(\\w)\\1\\1";
        // 공백 문자 정규식
        final String BLANKPT = "(\\s)";

        // 문자열 길이
        int Pwd_Len = pw.length();
        int PwdChk_Len = pwck.length();
        // 공백 체크
        if (pw.equals("")|| pwck.equals("")) { // 패스워드와 패스워드 확인 입력 칸중 하나라도 입력이 없는 경우
//          txt2.setText("패스워드를 입력 해 주세요");
            result = NONE_PW;
            pw_valid=INVALID;
        }else if(!pw.equals(pwck)){ // 패스워드와 패스워드 확인 입력이 다른 경우
//            txt2.setText("패스워드가 서로 다릅니다");
            result = PW_DIFF;
            pw_valid=INVALID;
        }else if (Pattern.compile(BLANKPT).matcher(pw).find()||Pattern.compile(BLANKPT).matcher(pwck).find()) { // 패스워드에 공백문자가 포함 된 경우
//           txt2.setText("패스워드에 공백은 들어갈 수 없습니다");
            result = PW_SPACE;
            pw_valid=INVALID;
        }else if(!Pattern.compile(REGEX).matcher(pw).find()||!Pattern.compile(REGEX).matcher(pwck).find()){ // 비밀번호 글자수 체크를 포함한 정규식 체크
            if ((Pwd_Len > 20 || Pwd_Len < 8)||((PwdChk_Len > 20 || PwdChk_Len < 8))) { // 글자수로 인해 정규식 불만족인 경우
//                txt2.setText("패스워드의 길이는 8글자 이상 20글자 미만이어야 합니다");
                result = PW_LEN;
                pw_valid=INVALID;
            }
            else{ // 글자수가 아닌 정규식 패턴이 불만족인 경우
//                txt2.setText("패스워드에는 최소 문자,숫자,특수문자가 한개이상 포함되어야 합니다");
                result = PW_REX;
                pw_valid=INVALID;
            }
        }else if(Pattern.compile(SAMEPT).matcher(pw).find()||Pattern.compile(SAMEPT).matcher(pwck).find()){ // 동일한 문자 3개 이상 체크
//            txt2.setText("패스워드에 동일한 문자를 3번이상 사용할 수 없습니다");
            result = PW_REFT;
            pw_valid=INVALID;
        }else{ // 모든 유효성 검사가 문제 없는 경우
//            txt2.setText("패스워드를 사용 가능합니다");
            result = USE;
            pw_valid=VALID;
        }

        return result;
            
        
    }   
}
