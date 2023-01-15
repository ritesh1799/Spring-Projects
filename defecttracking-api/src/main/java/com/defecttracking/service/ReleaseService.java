package com.defecttracking.service;

import com.defecttracking.model.ReleaseVO;

import java.util.List;

public interface ReleaseService {
    List<ReleaseVO> findAll();
}
