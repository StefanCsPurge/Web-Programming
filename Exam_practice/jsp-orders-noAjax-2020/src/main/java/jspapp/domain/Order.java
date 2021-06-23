package jspapp.domain;

public class Order {
    private String user;
    private int productId;
    private int quantity;

    public Order(String user, int productId, int quantity) {
        this.user = user;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getUser() {
        return user;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
