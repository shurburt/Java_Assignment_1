import java.io.*;
import java.util.Scanner;

public class imageCompressor {

	public static int imageData[][];
	
	public static boolean checkQuadrant(int threshold, int minWidth, int maxWidth, int minHeight, int maxHeight) {
		
		int numberOfPixels = (maxWidth-minWidth)*(maxHeight - minHeight);
		int average = average(minWidth, maxWidth, minHeight, maxHeight);
		double tempAverage = 0;
		double variance = 0;
		
		for(int i = minHeight; i < maxHeight; i++) {
			for(int j = minWidth; j < maxWidth; j++){
				tempAverage += Math.pow((imageData[i][j] - average), 2);
			}
		}

		variance = tempAverage/(numberOfPixels-1);
		
		if (variance <= threshold) {
			return true;
		} else {
		return false;
		}
		
	}
	
	public static int average(int minWidth, int maxWidth, int minHeight, int maxHeight) {
		
		int counter = 0;
		int numberOfPixels = (maxWidth-minWidth)*(maxHeight - minHeight);
		
		for(int i = minHeight; i < maxHeight; i++) {
			for(int j = minWidth; j < maxWidth; j++){
				counter += imageData[i][j];
			}
		}
		
		int average = (counter/numberOfPixels);
		return average;
		
	}
	
	public static int midpoint(int min, int max) {
		int midpoint = (max-min)/2;
		return midpoint;
	}
	
	public static void createNewQuadrant(int threshold, int minWidth, int maxWidth, int minHeight, int maxHeight) {
		
		if (threshold == 0) return;
		
		if ((maxWidth - minWidth) == 1 && (maxHeight - minHeight) == 1) {
			return;
		}
		
		if (checkQuadrant(threshold, minWidth, maxWidth, minHeight, maxHeight)) {
			int average = average(minWidth, maxWidth, minHeight, maxHeight);
			
			for(int i = minHeight; i < maxHeight; i++) {
				for(int j = minWidth; j < maxWidth; j++){
					imageData[i][j] = average;
				}
			}
			
			return;
		}
		else {
			
			createNewQuadrant(threshold, minWidth, (minWidth + midpoint(minWidth,maxWidth)), minHeight, (minHeight + midpoint(minHeight, maxHeight)));
			
			createNewQuadrant(threshold, (maxWidth - midpoint(minWidth,maxWidth)), maxWidth, minHeight, (minHeight + midpoint(minHeight, maxHeight)));
			
			createNewQuadrant(threshold, (maxWidth - midpoint(minWidth, maxWidth)), maxWidth, (maxHeight - midpoint(minHeight, maxHeight)), maxHeight);
			
			createNewQuadrant(threshold, minWidth, (minWidth + midpoint(minWidth, maxWidth)), (maxHeight - midpoint(minHeight, maxHeight)), maxHeight);
			
		}
	}
	
	public static void setDataArray(int array[][], int width, int height) {
		imageData = new int[height][width];
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				imageData[i][j] = array[i][j];
			}
		}
	}
	
	public static int[][] getImageDataArray() {
		return imageData;
	}
	
	public static void main(String args[]) throws IOException {
		
		File image = new File("image/baboon.pgma");
		Scanner imageScan = new Scanner(image);
		String imageInfo = null;
		int width = 0;
		int height = 0;
		int maxValue = 0;
		int threshold = 512;
		int currentImageData[][] = null;
		
		if(imageScan.nextLine().equals("P2")) {
			if (imageScan.next().equals("#")) {
				imageInfo = "#" + imageScan.nextLine();
			}
			
			width = Integer.parseInt(imageScan.next());
			height = Integer.parseInt(imageScan.next());
			maxValue = Integer.parseInt(imageScan.next());
			currentImageData = new int[width][height];
			
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					currentImageData[i][j] = Integer.parseInt(imageScan.next());
				}
			}
			imageScan.close();
		} else {
			imageScan.close();
			System.out.println("\nWrong file type, please check image and try again.\n");
			return;
		}
		
		int temporaryArray[][];

		imageCompressor.setDataArray(currentImageData, width, height);
		imageCompressor.createNewQuadrant(threshold, 0, width, 0, height);
		temporaryArray = imageCompressor.getImageDataArray();
		
		FileWriter output = new FileWriter("image/baboon(compression=" + threshold + ").pgma");
		BufferedWriter writer = new BufferedWriter(output);
		
		writer.write("P2\n");
		writer.write(imageInfo + "\n");
		writer.append(String.valueOf(width) + " " + String.valueOf(height) + "\n");
		writer.append(String.valueOf(maxValue + "\n"));
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < width; j++) {
				writer.append(String.valueOf(temporaryArray[i][j]) + " ");
			}
		}
		
		writer.close();
		System.out.println("Image Compressed.\n");
	}
	
}
