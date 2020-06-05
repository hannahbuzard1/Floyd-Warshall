import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

public class truffles2 {
    //global variables
    public static int colnumber;
    public static int rownumber;
    public static int INF = 99999;
    public static ArrayList<Integer> path = new ArrayList<Integer>(); //overall longest path
    public static ArrayList<Integer> pathnodes = new ArrayList<Integer>();
    public static ArrayList<Integer> currentpath = new ArrayList<Integer>();
    public static ArrayList<Integer> globalnodes = new ArrayList<Integer>(); //list of input nodes
    public static ArrayList<String> globalloc = new ArrayList<String>(); //lists of input locations
    public static ArrayList<String> currentloc = new ArrayList<String>();
    
    public static void floydWarshall(int graph[][], int V) { 
        int i = 0;
        int j = 0;
        int k = 0;
        int[][] p = new int[V][V];
        //initialize values in predecessor matrix
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if(i==j || graph[i][j] == INF) { //no path exists,then -1
                    p[i][j] = -1;
                }
                if(i != j && graph[i][j] < INF) { //if path exists, predecessor is i
                    p[i][j] = i;
                }
            }
        }

        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (graph[i][k] == INF || graph[k][j] == INF) {
                        continue;                 
                    }
                    if (graph[i][j] > graph[i][k] + graph[k][j]) { //if there is a shorter path than directly i to j....
                        graph[i][j] = graph[i][k] + graph[k][j]; //update [i][j] in graph and in predecessor matrix
                        p[i][j] = p[k][j];
                    }
                }
            }
         }
         //call function to get longest path
         pathRetrieval(graph, p, V);
    }
    public static void pathRetrieval(int[][] graph, int[][] p, int V) {
        //get overall longest path 
        int max = 0;
        int currentmax = 0;
        for(int ival = 0; ival < colnumber; ival++) {
            for(int jval = V - colnumber; jval< V; jval++) {
                currentmax = 0;
                getPath(p,ival,jval);
                for (int icount = 0; icount < currentpath.size(); icount++) {
                    currentmax = currentmax + currentpath.get(icount);
                }
                if(currentmax > max) {
                    max = currentmax;
                    path = (ArrayList<Integer>)currentloc.clone();
                    pathnodes = (ArrayList<Integer>)currentpath.clone();
                }
                currentpath.clear();
                currentloc.clear();
            }
        }
        //print results
        System.out.println("Maximum truffles is: " + max);
        System.out.print("Locations: ");
        System.out.println(Arrays.toString(path.toArray()));
        System.out.print("Nodes visited (in order): ");
        System.out.println(Arrays.toString(pathnodes.toArray()));
    }
    
  public static void getPath(int[][] predecessor, int i, int j) {
    //base case
    if(i == j) {
      currentpath.add(globalnodes.get(i));
      currentloc.add(globalloc.get(i));
    } else {
      //recursive call
      getPath(predecessor, i, predecessor[i][j]);
      //add index to global index array
      currentpath.add(globalnodes.get(j));
      currentloc.add(globalloc.get(j));
    }
  }
    public static void main(String args[]) throws FileNotFoundException { 
        //get row and column sizes from file
        int count = 0;
        // pre-read in the number of rows/columns
        String filename = args[0];
        int rowcount = 0;
        int colcount = 0;
        List<String> lines = new ArrayList<String>();
        String line = null;
        int counter = 0;
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                if(counter == 0) {
                    lines.add(line);
                }
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        rowcount = counter;
        String[] numbers = lines.get(0).split(" ");
        int stringsize = numbers.length;
        colcount = stringsize;
        colnumber = colcount;
        rownumber = rowcount;
        int nodes = colcount * rowcount;
        int[] nodelist = new int[nodes];
        String[] nodeloc = new String[nodes];
        //fill in list of nodes using input file
        try {
            Scanner input = new Scanner(new File(filename));
            for(int i = 0; i < rowcount; ++i) {
                for(int j = 0; j < colcount; ++j) {
                    if(input.hasNextInt()) {
                        int val = i + 1; //need this for one-indexed representation
                        nodelist[count] = input.nextInt();
                        nodeloc[count] = "[" + val + ", " + j + "]";
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //initialize weight matrix
        int[][] weights = new int [nodes][nodes];
        for(int i=0; i<nodes; i++) {
            for (int j=0; j<nodes; j++) {
                weights[i][j] = INF;
            }
        }
        //fill in weight matrix (to represent weighted, directed graph)
        for(int i=0; i<nodes; i++) {
            for(int j =0; j<nodes;j++) {
                if(i == j) { //path to itself is 0
                    weights[i][i] = 0;
                }
                else if(j < i) { //no path, then INF
                    weights[i][j] = INF;    
                }
                else if (i >=nodes - (colcount * 2) && i < nodes - colcount && j >= nodes - colcount) { //last row in input
                            weights[i][j] = nodelist[j];
                        }
                else if (nodes - i > colcount)  {
                    if(i % colcount == 0 && j == i+colcount) { //node is on left side of input (only 2 edges to other nodes)
                            weights[i][j] = nodelist[i] + nodelist[j];
                    }
                    else if (i % colcount == 0 && j == i+colcount+1) {  //node is on left side of input (only 2 edges to other nodes)
                            weights[i][j] = nodelist[i] + nodelist[j]; 
                    }
                    else if (i % colcount == colcount - 1 && j == i + colcount) { //node is on right side of input (only 2 edges to other nodes)
                            weights[i][j] = nodelist[i] + nodelist[j];
                        }
                    else if (i % colcount == colcount - 1 && j == i + colcount - 1) { //node is on right side of input (only 2 edges to other nodes)
                            weights[i][j] = nodelist[i] + nodelist[j];
                    }
                    else if (i % colcount != 0 && i % colcount != colcount - 1) { //node has 3 edges to other nodes (down and both diagonals)
                            weights[i][i + colcount] = nodelist[i] + nodelist[i + colcount];
                            weights[i][i + colcount + 1] = nodelist[i] + nodelist[i + colcount + 1];
                            weights[i][i + colcount - 1 ] = nodelist[i] + nodelist[i + colcount - 1];   
                        }
                    }
                }
            }
        for (int i=0; i< nodelist.length; i++) { //add nodes to global list of nodes (so able to access in other methods)
            globalnodes.add(nodelist[i]);
        }
        for(int i = 0; i < nodeloc.length; i++) {
            globalloc.add(nodeloc[i]);
        }
        for(int i=0; i<nodes; i++) { //convert to negative weights so longest path can be computed
            for (int j=0; j<nodes; j++) {
                if(weights[i][j] != INF) {
                    weights[i][j] = -(weights[i][j]);
                }
            }
        }
        //call Floyd Warshall with weight matrix
        floydWarshall(weights, nodes);
    }
}
