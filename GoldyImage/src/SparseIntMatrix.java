import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SparseIntMatrix {
    private int numRows;
    private int numCols;
    private MatrixEntry[] rowHeads;
    private MatrixEntry[] colHeads;

    public SparseIntMatrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        rowHeads = new MatrixEntry[numRows];
        colHeads = new MatrixEntry[numCols];
        for (int i = 0; i < numRows; i++) {
            rowHeads[i] = new MatrixEntry(i, -1, 0);
        }
        for (int i = 0; i < numCols; i++) {
            colHeads[i] = new MatrixEntry(-1, i, 0);
        }
    }

    public SparseIntMatrix(int numRows, int numCols, String inputFile) {
        this(numRows, numCols);
        try {
            File f = new File(inputFile);
            Scanner reader = new Scanner(f);
            String[] line;
            while (reader.hasNextLine()) {
                line = reader.nextLine().split(",");
                setElement(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
            }//set the situation if the input is invalid
        } catch (Exception e) {
            System.out.println("Error occured processing the input file: ");
            System.out.println(e);
        }

    }

    public int getElement(int row, int col) {
        MatrixEntry temp = rowHeads[row];
        while (temp.hasNextCol() && temp.getColumn() < col) {
            temp = temp.getNextCol();
        }
        if (temp.getColumn() == col) {
            return temp.getData();
        } else {
            return 0;
        }
    }

    public boolean setElement(int row, int col, int data) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols) {
            return false;
        }
        if (data == 0) {
            return true;
        }
        MatrixEntry tempRow = rowHeads[row];
        MatrixEntry tempCol = colHeads[col];
        MatrixEntry temp = new MatrixEntry(row, col, data);
        while (tempRow.hasNextCol() && tempRow.getNextCol().getColumn() <= col) {
            tempRow = tempRow.getNextCol();
        }
        if (tempRow.getColumn() == col) {
            tempRow.setData(data);
        } else {
            temp.setNextCol(tempRow.getNextCol());
            tempRow.setNextCol(temp);
        }

        while (tempCol.hasNextRow() && tempCol.getNextRow().getRow() <= row) {
            tempCol = tempCol.getNextRow();
        }
        if (tempCol.getRow() == row) {
            tempCol.setData(data);
        } else {
            temp.setNextRow(tempCol.getNextRow());
            tempCol.setNextRow(temp);
        }
        return true;
    }

    public boolean removeElement(int row, int col, int data) {
        if (row < 0 || row >= numRows || col < 0 || col >= numCols || data == 0) {
            return false;
        }
        MatrixEntry tempRow = rowHeads[row];
        MatrixEntry tempCol = colHeads[col];
        while (tempRow.hasNextCol() && tempRow.getNextCol().getColumn() < col) {
            tempRow = tempRow.getNextCol();
        }
        if (tempRow.hasNextCol() && tempRow.getNextCol().getColumn() == col) {
            tempRow.setNextCol(tempRow.getNextCol().getNextCol());
        } else {
            return false;
        }

        while (tempCol.hasNextRow() && tempCol.getNextRow().getRow() < row) {
            tempCol = tempCol.getNextRow();
        }
        if (tempCol.hasNextRow() && tempCol.getNextRow().getRow() == row) {
            tempCol.setNextRow(tempCol.getNextRow().getNextRow());
        } else {
            return false;
        }
        return true;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public boolean plus(SparseIntMatrix otherMat) {
        if (this.getNumRows() != otherMat.getNumRows() || this.getNumCols() != otherMat.getNumCols()) {
            return false;
        }
        int data;
        for (MatrixEntry temp : otherMat.rowHeads) {
            while (temp.hasNextCol()) {
                temp = temp.getNextCol();
                data = getElement(temp.getRow(), temp.getColumn());
                data += temp.getData();
                // Assuming every element in the other Matrix is valid and in the range.
                setElement(temp.getRow(), temp.getColumn(), data);
            }
        }
        return true;

    }

    public boolean minus(SparseIntMatrix otherMat) {
        if (this.getNumRows() != otherMat.getNumRows() || this.getNumCols() != otherMat.getNumCols()) {
            return false;
        }
        int data;
        for (MatrixEntry temp : otherMat.rowHeads) {
            while (temp.hasNextCol()) {
                temp = temp.getNextCol();
                data = getElement(temp.getRow(), temp.getColumn());
                data -= temp.getData();
                // Assuming every element in the other Matrix is valid and in the range.
                setElement(temp.getRow(), temp.getColumn(), data);
            }
        }
        return true;
    }
}
