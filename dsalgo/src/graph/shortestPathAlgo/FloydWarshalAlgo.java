package graph.shortestPathAlgo;

public class FloydWarshalAlgo {
    public void floydWarshall(int[][] dist) {
        int n = dist.length;
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(dist[i][k] != (int)1e8 && dist[k][j] != (int) 1e8) {
                        dist[i][j] = Math.min(dist[i][j], (dist[i][k] + dist[k][j]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] dist = {{0, 4, 108, 5, 108}, {108, 0, 1, 108, 6}, {2, 108, 0, 3, 108}, {108, 108, 1, 0, 2}, {1, 108, 108, 4, 0}};
        int n = dist.length;
        FloydWarshalAlgo obj = new FloydWarshalAlgo();
        obj.floydWarshall(dist);
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                System.out.print(dist[r][c] + " ");
            }
            System.out.println();
        }
    }
}
