package tpaao.DataStructure;

import tpaao.Main.App;

public class TSPSolver {
    Network network;

    int[][] memo;

    public TSPSolver(Network network) {
        this.network = network;
        this.memo = new int[this.network.getNumVertexes() + 1][1 << (this.network.getNumVertexes() + 1)];
    }

    // Method to solve the TSP problem
    public int solveTSPProblemUsingDynamicProgramming(int i, int mask) {
        App.incrementComplexity(); // Increment the complexity counter

        // base case
        // if only ith bit and 1st bit is set in our mask,
        // it implies we have visited all other nodes
        // already
        if (mask == ((1 << i) | 3)) {
            App.incrementComplexity(); // Increment the complexity counter
            return this.network.getDistanceMatrix()[1][i];
        }

        // memoization
        if (this.memo[i][mask] != 0) {
            App.incrementComplexity(); // Increment the complexity counter
            return this.memo[i][mask];
        }

        int res = App.MAX; // Initialize the result to the maximum value

        // we have to travel all nodes j in mask and end the
        // path at ith node so for every node j in mask,
        // recursively calculate cost of travelling all
        // nodes in mask
        // except i and then travel back from node j to node i,
        // taking the shortest path take the minimum of
        // all possible j nodes

        // Iterate through all possible next nodes
        for (int j = 1; j <= network.getNumVertexes(); j++) {
            App.incrementComplexity(); // Increment the complexity counter
            if ((mask & (1 << j)) != 0 && j != i && j != 1) {
                App.incrementComplexity(); // Increment the complexity counter
                // If the j-th bit is not set in the mask, it means the node j has not been
                // visited yet

                // Update the result by considering the minimum distance
                res = Math.min(res, solveTSPProblemUsingDynamicProgramming(j, mask & (~(1 << i))) + network.getDistanceMatrix()[j][i]);
            }
        }

        return this.memo[i][mask] = res;
    }
}
