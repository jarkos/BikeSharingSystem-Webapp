package com.jarkos.bss.service;

import com.jarkos.bss.persistance.dao.HistoryDao;
import com.jarkos.bss.persistance.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jarek on 2016-01-06.
 */

@Service
@Transactional
public class HistoryService {

    @Autowired
    private HistoryDao historyDao;

    public History findHistoryById(Integer id) {
        return historyDao.findHistoryById(id);
    }

    public List<History> findAllHistories() {
        return historyDao.findAllHistoryEntries();
    }

    public void saveNewHistory(History history) {
        historyDao.save(history);
    }

    public void deleteBike(History history) {
        historyDao.delete(history);
    }

    public void updateBike(History history){
        historyDao.update(history);
    }

    public List<History> findAllCustomersOperations() {
        return historyDao.findAllCustomersOperations();
    }

    public List<History> findAllBorrowBikeOperations() {
        return historyDao.findAllBorrowBikeOperations();
    }

    public List<History> findAllOperationsByCustomerId(Integer customerId) {
        return historyDao.findAllOperationsByCustomerId(customerId);
    }

    public List<History> findAllWorkersOperations() {
        return historyDao.findAllWorkersOperations();
    }

}
