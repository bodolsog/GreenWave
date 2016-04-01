package pl.bodolsog.greenwave.model;

public interface Dao<T> {

    boolean create(T object);
    boolean delete(long id);
    T read(long id);
    //boolean update();

}
