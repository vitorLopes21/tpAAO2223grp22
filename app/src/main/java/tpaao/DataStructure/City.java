package tpaao.DataStructure;

public class City {
    private String name;
    private double x;
    private double y;

    public City(String name, double x, double y) {
        this.name = name;
        // Generate random x and y coordinates
        this.x = x;
        this.y = y;
    }

    // Method to get the name of a city
    public String getName() {
        return this.name;
    }

    // Method to get the x coordinate of a city
    public double getX() {
        return this.x;
    }

    // Method to get the y coordinate of a city
    public double getY() {
        return this.y;
    }

    // Method to compare two cities
    public int compareTo(City city) {

        // If the cities are the same, return 0
        if (this.name.equals(city.getName()) && this.x == city.getX() && this.y == city.getY()) {
            return 0;
        }

        // If the cities are not the same, return -1
        return -1;
    }

    public String toString () {
        return this.name;
    }
}
