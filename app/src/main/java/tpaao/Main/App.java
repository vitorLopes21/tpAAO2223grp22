/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package tpaao.Main;

import javafx.application.Application;
import tpaao.DataStructure.City;
import tpaao.DataStructure.Network;
import tpaao.GUI.NetworkGUI;

public class App {
    private static Network network;

    public static Network getNetwork() {
        return network;
    }

    public static void main(String[] args) {
        try {
            City porto = new City("Porto");
            City lisboa = new City("Lisboa");
            City braga = new City("Braga");
            City aveiro = new City("Aveiro");
            City coimbra = new City("Coimbra");
            City leiria = new City("Leiria");
            City viseu = new City("Viseu");
            City guarda = new City("Guarda");

            Network network = new Network();
            network.addVertex(porto);
            network.addVertex(lisboa);
            network.addVertex(braga);
            network.addVertex(aveiro);
            network.addVertex(coimbra);
            network.addVertex(leiria);
            network.addVertex(viseu);
            network.addVertex(guarda);

            network.addEdgesBetweenAllCities();

            App.network = network;

            Application.launch(NetworkGUI.class, args);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
