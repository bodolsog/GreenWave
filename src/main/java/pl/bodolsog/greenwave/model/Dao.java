package pl.bodolsog.greenwave.model;

public interface Dao<T> {

    long create(T object);
    boolean delete(long id);
    T read(long id);
    T update(T object);

}
