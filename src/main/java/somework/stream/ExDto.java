package somework.stream;


public class ExDto {

    private String exType;

    private String name;

    private int price;


    public String getExType() {
        return exType;
    }

    public void setExType(String exType) {
        this.exType = exType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ExDto(String exType, String name, int price) {
        this.exType = exType;
        this.name = name;
        this.price = price;
    }


    @Override
    public String toString() {
        return "ExDto{" +
                "exType='" + exType + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
