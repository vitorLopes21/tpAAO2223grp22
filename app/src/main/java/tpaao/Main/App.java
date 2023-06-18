package tpaao.Main;

import javafx.application.Application;
import javafx.stage.Stage;
import tpaao.DataStructure.City;
import tpaao.DataStructure.Network;
import tpaao.DataStructure.TSPSolver;
import tpaao.GUI.NetworkGUI;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create a network with cities
        Network network = new Network();
        network.addCity(new City("Porto", 41.1496, -8.6110));
        network.addCity(new City("Lisboa", 38.736946, -9.142685));
        network.addCity(new City("Braga", 41.545448, -8.426507));
        network.addCity(new City("Aveiro", 40.64427, -8.64554));
        network.addCity(new City("Coimbra", 40.203314, -8.410257));
        network.addCity(new City("Leiria", 39.749533, -8.807683));
        network.addCity(new City("Madrid", 40.416775, -3.703790)); // Madrid coordinates
        network.addCity(new City("Paris", 48.8566, 2.3522)); // Paris coordinates

        // Solve the TSP problem
        TSPSolver tspSolver = new TSPSolver(network);
        tspSolver.solve();
        int[] optimalPath = tspSolver.getOptimalPath();
        double optimalDistance = tspSolver.getOptimalDistance();

        // Print the cost of the most efficient tour
        System.out.println("The cost of the most efficient tour: " + optimalDistance);

        // Print the most efficient tour
        System.out.println("The most efficient tour:");
        for (int cityIndex : optimalPath) {
            City city = network.getCities().get(cityIndex);
            System.out.println(city.getName());
        }

        // Launch the GUI
        NetworkGUI networkGUI = new NetworkGUI();
        networkGUI.setNetwork(network);
        networkGUI.setOptimalPath(optimalPath);
        networkGUI.start(primaryStage);
    }

}
