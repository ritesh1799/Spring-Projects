package com.defecttracking.controller;

import com.defecttracking.model.ReleaseVO;
import com.defecttracking.service.ReleaseService;
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
@RequestMapping("api/v1/release")
@Slf4j
public class ReleaseController {
    @Autowired
    ReleaseService releaseService;

    @GetMapping
    public ResponseEntity<List<ReleaseVO>> getRelease(){
        log.info("Inside the ReleaseController.getRelease");
        List<ReleaseVO> releaseVOS = null;
        try{
            releaseVOS = releaseService.findAll();
            log.info("Release details are not found");
            if(CollectionUtils.isEmpty(releaseVOS)){
                log.info("Release details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }}
        catch (Exception exception){
            log.error("Exception while calling getRelease", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<ReleaseVO>>(releaseVOS, HttpStatus.OK);
        }



}
