package tpaao.DataStructure;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<City> cities;

    public Network() {
        cities = new ArrayList<>();
    }

    public void addCity(City city) {
        cities.add(city);
    }

    public List<City> getCities() {
        return cities;
    }

    public int getNumVertexes() {
        return cities.size();
    }

    public double[][] getDistanceMatrix() {
        int numVertexes = getNumVertexes();
        double[][] distanceMatrix = new double[numVertexes][numVertexes];

        for (int i = 0; i < numVertexes; i++) {
            City cityA = cities.get(i);
            for (int j = 0; j < numVertexes; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    City cityB = cities.get(j);
                    double distance = calculateDistance(cityA, cityB);
                    distanceMatrix[i][j] = distance;
                }
            }
        }

        return distanceMatrix;
    }

    private double calculateDistance(City cityA, City cityB) {
        double xDiff = cityA.getX() - cityB.getX();
        double yDiff = cityA.getY() - cityB.getY();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }
}
