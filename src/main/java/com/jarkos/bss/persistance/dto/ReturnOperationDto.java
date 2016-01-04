package com.jarkos.bss.persistance.dto;

import java.util.Date;

/**
 * Created by Jarek on 2016-01-04.
 */
public class ReturnOperationDto extends BorrowOperationDto{

    public void setBorrowDate(Date borrowDate) {
        Date date = new Date();
        this.borrowDate = date;
    }

}
