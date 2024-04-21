package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.booths.interfaces.BaseBooth;
import christmasPastryShop.entities.booths.interfaces.Booth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BoothRepositoryImpl<T> implements BoothRepository<Booth> {
    private Collection<Booth> booth;

    public BoothRepositoryImpl(){
        this. booth=new ArrayList<>();
    }
    @Override
    public Booth getByNumber(int number) {
       return booth.stream()
                .filter(d ->d.getBoothNumber()==number)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<Booth> getAll() {
        return  Collections.unmodifiableCollection(booth);
    }

    @Override
    public void add(Booth booth1) {
    this.booth.add(booth1);
    }
}
