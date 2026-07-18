package io.agrimind.backend.mapper;

import io.agrimind.backend.dto.request.CreateFarmRequest;
import io.agrimind.backend.dto.response.FarmResponse;
import io.agrimind.backend.entity.Farm;

public final class FarmMapper {

    private FarmMapper() {
    }

    public static Farm toEntity(CreateFarmRequest request) {

        if (request == null) {
            return null;
        }

        return Farm.builder()
                .name(request.getName())
                .country(request.getCountry())
                .region(request.getRegion())
                .area(request.getArea())
                .build();
    }

    public static FarmResponse toResponse(Farm farm) {

        if (farm == null) {
            return null;
        }

        return FarmResponse.builder()
                .id(farm.getId())
                .name(farm.getName())
                .country(farm.getCountry())
                .region(farm.getRegion())
                .area(farm.getArea())
                .build();
    }
}