import java.util.Scanner;

public class MarubatsuGame {
	
	static final int EMPTY = 0;
	static final int BOARD_SIZE =3;
	static final int PLAYER_1 = 1;
	static final int PLAYER_2 = 2;


	public static void main(String[] args) {
		
		int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
		
		//盤面準備
		System.out.println("-----ゲーム開始-----");

		for(int i=0;i<BOARD_SIZE;i++) {
			String boardStr ="";
			for(int j=0;j<BOARD_SIZE-1;j++) {
				boardStr += "　│";
			}
			System.out.println(boardStr);
			
			boardStr ="";
			if(i<BOARD_SIZE-1) {
				for(int j=0;j<BOARD_SIZE;j++) {
					boardStr += "─　";
				}
				System.out.println(boardStr);
			}
			
		}

		
		//ゲームスタート
		int player;
		boolean isContinue =true;
		
		while(isContinue){
			//PL1		
				player=PLAYER_1;
				
				//マスを選択
				selectBoard(player,board);
					
				//勝利しているかチェック
				isContinue = isWinner(board);
				
				//選択を盤面に反映して出力
				printBoard(board,player,isContinue);
				
				//引き分けかチェック
				isContinue= isDraw(board,isContinue);
				
				//勝利.引き分けしていればゲーム終了
				if(!isContinue) {
					break;
				}
			
			
			//PL2
			//引き分けの場合終了させる
			if(!isContinue) {
				break;
			}
			player=PLAYER_2;
			
			selectBoard(player,board);
			
			//勝利しているかチェック
			isContinue = isWinner(board);
			
			//選択を盤面に反映して出力
			printBoard(board,player,isContinue);
			
			//勝利していればゲーム終了
			if(!isContinue) {
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
		boolean isContinue =true; 
		int[][] rows = new int[board.length][board.length];
		int[][] columns = new int[board.length][board.length];
		int[][] diagonals = new int[2][board.length];
		
		//横チェック
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				rows[i][j]=board[i][j];
			}
		}
		
		isContinue = isLineCompleted(rows);
		if(!isContinue) {
			return isContinue;
		}
		
		
		//縦チェック
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				columns[i][j]=board[j][i];
			}
		}
		
		isContinue = isLineCompleted(columns);
		if(!isContinue) {
			return isContinue;
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
		
		isContinue = isLineCompleted(diagonals);
		if(!isContinue) {
			return isContinue;
		}
		
		return isContinue;
			
	}
	
	
	/**
	 * ひとつ前のマス(preNum)と比較して同じ値かどうか判定する
	 * @param board 盤面を管理する配列
	 * @return 三マス揃っている箇所があるかどうか
	 */
	public static boolean isLineCompleted(int board[][]) {	
		boolean isContinue =true;
		int previousValue =0;
		
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
			
				//記入されていないマスがあればスキップ
				if(board[i][j]==EMPTY) {
					break;
				}
				
				//初回preNum設定
				if(j==0) {
					previousValue=board[i][j];
				}else {
					
					//ひとつ前の値と同じかチェック
					if(previousValue==board[i][j]) {
						
						//行の最後のマスなら勝利(継続しない)として返す
						if(j==board[i].length-1) {
							isContinue = false;
						}
						
						previousValue=board[i][j];
						
					}else {
						break;
					}
				}
			}
			
			//勝利していれば終了
			if(!isContinue) {
				return isContinue;
			}
		
		}
		
		return isContinue;
	}
	
	
	/**
	 * board配列を盤面に反映し、出力
	 * @param board 盤面を管理する配列
	 * @param player 何番目のプレイヤーか
	 * @param isWin 勝利しているかどうか　勝利している場合勝利メッセージを追加
	 */
	//
	public static void printBoard(int board[][],int player, boolean isContinue) {
		String boardStr="";
		for(int i=0;i<board.length;i++) {
			boardStr="";
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
			
			boardStr="";
			if(i<board.length-1) {
				for(int j=0;j<board.length;j++) {
					boardStr += "─　";
				}
				System.out.println(boardStr);
			}

		}
		if(!isContinue) {
			System.out.println("プレイヤー"+player+"の勝利です!");
			System.out.println("-----ゲーム終了-----");
		}
	}
	
	public static boolean isDraw(int board[][],boolean isContinue) {
	//引き分け判定	→まだ打てるか？　ゲーム継続できるか
	//空きがあるか
		for(int i=0;i<board.length;i++) {
			
			for(int j=0;j<board[i].length;j++) {
			
				//記入されていないマスがあればチェック終了＆ゲーム継続
				if(board[i][j]==EMPTY) {
					return isContinue;
				}
	
			}
		}
		isContinue =false;
		System.out.println("引き分けです。");
		return isContinue;
	}
		
	

}
