/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tpaao.Main;

import java.util.ArrayList;
import java.util.Scanner;

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
            int choice;
            ArrayList<City> cityArray = new ArrayList<City>();
            cityArray.add(new City("Porto", 41.1496, -8.6110));
            cityArray.add(new City("Lisboa", 38.736946, -9.142685));
            cityArray.add(new City("Braga", 41.545448, -8.426507));
            cityArray.add(new City("Aveiro", 40.64427, -8.64554));
            cityArray.add(new City("Coimbra", 40.203314, -8.410257));
            cityArray.add(new City("Leiria", 39.749533, -8.807683));
            cityArray.add(new City("Lixa", 41.3166654, -8.166666));
            cityArray.add(new City("Vila Nova de Gaia", 41.1339, -8.6176));
            cityArray.add(new City("Amadora", 38.7538, -9.2308));
            cityArray.add(new City("Setúbal", 38.5244, -8.8882));
            cityArray.add(new City("Funchal", 32.6669, -16.9241));
            cityArray.add(new City("Cacém", 38.7704, -9.3134));
            cityArray.add(new City("Vila Nova de Famalicão", 41.4135, -8.5196));
            cityArray.add(new City("Algueirão-Mem Martins", 38.803, -9.2542));
            cityArray.add(new City("Bragança", 41.8058, -6.7579));
            cityArray.add(new City("Viseu", 40.661, -7.9097));
            cityArray.add(new City("Felgueiras", 41.3684, -8.1951));
            cityArray.add(new City("Barreiro", 38.663, -9.072));
            cityArray.add(new City("Guarda", 40.5371, -7.2656));
            cityArray.add(new City("Espinho", 41.007, -8.6415));
            cityArray.add(new City("Póvoa de Varzim", 41.3833, -8.7667));
            cityArray.add(new City("Matosinhos", 41.1828, -8.6897));
            cityArray.add(new City("Maia", 41.2352, -8.6195));
            cityArray.add(new City("Gondomar", 41.1406, -8.5326));

            Network network = new Network();

            System.out.println(
                    "Which method do you want to choose? [1]-> 6 city method; [2]-> 12 city method; [3]-> 24 city method; [0] -> Go back");

            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("6 city Dynamic Programming method");
                    for (int i = 0; i < 6; i++) {
                        network.addVertex(cityArray.get(i));
                    }
                    break;
                case 2:
                    System.out.println("12 city Dynamic Programming method");
                    for (int i = 0; i < 12; i++) {
                        network.addVertex(cityArray.get(i));
                    }
                    break;
                case 3:
                    System.out.println("24 city Dynamic Programming method");
                    for (int i = 0; i < 24; i++) {
                        network.addVertex(cityArray.get(i));
                    }
                    break;
                case 0:
                    break;
            }

            if (choice != 0) {
                scanner.close();

                network.addEdgesBetweenAllCities();

                // network.printDistanceMatrix();

                App.network = network;

                TSPSolver tspSolver = new TSPSolver(network);

                int ans = Integer.MAX_VALUE;
                for (int i = 1; i <= network.getNumVertexes(); i++) {
                    int tourCost = tspSolver.solveTSPProblemUsingDynamicProgramming(i);
                    int distanceToStartCity = network.getDistanceMatrix()[i][0]; // Use index 0 for the start city
                    ans += Math.min(ans, tourCost + distanceToStartCity);
                }

                System.out.println("The cost of the most efficient tour = " + ans);

                Application.launch(NetworkGUI.class, args);
            }

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
