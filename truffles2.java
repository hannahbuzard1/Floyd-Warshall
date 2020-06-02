public class truffles {
    public static void main(String args[]) { 
    //get row and column sizes from file
    int colcount = 4;
    int rowcount = 4;
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
