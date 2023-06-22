package tpaao.DataStructure;

public class Network {
    // Maximum number of vertexes
    private final int MAX_VERTEXES = 25;
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
    protected int getIndex(City index) {
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

    // Method to add edges between all cities in the network
    public void addEdgesBetweenAllCities() {
        // Iterate through all cities in the network
        for (int i = 0; i < this.numVertexes; i++) {
            for (int j = i + 1; j < this.numVertexes; j++) {
                // Calculate the distance between the cities
                double x1 = vertexes[i].getX();
                double y1 = vertexes[i].getY();
                double x2 = vertexes[j].getX();
                double y2 = vertexes[j].getY();
                int distance = (int) Math.round(8 * Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)));

                // Set the distance in the distance matrix
                distanceMatrix[i][j] = distance;
                distanceMatrix[j][i] = distance;
            }
        }
    }

    public int getNumVertexes() {
        return this.numVertexes;
    }

    public City[] getVertexes() {
        return this.vertexes;
    }

    public int[][] getDistanceMatrix() {
        return this.distanceMatrix;
    }

    public void printDistanceMatrix() {
        for (int i = 0; i < numVertexes; i++) {
            for (int j = 0; j < numVertexes; j++) {
                System.out.print(distanceMatrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
