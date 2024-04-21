package christmasPastryShop.entities.cocktails.interfaces;

public abstract class BaseCocktail implements Cocktail{
private String name;
private int size;
private double price;
private String brand;



    protected BaseCocktail(String name, int size, double price, String brand){
        setName(name);
        setSize(size);
        setPrice(price);
        setBrand(brand);
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String toString() {
        return String.format("%s %s - %dml - %.2flv",getName(),getBrand(),getSize(),getPrice());
    }
}
