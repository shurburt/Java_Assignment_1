package matrix;
import java.util.Arrays;
import java.util.Random;
import matrix.MatrixManipulate.*;

public class Matrix {
    int row; int column;
    int[][] matrix;
    
    Matrix(int row, int column){
        this.matrix = new int[row][column];
        createMatrix(row,column);
        this.row = row;
        this.column = column;
    }
    Matrix(int row, int column, boolean empty){
        if(empty){
            createEmptyMat(row,column);
        }
    }
    Matrix(int row, int column, int value){
        createTestMat(row,column,value);
    }
    
    public void createMatrix(int row, int column){
        
        Random rand = new Random();
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++ ){
                this.matrix[i][j] = rand.nextInt(6 - 0) + 0;
            }
        }  
    }
    public void createEmptyMat(int row, int column){
        this.matrix = new int[row][column];
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++ ){
                this.matrix[i][j] = 0;
            }
        }
        
    }
    public void createTestMat(int row, int column, int value){
        this.row = row; 
        this.column = column;
        
        this.matrix = new int[row][column];
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++ ){
                this.matrix[i][j] = value;
            }
        } 
    }
    
    public void printMatrix(){
        System.out.println(Arrays.deepToString(this.matrix));
    }
    
    
    public int[][] getMatrix(){
        return this.matrix;
    } 
    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }
    public void setAtCell(int row, int column, int value){
        this.matrix[row][column] = value;
    }
    public int getAtDem(int row, int column){
        return this.matrix[row][column];
    }
    
    public static void main(String[] args) {
        
        int row = 3; int column = 3;
        Matrix mat1 = new Matrix(row,column);
        Matrix mat2 = new Matrix(row,column);
        mat1.printMatrix();
        mat2.printMatrix();
        MatrixManipulate manip = new MatrixManipulate();
        Matrix mat3 = manip.matrixMult(mat1, mat2);
        Matrix mat4 = manip.matrixAdd(mat1,mat2);
        mat3.printMatrix();
        mat4.printMatrix();
    }
    
}
