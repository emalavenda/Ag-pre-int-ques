/*
 * Sudoku implementation
 *
 * @version 1.0
 * @author Eric Malavenda
 */
public class Sudoku implements SudokuClientInterface {

	public static Sudoku sudokuGame = new Sudoku();

	public static int[][] cellValues;

	public static Sudoku getInstance() {
		return sudokuGame;
	}

	public static int[][] getCellValues() {
		return cellValues;
	}

    public static void main(String[] args) {
        cellValues = sudokuGame.boardSearch(args);
		sudokuGame.completeTurn();
		if (sudokuGame.buildPuzzle(0, 0)) { 
		    sudokuGame.completeTurn();
		}
	}

	public void setCellValue(int i, int j, int cellValue) {
	    cellValues[i][j] = cellValue;        
	}

	public int getCellValue(int i, int j) {
		return cellValues[i][j];
	}

    public boolean buildPuzzle(int i, int j) {
	    if (i == 9) {
	        i = 0;
	        if (++j == 9) { 
		        return true;
		    }
		}
		if (sudokuGame.getCellValue(i, j) != 0) {
		    return sudokuGame.buildPuzzle(i + 1, j);
		}
		
		for (int n = 1; n <= 9; ++n) {
		    if (sudokuGame.proceedWithMove(i, j, n)) {  
			    sudokuGame.setCellValue(i, j, n);
			    if (sudokuGame.buildPuzzle(i + 1, j)) {
				    return true;
				}
			}
		}
		sudokuGame.setCellValue(i, j, 0);
		return false;
    }

    public boolean proceedWithMove(int i, int j, int cellValue) {
		for (int k = 0; k < 9; ++k) {
		    if (cellValue == sudokuGame.getCellValue(k, j)) {
			    return false;
		    }
		}
		for (int k = 0; k < 9; ++k) {
		    if (cellValue == sudokuGame.getCellValue(k, j)) {
			    return false;
			}
		}
		int boardRow = (i / 3)*3;
		int boardColumn = (j / 3)*3;
		for (int l = 0; l < 3; ++l) {
		    for (int m = 0; m < 3; ++m) {
				if (cellValue == sudokuGame.getCellValue(boardRow + l, boardColumn + l)) {
				    return false;
				}
			}
	    }
	    return true;
	}

    public int[][] boardSearch(String[] givenVals) {
    	int board[][] = new int[9][9];
		for (int k = 0; k < givenVals.length; ++k) {
		    int i = Integer.parseInt(givenVals[k].substring(0,1));   
		    int j = Integer.parseInt(givenVals[k].substring(1,2));   
		    int value = Integer.parseInt(givenVals[k].substring(2,3)); 
		    board[i][j] = value;
		}
		return board;
    }

    public void completeTurn() {
    	if (cellValues != null) {
			for (int i = 0; i < 9; ++i) {
			    if (i % 3 == 0) {
				    System.out.println(" -----------------------");
			    }
			    for (int j = 0; j < 9; ++j) {
			    	if (j % 3 == 0) {
			    		System.out.print("| ");
			    	}
			    	if (sudokuGame.getCellValue(i, j) == 0) {
			    		System.out.print(" ");
			    	} else {
                        System.out.print(Integer.toString(sudokuGame.getCellValue(i, j)));
                    }
		            System.out.print(' ');
	            }
			    System.out.println("|");
			}
			System.out.println(" -----------------------");
	    }
    }

}