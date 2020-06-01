import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

class truffles { 
    
    static int getValue(int arr[][], int m, int n) { 
        int[][] pred = new int[m+1][n];
        for (int i = 0; i< m; i++) {
            for (int j =0; j< n; j++) {
                pred[i][j] = 0;
            }
        }
        //initalize resulting array
        int[][] dist = new int[m][n]; //not correct syntax, fix this
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                dist[i][j] = 0;
            }
        }
        for (int i = 0; i< m; i++) {
            for (int j =0; j< n; j++) {
                if(i==0) {
                    dist[i][j] = arr[i][j];
                    pred[i][j] = arr[i][j];
                }
                else if (j==0) {
                    if(Math.max(arr[i-1][j], arr[i-1][j+1]) == arr[i-1][j]) {
                        dist[i][j] = arr[i][j] + dist[i-1][j];
                        pred[i][j] = arr[i-1][j];
                    } else {
                        dist[i][j] = arr[i][j] + dist[i-1][j+1];
                        pred[i][j] = arr[i-1][j+1];
                    }
                }
                else if (j== n-1) {
                    if ( Math.max(arr[i-1][j], arr[i-1][j-1]) == arr[i-1][j]) {
                        dist[i][j] = arr[i][j] + dist[i-1][j];
                        pred[i][j] = arr[i-1][j];
                    } else {
                        dist[i][j] = arr[i][j] + dist[i-1][j-1];
                        pred[i][j] = arr[i-1][j-1];
                    }
                }
                else {
                    if(Math.max(arr[i-1][j], Math.max(arr[i-1][j-1], arr[i-1][j+1])) == arr[i-1][j]) {
                        dist[i][j] = arr[i][j] + dist[i-1][j];
                        pred[i][j] = arr[i-1][j];
                    } else if (Math.max(arr[i-1][j], Math.max(arr[i-1][j-1], arr[i-1][j+1])) == arr[i-1][j-1]) {
                        dist[i][j] = arr[i][j] + dist[i-1][j-1];
                        pred[i][j] = arr[i-1][j-1];
                    } else {
                        dist[i][j] = arr[i][j] + dist[i-1][j+1];
                        pred[i][j] = arr[i-1][j+1];
                    }
                }
            }
        }
        int ivar = 3;
        for(int j=0; j<n; j++) {
            if(j==0) {
                if(Math.max(arr[ivar-1][j], arr[ivar-1][j+1]) == arr[ivar-1][j]) {
                    pred[m][j] = arr[ivar-1][j];
                } else {
                    pred[m][j] = arr[ivar-1][j+1];
                }    
            }
            else if(j== n-1) {
            if ( Math.max(arr[ivar-1][j], arr[ivar-1][j-1]) == arr[ivar-1][j]) {
                    pred[m][j] = arr[ivar-1][j];
                } else {
                    pred[m][j] = arr[ivar-1][j-1];
                }    
            } else {
                if(Math.max(arr[ivar-1][j], Math.max(arr[ivar-1][j-1], arr[ivar-1][j+1])) == arr[ivar-1][j]) {
                    pred[m][j] = arr[ivar-1][j];
                } else if (Math.max(arr[ivar-1][j], Math.max(arr[ivar-1][j-1], arr[ivar-1][j+1])) == arr[ivar-1][j-1]) {
                    pred[m][j] = arr[ivar-1][j-1];
                } else {
                    pred[m][j] = arr[ivar-1][j+1];
                }    
            }
        }
        System.out.println("Path:");
        for (int i = m; i > 0; i--) {
            int max = 0;
            for(int j = n-1; j >= 0; j--) {
                if (pred[i][j] > max) {
                    max = pred[i][j];
                }
            }
            System.out.print(max);
            System.out.print(" ");
        }
        System.out.println("");
        
        //get max truffles and return to main
        return dist[n-1][m-1];
    } 
  
    public static void main(String args[]) { 
    //get row and column sizes from file
    int colcount = 3;
    int rowcount = 3;
    int[][] truffles = new int[rowcount][colcount];   
    try {
        //fill in matrix using file
        Scanner input = new Scanner(new File("test.txt"));
        for(int i = 0; i < rowcount; ++i) {
            for(int j = 0; j < colcount; ++j) {
                if(input.hasNextInt()) {
                    truffles[i][j] = input.nextInt();
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    //make call to function to find max path 
    int max = getValue(truffles, rowcount, colcount);
    System.out.println("Maximum truffles picked: ");
    System.out.println(max);
    
    }
}
