public class MatrixEntry {
    private int row;
    private int col;
    private int data;
    private MatrixEntry nextRow;
    private MatrixEntry nextCol;

    public MatrixEntry(int row, int col, int data) {
        this.row = row;
        this.col = col;
        this.data = data;
    }//constructor

    public int getColumn(){
        return col;
    }//get Column

    public void setColumn(int col) {
        this.col = col;
    }//set Column

    public int getRow() {
        return row;
    }//get Row

    public void setRow(int row) {
        this.row = row;
    }//set Row

    public int getData() {
        return data;
    }//get Data

    public void setData(int data) {
        this.data = data;
    }//set Data

    public MatrixEntry getNextRow() {
        return nextRow;
    }//get reference of next row

    public void setNextRow(MatrixEntry el) {
        nextRow = el;
    }//set reference of next row

    public MatrixEntry getNextCol() {
        return nextCol;
    }//get reference of next column

    public void setNextCol(MatrixEntry el) {
        nextCol = el;
    }//set reference of next column

    public boolean hasNextRow() {
        return nextRow != null;
    }

    public boolean hasNextCol() {
        return nextCol != null;
    }
}
