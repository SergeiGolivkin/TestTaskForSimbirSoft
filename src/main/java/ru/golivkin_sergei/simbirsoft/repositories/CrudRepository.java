package ru.golivkin_sergei.simbirsoft.repositories;

public interface CrudRepository<T> {
     void save(T entity);
}
