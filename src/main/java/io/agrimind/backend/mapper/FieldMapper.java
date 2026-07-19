package io.agrimind.backend.mapper;

import io.agrimind.backend.dto.request.CreateFieldRequest;
import io.agrimind.backend.dto.request.UpdateFieldRequest;
import io.agrimind.backend.dto.response.FieldResponse;
import io.agrimind.backend.entity.Field;

public class FieldMapper {

    private FieldMapper() {
    }

    public static Field toEntity(CreateFieldRequest request) {

        return Field.builder()
                .name(request.getName())
                .area(request.getArea())
                .soilType(request.getSoilType())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .build();
    }

    public static FieldResponse toResponse(Field field) {

        return FieldResponse.builder()
                .id(field.getId())
                .name(field.getName())
                .area(field.getArea())
                .soilType(field.getSoilType())
                .latitude(field.getLatitude())
                .longitude(field.getLongitude())
                .build();
    }

    public static void updateEntity(Field field, UpdateFieldRequest request) {

        field.setName(request.getName());
        field.setArea(request.getArea());
        field.setSoilType(request.getSoilType());
        field.setLatitude(request.getLatitude());
        field.setLongitude(request.getLongitude());
    }
}