#include<iostream>
#include<fstream>
#include<cstdlib>
#include<string>

using namespace std;

int main(){
    
    cout << "Welcome to the Mini Photoshop App!" << endl;
    cout << "Please enter the file name (including extension): ";
    string fileName;
    cin >> fileName;
    fstream dataFile;
    dataFile.open(fileName);   // Here we are opening data file for reading data
    //error checking
    if (!dataFile) {
        cout << "File does not exist" << endl;
    }
    
    fstream outputFile;
    string outputFileName = "OutputFile_"+fileName;
    outputFile.open(outputFileName, fstream::out);
    if (!outputFile) {
        cout << "Error opening output\n";
    }
    
    // Check Image Type
    string type;
    dataFile >> type;
    if (type == "P2") {
        cout << "Picture Type: " << type << endl;
    }
    else {
        cout << "Wrong file data" << endl;
    }
    
    
    string commentCheck;
    
    getline(dataFile, commentCheck);
    getline(dataFile, commentCheck);
    
    cout << commentCheck << endl;
    cout << "Hello!";
    int width;
    
    if (commentCheck.at(0) == '#') {
        dataFile >> width;
    }
    else {
        width = atoi(commentCheck.c_str());
    }
    
    
   // dataFile >> width;
    cout << "Image Width: " << width << endl;
    
    // Check Image Height
    int height;
    dataFile >> height;
    cout << "Image Height: " << height << endl;
    
    // Check Max Value
    int maxValue;
    dataFile >> maxValue;
    cout << "Max Image Value: " << maxValue << endl;
    
    int data[height][width][1];
    
    // Read Pixel RGB Data
    for (int i = 0; i <= height; i++) {
        for(int j = 0; j <= width-1; j++) {
                dataFile >> data[i][j][0];
        }
    }
    
    outputFile << type << endl;
    outputFile << width << " ";
    outputFile << height << endl;
    outputFile << maxValue << endl;
    
    for(int i = 0; i < height; i++){
        for(int j = 0; j < width; j++){
                outputFile << data[i][j][0] << " ";
        }
    }
    
    dataFile.close();
    outputFile.close();
    return 0;
}
