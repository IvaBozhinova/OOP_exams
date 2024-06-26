package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CocktailRepositoryImpl<T> implements CocktailRepository<Cocktail>{
    private  Collection<Cocktail> cocktails;

    public CocktailRepositoryImpl(){
        this.cocktails=new ArrayList<>();
    }
    @Override
    public Cocktail getByName(String name) {
       return cocktails.stream()
                .filter(d ->d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Cocktail> getAll() {
        return  Collections.unmodifiableCollection(cocktails);
    }

    @Override
    public void add(Cocktail cocktail) {
     this.cocktails.add(cocktail);
    }
}
