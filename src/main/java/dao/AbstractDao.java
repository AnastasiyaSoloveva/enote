package dao;

public interface AbstractDao<T> {
    void deleteById(int id);
    void create(T obj);
    void update(T obj);
    T findById(int id);
}
