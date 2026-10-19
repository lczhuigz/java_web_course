public class Clothes {
    private int id;
    private String name;
    private String style;
    private double price;
    // 构造函数
    public Clothes() {}

    public Clothes(String name, String style, double price) {
        this.name = name;
        this.style = style;
        this.price = price;
    }
    public Clothes(int id, String name, String style, double price) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.price = price;
    }
    // Getter和Setter方法
    public int getId() {
        return id;
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
    public String getStyle() {
        return style;
    }
    public void setStyle(String style) {
        this.style = style;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
