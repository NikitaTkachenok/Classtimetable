package ua.com.foxminded.classtimetable.repository.dao;

import java.util.List;

public interface DaoInterface<T> {

    T getById(int id);

    List<T> getAll();

    void create(T entity);

    void update(T entity);

    void delete(T entity);

}
