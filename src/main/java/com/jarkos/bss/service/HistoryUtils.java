package com.jarkos.bss.service;

import com.jarkos.bss.persistance.dto.BorrowOperationDto;
import com.jarkos.bss.persistance.dto.TransferBikesOperationDto;
import com.jarkos.bss.persistance.entity.History;
import com.jarkos.bss.persistance.enums.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Jarek on 2016-01-06.
 */
@Component
public class HistoryUtils {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserService userService;

    public void addHistoryEntry(OperationType operationType, BorrowOperationDto borrowBike) {
        History history = new History();
        history.setOperationType(operationType);
        history.setBikeId(borrowBike.getBikeId());
        history.setStationId(borrowBike.getStationId());
        history.setUserId(borrowBike.getUserId());
        String login = userService.findUserById(borrowBike.getUserId()).getUsername();
        history.setUserLogin(login);
        history.setCreated(borrowBike.getOperationDate());
        historyService.saveNewHistory(history);
    }

    public void addHistoryEntry(OperationType operationType, TransferBikesOperationDto transferBikesOperationDto) {
        for (Integer bikeId : transferBikesOperationDto.getBikeIds()) {
            History history = new History();
            history.setOperationType(operationType);
            history.setBikeId(bikeId);
            history.setStationId(transferBikesOperationDto.getStationId());
            history.setUserId(transferBikesOperationDto.getUserId());
            history.setCreated(transferBikesOperationDto.getOperationDate());
            historyService.saveNewHistory(history);
        }
    }

}
