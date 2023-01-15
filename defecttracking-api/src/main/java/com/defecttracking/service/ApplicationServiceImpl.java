package com.defecttracking.service;

import com.defecttracking.entity.Application;
import com.defecttracking.model.ApplicationVO;
import com.defecttracking.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public List<ApplicationVO> findAll() {
        log.info("Inside ApplicationServiceImpl.findAll");
        List<Application> applications = applicationRepository.findAll();
        log.info("Find all application response: {}",applications);
        List<ApplicationVO> applicationVOS = applications.parallelStream().map(application -> {
            ApplicationVO  applicationVO = new ApplicationVO();
            applicationVO.setApplicationId(application.getApplicationId());
            applicationVO.setDescription(application.getDescription());
            applicationVO.setApplicationName(application.getApplicationName());
            applicationVO.setOwner(application.getOwner());
            return applicationVO;
        }).collect(Collectors.toList());
        return applicationVOS;
    }

}
