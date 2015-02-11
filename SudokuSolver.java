
public class SudokuSolver {
	private static int SOLVED=1;
	private static int UNSOLVABLE=-1;
	private static int NOTYETSOLVED=0;
	
	public static OutputObject sudokuSolver(Cell[][] inputSudoku){
		int status = arcConsistency(inputSudoku);
		
		if(status==NOTYETSOLVED){
			Cell[][] newInputSudoku = new Cell[9][9];
			int x=0, y=0;
			boolean flag=true;
			for(int i=0; i<9; i++)
				for(int j=0; j<9; j++){
					newInputSudoku[i][j]=new Cell(inputSudoku[i][j]);
					if(flag && newInputSudoku[i][j].getValue()==-1){
						x=i;
						y=j;
						flag=false;
					}
				}
			for(int k : newInputSudoku[x][y].getPossibleValues())
				newInputSudoku[x][y].setValue(k);
			return sudokuSolver(newInputSudoku);
		}
		else if(status==UNSOLVABLE)
			return new OutputObject(status, null);
		else
			return new OutputObject(status, inputSudoku);
	}

	public static int arcConsistency(Cell[][] inputSudoku){
		boolean endLoop = true;	//repeat loop if a value is set in any of the cells in the current iteration, else stop
		
		do{
			endLoop = true;
			
			for(int i=0; i<9; i++){
				for(int j=0; j<9; j++){		//parse each cell l->r, t->b
					boolean changed = false;	//used to break the loop if value of current cell is determined
					if(inputSudoku[i][j].getValue()==-1){	//choose the cells whose values are unknown and determine the set of possible values for these cells
						
						//work-around since we cannot manipulate the cell values when using the iterator pointing to the cell whose values we are updating
						int[] valueSet = new int[inputSudoku[i][j].getPossibleValues().size()];
						int t=0;
						for(int val: inputSudoku[i][j].getPossibleValues())
							valueSet[t++]=val;
						
						for(t=0; t<valueSet.length; t++){	//loop through each possible values of the current cell
							int temp=valueSet[t];
							
							boolean repeated = false;	//used to check if the value is present as a fixed value in another cell or a possible value in an unknown cell
														//if the value is unique to this cell in the given row/col/box then that value is fixed to the current cell
							//parse column
							for(int k=0; k<9; k++){
								if(inputSudoku[k][j].getValue()==-1){	//used to check if the value is present as a possible value in an unknown cell
									if(i!=k && inputSudoku[k][j].getPossibleValues().contains(temp))
										repeated=true;
								}
								else{
									if(temp==inputSudoku[k][j].getValue()){	//used to check if the value is present as a fixed value in another cell
										repeated=true;
										inputSudoku[i][j].remove(temp);
										changed=true;	//if the value is found to be a fixed value of another cell then it is removed from the set  of possible values 
														//of current cell, after removal if only one possible value is left then it is set as the current cell's value 
														//in Cell.remove() method and hence changed flag is set
									}
								}
							}
							if(!repeated){	//if the value is unique to this cell in the given column then that value is fixed to the current cell
								inputSudoku[i][j].setValue(temp);
								changed=true;
								break;
							}
							
							repeated = false;
							for(int k=0; k<9; k++){		//same logic for row
								if(inputSudoku[i][k].getValue()==-1){
									if(j!=k && inputSudoku[i][k].getPossibleValues().contains(temp))
										repeated=true;
								}
								else{
									if(temp==inputSudoku[i][k].getValue()){
										repeated=true;
										inputSudoku[i][j].remove(temp);
										changed=true;
									}
								}
							}
							if(!repeated){
								inputSudoku[i][j].setValue(temp);
								changed=true;
								break;
							}

							repeated = false;	//similar logic for box
							int boxX = ((int)(i/3))*3, boxY = ((int)j/3)*3;		//determines the upper left position of the box which contains the present cell
							for(int x=boxX; x<boxX+3; x++){
								for(int y=boxY; y<boxY+3; y++){
									if(inputSudoku[x][y].getValue()==-1){
										if(!(x==i && y==j) && inputSudoku[x][y].getPossibleValues().contains(temp))
											repeated=true;
									}
									else{
										if(temp==inputSudoku[x][y].getValue()){
											repeated=true;
											inputSudoku[i][j].remove(temp);
											changed=true;
										}
									}
								}
							}
							if(!repeated){
								inputSudoku[i][j].setValue(temp);
								changed=true;
								break;
							}
						}
						
						if(changed && inputSudoku[i][j].getPossibleValues().size()==1)
							inputSudoku[i][j].setValue(inputSudoku[i][j].getPossibleValues().first());						
					}
					
					//repeat loop if a value is set in any of the cells in the current iteration, else stop
					if(endLoop && changed)	endLoop=false;
				}
			}			
		}while(!endLoop);
		
		int result=SOLVED;
		for(int i=0; i<9; i++)
			for(int j=0; j<9; j++)
				if(inputSudoku[i][j].getValue()==-1){
					if(inputSudoku[i][j].getPossibleValues().size()==0)
						return UNSOLVABLE;
					result=NOTYETSOLVED;
				}
		
		return result;
	}
}
