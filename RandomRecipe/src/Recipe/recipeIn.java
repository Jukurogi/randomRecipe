package Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import Judge.judge;

public class recipeIn {
	
	private int id = 0;
	private String name = null;
	private String material = null;
	private String sql = null;

	private PreparedStatement pstmt =  null;
	private Connection con = null;
	private ResultSet rs = null;
	
	private Scanner s = new Scanner(System.in);	
	
	//  コンストラクタすることなし
	public recipeIn() { }
		
	
	//	レシピ入力
	public void menu() throws SQLException, Exception  {
		
		while(true) {
		//  DBのidが一番でかいレコード(一番最後に入力されたレコード)のidカラムを取得
			this.sql = "SELECT MAX(id) AS id FROM recipe";
			this.pstmt = this.con.prepareStatement(this.sql);
			this.rs = this.pstmt.executeQuery();
			this.rs.next();
			//  結果格納
			this.id = this.rs.getInt("id");
			// 最終履歴数＋1の数にする
			this.id++;
			
			// レコード入力開始
			this.sql = "INSERT INTO recipe(id, name, material) VALUES(?, ?, ?)";
			this.pstmt = this.con.prepareStatement(this.sql);
			
			//  idセット
			this.pstmt.setInt(1, this.id);
			
			//  料理名セット
			System.out.println("料理名を入力");
			this.name = this.s.next();
			pstmt.setString(2, this.name);
			
			//  材料セット(一行で入れてるので後々splitで区切る)
			System.out.println("材料を入力(全て半角カンマで区切り、全角カナで入力)");
    		System.out.println("(例)ジャガイモ,ニンジン,タマネギ");
    		this.material = this.s.next();
			pstmt.setString(3, this.material);
			
			int n = this.pstmt.executeUpdate();
			
    		//  毎回終了するのかどうか確認
    		System.out.println("レシピ入力終了：1　続ける：1以外(半角入力)");
    		int i = this.s.nextInt();
    			if(i == 1) {
    				break;
    			}
		}
		//  メニュー呼び出し
		judge j = new judge();
		j.setCon(this.con);
		j.judge1();
	}	

	
	public void setCon(Connection con) {
		this.con = con;
	}
}
