package graph.mst;

import java.util.ArrayList;
import java.util.List;

// dynamic graph + connections == disjointSet
public class NumberOfIslandsIIOnlineQueries {
    // representing every cell as node in DS

    /**
     * 0  1  2  3  4
     * 5  6  7  8  9
     * 10 11 12 13 14
     * 15 16 17 18 19
     */
    // [2,1] == 11 ?
    // numOfElementsInEachRow * 2 + 1 = 5*2+1 = 11
    public List<Integer> numOfIslands(int n, int m, int[][] operators) {
        // if there are multiple same cell number [0,0], [0,0], we don't mark again
        DisjointSet ds = new DisjointSet(n * m);
        int[][] isVis = new int[n][m];
        int cntIsland = 0;
        List<Integer> ans = new ArrayList<>();
        int len = operators.length;
        // traverse each online query (operators)
        for (int i = 0; i < len; i++) {
            int row = operators[i][0];
            int col = operators[i][1];
            if (isVis[row][col] == 1) {
                ans.add(cntIsland);
                continue;
            }
            isVis[row][col] = 1;
            cntIsland++;

            int[] dr = {-1, 0, 1, 0};
            int[] dc = {0, 1, 0, -1};
            // check if neighbouring nodes have island or not
            int flag = 0;
            for (int ind = 0; ind < 4; ind++) {
                int adjr = row + dr[ind];
                int adjc = col + dc[ind];
                if (isValid(adjr, adjc, n, m)) {
                    // check if adjacent node has island
                    if (isVis[adjr][adjc] == 1) {
                        // will connect iff not already connected
                        int currNodeNum = row * m + col;
                        int adjNodeNum = adjr * m + adjc;
                        if (ds.findUPar(currNodeNum) != ds.findUPar(adjNodeNum)) {
                            cntIsland--;
                            ds.unionBySize(currNodeNum, adjNodeNum);
                        }
                    }
                }
            }
            ans.add(cntIsland);
        }
        return ans;
    }

    public boolean isValid(int adjr, int adjc, int n, int m) {
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < m;
    }

    public static void main(String[] args) {
        int n = 4, m = 5;
        int[][] operators = {{1, 1}, {0, 1}, {3, 3}, {3, 4}};
        NumberOfIslandsIIOnlineQueries obj = new NumberOfIslandsIIOnlineQueries();
        List<Integer> numOfIslands = obj.numOfIslands(n, m, operators);
        numOfIslands.forEach(System.out::println);
    }
}
