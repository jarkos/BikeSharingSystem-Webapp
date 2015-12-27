package com.jarkos.bss.persistance.dao;

import com.jarkos.bss.persistance.entity.Note;
import com.jarkos.bss.persistance.enums.BikeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Jarek on 2015-12-27.
 */
@Transactional(readOnly = false)
@Repository
public class NoteDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Note note) {
        entityManager.persist(note);
    }

    public void update(Note note) {
        entityManager.merge(note);
    }

    public List<Note> findAllNotes() {
        return entityManager.createQuery("SELECT b FROM Note AS b", Note.class).getResultList();
    }

    public Note findNoteById(Integer id) {

        TypedQuery<Note> query = entityManager.createQuery("SELECT b FROM Note AS b WHERE b.id=:id", Note.class);
        query.setParameter("id", id);

        return getSingleResultOrNull(query);
    }

    private Note getSingleResultOrNull(TypedQuery<Note> query) {
        query.setMaxResults(1);
        List<Note> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void delete(Note note) {
        entityManager.remove(entityManager.contains(note) ? note : entityManager.merge(note));
    }

    public List<Note> findAllNotesForBike(int bikeId) {
        TypedQuery<Note> query = entityManager.createQuery("SELECT b FROM Note AS b WHERE b.bike_id=:bike_id", Note.class);
        query.setParameter("bike_id", bikeId);
        return query.getResultList();
    }

}
