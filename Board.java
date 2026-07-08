
public class Board {
	private static final int EMPTY =0;
	private static final int BOARD_SIZE =3;
	private static final int[][] board = new int[BOARD_SIZE][BOARD_SIZE];;
	
	
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
			return true;
		}else {
			return false;
		}
		
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
