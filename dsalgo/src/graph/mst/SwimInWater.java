package graph.mst;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * problem asks for the least time t required to swim from the top-left to the bottom-right of a grid.
 * At time t, you can move from a cell (r, c) to an adjacent cell (nr, nc) if the elevation of both cells,
 * grid[r][c] and grid[nr][nc], is less than or equal to t.
 * <p>
 * can be rephrased as finding the minimum t such that there is a path from (0, 0) to (n-1, n-1)
 * where all cells on the path have an elevation of at most t.
 * This lends itself well to a DSU approach combined with sorting.
 */
public class SwimInWater {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    /**
     * as value of inside grid can be 0 <= grid[i][j] < n*n
     * range of t will be 0<=t<=(n*n)-1
     * Binary Search and DFS
     * Search between 0 and N * N - 1 using binary search
     * We stop when at a particular time, t, that the bottom is reached
     * We create a DFS function reachBottom(grid, t, N, visit, i, j), where t is the candidate and visit is boolean array to avoid repeated check
     * Time complexity O(log(N) * N^2)
     * Space complexity O(N^2)
     * TC:
     */
    public int swimInWaterBS_DFS(int[][] grid) {
        int N = grid.length;
        int low = 0, high = N * N - 1;
        // binary search to find the suitable t
        while (low < high) {
            int mid = low + (high - low) / 2;
            boolean[][] vis = new boolean[N][N];

            if (reachBottom(0, 0, grid, mid, vis, N)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // DFS
    public boolean reachBottom(int i, int j, int[][] grid, int time, boolean[][] vis, int N) {
        // check for boundary conditions || (if currNode time is already greater than calculated time) || (if node is already visited)
        if (i < 0 || i >= N || j < 0 || j >= N || vis[i][j] || grid[i][j] > time) return false;
        vis[i][j] = true;
        // reached right-most cell (destination)
        if (i == N - 1 && j == N - 1) return true;

        boolean res = false;
        for (int k = 0; k < 4; k++) {
            res = res || reachBottom(i + dx[k], j + dy[k], grid, time, vis, N);
        }
        return res;
    }

    /**
     * using modifies dijkstra
     * Dijkstra's Search
     * Create a node to store val and respective location i
     * Use heap to store the nodes based on its val
     * Every time, we poll the min node out and compared node's val with our current answer, res, where the max of two is our new answer
     * If we have reach the right bottom corner, our search is done
     * Else, we need to add current node's unvisited neighbors to the heap
     * Time complexity O(N^2log(N))
     * Space complexity O(N^2)
     */
    public int swimInWaterDijkstra(int[][] grid) {
        int n = grid.length;
        PriorityQueue<TripletSwim> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.h));
        int[][] vis = new int[n][n];
        // source = [0,0], dest = [n-1][n-1]
        vis[0][0] = 1;
        pq.add(new TripletSwim(grid[0][0], 0, 0));

        while (!pq.isEmpty()) {
            int currElevation = pq.peek().h;
            int currRow = pq.peek().r;
            int currCol = pq.peek().c;
            if (currRow == n - 1 && currCol == n - 1) return currElevation;
            pq.remove();
            // traverse along all 4 directions
            for (int ind = 0; ind < 4; ind++) {
                int adjr = currRow + dx[ind];
                int adjc = currCol + dy[ind];
                // adjNode is valid and not visited
                if (isValid(adjr, adjc, n) && vis[adjr][adjc] == 0) {
                    int adjElevation = grid[adjr][adjc];
                    vis[adjr][adjc] = 1;
                    pq.add(new TripletSwim(Math.max(adjElevation, currElevation), adjr, adjc));
                }
            }
        }
        return -1;
    }

    public boolean isValid(int adjr, int adjc, int n) {
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < n;
    }

    /**
     * Union Find
     * Union-find structure will be particularly useful for finding if two points are connected
     * We start putting each grid and its flattened index in the map
     * Use while loop to check 0 and N * N - 1 is connected
     * If not, we increase the water depth and get the respective flattened index out of map and connect its neighbors if the depth is greater than theirs
     * If yes, we can simply return the depth
     * Time complexity O(N^2)???
     * Space complexity O(N^2)
     */
    public int swimInWaterUnionFind(int[][] grid) {
        int n = grid.length;
        if (n == 1) return grid[0][0];
        // hashmap will contain mapping between (r,c) and nodeNum
        // (0,0) --> 0, (0,3) --> 3
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map.put(grid[i][j], i * n + j);
            }
        }
        DisjointSet ds = new DisjointSet(n * n);
        int startNode = 0;
        int endNode = n * n - 1;
        int waterDepth = 0;
        // check if sourceNode and endNode are connected or not
        while (ds.findUPar(startNode) != ds.findUPar(endNode)) {
            waterDepth++;
            int k = map.get(waterDepth);
            int i = k / n, j = k % n;
            System.out.println();
            for (int ind = 0; ind < 4; ind++) {
                int adjr = i + dx[ind];
                int adjc = j + dy[ind];
                if (isValid(adjr, adjc, n) && grid[i][j] > grid[adjr][adjc]) {
                }
            }
        }
        return waterDepth;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0, 2}, {1, 3}};
        SwimInWater obj = new SwimInWater();
        int result = obj.swimInWaterBS_DFS(grid);
        System.out.println("using Binary search and DFS: " + result);
        grid = new int[][]{{0, 1, 3}, {2, 4, 1}, {1, 2, 1}};
        result = obj.swimInWaterDijkstra(grid);
        System.out.println("using dijkstra: " + result);

        result = obj.swimInWaterUnionFind(grid);
        System.out.println("using Union-find: " + result);
    }
}

class TripletSwim {
    int r;
    int c;
    int h;

    public TripletSwim(int h, int r, int c) {
        this.h = h;
        this.r = r;
        this.c = c;
    }
}