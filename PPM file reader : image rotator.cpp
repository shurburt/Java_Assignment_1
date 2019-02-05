#include<iostream>
#include<fstream>
#include<cstdlib>
#include<string>

using namespace std;

int main(){
    
    cout << "Welcome to the Mini Photoshop App" << endl;
    cout << "Enter data file name (ppm): ";
    string fileName;
    cin >> fileName;
    fstream dataFile;
    dataFile.open(fileName, fstream::in);   // Here we are opening data file for reading data
    //error checking
    if (!dataFile) {
        cout << "File does not exist" << endl;
    }
    
    fstream outputFile;
    string outputFileName = "OutputFile_"+fileName;
    outputFile.open(outputFileName, fstream::out);
    if (!outputFile) {
        cout << "Out File Opening error ";
    }

    // Check Image Type
    string type;
    dataFile >> type;
    if (type == "P3") {
        cout << "Picture Type: " << type << endl;
    }
    else {
        cout << "Wrong file data" << endl;
    }

    // Check Image Width
    int width;
    dataFile >> width;
    cout << "Image Width: " << width << endl;

    // Check Image Height
    int height;
    dataFile >> height;
    cout << "Image Height: " << height << endl;

    // Check Max Value
    int maxValue;
    dataFile >> maxValue;
    cout << "Max Image Value: " << maxValue << endl;

    int data[height][width][3];
    
    // Read Pixel RGB Data
    for (int i = 0; i <= height; i++) {
        for(int j = 0; j <= width-1; j++) {
            for (int X = 0; X < 3; X++) {
                dataFile >> data[i][j][X];
            }
        }
    }
    
    int rotatedImage[width][height][3];
    
    // Swap Matrix Diagonally
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            for (int X = 0; X < 3; X++) {
                rotatedImage[j][i][X] = data[i][j][X];
            }
        }
    }
    
    for (int i = 0; i < width; i++) {
        for (int j = 0; j < height/2; j++) {
            for (int X = 0; X < 3; X++) {
                swap(rotatedImage[i][j][X], rotatedImage[i][height-j][X]);
            }
        }
    }
    
    outputFile << type << endl;
    outputFile << height << " ";
    outputFile << width << endl;
    outputFile << maxValue << endl;
    
    for(int i = 0; i < width; i++){
        for(int j = 0; j < height; j++){
            for (int X = 0; X < 3; X++) {
                outputFile << rotatedImage[i][j][X] << endl;
            }
        }
    }
    
    dataFile.close();
    outputFile.close();
    return 0;
}
