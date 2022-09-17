
public class SparseIntMatrixTest {
    public static void main(String[] args) {
        SparseIntMatrix matrix1 = new SparseIntMatrix(1000, 1000, "src/matrix1_data.txt");
        SparseIntMatrix matrix2 = new SparseIntMatrix(1000, 1000, "src/matrix2_data.txt");
        SparseIntMatrix matrix2Noise = new SparseIntMatrix(1000, 1000, "src/matrix2_noise.txt");//get an input matrix2_noise.txt into the function
        matrix2Noise.minus(matrix2);
        MatrixViewer.show(matrix2Noise);
        MatrixViewer.show(matrix1);
        matrix1.minus(matrix2);
        MatrixViewer.show(matrix1);

    }
}