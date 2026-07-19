package io.agrimind.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFarmRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String country;

    @NotBlank
    private String region;

    @NotNull
    private Double area;
}