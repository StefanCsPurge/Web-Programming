package model;

public class VDestination {
    private int id;
    private String destination;
    private String country;
    private int price;

    public VDestination(int id, String destination, String country, int price) {
        this.id = id;
        this.destination = destination;
        this.country = country;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
