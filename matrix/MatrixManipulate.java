/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrix;
/**
 *
 * @author michaelcorbett
 */
public class MatrixManipulate {
    
    public Matrix matrixMult(Matrix mat1, Matrix mat2){
        
        int mat1Row = mat1.getRow(); int mat1Column = mat1.getColumn();
        int mat2Row = mat2.getRow(); int mat2Column = mat2.getColumn();
        System.out.println( "Mat 1 row: " + mat1Row + " | Mat 1 column: " + mat1Column + "\n" +
                            "Mat 2 row: " + mat1Row + " | Mat 2 column: " + mat1Column 
                            );
        
        int temp[] = new int[mat1Column];
        int result = 0;
        
        Matrix mat3 = new Matrix(mat1Row, mat2Column, true);
        
        if(mat1Column == mat2Row){
            
            for(int i = 0; i < mat1Row; i++){
                for(int j = 0; j < mat2Column; j++){
                    for(int k = 0; k < mat1Column; k++){
                        System.out.println(mat1.getAtDem(i, k) + " * " + mat2.getAtDem(k, j) + " | ");
                        temp[k] = mat1.getAtDem(i, k) * mat2.getAtDem(k, j);
                    }
                    for(int l = 0; l < mat1Column; l++){
                        result += temp[l];
                    }
                    //System.out.println("Result: "+ result);
                    mat3.setAtCell(i,j,result);
                    result = 0;
                    /*
                    System.out.println("Result: "+ result);
                    System.out.println("Multiplied value: "+mat3.getAtDem(i,j));
                    */
                }
                
            }
            
        }
       return mat3;
    }
    
    public  Matrix matrixAdd(Matrix mat1,Matrix mat2){
        
        int mat1Row = mat1.getRow(); int mat1Column = mat1.getColumn();
        int mat2Row = mat2.getRow(); int mat2Column = mat2.getColumn();
        
        int result = 0;
        
        Matrix mat3 = new Matrix(mat1Row, mat2Column, true);
        for(int i = 0; i < mat1Row; i++){
            for(int j = 0; j < mat2Column; j++){
                System.out.println(mat1.getAtDem(i, j) + " + " + mat2.getAtDem(i, j) + " | ");
                result = mat1.getAtDem(i, j) + mat2.getAtDem(i, j);
                mat3.setAtCell(i, j, result);
                
            }
        }
        return mat3;
    }
    public static void main(String[] args){}
}
