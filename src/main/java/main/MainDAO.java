package main.list;

import domain.Main;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static main.MainSQL.*;

public class MainDAO {
    private DataSource ds;

    public MainDAO() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java:/comp/env");
            ds = (DataSource)envContext.lookup("jdbc/TestDB");
        }catch(NamingException ne) {
            ne.printStackTrace();
        }
    }

    ArrayList<Main> mainPage(){ // mainPage 불러오는 데이터 처리
        ArrayList<Main> list = new ArrayList<Main>();
        Main mainDto = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = SELECT_MAIN;

        try{
            con = ds.getConnection();
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                long board_seq = rs.getLong("board_seq");
                String academy_name = rs.getString("academy_name");
                String addr = rs.getString("addr");
                String phone = rs.getString("phone");
                Date edate = rs.getDate("edate");
                Date ldate = rs.getDate("ldate");
                String grade = rs.getString("grade");
                String subject = rs.getString("subject");
                String content = rs.getString("content");
                int book_limit = rs.getInt("book_limit");

                mainDto = new Main(board_seq, academy_name, addr,phone,edate,ldate,grade,
                        subject, content, book_limit);
                list.add(mainDto);
            }
            return list;
        }catch (SQLException se){
            se.printStackTrace();
            return null;
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            }catch (SQLException se2){
                se2.printStackTrace();
            }
        }
    }
}
