package pl.bodolsog.greenwave.model;

public interface Dao<T> {

    boolean create(T object);
    //<T> T read();
    //boolean update();
    //boolean delete();

}
