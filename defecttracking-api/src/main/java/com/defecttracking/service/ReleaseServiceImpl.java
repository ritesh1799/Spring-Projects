package com.defecttracking.service;

import com.defecttracking.entity.Release;
import com.defecttracking.model.ReleaseVO;
import com.defecttracking.repository.ReleaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReleaseServiceImpl implements ReleaseService{

    @Autowired
    ReleaseRepository releaseRepository;

    @Override
    public List<ReleaseVO> findAll() {
        log.info("Inside the release release.findAll");
        List<Release> releases =  releaseRepository.findAll();
        log.info("Find all release response: {}",releases);
        List<ReleaseVO> releaseVOS = releases.parallelStream().map(release -> {
                    ReleaseVO releaseVO = new ReleaseVO();
                    releaseVO.setReleaseId(release.getReleaseId());
                    releaseVO.setDescription(release.getDescription());
                    releaseVO.setRelease_date(release.getRelease_date());
                   // releaseVO.setApplicationVO(releaseVO.getApplicationVO());
                    return releaseVO;
                }).collect(Collectors.toList());
        return releaseVOS;
    }
}
