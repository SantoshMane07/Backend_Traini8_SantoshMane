package com.traini8.trainingcenters.traini8_registry.services;

import com.traini8.trainingcenters.traini8_registry.dto.TrainingCenterDto;
import com.traini8.trainingcenters.traini8_registry.entities.TrainingCenter;
import com.traini8.trainingcenters.traini8_registry.repositories.TrainingCenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TrainingCenterService {
    private final TrainingCenterRepository trainingCenterRepository;
    private final ModelMapper modelMapper;

    public TrainingCenterDto saveTrainingCenter(TrainingCenterDto trainingCenterDto) {
        log.info("Saving trainingCenter in DB");
        TrainingCenter trainingCenter = modelMapper.map(trainingCenterDto,TrainingCenter.class);
        TrainingCenter savedtrainingCenter = trainingCenterRepository.save(trainingCenter);
        log.info("Saved trainingCenter in DB successfully");
        return modelMapper.map(savedtrainingCenter, TrainingCenterDto.class);
    }

    public Page<TrainingCenterDto> getAllTrainingCenters(PageRequest pageRequest,String city, String state) {
        log.info("Fetching trainingCenters from DB");

        Page<TrainingCenter> trainingCenters;

        if (city != null && state != null) {
            trainingCenters = trainingCenterRepository.findByAddressCityIgnoreCaseAndAddressStateIgnoreCase(city, state, pageRequest);
        } else if (city != null) {
            trainingCenters = trainingCenterRepository.findByAddressCityIgnoreCase(city, pageRequest);
        } else if (state != null) {
            trainingCenters = trainingCenterRepository.findByAddressStateIgnoreCase(state, pageRequest);
        } else {
            trainingCenters = trainingCenterRepository.findAll(pageRequest);
        }

        log.info("Fetched trainingCenters from DB successfully");
        return trainingCenters.map(
                trainingCenter -> modelMapper.map(trainingCenter, TrainingCenterDto.class)
        );
    }

}
