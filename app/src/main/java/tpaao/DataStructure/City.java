package tpaao.DataStructure;

public class City {
    private String name;
    private double x;
    private double y;

    public City(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int compareTo(City city) {
        if (this.name.equals(city.getName()) && this.x == city.getX() && this.y == city.getY()) {
            return 0;
        }
        return -1;
    }

    public String toString() {
        return name;
    }
}
