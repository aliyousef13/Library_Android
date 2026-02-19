package models;

public class Price {
    private int id;
    private String name;
    private float rating;
    private float price;
    private int img;

    public Price() {}

    public Price(int id, String name, float rating, float price, int img) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.price = price;
        this.img = img;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public float getRating() { return rating; }
    public float getPrice() { return price; }
    public int getImg() { return img; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRating(float rating) { this.rating = rating; }
    public void setPrice(float price) { this.price = price; }
    public void setImg(int img) { this.img = img; }
}
