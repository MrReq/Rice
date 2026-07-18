package io.agrimind.backend.service;

import io.agrimind.backend.dto.request.CreateFarmRequest;
import io.agrimind.backend.dto.response.FarmResponse;
import io.agrimind.backend.entity.Farm;
import io.agrimind.backend.entity.User;
import io.agrimind.backend.mapper.FarmMapper;
import io.agrimind.backend.repository.FarmRepository;
import io.agrimind.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmService {

    private final FarmRepository farmRepository;
    private final UserRepository userRepository;

    public FarmResponse createFarm(Long ownerId, CreateFarmRequest request) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Farm farm = FarmMapper.toEntity(request);
        farm.setOwner(owner);

        Farm savedFarm = farmRepository.save(farm);

        return FarmMapper.toResponse(savedFarm);
    }

    public List<FarmResponse> getUserFarms(Long ownerId) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return farmRepository.findByOwner(owner)
                .stream()
                .map(FarmMapper::toResponse)
                .toList();
    }
}