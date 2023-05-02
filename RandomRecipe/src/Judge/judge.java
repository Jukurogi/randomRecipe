package Judge;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import Recipe.recipeIn;
import Recipe.recipeOut;

public class judge {
	
	private Scanner i = new Scanner(System.in);
	private recipeOut rOut = new recipeOut();

	private Connection con = null;	
	
	public void judge1() throws SQLException, Exception {
		
    	System.out.println("レシピ入力：1　メニュー決定(ランダム)：21　メニュー決定(材料から)：22　一覧表示：24　終了：3(半角入力)");
		int decision = this.i.nextInt();
		
		if(decision == 1){
			recipeIn rIn = new recipeIn();
			rIn.setCon(this.con);
			rIn.menu();
			
		} else if(decision == 21) {
			this.rOut.setCon(this.con);
			this.rOut.randomRecipe();
			
		} else if(decision == 22) {
			this.rOut.setCon(this.con);
			this.rOut.materialRecipe();
			
		} else if(decision == 24) {
			this.rOut.setCon(this.con);
			this.rOut.allRecipe();
			
		} else if(decision == 3) {
			this.con.close();
		} else {
			System.out.println("正しい数字を入力してください");
		}
    
    }
	
	
	public void setCon(Connection con) {
		this.con = con;
	}

}
