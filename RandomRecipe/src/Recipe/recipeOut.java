package Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import Judge.judge;

public class recipeOut {
	
	private int inId = 0;
	private int idCount = 0;	
	private String sql = null;
	private String menuName = null;
	
	private Connection con = null;
	private PreparedStatement pstmt =  null;
	private ResultSet rs = null;
	
	private Scanner s = new Scanner(System.in);
	private Random r = new Random();
	
	//  コンストラクタすることなし
	public recipeOut() { }
		
	
	public void randomRecipe() throws SQLException,Exception {
		
		//  DBのidが一番でかいレコード(一番最後に入力されたレコード)のidカラムを取得
		this.sql = "SELECT MAX(id) AS id FROM recipe";
		this.pstmt = this.con.prepareStatement(this.sql);
		this.rs = this.pstmt.executeQuery();
		this.rs.next();
		//  結果格納
		this.idCount = this.rs.getInt("id");
		
		System.out.println("ランダムで決定します");
		
		while(true) {
			//  1+idをランダムで排出(0を排出されたら困るので+1) 
			this.inId = 1 + r.nextInt(this.idCount);
			
			//  ランダムで出てきた数字と合致するidのレコード抽出
			this.sql = "SELECT * FROM recipe WHERE id = ?";
			this.pstmt = this.con.prepareStatement(this.sql);
			this.pstmt.setInt(1, this.inId);
			//  結果格納
			this.rs = this.pstmt.executeQuery();
			
			this.rs.next();
			
			//  抽出結果レコードのnameカラムを変数に代入
			this.menuName = this.rs.getString("name");
						
			//  結果表示
			System.out.println("今日のご飯は");
			System.out.println(this.menuName);
			System.out.println("です");
			
			System.out.println("決定：1　やりなおす：1以外の数字(半角入力)");
			int i = s.nextInt();
			
			if(i == 1) {
				this.rs.close();
				break;
			}
		}
		judge j = new judge();
		j.setCon(this.con);
		j.judge1();
		
	}	
	
	
	public void materialRecipe() throws SQLException, Exception {
		
		Map<String, String[]> material = new HashMap<>();
		String materialSet;
		String[] rsS = null;
		
		System.out.println("材料から決定します");
		
		while(true) {
			System.out.println("材料名を入力(全て全角カナで入力)");
			String inMaterial = this.s.next();
			
			//  材料は一文で入力されているので入力された材料と部分一致を使用		
			this.sql = "SELECT * FROM recipe WHERE material LIKE ?";
			this.pstmt = this.con.prepareStatement(this.sql);
			pstmt.setString(1,  "%" + inMaterial + "%");
			
			//  結果格納
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				//  SQL実行結果からmaterialカラムを取得
				materialSet = rs.getString("material");
				
				//  materialカラムに入っている材料を区切って配列へ
				rsS = materialSet.split(",");
				
				//  nameカラムを取得
				this.menuName = rs.getString("name");
				
				//  取得結果を材料配列と一緒にハッシュマップへ
				material.put(this.menuName,rsS);
			}
			
			//  部分一致した材料が入っている料理名を全て表示
			System.out.println("あなたのレシピの中で" + inMaterial + "を使う料理は");
					
			for (String key : material.keySet()) {
		        System.out.println(key + ":" + Arrays.toString(material.get(key)));
			}
			System.out.println("です");
			 
			
			System.out.println("決定：1　別の材料から探す：1以外(半角入力)");
			int i = this.s.nextInt();
				if(i == 1) {
					this.rs.close();
					break;
				}
			
		}
		judge j = new judge();
		j.setCon(this.con);
		j.judge1();
		
	}

	
	public void allRecipe() throws SQLException, Exception {
		
		Map<String, String[]> material = new HashMap<>();
		String materialSet = null;
		String[] rsS = null;
		
		System.out.println("過去入力した一覧を表示します");
		
		while(true) {
			
			//  レコードすべて抜き出し		
			this.sql = "SELECT * FROM recipe";
			this.pstmt = this.con.prepareStatement(this.sql);
			
			//  結果格納
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				//  SQL実行結果からmaterialカラムを取得
				materialSet = rs.getString("material");
				
				//  materialカラムに入っている材料を区切って配列へ
				rsS = materialSet.split(",");
				
				//  nameカラムを取得
				this.menuName = rs.getString("name");
				
				//  取得結果を材料配列と一緒にハッシュマップへ
				material.put(this.menuName,rsS);
			}
					
			for (String key : material.keySet()) {
		        System.out.println(key + ":" + Arrays.toString(material.get(key)));
			}
		
			System.out.println("メニューに戻る：1");
			int i = this.s.nextInt();
				if(i == 1) {
					this.rs.close();
					break;
				}
			
		}
		judge j = new judge();
		j.setCon(this.con);
		j.judge1();
		
	}

	
	public void setCon(Connection con) {
		this.con = con;
	}
}
