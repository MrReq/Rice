package io.agrimind.backend.controller;

import io.agrimind.backend.dto.request.CreateFarmRequest;
import io.agrimind.backend.dto.response.FarmResponse;
import io.agrimind.backend.service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<FarmResponse> createFarm(
            @RequestParam Long ownerId,
            @Valid @RequestBody CreateFarmRequest request) {

        FarmResponse response = farmService.createFarm(ownerId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<FarmResponse>> getUserFarms(
            @RequestParam Long ownerId) {

        return ResponseEntity.ok(farmService.getUserFarms(ownerId));
    }
}