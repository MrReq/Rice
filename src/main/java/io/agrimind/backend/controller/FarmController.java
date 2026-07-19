package io.agrimind.backend.controller;

import io.agrimind.backend.dto.request.CreateFarmRequest;
import io.agrimind.backend.dto.request.UpdateFarmRequest;
import io.agrimind.backend.dto.response.FarmResponse;
import io.agrimind.backend.service.FarmService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
@RequiredArgsConstructor
public class FarmController {

    private final FarmService farmService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FarmResponse createFarm(
            @Valid @RequestBody CreateFarmRequest request) {

        return farmService.createFarm(request);
    }

    @GetMapping
    public List<FarmResponse> getMyFarms() {

        System.out.println("GET /api/farms");

        return farmService.getMyFarms();
    }
    @GetMapping("/{id}")
    public FarmResponse getFarmById(@PathVariable Long id) {

        return farmService.getFarmById(id);
    }
    @PutMapping("/{id}")
    public FarmResponse updateFarm(
            @PathVariable Long id,
            @Valid @RequestBody UpdateFarmRequest request) {

        return farmService.updateFarm(id, request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFarm(@PathVariable Long id) {

        farmService.deleteFarm(id);
    }
}