package christmasPastryShop.entities.delicacies.interfaces;

public class Gingerbread extends BaseDelicacy{
    public static final double InitialGingerbreadPortion =200;


    public Gingerbread(String name, double price) {

        super(name,InitialGingerbreadPortion, price);
    }
}
