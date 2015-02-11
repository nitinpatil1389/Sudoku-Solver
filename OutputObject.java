
public class OutputObject {
	private int status;
	private Cell[][] outputSudoku;
	
	public OutputObject(int status, Cell[][] sudoku) {
		this.status = status;
		this.outputSudoku = sudoku;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the outputSudoku
	 */
	public Cell[][] getOutputSudoku() {
		return outputSudoku;
	}

	/**
	 * @param outputSudoku the outputSudoku to set
	 */
	public void setOutputSudoku(Cell[][] outputSudoku) {
		this.outputSudoku = outputSudoku;
	}
}
