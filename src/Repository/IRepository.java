package Repository;

import Models.Package;

import java.util.List;

public interface IRepository<T> {
    void add(T pack);
    List<T> getPacks();
}
