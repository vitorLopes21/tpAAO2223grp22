package tpaao.DataStructure;

public class City {
    private String name;
    private int x;
    private int y;

    public City(String name) {
        this.name = name;
        // Generate random x and y coordinates
        this.x = (int) (Math.random() * 100);
        this.y = (int) (Math.random() * 100);
    }

    // Method to get the name of a city
    public String getName() {
        return this.name;
    }

    // Method to get the x coordinate of a city
    public int getX() {
        return this.x;
    }

    // Method to get the y coordinate of a city
    public int getY() {
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
}
