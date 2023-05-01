package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Judge.judge;



class mainJudge {
    public static void main(String[] args){
    	
    	Connection con = null;
    	judge j = new judge();
//    	System.out.println(System.getProperty("java.class.path"));

    	try{
    		// データベースに接続
    		con = DriverManager.getConnection( "jdbc:テーブル名が入ります", 
    											"idが入ります", 
    											"パスワードが入ります");
    		System.out.println("データベースに接続しました");
    		
    		j.setCon(con);
    		j.judge1();
    		
    	} catch (SQLException e) {
    		System.out.println("SQL関係でエラーが起きました");
    		System.out.println(e.getMessage());
    	} catch (Exception e) {
    		System.out.println("エラーが発生しました");
    		System.out.println(e.getMessage());
    	} finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズに失敗しました。");
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
