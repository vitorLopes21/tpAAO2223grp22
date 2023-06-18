package tpaao.DataStructure;

public class TSPSolver {
    private Network network;
    private int[] optimalPath;
    private double optimalDistance;

    public TSPSolver(Network network) {
        this.network = network;
    }

    public void solve() {
        int numCities = network.getNumVertexes();
        int[] path = new int[numCities + 1];
        boolean[] visited = new boolean[numCities];
        path[0] = 0; // Start from city 0
        visited[0] = true;
        optimalDistance = Double.MAX_VALUE;
        tsp(1, 0, visited, path);
    }

    private void tsp(int depth, double distance, boolean[] visited, int[] path) {
        if (depth == network.getNumVertexes()) {
            double currentDistance = distance + network.getDistanceMatrix()[path[depth - 1]][path[0]];
            if (currentDistance < optimalDistance) {
                optimalDistance = currentDistance;
                optimalPath = path.clone();
            }
            return;
        }

        for (int i = 1; i < network.getNumVertexes(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                path[depth] = i;
                tsp(depth + 1, distance + network.getDistanceMatrix()[path[depth - 1]][i], visited, path);
                visited[i] = false;
            }
        }
    }

    public int[] getOptimalPath() {
        return optimalPath;
    }

    public double getOptimalDistance() {
        return optimalDistance;
    }
}
