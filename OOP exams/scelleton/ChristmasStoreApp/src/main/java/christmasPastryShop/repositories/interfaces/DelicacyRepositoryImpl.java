package christmasPastryShop.repositories.interfaces;

import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DelicacyRepositoryImpl<T> implements DelicacyRepository<Delicacy>{

    private Collection<Delicacy> delicacies;

    public DelicacyRepositoryImpl(){
        this.delicacies=new ArrayList<>();
    }
    @Override
    public Collection<Delicacy>getAll() {
        return  Collections.unmodifiableCollection(delicacies);
    }

    @Override
    public void add(Delicacy delicacy) {
     this.delicacies.add(delicacy);
    }

    @Override
    public Delicacy getByName(String name) {
        return delicacies.stream()
                .filter(d ->d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
