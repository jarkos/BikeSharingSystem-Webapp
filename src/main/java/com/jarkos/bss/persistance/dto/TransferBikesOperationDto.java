package com.jarkos.bss.persistance.dto;

import com.jarkos.bss.persistance.dto.BaseBikeOperationDto;

import java.util.List;

/**
 * Created by Jarek on 2016-01-06.
 */

public class TransferBikesOperationDto extends BaseBikeOperationDto {

    private List<Integer> bikeIds;

    public List<Integer> getBikeIds() {
        return bikeIds;
    }

    public void setBikeIds(List<Integer> bikeIds) {
        this.bikeIds = bikeIds;
    }
}
