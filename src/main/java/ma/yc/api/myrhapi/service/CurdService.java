package ma.yc.api.myrhapi.service;

public interface CurdService<T,D> {
    public T add(D o);
    public T update(D o);
    public T delete(D o);
    public T getAll();


}
