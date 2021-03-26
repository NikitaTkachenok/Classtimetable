package ua.com.foxminded.classtimetable.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.foxminded.classtimetable.entities.Building;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BuildingDao implements DaoInterface<Building> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Building getById(int id) {
        return getCurrentSession().get(Building.class, id);
    }

    @Override
    public List<Building> getAll() {
        return getCurrentSession().createQuery("from " + Building.class.getName()).list();
    }

    @Override
    public void create(Building building) {
        getCurrentSession().save(building);
    }

    @Override
    public void update(Building building) {
        getCurrentSession().merge(building);
    }

    @Override
    public void delete(Building building) {
        getCurrentSession().delete(building);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
