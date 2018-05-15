/**
 *
 * @version 1.0
 * @author Eric Malavenda
 */
public interface SudokuClientInterface {

	boolean buildPuzzle(int i, int j);

	void setCellValue(int i, int j, int cellValue);

	int getCellValue(int i, int j);

}