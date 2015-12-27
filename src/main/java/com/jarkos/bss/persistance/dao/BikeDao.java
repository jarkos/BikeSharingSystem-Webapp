package com.jarkos.bss.persistance.dao;

import com.jarkos.bss.persistance.entity.Bike;
import com.jarkos.bss.persistance.enums.BikeStatus;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Jarek on 2015-12-01.
 */
@Repository
@Transactional
public class BikeDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Bike bike) {
        entityManager.persist(bike);
    }

    public void update(Bike bike) {
        entityManager.merge(bike);
    }

    public List<Bike> findAllBikes() {
        return entityManager.createQuery("SELECT b FROM Bike AS b", Bike.class).getResultList();
    }

    public Bike findBikeById(Integer id) {

        TypedQuery<Bike> query = entityManager.createQuery("SELECT b FROM Bike AS b WHERE b.id=:id", Bike.class);
        query.setParameter("id", id);

        return getSingleResultOrNull(query);
    }

    private Bike getSingleResultOrNull(TypedQuery<Bike> query) {
        query.setMaxResults(1);
        List<Bike> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void delete(Bike bike) {
        entityManager.remove(entityManager.contains(bike) ? bike : entityManager.merge(bike));
    }

    public List<Bike> findAllNewBikes() {
        TypedQuery<Bike> query = entityManager.createQuery("SELECT b FROM Bike AS b WHERE b.bikeStatus=:bike_status", Bike.class);
        query.setParameter("bike_status", BikeStatus.NEW.name());

        return query.getResultList();
    }

    public List<Bike> findAllAvailableBikes() {
        TypedQuery<Bike> query = entityManager.createQuery("SELECT b FROM Bike AS b WHERE " +
                "b.bikeStatus=:bike_status1 OR b.bikeStatus=:bike_status2", Bike.class);
        query.setParameter("bike_status1", BikeStatus.NEW);
        query.setParameter("bike_status2", BikeStatus.TO_BORROW);

        return query.getResultList();
    }
}
