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
     public static ArrayList<Integer> globalnodes = new ArrayList<Integer>();
    public static int maxpath;
public static void floydWarshall(int graph[][], int V) { 
        int pred[][] = new int[V][V];
        int i = 0;
        int j = 0;
        int k = 0;
        int[][] p = new int[graph.length][graph.length];
        for (i = 0; i < graph.length; i++) {
            for (j = 0; j < graph.length; j++) {
                p[i][j] = -1;
            }
        }
        for (k = 0; k < graph.length; k++) {
            for (i = 0; i < graph.length; i++) {
                for (j = 0; j < graph.length; j++) {
                    if (graph[i][k] == INF || graph[k][j] == INF) {
                        continue;                 
                    }
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        p[i][j] = p[i][k];
                    }
                }
            }
         } 
        System.out.println("Dist matrix:");
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if(graph[i][j] == INF) {
                    System.out.print("INF");
                } else {
                    System.out.print(graph[i][j]); 
                }
                System.out.print(" "); 
            }
            System.out.println("");
        }
        System.out.println("Pred matrix:");
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if(pred[i][j] == -1) {
                    System.out.print("NULL");
                } else {
                    System.out.print(p[i][j]); 
                }
                System.out.print(" "); 
            }
            System.out.println("");
        }
        int max = 0;
        //getPath(p,0,8);
        for (i = 0; i < currentpath.size(); i++) {
             max = max + currentpath.get(i);
        }
        System.out.println("Maximum truffles is: " + max);
        System.out.println(Arrays.toString(currentpath.toArray()));
    }
    
  public static void getPath(int[][] predecessor, int i, int j) {
    //base case
     if(i == j) {
      System.out.println(globalnodes.get(i) + " ");
    } else {
      //recursive call
      getPath(predecessor, i, predecessor[i][j]);
      System.out.println(globalnodes.get(j));
    }
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
        for(int i=3; i<4; i++) {
            for(int j =0; j<nodes;j++) {
                if(i == j) {
                    System.out.println("here");
                    System.out.println(j);
                    weights[i][i] = 0;
                }
                else if(j < i) {
                    System.out.println("Now here");
                    System.out.println(j);
                    weights[i][j] = INF;    
                }
                else if (nodes - i > rowcount) {
                    if(i % colcount == 0 && j == i+colcount) {
                        System.out.println("line 129 ");
                        if (i >=nodes - (rowcount * 2) && i < nodes - rowcount && j >= nodes - rowcount) {
                            weights[i][j] = nodelist[j];
                        } else {
                            weights[i][j] = nodelist[i] + nodelist[j];
                        }
                    }
                    else if (i % colcount == 0 && j == i+colcount+1) {
                        if (i >=nodes - (rowcount * 2) && i < nodes - rowcount && j >= nodes - rowcount) {
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 133 ");
                            weights[i][j] = nodelist[i] + nodelist[j]; 
                        }
                    }
                    else if (i % colcount == colcount - 1 && j == i + colcount) {
                        if (i >=nodes - (rowcount * 2) && i < nodes - rowcount && j >= nodes - rowcount) {
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 137");
                            weights[i][j] = nodelist[i] + nodelist[j];
                        }
                    }
                    else if (i % colcount == colcount - 1 && j == i + colcount - 1) {
                        if (i >=nodes - (rowcount * 2) && i < nodes - rowcount && j >= nodes - rowcount) {
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 141");
                            weights[i][j] = nodelist[i] + nodelist[j];
                        }
                    }
                    else if (i % colcount != 0 && i % colcount != colcount - 1) {
                        if (i >=nodes - (rowcount * 2) && i < nodes - rowcount && j >= nodes - rowcount) {
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 145"); 
                            weights[i][i + colcount] = nodelist[i] + nodelist[i + colcount];
                            weights[i][i + colcount + 1] = nodelist[i] + nodelist[i + colcount + 1];
                            weights[i][i + colcount - 1 ] = nodelist[i] + nodelist[i + colcount - 1];   
                        }
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
        for (int i=0; i< nodelist.length; i++) {
            globalnodes.add(nodelist[i]);
        }
        for(int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                if(weights[i][j] != INF) {
                    weights[i][j] = -(weights[i][j]);
                }
            }
        }
        //call Floyd Warshall with weight matrix
        floydWarshall(weights, 9);
    }
}
