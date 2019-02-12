import java.io.*;
import java.util.Scanner;
public class Driver {
	
	public static void main(String args[]) throws IOException{
		System.out.println("Hello world");
		
		File file = new File("image/baboon.pgma");
		
		Scanner scan = new Scanner(file);
		String imgInfo = null;
		int width=0;
		int height=0;
		int maxValue=0;
		int data[][] = null;
		if(scan.nextLine().equals("P2")){
			if(scan.next().equals("#"))
				imgInfo = "#" + scan.nextLine();
			width = Integer.parseInt(scan.next());
			height = Integer.parseInt(scan.next());
			maxValue = Integer.parseInt(scan.next());
			data = new int[width][height];
			
			for(int i = 0; i < height; i++){
				for(int j = 0; j < width; j++){
					data[i][j] = Integer.parseInt(scan.next());
				}
			}
		}
		else{
			System.out.println("Not the right kind of file.");
		}
		
		int temp[][];
		ImageMan creator = new ImageMan();
		
		//***********************************************
		//Create Array for the image manipulator
		creator.setArr(data,width,height);
		//Create New array with quadrants
		creator.createQuad(200, 0, width, 0, height);
		//Apply the new array for data transfer
		temp = creator.getArr();
		//***********************************************
		
		//********DEBUGGING**********
		/*
		for(int i = 0; i<height;i++){
			for(int j=0; j<width;j++){
				System.out.print(temp[i][j] + " ");
			}
			System.out.print("\n");
		}
		*/
		FileWriter outFile = new FileWriter("image/out_baboon.pgma");
		BufferedWriter writer = new BufferedWriter(outFile);
		
		writer.write("P2\n");
		writer.write(imgInfo+"\n");
		writer.append(String.valueOf(width + " ")); 
		writer.append(String.valueOf(height)+"\n");
		writer.append(String.valueOf(maxValue+"\n"));
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				writer.append(String.valueOf(temp[i][j])+ " ");
			}
			
		}
		writer.close();
	}
}
