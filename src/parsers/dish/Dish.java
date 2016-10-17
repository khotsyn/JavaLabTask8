package parsers.dish;

public class Dish {

    private int id;
    private String dishType;
    private String photo;
    private String name;
    private String description;
    private String price;
    private String portion;

    public int getId() {
        return id;
    }
    public String getDishType() {
        return dishType;
    }
    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }
}