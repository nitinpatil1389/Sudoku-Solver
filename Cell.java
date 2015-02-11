import java.util.TreeSet;


public class Cell {
	private int value;
	private TreeSet<Integer> possibleValues = new TreeSet<Integer>();
	
	public Cell(){
		value=-1;
		for(int i=0; i<9; i++)
			possibleValues.add(i+1);
	}
	
	public Cell(int value){
		this.value=value;
	}
	
	public Cell(Cell copy){
		this.value=copy.value;
		for(int i:copy.getPossibleValues())
			this.getPossibleValues().add(i);
	}
	
	public boolean add(int value){
		return possibleValues.add(value);
	}
	
	public void remove(int value){
		possibleValues.remove(value);
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * @return the possibleValues
	 */
	public TreeSet<Integer> getPossibleValues() {
		return possibleValues;
	}

	/**
	 * @param possibleValues the possibleValues to set
	 */
	public void setPossibleValues(TreeSet<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}
}
