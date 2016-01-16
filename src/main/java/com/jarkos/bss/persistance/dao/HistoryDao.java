package com.jarkos.bss.persistance.dao;

import com.jarkos.bss.persistance.entity.History;
import com.jarkos.bss.persistance.enums.OperationType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Jarek on 2016-01-06.
 */

@Repository
@Transactional
public class HistoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(History historyEntry) {
        entityManager.persist(historyEntry);
    }

    public void update(History historyEntry) {
        entityManager.merge(historyEntry);
    }

    public List<History> findAllHistoryEntries() {
        return entityManager.createQuery("SELECT b FROM History AS b", History.class).getResultList();
    }

    public List<History> findHistoryById(Integer stationId) {
        TypedQuery<History> query = entityManager.createQuery("SELECT b FROM History AS b WHERE b.stationId=:stationId ",
                History.class);
        query.setParameter("stationId", stationId);
        return query.getResultList();
    }

    private History getSingleResultOrNull(TypedQuery<History> query) {
        query.setMaxResults(1);
        List<History> list = query.getResultList();
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public void delete(History historyEntry) {
        entityManager.remove(entityManager.contains(historyEntry) ? historyEntry : entityManager.merge(historyEntry));
    }

    public List<History> findAllBorrowBikeOperations() {
        TypedQuery<History> query = entityManager.createQuery("SELECT b FROM History AS b WHERE b.operationType=:operation_type",
                History.class);
        query.setParameter("operation_type", OperationType.BORROW_BIKE);

        return query.getResultList();
    }

    public List<History> findAllCustomersOperations() {
        TypedQuery<History> query = entityManager.createQuery("SELECT b FROM History AS b WHERE " +
                "b.operationType=:operation_type_borrow OR b.operationType=:operation_type_return", History.class);
        query.setParameter("operation_type_borrow", OperationType.BORROW_BIKE);
        query.setParameter("operation_type_return", OperationType.RETURN_BIKE);

        return query.getResultList();
    }

    public List<History> findAllOperationsByCustomerId(Integer user_id) {
        TypedQuery<History> query = entityManager.createQuery("SELECT b FROM History AS b WHERE " +
                "b.operationType=:operation_type_borrow OR b.operationType=:operation_type_return" +
                " AND b.userId=:user_id", History.class);
        query.setParameter("operation_type_borrow", OperationType.BORROW_BIKE);
        query.setParameter("operation_type_return", OperationType.RETURN_BIKE);
        query.setParameter("user_id", user_id);

        return query.getResultList();
    }

    public List<History> findAllWorkersOperations() {
        TypedQuery<History> query = entityManager.createQuery("SELECT b FROM History AS b WHERE " +
                "b.operationType=:operation_type1 OR b.operationType=:operation_type2", History.class);
        query.setParameter("operation_type1", OperationType.TRANSFER_B_FROM);
        query.setParameter("operation_type2", OperationType.TRANSFER_B_TO);

        return query.getResultList();
    }

}
