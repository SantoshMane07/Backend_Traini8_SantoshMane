package com.traini8.trainingcenters.traini8_registry.controllers;

import com.traini8.trainingcenters.traini8_registry.dto.TrainingCenterDto;
import com.traini8.trainingcenters.traini8_registry.entities.TrainingCenter;
import com.traini8.trainingcenters.traini8_registry.services.TrainingCenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/training-centers")
public class TrainingCenterController {
    private final TrainingCenterService trainingCenterService;

    @PostMapping
    public ResponseEntity<TrainingCenterDto> saveTrainingCenter(@RequestBody @Valid TrainingCenterDto trainingCenterDto) {
        return ResponseEntity.ok(trainingCenterService.saveTrainingCenter(trainingCenterDto));
    }

    @GetMapping
    public ResponseEntity<Page<TrainingCenterDto>> getAllTrainingCenters(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(defaultValue = "0") Integer pageOffset,
            @RequestParam(defaultValue = "10", required = false) Integer pageSize
    ) {
        PageRequest pageRequest = PageRequest.of(pageOffset, pageSize,
                Sort.by(Sort.Direction.DESC, "createdOn", "id"));
        return ResponseEntity.ok(trainingCenterService.getAllTrainingCenters(pageRequest,city,state));
    }
}
