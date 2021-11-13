package ua.com.foxminded.classtimetable.service;

import java.util.List;

public interface ServiceInterface<T> {

    List<T> getAll();

    T getById(int id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(int id);

}
