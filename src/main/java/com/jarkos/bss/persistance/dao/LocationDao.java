package com.jarkos.bss.persistance.dao;

import com.jarkos.bss.persistance.entity.Location;
import com.jarkos.bss.persistance.entity.Station;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Jarek on 2015-12-25.
 */
@Transactional
@Repository
public class LocationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Location location) {
        entityManager.merge(location);
    }

    public List<Location> getAll() {
        return entityManager.createQuery("SELECT b FROM Location AS b", Location.class).getResultList();
    }
}
