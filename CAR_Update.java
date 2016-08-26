package com;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CAR_Update {
       static String url = "jdbc:oracle:thin:@//dbplosbdb02.uk.dnb.com:1521/cmplprd.uk.dnb.com";
                   //static String url = "jdbc:oracle:thin:@dbslosbdb02.uk.dnb.com/cmplstg.uk.dnb.com";
       static String username = "compliance";
       static String password = "compliance";
    
       public static void main(String[] args) throws Exception {
              Class.forName("oracle.jdbc.driver.OracleDriver");
              Connection conn = DriverManager.getConnection(url, username, password);
              conn.setAutoCommit(true);

              // String sql = "INSERT INTO CAR (CAR_ID, CAR_NBR,
              // SUPP_ID,TEST_CAR_INDC,DEL_INDC,ROW_CRE_ID,ROW_CRE_DT,ROW_UPD_DT,UPD_USR_ID,car_obj)
              // VALUES (CAR_seq.nextval, 1,
              // 1,1,1,'balaji',sysdate,sysdate,'balaji',?)";

              String sql = "update car set car_obj=? where car_id=38509";
              PreparedStatement stmt = conn.prepareStatement(sql);

              // stmt.setString(1, "java.gif");
              // stmt.setString(2, "Java Official Logo");

              System.out.println("File ready");

              File text = new File("C:\\Chetan\\CAR_JSON\\38509.txt");
              BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(text), "UTF8"));

              StringBuffer stringBuffer = new StringBuffer();

              String str = null;

              while ((str = in.readLine()) != null) {
                     // System.out.println(str);
                     stringBuffer.append(str);
              }

              in.close();

              System.out.println(" =========" + stringBuffer.toString().getBytes());
              InputStream is = new ByteArrayInputStream(stringBuffer.toString().getBytes(StandardCharsets.UTF_8));
              
              stmt.setBinaryStream(1, is);
              //System.out.println(is.read());
              //stmt.setBinaryStream(parameterIndex, x, length);(1, is);
              // stmt.setBinaryStream(1, stringBuffer.toString().getBytes(), (int)
              // text.length());
              System.out.println("File ready");
              stmt.execute();

              conn.commit(); // fis.close(); conn.close(); System.out.println(

       }
}
