
public class ImageMan {
	
	private static int data[][];

	//Check the sub array for the right pixels within the threshold
	public static boolean checkQuad(int thresh, int minWidth, int maxWidth, int minHeight, int maxHeight){
		
		//Set The matching pixel
		int match = data[minWidth][minHeight];
		
		for(int i = minHeight; i < maxHeight; i++){
			for(int j = minWidth; j < maxWidth; j++){
				
				//Check if each pixel is within threshold
				if((data[i][j] > match - thresh && data[i][j] < match + thresh) || data[i][j] == match)
					continue;
				else
					return false;
			}
		}
		
		return true;
	}
	
	public static int avgValue(int minWidth, int maxWidth, int minHeight, int maxHeight){
		
		int count = 0;
		int numberOfEntries = (maxWidth - minWidth) * (maxHeight - minHeight);

		for(int i = minHeight; i < maxHeight; i++){
			for(int j = minWidth; j < maxWidth; j++){
				count+= data[i][j];
			}
		}
		
		return count/numberOfEntries;
	}
	
	
	public static int midPoint(int demMin, int demMax){
		return (demMax-demMin)/2;
	}
	public static void createQuad(int thresh, int minWidth, int maxWidth, int minHeight, int maxHeight){
		
		if(checkQuad(thresh,minWidth,maxWidth,minHeight,maxHeight)){
			if(thresh == 0){
				return;
			}
			else{
				int avg = avgValue(minWidth,maxWidth,minHeight,maxHeight);
				for(int i = minHeight; i < maxHeight; i++){
					for(int j = minWidth; j < maxWidth; j++){
						//*****************************************
						data[i][j] = avg; // ISSUE SEEMS TO BE HERE
						//*****************************************
						
						//********DEBUGGING**********
						/*
						for(i = minHeight; i < maxHeight; i++){
							for(j = minWidth; j < maxWidth; j++){
								System.out.print((data[i][i]) + " ");
							}
						}
						*/
						return;
					}
				}
			}
		}
		else{
			//Split the array into quadrants 
			
			//Q0 top Left
			createQuad(thresh,minWidth,minWidth + midPoint(minWidth,maxWidth),minHeight, minHeight + midPoint(minHeight,maxHeight));
			//Q1 Top Right
			createQuad(thresh,maxWidth - midPoint(minWidth,maxWidth),maxWidth,minHeight, minHeight + midPoint(minHeight,maxHeight));
			//Q2 Bottom right
			createQuad(thresh,maxWidth - midPoint(minWidth,maxWidth),maxWidth,maxHeight - midPoint(minHeight,maxHeight), maxHeight);
			//Q3 Bottom Left
			createQuad(thresh,minWidth,minWidth + midPoint(minWidth,maxWidth),maxHeight - midPoint(minHeight,maxHeight), maxHeight);
			
		}
		
	}
	
	public void setArr(int arr[][],int width,int height){ 
		data = new int[height][width];
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++){
				data[i][j] = arr[i][j]; 
			}
	}
	public int[][] getArr(){ return data; }
	public static void main(String[] args){
		
	}
}
