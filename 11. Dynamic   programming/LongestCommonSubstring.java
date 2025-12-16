// Java code for calculating the longest common substring and printing the grid
public class LongestCommonSubstring {
    public static void main(String[] args) {
        String a = "FISH";
        String b = "FOSH";
        int [][] grid = getLCS_Grid(a, b);
        printLCS_Grid(grid);

        String c = "HISH";
        String d = "VISTA";
        int [][] grid2 = getLCS_Grid(c, d);
        printLCS_Grid(grid2);

        // exercise 11.3
        String e = "blue";
        String f = "clues";
        int [][] grid3 = getLCS_Grid(e, f);
        printLCS_Grid(grid3);
    }
    public static int[][] getLCS_Grid (String a, String b) {
        // extra row/col for empty prefixes
        int[][] grid = new int[a.length() + 1][b.length() + 1];
        // Fill the table
        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    // Characters match: extend LCS from diagonal
                    grid[i][j] = grid[i - 1][j - 1] + 1;
                } else {
                    // No match: take best from top or left
                    grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid;
    }
    public static void printLCS_Grid (int[][] grid) {
        // Result is in bottom-right
        System.out.println("Length of LCS: " + grid[grid.length-1][grid[0].length-1]);
        // print the whole table
        for (int[] row : grid){
            for (int i : row)
                System.out.print(i + " ");
            System.out.println();
        }

    }
}
