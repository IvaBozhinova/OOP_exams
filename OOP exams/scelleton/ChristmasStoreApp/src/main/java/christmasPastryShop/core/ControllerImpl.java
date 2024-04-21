package christmasPastryShop.core;

import christmasPastryShop.common.ExceptionMessages;
import christmasPastryShop.common.OutputMessages;
import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.interfaces.OpenBooth;
import christmasPastryShop.entities.booths.interfaces.PrivateBooth;
import christmasPastryShop.entities.cocktails.interfaces.Hibernation;
import christmasPastryShop.entities.cocktails.interfaces.MulledWine;
import christmasPastryShop.entities.delicacies.interfaces.BaseDelicacy;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Gingerbread;
import christmasPastryShop.entities.delicacies.interfaces.Stolen;
import christmasPastryShop.repositories.interfaces.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {


    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository
            , CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository)
    {
    }
    DelicacyRepository<Delicacy> delicacyRepository=new DelicacyRepositoryImpl<Delicacy>();
    CocktailRepository<Cocktail> cocktailRepository=new CocktailRepositoryImpl<Cocktail>();
    BoothRepository<Booth> boothRepository=new BoothRepositoryImpl<Booth>();
    double totalIncome=0;

    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy;
        if(type.equals("Gingerbread")){
            delicacy=new Gingerbread(name, price);
        }
        else{
            delicacy=new Stolen(name, price);
        }
        Collection<Delicacy> delicacies=delicacyRepository.getAll();
      boolean isExisting =delicacies.contains(delicacy);
        if(isExisting){
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,type,name));

        }
        delicacyRepository.add(delicacy);
        return String.format(OutputMessages.DELICACY_ADDED,name,type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail;
        if(type.equals("Hibernation")){
            cocktail=new Hibernation(name,size,brand);
        }
        else{
            cocktail=new MulledWine(name,size,brand);
        }
        Collection<Cocktail> cocktails=cocktailRepository.getAll();
        boolean isExisting =cocktails.contains(cocktail);
        if(isExisting){
            throw new IllegalArgumentException(String.format(ExceptionMessages.FOOD_OR_DRINK_EXIST,type,name));

        }
        cocktailRepository.add(cocktail);
        return String.format(OutputMessages.COCKTAIL_ADDED,name,type);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth;
        if(type.equals("OpenBooth")){
            booth=new OpenBooth(boothNumber,capacity);
        }
        else{
            booth= new PrivateBooth(boothNumber,capacity);
        }
        Collection<Booth> booths= boothRepository.getAll();
        boolean isExisting =booths.contains(booth);
        if(isExisting){
            throw new IllegalArgumentException(String.format(ExceptionMessages.BOOTH_EXIST,boothNumber));

        }
        boothRepository.add(booth);
        return String.format(OutputMessages.BOOTH_ADDED,boothNumber);
    }


    @Override
    public String reserveBooth(int numberOfPeople) {
       Collection<Booth> booths =boothRepository.getAll().stream()
                .filter(d -> d.getCapacity() > numberOfPeople && !d.isReserved()).findFirst().stream().toList();

        if (!booths.isEmpty()) {
            throw new IllegalArgumentException(String.format("Booth %d has been reserved for %d people!",numberOfPeople));
        }
return String.format(OutputMessages.RESERVATION_NOT_POSSIBLE,numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
      Booth booth= boothRepository.getByNumber(boothNumber);
      double bill= booth.getBill();
      booth.clear();;
      totalIncome+=bill;
      return String.format(OutputMessages.BILL,boothNumber,bill);
    }

    @Override
    public String getIncome() {
        return String.format("Income: %.2flv",totalIncome);
    }
}
