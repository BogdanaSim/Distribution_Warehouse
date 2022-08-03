package Service;

import java.util.List;

public interface IService<T> {
    void add(T elem);

    List<T> getAll();
}
