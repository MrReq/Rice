package io.agrimind.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateFarmRequest {

    @NotBlank
    private String name;

    private String country;

    private String region;

    private Double area;
}