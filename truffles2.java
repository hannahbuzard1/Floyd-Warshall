import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

public class truffles2 {
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
            if(i % colcount == 0) {
                weights[i][colcount] = nodelist[i] + nodelist[colcount];
                weights[i][colcount+1] = nodelist[i] + nodelist[colcount + 1];
            }
            else if (i % colcount - 1) {
                weights[i][colcount + 1] = nodelist[i] + nodelist[colcount + 1];
                weights[i][colcount - 1] = nodelist[i] + nodelist[colcount - 1];
            }
            else {
                weights[i][colcount] = nodelist[i] + nodelist[colcount];
                weights[i][colcount + 1] = nodelist[i] + nodelist[colcount + 1];
                weights[i][colcount - 1] = nodelist[i] + nodelist[colcount - 1];        
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
    }
}
