package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Transactional
public abstract class AbstractDao<T extends Serializable> {

    private Class<T> clazz;

    @Autowired
    EntityManager entityManager;

    public void setClazz(Class<T> clazzToSet) {
        clazz = clazzToSet;
    }

    public T getById(int id) {
        return entityManager.find(clazz, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return entityManager.createQuery("from " + clazz.getName()).getResultList();
    }

    public void create(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void deleteById(int id) {
        delete(getById(id));
    }

}
