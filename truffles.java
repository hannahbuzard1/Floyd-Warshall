import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

class truffles { 
    
    static int getValue(int arr[][], int m, int n) { 
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
                }
                else if (j==0) {
                    dist[i][j] = Math.max(arr[i-1][j], arr[i-1][j+1]) + arr[i][j];
                }
                else if (j== n-1) {
                    dist[i][j] = Math.max(arr[i-1][j], arr[i-1][j-1]) + arr[i][j];
                }
                else {
                    dist[i][j] = Math.max(arr[i-1][j], Math.max(arr[i-1][j-1], arr[i-1][j+1])) + arr[i][j]; 
                }
            }
        }
        for (int i = 0; i< m; i++) {
            for (int j =0; j< n; j++) {
                System.out.print(dist[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        
        //get max truffles and return to main
        return dist[n-1][m-1];
    } 
  
    public static void main(String args[]) { 
    System.out.println("In main");
    //get row and column sizes from file
    int colcount = 3;
    int rowcount = 3;
    int[][] truffles = new int[rowcount][colcount];   
    try {
        System.out.println("Filling truffles");
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
