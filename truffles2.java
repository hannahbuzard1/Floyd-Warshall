import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

public class truffles2 {
    public static int colnumber;
    public static int INF = 99999;
    public static ArrayList<Integer> path = new ArrayList<Integer>();
    public static ArrayList<Integer> currentpath = new ArrayList<Integer>();
    public static int maxpath;
    public static void FloydWarshall(int[][] matrix, int nodes) {
       int dist[][] = new int[nodes][nodes]; 
       int pred[][] = new int [nodes][nodes];
        int i, j, k; 

        for (i = 0; i < nodes; i++) {
            for (j = 0; j < nodes; j++) {
                dist[i][j] = matrix[i][j]; 
                pred[i][j] = i;
            }
        }
                
        for (k = 0; k < nodes; k++) { 
            // Pick all vertices as source one by one 
            for (i = 0; i < nodes; i++)  { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < nodes; j++) { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] > dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                        pred[i][j] = pred[i][k];
                    }
                } 
            } 
        }
        System.out.println("Pred matrix:");
        for (i = 0; i < nodes; i++) {
            for (j = 0; j < nodes; j++) {
                System.out.print(pred[i][j]); 
                System.out.print(" "); 
            }
            System.out.println("");
        }
        int max = 0;
        Collections.copy(currentpath, getPath(pred,0,8));
        for (i = 0; i < currentpath.size(); i++) {
             max = max + currentpath.get(i);
        }
        Collections.copy(path, currentpath);
        System.out.println("Maximum truffles is: " + max);
        System.out.println(Arrays.toString(path.toArray()));
    }
    
    public static ArrayList<Integer> getPath (int[][] pred, int u, int v) {
        ArrayList<Integer> predpath = new ArrayList<Integer>();
        if (pred[u][v] == 0) {
            return predpath;    
        }
        path.add(u);
        while (u != v) {
            u = pred[u][v];
            predpath.add(u);
        }
        return predpath;
    }
    public static void main(String args[]) { 
        //get row and column sizes from file
        int colcount = 3;
        int rowcount = 3;
        colnumber = colcount;
        int nodes = colcount * rowcount;
        int[] nodelist = new int[nodes];
        int count = 0;
        try {
            //fill in matrix using file
            Scanner input = new Scanner(new File("test.txt"));
            for(int i = 0; i < rowcount; ++i) {
                for(int j = 0; j < colcount; ++j) {
                    if(input.hasNextInt()) {
                        nodelist[count] = input.nextInt();
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[][] weights = new int [nodes][nodes];
        for(int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                weights[i][j] = INF;
            }
        }
        for(int i=0; i<nodes; i++) {
            for(int j =0; j<nodes;j++) {
                if(i == j) {
                    weights[i][i] = 0;
                }
                else if(j < i) {
                    weights[i][j] = INF;    
                }
                else if (j < i + rowcount) {
                    weights[i][j] = INF;
                }
                else if (nodes - i > rowcount) {
                    if (i >=nodes - (rowcount * 2) && i < nodes - rowcount && j >= nodes - rowcount) {
                        weights[i][j] = nodelist[j];
                    }
                    else if(i % colcount == 0 && j == i+colcount) {
                        weights[i][j] = nodelist[i] + nodelist[j];
                    }
                    else if (i % colcount == 0 && j == i+colcount+1) {
                        weights[i][j] = nodelist[i] + nodelist[j];  
                    }
                    else if (i % colcount == colcount - 1 && j == i + colcount) {
                        weights[i][j] = nodelist[i] + nodelist[j];
                    }
                    else if (i % colcount == colcount - 1 && j == i + colcount - 1) {
                        weights[i][j] = nodelist[i] + nodelist[j];
                    }
                    else if (i % colcount != 0 && i % colcount != colcount - 1) {
                        weights[i][i + colcount] = nodelist[i] + nodelist[i + colcount];
                        weights[i][i + colcount + 1] = nodelist[i] + nodelist[i + colcount + 1];
                        weights[i][i + colcount - 1 ] = nodelist[i] + nodelist[i + colcount - 1];        
                    }
                }
            }
        }
        System.out.println("Weight matrix:");
        for(int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                if(weights[i][j] == INF) {
                    System.out.print("INF");
                } else {
                    System.out.print(weights[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
        //call Floyd Warshall with weight matrix
        FloydWarshall(weights, nodes);
    }
}
