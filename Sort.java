/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

/**
 *
 * @author cornb
 */
public class Sort {
    int indArr[];
    int arrVal[];
    static int origArr[];
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    
    Sort(int arr[]){
        int temp[] = new int[arr.length];
        temp = arr;
        this.arrVal = new int[arr.length];
        this.arrVal = arr;
        this.origArr = new int[arr.length];
        this.origArr = temp;
        
        for(int i = 0; i < arr.length; i++){
            System.out.print(origArr[i] + " | ");
        }
        System.out.println();
        this.indArr = new int[arr.length];
        
        }
    /*
    public int[] createIndArr(int[] arr){
        
        int indArr[] = new int[arr.length];
        for(int i = 0; i < arr.length; i++){
            indArr[i] = i;
        }
        return indArr;
    }
    */
    public void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        
        int n1 = m - l + 1;
        int n2 = r - m;
        
        //int indArr[] = createIndArr(arr);
        
        
       
        
        /* Create temp arrays */
        int L[] = new int [n1];
        int R[] = new int [n2];
 
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
 
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarry array
        int k = l;
        
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];              
                i++;
                
            }
            else
            {
                arr[k] = R[j];
               
                j++;
                
            }
            k++;
            
        }
        
 
        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            ///*
            //indArr[n] = getArrayIndex(origArr,L[i]);
            i++;
            k++;
            
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            //indArr[j] = getArrayIndex(origArr,R[j]);
            j++;
            k++;
            
            
        }
        
        
        
    }
     
    // Main function that sorts arr[l..r] using
    // merge()
    public void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l+r)/2;
 
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr , m+1, r);
 
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
        this.arrVal = arr;
    }
    public int[] getIndArray(){
        return this.indArr;
    }
    public int[] getArray(){
        return this.arrVal;
    }
     public int[] getOrigArray(){
        return this.origArr;
    }
    public void printInd(int arr[]){
        for(int i =0; i < arr.length; i++){
            System.out.print(this.indArr[i] + " | ");
        }
        System.out.println();
    }
    public void printOrigInd(){
        System.out.println("Original: ");
        for(int i =0; i < getOrigArray().length; i++){
            System.out.print(this.origArr[i] + " &| ");
        }
        System.out.println();
    }
    
     public int getArrayIndex(int[] arr,int value) {

        int k=0;
        for(int i=0;i<arr.length;i++){

            if(arr[i]==value){
                k=i;
                break;
            }
        }
    return k;
}
    public void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public void setIndArray(int arr[]){
        for(int i=0; i < arr.length; i++ ){
            this.indArr[i] = getArrayIndex(this.origArr,arr[i]);
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        int arr[] = {12, 11, 13, 5, 6, 7};
        
        Sort ob = new Sort(arr);
        ob.sort(arr, 0, arr.length-1);
        ob.printArray(arr);
        ob.printOrigInd();
        ob.setIndArray(ob.getArray());
        ob.printInd(ob.getIndArray());
    }
    
}
