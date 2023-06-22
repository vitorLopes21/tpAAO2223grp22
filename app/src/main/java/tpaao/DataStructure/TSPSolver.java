package tpaao.DataStructure;

import tpaao.Main.App;
import java.util.ArrayList;
import java.util.List;

public class TSPSolver {
    Network network;
    int[][] memo;

    public TSPSolver(Network network) {
        this.network = network;
        this.memo = new int[this.network.getNumVertexes() + 1][1 << (this.network.getNumVertexes() + 1)];
    }

    // Method to solve the TSP problem using dynamic programming
    public int solveTSPProblemUsingDynamicProgramming(int i, int mask) {
        // Base case: If only the ith bit and 1st bit are set in our mask,
        // it implies we have visited all other nodes already
        App.incrementComplexity();
        if (mask == ((1 << i) | 3)) {
            return this.network.getDistanceMatrix()[1][i];
        }

        // Memoization
        App.incrementComplexity();
        if (this.memo[i][mask] != 0) {
            return this.memo[i][mask];
        }

        int res = App.MAX; // Result

        // We have to travel to all nodes j in the mask and end the
        // path at the ith node. For every node j in the mask,
        // recursively calculate the cost of traveling all
        // nodes in the mask except i and then travel back from node j to node i,
        // taking the shortest path. Take the minimum of all possible j nodes.

        // Iterate through all possible next nodes
        for (int j = 1; j <= network.getNumVertexes(); j++) {
            App.incrementComplexity();
            if ((mask & (1 << j)) != 0 && j != i && j != 1) {
                // If the j-th bit is set in the mask and it is not the current node (i) or the
                // start node (1)

                // Update the result by considering the minimum distance
                res = Math.min(res, solveTSPProblemUsingDynamicProgramming(j, mask & (~(1 << i)))
                        + network.getDistanceMatrix()[j][i]);
            }
        }

        return this.memo[i][mask] = res;
    }

    // Method to calculate the optimal path
    public List<City> calculateOptimalPath() {
        List<City> optimalPath = new ArrayList<>();
        int mask = (1 << (network.getNumVertexes() + 1)) - 1;

        int startCityIndex = 1; // Assuming the start city is at index 1

        optimalPath.add(network.getVertexes()[startCityIndex]);

        while (mask != ((1 << startCityIndex) | 3)) {
            int nextCityIndex = -1;
            int minDistance = App.MAX;

            for (int i = 1; i <= network.getNumVertexes(); i++) {
                if ((mask & (1 << i)) != 0 && i != startCityIndex) {
                    int distance = memo[i][mask & (~(1 << startCityIndex))]
                            + network.getDistanceMatrix()[i][startCityIndex];
                    if (distance < minDistance) {
                        minDistance = distance;
                        nextCityIndex = i;
                    }
                }
            }

            optimalPath.add(network.getVertexes()[nextCityIndex]);
            mask &= ~(1 << startCityIndex);
            startCityIndex = nextCityIndex;
        }

        optimalPath.add(network.getVertexes()[startCityIndex]);

        return optimalPath;
    }
}
