import java.lang.Math;
import java.util.Arrays;
import java.util.*;
import java.lang.*;
import java.util.Collections;

class truffles { 
    
    int getValue(int arr[][], int m, int n) { 
        //initalize resulting array
        int dist[m][n]; //not correct syntax, fix this
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                dist[i][j] = 0;
            }
        }
        for j in range(1,m):
            for i in range(1,n):
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
                dist[i][j] = Math.max(arr[i-1][j], Math.max(arr[i-1][j-1], arr[i+1][j+1]); 
            }
        //get path and print path
        backtrace(dist, m,n);
        //get max truffles and return to main
        return dist[n-1][m-1]
    } 
    public int[] backtrace(int trace[][], int m, int n) {
        //base case
        if (i == 0 and j == 0) {
            return;
        }
        down = dist[i-1][j]
        diagonalright = dist[i][j-1]
        diagonalleft = dist[i-1][j-1]
    
        if(min(left,up,diagonal) == down):
            BT.append('i')
            backtrace(dist, i-1,j)
        if(min(left,up,diagonal) == leftdiagonal):
            BT.append('d')
            backtrace(dist,i,j-1)
        if(min(left,up,diagonal) == rightdiagonal):
            BT.append('s')
            backtrace(dist, (i-1), j-1)    
        //need to reverse list
    }
  
    public static void main(String args[]) { 
    //get row and column sizes from file
    FileReader readfile = new FileReader(args[0]);
    BufferedReader rowreader = new BufferedReader(readfile);
    colcount = 0;
    rowcount = 0;
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
