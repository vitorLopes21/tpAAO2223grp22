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
    private int optimalPathIndex;
    private Canvas canvas;
    private GraphicsContext gc;
    private static final double ZOOM_FACTOR = 1.1; // Increase zoom by 10%
    private static final double MIN_ZOOM = 0.1;
    private double zoomLevel = 1.0;

    public NetworkGUI() {
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1440, 600);

        this.network = App.getNetwork();
        this.optimalPathIndex = App.getResult();

        canvas = new Canvas(1440, 1200);
        gc = canvas.getGraphicsContext2D();

        pane.getChildren().add(canvas);

        int[] optimalPath = convertIndexToPath(optimalPathIndex, network.getNumVertexes()); // Convert index to path
                                                                                            // array

        drawNetwork(optimalPath);

        // Add event handlers for arrow key presses
        scene.setOnKeyPressed(event -> {
            double moveAmount = 100; // Adjust this value to control the movement speed

            switch (event.getCode()) {
                case UP:
                    canvas.setTranslateY(canvas.getTranslateY() + moveAmount);
                    break;
                case DOWN:
                    canvas.setTranslateY(canvas.getTranslateY() - moveAmount);
                    break;
                case LEFT:
                    canvas.setTranslateX(canvas.getTranslateX() + moveAmount);
                    break;
                case RIGHT:
                    canvas.setTranslateX(canvas.getTranslateX() - moveAmount);
                    break;
                case PLUS:
                case ADD:
                    zoomIn();
                    break;
                case MINUS:
                case SUBTRACT:
                    zoomOut();
                    break;
                default:
                    break;
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Network Graph");
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private void zoomIn() {
        zoomLevel *= ZOOM_FACTOR;
        applyZoom();
    }

    private void zoomOut() {
        zoomLevel /= ZOOM_FACTOR;
        if (zoomLevel < MIN_ZOOM) {
            zoomLevel = MIN_ZOOM;
        }
        applyZoom();
    }

    private void applyZoom() {
        canvas.setScaleX(zoomLevel);
        canvas.setScaleY(zoomLevel);
    }

    private int[] convertIndexToPath(int optimalPathIndex, int numberOfCities) {
        // Convert the optimal path index to an array of city indices
        // You need to implement this conversion based on your TSP problem
        // representation

        int[] optimalPath = new int[numberOfCities];
        // System.out.println("Number of cities: " + numberOfCities);
        for (int i = 1; i < numberOfCities; i++) {
            optimalPath[i] = i;
        }

        return optimalPath;
    }

    private void drawNetwork(int[] optimalPath) {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        double cityRadius = 10; // Radius of the city circle
        double padding = 50; // Padding around the network

        // Find the minimum and maximum coordinates
        for (int i = 1; i < network.getNumVertexes(); i++) {
            double x = network.getVertexes()[i].getX();
            double y = network.getVertexes()[i].getY();

            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }

        // Calculate the scaling factor based on the canvas size and the range of
        // coordinates
        double canvasWidth = canvas.getWidth();
        double canvasHeight = canvas.getHeight();
        double scaleX = (canvasWidth - 2 * padding) / (maxX - minX);
        double scaleY = (canvasHeight - 2 * padding) / (maxY - minY);
        double offsetX = -minX * scaleX + padding;
        double offsetY = -minY * scaleY + padding;

        // Clear the canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);

        // Draw the edges between the cities
        for (int i = 1; i < network.getNumVertexes(); i++) {
            for (int j = 1; j < network.getNumVertexes(); j++) {
                if (network.getDistanceMatrix()[i][j] != 0) {
                    double startX = network.getVertexes()[i].getX() * scaleX + offsetX;
                    double startY = network.getVertexes()[i].getY() * scaleY + offsetY;
                    double endX = network.getVertexes()[j].getX() * scaleX + offsetX;
                    double endY = network.getVertexes()[j].getY() * scaleY + offsetY;

                    gc.setStroke(Color.GRAY);
                    gc.strokeLine(startX, startY, endX, endY);
                }
            }
        }

        // Draw the cities and their circles
        for (int i = 1; i < network.getNumVertexes(); i++) {
            double x = network.getVertexes()[i].getX() * scaleX + offsetX;
            double y = network.getVertexes()[i].getY() * scaleY + offsetY;

            // Set border color and width
            gc.setStroke(Color.WHITE);
            gc.setLineWidth(2);

            // Draw circle border
            gc.strokeOval(x - cityRadius, y - cityRadius, 2 * cityRadius, 2 * cityRadius);

            // Set fill color for the circle
            gc.setFill(Color.BLUE);

            // Draw filled circle
            gc.fillOval(x - cityRadius, y - cityRadius, 2 * cityRadius, 2 * cityRadius);
        }

        // Draw the cities' names
        for (int i = 1; i < network.getNumVertexes(); i++) {
            double x = network.getVertexes()[i].getX() * scaleX + offsetX;
            double y = network.getVertexes()[i].getY() * scaleY + offsetY;

            gc.setFill(Color.BLACK);
            gc.fillText(network.getVertexes()[i].getName(), x + cityRadius, y - cityRadius);
        }

        // Draw the optimal path
        gc.setStroke(Color.RED);
        gc.setLineWidth(2);

        // Draw the edges between the cities
        for (int i = 1; i < optimalPath.length; i++) {
            int cityIndex1 = optimalPath[i]; // Connect the current city with the next city
            int cityIndex2 = optimalPath[(i + 1) % optimalPath.length]; // Connect the last city with the first city

            double startX = network.getVertexes()[cityIndex1].getX() * scaleX + offsetX;
            double startY = network.getVertexes()[cityIndex1].getY() * scaleY + offsetY;
            double endX = network.getVertexes()[cityIndex2].getX() * scaleX + offsetX;
            double endY = network.getVertexes()[cityIndex2].getY() * scaleY + offsetY;

            gc.strokeLine(startX, startY, endX, endY);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */