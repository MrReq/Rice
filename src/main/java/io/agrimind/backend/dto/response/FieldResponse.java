package io.agrimind.backend.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldResponse {

    private Long id;

    private String name;

    private Double area;

    private String soilType;

    private Double latitude;

    private Double longitude;
}