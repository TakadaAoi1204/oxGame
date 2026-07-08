
public class Board {
	public static final int EMPTY =0;
	private final int[][] board;
	
	public Board(int boardSize) {
		board = new int[boardSize][boardSize];
	}
	
	/**
	 * 盤面に選択したマスを置けるか判定
	 * @param row
	 * @param column
	 * @param player
	 * @return
	 */
	public boolean place(int row,int column,int player) {
		if(board[row][column] == EMPTY) {
			board[row][column]=player;
		}else {
			return false;
		}
		return true;
	}
	
	/**
	 * すべてのマスが埋まっているかどうか
	 * @return
	 */
	public boolean isFull() {
		for(int i=0;i<board.length;i++) {	
			for(int j=0;j<board[i].length;j++) {
				//記入されていないマスがあればチェック終了
				if(board[i][j]==EMPTY) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public int[][] getBoard(){
		return board;
	}
}
