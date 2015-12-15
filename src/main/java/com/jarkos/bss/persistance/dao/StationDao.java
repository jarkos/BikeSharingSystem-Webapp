package com.jarkos.bss.persistance.dao;

import com.jarkos.bss.persistance.entity.Station;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Jarek on 2015-12-15.
 */
@Transactional
@Repository
public class StationDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Station station) {
        entityManager.persist(station);
    }

    public void update(Station station) {
        entityManager.merge(station);
    }

    public List<Station> findAllStaions() {
        List<Station> stations = entityManager.createQuery("FROM Station", Station.class).getResultList();
        TypedQuery<Station> query = entityManager.createQuery("FROM Station", Station.class);
        Station b = getSingleResultOrNull(query);
        return entityManager.createQuery("SELECT b FROM Station AS b", Station.class).getResultList();
    }

    public Station findStationById(Integer id) {

        TypedQuery<Station> query = entityManager.createQuery("SELECT b FROM Station AS b WHERE b.id=:id", Station.class);
        query.setParameter("id", id);

        return getSingleResultOrNull(query);
    }

    private Station getSingleResultOrNull(TypedQuery<Station> query) {
        query.setMaxResults(1);
        List<Station> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void delete(Station station) {
        entityManager.remove(entityManager.contains(station) ? station : entityManager.merge(station));
    }

}
