package io.agrimind.backend.service;

import io.agrimind.backend.dto.request.CreateFarmRequest;
import io.agrimind.backend.dto.request.UpdateFarmRequest;
import io.agrimind.backend.dto.response.FarmResponse;
import io.agrimind.backend.entity.Farm;
import io.agrimind.backend.entity.User;
import io.agrimind.backend.exception.ResourceNotFoundException;
import io.agrimind.backend.mapper.FarmMapper;
import io.agrimind.backend.repository.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;
    private final CurrentUserService currentUserService;

    public FarmResponse createFarm(CreateFarmRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Farm farm = FarmMapper.toEntity(request);
        farm.setOwner(currentUser);

        Farm savedFarm = farmRepository.save(farm);

        return FarmMapper.toResponse(savedFarm);
    }

    public List<FarmResponse> getMyFarms() {

        User currentUser = currentUserService.getCurrentUser();

        return farmRepository.findByOwner(currentUser)
                .stream()
                .map(FarmMapper::toResponse)
                .toList();
    }
    public FarmResponse getFarmById(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        Farm farm = farmRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found."));

        return FarmMapper.toResponse(farm);
    }
    public FarmResponse updateFarm(Long id, UpdateFarmRequest request) {

        User currentUser = currentUserService.getCurrentUser();

        Farm farm = farmRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found."));

        farm.setName(request.getName());
        farm.setCountry(request.getCountry());
        farm.setRegion(request.getRegion());
        farm.setArea(request.getArea());

        Farm updatedFarm = farmRepository.save(farm);

        return FarmMapper.toResponse(updatedFarm);
    }
    public void deleteFarm(Long id) {

        User currentUser = currentUserService.getCurrentUser();

        Farm farm = farmRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new ResourceNotFoundException("Farm not found."));

        farmRepository.delete(farm);
    }
}