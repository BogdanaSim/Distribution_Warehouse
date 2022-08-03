package Service;

import Models.Package;
import Repository.Repository;

import java.util.List;

public class Service<T extends Package> implements IService<T>{
    private Repository<T> repository;

    public Service() {
        repository=new Repository<T>();
    }

    public Service(Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public void add(T elem) {
        repository.add(elem);
    }

    @Override
    public List<T> getAll() {
        return repository.getPacks();
    }

}
