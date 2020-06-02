import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

public class truffles2 {
    public static void FloydWarshall(int[][] matrix, int nodes) {
       int dist[][] = new int[nodes][nodes]; 
        int i, j, k; 

        for (i = 0; i < nodes; i++) {
            for (j = 0; j < nodes; j++) {
                dist[i][j] = matrix[i][j]; 
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
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        }
        System.out.println("New matrix:");
        for (i = 0; i < nodes; i++) {
            for (j = 0; j < nodes; j++) {
                System.out.print(dist[i][j]); 
            }
            System.out.println("");
        }
    }
    public static void main(String args[]) { 
        //get row and column sizes from file
        int colcount = 3;
        int rowcount = 3;
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
        //make call to function to find max path 
        int[][] weights = new int [nodes][nodes];
        for(int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                weights[i][j] = 1000;
            }
        }
        for(int i=0; i<nodes; i++) {
            weights[i][i] = nodelist[i];
            if(nodes - i <= rowcount) {
                weights[i][i] = nodelist[i];
            }
            else if(i % colcount == 0) {
                weights[i][i + colcount] = nodelist[i] + nodelist[i + colcount];
                weights[i][i + colcount+1] = nodelist[i] + nodelist[i + colcount + 1];
            }
            else if (i % colcount == colcount - 1) {
                weights[i][i + colcount] = nodelist[i] + nodelist[i + colcount];
                weights[i][i + colcount - 1] = nodelist[i] + nodelist[i + colcount - 1];
            }
            else {
                weights[i][i + colcount] = nodelist[i] + nodelist[i + colcount];
                weights[i][i + colcount + 1] = nodelist[i] + nodelist[i + colcount + 1];
                weights[i][i + colcount - 1 ] = nodelist[i] + nodelist[i + colcount - 1];        
            }
        }
        System.out.println("Weight matrix:");
        for(int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                System.out.print(weights[i][j]);
                System.out.print(" ");
            }
            System.out.println("");
        }
        System.out.println("");
        for(int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                weights[i][j] = -(weights[i][j]);
            }
        }
        //call Floyd Warshall with weight matrix
        FloydWarshall(weights, nodes);
    }
}
