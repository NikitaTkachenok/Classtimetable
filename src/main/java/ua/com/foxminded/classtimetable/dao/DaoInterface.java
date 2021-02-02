package ua.com.foxminded.classtimetable.dao;

import java.util.List;

public interface DaoInterface<T> {

	T getById(int id);

	List<T> getAll();

	void create(T t);

	void update(T t);

	void delete(T t);
	
}
