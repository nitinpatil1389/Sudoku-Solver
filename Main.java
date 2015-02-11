import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;


public class Main {

	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
			Cell[][] sudoku = new Cell[9][9];

			for(int i=0; i<9; i++){
				String str = br.readLine();
				for(int j=0; j<9; j++){
					char value = str.charAt(j);
					if(value=='*')
						sudoku[i][j]=new Cell();
					else{
						String num=""+value;
						sudoku[i][j]=new Cell(Integer.parseInt(num));
					}
				}
			}

			/*for(int i=0; i<9; i++){
				for(int j=0; j<9; j++)
					System.out.print(sudoku[i][j].getValue()+",");
				System.out.println();
			}*/

			br.close();
			
			OutputObject output=SudokuSolver.sudokuSolver(sudoku);
			
			//System.out.println("Status: "+output.getStatus());
			PrintWriter writer = new PrintWriter("output.txt");
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){
					int value = output.getOutputSudoku()[i][j].getValue();
					if(value==-1)
						writer.print("* ");
					else
						writer.print(value+" ");
				}
				writer.println();
			}
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
