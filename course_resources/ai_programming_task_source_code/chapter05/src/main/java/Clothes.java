public class Clothes {
    private int id;
    private String name;
    private double price;
    // 构造函数
    public Clothes(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getter 和 Setter 方法
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
