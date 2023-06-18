package tpaao.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tpaao.DataStructure.City;
import tpaao.DataStructure.Network;

import java.util.List;

public class NetworkGUI extends Application {
    private Network network;
    private int[] optimalPath;
    private Pane pane;
    private double originX;
    private double originY;
    private double canvasWidth;
    private double canvasHeight;
    private double moveStep = 10.0;
    private double scalingFactor = 100.0;

    public void setNetwork(Network network) {
        this.network = network;
    }

    public void setOptimalPath(int[] optimalPath) {
        this.optimalPath = optimalPath;
    }

    @Override
    public void start(Stage primaryStage) {
        pane = new Pane();
        pane.setPadding(new Insets(10));

        List<City> cities = network.getCities();
        double radius = 5.0;

        calculateOriginAndCanvasSize();

        // Create circles for cities
        for (City city : cities) {
            double x = (city.getX() + originX) * scalingFactor;
            double y = (city.getY() + originY) * scalingFactor;

            Circle circle = new Circle(x, y, radius);
            circle.setFill(Color.GREEN);
            pane.getChildren().add(circle);

            Text cityText = new Text(x - 10, y - 10, city.getName());
            cityText.setFill(Color.BLUE);
            pane.getChildren().add(cityText);

        }

        // Create lines for all possible paths
        double[][] distanceMatrix = network.getDistanceMatrix();
        int numCities = network.getNumVertexes();

        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                if (i != j) {
                    double distance = distanceMatrix[i][j];

                    City cityA = cities.get(i);
                    City cityB = cities.get(j);

                    Line line = new Line((cityA.getX() + originX) * scalingFactor,
                            (cityA.getY() + originY) * scalingFactor,
                            (cityB.getX() + originX) * scalingFactor, (cityB.getY() + originY) * scalingFactor);
                    line.setStroke(Color.GRAY);
                    line.setStrokeWidth(1.0);
                    pane.getChildren().add(line);

                    Text distanceText = new Text((cityA.getX() + cityB.getX()) / 2 * scalingFactor,
                            (cityA.getY() + cityB.getY()) / 2 * scalingFactor, String.format("%.2f", distance));
                    pane.getChildren().add(distanceText);
                }
            }
        }

        // Create lines for the optimal path
        if (optimalPath != null) {
            for (int i = 0; i < optimalPath.length - 1; i++) {
                int cityIndexA = optimalPath[i];
                int cityIndexB = optimalPath[i + 1];

                City cityA = cities.get(cityIndexA);
                City cityB = cities.get(cityIndexB);

                Line line = new Line((cityA.getX() + originX) * scalingFactor, (cityA.getY() + originY) * scalingFactor,
                        (cityB.getX() + originX) * scalingFactor, (cityB.getY() + originY) * scalingFactor);
                line.setStroke(Color.RED);
                line.setStrokeWidth(2.0);
                pane.getChildren().add(line);
            }
        }

        Scene scene = new Scene(pane, canvasWidth, canvasHeight);
        scene.setOnKeyPressed(event -> handleKeyPress(event.getCode()));

        primaryStage.setTitle("TSP Network");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void calculateOriginAndCanvasSize() {
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (City city : network.getCities()) {
            double x = city.getX();
            double y = city.getY();

            if (x < minX)
                minX = x;
            if (y < minY)
                minY = y;
            if (x > maxX)
                maxX = x;
            if (y > maxY)
                maxY = y;
        }

        double offsetX = (maxX - minX) / 2.0;
        double offsetY = (maxY - minY) / 2.0;

        originX = offsetX - minX;
        originY = offsetY - minY;

        canvasWidth = (maxX - minX + offsetX * 2) * scalingFactor;
        canvasHeight = (maxY - minY + offsetY * 2) * scalingFactor;
    }

    private void handleKeyPress(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                pane.setLayoutY(pane.getLayoutY() + moveStep);
                break;
            case DOWN:
                pane.setLayoutY(pane.getLayoutY() - moveStep);
                break;
            case LEFT:
                pane.setLayoutX(pane.getLayoutX() + moveStep);
                break;
            case RIGHT:
                pane.setLayoutX(pane.getLayoutX() - moveStep);
                break;
        }
    }
}
