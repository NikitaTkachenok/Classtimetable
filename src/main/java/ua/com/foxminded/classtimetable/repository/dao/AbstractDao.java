package ua.com.foxminded.classtimetable.repository.dao;

import org.springframework.beans.factory.annotation.Autowired;
import ua.com.foxminded.classtimetable.repository.entities.CommonEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public abstract class AbstractDao<T extends CommonEntity> {

    public Class<T> clazz;

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
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public void deleteById(int id) {
        delete(getById(id));
    }

}
