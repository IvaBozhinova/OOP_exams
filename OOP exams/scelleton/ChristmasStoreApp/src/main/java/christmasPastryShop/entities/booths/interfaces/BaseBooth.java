package christmasPastryShop.entities.booths.interfaces;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseBooth implements Booth{
    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;


protected BaseBooth(int boothNumber, int capacity, double pricePerPerson){
    setBoothNumber(boothNumber);
    setCapacity(capacity);
    setPricePerPerson(pricePerPerson);
    this.delicacyOrders=new ArrayList<>();
    this.cocktailOrders=new ArrayList<>();
}

    public void setBoothNumber(int boothNumber) {
        this.boothNumber = boothNumber;
    }

    public void setCapacity(int capacity) {
        if(capacity<0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_TABLE_CAPACITY);
        }
    this.capacity = capacity;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if(numberOfPeople<=0){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_NUMBER_OF_PEOPLE);
        }
    this.numberOfPeople = numberOfPeople;
    }

    public void setPricePerPerson(double pricePerPerson) {
        this.pricePerPerson = pricePerPerson;
    }



    @Override
    public int getBoothNumber() {
        return this.boothNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {

        setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
        this.price = numberOfPeople * this.pricePerPerson;
    }



    @Override
    public double getBill() {

        double priceAllDel = this.delicacyOrders.stream()
                .mapToDouble(Delicacy::getPrice)
                .sum();
        double priceAllCock = this.cocktailOrders.stream()
                .mapToDouble(Cocktail::getPrice)
                .sum();
        double bill = priceAllCock + priceAllDel;

        return bill;
    }

    @Override
    public void clear() {
       delicacyOrders.clear();
       cocktailOrders.clear();
       setNumberOfPeople(0);
      this.price=0 ;
    }
}
