/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tpaao.Main;

import javafx.application.Application;
import tpaao.DataStructure.City;
import tpaao.DataStructure.Network;
import tpaao.DataStructure.TSPSolver;
import tpaao.GUI.NetworkGUI;

public class App {
    private static Network network;

    public static Network getNetwork() {
        return network;
    }

    public static void main(String[] args) {
        try {
            City porto = new City("Porto", 41.1496, -8.6110);
            City lisboa = new City("Lisboa", 38.736946, -9.142685);
            City braga = new City("Braga", 41.545448, -8.426507);
            City aveiro = new City("Aveiro", 40.64427, -8.64554);
            City coimbra = new City("Coimbra", 40.203314, -8.410257);
            City leiria = new City("Leiria", 39.749533, -8.807683);

            Network network = new Network();
            network.addVertex(porto);
            network.addVertex(lisboa);
            network.addVertex(braga);
            network.addVertex(aveiro);
            network.addVertex(coimbra);
            network.addVertex(leiria);

            network.addEdgesBetweenAllCities();

            network.printDistanceMatrix();

            App.network = network;

            TSPSolver tspSolver = new TSPSolver(network);

            int ans = Integer.MAX_VALUE;
            for (int i = 1; i <= network.getNumVertexes(); i++) {
                int tourCost = tspSolver.solveTSPProblemUsingDynamicProgramming(i);
                int distanceToStartCity = network.getDistanceMatrix()[i][0]; // Use index 0 for the start city
                ans += Math.min(ans, tourCost + distanceToStartCity);
            }

            System.out.println("The cost of the most efficient tour = " + ans);

            //Application.launch(NetworkGUI.class, args);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
