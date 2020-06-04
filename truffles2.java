import java.util.ArrayList;
import java.util.*;
import java.io.*;
import java.lang.Math;
import java.util.Arrays;
import java.lang.*;
import java.util.Collections;

public class truffles2 {
    public static int colnumber;
    public static int rownumber;
    public static int INF = 99999;
    public static ArrayList<Integer> path = new ArrayList<Integer>();
    public static ArrayList<Integer> currentpath = new ArrayList<Integer>();
    public static ArrayList<Integer> globalnodes = new ArrayList<Integer>();
public static void floydWarshall(int graph[][], int V) { 
        int i = 0;
        int j = 0;
        int k = 0;
        int[][] p = new int[V][V];
        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if(i==j || graph[i][j] == INF) {
                    p[i][j] = -1;
                }
                if(i != j && graph[i][j] < INF) {
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
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                        p[i][j] = p[k][j];
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
                if(p[i][j] == -1) {
                    System.out.print("NULL");
                } else {
                    System.out.print(p[i][j]); 
                }
                System.out.print(" "); 
            }
            System.out.println("");
        }
        int max = 0;
        int currentmax = 0;
        for(int ival = 0; ival < colnumber; ival++) {
            for(int jval = V - colnumber; jval< V; jval++) {
                currentmax = 0;
                getPath(p,ival,jval);
                for (int icount = 0; icount < currentpath.size(); icount++) {
                    currentmax = currentmax + currentpath.get(icount);
                }
                System.out.println("Current max: " + currentmax);
                if(currentmax > max) {
                    max = currentmax;
                    path = (ArrayList<Integer>)currentpath.clone();
                }
                currentpath.clear();
            }
        }
        System.out.println("Maximum truffles is: " + max);
        System.out.println(Arrays.toString(path.toArray()));
    }
    
  public static void getPath(int[][] predecessor, int i, int j) {
    //base case
    if(i == j) {
      currentpath.add(globalnodes.get(i));
    } else {
      //recursive call
      getPath(predecessor, i, predecessor[i][j]);
      //add index to global index array
      currentpath.add(globalnodes.get(j));
    }
  }
    public static void main(String args[]) throws FileNotFoundException { 
        //get row and column sizes from file
        int count = 0;
        // pre-read in the number of rows/columns
        String filename = "test.txt";
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
        System.out.println("Columns:" + colcount);
        System.out.println("Rows: " + rowcount);
        int nodes = colcount * rowcount;
        int[] nodelist = new int[nodes];
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
                System.out.println(j);
                if(i == j) {
                    System.out.println("line 155");
                    weights[i][i] = 0;
                }
                else if(j < i) {
                    System.out.println("line 159");
                    weights[i][j] = INF;    
                }
                else if (nodes - i > colcount) {
                    if(i % colcount == 0 && j == i+colcount) {
                        System.out.println("line 164");
                        if (i >=nodes - (colcount * 2) && i < nodes - colcount && j >= nodes - colcount) {
                            System.out.println("line 166");
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 169");
                            weights[i][j] = nodelist[i] + nodelist[j];
                        }
                    }
                    else if (i % colcount == 0 && j == i+colcount+1) {
                        System.out.println("line 174");
                        if (i >=nodes - (colcount * 2) && i < nodes - colcount && j >= nodes - colcount) {
                            System.out.println("line 176");
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 179");
                            weights[i][j] = nodelist[i] + nodelist[j]; 
                        }
                    }
                    else if (i % colcount == colcount - 1 && j == i + colcount) {
                        System.out.println("line 189");
                        if (i >=nodes - (colcount * 2) && i < nodes - colcount && j >= nodes - colcount) {
                            System.out.println("line 189");
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 189");
                            weights[i][j] = nodelist[i] + nodelist[j];
                        }
                    }
                    else if (i % colcount == colcount - 1 && j == i + colcount - 1) {
                        System.out.println("line 194");
                        if (i >=nodes - (colcount * 2) && i < nodes - colcount && j >= nodes - colcount) {
                            System.out.println("line 196");
                            weights[i][j] = nodelist[j];
                        } else {
                            System.out.println("line 199");
                            weights[i][j] = nodelist[i] + nodelist[j];
                        }
                    }
                    else if (i % colcount != 0 && i % colcount != colcount - 1) {
                        if (i >=nodes - (colcount * 2) && i < nodes - colcount && j >= nodes - colcount) {
                            System.out.println("line 206");
                            weights[i][j] = nodelist[j];
                        } else {
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
        floydWarshall(weights, nodes);
    }
}
