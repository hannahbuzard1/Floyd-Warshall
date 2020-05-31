import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

class truffles { 
    
    int getValue(int arr[][], int m, int n) { 
        //initalize resulting array
        int[][] dist = new int[m][n]; //not correct syntax, fix this
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                dist[i][j] = 0;
            }
        }
        for (int j = 1; j<= m; j++) {
            for (int i =0; i<= n; i++) {
                if (i==0 && j==0) {
                    dist[i][j] = arr[i][j];
                }
                else if(i==0) {
                    dist[i][j] = arr[i][j];
                }
                else if (j==0) {
                    dist[i][j] = Math.max(arr[i-1][j], arr[i+1][j+1]);
                }
                else {
                    dist[i][j] = Math.max(arr[i-1][j], Math.max(arr[i-1][j-1], arr[i+1][j+1])); 
                }
            }
        }
        //get path and print path
        
        //get max truffles and return to main
        return dist[n-1][m-1];
    } 
  
    public static void main(String args[]) { 
    //get row and column sizes from file
    FileReader readfile = new FileReader(args[0]);
    BufferedReader rowreader = new BufferedReader(readfile);
    int colcount = 0;
    int rowcount = 0;
    Scanner input = new Scanner (new File("src/array.txt"));
    while(rowreader.hasNextLine()) {
        rowcount = rowcount + 1;
        Bufferedreader colReader = new BufferedReader(input.nextLine());
        while(colReader.hasNextInt()) {
            colcount = colcount + 1;
        }
    }
    int[][] truffles = new int[rowcount][colcount];   
    //fill in matrix using file
    BufferedReader readmatrix = new BufferedReader(readfile);
    for(int i = 0; i < rowcount; i++) {
        for(int j = 0; j < colcount; j++) {
            if(input.hasNextInt()) {
                truffles[i][j] = input.nextInt();
            }
        }
    }
    //print matrix to check for correctness
    for(int i = 0; i < rowcount; i++) {
        for(int j = 0; j < colcount; j++) {
            System.out.println(truffles[i][j]);
        }
    }
    //make call to function to find max path 
    int max = getValue(truffles, m, n);
    System.out.println("Maximum truffles picked: ");
    System.out.println(max);
    
    }
}
