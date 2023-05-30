package tpaao.GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import tpaao.DataStructure.Network;
import tpaao.Main.App;

public class NetworkGUI extends Application {
    private Network network;
    // JavaFX components
    private Canvas canvas;
    private GraphicsContext gc;

    public NetworkGUI() {
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Scene scene = new Scene(pane, 800, 600);

        this.network = App.getNetwork();

        canvas = new Canvas(800, 600);
        gc = canvas.getGraphicsContext2D();

        pane.getChildren().add(canvas);

        drawNetwork();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Network Graph");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private void drawNetwork() {
        // Draw the edges between the cities
        for (int i = 0; i < this.network.getNumVertexes(); i++) {
            for (int j = 0; j < this.network.getNumVertexes(); j++) {
                if (this.network.getDistanceMatrix()[i][j] != 0) {
                    gc.setStroke(Color.BLACK);
                    gc.strokeLine(this.network.getVertexes()[i].getX(), this.network.getVertexes()[i].getY(),
                            this.network.getVertexes()[j].getX(), this.network.getVertexes()[j].getY());
                }
            }
        }

        // Draw the cities
        for (int i = 0; i < this.network.getNumVertexes(); i++) {
            gc.setFill(Color.RED);
            gc.fillOval(this.network.getVertexes()[i].getX() - 5, this.network.getVertexes()[i].getY() - 5, 10, 10);
            // Set the city name
        }
    }
}
