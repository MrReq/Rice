package io.agrimind.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFieldRequest {

    @NotBlank
    private String name;

    @NotNull
    private Double area;

    @NotBlank
    private String soilType;

    private Double latitude;

    private Double longitude;
}