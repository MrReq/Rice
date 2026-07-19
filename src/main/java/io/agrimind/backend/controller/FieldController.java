package io.agrimind.backend.controller;

import io.agrimind.backend.dto.request.CreateFieldRequest;
import io.agrimind.backend.dto.request.UpdateFieldRequest;
import io.agrimind.backend.dto.response.FieldResponse;
import io.agrimind.backend.service.FieldService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms/{farmId}/fields")
@RequiredArgsConstructor
public class FieldController {

    private final FieldService fieldService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FieldResponse createField(
            @PathVariable Long farmId,
            @Valid @RequestBody CreateFieldRequest request) {

        return fieldService.createField(farmId, request);
    }
    @GetMapping
    public List<FieldResponse> getFields(
            @PathVariable Long farmId) {

        return fieldService.getFields(farmId);
    }

    @GetMapping("/{fieldId}")
    public FieldResponse getField(
            @PathVariable Long farmId,
            @PathVariable Long fieldId) {

        return fieldService.getField(farmId, fieldId);
    }
    @PutMapping("/{fieldId}")
    public FieldResponse updateField(
            @PathVariable Long farmId,
            @PathVariable Long fieldId,
            @Valid @RequestBody UpdateFieldRequest request) {

        return fieldService.updateField(farmId, fieldId, request);
    }
    @DeleteMapping("/{fieldId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteField(
            @PathVariable Long farmId,
            @PathVariable Long fieldId) {

        fieldService.deleteField(farmId, fieldId);
    }
}