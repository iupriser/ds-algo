package graph.shortestPathAlgo;

public class CityWithSmallestNumberOfNeighbour {
    int findCity(int n, int m, int[][] edges, int distanceThreshold) {
        // create a distance matrix
        int[][] dist = new int[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == c) dist[r][c] = 0;
                else dist[r][c] = (int) 1e8;
            }
        }
        for (int r = 0; r < m; r++) {
            int u = edges[r][0];
            int v = edges[r][1];
            int wt = edges[r][2];
            dist[u][v] = wt;
            dist[v][u] = wt;
        }


        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != (int) 1e8 && dist[k][j] != (int) 1e8) {
                        dist[i][j] = Math.min(dist[i][j], (dist[i][k] + dist[k][j]));
                    }
                }
            }
        }

        int city = -1, maxCities = Integer.MAX_VALUE, currCityCount = 0;
        for (int i = 0; i < n; i++) {
            currCityCount = 0;
            for (int j = 0; j < n; j++) {
                if (dist[i][j] <= distanceThreshold) {
                    currCityCount++;
                }
            }
            if (maxCities >= currCityCount) {
                maxCities = currCityCount;
                city = i;
            }

        }

        return city;
    }

    public static void main(String[] args) {
        int n = 4, m = 4, distanceThreshold = 4;
        int[][] edges = {{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}};
        CityWithSmallestNumberOfNeighbour obj = new CityWithSmallestNumberOfNeighbour();
        int city = obj.findCity(n, m, edges, distanceThreshold);
        System.out.println("City with smallest number of neighbour with threshold: " + city);

    }
}

