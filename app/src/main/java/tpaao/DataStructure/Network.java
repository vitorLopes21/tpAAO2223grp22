package tpaao.DataStructure;

public class Network {
    // Maximum number of vertexes
    private final int MAX_VERTEXES = 20;
    // Number of vertexes in the network
    private int numVertexes;
    // Array of vertexes
    private City[] vertexes;
    // Weight of the edges between vertexes
    private int[][] distanceMatrix;

    // Constructor
    public Network() {
        this.numVertexes = 0;
        this.vertexes = new City[MAX_VERTEXES];
        this.distanceMatrix = new int[MAX_VERTEXES][MAX_VERTEXES];
    }

    // Support method to get the index of a city in the network
    private int getIndex(City index) {
        // Iterate through all cities in the network
        for (int i = 0; i < this.numVertexes; i++) {
            if (this.vertexes[i].compareTo(index) == 0) {
                // If the city has the same name, x and y coordinates as the city in the
                // network, return its index
                return i;
            }
        }

        // If the city is not in the network, return -1
        return -1;
    }

    // Method to add a city to the network
    public void addVertex(City city) {
        this.vertexes[this.numVertexes++] = city;
    }

    // Method to add an edge between two cities
    public void addEdge(City city1, City city2) {
        // Calculate the distance between the two cities
        // based on their x and y coordinates
        int distance = (int) Math
                .sqrt(Math.pow(city1.getX() - city2.getX(), 2) + Math.pow(city1.getY() - city2.getY(), 2));

        // If the cities are not in the network, return
        if (getIndex(city1) == -1 || getIndex(city2) == -1) {
            throw new IllegalArgumentException("City not found in the network");
        }

        this.distanceMatrix[getIndex(city1)][getIndex(city2)] = distance;
        this.distanceMatrix[getIndex(city2)][getIndex(city1)] = distance;
    }

    // Method to add edges between all cities in the network
    public void addEdgesBetweenAllCities() {
        // Iterate through all cities in the network
        for (int i = 0; i < this.numVertexes; i++) {
            for (int j = 0; j < this.numVertexes; j++) {
                if (i != j) {
                    // If the cities are not the same, add an edge between them
                    addEdge(this.vertexes[i], this.vertexes[j]);
                }
            }
        }
    }

    // Method to solve the TSP problem
    public void solveTSPProblem() {
        // To accomplish this, we want to find the shortest path between
        // traveling through all cities in the network

    }
}
