package com.defecttracking.controller;

import com.defecttracking.model.ApplicationVO;
import com.defecttracking.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/applications")
@Slf4j
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationVO>> getApplication(){
        log.info("Inside the ApplicationController.getApplications");
        List<ApplicationVO> applicationVOS = null;
        try{
            applicationVOS = applicationService.findAll();
            log.info("Application details are not found");
            if (CollectionUtils.isEmpty(applicationVOS)){
                log.info("Application details are not found");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}
        catch (Exception exception){
            log.error("Exception while calling getApplications", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<ApplicationVO>>(applicationVOS, HttpStatus.OK);
    }
}
