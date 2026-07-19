package io.agrimind.backend.service;

import io.agrimind.backend.dto.request.CreateFieldRequest;
import io.agrimind.backend.dto.request.UpdateFieldRequest;
import io.agrimind.backend.dto.response.FieldResponse;
import io.agrimind.backend.entity.Farm;
import io.agrimind.backend.entity.Field;
import io.agrimind.backend.entity.User;
import io.agrimind.backend.exception.ResourceNotFoundException;
import io.agrimind.backend.mapper.FieldMapper;
import io.agrimind.backend.repository.FarmRepository;
import io.agrimind.backend.repository.FieldRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FieldService {

    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;
    private final CurrentUserService currentUserService;

    public FieldResponse createField(Long farmId,
                                     CreateFieldRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Farm farm = farmRepository.findByIdAndOwner(farmId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Farm not found."));

        Field field = FieldMapper.toEntity(request);
        field.setFarm(farm);

        Field savedField = fieldRepository.save(field);

        return FieldMapper.toResponse(savedField);


    }
    public List<FieldResponse> getFields(Long farmId) {

        User currentUser = currentUserService.getCurrentUser();

        Farm farm = farmRepository.findByIdAndOwner(farmId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Farm not found."));

        return fieldRepository.findByFarm(farm)
                .stream()
                .map(FieldMapper::toResponse)
                .toList();
    }
    public FieldResponse getField(Long farmId, Long fieldId) {

        User currentUser = currentUserService.getCurrentUser();

        Farm farm = farmRepository.findByIdAndOwner(farmId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Farm not found."));

        Field field = fieldRepository.findByIdAndFarm(fieldId, farm)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Field not found."));

        return FieldMapper.toResponse(field);
    }
    private Farm getOwnedFarm(Long farmId) {

        User currentUser = currentUserService.getCurrentUser();

        return farmRepository.findByIdAndOwner(farmId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Farm not found."));
    }
    public FieldResponse updateField(
            Long farmId,
            Long fieldId,
            UpdateFieldRequest request) {

        Farm farm = getOwnedFarm(farmId);

        Field field = fieldRepository.findByIdAndFarm(fieldId, farm)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Field not found."));

        FieldMapper.updateEntity(field, request);

        Field updatedField = fieldRepository.save(field);

        return FieldMapper.toResponse(updatedField);
    }
    public void deleteField(Long farmId, Long fieldId) {

        Farm farm = getOwnedFarm(farmId);

        Field field = fieldRepository.findByIdAndFarm(fieldId, farm)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Field not found."));

        fieldRepository.delete(field);
    }
}