import java.util.Scanner;

public class MarubatsuGame {
	
	static final int EMPTY = 0;
	static final int BOARD_SIZE =3;
	static final int PLAYER_1 = 1;
	static final int PLAYER_2 = 2;


	public static void main(String[] args) {
		int player;
		boolean isWin =false;

		final int BOARD_SIZE =3;
		int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
		
		
		//盤面準備
		System.out.println("-----ゲーム開始-----");

		for(int i=0;i<board.length;i++) {
			System.out.println("　│　│　 ");
			if(i<board.length-1) {
				System.out.println("─　─　─ ");
			}	
		}
		
		//ゲームスタート
		while(!isWin){
			//PL1		
				player=PLAYER_1;
				
				//マスを選択
				selectBoard(player,board);
					
				//勝利しているかチェック
				isWin = isWinner(board);
				
				//選択を盤面に反映して出力
				printBoard(board,player,isWin);
				
				//勝利していればゲーム終了
				if(isWin) {
					break;
				}
			
			//PL2
			player=PLAYER_2;
			
			selectBoard(player,board);
			
			//勝利しているかチェック
			isWin = isWinner(board);
			
			//選択を盤面に反映して出力
			printBoard(board,player,isWin);
			
			//勝利していればゲーム終了
			if(isWin) {
				break;
			}
		}

	}
	
	
	/**
	 * マスを選択させ、記入済みでないかチェックしBoard配列に反映
	 * @param player 何番目のプレイヤーか
	 * @param board 盤面を管理する配列
	 */
	public static void selectBoard(int player,int board[][]) {
		int row;
		int column;
		boolean isRetry = true;
		Scanner scan = new Scanner(System.in);
		
		while(isRetry) {
			
			System.out.println("プレイヤー"+player+"は行と列を指定してください。");
			System.out.println("行を入力");
			row = scan.nextInt()-1;
			System.out.println("列を入力");
			column = scan.nextInt()-1;
			
			//記入済みの場合やり直し
			if(board[row][column]!=EMPTY) {
				System.out.println("そのマスは記入済みです。他のマスを指定してください");
				continue;
			}
			
			board[row][column] = player;
			
			isRetry = false;
			
		}
	}
	
	/**
	 * 縦、横、斜めの値を格納した配列を作成、各配列の値をcheck関数で調べて勝利を判定
	 * @param board 盤面を管理する配列
	 * @return 勝者であるかどうか
	 */
	public static boolean isWinner(int board[][]) {
		boolean isWin =false; 
		int[][] rows = new int[board.length][board.length];
		int[][] columns = new int[board.length][board.length];
		int[][] diagonals = new int[2][board.length];
		
		//横チェック
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				rows[i][j]=board[i][j];
			}
		}
		
		isWin = isLineCompleted(rows);
		if(isWin) {
			return isWin;
		}
		
		
		//縦チェック
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				columns[i][j]=board[j][i];
			}
		}
		
		isWin = isLineCompleted(columns);
		if(isWin) {
			return isWin;
		}
		
		
		//ななめチェック
		//左上から右下
		for(int i=0;i<board.length;i++) {	
			diagonals[0][i] = board[i][i];
		}
		//右上から左下
		for(int i=0;i<board.length;i++) {	
			diagonals[1][i]=board[i][board.length-(i+1)];
		}
		
		isWin = isLineCompleted(diagonals);
		if(isWin) {
			return isWin;
		}
		
		return isWin;
			
	}
	
	
	/**
	 * ひとつ前のマス(preNum)と比較して同じ値かどうか判定する
	 * @param board 盤面を管理する配列
	 * @return 三マス揃っている箇所があるかどうか
	 */
	public static boolean isLineCompleted(int board[][]) {	
		boolean isComplete =false;
		int previousValue =0;
		
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
			
				//記入されていないマスがあればスキップ
				if(board[i][j]==0) {
					break;
				}
				
				//初回preNum設定
				if(j==0) {
					previousValue=board[i][j];
				}else {
					
					//ひとつ前の値と同じかチェック
					if(previousValue==board[i][j]) {
						
						//行の最後のマスなら勝利として返す
						if(j==board[i].length-1) {
							isComplete = true;
						}
						
						previousValue=board[i][j];
						
					}else {
						break;
					}
				}
			}
			
			//勝利していれば終了
			if(isComplete==true) {
				return isComplete;
			}
		
		}
		
		return isComplete;
	}
	
	
	/**
	 * board配列を盤面に反映し、出力
	 * @param board 盤面を管理する配列
	 * @param player 何番目のプレイヤーか
	 * @param isWin 勝利しているかどうか　勝利している場合勝利メッセージを追加
	 */
	//
	public static void printBoard(int board[][],int player, boolean isWin) {
		for(int i=0;i<board.length;i++) {
			String boardStr="";
			for(int j=0;j<board[i].length;j++) {
				switch (board[i][j]) {
				//PL1
				case PLAYER_1: {
					boardStr += "〇";
					if(j<board[i].length-1) {
						boardStr += "│";
					}	
					break;
				}
				//PL2
				case PLAYER_2: {
					boardStr += "×";
					if(j<board[i].length-1) {
						boardStr += "│";
					}
					break;
				}
				//無記入
				default:
					boardStr += "　";
					if(j<board[i].length-1) {
						boardStr += "│";
					}
					break;
				}
			}
			System.out.println(boardStr);
			if(i<board.length-1) {
				System.out.println("─　─　─ ");
			}
			
			
		}
		if(isWin) {
			System.out.println("プレイヤー"+player+"の勝利です!");
			System.out.println("-----ゲーム終了-----");
		}
	}
		
	

}
