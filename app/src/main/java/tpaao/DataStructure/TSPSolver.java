package tpaao.DataStructure;

public class TSPSolver {
    Network network;

    int[][] memo;

    public TSPSolver(Network network) {
        this.network = network;
        this.memo = new int[this.network.getNumVertexes() + 1][1 << (this.network.getNumVertexes() + 1)];
    }

    // Method to solve the TSP problem
    public int solveTSPProblemUsingDynamicProgramming(int i) {
        
        int mask = (1 << (this.network.getNumVertexes() + 1)) - 1;

        // base case
        // if only ith bit and 1st bit is set in our mask,
        // it implies we have visited all other nodes
        // already
        if (mask == ((1 << (this.network.getNumVertexes() + 1)) - 1)) {
            return this.network.getDistanceMatrix()[1][i];
        }

        // memoization
        if (this.memo[i][mask] != 0) {
            return this.memo[i][mask];
        }

        int res = Integer.MAX_VALUE; // result

        // we have to travel all nodes j in mask and end the
        // path at ith node so for every node j in mask,
        // recursively calculate cost of travelling all
        // nodes in mask
        // except i and then travel back from node j to node i,
        // taking the shortest path take the minimum of
        // all possible j nodes

        // Iterate through all possible next nodes
        for (int j = 1; j <= network.getNumVertexes(); j++) {
            if ((mask & (1 << j)) == 0) {
                // If the j-th bit is not set in the mask, it means the node j has not been
                // visited yet
                // Calculate the distance from the current node (last visited node) to node j
                int distance = network.getDistanceMatrix()[j][i]; // Assuming the start node is at index 0

                // Recursively calculate the TSP solution for the remaining unvisited nodes
                int subProblem = solveTSPProblemUsingDynamicProgramming(mask | (1 << j));

                // Update the result by considering the minimum distance
                res = Math.min(res, distance + subProblem);
            }
        }

        return this.memo[i][mask] = res;
    }

}